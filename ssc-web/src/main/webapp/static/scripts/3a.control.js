/*
 *
 * @description 数据模型执行层，根据路由做筛选，执行对应所需接口Api调用方法
 *
 * */
(function($) {
    //绑定银行卡表单
    var options = {
        // attach: $('.prompt-text span a, .no-bank-card a'),
        title: '添加银行卡',
        content: $("#add-card"),
        width: 460,
        height: 'auto'
    };
    var bandCardModal = new jBox('Modal', options);
    var bindBtn = $('.prompt-text span a, .no-bank-card a');
    bindBtn.bind('click', function() {
        bandCardModal.open();
    });
    $("#formBindCard").validate({
        rules: {
            sn: {
                required: true
            },
            bankCardNo: {
                required: true
            },
            bankAllas: {
                required: true
            },
            province: {
                required: true
            },
            city: {
                required: true
            },
            place: {
                required: true
            }
        },
        messages: {
            sn: {
                required: ""
            },
            bankCardNo: {
                required: ""
            },
            bankAllas: {
                required: ""
            },
            province: {
                required: ""
            },
            city: {
                required: ""
            },
            place: {
                required: ""
            }
        },
        errorPlacement: function(error, element) {
            element.parent().addClass('error');
        },
        submitHandler: function(form) {
            $(":submit").addClass("loading").attr("data-value", "clicked");
            var sn = $('#sn').val();
            var bankCardNo = $('#bankCardNo').val();
            var bankAllas = $('#bankAllas').val();
            var province = $('#J-province').val();
            var city = $('#city').val();
            var place = $('#place').val();
            var data = {
                'sn': sn,
                'bankCardNo': bankCardNo,
                'bankAllas': bankAllas,
                'province': province,
                'city': city,
                'place': place
            }
            Api.getBindCardInfo(data, function(e) {
                bandCardModal.close();
                commonUtil.commonTipAlert_f(e.Msg);
            });
        }
    });
    //银行卡信息-城市切换
    $("#J-province").on('change', function() {
        value = $(this).val();
        var city = $("#city");
        if (value == "") {
            city.empty();
            city.append('<option value="">请选择</option>');
        } else {
            city.empty();
            $.ajax({
    			url:"http://www.da883.com/hz/pay/getcities?provid=" + value,
//    		    type:'GET',
    		    dataType:'JSONP',
    		    jsonp:"callback",
    		    success:function(response){
    		    	console.log(data);
    		    	var data = JSON.stringify(response);
//    		    	console.log(response);
//    		    	var data = response.callback.data;
                    var St = "";
                    for (var i in data) {
                        var t = data[i];
                        var opt = "<option value=" + t + ">" + t + "</option>";
                        St += opt;
                    }
                    city.append(St);
    		    },error:function(response){
    		    	console.log(data);
    		    	var data = JSON.stringify(response);
//    		    	console.log(response);
    		    	var data = response.data;
                    var St = "";
                    for (var i in data) {
                        var t = data[i];
                        var opt = "<option value=" + t + ">" + t + "</option>";
                        St += opt;
                    }
                    city.append(St);
    		    	console.log("网络异常，请重新尝试！");
    		    }
    		});
            
            
           /* Api.getCities({
                'provid': value
            }, function(response) {
            	var data = response.data;
                var St = "";
                for (var i in data) {
                    var t = data[i];
                    var opt = "<option value=" + t + ">" + t + "</option>";
                    St += opt;
                }
                city.append(St);
            });*/
        }
    });
    function callback(result) { 
    	var data = response.data;
    	alert(data);
        
    }  
    
    
    //路由判断 执行各页面逻辑
    var a = document.location.toString();
    if (-1 != a.lastIndexOf("/")) {
        var e = a.substring(a.lastIndexOf("/") + 1, a.length);
        e = e.split(/[.?#]/)[0];
        switch (e) {
            //取款页面
            case 'qukuan':
                var $btn = $('#J-submit');
                var $span = $('span.oc-only');
                configData.getConfigData();
                var drawCashLimit = configData.drawCashLimit;
                //$('[data-drawcash-start]').html(drawCashLimit.startTime);
                //$('[data-drawcash-end]').html(drawCashLimit.endTime);
                var time_range = function(nowTime) {
                    switch (drawCashLimit.isCross) {
                        case 1:
                            if (drawCashLimit.startTime <= nowTime && nowTime <= "23:59:59") {
                                $btn.removeClass('disabled').removeAttr('disabled', 'disabled');
                                $('#formDrawCash').submit();
                                return true;
                            }
                            if ("00:00:00" <= nowTime && nowTime <= drawCashLimit.endTime) {
                                $btn.removeClass('disabled').removeAttr('disabled', 'disabled');
                                $('#formDrawCash').submit();
                                return true;
                            }
                            break;
                        case 0:
                            if (drawCashLimit.startTime <= nowTime && nowTime <= drawCashLimit.endTime) {
                                $btn.removeClass('disabled').removeAttr('disabled', 'disabled');
                                $('#formDrawCash').submit();
                                return true;
                            }
                            break;
                    }
                    $btn.addClass('disabled').attr('disabled', 'disabled');
                    commonUtil.showTip('当前时间段内不能取款，请留意取款时间。');
                    return false;
                }
                // if (keyWords == 'oc' || keyWords == 'whgj') {
                //     $span.show();
                //     $btn.bind('click', function(e) {
                //         e.preventDefault();
                //         time_range(commonUtil.getThisTime());
                //     });
                // }
                //获取提款页面银行卡列表
                Api.getDrawCashInfo({}, function(e) {
                    if (e && e.isSuccess == 1) {
                        data = e.data;
                        $('#sn').val(data.sn.split('')[0] + '**');
                        $('.c-important').html(data.sn.split('')[0] + '**');
                        var userPoint = commonUtil.toFixedNum(data.userPoint);
                        //取款时间范围限制
                        platform == 'zd' && $('.oc-only').removeClass('oc-only');
                        $('[data-drawcash-start]').html(data.withdrawStartTime);
                        $('[data-drawcash-end]').html(data.withdrawEndTime);
                        //余额格式化
                        $('#J-money-withdrawable').html(userPoint.split('.')[0]).append('<small>.' + userPoint.split('.')[1] + '</small>');
                        //取款大小限制
                        $('#J-money-min').html(data.onceWithdrawMin);
                        $('#J-money-max').html(data.onceWithdrawMax);
                        //TOKEN
                        $('input[name=token]').val(data.token);
                        var bankCards = data.bankCards;
                        if (bankCards.length > 0) {
                            for (var i = 0; i < bankCards.length; i++) {
                                var bankCardNo = bankCards[i].bankCardNo;
                                var bankCardNo_s = bankCardNo.substring(bankCardNo.length, bankCardNo.length - 4);
                                var banks_Tmpl = '<div class="card-info-list ' + bankCards[i].is_default + '">' +
                                    '<label class="img-bank" data-id="' + bankCards[i].bankAllas + '" data-value=' + bankCards[i].bankCardNo + ' data-bind=' + bankCards[i].is_default + '>' +
                                    '<span class="check-icon"></span>' +
                                    '<span class="ico-bank ' + bankCards[i].bankAllas + '"></span>' +
                                    '<span>尾号：' + bankCardNo_s + '</span>' +
                                    '</label>' +
                                    '</div>'
                                $('#drawCashBankList').prepend(banks_Tmpl);
                            }
                            $('#drawCashBankList').children('.1').eq(0).find('label.img-bank').addClass('active');
                        };
                    }
                });
                var tip = new dsgame.Tip({
                    cls: 'j-ui-tip-b j-ui-tip-input-floattip'
                }),
                $bankname = $('#J-bank-name');
                $('body').on('click', 'label.img-bank', function() {
                    var $this = $(this);
                    // if ($this.hasClass('active') || $this.data('bind') != '1') return false;
                    if ($this.data('bind') != '1') {
                        commonUtil.commonTipAlert('当前银行卡不可用');
                        return false;
                    }
                    $('label.img-bank').removeClass('active');
                    $this.addClass('active');
                    var id = parseInt($this.find('.ico-bank').data('id')) || 0;
                    $bankname.val(id);
                });

                if(keyWords !== 'zd'){
                    $('#withdrawMoney').show();
                    $('#withdrawMoney_zd').remove();
                }else {
                    $('#withdrawMoney_zd').show();
                    $('#withdrawMoney').remove();
                }

                var moneyInput = $('#withdrawMoney, #withdrawMoney_zd');
                moneyInput.keyup(function(e) {
                    var v = $.trim(this.value),
                        arr = [],
                        code = e.keyCode;
                    if (code == 37 || code == 39) {
                        return;
                    }
                    v = v.replace(/[^\d|^\.]/g, '');
                    arr = v.split('.');
                    if (arr.length > 2) {
                        v = '' + arr[0] + '.' + arr[1];
                    }
                    arr = v.split('.');
                    if (arr.length > 1) {
                        arr[1] = arr[1].substring(0, 2);
                        v = arr.join('.');
                    }
                    this.value = v;
                    v = v == '' ? '&nbsp;' : v;
                    if (!isNaN(Number(v))) {
                        v = dsgame.util.formatMoney(v);
                    }
                    tip.setText(v);
                    // tip.getDom().css({
                    //     left: moneyInput.offset().left + moneyInput.width() / 2 - tip.getDom().width() / 2
                    // });
                });

                moneyInput.focus(function() {
                    var v = $.trim(this.value);
                    if (v == '') {
                        v = '&nbsp;';
                    }
                    if (!isNaN(Number(v))) {
                        v = dsgame.util.formatMoney(v);
                    }
                    tip.setText(v);
                    tip.show(moneyInput.width() / 2 - tip.getDom().width() / 2, tip.getDom().height() * -1 - 42, this);
                });
                moneyInput.blur(function() {
                    var v = Number(this.value),
                        minNum = Number($('#J-money-min').text().replace(/,/g, '')),
                        maxNum = Number($('#J-money-max').text().replace(/,/g, '')),
                        withdrawable = Number($('#J-money-withdrawable').text().replace(/,/g, ''));
                    v = v < minNum ? minNum : v;
                    v = v > maxNum ? maxNum : v;
                    v = v > withdrawable ? withdrawable : v;
                    //纵达不允许输入小数点
                    if(keyWords !== 'zd'){
                        this.value = dsgame.util.formatMoney(v).replace(/,/g, '');
                    }else {
                        this.value = dsgame.util.formatMoney(v, 0).replace(/,/g, '');
                    }
                    tip.hide();
                });

                $("#formDrawCash").validate({
                    rules: {
                        withdrawMoney: {
                            required: true
                        },
                        payPassword: {
                            required: true
                        }
                    },
                    messages: {
                        withdrawMoney: {
                            required: "请输入提款金额"
                        },
                        payPassword: {
                            required: "请输入资金密码"
                        }
                    },
                    errorPlacement: function(error, element) {
                        // console.log(error[0]);
                    },
                    submitHandler: function(form) {
                        $(":submit").addClass("loading").attr("data-value", "clicked");
                        var pay_password = md5($('#payPassword').val());
                        $('#pay_password').val(pay_password);
                        var currentCard = $('.card-info-list').find('label.active');
                        $('input[name=cardNo]').val(currentCard.data('value'));
                        // $('input[name=user_bank_id]').val(currentCard.data('id'));
                        form.submit();
                    }
                });
                break;
                //首页
            case 'home':
                //首页公告
                $('#header-new').show();
                Api.getNoticeTitle({}, function(e) {
                    typeof initMsgs !== 'undefined' ? initMsgs(e) : !0;

                    if (e.items && e.items.length > 0) {
                        var htmlArr = [],
                            linkArr = [];
                        // var htmlArr_7h = [];
                        for (var i = (e.items.length - 1); i >= 0; i--) {
                            // htmlArr.push('<li class="even" style="cursor:pointer;" onclick="javascript:window.location=\'' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\'"> <span class="time">' + e.items[i].showCreateTime + '</span> <a> [通知] ' + e.items[i].title + '<i class="c-important">&nbsp;&nbsp;New</i></a></li></a>');
                            htmlArr.push('<li class="even" style="cursor:pointer;" onclick="javascript:window.location=\'' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\'"> <a> <span>[通知]&nbsp;&nbsp;</span> ' + e.items[i].title + '</a></li></a>');
                            linkArr.push('<a href="' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\" target="_blank"> <span>[通知]&nbsp;&nbsp;</span> ' + e.items[i].title + '</a>&nbsp;&nbsp;&nbsp;&nbsp;')
                                // htmlArr_7h.push('<span><a href="javascript:;" onclick="javascript:window.location=\'' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + '\'">' + e.items[i].title + '</a> <span class="time">' + e.items[i].showCreateTime + '</span></span>');
                        }
                        $(".news-list").html(htmlArr.join(""));
                        $(".notification-msg").html(linkArr.join(""));
                        $("#notice").append(htmlArr.join(""));
                        $('.index-notice-7h,.index-notice-whgj').marquee({
                            auto: true,
                            interval: 3000,
                            speed: 1000,
                            showNum: 11,
                            stepLen: 1,
                            type: 'vertical'
                        });
                    }else {
                        $("#notice").html('<li class="notice-nodata"><a href="javascript:;"><span>暂无数据</span></a></li>');
                    }
                });
                Api.getIndexBanner({}, function(d) {
                    d = d.result;
                    if (typeof d !== 'undefined' && d.length > 0) {
                        var rowHtmlArr = [];
                        var bannerArr = [];
                        for (var i = 0; i < d.length; i++) {
                            var banner_html = '<a href="' + ctx + '/mkg/activity/goIndex.html#' + d[i].relatedActivityFont + '"><img src="' + d[i].imagePath + '" alt=""></a>'
                            bannerArr.push(banner_html);
                        }
                        $('.cycle-slideshow').cycle('add', bannerArr);
                    } else {
                        $('.cycle-slideshow').append('<a href="javascript:;"><img src="/static/images/no-banner.png" alt="暂无数据" title="暂无数据"></a>');
                    }
                });
                break;
            case 'noticeDetail':
                Api.getNoticeTitle({}, function(e) {
                    var htmlArr = [],
                        linkArr = [];
                    var currID = commonUtil.getUrlData('?').split('=')[1];
                    var frameIs = '';
                    //console.log(currID);
                    if (String(currID).indexOf('&')!=-1) {
                      frameIs = String(currID).split('&')[1];
                      currID = String(currID).split('&')[0];
                      
                      $('#header,#nav,#template-footer').hide();
                      $('#nav').next().addClass('nomargin');
                    }
                    for (var i = 0; i < e.items.length; i++) {
                        htmlArr.push('<li id="' + e.items[i].id + '" class="even" style="cursor:pointer;" onclick="javascript:window.location=\'' + ctx + '/mkg/adminCommon/noticeDetail.html?id=' + e.items[i].id + ''+(frameIs==''?'':'&iframe=1')+'\'"> <a> <span>[通知]&nbsp;&nbsp;</span> ' + e.items[i].title + '</a></li></a>');
                    }
                    //console.log(frameIs,'frameIs');
                    $("#notice-list").append(htmlArr.join(""));
                    $('#notice-list').children('#' + currID + '').addClass('current');
                });
                break;
            case 'goTransfer':
                //转账页面options切换脚本
                Api.getptList({}, function(d) {
                    //如果是从接口取得数据则
                    for (var i = d.length - 1; i >= 0; i--) {
                        var optionId = d[i].cbId;
                        var optionName = d[i].cbName;
                        var option = $("<option>").text(d[i].cbName).attr({
                            "id": optionId,
                            "value": optionId
                        });
                        $("#turnOut,#private_platform").append(option);
                        var _option = option.not("#sobet_01");
                        $("#private_platform_2").append(_option);

                    };
                    $('#turnOut').change(function() {
                        var from = $(this).children('option:selected').attr("id");
                        var sVal = $(this).children('option:selected').text();
                        $('#main #out').html(sVal);
                        $("#turnOutCn").val(sVal);
                        if (from != "" && typeof(from) != 'undefined') {
                            Api.getPtBalance('cbId=' + from + '', function(d) {
                                var thisCash = d.cash;
                                if (typeof(thisCash) != 'indefined') {
                                    $("#qbBalance").html(commonUtil.toFixedNum(thisCash));
                                }
                            });
                        } else {
                            $("#qbBalance").html('');
                        }
                        $("#turnIn option").each(function() {
                            $(this).remove();
                        });
                        //实时生成被转出的select
                        if (from == "sobet_01") {
                            var tmphtml = $("#private_platform_2").html();
                            $("#turnIn").append(tmphtml);
                            var turnInCn = $("#turnIn").find("option:selected").text();
                            $("#turnInCn").val(turnInCn);
                            $("#main #in").html(turnInCn);
                        } else {
                            $("#turnIn").append("<option id='sobet_01' selected='selected' value='sobet_01'>平台钱包</option>");
                            $("#main #in").html("平台钱包");
                            $("#turnInCn").val("平台钱包");
                        }
                        if ($('#turnOut').val() == '') {
                            $("#turnIn").html('').append("<option value=''>请选择</option>");
                        }
                    });
                    $("#turnIn").change(function() {
                        var selValIn_ = $("#turnIn option:selected").text();
                        var turnInId = $("#turnIn option:selected").attr('id');
                        $("#turnInCn").val(selValIn_);
                        $("#main #in").html(selValIn_);
                    });
                });
                break;
            case 'orders':
                Api.getLotteryList({}, function(rst) {
                    firstSelect = new dsgame.Select({
                        realDom: '#J-select-1',
                        cls: 'w-3',
                        valueKey: 'id',
                        textKey: 'name'
                    });
                    secondSelect = new dsgame.Select({
                        realDom: '#J-select-2',
                        cls: 'w-4',
                        valueKey: 'id',
                        textKey: 'name'
                    });
                    commonUtil.buildSelect(firstSelect, secondSelect, rst.result.lotteryList);
                    var selectIssue = new dsgame.Select({
                            realDom: '#J-select-issue',
                            cls: 'w-2'
                        }),
                        selectStatus = new dsgame.Select({
                            realDom: '#J-select-status',
                            cls: 'w-2'
                        });
                });
                break;
            case 'statement':
                new dsgame.Select({
                    realDom: '#J-select-queryType-account',
                    cls: 'w-3'
                });
                new dsgame.Select({
                    realDom: '#J-select-queryType-recharge',
                    cls: 'w-3'
                });
                new dsgame.Select({
                    realDom: '#J-select-queryType-drawcash',
                    cls: 'w-3'
                });
                break;
            case 'dayreport':
                var querySelect = new dsgame.Select({
                    realDom: '#J-select-queryType-single',
                    cls: 'w-2'
                });
                //var querySelectGame = new dsgame.Select({realDom: '#J-select-queryGame', cls: 'w-2'});
                //querySelect.addEvent('change', function (e, value, text) {});
                break;
            case 'deposit':
                //TAB组件初始化
                $('.tabs_default').tabslet();
                $('.tab-second').tabslet({
                    active: false
                });
                configData.getConfigData();
                var limitAmount = configData.chargeLimit;

                var wechatmin = limitAmount.wechat.min;
                var wechatmax = limitAmount.wechat.max;

                var alimin = limitAmount.alipay.min;
                var alimax = limitAmount.alipay.max;

                var $min = $('#eachMinM');
                var $max = $('#dayMaxM');
                var minAmount = $min.html();
                var maxAmount = $max.html();
                //充值限额配置，微信和支付宝扫码
                var scanAmountLimit = {
                        wechat: {
                            min: wechatmin,
                            max: wechatmax
                        },
                        alipay: {
                            min: alimin,
                            max: alimax
                        }
                    }
                    //切换事件：切换后
                $('.tabs_default').on('_after', function() {
                    var payType = $('.tabs_default .active').find('a').data('type');
                    //单笔存款限制
                    if (payType == 3) {
                        $min.html(scanAmountLimit.wechat.min);
                        $max.html(scanAmountLimit.wechat.max);
                    } else if (payType == 4 || payType == 100021) {
                        $min.html(scanAmountLimit.alipay.min);
                        $max.html(scanAmountLimit.alipay.max);
                    } else if (payType === '000007' || payType === 100008 || payType === 100011 || payType === 100020) { //微信支付
                        //充值限额，最小和最大
                        $min.html(wechatmin);
                        $max.html(wechatmax);
                    } else {
                        $min.html(minAmount);
                        $max.html(maxAmount);
                    }
                });
                $(".banks-new").find('li').eq(0).trigger('click');
                var myDate = new Date();
                var thisDate = myDate.toLocaleDateString();
                var thisHour = myDate.getHours();
                var thisMm = myDate.getMinutes();

                String(thisHour).length == 1 ? thisHour = '0' + thisHour : thisHour = thisHour;
                String(thisMm).length == 1 ? thisMm = '0' + thisMm : thisMm = thisMm;

                function fillInfo(way) {
                    var cate = {
                        wechat: Api.getWechatInfo,
                        alipay: Api.getAlipayInfo
                    }
                    cate[way]({}, function(res) {
                        if (res.isSuccess === 1) {
                            var account_dom = $('label[data-' + way + '-account]');
                            var name_dom = $('label[data-' + way + '-name]');
                            var qrcode_dom = $('img[data-' + way + '-qrcode]')
                            var orderNo_dom = $('#' + way + '_orderNo');
                            var fuyan_dom = $('label[data-' + way + '-fuyan]');
                            var data = res.data;
                            var account = data.account;
                            var nickName = data.nickName;
                            var orderNo = data.orderNo;
                            var qrCode = data.graphicDataStr;
                            var fuyan = data.fuyanNo;
                            account_dom.html(account);
                            name_dom.html(nickName);
                            qrcode_dom.attr('src', qrCode);
                            orderNo_dom.val(orderNo);
                            fuyan_dom.html(fuyan);
                        }
                    });

                    $('#' + way + '_recharge_time').val(thisDate);

                    for (var i = 0; i < 24; i++) {
                        if (String(i).length == 1) {
                            i = '0' + i;
                        }
                        $('#' + way + '-time-mm').append('<option>' + i + '</option>');
                    }
                    $('#' + way + '-time-mm').val(thisHour);

                    for (var s = 0; s < 60; s++) {
                        if (String(s).length == 1) {
                            s = '0' + s;
                        }
                        $('#' + way + '-time-ss').append('<option>' + s + '</option>');
                    }
                    $('#' + way + '-time-ss').val(thisMm);

                    $('#' + way + '-timeSet').jBox('Tooltip', {
                        content: '充值时间可选择精确至时分，以当地时间为准',
                        position: {
                            x: 'right',
                            y: 'center'
                        },
                        outside: 'x',
                        offset: {
                            x: 100
                        }
                    });
                    var contentObj1 = {
                        wechat: '充值限额：最低充值' + wechatmin + '元，最高充值' + wechatmax + '元',
                        alipay: '充值限额：最低充值' + wechatmin + '元，最高充值' + wechatmax + '元'
                    }
                    $('#amount_' + way + '').jBox('Tooltip', {
                        content: contentObj1[way],
                        position: {
                            x: 'right',
                            y: 'center'
                        },
                        outside: 'x'
                    });
                    var contentObj2 = {
                        wechat: '此处填入您的微信昵称',
                        alipay: '此处填入您的支付宝昵称'
                    }
                    $('#' + way + '_username').jBox('Tooltip', {
                        content: contentObj2[way],
                        position: {
                            x: 'right',
                            y: 'center'
                        },
                        outside: 'x'
                    });
                }
                //目前开通扫描支付的平台只有7h
                //if(keyWords == '7h'){
                fillInfo('wechat');
                fillInfo('alipay');
                //}
                break;
            case 'general':
                var today = commonUtil.GetDateToday(); //当天数据
                var month = commonUtil.GetDateMonth(); //最近一个月数据
                var week = commonUtil.GetDateWeek(); //最近一周数据
                $('#J-date-start').val(today);
                $('#J-date-end').val(today);
                var $tabLi = $('.general-detail li');
                var $sevenDays = $('a.seven-days');
                var $oneMonth = $('a.one-month');
                var $search = $('a.search');
                var $timeStart = $('#J-date-start');
                var $timeEnd = $('#J-date-end');
                var currChartData = $('.general-detail li.active').data('value');
                var dateArr = [];
                //默认查询最近一天的数据
                var timeParam = {
                    start: today, //开始时间
                    end: today //结束时间
                }
                var depositArr = [];
                var drawcashArr = [];
                var costArr = [];
                var prizeArr = [];
                var pointArr = [];
                var promototalArr = [];
                var newusrArr = [];
                var newusrDate = [];
                var colorPalette = ['#d87c7c', '#919e8b', '#d7ab82', '#6e7074', '#61a0a8', '#efa18d', '#787464', '#cc7e63', '#724e58', '#4b565b'];
                echarts.registerTheme('vintage', {
                    color: colorPalette,
                    // backgroundColor: '#fef8ef',
                    graph: {
                        color: colorPalette
                    }
                });
                var myChart = echarts.init(document.getElementById('myChart'), 'vintage');
                myChart.showLoading();
                var option = {
                    title: {
                        text: '团队总览'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: []
                    },
                    animationDuration: 300,
                    toolbox: {
                        show: true,
                        feature: {
                            magicType: {
                                show: true,
                                type: ['stack', 'tiled']
                            },
                            dataView: {
                                readOnly: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    xAxis: {
                        data: dateArr
                    },
                    yAxis: {},
                    series: [{
                        name: [],
                        type: 'line',
                        smooth: true,
                        data: []
                    }]
                };

                var titleMap = {
                        depoist: '充值总额',
                        drawcash: '提款总额',
                        cost: '消费总额',
                        prize: '派奖总额',
                        point: '返点总额',
                        promototal: '活动总额',
                        newusr: '新增会员'
                    }
                    //后取数据，渲染图标
                function renewData(currChartData) {
                    myChart.hideLoading();
                    dateArr = [];
                    Api.getChartData(timeParam, function(d) {
                        d = d.data;
                        //总数据
                        $('[data-depoist-money]').html(d.depositAmountSum); //充值总额
                        $('[data-drawcash-money]').html(d.withdrawAmountSum); //提款总额
                        $('[data-cost-money]').html(d.confirmAmountSum); //消费总额
                        $('[data-prize-money]').html(d.awardAmountSum); //派奖总额
                        $('[data-point-money]').html(d.pointAmountSum); //返点总额
                        $('[data-promo-money]').html(d.activityAmountSum); //活动总额
                        $('[data-new-user]').html(d.registerSum); //新增用户
                        //团队成员
                        $('dl.team-num').find('dd').eq(0).find('i').html(d.userCount); //总人数
                        $('dl.team-num').find('dd').eq(1).find('i').html(d.agentCount); //代理
                        $('dl.team-num').find('dd').eq(2).find('i').html(d.memberCount); //会员
                        //团队余额
                        $('dl.balance').find('i').html(d.teamAvailableBalance);
                        //在线人数
                        $('dl.online').find('i').html(d.userOnlineCount);
                        if (typeof d !== 'undefined' && d.dayReportList.length > 0) {
                            var charData = d.dayReportList;
                            var newusr = d.registerList; //新增用户
                            for (var i = 0; i < newusr.length; i++) {
                                newusrArr.push(newusr[i].count);
                                newusrDate.push(newusr[i].registerDate);
                            }
                            for (var i = 0; i < charData.length; i++) {
                                //日数据
                                var date = charData[i].date;
                                depositA = charData[i].depositAmount; //充值总额
                                drawcashA = charData[i].withdrawAmount; //提款总额
                                costA = charData[i].confirmAmount; //消费总额
                                prizeA = charData[i].awardAmount; //派奖总额
                                pointA = charData[i].pointAmount; //返点总额
                                promototalA = charData[i].activityAmount; //活动总额
                                dateArr.push(date);
                                depositArr.push(depositA);
                                drawcashArr.push(drawcashA);
                                costArr.push(costA);
                                prizeArr.push(prizeA);
                                pointArr.push(pointA);
                                promototalArr.push(promototalA);
                            }
                            var keyMap = {
                                depoist: depositArr,
                                drawcash: drawcashArr,
                                cost: costArr,
                                prize: prizeArr,
                                point: pointArr,
                                promototal: promototalArr,
                                newusr: newusrArr
                            }
                            currChartData == 'newusr' ? option.xAxis.data = newusrDate : option.xAxis.data = dateArr;
                            option.series[0].name = titleMap[currChartData];
                            option.legend.data = titleMap[currChartData];
                            option.series[0].data = keyMap[currChartData];
                            myChart.setOption(option);

                            $tabLi.unbind('click').bind('click', function() {
                                myChart.clear();
                                var me = $(this);
                                var key = me.data('value');
                                key == 'newusr' ? option.xAxis.data = newusrDate : option.xAxis.data = dateArr;
                                me.addClass('active').siblings().removeClass('active');
                                option.series[0].name = titleMap[key];
                                option.legend.data = titleMap[key];
                                option.series[0].data = keyMap[key];
                                myChart.setOption(option);
                            });

                        } else {
                            myChart.clear();
                            $tabLi.unbind('click');
                            myChart.setOption({
                                title: {
                                    text: '暂无数据'
                                },
                                tooltip: {
                                    trigger: 'axis'
                                },
                                legend: {
                                    data: []
                                        // data: titleMap[currChartData]
                                },
                                xAxis: {
                                    data: []
                                },
                                yAxis: {},
                                series: []
                            });
                        }

                        //指定天数过滤
                        $('.area-search a').unbind('click').bind('click', function() {
                            depositArr = [];
                            drawcashArr = [];
                            costArr = [];
                            prizeArr = [];
                            pointArr = [];
                            promototalArr = [];
                            newusrArr = [];
                            newusrDate = [];
                            currChartData = $('.general-detail li.active').data('value');
                            $(this).addClass('active').siblings().removeClass('active');
                            var searchType = $(this).data('value');
                            switch (searchType) {
                                case 'week':
                                    timeParam.start = today;
                                    timeParam.end = week;
                                    $('#J-date-start').val(today);
                                    $('#J-date-end').val(week);
                                    renewData(currChartData);
                                    break;
                                case 'month':
                                    timeParam.start = month;
                                    timeParam.end = today;
                                    $('#J-date-start').val(month);
                                    $('#J-date-end').val(today);
                                    renewData(currChartData);
                                    break;
                                case 'all':
                                    if ($timeStart.val() !== '' && $timeEnd.val() !== '') {
                                        $sevenDays.removeClass('active');
                                        $oneMonth.removeClass('active');
                                        timeParam.start = $timeStart.val();
                                        timeParam.end = $timeEnd.val();
                                        renewData(currChartData);
                                    } else {
                                        commonUtil.showTip('开始或结束时间不能为空');
                                    }
                                    break;
                            }
                        });
                    });
                }
                renewData(currChartData);
        }
    }
})(jQuery);