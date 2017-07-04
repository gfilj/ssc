var formatDateUnix = formatDateUnix || function(unix) {
	var d = new Date();
	d.setTime(parseInt(unix) * 1000);
	var month = d.getMonth() + 1,
		day = d.getDate(),
		hours = d.getHours(),
		minutes = d.getMinutes(),
		seconds = d.getSeconds();

	if (month < 10) month = '0' + month;
	if (day < 10) day = '0' + day;
	if (hours < 10) hours = '0' + hours;
	if (minutes < 10) minutes = '0' + minutes;
	if (seconds < 10) seconds = '0' + seconds;
	return month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}
var formatMoney = formatMoney || function(num) {
	var num = Number(num),
		re = /(-?\d+)(\d{3})/;
	if (Number.prototype.toFixed) {
		num = (num).toFixed(2)
	} else {
		num = Math.round(num * 100) / 100
	}
	num = '' + num;
	while (re.test(num)) {
		num = num.replace(re, "$1,$2")
	}
	return num
}

var dsCookie = {
    readCookie: function (name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ')
                c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) === 0)
                return c.substring(nameEQ.length, c.length);
        }
        return "";
    },
    eraseCookie: function (name) {
        this.createCookie(name, "", -1);
    },
    createCookie: function (name, value, days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toGMTString();
        }
        document.cookie = name + "=" + value + expires + "; path=/";
    }
};

;(function($){
	function F(t, o){
		this.opts = $.extend({
			delay: 300,
			handler: '[data-overdropdown="handler"]',
			dropdown: '[data-overdropdown="dropdown"]',
			activeClass: 'active',
			handlerIsLink: false,
			debugs: false,
      fade:true
		}, o);
		this.$t = $(t);
		this.debugs = this.opts.debugs;
		this.init();
	}

	F.prototype = {
		init: function(){
			var me = this,
				$h = me.$t.find(me.opts.handler);
        if (me.opts.fade) {
          $d = me.$t.find(me.opts.dropdown).fadeOut('fast');  
        }else {
          $d = me.$t.find(me.opts.dropdown).hide(); 
        }
				
			if( !$h.length || !$d.length ){
        return false;
			}
			me.timerOut = null;
			me.timerIn = null;
			me.$handler = $h;
			me.$dropdown = $d;
			me.bindEvent();
		},
		bindEvent: function(){
			var me = this, opts = me.opts;
			// Hover下拉
			me.$handler.hover(function(){
				var $t = $(this);
				if( me.timerOut ){
					clearTimeout(me.timerOut);
				}
				me.timerIn = setTimeout(function(){
					$t.addClass(opts.activeClass);
					opts.fade ? me.$dropdown.fadeIn('fast') : me.$dropdown.show();
				}, opts.delay);
			}, function(){
				var $t = $(this);
				if( me.timerIn ){
					clearTimeout(me.timerIn);
				}
				me.timerOut = setTimeout(function(){
					$t.removeClass(opts.activeClass);
					opts.fade ? me.$dropdown.fadeOut('fast') : me.$dropdown.hide();
				}, parseInt(opts.delay,10)+300);
			}).on('click', function(){
				if( !opts.handlerIsLink ) return false;
			});

			me.$dropdown.hover(function(){
				if( me.$dropdown.is(':visible') ){
					if( me.timerOut ){
						clearTimeout(me.timerOut);
					}
				}else{
					me.$handler.fadeIn('fast');
				}
			}, function(){
				if( me.$dropdown.is(':visible') ){
					me.timerOut = setTimeout(function(){
						me.$handler.removeClass(opts.activeClass);
						opts.fade ? me.$dropdown.fadeOut('fast') : me.$dropdown.hide();
					}, parseInt(opts.delay,10)+300);
				}
			});
		},
		// debug
		debug: function(){
			this.debugs && window.console && console.log && console.log( '[overdropdown]', arguments );
		}
	}

	$.fn.overdropdown = function(o) {
		var instance;
		this.each(function() {
			instance = $.data( this, 'overdropdown' );
			if ( instance ) {
			} else {
        instance = $.data( this, 'overdropdown', new F(this, o) );
			}
		});
		return instance;
	}
})(jQuery);

