/**
 *
 * @authors Your Name (you@example.org)
 * @date    2016-05-11 14:56:47
 * @version $Id$
 */

var spinner_html = '<div class="player-loading-spinner"></div>',jb_this;
if (typeof jBox !== 'undefined') {
  jb_this =  new jBox("Modal", {
    theme: "TooltipDark",
    content: spinner_html,
    blockScroll: !1,
    autoClose: !1,
    closeOnEsc: !1,
    closeOnClick: !1,
    overlay: !1,
    closeButton: !1,
    animation: {
        close: "zoomOut"
    }
  });
}
var Api = Api || {};

Api = {
    url: ctx,
    apimap: {
        route: {
            'getNoticeTitle': '/mkg/adminCommon/getAdminNotice.do', //首页公告
            'onlineLotterys':'/mkg/api/lottery/onlineLotterys',//所有在售的彩种
            'getptList': '/pay/getPcodeCbBaseList', //平台列表
            'getPtBalance': '/pay/getPlayerBalance', //各平台余额
            'getBindCardInfo': '/mkg/api/users/saveOrUpdateCard', //绑定银行卡
            'getDrawCashInfo': '/mkg/api/users/queryWithDrawInfo', //取款-银行卡信息
            'getLowerUserList': '/pay/getAgentUserList', //下级用户列表
            'saveQuota': '/mkg/api/agent/update_quotas', //配额管理
            'getPtGamesData': "/u/api/pt/games", //pt游戏列表
            'getPtGamesMenu': "/u/api/pt/types", //pt游戏导航
            'getUserInfo': "/mkg/api/pt/userinfo", //pt用户信息
            'getCities': "/pay/getcities",//获取城市列表
            'getUserBalance': "/mkg/api/users/user-monetary-info", //查询余额
            'getLotteryList': '/mkg/api/query/filters', //查询模块获取彩票彩种列表
            'getGameRecord': '/mkg/api/query/orders', //查询游戏记录
            'getTraceRecord': '/mkg/api/query/lottery_trace', //追号记录
            'getCountRecord': '/mkg/api/query/statements', //账变记录
            'getRechargeRecord': '/mkg/api/query/recharge', //充值记录
            'getWithdrawRecord': '/mkg/api/query/withdraw', //取款记录
            'getDayreportRecord': '/mkg/api/query/dayreport', //个人报表
            'getQrPayChannelList':'/mkg/api/pay/getQrPayChannelList',//获取扫描充值方式
            'getTeamRecord': '/mkg/api/query/teamreport', //团队报表
            'getUserList': '/mkg/api/agent/users', //用户列表
            'cancelOrder': '/mkg/api/order/cancel', //撤单
            'cancelTrace': '/mkg/api/order/trace_cancel', //追号撤单
            'getUserLinks': '/mkg/api/agent/links', //链接管理
            'deleteUserLinks': '/mkg/api/agent/link_delete', //删除链接
            'getMsgCount': '/u/api/message/queryUnRMCount', //获取消息数
            'getQueryMessage': '/u/api/message/queryMessage', //消息列表数据
            'getIsRead': '/u/api/message/read', //阅读信息传送
            'getWechatInfo': '/mkg/api/users/getTqrCodePayInfo?codeType=2', //支付宝支付信息
            'getAlipayInfo': '/mkg/api/users/getTqrCodePayInfo?codeType=1', //微信支付信息
            'getSportDeals':'/api/i/u/query/orders/sb',//沙巴游戏记录
            'getPTDeals':'/api/i/u/query/orders/pt',//娱乐场游戏记录
            'getAGDeals':'/api/i/u/query/orders/ag',//AG游戏记录
            'getSalary':'/mkg/api/query/userSalary',//日工资
            'getDividend':'/mkg/api/query/userProfitBonus',//分红
            'personProfitBonus':'/mkg/api/query/personProfitBonus',//分红明细
            'payAllUserBonus':'/mkg/api/agent/payAllUserBonus',//派发分红
            'getGameForms': '/mkg/api/query/sportdeals',//体育结算注单,报表查询
	          'getThirdTeamReport': '/api/i/u/query/tpTeamReport',//第三方团队报表查询
            'getIndexBanner': '/mkg/api/users/activity/queryActivityImages?type=2', //首页轮播图
            'userSalaryPoint':'/mkg/api/query/userSalaryPoint/__id',//下级用户ID，RESTful风格参数
            'getUpdatePointInfo':'/mkg/api/agent/getUpdatePointInfo',//读取返点?userId=8118
            'getUserPoint':'/mkg/api/users/user-point',//读取开户返点
            'getThirdPayChannelList':'/mkg/api/pay/getThirdPayChannelList',
            'getOfflineBankList':'/mkg/api/pay/getOfflineBankList',
	          'rechargeAjax':'/mkg/api/pay/rechargeAjax',
            'getRechargeToken':'/mkg/api/pay/getRechargeToken',
            'getChartData': '/mkg/api/query/teamOverview', //团队总览图表数据
            	 'getThirdRecharge':'/mkg/api/pay/getThirdPayChannelList',
                 'getOfflineBankList':'/mkg/api/pay/getOfflineBankList',
                 'getQrPayChannelList': '/mkg/api/pay/getQrPayChannelList',
                 'rechargeAjax':'/mkg/api/pay/rechargeAjax',
                 'getRechargeToken':'/mkg/api/pay/getRechargeToken'
        }
    },
    getUrl: function (apiName) {
        var params;
        if (arguments.length > 1) {
            params = arguments[1];
        }

        if (typeof Api.apimap.route[apiName] == 'object') {
            if (params) {
                if (arguments.length > 2) {
                    pageparams = arguments[2];
                    return [String(Api.apimap.route[apiName][params]).replace('.json', (pageparams.page > 1 ? '_' + pageparams.page : '') + '.json')].join('');
                }

                return [Api.apimap.route[apiName][params].join('')];
            }
        }
        //兼容新版的restful api
        if (typeof params.rest !== 'undefined') {
          return params.restful([Api.url, Api.apimap.route[apiName]].join('')); 
        }
        return [Api.url, Api.apimap.route[apiName]].join('');
    },
    getCommon: function (route, p, fn, type) {
        $.ajax({
            url: Api.getUrl(route, p),
            type: type == undefined ? 'GET' : type,
            dataType: "json",
            data: p,
            cache: false,
            timeout: 30000,
            error: function (jqXHR, textStatus, errorThrown) {
                if(textStatus=='timeout'){
                    //处理超时的逻辑
                    commonUtil.commonTipAlert_f('请求超时，请稍后再试');
                }
            },
            beforeSend: function () {
                typeof jb_this !== 'undefined' ? jb_this.open() :!0;
            }
        }).done(function (res) {
            typeof jb_this !== 'undefined' ? jb_this.close():!0;
            fn(res);
        }).fail(function () {
            typeof jb_this !== 'undefined' ? jb_this.close():!0;
            fn('error');
        });
    },
    getCommonNoLoading: function (route, p, fn, type) {
        $.ajax({
            url: Api.getUrl(route, p),
            type: type == undefined ? 'GET' : type,
            dataType: "json",
            data: p,
            cache: false
        }).done(function (res) {
            fn(res);
        }).fail(function () {
            fn('error');
        });
    },
    getNoticeTitle: function (p, fn) {
        Api.getCommon('getNoticeTitle', p, fn);
    },
    getptList: function (p, fn) {
        Api.getCommon('getptList', p, fn);
    },
    getPtBalance: function (p, fn) {
        Api.getCommon('getPtBalance', p, fn);
    },
    getBindCardInfo: function (p, fn) {
        Api.getCommon('getBindCardInfo', p, fn);
    },
    getDrawCashInfo: function (p, fn) {
        Api.getCommon('getDrawCashInfo', p, fn);
    },
    getLowerUserList: function (p, fn) {
        Api.getCommon('getLowerUserList', p, fn);
    },
    saveQuota: function (p, fn) {
        Api.getCommon('saveQuota', p, fn, 'POST');
    },
    getPtGamesData: function (p, fn) {
        Api.getCommon('getPtGamesData', p, fn);
    },
    getPtGamesMenu: function (p, fn) {
        Api.getCommon('getPtGamesMenu', p, fn);
    },
    getUserInfo: function (p, fn) {
        Api.getCommon('getUserInfo', p, fn);
    },
    getCities: function (p, fn) {
        Api.getCommon('getCities', p, fn);
    },
    getUserBalance: function (fn) {
        $.ajax({
            url: ctx + Api.apimap.route['getUserBalance'],
            type: 'GET',
            dataType: "json",
            cache: false,
            timeout: 8000
            //error: function (jqXHR, textStatus, errorThrown) {
            //    if(textStatus=='timeout'){
            //        //处理超时的逻辑
            //        commonUtil.commonTipAlert_f('余额查询超时，请稍后再试');
            //    }
            //    else{
            //        //其他错误的逻辑
            //        commonUtil.commonTipAlert_f('余额刷新错误，请稍后再试或联系客服');
            //    }
            //}
        }).done(function (res) {
            fn(res);
        }).fail(function () {
            fn('error');
        });
    },
    getLotteryList: function(p, fn){
        Api.getCommon('getLotteryList', p, fn, 'POST');
    },
    getGameRecord: function(p, fn){
        Api.getCommon('getGameRecord', p, fn);
    },
    getTraceRecord: function(p, fn){
        Api.getCommon('getTraceRecord', p, fn);
    },
    getCountRecord: function(p, fn){
        Api.getCommon('getCountRecord', p, fn);
    },
    getRechargeRecord: function(p, fn){
        Api.getCommon('getRechargeRecord', p, fn);
    },
    getWithdrawRecord: function(p, fn){
        Api.getCommon('getWithdrawRecord', p, fn);
    },
    getDayreportRecord: function(p, fn){
        Api.getCommon('getDayreportRecord', p, fn);
    },
    getTeamRecord: function(p, fn){
        Api.getCommon('getTeamRecord', p, fn);
    },
    getUserList: function(p, fn){
        Api.getCommon('getUserList', p, fn);
    },
    getUserLinks: function(p, fn){
        Api.getCommon('getUserLinks', p, fn);
    },
    cancelOrder: function(p, fn) {
        Api.getCommon('cancelOrder', p, fn, 'POST');
    },
    cancelTrace: function(p, fn) {
        Api.getCommon('cancelTrace', p, fn, 'POST');
    },
    deleteUserLinks: function(p, fn) {
        Api.getCommon('deleteUserLinks', p, fn, 'POST');
    },
    getMsgCount: function(p, fn){
        $.ajax({
            url: ctx + Api.apimap.route['getMsgCount'],
            type: 'GET',
            dataType: "json",
            cache: false
        }).done(function (res) {
            fn(res);
        }).fail(function () {
            fn('error');
        });
    },
    getGameRecord_bet: function(p, fn){
      $.ajax({
          url: ctx + Api.apimap.route['getGameRecord'],
          type: 'GET',
          dataType: "json",
          cache: false
      }).done(function (res) {
          fn(res);
      }).fail(function () {
          fn('error');
      });
    },
    getQueryMessage: function(p, fn) {
        Api.getCommon('getQueryMessage', p, fn);
    },
    getIsRead: function(p, fn) {
        Api.getCommon('getIsRead', p, fn);
    },
    getWechatInfo: function(p, fn) {
        Api.getCommon('getWechatInfo', p, fn);
    },
    getAlipayInfo: function(p, fn) {
        Api.getCommon('getAlipayInfo', p, fn);
    },
    getIndexBanner: function(p, fn) {
        Api.getCommon('getIndexBanner', p, fn);
    },
    getChartData: function(p, fn) {
        Api.getCommon('getChartData', p, fn);
    }
}