package com.project.dispatcher;

import com.project.common.util.LogUtil;
import com.project.service.message.util.MessageUtil;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @Description: 事件消息业务分发器
 */
public class EventDispatcher {
    
    private static Logger logger = LogUtil.getLogger(EventDispatcher.class);
    
    public static String processEvent(Map<String, String> map) {
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            logger.info("==============这是关注事件！");
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

        return "";
    }
}