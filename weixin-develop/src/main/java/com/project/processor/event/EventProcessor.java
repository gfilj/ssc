package com.project.processor.event;

import com.project.common.exception.BusinessException;

import java.util.Map;

/**
 * Created by goforit on 2017/11/25.
 */
public interface EventProcessor {
    String process(Map<String, String> map) throws BusinessException;
}
