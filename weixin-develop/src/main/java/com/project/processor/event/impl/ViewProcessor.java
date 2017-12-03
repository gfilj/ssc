package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.processor.event.EventProcessor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by goforit on 2017/12/3.
 */
@Service("VIEWProcessor")
public class ViewProcessor extends AbstractEventProcessor implements EventProcessor {
    @Override
    public String doProcess(Map<String, String> map) throws BusinessException {
        logger.info("==============这是自定义菜单 View 事件!");
        return "success";
    }
}
