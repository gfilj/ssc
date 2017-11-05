package com.project.dispatcher;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.service.message.util.MessageUtil;
import com.project.service.weixin.WechatAccessService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 事件消息业务分发器
 */
@Service
public class EventDispatcher {
    
    private Logger logger = LogUtil.getLogger(EventDispatcher.class);

    public static final String oldOpenIdPattern = "^qrscene_(.*)$";

    @Autowired
    private WechatAccessService wechatAccessService;

    public String processEvent(Map<String, String> map) throws BusinessException {
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            logger.info("==============这是关注事件！");
            String oldOriginalMessage = map.get("EventKey");
            String newOpenId = map.get("FromUserName");
            Pattern oldOriginalMessagePattern = Pattern.compile(oldOpenIdPattern);
            Matcher oldOriginalMessageMatcher = oldOriginalMessagePattern.matcher(oldOriginalMessage);
            if(oldOriginalMessageMatcher.find()){
                String oldOpendId = oldOriginalMessageMatcher.group(0);
                logger.info("老粉丝openid：" + oldOpendId + ",新粉丝openid:" + newOpenId);
                //获取用户信息
                logger.info("老粉丝信息：" + wechatAccessService.getUserInfo(oldOpendId));
                logger.info("新粉丝信息：" + wechatAccessService.getUserInfo(newOpenId));
            }

        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            logger.info("==============这是取消关注事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
            logger.info("==============这是扫描二维码事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
            logger.info("==============这是位置上报事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            logger.info("==============这是自定义菜单点击事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
            logger.info("==============这是自定义菜单 View 事件！");
        }

        return "success";
    }
}