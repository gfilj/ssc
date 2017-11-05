package com.project.service.message;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.dispatcher.EventDispatcher;
import com.project.dispatcher.MsgDispatcher;
import com.project.service.message.util.MessageUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by goforit on 2017/10/29.
 */
@Service
public class MessageService {

    private Logger logger = LogUtil.getLogger(getClass());
    @Autowired
    private EventDispatcher eventDispatcher;
    @Autowired
    private MsgDispatcher msgDispatcher;

    public String handleMessage(HttpServletRequest request) throws BusinessException{
        try {
            Map<String, String> map=MessageUtil.parseXml(request);
            logger.info("接收到微信发过来的消息："+ map.toString());
            String msgtype=map.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                return eventDispatcher.processEvent(map); //进入事件处理
            }else{
                return msgDispatcher.processMessage(map); //进入消息处理
            }
        } catch (Exception e) {
            logger.error("消息处理类出现异常！",e);
            throw new BusinessException(ExceptionEnum.XML_PARSE_CAUSE,e);
        }

    }
}
