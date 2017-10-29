package com.project.controller;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.WechatAccessEntity;
import com.project.service.message.MessageService;
import com.project.service.weixin.WechatAccessService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by goforit on 2017/10/22.
 */
@Controller
@RequestMapping("/wechat")
public class WechatAccessController {

    private Logger logger = LogUtil.getLogger(getClass());

    @Resource
    private WechatAccessService wechatAccessService;
    @Resource
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET, value = "access")
    public @ResponseBody
    String doGet(WechatAccessEntity wechatAccessEntity) {
        logger.info(String.valueOf(wechatAccessEntity));
        String token = null;
//        try {
//            String qrInfo = wechatAccessService.createQR(wechatAccessService.getToken());
//            logger.info(qrInfo);

//        } catch (BusinessException e) {
//            return e.getResult().toString();
//        }

        return wechatAccessEntity.getEchostr();
    }

    @RequestMapping(method = RequestMethod.POST, value = "access")
    @ResponseBody
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            return messageService.handleMessage(request);
        } catch (BusinessException e) {
            logger.info(e.getResult().getMsg(),e);
            return "";
        }
    }

}
