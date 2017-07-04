/**
 *
 * @authors Ryan (you@example.org)
 * @date    2016-05-11 10:45:11
 * @version $Id$
 */

var commonModal = new jBox('Modal', {
    overlay: true,
    draggable: 'title',
    repositionOnOpen: false,
    repositionOnContent: false,
    closeButton: false,
    pushloop: 0,
    width: 460,
    onClose: function() {
        location.reload();
    }
});
var commonUtil = commonUtil || {};
/**
 * 封装常用方法
 */
commonUtil = {
    //全局退出按钮
    logoutBtn: $('#header a.highlight-color,#nav a.logoutlink,#seven-top a.highlight-color'),
    speed: 300,
    confirmCallback: {
        'cancelOrder': 'cancelOrder',
        'recharge': 'recharge',
        'cancelTrace': 'cancelTrace'
    },
    //显示提示信息-无自动关闭
    showTip: function(msg) {
        var tipAlert = new jBox('Modal', {
            id: 'a3tip',
            title: '温馨提示：',
            content: '<span class="tips">' + msg + '</span>',
            closeButton: 'title',
            minWidth: 430,
            onCloseComplete: function() {
                this.destroy();
            }
        }).open();
    },
    //提示框
    showNotice: function(msg, btn) {
        new jBox('Notice', {
            content: msg,
            stack: true,
            //autoClose: false,
            theme: 'NoticeBorder',
            attributes: {
                x: 'right',
                y: 'bottom'
            }
        });
    },
    //确认框
    confirmTip: function(title, msg, confirmCallback, data, dom) {
        var tipAlert = new jBox('Confirm', {
            title: title,
            content: '<span class="tip">' + msg + '</span>',
            confirmButton: '确定',
            cancelButton: '取消',
            closeButton: 'title',
            minWidth: 430,
            confirm: function() {
                var funcObj = {
                    'cancelOrder': commonUtil.cancelOrder,
                    'recharge': commonUtil.recharge,
                    'cancelTrace': commonUtil.cancelTrace,
                    'deleteUserLinks': commonUtil.deleteUserLinks
                }
                funcObj[confirmCallback](data, dom);
            },
            cancel: function() {
                tipAlert.destroy();
            }
        }).open();
    },
    //jBox TIP 关闭刷新
    commonTipAlert_f: function(msg) {
        var tipAlert = new jBox('Modal', {
            id: 'a3tip',
            title: '温馨提示：',
            content: '<span class="tips">' + msg + '</span>',
            confirmButton: '确定',
            closeButton: 'title',
            minWidth: 430,
            onClose: function() {
                if (keyWords == 'zd') {
                    location.reload();
                } else {
                    if (isMobile.any()) {
                        window.location.href = ctx + "/auth/signin.html";
                    } else {
                        location.reload();
                    }
                }
            }
        }).open();
        //setTimeout(function(){
        //    tipAlert.close();
        //}, 2000);
    },
    //jBox TIP-自动关闭 无关闭回调
    commonTipAlert: function(msg) {
        var tipAlert = new jBox('Modal', {
            title: '温馨提示：',
            content: '<span class="tips">' + msg + '</span>',
            confirmButton: '确定',
            closeButton: 'title',
            minWidth: 430
        }).open();
        setTimeout(function() {
            tipAlert.close();
        }, 2000);
    },
    //全局退出函数
    ssologout: function() {
        $.post("/sso/logout", {}, function() {
            window.location.href = "/";
        });
    },
    //精确小数点后4位
    toFixedNum: function(num) {
        var stringNum = String(num);
        strNumArr = stringNum.split('.');
        if (strNumArr.length >= 2) {
            stringNum = strNumArr[1] + '0000';
            num = strNumArr[0] + '.' + stringNum.substring(0, 4) + '';
        } else {
            num = strNumArr[0] + '.00';
        }
        return num;
    },
    //登录页面设置内容和底部盒子的高度
    setLoginHeight: function() {
        var win_height = $(window).height();
        var con_height = win_height * (8 / 10);
        var bottom_height = win_height * (2 / 10);
        $("#J_conBox").height(con_height);
        $("#J_bottomBox").height(bottom_height);
    },
    //注册页面设置内容和底部盒子的高度
    setRegisterHeight: function() {
        var win_height = $(window).height();
        var top_height = (win_height - 650) * 0.4;
        //    var con_height = win_height * 0.68;
        var bottom_height = (win_height - 650) * 0.6;
        $("#J_topBox").height(top_height);
        //    $("#J_centerBox").height(con_height);
        $("#J_bottomBox").height(bottom_height);
    },
    //tab切换
    changeTitle: function(title) {
        $("#" + title).show().siblings('.content').hide();
        $("[data-tab-" + title + "]").addClass('current').siblings().removeClass('current');
        $("[data-tab-" + title + "]").find('a').addClass('self').siblings().removeClass('self');
        var btnVal = $('#' + title).find('input#J-submit');
        this.category = btnVal.data('skey');
    },
    //获取路由
    getUrlRoute: function() {
        var a = document.location.toString();
        if (-1 != a.lastIndexOf("/")) {
            var e = a.substring(a.lastIndexOf("/") + 1, a.length);
            e = e.split(/[.?#]/)[0];
        }
        return e;
    },
    //获取URL参数
    getUrlData: function(sign) {
        var a = document.location.toString();
        if (-1 != a.lastIndexOf("/")) {
            var e = a.substring(a.lastIndexOf("/"), a.length);
            e = e.split(sign)[1];
        }
        return e;
    },
    //二级菜单下拉
    dropDown: function(style) {
        //菜单下拉延迟处理函数
        $.fn.hoverDelay = function(options) {
            var defaults = {
                hoverDuring: 300,
                outDuring: 300,
                hoverEvent: function() {
                    $.noop();
                },
                outEvent: function() {
                    $.noop();
                }
            };
            var sets = $.extend(defaults, options || {});
            var hoverTimer, outTimer;
            return $(this).each(function() {
                $(this).hover(function() {
                    clearTimeout(outTimer);
                    hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
                }, function() {
                    clearTimeout(hoverTimer);
                    outTimer = setTimeout(sets.outEvent, sets.outDuring);
                });
            });
        }

        var drop_what = $('.nav-' + style + '-lists');
        //console.log(style,drop_what);

        if (drop_what.size() > 0) {
            $('[data-' + style + ']').hoverDelay({
                hoverEvent: function() {
                    drop_what.fadeIn(commonUtil.speed);
                },
                outEvent: function() {
                    drop_what.fadeOut(commonUtil.speed);
                }
            });
        }
    },
    //go home
    goHome: function(me) {
        setInterval(function() {
            var em = $("#gohome em");
            var time = parseInt(em.html(), 10);
            if (time === 0) {
                if (window.location.origin) {
                    window.location = window.location.origin + "" + ctx + "/mkg/users/user";
                } else {
                    window.location = 'http://' + window.location.host +
                        "" + ctx + "/mkg/users/user";
                }
            } else {
                em.html(time - 1);
            }
        }, 1000);
    },
    //下拉选择框渲染
    buildSelect: function(firstSelect, secondSelect, list) {
        firstSelect.reBuildSelect($.merge([{
            id: '',
            name: '所有游戏',
            checked: true
        }], list));

        var lottery = {
            "": []
        };
        $.each(list, function(i, item) {
            lottery[item.id] = item.methods;
        });

        firstSelect.addEvent('change', function(e, value, text) {
            secondSelect.reBuildSelect($.merge([{
                id: '',
                name: '所有玩法',
                checked: true
            }], lottery[value]));
        });
    },
    //撤单
    cancelOrder: function(data, dom) {
        typeof data !== 'undefined' ? orderId = data : orderId = commonUtil.getUrlRoute();
        // var orderId = commonUtil.getUrlRoute();
        Api.cancelOrder('orderId=' + orderId + '', function(rst) {
            var rstCode = parseInt(rst.code);
            if (rstCode != 0) {
                commonUtil.showTip(rst.msg);
            } else {
                $("#J-status").text("个人撤单");
                // dom.unbind('click').addClass('disabled').html('已撤单');
                dom.hide();
                commonUtil.showTip('订单已撤销');
                dom.parent().prev('td').html('个人撤单');
            }
        });
    },
    //追号撤单
    cancelTrace: function(data) {
        Api.cancelTrace(data, function(rst) {
            var rstCode = parseInt(rst.code);
            if (rstCode == 1) {
                commonUtil.commonTipAlert_f('操作已成功');
            } else {
                commonUtil.commonTipAlert_f(rst.Msg);
            }
        });
    },
    //删除注册链接
    deleteUserLinks: function(data) {
        Api.deleteUserLinks(data, function(rst) {
            var rstCode = parseInt(rst.code);
            if (rstCode == 1) {
                commonUtil.commonTipAlert_f('操作已成功');
            } else {
                commonUtil.commonTipAlert_f(rst.Msg);
            }
        });
    },
    //消息推送
    getMsgCount: function() {
        Api.getMsgCount({}, function(res) {
            var count = res.data;
            count > 0 ? $('.msg-tip').show() : $('.msg-tip').hide();
        });
    },
    //终端识别
    versions: function() {
        var u = navigator.userAgent,
            app = navigator.appVersion;
        return { //移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
            weixin: u.indexOf('MicroMessenger') > -1, //是否微信
            qq: u.match(/\sQQ/i) == " qq" //是否QQ
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase(),
    //多终端适配 (viewport标签定义)
    viewPortSet: function() {
        var dpr, rem, scale;
        var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
        var docEl = document.documentElement;
        var fontEl = document.createElement('style');
        var metaEl = document.querySelector('meta[name="viewport"]');
        dpr = window.devicePixelRatio || 1;
        rem = docEl.clientWidth * dpr / 10;
        scale = 1 / dpr;

        var mobileWidth = docEl.clientWidth; //实际屏幕尺寸 px
        var dpiMap = {
            213: 0.18,
            320: 0.26,
            360: 0.3,
            375: 0.3,
            414: 0.33,
            435: 0.35,
            480: 0.4,
            540: 0.45,
            600: 0.5,
            768: 0.63,
        }
        var scaleSet = typeof(dpiMap[mobileWidth]) == 'undefined' ? 0.3 : dpiMap[mobileWidth];
        //大于768小于1200或者1200以上的适配
        var canSet = false;
        switch (!canSet) {
            case mobileWidth >= 1200:
                scaleSet = 1;
                break;
            case 768 < mobileWidth && mobileWidth < 1200:
                scaleSet = 0.65;
                break;
        }
        // 设置viewport，进行缩放，达到高清效果
        metaEl.setAttribute('content', 'width=device-width, initial-scale=' + scaleSet + ',maximum-scale=' + scaleSet * 2 + ', minimum-scale=' + scaleSet + ',user-scalable=yes');

        // // 设置data-dpr属性，留作的css hack之用
        // docEl.setAttribute('data-dpr', dpr);
        //
        // // 动态写入样式
        // docEl.firstElementChild.appendChild(fontEl);
        // fontEl.innerHTML = 'html{font-size:' + rem + 'px!important;}';
        //
        // // 给js调用的，某一dpr下rem和px之间的转换函数
        // window.rem2px = function(v) {
        //     v = parseFloat(v);
        //     return v * rem;
        // };
        // window.px2rem = function(v) {
        //     v = parseFloat(v);
        //     return v / rem;
        // };
        //
        // window.dpr = dpr;
        // window.rem = rem;
    },
    getCookie: function(cookieName) {
        var strCookie = document.cookie;
        var arrCookie = strCookie.split("; ");
        for (var i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");
            if (cookieName == arr[0]) {
                return arr[1];
            }
        }
        return "";
    },
    messagePush: function() {
        var sig = this.getCookie("SIG");
        var hostObj = {
            //测试环境 socket host
            test: {
                oc: '23.234.6.245',
                whgj: '119.28.50.75'
            },
            //线上环境 socket host
            online: {
                oc: 'www.caika888.com',
                whgj: 'www.ttwbet.com',
                jinbei: 'www.jb112.com'
            }
        }

        // var socketUrl = window.location.host;
        var socketUrl = hostObj['online'][keyWords];
        if (typeof socketUrl == 'undefined' || String(socketUrl).indexOf('192.168') > 0) {
            socketUrl = location.hostname;
            //return false;
        }
        if (String(socketUrl).indexOf('www') < 0) {
            socketUrl = String(location.host).replace('localhost', 'www');
        }
        require(['socket.io'],function(io)
        {
            console.log(typeof io,'socketUrl',socketUrl);
            
            if (typeof io !== 'undefined') {
                var socket = io.connect(socketUrl);
                socket.on('connect_error', function () {
                    console.log('推送服务无法连接');
                    socket.disconnect();
                });
                socket.on('login', function () {
                    // console.log('登录socket成功，准备接受推送');
                });

                socket.on('connect', function () {
                    var userName = $('span[data-user-name]').html();
                    var jsonObject = {
                        userName: userName,
                        sig: sig,
                        type: '3',
                        appId: '5'
                    };
                    socket.emit('login', jsonObject);
                });

                var seconds = parseInt(Math.random() * 3000);
                setTimeout(function () {
                    socket.on('message', function (data) {
                        data = JSON.parse(data);
                        var type = data.type;
                        if (type == 1 && !defaultNonpush) {
                            data = JSON.parse(data.message);
                            var bonus = commonUtil.toFixedNum(data.bonus);
                            var content = '<div class="message-push"><ul>' +
                                '<li><span>投注彩种：</span> <label>' + data.lottery + '</label></li>' +
                                '<li><span>投注奖期：</span> <label>' + data.issue + '</label></li>' +
                                '<li><span>投注金额：</span> <label>' + data.amount + '</label> 元</li>' +
                                '<li><span>中奖金额：</span> <label><i class="bold">' + bonus + '</i>元</label></li>' +
                                '</ul></div>';
                            pushDom = new jBox('Notice', {
                                title: '中奖提示',
                                content: content,
                                attributes: {
                                    x: 'right',
                                    y: 'bottom'
                                },
                                position: {
                                    x: 50,
                                    y: 50
                                },
                                // overlay: false,
                                autoClose: 10000,
                                audio: '/static/theme/' + keyWords + '/sound/yo',
                                volume: 52,
                                closeButton: 'title',
                                // draggable: true,
                                onInit: function () {
                                    setTimeout(function () {
                                        $('body').find('[data-refresh-balance]').eq(0).trigger('click');
                                    }, 3 * 1000);
                                    var luyou = commonUtil.getUrlRoute();
                                    luyou = parseInt(luyou); //通过路由判断是否为彩票投注页
                                    if (luyou >= 0) {
                                        getOrders();
                                    }
                                }
                            });
                        }
                    });
                }, seconds);

                socket.on('disconnect', function () {
                    // console.log('disconnect');
                });
            }
        });
    },
    //银行卡隐藏方法
    hiddenString: function(txt, dom) {
        var regex = /^[\u4E00-\u9FA5]+$/;
        if (regex.test(txt)) {
            var length = txt.length;
            if (length <= 2) {
                txt = "*" + txt.substring(1, length) + "";
            } else {
                txt = "*" + txt.substring((length - 1) - 1, length) + "";
            }
        } else {
            txt = "**** " + txt.substring(txt.length, txt.length - 4) + "";
        }
        dom.html(txt);
        dom.val(txt);
    },
    //获取当前时间
    getThisTime: function() {
        var date = new Date();
        var thisHour = date.getHours();
        if (Number(thisHour) < 10) {
            thisHour = "0" + thisHour;
        }
        var thisMin = date.getMinutes();
        if (Number(thisMin) < 10) {
            thisMin = "0" + thisMin;
        }
        var thisSecond = date.getSeconds();
        if (Number(thisSecond) < 10) {
            thisSecond = "0" + thisSecond;
        }
        var nowTime = thisHour + ':' + thisMin + ":" + thisSecond;
        return nowTime;
    },
    //获取当前日期
    GetDateToday: function() {
        var d, s;
        d = new Date();
        s = d.getFullYear() + "-"; //取年份
        var ab = d.getMonth() + 1;
        ab.toString().length == 1 ? ab = '0' + ab : !0;
        s = s + ab + "-"; //取月份
        var dd = d.getDate();
        dd.toString().length == 1 ? dd = '0' + dd : !0;
        s += dd; //取日期
        return (s);
    },
    GetDateMonth: function() {
        var d, s;
        d = new Date();
        s = d.getFullYear() + "-"; //取年份
        var ab = d.getMonth();
        d.setMonth(ab - 1); //得到前一个月的
        ab.toString().length == 1 ? ab = '0' + ab : !0;
        s = s + ab + "-"; //取月份
        var dd = d.getDate();
        dd.toString().length == 1 ? dd = '0' + dd : !0;
        s += dd; //取日期
        return (s);
    },
    GetDateWeek: function() {
        var d, s;
        d = new Date();
        s = d.getFullYear() + "-"; //取年份
        var ab = d.getMonth() + 1;
        ab.toString().length == 1 ? ab = '0' + ab : !0;
        s = s + ab + "-"; //取月份
        var dd = d.getDate();
        dd = dd + 7;
        dd.toString().length == 1 ? dd = '0' + dd : !0;
        d.setDate(dd); //得到前7天的
        s += dd; //取日期
        return (s);
    },
    //获取公告
    getNoticeInfo: function() {
        $('#header-new').show();
        Api.getNoticeTitle({}, function(e) {
            typeof initMsgs !== 'undefined' ? initMsgs(e) : !0;
            if (e.items && e.items.length > 0) {
                var htmlArr = [],
                    linkArr = [];
                // var htmlArr_7h = [];
                for (var i = 0; i < e.items.length; i++) {
                    // htmlArr.push('<li class="even" style="cursor:pointer;" onclick="javascript:window.location=\'' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\'"> <span class="time">' + e.items[i].showCreateTime + '</span> <a> [通知] ' + e.items[i].title + '<i class="c-important">&nbsp;&nbsp;New</i></a></li></a>');
                    htmlArr.push('<li class="even" style="cursor:pointer;" onclick="javascript:window.location=\'' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\'"> <a> <span>[通知]&nbsp;&nbsp;</span> ' + e.items[i].title + '</a></li></a>');
                    linkArr.push('<a href="' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\" target="_blank"> <span>[通知]&nbsp;&nbsp;</span> ' + e.items[i].title + '</a>&nbsp;&nbsp;&nbsp;&nbsp;')
                        // htmlArr_7h.push('<span><a href="javascript:;" onclick="javascript:window.location=\'' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\'">' + e.items[i].title + '</a> <span class="time">' + e.items[i].showCreateTime + '</span></span>');
                }
                $(".news-list").html(htmlArr.join(""));
                $(".notification-msg").html(linkArr.join(""));
                $("#notice").append(htmlArr.join(""));
                $('.index-notice-ttw').marquee({
                    auto: true,
                    interval: 3000,
                    speed: 1000,
                    showNum: 11,
                    stepLen: 1
                });
                $('.index-notice-7h,.index-notice-whgj').marquee({
                    auto: true,
                    interval: 3000,
                    speed: 1000,
                    showNum: 11,
                    stepLen: 1,
                    type: 'vertical'
                });
            }
        });
    },
    //导航下拉
    menuDropDown: function(width, target, content) {
        new jBox('Tooltip', {
            id: 'newMenu',
            width: width,
            closeOnMouseleave: true,
            animation: 'move',
            attach: target,
            zIndex: 8000,
            content: content,
            // position: {
            //     x: 'right',
            //     y: 'right'
            // },
            adjustDistance: {
                top: 50,
                right: 20,
                bottom: 5,
                left: 400
            },
            offset: {
                x: 0,
                y: 0
            }
        });
    },
    //展示移动端二维码
    showQrCode: function(evt) {
        var me = $(evt);
        var tipAlert = new jBox('Modal', {
            id: 'a3tip',
            title: '手机端下载：',
            content: '<span class="qr-code-tip">扫描下方二维码下载手机客户端<img src="/assets/images/appscan.png" width="102" height="100" ></span>',
            closeButton: 'title',
            minWidth: 230,
            onCloseComplete: function() {
                this.destroy();
            }
        }).open();
    },
    //设置cookie
    setCookie: function(c_name, value) {
        $.cookie(c_name, value, {
            expires: 1,
            path: '/'
        });
    },
    //手机端登陆页模式选择
    typeSwitch: function() {
        var switchType = commonUtil.getCookie('deviceType');
        var $switchBtn = $('#typeSwitch');
        var initType = $switchBtn.data('type');
        if (switchType == '') {
            switchType = initType
        }
        if (switchType == 'mobile') {
            $switchBtn.prop('checked', 'checked');
            $('#typeSwitch').next().html('移动端模式');
        } else if (switchType == 'pc' || switchType == '') {
            $switchBtn.removeAttr('checked');
            $('#typeSwitch').next().html('PC端模式')
        }
        (isMobile.any() ? $('#pcm_switch').show() : $('#pcm_switch').hide()),

        $switchBtn.bind('click', function() {
            $switchBtn.prop('checked') ? (commonUtil.setCookie('deviceType', 'mobile'), $('#typeSwitch').next().html('移动端模式')) : (commonUtil.setCookie('deviceType', 'pc'), $('#typeSwitch').next().html('PC端模式'));
            typeof _switchCall !== 'undefined' && _switchCall(); //assets/js/config.js
        });
    },
};

var isMobile = {
    Android: function() {
        return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function() {
        return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function() {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function() {
        return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function() {
        return navigator.userAgent.match(/IEMobile/i);
    },
    any: function() {
        return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
    }
};

Date.prototype.DateAdd = function(strInterval, Number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's':
            return new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'n':
            return new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'h':
            return new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'd':
            return new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'w':
            return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
        case 'q':
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number * 3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'm':
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'y':
            return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
};

Date.prototype.Format = function(fmt) { //author: meizz "yyyy-MM-dd hh:mm:ss.S"
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}