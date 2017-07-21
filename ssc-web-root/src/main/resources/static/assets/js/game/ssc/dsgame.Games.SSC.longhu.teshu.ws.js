
(function(host, GameMethod, undefined){
	var defConfig = {
		name:'longhu.teshu.ws'
	},
	Games = host.Games,
	SSC = Games.SSC.getInstance();


	//定义方法
	var pros = {
        init: function(cfg) {
            var me = this;

        },
        //时时彩复式结构为5行10列
        //复位选球数据
        rebuildData: function() {
            var me = this;
            me.balls = [
                [-1, -1, -1]
            ];
        },
        buildUI: function() {
            var me = this;
            me.container.html(html_all.join(''));
        },
        formatViewBalls: function(original) {
            var me = this,
                result = [],
                len = original.length,
                i = 0,
                tempArr = [],
                names = ['龙', '虎', '和'];
            for (; i < len; i++) {
                tempArr = [];
                $.each(original[i], function(j) {
                    tempArr[j] = names[Number(original[i][j])];
                });
                result = result.concat(tempArr.join(''));
            }
            return result.join('|');
        },
        miniTrend_createHeadHtml: function() {
            var me = this,
                html = [];
            html.push('<table width="100%" class="table bet-table-trend" id="J-minitrend-trendtable-' + me.getId() + '">');
            html.push('<thead><tr>');
            html.push('<th><span class="number">奖期</span></th>');
            html.push('<th><span class="balls">开奖</th>');
            html.push('<th><span>龙虎和</span></th>');
            //html.push('<th><span>单双</span></th>');
            html.push('</tr></thead>');
            html.push('<tbody>');
            return html.join('');
        },
        miniTrend_createRowHtml: function() {
            var me = this,
                data = me.miniTrend_getBallsData(),
                // data = Games.getCurrentGame().getGameConfig().getInstance().getLotteryNumbers(),
                dataLen = data.length,
                trcls = '',
                currCls = 'curr',
                item,
                html = [];
            $.each(data, function(i) {
                item = this;
                trcls = '';
                trcls = i == 0 ? 'first' : trcls;
                trcls = i == dataLen - 1 ? 'last' : trcls;
                var number = item['wn_number'];
                var currtag = '<i class="curr">';
                html.push('<tr class="' + trcls + '">');
                html.push('<td><span class="number">' + item['issue'].substr(2) + ' 期</span></td>');
                html.push('<td><span class="balls">' + number);
                html.push('</span></td>');
                if (parseInt(number[0]) > parseInt(number[3])) {
                    xtText = '龙';
                } else if (parseInt(number[0]) < parseInt(number[3])) {
                    xtText = '虎';
                } else if(parseInt(number[0]) == parseInt(number[3])) {
                    xtText = '和';
                } else {
					xtText = '';
				}
                html.push('<td>' + xtText + '</td>');
                html.push('</tr>');
            });
            return html.join('');
        }

    };




	//html模板
	var html_head = [];
		//头部
		html_head.push('<div class="number-select-title balls-type-title clearfix"><div class="number-select-link"><a href="" class="pick-rule">选号规则</a><a href="" class="win-info">中奖说明</a></div><div class="function-select-title"></div></div>');
		html_head.push('<div class="number-select-content">');
		html_head.push('<ul class="ball-section">');
		//每行
	var html_row = [];
		html_row.push('<li>');
		html_row.push('<div class="ball-title"><strong><#=title#>万十</strong><span></span></div>');
		html_row.push('<ul class="ball-content">');
			$.each(['龙', '虎', '和'], function(i){
				html_row.push('<li><a class="ball-number" data-param="action=ball&value='+ i +'&row=<#=row#>" href="javascript:void(0);">'+ this +'</a></li>');
			});
		html_row.push('</ul>');
		html_row.push('</li>');

	var html_bottom = [];
		html_bottom.push('</ul>');
		html_bottom.push('</div>');
		//拼接所有
	var html_all = [],rowStr = html_row.join('');
		html_all.push(html_head.join(''));
		$.each([''], function(i){
			html_all.push(rowStr.replace(/<#=title#>/g, this).replace(/<#=row#>/g, i));
		});
		html_all.push(html_bottom.join(''));



	//继承GameMethod
	var Main = host.Class(pros, GameMethod);
		Main.defConfig = defConfig;
	//将实例挂在游戏管理器实例上
	SSC.setLoadedHas(defConfig.name, new Main());

})(dsgame, dsgame.GameMethod);