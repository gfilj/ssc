package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.processor.event.EventProcessor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 关注处理器
 * Created by goforit on 2017/11/25.
 */
@Service("SubscribProcessor")
public class SubscribProcessor extends AbstractEventProcessor implements EventProcessor {

    @Override
    void doProcess(Map<String, String> map) throws BusinessException {
        logger.info("==============这是关注事件！");
        String oldOriginalMessage = map.get("EventKey");
        String newOpenId = map.get("FromUserName");
        Pattern oldOriginalMessagePattern = Pattern.compile(oldOpenIdPattern);
        Matcher oldOriginalMessageMatcher = oldOriginalMessagePattern.matcher(oldOriginalMessage);
        if(oldOriginalMessageMatcher.find()){
            String oldOpendId = oldOriginalMessageMatcher.group(1);
            logger.info("老粉丝openid：" + oldOpendId + ",新粉丝openid:" + newOpenId);
            //获取用户信息
            logger.info("老粉丝信息：" + wechatAccessService.getUserInfo(oldOpendId));
            logger.info("新粉丝信息：" + wechatAccessService.getUserInfo(newOpenId));
        }
    }
}
