package com.project.service.message;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.dispatcher.EventDispatcher;
import com.project.dispatcher.MsgDispatcher;
import com.project.service.message.util.MessageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by goforit on 2017/10/29.
 */
@Service
public class MessageService {

    public String handleMessage(HttpServletRequest request) throws BusinessException{
        try {
            Map<String, String> map=MessageUtil.parseXml(request);
            String msgtype=map.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                return EventDispatcher.processEvent(map); //进入事件处理
            }else{
                return MsgDispatcher.processMessage(map); //进入消息处理
            }
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.XML_PARSE_CAUSE,e);
        }

    }
}
