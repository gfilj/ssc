package com.project.service;

import com.project.model.api.ResponseVO;
import com.project.model.api.builder.LoadIssueVoBuilder;
import com.project.model.api.builder.LoadTimeVOBuilder;
import com.project.spider.model.Row;
import com.project.spider.service.sscservice.SSCRepoService;
import com.project.spider.task.SSCScheduleTask;
import com.project.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSCIssueService {
    @Autowired
    private SSCRepoService sscRepoService;

    @Autowired
    private SSCLotteryService sscLotteryService;

    @Autowired
    private SSCScheduleTask sscScheduleTask;

    @Autowired
    private SSCRecordService sscRecordService;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public ResponseVO loadtime(int gameId) {
        /**
         * 1.选择现在的时间，查找到上一期的结果
         * 2.查询上次的时间是否出现，如果没出现调度节点重新下发，在当前时间添加5s，轮次切换回来，如果存在继续下次进行
         */
        Row row = sscLotteryService.loadTime(gameId);
        String previousIssueNumer = sscLotteryService.getPreviousIssueNumer(row);
        Row previousRow = sscRepoService.selectByID(previousIssueNumer, gameId);
        /**
         * 要存入的时间数
         */
        long currentIssueEndTime;
        String currentIssueNumber;
        long nextIssueEndTime;
        String nextIssueNumber;

        if(previousRow==null){
            sscScheduleTask.per5MinuteTask();
            currentIssueEndTime = DateUtils.getDateUnixTimeStamp(DateUtils.skipSecond(5));
            currentIssueNumber = sscLotteryService.getPreviousIssueNumer(row);
            nextIssueEndTime = sscLotteryService.getCurrentIssueEndTime(row);
            nextIssueNumber = sscLotteryService.getCurrentIssueNumber(row);
        }else{
            logger.info("load time :" + previousRow.toString());
            //TODO 开奖
            rollUpLottery(gameId,previousIssueNumer,previousRow);
            currentIssueEndTime = sscLotteryService.getCurrentIssueEndTime(row);
            currentIssueNumber = sscLotteryService.getCurrentIssueNumber(row);
            nextIssueEndTime = sscLotteryService.getNextIssueEndTime(row);
            nextIssueNumber = sscLotteryService.getNextIssueNumber(row);
        }

        return LoadTimeVOBuilder.getInstance()
                .setIsSuccess(1)
                .setType("info")
                .setData(DateUtils.getDateUnixTimeStamp(),
                        currentIssueEndTime,
                        currentIssueNumber,
                        nextIssueEndTime,
                        nextIssueNumber).build();
    }


     /**
     * 开奖
     */
    public void rollUpLottery(int gameId, String previousIssueNumer, Row previousRow) {
         /**
         * 1.选择出未开奖的类型
         */
        Row row = sscRepoService.selectByID(previousIssueNumer, gameId);
        /**
         * 计算出中奖号码
         */
        int digit5 = previousRow.getInt("digit5");
        int digit4 = previousRow.getInt("digit4");
        int digit3 = previousRow.getInt("digit3");
        int digit2 = previousRow.getInt("digit2");
        int digit1 = previousRow.getInt("digit1");
        String winningNumber = digit5+","+digit4+","+digit3+","+digit2+","+digit1;
        logger.info("winningNumber");
//        if(row.getInt("rollout")==0){
            /**
             * 2.设置为已经开过奖
             */
            sscRepoService.updateRollUp(previousIssueNumer,gameId);
            /**
             * 3.选择出这个单号的下的所有订单
             */
            List<Row> rows = sscRecordService.selectOrdersByIssue(previousIssueNumer, gameId);
            /**
             * 4.将订单号码选出来
             */
            logger.info(rows.toString());
//        }
    }

    public Row loadIssue(int gameId) {
        Row row = sscLotteryService.loadTime(gameId);
        String previousIssueNumer = sscLotteryService.getPreviousIssueNumer(row);
        Row previousRow = sscRepoService.selectByID(previousIssueNumer, gameId);
        sscRepoService.selectRecentList(gameId,20);
        return LoadIssueVoBuilder.getInstance().setCurrentIssue(sscLotteryService.getCurrentIssueNumber(row))
                .setIssues(sscRepoService.selectRecentList(gameId,20)).build();
    }
}
