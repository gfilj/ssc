/*package com.project.controller;

import java.io.BufferedOutputStream;
import java.share.util.Enumeration;
import java.share.util.HashMap;
import java.share.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/alipay")
public class AliPayController {

	private static final Logger LOG = LogManager.getLogger(AliPayController.class);
	 //初始化参数 不然signVerified会验证失败
    static {
           Configs.init("zfbinfo.properties");
    }
   *//**
    * 支付宝支付回调
    * @Author 小柒
    * @param request
    * @param response
    * @throws Exception
    *             void
    * @Date 2016年10月31日 更新日志 2016年10月31日 小柒 首次创建
    *
    *//*
   @SuppressWarnings("unchecked")
   @RequestMapping(value = "pay",method = RequestMethod.POST)
   public void alipay_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   LOG.info("支付宝付款异步通知！");
       String  message = "success";
       Map<String, String> params = new HashMap<String, String>();
       // 取出所有参数是为了验证签名
       Enumeration<String> parameterNames = request.getParameterNames();
       while (parameterNames.hasMoreElements()) {
           String parameterName = parameterNames.nextElement();
           params.put(parameterName, request.getParameter(parameterName));
       }
       //验证签名
       boolean signVerified = false;
       try {
           signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8");
       } catch (AlipayApiException e) {
           e.printStackTrace();
           message =  "failed";
       }
       if (signVerified) {
    	   LOG.info("验证签名成功！");
           // 若参数中的appid和填入的appid不相同，则为异常通知
           if (!Configs.getAppid().equals(params.get("app_id"))) {
        	   LOG.info("与付款时的appid不同，此为异常通知，应忽略！");
               message =  "failed";
           }else{
               String outtradeno = params.get("out_trade_no");
               LOG.info(outtradeno + "号订单回调通知。");
               //在数据库中查找订单号对应的订单，并将其金额与数据库中的金额对比，若对不上，也为异常通知
               String status = params.get("trade_status");
               if (status.equals("WAIT_BUYER_PAY")) { // 如果状态是正在等待用户付款
               } else if (status.equals("TRADE_CLOSED")) { // 如果状态是未付款交易超时关闭，或支付完成后全额退款
               } else if (status.equals("TRADE_SUCCESS") || status.equals("TRADE_FINISHED")) { // 如果状态是已经支付成功
                   //成功 更新状态
               } else {
                   weixinpayBack.updateAccOrder(outtradeno);
               }
               LOG.info(outtradeno + "订单的状态已经修改为" + status);
           }
       } else { // 如果验证签名没有通过
           message =  "failed";
           LOG.info("验证签名失败！");
       }
       BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
       out.write(message.getBytes());
       out.flush();
       out.close();
   }
}
}*/
