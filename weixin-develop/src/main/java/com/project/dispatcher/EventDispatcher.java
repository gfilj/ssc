package com.project.dispatcher;

import com.project.common.exception.BusinessException;
import com.project.common.extension.SpringUtil;
import com.project.common.util.LogUtil;
import com.project.processor.event.EventProcessor;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 事件消息业务分发器
 */
@Service
public class EventDispatcher {
    
    private Log logger = LogUtil.getLogger(EventDispatcher.class);

    public String processEvent(Map<String, String> map) throws BusinessException {
        String eventProcessorStr = map.get("Event") + "Processor";
        EventProcessor  eventProcessor= (EventProcessor)SpringUtil.getBean(eventProcessorStr);
        return eventProcessor.process(map);
    }
}