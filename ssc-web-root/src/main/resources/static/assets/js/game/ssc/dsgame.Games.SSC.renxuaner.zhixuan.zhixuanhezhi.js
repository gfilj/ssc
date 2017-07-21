

//五星直选复式玩法实现类
(function(host, GameMethod, undefined){
	var defConfig = {
		//名称
		name:'renxuaner.zhixuan.zhixuanhezhi',
		//玩法提示
		tips:'直选复式玩法提示',
		//选号实例
		exampleTip: '五星直选复式范例',
		// 位数默认选择
		digitConf: [0,0,0,1,1],
		// 需要的位数
		digitNeed: 2
	};
		//游戏类
	var SSC = host.Games.SSC.getInstance();

	//定义方法
	var pros = {
		init:function(cfg){
			var me = this;

		},
		//时时彩复式结构为5行10列
		//复位选球数据
		rebuildData:function(){
			var me = this;
			me.balls = [
				[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
			];
		},
		buildUI:function(){
			var me = this;
			me.container.html(html_all.join(''));
		},
		formatViewBalls:function(original){
			var me = this,
				result = [],
				len = original.length,
				i = 0;
			for (; i < len; i++) {
				result = result.concat(original[i].join('|'));
			}
			return result.join('|');
		},
		makePostParameter: function(original){
			var me = this,
				result = [],
				len = original.length,
				i = 0;
			for (; i < len; i++) {
				result = result.concat(original[i].join('|'));
			}
			return result.join('');
		},
		//获取总注数/获取组合结果
		//isGetNum=true 只获取数量，返回为数字
		//isGetNum=false 获取组合结果，返回结果为单注数组
		getLottery:function(isGetNum){
			var me = this,
				data = me.getBallData(),
				i = 0,
				len = data.length,
				row,
				_tempRow = [],
				j = 0,
				len2 = 0,
				result = [],
				//普通组合结果
				combinationResult = [],
				//最终结果
				finalResult = [],
				tempArr = [],
				//总注数
				total = 1,
				ballNumNeed = 1; // 需要至少选择两个球;
			me.isBallsComplete = true;
			$.each(data[0], function(i,n){
				if( n > 0 ){
					//需要计算组合则推入结果
					if(!isGetNum){
						tempArr = me.heizhi_combs_calc(i);
						if( tempArr.length ){
							result = result.concat(tempArr);
						}
					}
				}
			});
			return result;
		},
		heizhi_combs_calc: function(num){
			var ret = [], max = num, min = 0;
			if( num > 9 ){
				max = 9;
				min = num - max;
			}
			for(var i = max; i >= min; i--){
				ret.push([num-i,i]);
			}
			return ret;
		}

	};

	//html模板
	var html_head = [];
		//头部
		html_head.push('<div class="number-select-title balls-type-title clearfix"><div class="number-select-link"><a href="#" class="pick-rule">选号规则</a><a href="#" class="win-info">中奖说明</a></div><div class="function-select-title"></div></div>');
		html_head.push('<div class="number-select-content">');
		html_head.push('<ul class="ball-section">');
		//每行
	var html_row = [];
		html_row.push('<li>');
		// html_row.push('<div class="ball-title"><strong><#=title#></strong><span></span></div>');
		html_row.push('<ul class="ball-content">');
			$.each([0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18], function(i){
				html_row.push('<li><a class="ball-number" data-param="action=ball&value='+ this +'&row=<#=row#>" href="javascript:void(0);">'+ this +'</a></li>');
			});
		html_row.push('</ul>');
		// html_row.push('<div class="ball-control">');
		// html_row.push('<a href="javascript:void(0);" class="circle"></a>');
		// html_row.push('<a href="javascript:void(0);" data-param="action=batchSetBall&amp;row=<#=row#>&amp;bound=all" class="all">全</a>');
		// html_row.push('<a href="javascript:void(0);" data-param="action=batchSetBall&amp;row=<#=row#>&amp;bound=big" class="big">大</a>');
		// html_row.push('<a href="javascript:void(0);" data-param="action=batchSetBall&amp;row=<#=row#>&amp;bound=small" class="small">小</a>');
		// html_row.push('<a href="javascript:void(0);" data-param="action=batchSetBall&amp;row=<#=row#>&amp;bound=odd" class="odd">奇</a>');
		// html_row.push('<a href="javascript:void(0);" data-param="action=batchSetBall&amp;row=<#=row#>&amp;bound=even" class="even">偶</a>');
		// html_row.push('<a href="javascript:void(0);" data-param="action=batchSetBall&amp;row=<#=row#>&amp;bound=none" class="none">清</a>');
		// html_row.push('</div>');
		html_row.push('</li>');

	var html_bottom = [];
		html_bottom.push('</ul>');
		html_bottom.push('</div>');

		html_bottom.push('<div class="panel-digit-checkbox">');
			html_bottom.push('<label><input value="0" digit-status' + (defConfig.digitConf[0] ? ' checked' : '') + ' type="checkbox">万位</label>');
			html_bottom.push('<label><input value="1" digit-status' + (defConfig.digitConf[1] ? ' checked' : '') + ' type="checkbox">千位</label>');
			html_bottom.push('<label><input value="2" digit-status' + (defConfig.digitConf[2] ? ' checked' : '') + ' type="checkbox">百位</label>');
			html_bottom.push('<label><input value="3" digit-status' + (defConfig.digitConf[3] ? ' checked' : '') + ' type="checkbox">十位</label>');
			html_bottom.push('<label><input value="4" digit-status' + (defConfig.digitConf[4] ? ' checked' : '') + ' type="checkbox">个位</label>');
			html_bottom.push('<span>请至少选择<b class="c-important">' + defConfig.digitNeed + '</b>位位数，');
				html_bottom.push('您当前选择了<b class="c-important J-digit-num-choosed">' + defConfig.digitNeed + '</b>个位置，');
				html_bottom.push('系统将自动生成<b class="c-important J-digit-project-num">1</b>个方案</span>');
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
