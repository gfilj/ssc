




//模拟弹出窗口
//内部已经绑定了在IE6下模拟fixed效果
;(function(host, name, Event, $, undefined){
	var util = host.util,
	defConfig = {
		//点击按钮
		confirmButtonText : 'confirm',
		//取消按钮
		cancelButtonText : 'cancel',
		//关闭按钮
		closeButtonText : 'closeTip',
		//实例化时追加的最外层样式名
		cls:'',
		//窗体内主内容html字符串
		contentHtml:'',
		//在主体内容生成之后，执行afterSetConent函数时的延迟时间，以确保内容生成完成或者其内部的资源加载完成
		//也可以再每次调用setContent的时候，设置延迟参数值
		afterSetContentDelay:0,
		effectShow:function(){
			var me = this,top;
			util.toViewCenter(me.dom);

			top = parseInt(me.dom.css('top'));
			me.dom.css({top:top + 100, opacity:1});
			me.dom.show();
			me.dom.animate({top:top - 100, opacity:1}, 300);
		},
		effectHide:function(){
			this.dom.hide();
		},
		zIndex:8100,
		//是否使用fixed模式
		isFixed:true
	},
	doc = $(document),
	instance;

	var pros = {
		init:function(cfg){
			var me = this,position = cfg.isFixed ? 'fixed' : 'absolute';
			position = util.isIE6 ? 'absolute' : position;
			me.dom = $('<div class="pop '+ cfg.cls +'" style="z-index:'+ cfg.zIndex +';position:'+ position +';display:none;"><div class="pop-hd"><a href="#" class="pop-close"></a><h3 class="pop-title"><span class="title">提示</span></h3></div><div class="pop-bd"></div><div class="pop-btn"><a style="display:none" href="javascript:void(0);" class="btn confirm"><span>确 认</span><b class="btn-inner"></b></a><a style="display:none" href="javascript:void(0);" class="btn cancel"><span>取 消</span><b class="btn-inner"></b></a><a href="javascript:void(0);" style="display:none" class="btn closeTip"><span>关 闭</span><b class="btn-inner"></b></a></div></div>').appendTo('body');
			me.effectShow = cfg.effectShow;
			me.effectHide = cfg.effectHide;
			me.dom.on('click', '.pop-close', function(e){
				e.preventDefault();
				me.doNormalClose();
				me.hide();
			})
			me.dom.find('.' + me.defConfig.confirmButtonText).bind('click', function(){
				me.doConfirm();
			});
			me.dom.find('.' + me.defConfig.cancelButtonText).bind('click', function(){
				me.doCancel();
			});
			me.dom.find('.' + me.defConfig.closeButtonText).bind('click', function(){
				me.doClose();
			});
		},
		//默认关闭按钮事件
		doNormalClose: function(){
		},
		//执行确定事件
		doConfirm: function(){
		},
		//执行取消事件
		doCancel: function(){
		},
		//执行关闭事件
		doClose: function(){

		},
		//获取确认按钮对象
		getConfirmButtonDom: function(){
			var me= this;
			return me.getContainerDom().find('.' + me.defConfig.confirmButtonText);
		},
		//获取取消按钮对象
		getCancelButtonDom: function(){
			var me= this;
			return me.getContainerDom().find('.' + me.defConfig.cancelButtonText);
		},
		//获取关闭按钮对象
		getCloseButtonDom: function(){
			var me= this;
			return me.getContainerDom().find('.' + me.defConfig.closeButtonText);
		},
		//修改确认按钮名称
		setConfirmName: function(text){
			var me= this;
			me.getConfirmButtonDom().find('span').text(text);
		},
		//修改取消按钮名称
		setCancelName: function(text){
			var me= this;
			me.getCancelButtonDom().find('span').text(text);
		},
		//修改关闭按钮名称
		setCloseName: function(text){
			var me= this;
			me.getCloseButtonDom().find('span').text(text);
		},
		//显示确认按钮对象
		showConfirmButton: function(){
			var me= this;
			me.getConfirmButtonDom().show();
		},
		//显示取消按钮对象
		showCancelButton: function(){
			var me= this;
			me.getCancelButtonDom().show();
		},
		//显示关闭按钮对象
		showCloseButton: function(){
			var me= this;
			me.getCloseButtonDom().show();
		},
		//隐藏确认按钮对象
		hideConfirmButton: function(){
			var me= this;
			me.getConfirmButtonDom().hide();
		},
		//隐藏取消按钮对象
		hideCancelButton: function(){
			var me= this;
			me.getCancelButtonDom().hide();
		},
		//隐藏关闭按钮对象
		hideCloseButton: function(){
			var me= this;
			me.getCloseButtonDom().hide();
		},
		setTitle:function(html){
			this.getTitleDom().html(html);
		},
		setContent:function(html, delay){
			var me = this,cfg = me.defConfig,delayTime = 0;
			me.getContentDom().html(html);

			delayTime = !!cfg.afterSetContentDelay ? cfg.afterSetContentDelay : delayTime;
			delayTime = !!delay ? delay : delayTime;
			if(delayTime > 0){
				setTimeout(function(){
					me.fireEvent('afterSetContent');
				},delayTime);
			}else{
				me.fireEvent('afterSetContent');
			}
		},
		show:function(){
			var me = this;
			me.fireEvent('beforeShow');
			this.effectShow();
			me.fireEvent('afterShow');
			if(host.util.isIE6 && me.defConfig.isFixed){
				me._IE6Fixed = host.util.startFixed(me.dom);
			}
		},
		hide:function(){
			var me = this;
			me.effectHide();
			me.fireEvent('afterHide');
			if(host.util.isIE6 && me.defConfig.isFixed){
				me._IE6Fixed.stop();
			}
		},
		getContainerDom: function(){
			var me = this;
			return me._containerDom || (me._containerDom = me.dom);
		},
		getCloseDom: function(){
			var me = this;
			return me._closeDom || (me._closeDom = me.dom.find('.close'));
		},
		getTitleDom:function(){
			var me = this;
			return me._titleDom || (me._titleDom = me.dom.find('.pop-title'));
		},
		getContentDom:function(){
			var me = this;
			return me._ContentDom || (me._ContentDom = me.dom.find('.pop-bd'));
		}
	};



	var Main = host.Class(pros, Event);
		Main.defConfig = defConfig;

	//可生成多个实例
	host[name] = Main;
	//也可以重复使用实例
	host[name].getInstance = function(){
		return instance || (instance = new Main(defConfig));
	};

})(dsgame, "MiniWindow", dsgame.Event, jQuery);













