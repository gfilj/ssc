package com.project.service;

import com.project.model.api.BaseVO;
import com.project.model.api.builder.LotteryDOBuilder;
import com.project.model.api.builder.LotteryVOBuilder;
import com.project.spider.mapper.SSCLotteryMapper;
import com.project.spider.model.Row;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author goforit
 *
 */
@Service
public class SSCLotteryService implements InitializingBean {

	@Autowired
	private SSCLotteryMapper sscLotteryMapper;

	private Map<Integer,Row> lotterMap = new HashMap<Integer, Row>();

	public Map<Integer, Row> getLotterMap() {
		return lotterMap;
	}

	public BaseVO onlineLotterys() {
		return LotteryVOBuilder.getInstance()
		.setCode(1).setMsg("ok").setResult(lotterMap.values()).build();
	}

    /**
     * 根据id来选择玩法
     * @param id
     * @return
     */
	public Row selectById(int id) {
		Row param = Row.getInstance();
		param.put("id", id);
		return sscLotteryMapper.selectByPrimaryKey(param);
	}

    /**
     * load data to map
     * @throws Exception
     */
	@Override
	public void afterPropertiesSet() throws Exception {
		sscLotteryMapper.selectAll().forEach((v)->{
            v= LotteryDOBuilder.getInstance().setRow(v).setBeginDate().setEndDate().setChangeDate().build();
			lotterMap.put(v.getInt("id"),v);
		});
	}

    /**
     * 设置每期的时间
     * @param id
     * @return
     */
	public Row loadTime(int id){
        return LotteryDOBuilder.getInstance().setRow(lotterMap.get(id)).setSSCIssueLoad().build();
    }

    /**
     * 分装对字段的获取
     * @param row
     * @return
     */
    public String getPreviousIssueNumer(Row row){
        return row.getString("previousIssueNumer");
    }

    public long getPreviousIssueEndTime(Row row){
        return row.getLong("previousIssueEndTime");
    }

    public String getCurrentIssueNumber(Row row){
        return row.getString("currentIssueNumber");
    }

    public long getCurrentIssueEndTime(Row row){
        return row.getLong("currentIssueEndTime");
    }

    public String getNextIssueNumber(Row row){
        return row.getString("nextIssueNumber");
    }

    public long getNextIssueEndTime(Row row){
        return row.getLong("nextIssueEndTime");
    }
}
