/**
 * Created by User on 2016/6/16.
 * 整合分页
 */
(function($) {

    //各查询接接口url map
    var urlMap = {
        game: '/mkg/api/query/orders', //游戏记录
        trace: '/mkg/api/query/lottery_trace', //追号记录
        account: '/mkg/api/query/statements', //账变记录
        recharge: '/mkg/api/query/recharge', //充值记录
        drawcash: '/mkg/api/query/withdraw', //提现记录
        single: '/mkg/api/query/dayreport', //个人报表
        team: '/mkg/api/query/teamreport', //团队报表
        user: '/mkg/api/agent/users', //用户列表
        links: '/mkg/api/agent/links', //注册链接
        msgs: '/u/api/message/queryMessage', //消息列表
        thirdgame: '/api/i/u/query/tpOrders', //游戏记录
        salary: '/mkg/api/query/userSalary', //日工资
        dividend: '/mkg/api/query/userProfitBonus', //分红
        thirdforms: '/api/i/u/query/reports/game', //游戏报表
        thirdteam: '/api/i/u/query/tpTeamReport' //第三方团队报表
    };

    var localMap = {
        //dividend:'/static/json/2.json',//分红
    }

    var todayDate = commonUtil.GetDateToday();
    $('input[name=bought_at_from]:not(.personal)').val('' + todayDate + ' 00:00:00');
    $('input[name=bought_at_to]:not(.personal)').val('' + todayDate + ' 23:59:59');

    if (location.hash == '#salary_view') {
        $("#salary").show().siblings('.content').hide();
        $('.tab-title li').removeClass('current');
        $("[data-tab-salary]").addClass('current');
        $('.choose-model').hide();
    }
    if (location.pathname =='/hz/mkg/statement.html') {
      $('.zjmx').attr('href','javascript:;').click(function(){
        $('a[data-skey="account"]').click();
      });
    }
    if (location.hash == '#dividend_view') {
        $("#dividend").show().siblings('.content').hide();
        $('.tab-title li').removeClass('current');
        $("[data-tab-dividend]").addClass('current');
        $('.choose-model').hide();
        $('.area-search').hide();
    } else {
        $('.area-search').show();
    }
    if (location.hash == '#account_view') {
        $("#account").show().siblings('.content').hide();
        $('.tab-title li').removeClass('current');
        $("[data-tab-account]").addClass('current');
    }
    if (location.hash == '#recharge_view') {
        $("#recharge").show().siblings('.content').hide();
        $('.tab-title li').removeClass('current');
        $("[data-tab-recharge]").addClass('current');
    }
    if (location.hash == '#thirdteam_view') {
        $("#thirdteam").show().siblings('.content').hide();
        $('.tab-title li').removeClass('current');
        $("[data-tab-thirdteam]").addClass('current');
    }
    if (location.hash == '#drawcash_view') {
        $("#drawcash").show().siblings('.content').hide();
        $('.tab-title li').removeClass('current');
        $("[data-tab-drawcash]").addClass('current');
    }
    //封装执行函数
    var page3a = {
        //页面初始化以及出发查询事件后，过滤条件集合
        getQueryData: function(skey, isDirect) {
            var number_type = $("select[name='number_type'] option:selected").val(); //奖期编号还是
            var number_value = $("input[name='number_value']").val(); //number_type对应的值
            var issue;
            if (number_type === 'issue') {
                issue = number_value;
                number_value = null;
            }
            //判断是否从报表查询页面带过来含有用户名和时间的查询
            var start,
                end,
                subName;
            var queryArr = [];
            if (typeof isDirect == 'undefined') {
                start = $('#J-date-start').val(); //查询开始时间
                end = $('#J-date-end').val(); //查询结束时间
                subName = $('#J-input-subname').val(); //查询下级-下级用户名
            } else {
                var queryStr = commonUtil.getUrlData('?').split('&');
                for (var i = 0; i < queryStr.length; i++) {
                    var perStr = queryStr[i].split('=')[1];
                    queryArr.push(perStr);
                }
                var currName = $('span[data-user-name]').html(); //获取当前登录用户名
                queryArr[0] == currName ? subName = '' : subName = queryArr[0]; //带过来的用户名，如果是登录用户自己，则subName置空
                if (skey == 'account' || skey == 'recharge') {
                    subName = '';
                    $('#J-name').val(queryArr[0]);
                }
                start = '' + queryArr[1] + ' 00:00:00'; //开始时间
                end = '' + (typeof queryArr[2] !== 'undefined' ? queryArr[2].replace(location.hash, '') : '') + ' 23:59:59'; //结束时间
                if (currName == subName) { //如果查询的是自己
                    $('#J-input-subname').val('');
                } else {
                    $('#J-input-subname').val(subName); //赋值
                }
                $('#J-date-start').val(start);
                $('#J-date-end').val(end);
                delete isDirect;
            }
            var userName = $('#J-name').val(); // 查询-用户名
            var lottery = $("select[name='lottery_id'] option:selected").val(); //所选游戏名称，即彩种名称
            var method = $("select[name='way_group_id'] option:selected").val(); //彩种对应玩法名称
            var status = $("select[name='status'] option:selected").val(); //状态
            var account_from = $('input[name=account_from]').val();
            var account_to = $('input[name=account_to]').val();
            var queryType = $('select[id="J-select-queryType-' + skey + '"] option:selected, select[id="J-select-queryType"] option:selected').val(); //类型
            var group_by;
            if (skey == 'dividend') {
                userName = $('#J-inner-name').val();
                start = $('#startInner').val();
                end = $('#endInner').val();
            }
            if (skey == 'thirdteam') {
                subName = $('#J-name').val();
                userName = '';
                group_by = $('#J-select-queryGame').val();
            }

            var queryData = {
                //查询开始时间
                start: start,
                //结束时间
                end: end,
                //？
                orderId: number_value,
                //奖期编号
                issue: issue,
                //用户名
                userName: userName,
                //游戏名称
                lottery: lottery,
                //对应玩法名称
                method: method,
                //下级用户名
                subName: subName,
                //游戏记录查询状态
                status: status,
                //类型
                queryType: queryType,
                //第三方查询参数-游戏平台type
                game: queryType,
                //余额范围-起始
                account_from: account_from,
                //余额范围-结束
                account_to: account_to,
                //第三方团队报表查询类型0全部,1AG,2PT,3沙巴
                groupby: group_by,
                //当前页
                currPage: 1
            };
            return queryData;
        },
        //对象属性过滤
        pramsFilter: function(obj) {
            //删除对象中无效字段
            for (var i in obj) {
                if (obj.hasOwnProperty(i)) {
                    typeof obj[i] == 'undefined' || obj[i] == '' ? delete obj[i] : '';
                }
            }
        },
        //触发二次查询事件，此时分页已渲染完毕，则只需要指定params、remote，以及页码DOM重新渲染
        triggerEvt: function(skey, total, queryData) {
            if (typeof urlMap[skey] !== 'undefined') {
                $('#pagination-container-' + skey + '').pagination('setParams', queryData);
                $('#pagination-container-' + skey + '').pagination('remote');
                $('#pagination-container-' + skey + '').pagination('render', total);
            }
        },
        //初始化 category，确定点击按钮是要查询哪个接口
        dataInit: function() { //初始化，选定category，包括定义category下属input select等的值；

            //当出发TAB事件后，再次执行此初始化函数，执行获取category以及传递的data的值
            var currTitle = $('.tab-title li.current a, input[name=skey]');
            //查询keyword
            var skey = currTitle.data('skey');

            if (skey == 'salary') {
                //$("#salary").show().siblings('.content').hide();
                $('.tab-title li').removeClass('current');
                $("[data-tab-salary]").addClass('current');
                $('.choose-model').hide();
            }
            if (skey == 'dividend') {
                //$("#dividend").show().siblings('.content').hide();
                $('.tab-title li').removeClass('current');
                $("[data-tab-dividend]").addClass('current');
                $('.choose-model').hide();
            }
            if (skey == 'single') {
                $('#J-select-queryGame,#J-select-queryGame-label').hide();
            } else {
                $('#J-select-queryGame,#J-select-queryGame-label').show();
            }

            page3a.noData(skey); //清空当前数据

            //确认URL是否带有参数
            var isDirect = commonUtil.getUrlData('?');
            //切换tab直接查询
            var isInit = false; 

            if (location.pathname == '/hz/mkg/teamreport.html' || location.pathname == '/hz/mkg/dayreport.html') {
                $('#J-date-start').val($('#J-date-end').val());
                if (location.hash == '#salary_view') {
                    $('#J-date-start,#J-date-end').val((new Date()).DateAdd('d', -1).Format("yyyy-MM-dd"));
                }
                if (location.hash == '#dividend_view') {
                    $('#J-date-start,#J-date-end,#startInner,#endInner').val((new Date()).DateAdd('d', -1).Format("yyyy-MM-dd"));
                }
            }
            if (location.pathname == '/hz/mkg/orders.html') {
                $('#J-date-start').val((new Date()).Format("yyyy-MM-dd 00:00:00"));
                $('#J-date-end').val((new Date()).Format("yyyy-MM-dd 23:59:59"));
            }

            var queryData = page3a.getQueryData(skey, isDirect); //重定义过滤条件
            page3a.pramsFilter(queryData);

            console.log($==jQuery,$('#pagination-container-' + skey + ''),'containercontainercontainer');
            

            require([
              'pagination'
            ], function() {
              console.log(typeof $('#pagination-container-' + skey + '').pagination,'$.pagination$.pagination');
              isInit = $('#pagination-container-' + skey + '').pagination();//分页组件是否已经初始化
            });  
            if (isInit) { //如果分页组件已经初始化
                page3a.triggerEvt(skey, isDirect, queryData);
            } else { //如果分页组件还未初始化，即页面第一次加载，则调用初始化分页组件方法
                page3a.callApi(skey, isDirect, queryData);
            }
            //初始化日历控件
            if (skey == 'single' || skey == 'salary' || skey == 'dividend' || skey == 'thirdteam' || skey == 'team' || skey == 'general') {
                $('#J-date-start').unbind('click').click(function() {
                    if ($(this).hasClass('on')) {
                        $('.j-ui-datepicker').hide();
                        $(this).removeClass('on');
                    } else {
                        (new dsgame.DatePicker({
                            input: '#J-date-start',
                            isShowTime: false
                        })).show();
                        $(this).addClass('on');
                    }
                });
                $('#J-date-end').unbind('click').click(function() {
                    if ($(this).hasClass('on')) {
                        $('.j-ui-datepicker').hide();
                        $(this).removeClass('on');
                    } else {
                        (new dsgame.DatePicker({
                            input: '#J-date-end',
                            isShowTime: false
                        })).show();
                        $(this).addClass('on');
                    }
                });
            } else {
                $('#J-date-start').unbind('click').click(function() {
                    if ($(this).hasClass('on')) {
                        $('.j-ui-datepicker').hide();
                        $(this).removeClass('on');
                    } else {
                        (new dsgame.DatePicker({
                            input: '#J-date-start',
                            isShowTime: true
                        })).show();
                        $(this).addClass('on');
                    }
                });
                $('#J-date-end').unbind('click').click(function() {
                    if ($(this).hasClass('on')) {
                        $('.j-ui-datepicker').hide();
                        $(this).removeClass('on');
                    } else {
                        (new dsgame.DatePicker({
                            input: '#J-date-end',
                            isShowTime: true
                        })).show();
                        $(this).addClass('on');
                    }
                });
            }
            if (skey == 'dividend') {
                $('#startInner').unbind('click').click(function() {
                    if ($(this).hasClass('on')) {
                        $('.j-ui-datepicker').hide();
                        $(this).removeClass('on');
                    } else {
                        (new dsgame.DatePicker({
                            input: '#startInner',
                            isShowTime: false
                        })).show();
                        $(this).addClass('on');
                    }
                });
                $('#endInner').unbind('click').click(function() {
                    if ($(this).hasClass('on')) {
                        $('.j-ui-datepicker').hide();
                        $(this).removeClass('on');
                    } else {
                        (new dsgame.DatePicker({
                            input: '#endInner',
                            isShowTime: false
                        })).show();
                        $(this).addClass('on');
                    }
                });
            }
            //不同查询模块下的过滤条件逻辑
            page3a.filterControl(skey);
            //不同查询模块下select控件的隐藏与显示
            $('#J-select-queryType-' + skey + '').prev().show().siblings('.choose-model').hide();

        },
        //调用各查询API
        callApi: function(skey, isDirect, queryData) {
            var me = this;
            var getPersonProfitBonus = function() {
              Api.getCommon('personProfitBonus', {}, function(res) {
                  if (res.errno=='1001') {
                    return false;
                  }
                  var c = res.result;
                  $('#dividend-details .startDay').html(c.startDay);
                  $('#dividend-details .endDay').html(c.endDay);
                  //console.log(c.percent,c);

                  if (c.percent=='') {
                    $('#dividend-details .percent').html('我的分红');
                  }else {
                    $('#dividend-details .percent').html(c.percent * 100 + '%');
                  }

                  $('#dividend-details .profitAmount').html(c.profitAmount);
                  if (c.mixPercents=='' || c.mixPercents==null) {
                    $('#dividend-details .percent').addClass('nobot').removeClass('hand');
                  }
                  $('#dividend-details .percent').unbind('click').click(function(){
                    if (c.mixPercents=='' || c.mixPercents==null) {
                      return false;
                    }
                    var tipAlert = new jBox('Modal', {
                        id: 'showProfitAmount',
                        title: '我的分红',
                        content: '<p><input type="checkbox" onclick="return false" id="showLastweek">累计上周期模式<em id="help_lastloop" class="m_ico hand">&#59183;</em></p><div><em id="help_sales" class="m_ico hand">&#59183;</em><ul class="myrates"></ul></div>',
                        confirmButton: '确定',
                        closeButton: 'title',
                        minWidth: 430,
                        onClose: function() {

                        },
                        onOpen: function() {
                          $('#showProfitAmount #help_lastloop').jBox('Tooltip', {
                            content: '勾选之后下级团队将自动累计每月上半月的负亏损到下半月'
                          });
                          $('#showProfitAmount #help_sales').jBox('Tooltip', {
                            content: '销量最多可以设置10个等级，当月团队销量达到<br>哪一个等级就按照对应的分红比例计算'
                          });
                          if (c.mixPercents!='' && c.mixPercents!=null) {
                            var allpercents = JSON.parse(c.mixPercents);
                            for (i = 0; i < allpercents.length; i++) {
                              $('.myrates').append('<li>半月总销量(元)≥<em>'+allpercents[i].amount+'</em><span class="gapin"></span>分红<em>'+allpercents[i].percent+'</em>%</li>');
                            }
                          }
                          if (c.checkLast) {
                            $('#showProfitAmount #showLastweek').attr('checked','checked');
                          }
                        }
                    }).open();
                  });
                  $('#dividend-details .bonus').html(c.bonus);
                  $('#dividend-details .receivedBonus').html(c.receivedBonus);
                  $('#dividend-details .deservedBonus').html(c.deservedBonus);
                  $('#paifa-details .shouleDistributBonus').html(c.shouleDistributBonus);
                  $('#paifa-details .alreadyDistributBonus').html(c.alreadyDistributBonus);
                  $('#paifa-details .yetDistributBonus').html(c.yetDistributBonus);
                  if (parseFloat(c.yetDistributBonus) > 0) {
                      $('#sendBonus').show().removeClass('disabled');
                  } else {
                      $('#sendBonus').show().addClass('disabled');
                  }
              });
            }
            $('#pagination-container-' + skey + '').html('');
            $('#pagination-container-' + skey + '').show();
            require([
              'pagination'
            ], function() {
              console.log(typeof $('#pagination-container-' + skey + '').pagination,'$11111111111.pagination$.pagination');
              $('#pagination-container-' + skey + '').pagination({
                  debug: true,
                  showInfo: true,
                  showJump: true,
                  showPageSizes: true,
                  loadFirstPage: true,
                  showFirstLastBtn: true,
                  remote: {
                      url: (typeof localMap[skey] !== 'undefined' ? localMap[skey] : ctx + urlMap[skey]),
                      //url: skey == 'thirdgame' ? urlMap[skey] = ctx + urlMap[skey] + queryData.queryType : ctx + urlMap[skey],
                      params: queryData,
                      totalName: 'page.totalItem',
                      beforeSend: function() {
                          jb_this.open();
                      },
                      success: function(data) {
                          jb_this.close();
                          if (skey == 'dividend') {
                            getPersonProfitBonus();
                          }
                          if (typeof(data.code) != 'undefined' && data.code === 0) {
                              var resdata = data.page;
                              var reldata = data.data;
                              var datas = resdata.list;
                              if (datas && datas.length > 0) {
                                  var totalItem = resdata.totalItem;
                                  var pageSize = resdata.pageSize;
                                  $('.msg-list .in').html('');
                                  // 各查询接口对应模板渲染方法
                                  var content='';
                                  var fills = {
                                      game: me.fillTable_game,
                                      trace: me.fillTable_trace,
                                      account: me.fillTable_account,
                                      recharge: me.fillTable_recharge,
                                      drawcash: me.fillTable_drawcash,
                                      single: me.fillTable_single,
                                      team: me.fillTable_team,
                                      user: me.fillTable_user,
                                      links: me.fillTable_links,
                                      msgs: me.fillTable_msgs,
                                      thirdgame: me.fillTable_thirdgame,
                                      salary: me.fillTable_salary,
                                      dividend: me.fillTable_dividend,
                                      thirdforms: me.fillTable_thirdforms,
                                      thirdteam: me.fillTable_thirdteam
                                  };
                                  fills[skey](datas, content, skey, resdata, reldata);
                              }
                              $('#sendBonus').unbind('click').click(function() {
                                  if ($(this).hasClass('disabled')) {
                                      return false;
                                  }
                                  Api.getCommon('payAllUserBonus', {}, function(res) {
                                      if (res && res.code == '1') {
                                          commonUtil.showTip('成功派发：￥'+ Number(res.result.amount).toFixed(4));
                                          getPersonProfitBonus();
                                          $('#dividendSearch').click();
                                          $('#sendBonus').addClass('disabled');
                                      } else {
                                          commonUtil.showTip(res.msg);
                                          $('#sendBonus').removeClass('disabled');
                                      }
                                  }, 'POST')
                              });
                              $('#J-submit,#dividendSearch').unbind('click').bind('click', function() {
                                  //重新查找当前高亮title的skey
                                  $('#dividend-details label').html('-');
                                  skey = $('.tab-title li.current').find('a').data('skey');
                                  queryData = page3a.getQueryData(skey);
                                  page3a.pramsFilter(queryData);
                                  page3a.noData(skey);
                                  page3a.triggerEvt(skey, totalItem, queryData);
                              });
                          }
                      }
                  }
              });
            });
        },
        //清空数据，以及没有查询到数据的展现
        noData: function(skey) {
            $('#' + skey + ' #contentTable').html('');
            $('#contentTable-sum').html('');
            //$('#pagination-container-' + skey + '').html('没有符合条件的记录，请更改查询条件');
        },
        fillTable_thirdteam: function(data, content, skey, a, b) {
            $('#startInner,#endInner').removeClass('on');
            for (var i = 0, l = data.length; i < l; i++) {
                var type = "否";
                if (data[i].winStop == "1") {
                    type = "是";
                }
                var code = data[i];

                content = content + (
                    (data[i].head == '0' || data[i].head == '1') ? (
                        ['<tr class=" table-tr-item ">',
                            '<td>', data[i].cn, '</td>',
                            '<td>', data[i].transferInamount, '</td>',
                            '<td>', data[i].transferOutamount, '</td>',
                            '<td>', data[i].confirmAmount, '</td>',
                            '<td>', data[i].awardAmount, '</td>',
                            '<td>', data[i].rebateAmount, '</td>',
                            '<td>', data[i].bonusAmount, '</td>',
                            '<td>', data[i].profitAmount, '</td>',
                            '<td><a href="javascript:;" class="query-team" data-name="', data[i].cn, '">查询</a></td>',
                            '</tr>'
                        ]
                        .join('')
                    ) : (
                        ['<tr class=" table-tr-item ">', '<td>汇总</td>',
                            '<td>', data[i].transferInamount, '</td>',
                            '<td>', data[i].transferOutamount, '</td>',
                            '<td>', data[i].confirmAmount, '</td>',
                            '<td>', data[i].awardAmount, '</td>',
                            '<td>', data[i].rebateAmount, '</td>',
                            '<td>', data[i].bonusAmount, '</td>',
                            '<td>', data[i].profitAmount, '</td>',
                            '<td>&nbsp;</td>',
                            '</tr>'
                        ]
                        .join('')
                    )

                );
            }
            $('#' + skey + ' #contentTable').html(content);
            $('#' + skey + ' #contentTable a.query-team').unbind('click').bind('click', function() {
                var queryUserName = $(this).data('name');
                $('#J-name').val(queryUserName);
                queryData = page3a.getQueryData(skey);
                page3a.pramsFilter(queryData);
                page3a.noData(skey);
                page3a.triggerEvt(skey, a.totalItem, queryData);
            });
        },
        fillTable_dividend: function(data, content, skey, a, b) {
            $('#startInner,#endInner').removeClass('on');
            for (var i = 0, l = data.length; i < l; i++) {
                var type = "否";
                if (data[i].winStop == "1") {
                    type = "是";
                }
                var code = data[i];

                content = content + (!data[i].summary ? (
                        ['<tr class=" table-tr-item ">', '<td>', data[i].startDay, '~', data[i].endDay, '</td>', '<td>', data[i].userName, '</td>',
                            '<td>', Number(data[i].percent * 100).toFixed(2), '%</td>', '<td>', data[i].realProfitAmount, '</td>', '<td>', data[i].bonus, '</td>', '<td>', data[i].statusStr, '</td>', '</tr>'
                        ]
                        .join('')
                    ) : (
                        ['<tr class=" table-tr-item ">', '<td>汇总</td>', '<td colspan="2">&nbsp;</td>', '<td>', data[i].teamAmountSum, '</td>', '<td>', data[i].bonusSum, '</td>', '<td></td>', '</tr>']
                        .join('')
                    )

                );
            }
            $('#' + skey + ' #contentTable').html(content);

        },
        fillTable_salary: function(data, content, skey) {
            $('#J-date-start,#J-date-end').removeClass('on');
            var formatLnk = function(str) {
                if (typeof str == 'undefined') {
                    return '总代';
                }
                var outputStr = [];
                for (i = 0; i < str.length; i++) {
                    outputStr.push('*')
                }
                return outputStr.join('>')
            }
            for (var i = 0, l = data.length; i < l; i++) {
                var type = "否";
                if (data[i].winStop == "1") {
                    type = "是";
                }
                var code = data[i];

                content = content + (!data[i].summary ? (
                        ['<tr class=" table-tr-item ">', '<td>', data[i].day, '</td>', '<td>', data[i].userName, '</td>',
                            //'<td>',formatLnk(data[i].agentLinks),'</td>',
                            '<td>', Number(data[i].point * 100).toFixed(2), '%</td>', '<td>', data[i].teamAmount, '</td>', '<td>', data[i].bonus, '</td>', '<td>', data[i].statusStr, '</td>', '</tr>'
                        ]
                        .join('')
                    ) : (
                        ['<tr class=" table-tr-item ">', '<td>汇总</td>', '<td colspan="2">&nbsp;</td>', '<td>', data[i].teamAmountSum, '</td>', '<td>', data[i].bonusSum, '</td>', '<td></td>', '</tr>']
                        .join('')
                    )

                );
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //数据HTML渲染方法
        //游戏记录模板
        fillTable_game: function(data, content, skey) {
            for (var i = 0; i < data.length; i++) {
                var code = data[i].code;
                if (code.length > 20) {
                    code = code.substr(0, 20) + '...';
                }
                var bingo = false;
                if (data[i].awardMoney > 0) {
                    bingo = true;
                }
                data[i].winningNumber = data[i].winningNumber || '';
                content = content + '<tr>' +
                    '<td><a style="font-family: Courier, monospace" href="' + ctx + '/mkg/orders/view/' + data[i].orderItemId + '">' + data[i].orderItemId + '	</a> </td>' +
                    '<td>' + data[i].userName + '</td>' +
                    '<td><span class="c-important">' + data[i].lottery + '</span><br>' +
                    '<span>' + data[i].method + '</span></td>' +
                    '<td><span>' + data[i].orderTime + '</span><br><span class="cell-label">期号</span>' + data[i].issue + '<br></td>' +
                    '<td style="min-width: 80px">' + code + '</td>' +
                    '<td><dfn>￥</dfn>' + data[i].perAmount + '</td>' +
                    '<td>' + data[i].count + '</td>' +
                    '<td><dfn>￥</dfn>' + data[i].amount + '</td>';
                if (bingo) {
                    content += '<td><dfn>￥</dfn><span class="c-important">' + data[i].awardMoney + '</span></td>'
                    content += '<td><span class="c-important">' + data[i].winningNumber + '</span></td>';
                    content += '<td><span class="c-important">' + data[i].state + '</span></td>';

                } else {
                    content += '<td></td>';
                    content += '<td>' + data[i].winningNumber + '</td>';
                    content += '<td>' + data[i].state + '</td>';
                }

                if(data[i].canCancel === false){
                    content += "<td>无</td>";
                }else {
                    if(data[i].cancelStatus === 1){
                        content += "<td><a class='btn disabled' id='cancelProject' href='javascript:void(0);' data-id='" + data[i].orderItemId + "'>已撤单</a></td>";
                    }else {
                        content += "<td><a class='btn btn-cancel-order' id='cancelProject' href='javascript:void(0);' data-id='" + data[i].orderItemId + "'>撤单</a></td>";
                    }
                }

                content += +'</tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
            $('#' + skey + ' #contentTable').find('a.btn-cancel-order').bind('click', function(){
                var orderItemId = $(this).data('id');
                commonUtil.confirmTip('撤销订单', '确定要撤单么？', 'cancelOrder', orderItemId, $(this));
            });
        },
        //追号记录模板
        fillTable_trace: function(data, content, skey) {
            for (var i = 0, l = data.length; i < l; i++) {
                var type = "否";
                if (data[i].winStop == "1") {
                    type = "是";
                }
                var code = data[i];

                content = content + '<tr class=" table-tr-item "> <td><a href="' + ctx + '/mkg/orders/trace/view/' + data[i].traceId + '">' +
                    data[i].traceId + ' </a></td><td>' + data[i].createTime + ' </td><td>' +
                    '<span class="c-important">' + data[i].lottery + '</span><br>' +
                    '<span>' + data[i].method + '</span></td>' +
                    '<td><span class="cell-label">期号</span>' + data[i].start + '<br><span class="cell-label">追号</span>' + data[i].finishCount + ' / ' + data[i].issueCount + '  期<td>' +

                    '<span class="c-important">总金额</span><dfn>￥</dfn><span class="c-important" data-money-format="" style="font-size: 13px;">' + data[i].totalMoney + '</span><br>' +
                    '<span>已完成</span><dfn>￥</dfn><span data-money-format="" style="font-size: 13px;">' + data[i].finishMoney + '</span><br>' +
                    '<span class="c-gray">已取消</span><dfn>￥</dfn><span class="c-gray" data-money-format="" style="font-size: 13px;">' + data[i].cancelMoney + '</span></td>' +

                    '<td><font ">' + type + '</font></td><td>' + data[i].status + '</td><td></td><td></td><td></td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //账变记录模板
        fillTable_account: function(data, content, skey) {
            for (var i = 0, l = data.length; i < l; i++) {
                var type = "+";
                var color = "red";
                if (data[i].optType == 2) {
                    type = "-";
                    color = "green";
                }
                content = content + '<tr class=" table-tr-item "> <td>' + data[i].spsn + ' </td><td>' + data[i].cn + ' </td><td>' + data[i].createTime + ' </td><td>' + data[i].changeTypeStr + '</td><td>' + data[i].changeTypeDetailStr + ' </td><td><font color="' + color + '">' + type + data[i].amount + '</font></td><td>' + data[i].point + '</td><td><font ">' + data[i].note + '</font></td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //充值记录模板
        fillTable_recharge: function(data, content, skey) {
            for (var i = 0, l = data.length; i < l; i++) {
                if(data[i].status == 6){
                    data[i].fee = 0;
                }
                content = content + '<tr class=" table-tr-item "><td>' + data[i].userName + '</td><td>' + data[i].orderDate + ' </td><td>' + data[i].spsn + ' </td><td>' + typeDoc.get(data[i].way) + ' </td><td>' + data[i].cash + '元</td><td>' + data[i].fee + '元</td><td><font>' + doc.get(data[i].status) + '</font></td><td></td><td></td><td></td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //提现记录模板
        fillTable_drawcash: function(data, content, skey) {
            for (var i = 0, l = data.length; i < l; i++) {
                content = content + '<tr class=" table-tr-item "> <td>' + data[i].createTime + ' </td><td>' + data[i].spsn + ' </td><td>' + data[i].cash + '元</td><td><font ">' + doc.get(data[i].status) + '</font></td><td></td><td></td><td></td><td></td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //个人报表
        fillTable_single: function(data, content, skey) {
            for (var i = 0, l = data.length; i < l; i++) {
                var item = data[i];
                content = content + '<tr class=" table-tr-item " style="color:red"> <td>' + item.userName + '</td><td>' + item.date +
                    ' </td><td>' + (item.depositAmount).toFixed(4) + ' </td><td>' + (item.withdrawAmount).toFixed(4) +
                    ' </td><td>' + (item.confirmAmount).toFixed(4) + '</td>' +
                    '<td>' + (item.awardAmount).toFixed(4) + '</td><td><font ">' + (item.pointAmount + item.rebateAmount).toFixed(4) + '</font></td><td>' + (item.activityAmount).toFixed(4) + '</td><td>' + (0 - item.profitAmount).toFixed(4) + ' </td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //团队报表
        fillTable_team: function(data, content, skey, resdata) {
            var start = $('#J-date-start').val();
            var end = $('#J-date-end').val();
            for (var i = 0, l = data.length; i < l; i++) {
                var item = data[i];
                if (item.head < 2) {
                    content = content +
                        '<tr class=" table-tr-item ">' +
                        '<td>' + item.userName + '</td>' +
                        '<td>' + (item.userType == 0 ? "会员" : "代理") + '</td>' +
                        '<td>' + item.balance + '</td>' +
                        '<td>' + (item.depositAmount).toFixed(4) + '</td>' +
                        '<td>' + (typeof item.feeAmount !== 'undefined' ? (item.feeAmount).toFixed(4) : '0.00') + '</td>' +
                        '<td>' + (item.withdrawAmount).toFixed(4) + '</td>' +
                        '<td>' + (item.confirmAmount).toFixed(4) + '</td>' +
                        '<td>' + (item.awardAmount).toFixed(4) + '</td>' +
                        '<td>' + (item.pointAmount + item.rebateAmount).toFixed(4) + '</td>' +
                        '<td>' + (item.activityAmount).toFixed(4) + '</td>' +
                        '<td>' + (0 - item.profitAmount).toFixed(4) + '</td>' +
                        '<td>' +
                        '<a class="" href="' + ctx + '/mkg/orders.html?querySub=' + item.userName + '&start=' + start + '&end=' + end + '" target="_blank">游戏记录</a>' +
                        (item.userType > 0 && item.head != 1 ? '<a id="query-team" href="javascript:;">团队报表</a>' : '') +
                        (item.userType > 0 && item.head != 1 ? '<a id="query-money" href="' + ctx + '/mkg/statement.html?userName=' + item.userName + '&start=' + start + '&end=' + end + '#account_view" target="_blank">资金明细</a>' : '') +
                        (item.userType > 0 && item.head != 1 ? '<a id="query-money" href="' + ctx + '/mkg/statement.html?userName=' + item.userName + '&start=' + start + '&end=' + end + '#recharge_view" target="_blank">充值记录</a>' : '') +
                        '</td>' +
                        '</tr>';
                }
            }
            $('#' + skey + ' #contentTable').html(content);
            if (resdata.currPage == 1) {
                var item = data[data.length - 1];
                if (item.head == 2) {
                    var content = '';
                    content = content +
                        '<tr class=" table-tr-item ">' +
                        '<td colspan="3"><strong>汇总</strong></td>' +
                        '<td>' + item.depositAmount.toFixed(4) + '</td>' +
                        '<td>' + (typeof item.feeAmount !== 'undefined' ? (item.feeAmount).toFixed(4) : '0.00') + '</td>' +
                        '<td>' + item.withdrawAmount.toFixed(4) + '</td>' +
                        '<td>' + item.confirmAmount.toFixed(4) + '</td>' +
                        '<td>' + item.awardAmount.toFixed(4) + '</td>' +
                        '<td>' + (item.pointAmount + item.rebateAmount).toFixed(4) + '</td>' +
                        '<td>' + item.activityAmount.toFixed(4) + '</td>' +
                        '<td>' + (0 - item.profitAmount).toFixed(4) + '</td>' +
                        '<td></td>' +
                        '</tr>';
                    $("#contentTable-sum").html(content);
                }
            }

            $('#contentTable tr td a#query-team').bind('click', function() {
                var queryUserName = $(this).parent().siblings().eq(0).html();
                $('#J-name').val(queryUserName);
                queryData = page3a.getQueryData(skey);
                page3a.pramsFilter(queryData);
                page3a.noData(skey);
                page3a.triggerEvt(skey, resdata.totalItem, queryData);
            });
        },
        //用户列表
        fillTable_user: function(data, content, skey, resdata) {
            var transferTurn = $('#transferTurn').val();
            for (var i = 0, l = data.length; i < l; i++) {
                var canSet = data[i].set;
                content = content + '<tr>' +
                    '<td>' + data[i].userName + '</td>' +
                    '<td>' + groupType.get(data[i].userType) + '</td>' +
                    '<td>' + data[i].totalCount + '</td>' +
                    '<td><dfn>￥</dfn><span class="c-important" data-money-format="">' + data[i].balance + '</span></td>' +
                    '<td><dfn>￥</dfn><span class="c-important" data-money-format="">' + data[i].teamBalance + '</span></td>' +
                    '<td>' + data[i].point + '</td>' +
                    '<td>' + data[i].registerTime + '</td>' +
                    '<td>' +
                    (canSet == 0 ? '<a class="ui-action-adjust" href="/hz/mkg/agent/user/' + data[i].userId + '">设置</a>' +
                        (parseInt(transferTurn) > 0 ? '<a class="ui-action-check" href="/hz/mkg/pay/agentTransfer.html#lowerName=' + data[i].userName + '&lowerId=' + data[i].userId + '">转账</a>' : '') :
                        ''
                    ) +
                    (data[i].userType > 0 && data[i].totalCount > 0 ? '<a id="query-team" class="ui-action-adjust" href="javascript:;">查询下级</a>' : '') +
                    '' +
                    '</td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
            $('#contentTable tr td a#query-team').bind('click', function() {
                var queryUserName = $(this).parent().siblings().eq(0).html();
                $('#J-input-subname').val(queryUserName);
                queryData = page3a.getQueryData(skey);
                page3a.pramsFilter(queryData);
                page3a.noData(skey);
                page3a.triggerEvt(skey, resdata.totalItem, queryData);
            });
        },
        //注册链接列表
        fillTable_links: function(data, content, skey, resdata) {
            $('#J-total-num').html(resdata.totalItem);
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var expireTime = item.expire == -1 ? "永久有效" : item.expire + "天"
                var status = item.status == 0 ? "正常" : "失效";
                var userType = item.userType == 0 ? "会员" : "代理";
                var point = item.point * 1000 / 10;
                var content = content + "<tr class=' table-tr-item '>" +
                    "<td class='text-center'>推广</td>" +
                    "<td><input type='text' id='copy_input_" + i + "' onClick='this.select();' class='input w-3' value='" + "http://" + window.location.host + item.link + "' /><span id='copy_btn' class='copy_btn' data-clipboard-target='#copy_input_" + i + "'>复制</span></td>" +
                    "<td>" + userType + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" + point + "%</td>" +
                    "<td>" + expireTime + "</td>" +
                    "<td>" + item.createTime + "</td>" +
                    (item.status == 0 ? "<td><a data-linkid=" + item.linkId + " class='ui-action-delete confirmDelete' href='javascript:void(0);'>删除</a></td>" : '<td>无</td>') +
                    "</tr>"
            }
            $('#' + skey + ' #contentTable').html(content);
            $('.confirmDelete').click(function() {
                var linkId = $(this).data('linkid');
                var dataQuery = {
                    id: linkId
                }
                commonUtil.confirmTip('删除确认', '确定关闭该开户链接么？', 'deleteUserLinks', dataQuery);
            });
        },
        //消息列表
        fillTable_msgs: function(data, content, skey) {
            var flag = false;

            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var isRead = item.readStateStr === '已读' ? '<li class="read"> <div class="tit"></div>' : '<li class="bold"><div class="tit">';
                var content = '' + isRead + '' +
                    '<span class="msgType">[' + item.msgTypeStr + ']：</span>' +
                    '<a data-id="' + item.id + '" data-msgid="' + item.userMessageId + '" href="javascript:;">' + item.title + '</a>' +
                    '<label>' + item.createDateString + '</label></div>' +
                    '<div class="msg-con" data-msgid="' + item.userMessageId + '"><p>' + item.content + '</p>' +
                    '</div>';
                $('.msg-list .in').append(content);
            }

            function boldOrNo(evt) {
                evt.siblings().find('.msg-con').slideUp(300);
                evt.find('.msg-con').slideToggle(500, 'easeOutExpo', function() {
                    var isRead = evt.attr('class');
                    var msgid = evt.find('a').data('msgid');
                    if (isRead !== 'read') {
                        Api.getIsRead({
                            'userMessageId': msgid
                        }, function(res) {
                            if (res.code === 0) {
                                evt.removeClass('bold').addClass('read');
                                commonUtil.getMsgCount();
                            }
                        });
                    }
                });
            }

            $('.msg-list li').each(function() {
                var me = $(this);
                me.on('click', function() {
                    boldOrNo(me);
                });
            });

            function sendReadMsg(evt) {
                var thisLi = $(evt);
                var msgid = $(evt).find('a').data('msgid');
                Api.getIsRead({
                    'userMessageId': msgid
                }, function(res) {
                    if (res.code === 0) {
                        thisLi.removeClass('bold').addClass('read');
                        boldOrNo(this);
                        commonUtil.getMsgCount();
                        thisLi.unbind('click').bind('click', function() {
                            boldOrNo(this);
                        });
                    }
                });
            }
        },
        //第三方游戏记录
        fillTable_thirdgame: function(data, content, skey) {
            for (var i = 0, l = data.length; i < l; i++) {
                if (data[i].profit == null) {
                    data[i].profit = '';
                }
                content = content + '<tr class=" table-tr-item "> <td>' + data[i].cn + ' </td><td>' + data[i].orderTime + ' </td><td>' + gameFrom.get(data[i].platFormCn) + ' </td><td>' + data[i].orderAmount + '元</td><td><font ">' + data[i].profit + '</font></td><td>' + data[i].jieSuanStatus + '</td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //第三方游戏报表
        fillTable_thirdforms: function(data, content, skey) {
            var gameFromSelected = $('#J-select-queryType').val();
            for (var i = 0, l = data.length; i < l; i++) {
                content = content + '<tr class=" table-tr-item "> <td>' + data[i].cn + ' </td><td>' + data[i].date + ' </td><td>' + gameFrom.get(gameFromSelected) + ' </td><td>' + data[i].transferInamount + '元</td><td>' + data[i].transferOutamount + '元</td><td>' + data[i].confirmAmount + '元</td><td>' + data[i].profitAmount + '元</td></tr>';
            }
            $('#' + skey + ' #contentTable').html(content);
        },
        //不同查询接口下的不同过滤条件
        filterControl: function(e) {
            var gameTraceFilter = $('span.filter-exc');
            switch (e) {
                case 'trace':
                    gameTraceFilter.hide();
                    break;
                case 'game':
                    gameTraceFilter.show();
                    break;
            }
        },
        //状态码字典查询
        dictionary: function() {
            var items = {};
            this.set = function(key, value) {
                if (typeof key == 'object') {
                    for (var i = 0; i < key.length; i++) {
                        items[key[i]] = value;
                    }
                } else {
                    items[key] = value;
                }
            };
            this.remove = function(key) {
                if (this.has(key)) {
                    delete items[key];
                    return true;
                }
                return false;
            };
            this.has = function(key) {
                return items.hasOwnProperty(key);
            };
            this.get = function(key) {
                return this.has(key) ? items[key] : undefined;
            };
            this.keys = function() {
                return Object.keys(items);
            };
            this.values = function() {
                var values = [];
                for (var k in items) {
                    if (this.has(k)) {
                        values.push(items[k]);
                    }
                }
                return values;
            };
        }
    };
    //字典转义，状态码转换成对应文本
    var doc = new page3a.dictionary();
    var typeDoc = new page3a.dictionary();
    var groupType = new page3a.dictionary();
    var gameFrom = new page3a.dictionary();
    gameFrom.set(['SB', 'sb'], '沙巴');
    gameFrom.set(['AG', 'ag'], 'AG');
    gameFrom.set(['PT', 'pt'], 'PT');
    groupType.set('0', '会员');
    groupType.set('1', '代理');
    typeDoc.set('0', '第三方支付');
    typeDoc.set('1', '网银支付');
    typeDoc.set('3', '微信扫码');
    typeDoc.set('4', '支付宝扫码');
    typeDoc.set(['', '2', '5', '6', '7', '8', '9'], '其他方式支付');
    doc.set('0', '成功');
    doc.set(['6', '7'], '失败');
    doc.set(['1', '2', '3', '4', '5', '8', '9'], '待处理');
    //默认加载事件
    var currTitle = $('.tab-title li a');
    currTitle.unbind('click').bind('click', function(e) {
        var currLi = $(this).parent();
        if (!currLi.hasClass('current')) {
            var category = $(this).data('skey');
            var url = $(this).attr('href');
            if (category == 'salary' || category == 'dividend') {
                $('.choose-model').hide();
                $('#J-date-start,#J-date-end').removeClass('on');
            }
            if (category == 'dividend') {
                $('.area-search').hide();
                if ($('#startInner').val() == '') {
                    $('#startInner,#endInner').val((new Date()).DateAdd('d', -1).Format("yyyy-MM-dd"));
                }
            } else {
                if ($('#startInner').val() == '') {
                    $('#startInner,#endInner').val($('#J-date-start').val());
                }
                $('.area-search').show();
            }
            //不同查询模块下select控件的隐藏于显示
            //if (category!='salary' && category!='dividend') {
            page3a.filterControl(category);
            $('#J-select-queryType-' + category + '').prev().show().siblings('.choose-model').hide();
            $("#" + category).show().siblings('.content').hide();
            $('.tab-title li').removeClass('current');
            $("[data-tab-" + category + "]").addClass('current');
            //}
            if (typeof category !== 'undefined') {
                //切换category之后重新初始化
                page3a.dataInit();
            }
        }
    });
    //页面加载完毕初始化
    page3a.dataInit();
})(jQuery);