(function(host, name, Event,undefined){
	var defConfig = {
			cls:''
		},
	instance;

	var pros = {
		//初始化
		init: function(cfg){
			var me = this;
			me.win = new host.MiniWindow(cfg);
			me.mask = host.Mask.getInstance();
			//绑定隐藏完成事件
			me.reSet();
			me.win.addEvent('afterHide', function(){
				me.reSet();
			})
			//定时器缓存
			me.closeTime = 0;
		},
		doAction: function(data){
			var me = this,
				funName = 'rebulid' + data['type'],
				getHtml = 'getHtml' + data['type'],
				fn = function(){
				};

			if(me[funName] && $.isFunction(me[funName])){
				fn = me[funName];
			}
			data['tpl']  = typeof data['tpl'] == 'undefined' ? me[getHtml]() : '' + data['tpl'];
			//删除type数据
			//防止在渲染的时候进行递归调用
			delete data['type'];
			//调用子类方法
			fn.call(me, data);
		},
		formatHtml:function(tpl, order){
			var me = this,o = order,p,reg;
			for(p in o){
				if(o.hasOwnProperty(p)){
					reg = RegExp('<#=' + p + '#>', 'g');
					tpl = tpl.replace(reg, o[p]);
				}
			}
			return tpl;
		},
		//添加题目
		setTitle: function(html){
			var me = this, win = me.win;
			win.setTitle(html);
		},
		//添加内容
		setContent: function(html, delay){
			var me = this, win = me.win;
			win.setContent(html, delay);
		},
		//隐藏关闭按钮
		hideClose: function(){
			var me = this, win = me.win;
			win.getCloseDom().hide();
		},
		//隐藏标题栏
		hideTitle: function(){
			var me = this, win = me.win;
			win.getTitleDom().hide();
		},
		//弹窗外容器增加样式
		addCssName: function(cssName){
			var me =this,
				win = me.win;
			win.getContainerDom().addClass(cssName);
		},
		//弹窗外容器恢复初始样式
		restCssName: function(){
			var me =this,
				win = me.win,
				cssName = me.defConfig.cls;

			me.removeCssName();
			win.getContainerDom().addClass(cssName);
		},
		//清除弹窗外容器样式
		removeCssName: function(cssName){
			var me =this,
				win = me.win;
			if(cssName){
				win.getContainerDom().removeClass(cssName);
			}else{
				win.getContainerDom().removeClass();
			}
		},
		//弹窗显示 具体参数说明
		//弹窗类型(会根据弹窗类型自动获取模版) type
		//模版 tpl  数据 tplData
		//内容:content, 绑定函数: callback, 是否遮罩: mask
		//宽度:width, 长度:height, 自动关闭时间单位S:time
		//是否显示头部: hideTitle, 是否显示关闭按钮:hideClose
		//增加弹窗外容器样式名称(一次性) cssName
		//确认按钮 是否显示: confirmIsShow 名称: confirmText 事件: confirmFun
		//取消按钮 是否显示: cancelIsShow  名称: cancelText	事件: cancelFun
		//关闭按钮 是否显示: closeIsShow   名称: closeText	事件: closeFun
		//默认关闭按钮 normalCloseFun 右上角关闭按钮关闭时触发执行函数
		show: function(data){
			var me = this, win = me.win;
			if(typeof data == 'undefined'){
				win.show();
				return;
			}

			me.reSet();
			if(typeof data['data'] == 'undefined'){
				data['data'] = {};
			}
			data['data']['tplData'] = typeof data['data']['tplData'] == 'undefined' ? {} : data['data']['tplData'];

			if(!data){return}

			if(data['type']){
				me.doAction(data);
				return;
			}else{
				if(typeof data['tpl'] != 'undefined'){
					data['content'] = me.formatHtml(data['tpl'], data['data']['tplData']);
				}
			}

			//取消自动关闭时间缓存
			if(me.closeTime){
				clearTimeout(me.closeTime);
				me.closeTime = null;
			}

			//加入题目 && 内容
			me.setTitle(data['title'] || '温馨提示');
			me.setContent(data['content'] || '');

			if(data['cssName']){
				me.addCssName(data['cssName']);
			}

			//按钮名称
			if(data['confirmText']){
				win.setConfirmName(data['confirmText']);
			}
			if(data['cancelText']){
				win.setCancelName(data['cancelText']);
			}
			if(data['closeText']){
				win.setCloseName(data['closeText']);
			}
			//按钮事件
			if(data['normalCloseFun']){
				win.doNormalClose = data['normalCloseFun'];
			}
			if(data['confirmFun']){
				win.doConfirm = data['confirmFun'];
			}
			if(data['cancelFun']){
				win.doCancel = data['cancelFun'];
			}
			if(data['closeFun']){
				win.doClose = data['closeFun'];
			}
			//按钮显示
			if(data['confirmIsShow']){
				win.showConfirmButton();
			}
			if(data['cancelIsShow']){
				win.showCancelButton();
			}
			if(data['closeIsShow']){
				win.showCloseButton();
			}
			//判断是否隐藏头部和关闭按钮
			if(data['hideTitle']){
				me.hideTitle();
			}
			if(data['hideClose']){
				me.hideClose();
			}
			//遮罩显示
			if(data['isShowMask']){
				me.mask.show();
			}

			win.show();

			//执行回调事件
			if(data['callback']){
				data['callback'].call(me);
			}

			//定时关闭
			if(data['time'] > 0){
				me.closeTime = setTimeout(function(){
					me.hide();
					clearTimeout(me.closeTime);
					me.closeTime = null;
				}, data['time'] * 1000);
			}
		},
		getContainerDom : function(){
			var me = this;
			return me.win.getContainerDom();
		},
		//获取内容容器DOM
		getContentDom : function(){
			var me = this;
			return me.win.getContentDom();
		},
		//弹窗隐藏
		hide: function(){
			var me = this, win = me.win;
			win.hide();
			me.reSet();
		},
		//重置
		reSet: function(){
			var me = this, win = me.win;

			me.mask.hide();
			me.setTitle('提示');
			me.setContent('');
			//me.restCssName();
			win.hideConfirmButton();
			win.hideCancelButton();
			win.hideCloseButton();
			win.doConfirm = function(){};
			win.doCancel = function(){};
			win.doClose = function(){};
			win.doNormalClose = function(){};
			win.setConfirmName('确 认');
			win.setCancelName('取 消');
			win.setCloseName('关 闭');
		}
	}

	var Main = host.Class(pros, Event);
		Main.defConfig = defConfig;
		Main.getInstance = function(cfg){
			return instance || (instance = new Main(cfg));
		};
	host[name] = Main;

})(dsgame, "Message",  dsgame.Event);









