package com.project.dispatcher;

import com.project.common.exception.BusinessException;
import com.project.common.extension.SpringUtil;
import com.project.common.util.LogUtil;
import com.project.processor.event.EventProcessor;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 点击事件分发器
 * Created by goforit on 2017/12/3.
 */
@Service
public class EventKeyDispatcher {
    private Log logger = LogUtil.getLogger(EventKeyDispatcher.class);

    public String processEvent(Map<String, String> map) throws BusinessException {
        String eventProcessorStr = map.get("EventKey") + "Processor";
        EventProcessor eventProcessor= (EventProcessor) SpringUtil.getBean(eventProcessorStr);
        return eventProcessor.process(map);
    }
}
