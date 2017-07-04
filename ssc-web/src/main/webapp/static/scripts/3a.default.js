/**
 *
 * @authors Your Name (you@example.org)
 * @date    2016-05-11 11:32:31
 * @version $Id$
 * @description 页面加载完毕需要执行的一些方法及逻辑
 */

//----------------------------------
// 轮询监控
//----------------------------------
var LoopManage = LoopManage || {
    refreshMoney: null,
    refreshIssus: null,
    refreshBonus: null,
    refreshSetting: null,
    refreshTime: null
};
var platform = keyWords;

$(function() {
    var a = document.location.toString();
    if (-1 != a.lastIndexOf("/")) {
    	var e = a.substring(a.lastIndexOf("/"), a.length);
    	e = e.split('#')[1];
    }
    console.log(e);
    if(e == 'index') {
    	$.ajax({
    	  url: "/hz/mkg/adminCommon/getAdminNotice.do",
    	  type: 'GET',
    	  dataType: "json",
    	  cache: false,
    	  timeout: 30000,
      }).done(function(e) {
    	  
    	  var lastId = e.items[0].id || 1;
          var myModal = new jBox('Modal', {
            id:'showNoticePop',
            content: '<div><iframe id="noticeframe" src="/hz/mkg/adminCommon/noticeDetail.html?id='+lastId+'&iframe=1" frameborder="0" scrolling="no" style="width:1200px;height:550px;overflow-x: hidden;"></iframe></div>',
            width:1200,
            height:550
          });
          myModal.open();
          
          /*if (e.items && e.items.length > 0) {
            $('body').append('<a id="noticeClick" style="display:none;" href="' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[0].id + '\" target="_blank"> <span>[通知]&nbsp;&nbsp;</span> ' + e.items[0].title + '</a>');
          }
          document.getElementById("noticeClick").click(); */
    	});
    	//window.open('/hz/mkg/adminCommon/getAdminNotice.do', '_blank', 'width=1200, height=800');
    }
    //动态彩种LOGO显示控制
    ((String($('.detailhead').attr('rel')).indexOf('MKG')>-1 || String($('#J-lottery-logo').attr('rel')).indexOf('MKG')>-1)  ? $('.logo-tag').show() : $('.logo-tag').hide())
    //推送秒秒彩除外
    console.log('messagePushmessagePush');
    location.pathname != '/static/m/game/mmc.html' && commonUtil.messagePush();
    //----------------------------------
    // 终端判断及页面显示解决方案
    //----------------------------------
    if ((typeof commonUtil.getCookie('deviceType') == 'undefined' || commonUtil.getCookie('deviceType') == 'pc') && keyWords != 'daz' && platform != 'dk' && (commonUtil.versions.mobile || commonUtil.versions.ios || commonUtil.versions.android || commonUtil.versions.iPhone || commonUtil.versions.iPad)) {
        commonUtil.viewPortSet();
    }
    //----------------------------------
    // 其他菜单下拉
    //----------------------------------
    commonUtil.dropDown('record');
    commonUtil.dropDown('fund');
    commonUtil.dropDown('team');
    commonUtil.dropDown('agent');
    commonUtil.dropDown('account');
    commonUtil.dropDown('form');
    commonUtil.dropDown('lobby');
    //----------------------------------
    // 游戏大厅下拉
    //----------------------------------
    if ($('#nav').size() > 0 || $('#seven-nav').size() > 0) {
      $('#nav, #seven-nav').overdropdown({
          handler: 'li[data-gamelist] > a',
          fade: false,
          delay: 300,
          //menucls:'beside',//居中不居中通过此选项判断，不需要这个选项
          // dropdown: (keyWords != 'zd' ? '.nav-game-lists' : '.nav-container-fluid')
          dropdown: '.nav-game-lists, .nav-container-fluid'
      });
    }
    var $lobby = $('#nav .nav-lobby,#nav .nav-lottery, #seven-nav .nav-lobby'),
        $gameLists = $('.nav-game-lists');
    if ($lobby.size() > 0 || $gameLists.size() > 0) {
        if ($('#nav .nav-logo').is(':visible') == true) {
            $gameLists.css({
                marginLeft: ($lobby.offset().left + $lobby.outerWidth() / 2) - $(window).outerWidth() / 2 - $gameLists.outerWidth() / 2 + 237
            });
        } else {
            $gameLists.css({
                marginLeft: ($lobby.offset().left + $lobby.outerWidth() / 2) - $(window).outerWidth() / 2 - $gameLists.outerWidth() / 2
            });
        }
    }

    //----------------------------------
    // APP下载下拉
    //----------------------------------
    if ($('#mobile-nav').size() > 0) {
        $('#relatelnk').overdropdown({
            handler: '.mobile-lnk',
            fade: false,
            delay: 50,
            dropdown: '.mobile-menu'
        });
    }
    //----------------------------------
    // 顶部用户菜单下拉
    //----------------------------------
    if ($('.top-nav-user, .top-nav-msg, .top-nav-mobile').size() > 0) {
        $('.top-nav-user, .top-nav-msg, .top-nav-mobile, .top-nav-user-new').overdropdown({
            activeClass: 'top-nav-toggle-active',
            handlerIsLink: true //,fade:true
        });
    }
    //----------------------------------
    // 推送开关和声音开关
    //----------------------------------
    $.cookie('hashid',String($('span[data-user-name]').html()).hashCode(),{path:'/'});
    //默认开启声音和推送cookie
    $.cookie('ismute'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :''),1,{path:'/'});
    $.cookie('nonpush'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :''),1,{path:'/'});

    if (typeof noswitchSoundAndPush == 'undefined') {
      defaultSoundMute = (typeof $.cookie('ismute'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == 'undefined' || $.cookie('ismute'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == null) ? true : false;
      defaultNonpush = (typeof $.cookie('nonpush'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == 'undefined' || $.cookie('nonpush'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == null) ? true : false;
    }
    if ($('.top-switch-link').size() > 0) {
        $('#top-switch').overdropdown({
            activeClass: 'top-switch-link-active',
            handler: '.top-switch-link',
            fade: false,
            delay: 50,
            dropdown: '#top-switch-menu'
        });
        //开关时间，联动cookie
        $('#top-switch-menu .m_sw').off('click').on('click',function(){
          var typ = $(this).attr('type');
          if ($(this).hasClass('m_switchon')) {
            $(this).removeClass('m_switchon');
            $(this).html('&#59221;');
            if (typ=='sound') {
              $.removeCookie('ismute'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :''),{path:'/'});
            }
            if (typ=='push') {
              $.removeCookie('nonpush'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :''),{path:'/'})
            }
          }else {
            $(this).addClass('m_switchon');
            $(this).html('&#59222;');
            if (typ=='sound') {
              $.cookie('ismute'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :''),'1',{expires:7,path:'/'})
            }
            if (typ=='push') {
              $.cookie('nonpush'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :''),'1',{expires:7,path:'/'})
            }
          }
          defaultSoundMute = (typeof $.cookie('ismute'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == 'undefined' || $.cookie('ismute'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == null) ? true : false;
          defaultNonpush = (typeof $.cookie('nonpush'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == 'undefined' || $.cookie('nonpush'+(typeof $.cookie('hashid') !== 'undefined' ? $.cookie('hashid') :'')) == null) ? true : false;
        });
        //读取声音默认值
        if (typeof defaultSoundMute !== 'undefined') {
          if (!defaultSoundMute) {
            $('#top-switch-menu .m_sw[type="sound"]').addClass('m_switchon');
            $('#top-switch-menu .m_sw[type="sound"]').html('&#59222;');
          }else {
            $('#top-switch-menu .m_sw[type="sound"]').removeClass('m_switchon');
            $('#top-switch-menu .m_sw[type="sound"]').html('&#59221;');
          }
        }
        //读取推送开关默认值
        if (typeof defaultNonpush !== 'undefined') {
          if (!defaultNonpush) {
            $('#top-switch-menu .m_sw[type="push"]').addClass('m_switchon');
            $('#top-switch-menu .m_sw[type="push"]').html('&#59222;');
          }else {
            $('#top-switch-menu .m_sw[type="push"]').removeClass('m_switchon');
            $('#top-switch-menu .m_sw[type="push"]').html('&#59221;');
          }
        }
    }
    //----------------------------------
    // 余额显示隐藏
    //----------------------------------
    var $balanceHandler = $('.balance-toggle');
    if ($.cookie('userBalanceIsVisible')) {
        userBalanceOfTopNav('show');
        $('#nav .moneybox [data-user-account-balance]').show();
        $('.switch_money').html('&#59186;').addClass('on');
    } else {
        userBalanceOfTopNav('hide');
        $('#nav .moneybox [data-user-account-balance]').hide();
        $('.switch_money').html('&#59185;').removeClass('on');
    }
    $balanceHandler.on('click', function() {
        if ($.cookie('userBalanceIsVisible')) {
            userBalanceOfTopNav('hide');
            $.cookie('userBalanceIsVisible', '', {
                expires: -1,
                path: '/'
            });
        } else {
            userBalanceOfTopNav('show');
            $.cookie('userBalanceIsVisible', true, {
                expires: 1,
                path: '/'
            });
        }
        return false;
    });
    $('.switch_money').click(function(){
      if ($(this).hasClass('on')) {
        $('#nav .moneybox [data-user-account-balance]').hide();
        $('.switch_money').html('&#59185;').removeClass('on');
        $.cookie('userBalanceIsVisible', '', {
            expires: -1,
            path: '/'
        });
      }else {
        $('#nav .moneybox [data-user-account-balance]').show();
        $('.switch_money').html('&#59186;').addClass('on');
        $.cookie('userBalanceIsVisible', true, {
            expires: 1,
            path: '/'
        });
      }
    });
    //----------------------------------
    // 余额操作
    //----------------------------------
    $('body').on('click', '[data-refresh-balance]', function() {
        var me = this;
        var defaultClsName = 'onhandled';
        if ($(me).hasClass('reverse')) {
            defaultClsName = 're_onhandled';
        }
        if ($(me).hasClass(defaultClsName))
            return false;
        $(me).addClass(defaultClsName);

        var st = new Date().getTime(),
            delay = 2000,
            handler = function(resp) {
                if (resp == -1) {
                    if ($('#a3tip').size() == 0) {
                        commonUtil.commonTipAlert_f('登录超时，为了您的账号安全，请您重新登陆');
                    }
                    return false;
                } else {
                    if (typeof resp.data !== 'undefined') {
                        var b = resp.data.available;
                        $('[data-user-account-balance]').html(b);
                    }
                }
                $(me).removeClass(defaultClsName);
            };
        Api.getUserBalance(function(resp) {
            var st2 = new Date().getTime() - st;
            if (st2 > delay) {
                handler(resp);
            } else {
                LoopManage.refreshMoney = setTimeout(function() {
                    handler(resp);
                }, delay - st2);
            }
        });
        return false;
    }).find('[data-refresh-balance]').eq(0).trigger('click');
    //----------------------------------
    // 定时刷新余额
    //----------------------------------
    setInterval(function() {
        $('body').find('[data-refresh-balance]').eq(0).trigger('click');
    }, 20 * 1000);

    function userBalanceOfTopNav(type) {
        var type = type || 'show',
            $spans = $balanceHandler
            .siblings('span');
        if (type == 'show') {
            $spans.eq(0).show();
            $spans.eq(1).hide();
            $balanceHandler.text('隐藏');
        } else {
            $spans.eq(0).hide();
            $spans.eq(1).show();
            $balanceHandler.text('显示');
        }
    }
    //----------------------------------
    // 账户余额-摇奖滚动显示
    //----------------------------------
    var ernie, $button = $('.balance-hammer'),
        nums = $.trim(
            $('#J-user-balance-value').val()).replace('.', ''),
        numStr = '0000000000000', // '000,000,000.0000'
        locked = false;

    nums = numStr.substr(0, numStr.length - nums.length) + nums;
    nums = nums.split('');
    ernie = (typeof dsgame !== 'undefined' ? new dsgame.Ernie({
        dom: $('.balance-box li:not(".money-dot")'),
        height: 24,
        length: 10,
        callback: function() {
            $button.find('.hammer-down').hide();
            $button.find('.hammer-up').show();
        }
    }) : false);

    $button.on('click', function() {
        if (locked) {
            return;
        }
        $.ajax({
            url: '' + ctx + '/mkg/api/users/user-monetary-info',
            //type: 'POST',
            dataType: 'json',
            beforeSend: function() {
                locked = true;
                $button.find('.hammer-up').hide();
                $button.find('.hammer-down').show();
                ernie.start();
            },
            success: function(data) {
                if (Number(data['isSuccess']) == 1) {
                    var monetary = '' + data['data']['available']; // isAgent ? data['data']['team_turnover'] : data['data']['available']
                    var decimals = '0000';
                    monetary = monetary.split('.');
                    if (monetary[1]) {
                        decimals = (monetary[1] + '0000').substr(0,
                            4);
                    }
                    var num = monetary[0] + decimals;
                    monetary = monetary[0] + '.' + decimals;
                    num = numStr.substr(0, numStr.length - num.length) + num;
                    ernie.stop(num.split(''));
                    $('[data-user-account-balance]').html(monetary);
                }
            },
            complete: function() {
                locked = false;
            }
        });
    }).trigger('click');
    if (ernie) {
      ernie.start();
      ernie.stop(nums);
    }

    //----------------------------------
    // 安全评分
    //----------------------------------
    var $safetyPercent = $('#ucenter [data-safety]'),
        $score = $('#J-safety-score'),
        $safetySpan = $safetyPercent
        .find('span'),
        safetyScore = parseInt($safetyPercent
            .data('safety')) || 0,
        score = safetyScore / 4 * 100;
    $score.text(score);
    if (score >= 100) {
        score = 100;
        $safetySpan.css({
            'background-color': '#848484'
        });
    }
    $safetySpan.animate({
        width: score + '%'
    }, 1000, function() {});
    //----------------------------------
    // 获奖名单滚动
    //----------------------------------
    $('.J-prize-marquee').marquee({
        auto: true,
        interval: 3000,
        speed: 1000,
        showNum: 3,
        stepLen: 1,
        type: 'vertical'
    });

    $('.J-prize-marquee-ruibo').marquee({
        auto: true,
        interval: 3000,
        speed: 1000,
        showNum: 3,
        stepLen: 1,
        type: 'horizontal'
    });
    //----------------------------------
    // 首页游戏介绍TAB切换
    //----------------------------------
    $('#J-games').find('li').bind('click', function() {
        var me = $(this);
        me.addClass('cur').siblings().removeClass('cur');
        var tabIndex = me.data('gametype');
        $('#J-game-' + tabIndex + '').addClass('active').siblings().removeClass('active');
    });
    //----------------------------------
    // 全局退出
    //----------------------------------
    commonUtil.logoutBtn.bind('click', function() {
        commonUtil.ssologout();
    });
    //----------------------------------
    // 根据导航判断logo尺寸
    //----------------------------------
    var flag = $('#nav .nav-content li a').find('i').is(':hidden');
    if (flag) {
        $('#nav #logo').hide();
        $('.logo-diff').show();
        if (keyWords == 'whgj') {
            $('#nav .nav-content').css({
                top: 0,
                position: 'absolute',
                right: 0
            });
        }
    }
    //----------------------------------
    // 导航定位
    //----------------------------------
    var high_in = $('#tab_input').val();
    var getNavType = function(path) {
      if (String(path).indexOf('mkg/bet/')>-1) {
        path = '/hz/mkg/bet';
      }
      return manualHighlight[path]
    }
    if (typeof manualHighlight == 'undefined') {
      $('#nav li.nav-' + high_in + ', #nav li a.nav-' + high_in + ', #nav li a.nav-' + high_in + ' i').addClass('active').find('p.h2').css('color', '#fff');
      $('.header-box .right-box li i.nav-' + high_in + '').parent().parent().addClass('on');
    }else {
      $('#nav li[data-'+getNavType(location.pathname)+'] a').addClass('active')
    }

    //----------------------------------
    // 首页公告关闭
    //----------------------------------
    $('#notice-close').bind('click', function() {
        $(this).parent().parent().hide();
    });
    //----------------------------------
    // 银行快捷充值-选中效果
    //----------------------------------
    $('.quick-banks li').each(function() {
        $(this).click(function() {
            $(this).addClass('selected').find('span.radio').addClass('cur');
            $(this).siblings().removeClass('selected').find('span.radio').removeClass('cur');
        });
    });
    $(".bank_choose .bc_part").each(function(i) {
        $(this).click(function() {
            $(this).addClass("bc_part_cur").siblings().removeClass("bc_part_cur");
            $(this).find(".sel_badage").show();
            $(this).siblings().find(".sel_badage").hide();
        });
    });
    //----------------------------------
    // 系统消息推送
    //----------------------------------
    commonUtil.getMsgCount();
    setInterval(function() {
        commonUtil.getMsgCount();
    }, 60000);
    //----------------------------------
    // 开奖中动画
    //----------------------------------
    function changeFontSize(i) {
        var animation_i = $('.loading-lottery i.elli');
        animation_i.eq(i).animate({
            opacity: 1
        }, 300, 'swing', function() {
            var t = i + 1;
            if (t > 2) {
                animation_i.css('opacity', '0');
                t = 0;
            }
            changeFontSize(t);
        });
    }
    changeFontSize(0);
    //----------------------------------
    // 加泛亚logo
    //----------------------------------
    if ($('#footer .ft-link').is(':visible') === true) {
        var powerby = '<div class="power-by"><label>Powered by</label><img src="/assets/images/fanya.png"></div>';
        $('#footer .ft-cont').append(powerby);
    }
    //----------------------------------
    // 复制事件
    //----------------------------------
    if (typeof Clipboard !== 'undefined') {
      var clipboard = new Clipboard('.copy_btn');
      clipboard.on('success', function(e) {
          new jBox('Notice', {
              // trigger: 'click',
              content: '已成功复制所选内容',
              theme: 'TooltipBorder',
              position: {
                  x: 'center',
                  y: 'center'
              }
          });
          // e.clearSelection();
      });
    }

    //----------------------------------
    // 鼠标hover效果
    //----------------------------------
    $(".index-bottom-game .content .in:not(.fixed)").hover(function() {
        $(this).find("img").show().stop().animate({
                opacity: .3
            }, 400, "easeOutQuad"),
            $(this).find("label").show().stop().animate({
                opacity: 1,
                top: '50%'
            }, 200, "easeOutQuad")
    }, function() {
        $(this).find("img").show().stop().animate({
                opacity: 1
            }, 400, "easeOutQuad"),
            $(this).find("label").stop().animate({
                opacity: 0,
                top: -44
            }, 200, "easeInQuad")
    });
    //----------------------------------
    // 公告信息
    //----------------------------------
    if (keyWords == 'whgj') {
        commonUtil.getNoticeInfo();
    }
    //----------------------------------
    // 金贝彩票导航下拉
    //----------------------------------
    if (keyWords == 'jinbei' || typeof autoLobby !== 'undefined') {
        var content_userName = '<div class="common-drop"><ul>' +
            '<li><a href="' + ctx + '/mkg/users/change-password.html">登录密码</a></li>' +
            '<li><a href="' + ctx + '/mkg/users/change-fund-password.html">资金密码</a></li>' +
            '<li><a href="' + ctx + '/mkg/users/personal">修改资料</a></li>' +
            '<li><a href="' + ctx + '/mkg/users/user-bankards">银行卡管理</a></li>' +
            '</ul></div>';
        var content_found = '<div class="common-drop"><ul>' +
            '<li><a href="' + ctx + '/mkg/orders.html">游戏记录</a></li>' +
            '<li><a href="' + ctx + '/mkg/statement.html#account_view">资金明细</a></li>' +
            '<li><a href="' + ctx + '/mkg/dayreport.html">团队报表</a></li>' +
            '</ul></div>';
        var content_account = '<div class="common-drop"><ul>' +
            '<li><a href="' + ctx + '/mkg/users/change-password.html">个人管理</a></li>' +
            '<li><a href="' + ctx + '/mkg/user.html">团队管理</a></li>' +
            '</ul></div>';
        commonUtil.menuDropDown(130, $('.top-nav-toggle'), content_userName);
        commonUtil.menuDropDown(130, $('#nav a.nav-form'), content_found);
        commonUtil.menuDropDown(130, $('#nav a.nav-account'), content_account);
        commonUtil.menuDropDown('auto', $('#nav a.nav-lobby'), $('.nav-container-fluid'));
    }
    //----------------------------------
    // 客户端下载弹出
    //----------------------------------
    new jBox('Modal', {
        // width: 470,
        height: 300,
        attach: $('a#clientDownload'),
        title: '安卓客户端下载：',
        content: $('section.client-down')
    });
    $('.download li').bind('click', function(){
        var me = $(this);
        var dt = me.attr('class');
        var dtobj = {
            apple: '苹果',
            android: '安卓'
        }
        // if(dt === 'android'){
            var imgtxt = '<img src="/assets/images/appscan_' + dt + '.png" alt="" width="102" height="100"><div class="qrcode-text"><p>扫描左侧二维码</p><p>下载' + dtobj[dt] + '手机客户端</p></div>';
            commonUtil.showTip(imgtxt);
        // }else {
            // commonUtil.showTip('敬请期待');

        // }
    });
    //----------------------------------
    // 数字滚动动画
    //----------------------------------
    if ($('[data-animation-number]').length > 0) {
        var number_steps = {
            'money': $.animateNumber.numberStepFactories.separator(','),
            'time': function(now, tween) {
                var m = Math.floor(now / 60),
                    s = Math.floor(now % 60),
                    target = $(tween.elem),
                    text = '';
                if (m > 0) {
                    text += m + s;
                } else {
                    if(now  == 1.5){
                        text = s + 0.5;
                    }else {
                        text = s;
                    }
                }
                target.text(text);
            }
        };
        $('body').on('inview', '[data-animation-number]', function() {
            var progressBarDom = $('.ft-percent-real');
            progressBarDom.each(function() {
                var me = $(this);
                var disWidth = me.data('percent');
                me.animate({
                    width: disWidth,
                }, 1000);
                var disLeft = String(parseInt(disWidth) - 1) + '%';
                me.next('i').animate({
                    left: disLeft,
                }, 1000);
            });
            var $this = $(this);
            var number = $this.data('animation-number'),
                type = $this.data('type') || 'default',
                options = {
                    number: number,
                    easing: 'easeInQuad'
                };
            if (number_steps[type]) {
                options['numberStep'] = number_steps[type];
            }
            $this.animateNumber(options);
        });
    }
});