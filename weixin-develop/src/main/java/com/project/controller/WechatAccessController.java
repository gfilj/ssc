package com.project.controller;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.WechatAccessEntity;
import com.project.service.weixin.WechatAccessService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by goforit on 2017/10/22.
 */
@Controller
@RequestMapping("/wechat")
public class WechatAccessController {

    private Logger logger = LogUtil.getLogger(getClass());

    @Autowired
    private WechatAccessService wechatAccessService;

    @RequestMapping(method = RequestMethod.GET, value = "access")
    public @ResponseBody
    String add(WechatAccessEntity wechatAccessEntity) {
        logger.info(String.valueOf(wechatAccessEntity));
        String token = null;
        try {
            String qrInfo = wechatAccessService.createQR(wechatAccessService.getToken());
            logger.info(qrInfo);
        } catch (BusinessException e) {
            return e.getResult().toString();
        }

        return wechatAccessEntity.getEchostr();
    }
}
