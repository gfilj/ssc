/**
 *
 * @authors Your Name (you@example.org)
 * @date    2016年9月14日17:13:01
 * @version $Id$
 * @description 各配置项
 */
 var configData = configData ||  {
     drawCashLimit: {},
     chargeLimit: {
         wechat: {},
         alipay: {}
     },
     //为之后config接口做准备
    getConfigData: function(d){
        return this.setConfigData(d);
    },
    setConfigData: function(d){
        var me = this;
        var drawCash = me.drawCashLimit;
        var charge = me.chargeLimit;
        //提款时间限制
        drawCash.startTime = '12:00:00'; //开始时间
        drawCash.endTime = '02:00:00'; //结束时间
        drawCash.isCross = 1; //1为跨日，0为不跨日
        //微信及支付宝充值额度限制
        var wechatConf = charge.wechat;
        var alipayConf = charge.alipay;
        //微信额度
        wechatConf.min = 50; //最小
        wechatConf.max = 2000; //最大
        //支付宝额度
        alipayConf.min = 50;
        alipayConf.max = 5000;
    }
 }