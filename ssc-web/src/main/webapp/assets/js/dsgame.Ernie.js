
//Ernie
//摇奖机滚动
(function(host, name, Event, $, undefined){
	var defConfig = {
	};

	var Cell = function(cfg){
		var me = this;
		me.init(cfg);
	};
	Cell.prototype = {
		init:function(cfg){
			var me = this;
			me.defConfig = cfg;
			me.height = cfg.height;
			me.length = cfg.length;
			me.dom = $(cfg.dom);
			me.firstDom = me.dom.children().eq(0);
			me.top = parseInt(me.dom.css('marginTop'));
			me.firstDomIsRelative = false;
			me.stopPos = 0;
			me.defConfig.emptyTimes = cfg.emptyTimes || 1;
			me.moveEmptyTimes = 100000000;
			if(cfg.callback){
				me.callback = cfg.callback;
			}
		},
		callback:function(){

		},
		start:function(){
			var me = this;
			me.isMoving = false;
			me.moveEmpty();
		},
		setStopPos:function(pos){
			this.stopPos = pos;
		},
		moveStop:function(num){
			var me = this;
			me.setStopPos(num * me.height * -1);
			me.moveEmptyTimes = me.defConfig.emptyTimes;
		},
		reSet:function(){

		},
		moveEmpty:function(opts){
			var me = this,
				dis = me.height * me.length * -1 + 1,
				h1 = dis + me.height,
				animateOptions = opts || {
					'duration':1000,
					'easing':'linear',
					'step':function(){
						me.top = parseInt(me.dom.css('marginTop'));
						if(!me.firstDomIsRelative && me.top <= h1){
							me.firstDom.css('position', 'relative');
							me.firstDomIsRelative = true;
						}
					},
					'complete':function(){
						me.firstDom.css({'position':'static'});
						me.firstDomIsRelative = false;
						me.dom.css('marginTop', 0);
						me.top = 0;
						me.moveEmptyTimes -= 1;
						if(me.moveEmptyTimes > 1){
							me.moveEmpty();
						}else{
							me.moveEmptyTimes = me.defConfig.emptyTimes;
							if(me.stopPos == 0){
								me.dom.css({'marginTop': me.height * -1});

								me.dom.animate({'marginTop':me.height * me.length * -1 + 1}, {
											   'duration':2500,
											   'easing':'easeOutQuart',
											   'step':function(){
													me.top = parseInt(me.dom.css('marginTop'));
													if(!me.firstDomIsRelative && me.top <= h1){
														me.firstDom.css('position', 'relative');
														me.firstDom.css('top', me.height * me.length);
														me.firstDomIsRelative = true;
													}
												},
											   'complete':function(){
													me.isMoving = true;
													me.callback();
												}
								});
							}else{
								me.dom.stop().animate({'marginTop':me.stopPos}, {'duration':host.util.getRandom(1000, 2500), 'easing':'easeOutQuart', 'complete':function(){
									me.isMoving = true;
									me.callback();
								}});
							}

						}

					}
				};
			me.dom.stop().animate({'marginTop':dis}, animateOptions);
		},
		constructor:Cell
	};


	var pros = {
		init:function(cfg){
			var me = this,
				cfg = $.extend({}, cfg);

			me.doms = $(cfg.dom);
			me.items = [];
			if(cfg.callback){
				me.callback = cfg.callback;
			}

			me.doms.each(function(i){
				me.items[i] = new Cell($.extend({}, cfg, {'dom':this, 'callback':function(){
					var len = 1;
					$.each(me.items, function(){
						if(!this.isMoving){
							len += 1;
						}
					});
					if(len == me.items.length){
						me.callback();
						//reSet
					}
				}}));
			});

		},
		callback:function(){

		},
		start:function(){
			var me = this;
			me.doms.each(function(i){
				setTimeout(function(){
					me.items[i].start();
				}, host.util.getRandom(1, 5) * 100);

			});
		},
		stop:function(numArr){
			var me = this;
			$.each(me.items, function(i){
				this.moveStop(numArr[i]);
			});
		}
	};


	var Main = host.Class(pros, Event);
		Main.defConfig = defConfig;
	host[name] = Main;

})(dsgame, "Ernie", dsgame.Event, jQuery);



























