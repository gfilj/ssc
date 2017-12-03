package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.processor.event.EventProcessor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by goforit on 2017/12/3.
 */
@Service("CLICKProcessor")
public class ClickProcessor extends AbstractEventProcessor implements EventProcessor {

    @Override
    void doProcess(Map<String, String> map) throws BusinessException {
        logger.info("==============这是自定义菜单 CLICK 事件!");

    }
}