// 热门活动推荐
;(function($){
	function F(t, o){
		this.opts = $.extend({
			eventsData: [],
			debugs: false
		}, o);
		this.$t = $(t);
		this.debugs = this.opts.debugs;
		this.init();
	}

	F.prototype = {
		init: function(){
			var me = this,
				data = me.opts.eventsData,
				html = '<div class="event-push"> \
				<span class="push-close right c-gray">关闭提示</span> \
				<label class="left"><b>当前热门活动</b></label>';

			$.each(data, function(idx, d){
				var href = d['link'] ? d['link'] : 'javascript:void(0);';
				if( idx > 0 ){
					html += '|';
				}
				html += '<a class="c-important" href="' + href + '">' + d['title'] + '</a>';
			});
			html += '</div>';
			var $html = $(html);
			me.$t.replaceWith($html);
			me.$t = $html;

			me.$t.find('.push-close').on('click', function(){
				me.$t.fadeOut('slow');
				return false;
			});
		},
		// debug
		debug: function(){
			this.debugs && window.console && console.log && console.log( '[eventsPush]', arguments );
		}
	}

	$.fn.eventsPush = function(o) {
		var instance;
		this.each(function() {
			instance = $.data( this, 'eventsPush' );
			// instance = $(this).data( 'eventsPush' );
			if ( instance ) {
				// instance.init();
			} else {
				instance = $.data( this, 'eventsPush', new F(this, o) );
			}
		});
		return instance;
	}
})(jQuery);

$(function() {
	//全局金额重置
	$('[data-money-fixed]').each(function() {
		var $this = $(this);
		var size = $this.data("money-fixed") || 4;
		var html = $this.html();
		var num = Number(html);
		if(num){
			num = num.toFixed(size);
			$this.html(num+'');
		}
	});
	
	$('[data-money-format]').each(function() {
		var $this = $(this);
		html = $this.html(),
		re = /(\d+)\.(\d+)/,
		fontSize = parseInt($this.css('fontSize'));
		// console.log(fontSize);
		if (fontSize <= 12) {
			$this.css({
				'font-size': '13px'
			});
		}
		html = html.replace(re, '$1' + '.' + '<small>' + '$2' + '</small>');
		$this.html(html);
	});

	// 热门活动推荐
//	$('[data-event-push]').eventsPush({
//		eventsData: [{
//			title: '天天拿红包，奖金您做主！更低倍数更给力！【加强版】',
//			// title: '天天拿红包，奖金您做主！',
//			link: '/events/everydaygetmoney'
//		}]
//	});


	// 打印
	$('[data-print]').on('click', function(){
		window.print();
	});

	// 添加银行卡
	// 变量必须保证为全局变量，以便iframe内调用
	/*if( dsgame && dsgame.Mask && dsgame.MiniWindow ){
		var addCardMask = new dsgame.Mask(),
			addCardMiniwindow = new dsgame.MiniWindow({
				cls: 'w-12 add-card-miniwindow'
			});

		var CardAddFun = function(url) {
			var hideMask = function() {
				addCardMiniwindow.hide();
				addCardMask.hide();
			};

			addCardMiniwindow.setContent(
				'<iframe src="' + url + '" id="card-add-bind-frame" ' +
				'width="100%" height="360" frameborder="0" allowtransparency="true" scrolling="no"></iframe>'
			);
			addCardMiniwindow.setTitle('添加银行卡');
			addCardMiniwindow.showCancelButton();
			// addCardMiniwindow.showConfirmButton();

			addCardMiniwindow.doNormalClose = hideMask;
			addCardMiniwindow.doConfirm = hideMask;
			addCardMiniwindow.doClose = hideMask;
			addCardMiniwindow.doCancel = hideMask;

			$('[data-add-bankcard]').on('click', function() {
				addCardMask.show();
				addCardMiniwindow.show();
			});
		};
	}else{
		var CardAddFun = function(){};
	}*/

});