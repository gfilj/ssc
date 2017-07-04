//Mask页面遮罩静态类
//todo 考虑监听窗体缩放
(function(host, name, Event, $, undefined){
	var defConfig = {
		//参照物，将以该参照物的大小设置遮罩宽高
		target:'body',
		//
		opacity:.7,
		//
		'background-color':'#000',
		//
		'z-index':8060,
		effectShow:function(){
			var me = this;
			me.dom.css('opacity', 0);
			me.dom.show();
			me.dom.animate({opacity:.7}, 300);
		},
		effectHide:function(){
			this.dom.hide();
		}
	},
	html = $('html'),
	doc = $(document),
	instance;

	var pros = {
		init:function(cfg){
			var me = this;
			me.dom = $('<div class="j-ui-mask" style="display:none;position:absolute;left:0;top:0;"></div>').appendTo('body').css({'opacity':cfg['opacity'],'background-color':cfg['background-color'],'z-index':cfg['z-index']});
			me.effectShow = cfg.effectShow;
			me.effectHide = cfg.effectHide;
		},
		show:function(){
			var me = this,w = doc.width(),h = doc.height();
			me.dom.css({width:w,height:h});
			this.effectShow();
		},
		hide:function(){
			this.effectHide();
			this.fireEvent('afterHide');
		},
		css:function(styles){
			this.dom.css(styles);
		}

	};



	var Main = host.Class(pros, Event);
		Main.defConfig = defConfig;

	//遮罩层无需多个实例，直接限制为唯一单例
	host[name] = function(){
		return host[name].getInstance();
	};

	host[name].getInstance = function(){
		return instance || (instance = new Main(defConfig));
	};

})(dsgame, "Mask", dsgame.Event, jQuery);






