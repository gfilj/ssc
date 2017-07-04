package com.project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wechatpay")
public class WechatPayController {
	
	
	private static final Logger LOG = LogManager.getLogger(WechatPayController.class);
	
//	@Autowired
//	 private IWeixinPayService weixinpayBack;
	   /* *//**
	     * 微信支付回调
	     * @Author    张志朋
	     * @param request
	     * @param response
	     * @throws Exception  void
	     * @Date    2016年9月28日
	     * 更新日志
	     * 2016年9月28日 张志朋  首次创建
	     *
	     *//*
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    @RequestMapping(value = "pay")
	    public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        LogUtil.info("支付成功回调");
	        // 读取参数
	        InputStream inputStream = request.getInputStream();
	        StringBuffer sb = new StringBuffer();
	        String s;
	        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	        while ((s = in.readLine()) != null) {
	            sb.append(s);
	        }
	        in.close();
	        inputStream.close();
	        // 解析xml成map
	        Map<String, String> m = new HashMap<String, String>();
	        m = XMLUtil.doXMLParse(sb.toString());
	        // 过滤空 设置 TreeMap
	        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
	        Iterator it = m.keySet().iterator();
	        while (it.hasNext()) {
	            String parameter = (String) it.next();
	            String parameterValue = m.get(parameter);
	            String v = "";
	            if (null != parameterValue) {
	                v = parameterValue.trim();
	            }
	            packageParams.put(parameter, v);
	        }
	        // 账号信息
	        String key = ConfigUtil.API_KEY; // key
	        // 判断签名是否正确
	        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
	            // ------------------------------
	            // 处理业务开始
	            // ------------------------------
	            String resXml = "";
	            if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
	                // 这里是支付成功
	                String orderNo = (String) packageParams.get("out_trade_no");
	                String attach = (String) packageParams.get("attach");
	                //回调K12
	                LogUtil.info(attach+"(订单号:"+orderNo+"付款成功)");
	                // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
	                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
	                weixinpayBack.updateAccOrder(orderNo);
	            } else {
	                LogUtil.info("支付失败,错误信息：" + packageParams.get("err_code"));
	                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
	            }
	            // ------------------------------
	            // 处理业务完毕
	            // ------------------------------
	            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
	            out.write(resXml.getBytes());
	            out.flush();
	            out.close();
	        } else {
	            LogUtil.info("通知签名验证失败");
	        }
	    }
	}*/
	

}
