
(function(host, Danshi, undefined){
	var defConfig = {
		name:'renxuaner.zuxuan.zuxuandanshi',
		//玩法提示
		tips: '四星直选单式玩法提示',
		//选号实例
		exampleTip: '四星直选单式弹出层1111提示',
		// 位数默认选择
		digitConf: [0,0,0,1,1],
		// 需要的位数
		digitNeed: 2
	},
	Games = host.Games,
	SSC = Games.SSC.getInstance();


	//定义方法
	var pros = {
		init:function(cfg){
			var me = this;
			//建立编辑器DOM
			//防止绑定事件失败加入定时器
			setTimeout(function(){
				me.initFrame();
			},25);
		},
		rebuildData:function(){
			var me = this;
			me.balls = [
				[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],
				[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
			];
		},
		// //检测单注号码是否通过
		// checkSingleNum: function(lotteryNum){
		// 	var me = this;
		// 	console.log(lotteryNum)
		// 	console.log(me.balls)
		// 	if(lotteryNum.length != me.balls.length){
		// 		return false;
		// 	}
		// 	if(lotteryNum[0] != lotteryNum[1] && lotteryNum[0] != lotteryNum[2] && lotteryNum[1] != lotteryNum[2]){
		// 		return false;
		// 	}
		// 	if(lotteryNum[0] == lotteryNum[1] && lotteryNum[0] == lotteryNum[2]){
		// 		return false;
		// 	}
		// 	return true;
		// },
		//检测选球是否完整，是否能形成有效的投注
		//并设置 isBallsComplete
		checkBallIsComplete:function(data){
			var me = this,
				len,
				i = 0,
				balls,
				ballsStr,
				has = {},
				result = [];

				me.aData = [];
				me.vData = [];
				me.sameData = [];
				me.errorData = [];
				me.tData = [];
			//按规则进行拆分结果
			result = me.iterator(data);
			len = result.length;

			for(i = 0; i < len; i++){

				balls = result[i].split('');
				ballsStr = balls.sort(function(a,b){
					return Number(a) > Number(b);
				}).join('');
				//检测基本长度
				if(me.checkSingleNum(ballsStr)){
					if(has[ballsStr]){
						//重复
						me.sameData.push(balls);
					}else{
						me.tData.push(balls);
						has[ballsStr] = true;
					}
				}else{
					me.errorData.push(balls);
				}
			}
			//校验
			if(me.tData.length > 0){
				me.isBallsComplete = true;
				return me.tData;
			}else{
				me.isBallsComplete = false;
				return [];
			}
		},
		//生成一个当前玩法的随机投注号码
		//该处实现复式，子类中实现其他个性化玩法
		//返回值： 按照当前玩法生成一注标准的随机投注单(order)
		randomNum:function(){
			var me = this,
				i = 0,
				current = [],
				currentNum,
				ranNum,
				order = null,
				dataNum = me.getBallData(),
				name_en = Games.getCurrentGame().getCurrentGameMethod().getGameMethodName(),
				name = me.defConfig.name,
				lotterys = [],
				original = [];

			//增加机选标记
			me.addRanNumTag();

			current  = me.checkRandomBets();
			original = [[current.join(',')],[],[],[]];
			lotterys = me.combination(current);
			//生成投注格式
			order = {
				'type': name_en,
				'original':original,
				'lotterys':lotterys,
				'moneyUnit': Games.getCurrentGameStatistics().getMoneyUnit(),
				'multiple': Games.getCurrentGameStatistics().getMultip(),
				'onePrice': Games.getCurrentGame().getGameConfig().getInstance().getOnePrice(name_en),
				'num': lotterys.length
			};
			order['amountText'] = Games.getCurrentGameStatistics().formatMoney(order['num'] * order['moneyUnit'] * order['multiple'] * order['onePrice']);
			return order;
		},
		getHTML:function(){
			//html模板
			var iframeSrc = Games.getCurrentGame().getGameConfig().getInstance().getUploadPath();
			var token = Games.getCurrentGame().getGameConfig().getInstance().getToken();
			var digitStatus = this.defConfig.digitConf;
			var html_all = [];
				html_all.push('<div class="number-select-title balls-type-title clearfix"><div class="number-select-link"><a href="#" class="pick-rule">选号规则</a><a href="#" class="win-info">中奖说明</a></div></div>');
				html_all.push('<div class="balls-import clearfix">');
					html_all.push('<form id="form1" name="form1" enctype="multipart/form-data" method="post" action="'+ iframeSrc +'" target="check_file_frame" style="position:relative;padding-bottom:10px;">');
					html_all.push('<input name="betNumber" type="file" id="file" size="40" hidefocus="true" value="导入" style="outline:none;-ms-filter:progid:DXImageTransform.Microsoft.Alpha(Opacity=0);filter:alpha(opacity=0);opacity: 0;position:absolute;top:0px; left:0px; width:115px; height:30px;z-index:1;background:#000;cursor: pointer;" />');
					html_all.push('<input name="_token" type="hidden" value="'+ token +'" />');
					html_all.push('<input type="button" class="btn balls-import-input" style="cursor: pointer;" value="导入注单" onclick=document.getElementById("form1").file.click()>&nbsp;&nbsp;&nbsp;&nbsp;<a style="display:none;" class="balls-example-danshi-tip" href="#">查看标准格式样本</a>');
					html_all.push('<input type="reset" style="outline:none;-ms-filter:progid:DXImageTransform.Microsoft.Alpha(Opacity=0);filter:alpha(opacity=0);opacity: 0;width:0px; height:0px;z-index:1;background:#000" />');
					html_all.push('<iframe src="'+ iframeSrc +'" name="check_file_frame" style="display:none;"></iframe>');
					html_all.push('</form>');
					html_all.push('<div class="panel-select"><iframe style="width:100%;height:100%;border:0 none;background-color:#F9F9F9;" class="content-text-balls"></iframe></div>');
					html_all.push('<div class="panel-digit-checkbox">');
						html_all.push('<label><input value="0" digit-status' + (digitStatus[0] ? ' checked' : '') + ' type="checkbox">万位</label>');
						html_all.push('<label><input value="1" digit-status' + (digitStatus[1] ? ' checked' : '') + ' type="checkbox">千位</label>');
						html_all.push('<label><input value="2" digit-status' + (digitStatus[2] ? ' checked' : '') + ' type="checkbox">百位</label>');
						html_all.push('<label><input value="3" digit-status' + (digitStatus[3] ? ' checked' : '') + ' type="checkbox">十位</label>');
						html_all.push('<label><input value="4" digit-status' + (digitStatus[4] ? ' checked' : '') + ' type="checkbox">个位</label>');
						html_all.push('<span>请至少选择<b class="c-important">' + this.defConfig.digitNeed + '</b>位位数，');
							html_all.push('您当前选择了<b class="c-important J-digit-num-choosed">' + this.defConfig.digitNeed + '</b>个位置，');
							html_all.push('系统将自动生成<b class="c-important J-digit-project-num">1</b>个方案</span>');
					html_all.push('</div>');
					html_all.push('<div class="panel-btn">');
						html_all.push('<a class="btn filter-order" href="javascript:void(0);">清除错误或重复项</a>');
						// html_all.push('<a class="btn remove-error" href="javascript:void(0);">删除错误项</a>');
						// html_all.push('<a class="btn remove-same" href="javascript:void(0);">删除重复项</a>');
						html_all.push('<a class="btn remove-all" href="javascript:void(0);">清空文本框</a>');

					html_all.push('</div>');
				html_all.push('</div>');
			return html_all.join('');
		}
	};

	//继承Danshi
	var Main = host.Class(pros, Danshi);
		Main.defConfig = defConfig;
	//将实例挂在游戏管理器上
	SSC.setLoadedHas(defConfig.name, new Main());



})(dsgame, dsgame.Games.SSC.Danshi);
