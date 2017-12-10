package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.processor.event.EventProcessor;
import com.project.service.user.impl.UserService;
import com.project.service.weixin.user.WechatUserReleationLogService;
import com.project.service.weixin.user.WechatUserReleationService;
import com.project.type.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 关注处理器
 * Created by goforit on 2017/11/25.
 */
@Service
public class SubscribeProcessor extends AbstractEventProcessor implements EventProcessor {

    @Autowired
    private UserService userService;
    @Autowired
    private WechatUserReleationService wechatUserReleationService;

    private WechatUserReleationLogService wechatUserReleationLogService;
    @Override
    public String doProcess(Map<String, String> map) throws BusinessException {
        String newOpenId = map.get("FromUserName");
        String member = wechatAccessService.getUserInfo(newOpenId);


        String oldOriginalMessage = map.get("EventKey");
        Pattern oldOriginalMessagePattern = Pattern.compile(oldOpenIdPattern);
        Matcher oldOriginalMessageMatcher = oldOriginalMessagePattern.matcher(oldOriginalMessage);
        if(oldOriginalMessageMatcher.find()){
            logger.info("==============这是通过跟踪二维码关注事件！");
            String oldOpendId = oldOriginalMessageMatcher.group(1);
            logger.info("老粉丝openid：" + oldOpendId + ",新粉丝openid:" + newOpenId);
            //获取用户信息
            String introduce = wechatAccessService.getUserInfo(oldOpendId);
            logger.info("老粉丝信息：" + introduce);

            logger.info("新粉丝信息：" + member);

            userService.insert(introduce);
            userService.insert(member);
            //最终状态表
            wechatUserReleationService.insert(introduce,member, Relation.Subscrib.getValue());
            //明细表
            wechatUserReleationLogService.insert(introduce,member, Relation.Subscrib.getValue());
        }else{
            logger.info("==============这是普通关注事件！");
            //最终状态表
            wechatUserReleationService.insert(null,member, Relation.Subscrib.getValue());
            //明细表
            wechatUserReleationLogService.insert(null,member, Relation.Subscrib.getValue());
        }
        return "success";
    }
}
