

//拖动条 类
(function(host, name, Event, $, undefined){
	var defConfig = {
		'minBound':10,
		'maxBound':100,
		'rangeBound': [],
		'value':50,
		'step':5,
		//是否只能往上调整
		'isUpOnly':false,
		'isDownOnly':false,
		'parentDom': null
	};

	var pros = {
			init:function(cfg){
				var me = this;
				me.parentDom = $(cfg.parentDom);
				if( me.parentDom.length ){
					me.minDom = $(cfg.minDom, me.parentDom);
					me.maxDom = $(cfg.maxDom, me.parentDom);
					me.contDom = $(cfg.contDom, me.parentDom);
					me.handleDom = $(cfg.handleDom, me.parentDom);
					me.innerDom = $(cfg.innerDom, me.parentDom);
					me.minNumDom = $(cfg.minNumDom, me.parentDom);
					me.maxNumDom = $(cfg.maxNumDom, me.parentDom);
				}else{
					me.minDom = $(cfg.minDom);
					me.maxDom = $(cfg.maxDom);
					me.contDom = $(cfg.contDom);
					me.handleDom = $(cfg.handleDom);
					me.innerDom = $(cfg.innerDom);
					me.minNumDom = $(cfg.minNumDom);
					me.maxNumDom = $(cfg.maxNumDom);
				}
				me.rangeBound = [cfg.rangeBound[0] || cfg.minBound, cfg.rangeBound[1] || cfg.maxBound];
				if( me.rangeBound[0] < cfg.minBound ){
					me.rangeBound[0] = cfg.minBound;
				}
				if( me.rangeBound[1] > cfg.maxBound ){
					me.rangeBound[1] = cfg.maxBound;
				}
				me.minBound = cfg.minBound;
				me.maxBound = cfg.maxBound;
				me.width = me.contDom.width() - me.handleDom.width();
				me.step = cfg.step;
				me.isDrag = false;
				me.dragX = 0;
				me.dragLeft = 0;
				me.value = cfg.value;
				me.minNumDom.text(me.minBound);
				me.maxNumDom.text(me.maxBound);
				me.initEvent();
				me.defaultValue = cfg.value;
				me.setValue(me.value);
			},
			getDom:function(){
				return this.parentDom;
			},
			getPosByValue:function(value){
				var me = this,value = Number(value) - me.minBound,pos = 0;
				pos = value / (me.maxBound - me.minBound) * me.width;
				return pos;
			},
			getValueByPos:function(pos){
				var me = this,pos = Number(pos),value = 0;
				value = pos / me.width * (me.maxBound - me.minBound);
				return Math.ceil(value + me.minBound);
			},
			initEvent:function(){
				var me = this,moveFn,upFn;
				me.handleDom.mousedown(function(e){
					me.isDrag = true;
					me.dragX = e.clientX;
					me.dragLeft = parseInt(me.handleDom.css('left'));
					if(me.handleDom.get(0).setCapture){
						me.handleDom.get(0).setCapture();
					}else if(window.captureEvents){
						window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
					}
					$(document).bind('mousemove', moveFn);
					$(document).bind('mouseup', upFn);
				});
				me.contDom.click(function(e){
					var target = e.target || e.srcElement;
					if( !$(target).is(me.handleDom) ){
						var pos = e.pageX - $(this).offset().left;
						me.setValue(me.getValueByPos(pos));
					}
				});
				moveFn = function(e){
					var left = 0,
						//临时倍数
						mul = 1,
						value = 0;
					if(me.isDrag){
						left = me.dragLeft + e.clientX - me.dragX;
						if(left < 0 || (left > me.width)){
							return;
						}
						value = me.getValueByPos(left);
						mul = Math.ceil(value/me.step);
						value = mul * me.step;
						me.setValue(value);
					}
				};
				upFn = function(){
					me.isDrag = false;
					if(me.handleDom.get(0).releaseCapture){
						me.handleDom.get(0).releaseCapture();
					}else if(window.captureEvents){
						window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
					}
					$(document).unbind('mousemove', moveFn);
					$(document).unbind('mouseup', upFn);
				};

				me.minDom.click(function(){
					me.setValue(me.getValue() - me.step);
				});
				me.maxDom.click(function(){
					me.setValue(parseInt(me.getValue()) + me.step);
				});
			},
			//cfg {minBound:,maxBound,step,value}
			reSet:function(cfg){
				var me = this;
				me.minBound = typeof cfg.minBound != 'undefined' ? cfg.minBound : me.minBound;
				me.maxBound = typeof cfg.maxBound != 'undefined' ? cfg.maxBound : me.maxBound;
				me.step = typeof cfg.step != 'undefined' ? cfg.step : me.step;
				me.defaultValue = cfg.value;
				me.minNumDom.text(me.minBound);
				me.maxNumDom.text(me.maxBound);
				me.setValue(cfg.value);
			},
			setValue:function(value){
				var me = this,cfg = me.defConfig,pos = 0;
				value = value < me.rangeBound[0] ? me.rangeBound[0] : value;
				value = value > me.rangeBound[1] ? me.rangeBound[1] : value;

				//仅只能向下调整
				if(cfg.isDownOnly){
					value = value > me.defaultValue ? me.defaultValue : value;
				}
				//仅只能向上调整
				if(cfg.isUpOnly){
					//console.log(me.defaultValue);
					value = value < me.defaultValue ? me.defaultValue : value;
				}

				if( value <= me.rangeBound[0] ){
					me.minDom.addClass('disabled');
				}else{
					me.minDom.removeClass('disabled');
				}

				if( value >= me.rangeBound[1] ){
					me.maxDom.addClass('disabled');
				}else{
					me.maxDom.removeClass('disabled');
				}

				me.value = value;
				pos = me.getPosByValue(value);
				pos = pos < 0 ? 0 : pos;
				pos = pos > me.width ? me.width : pos;
				me.setPos(pos);
				me.fireEvent('change');
			},
			getValue:function(){
				return this.value;
			},
            setDefaultValue : function(value){
                var me = this;
                me.defaultValue = value;
            },
			setPos:function(pos){
				var me = this;
				pos = pos < 0 ? 0 : pos;
				pos = pos > me.width ? me.width : pos;
				me.handleDom.css('left', pos);
				me.innerDom.css('width', pos);
			}
	};


	var Main = host.Class(pros, Event);
		Main.defConfig = defConfig;
	host[name] = Main;

})(dsgame, "SliderBar", dsgame.Event, jQuery);