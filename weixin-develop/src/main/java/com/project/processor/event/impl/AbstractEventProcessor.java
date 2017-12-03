package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.processor.event.EventProcessor;
import com.project.service.weixin.access.WechatAccessService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by goforit on 2017/11/25.
 */
public abstract class AbstractEventProcessor implements EventProcessor {

    protected Logger logger = LogUtil.getLogger(getClass());

    @Autowired
    protected WechatAccessService wechatAccessService;

    public static final String oldOpenIdPattern = "^qrscene_(.*)$";

    @Override
    public String process(Map<String, String> map) throws BusinessException {
        doProcess(map);
        return "success";
    }

    abstract void doProcess(Map<String, String> map) throws BusinessException ;
}
