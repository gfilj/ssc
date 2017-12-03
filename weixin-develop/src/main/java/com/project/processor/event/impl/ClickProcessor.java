package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.dispatcher.EventKeyDispatcher;
import com.project.processor.event.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by goforit on 2017/12/3.
 */
@Service("CLICKProcessor")
public class ClickProcessor extends AbstractEventProcessor implements EventProcessor {
    @Autowired
    private EventKeyDispatcher eventKeyDispatcher;
    @Override
    public String doProcess(Map<String, String> map) throws BusinessException {

        logger.info("==============这是自定义菜单 CLICK 事件!");
        return eventKeyDispatcher.processEvent(map);

    }
}

