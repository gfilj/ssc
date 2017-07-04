
//模拟下拉框组件
(function(host, name, Event, $, undefined){
	var defConfig = {
		//最外层添加的class样式
		cls:'',
        valueKey: 'value',
        textKey: 'text',
		//是否同时能输入
		isInput:false,
		// 是否能作为计数器使用
		isCounter: false,
		// 计数器的最小值
		min: 1,
		// 计数器的最大值
		max: 99999,
		// 加减的步长
		step: 1,
		//对应的真实select
		realDom:'',
		//面板展开时的z-index值
		zIndex:100,
		//是否开启鼠标条
		isScroll:true,
		//滚动容器显示高度
		scrollHeight:310,
		//滚轮每次滚动面变移动距离
		scrollDis:31,
		//模拟select模板
		tpl:'<div class="choose-model"><div class="choose-list"><div class="choose-list-cont"><#=loopItems#></div></div><span class="choose-scroll" onselectstart="return false;"></span><span class="info"><input data-realvalue="<#=value#>" class="choose-input choose-input-disabled" disabled="disabled" type="text" value="<#=text#>" /></span><i></i></div>',
		//单行元素模板
		itemTpl:'<a data-value="<#=value#>" href="#"><#=text#></a>'
	};

	var pros = {
		init:function(cfg){
			var me = this;
			me.opts = cfg;
			me.realDom = $(cfg.realDom);
			me.realDom.hide();
			me.dom = null;
			me.listDom = null;
			me.buildSelect();
			// 计数器相关
			if( cfg.isCounter ){
				me.$ctrlDecrease = $('<span class="select-counter-action counter-decrease" data-counter-action="decrease">－</span>');
				me.$ctrlIncrease = $('<span class="select-counter-action counter-increase" data-counter-action="increase">+</span>');
				me.dom.before(me.$ctrlDecrease).after(me.$ctrlIncrease);
				me.$ctrl = me.$ctrlDecrease.add(me.$ctrlIncrease);
				me.setMinValue(cfg.min);
				me.setMaxValue(cfg.max);
				// me.checkCtrl();
				me.counterEvent(cfg);
			}
			me.initEvent();
		},
		show:function(){
			this.dom.show();
		},
		hide:function(){
			this.dom.hide();
		},
		showScroll:function(){
			var me = this;
			if(me.isScrollAble){
				this.getScrollDom().show();
			}
		},
		hideScroll:function(){
			var me = this,
				dom = me.getScrollDom();
			dom.hide();
			if(me.isScrollAble){
				me.scrollSetTop(0);
				dom.css('top', 0);
			}
		},
		getScrollDom:function(){
			var me = this;
			return me.scrollDom || (me.scrollDom = me.dom.find('.choose-scroll'));
		},
		initScroll:function(){
			var me = this,cfg = me.defConfig;
			if(me.getListContDomHeight() > cfg.scrollHeight){
				me.isScrollAble = true;
				me.getListDom().css({'height':cfg.scrollHeight, 'overflow':'hidden'});
				me.reBuildScroll();
			}else{
				me.isScrollAble = false;
			}
		},
		//计算滚动条相关参数
		reBuildScroll:function(){
			var me = this,cfg = me.defConfig,
				outerHeight = cfg.scrollHeight,
				innerHeight = me.getListContDomHeight();

			//最高内容器高度与显示区域高度比例
			me.scrollBl = innerHeight/outerHeight;
			//滚动条应显示的高度
			me.getScrollDom().css('height', outerHeight/me.scrollBl);
			me.scrollMin = 0;
		},
		scrollSetTop:function(top){
			var me = this;
			me.getListDom().scrollTop(top * me.scrollBl);
		},
		getListDom:function(){
			var me = this;
			return me.listDom || (me.listDom = me.dom.find('.choose-list'));
		},
		getListContDom:function(){
			var me = this;
			return me.listContDom || (me.listContDom = me.dom.find('.choose-list-cont'));
		},
		getListContDomHeight:function(){
			var me = this,h = 0;
			me.getListDom().css({'visibility':'hidden'}).show();
			h = me.getListContDom().height();
			me.getListDom().css({'visibility':'visible'}).hide();
			return h;
		},
		buildSelect:function(){
			var me = this,cfg = me.defConfig,tpl = cfg.tpl,itemTpl = cfg.itemTpl,items = me.getRealDom().options,len = items.length,i = 0,
				itemStrArr = [],
				currValue = '',
				currText = '';
			for(;i < len;i++){
				itemStrArr[i] = itemTpl.replace(/<#=value#>/g, items[i].value).replace(/<#=text#>/g, items[i].text);
				if(i == me.getRealDom().selectedIndex){
					currValue = items[i].value;
					currText = items[i].text;
				}
			}
			tpl = tpl.replace(/<#=text#>/g, currText).replace(/<#=loopItems#>/g, itemStrArr.join(''));
			me.dom = $(tpl);
			me.dom.addClass(cfg.cls);
			me.dom.insertBefore(me.getRealDom());

			if(cfg.isScroll){
				me.initScroll();
			}

			if(cfg.isInput){
				me.getInput().removeAttr('disabled');
				me.getInput().removeClass('choose-input-disabled');
				me.inputEvent();
			}
			if ($(me.dom).hasClass('select-game-statics-multiple')) {
        $('#multitimes').val(currValue);
      }
      me.setValue(currValue);

			me.reSetListWidth();
		},
		reSetListWidth:function(){
			var me = this,width = 0;
			if(host.util.isIE6){
				width = me.dom.width() + 8;
				me.getListDom().width(width);
			}
		},
		//data [{value:,text:,checked:true}]
		reBuildSelect:function(data){
			var me = this,cfg = me.defConfig,sel = $(me.getRealDom()),strArr = [],strArrOption = [],itemTpl = me.defConfig.itemTpl,selectIndex;
			$.each(data, function(i){
				strArr[i] = '<option value="'+ this[cfg.valueKey] +'">'+ this[cfg.textKey] +'</option>';
				strArrOption[i] = itemTpl.replace(/<#=value#>/g, this[cfg.valueKey]).replace(/<#=text#>/g, this[cfg.textKey]);
				if(this['checked']){
					selectIndex = i;
				}
			});
			sel.html(strArr.join(''));
			me.getListContDom().html(strArrOption.join(''));

			if(typeof selectIndex != 'undefined'){
				me.setValue(data[selectIndex][cfg.valueKey]);
			}

			if(me.defConfig.isScroll){
				me.initScroll();
			}
		},
		initEvent:function(){
			var me = this,scrollDis = me.defConfig.scrollDis;

			$(document).mousedown(function(e){
				var el = e.target;
				if(!$.contains(me.dom.get(0), el)){
					me.getListDom().hide();
					me.hideScroll();
					me.dom.css('zIndex', '');
					me.dom.find('.open').removeClass('open');
				}
			});
			$(window).blur(function(){
				me.getListDom().hide();
				me.dom.css('zIndex', '');
				me.dom.find('.open').removeClass('open');
				me.hideScroll();
			});


			me.dom.click(function(e){
				var el = e.target,attr = el.getAttribute('data-value');
				//如果是选项点击
				if(attr != null){
					me.setValue(attr);
				}
				if($.trim(me.getListDom().css('display').toLowerCase()) != 'none'){
					me.dom.css('zIndex', '');
					me.getListDom().hide();
					me.hideScroll();
					me.dom.find('.open').removeClass('open');
				}else{
					me.dom.css('zIndex', me.defConfig.zIndex);
					me.getListDom().show();
					me.showScroll();
					me.dom.find('i').addClass('open');
				}

				e.preventDefault();
			});

			var deltaAll = 0;
			me.getListDom().mousewheel(function(e, delta){
				deltaAll += delta * 5;
				e.preventDefault();
				clearTimeout(me.timer);
				me.timer = setTimeout(function(){
					var el = me.getListDom(),
						top = el.scrollTop(),
						delta = delta * 3,
						sTop = parseInt(me.getScrollDom().css('top'));
						newTop = top + deltaAll * scrollDis * -1,
						scrollTop = 0,
						contHeight = me.getListContDom().height(),
						maxScroll = el.height() - me.getScrollDom().outerHeight() - 4;

					deltaAll = 0;
					el.stop().animate({scrollTop:newTop}, 500, 'easeOutQuad');
					scrollTop = newTop/me.scrollBl;
					scrollTop = scrollTop < 0 ? 0 : scrollTop;
					scrollTop = scrollTop > maxScroll ? maxScroll : scrollTop;

					me.scrollDom.stop().animate({top:scrollTop}, 400, 'easeOutQuad');
				}, 10);
			});


			var dragDom = me.getScrollDom(),donwX,donwY,isDraging = false,
				downEventFn = function(e){
					isDraging = true;
					donwY = e.clientY - parseInt(dragDom.css('top'));
					if(dragDom.get(0).setCapture){
						dragDom.get(0).setCapture();
					}else if(window.captureEvents){
						window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
					}
					$(document).bind('mousemove', moveEventFn);
					$(document).bind('mouseup', upEventFn);
				},
				moveEventFn = function(e){
					var top = e.clientY - donwY,h = 0;
					e.preventDefault();
					if(!isDraging){
						return false;
					}
					h = me.getListDom().height() - dragDom.height() - 4;
					top = top < 0 ? 0 : top;
					top = top > h ? h : top;

					dragDom.css('top',top);

					//到达极限时为防止误差，直接设很大的数字(scroll的设置对超出范围没有影响)
					top = top == h ? top * 100 : top;
					me.scrollSetTop(top);
				},
				upEventFn = function(e){
					if(dragDom.get(0).releaseCapture){
						dragDom.get(0).releaseCapture();
					}else if(window.captureEvents){
						window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
					}
					isDraging = false;
					$(document).unbind('mousemove', moveEventFn);
					$(document).unbind('mouseup', upEventFn);
				};
			dragDom.mousedown(downEventFn);
			dragDom.click(function(e){
				e.preventDefault();
				e.stopPropagation();
			});

		},
		getInput:function(){
			var me = this;
			return me.input || (me.input = me.dom.find('.choose-input'));
		},
		//input校验函数
		inputEvent:function(){
			var me = this;
			me.getInput().on('keyup blur', function(){
				var val = this.value.replace(/[^\d]/g, '');
				if (val <= me.getMinValue()) {
					me.setButtonStatus('decrease', 'disabled');
					val = me.getMinValue();
				} else {
					me.setButtonStatus('decrease');
				}
				if (val >= me.getMaxValue()) {
					me.setButtonStatus('increase', 'disabled');
					val = me.getMaxValue();
				} else {
					me.setButtonStatus('increase');
				}
				me.setValue(val);
			});
		},
		getRealDom:function(){
			return this.realDom.get(0);
		},
		getItemList:function(){
			var me = this;
			return me.getListContDom().children();
		},
		//isStop 防止两个对象相互调用，相互触发形成死循环，例如：日期控件的面板渲染和Select的相互触发
		setValue:function(value, isStop){
      //t.multipleDom.addEvent("change"
      var me = this,dom = me.getRealDom(),index = dom.selectedIndex,options = dom.options,len = options.length,i = 0,text = '';
			for(;i < len;i++){
				if(value == options[i].value){
					options[i].selected = true;
					text = options[i].text;
				}else{
					options[i].selected = false;
				}
			}
			value = '' + value;
      
			me.getInput().attr('data-realvalue', value);
			text = text == '' ? value : text;
			me.getInput().val(text);
			if(!isStop){
				me.fireEvent('change', value, text);
			}
			me.getItemList().removeClass('choose-item-current').parent().find('[data-value="'+ value +'"]').addClass('choose-item-current');
			if( me.opts.isCounter ){
				me.checkCtrl();
			}
		},
		getValue:function(){
			var me = this,dom = me.getRealDom(),index = dom.selectedIndex;
			if(me.defConfig.isInput){
				return me.getInput().attr('data-realvalue');
			}
			if(!dom.options[index]){
				return null;
			}
			return dom.options[index].value;
		},
		getText:function(){
			var dom = this.getRealDom(),index = dom.selectedIndex;
			return dom.options[index].text;
		},
		setMinValue: function( num ){
			this.minValue = num;
			this.checkCtrl();
		},
		getMinValue: function(){
			return this.minValue;
		},
		setMaxValue: function( num ){
			this.maxValue = num;
			this.checkCtrl();
		},
		getMaxValue: function(){
			return this.maxValue;
		},
		setButtonStatus: function(button, status){
			if( !this.$ctrl ) return;
			var $ctrl = this.$ctrl.filter('[data-counter-action="' + button + '"]');
			if( status == 'disabled' ){
				$ctrl.addClass('disabled');
			}else{
				$ctrl.removeClass('disabled');
			}
		},
		checkCtrl: function() {
			var me = this, val = me.getValue();
			if (val <= me.getMinValue()) {
				me.setButtonStatus('decrease', 'disabled');
				val = me.getMinValue();
			} else {
				me.setButtonStatus('decrease');
			}
			if (val >= me.getMaxValue()) {
				me.setButtonStatus('increase', 'disabled');
				val = me.getMaxValue();
			} else {
				me.setButtonStatus('increase');
			}
			return val;
		},
		counterEvent: function() {
			var me = this, opts = me.opts;
			me.$ctrl.on('click', function(e) {
				if ($(this).hasClass('disabled')) return false;
				var val = parseInt(me.getValue()),
					action = $(this).data('counter-action');
				if (action == 'increase') val += opts.step;
				else if (action == 'decrease') val -= opts.step;
        if ($(me.dom).hasClass('select-game-statics-multiple')) {
          $('#multitimes').val(val);
        }
        me.setValue(val);
			});
		}

	};

	var Main = host.Class(pros, Event);
		Main.defConfig = defConfig;
	host[name] = Main;


})(dsgame, "Select", dsgame.Event, jQuery);