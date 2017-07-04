(function(host, name, undefined) {
    var Main = function() {};
    host[name] = Main
})(this, "dsgame");

(function(host, name, $, undefined) {
    var BEFOREINIT = "beforeInit",
    AFTERINIT = "afterInit";
    var utilConstructor = function(Sub, cases, config) {
        var config = config || {};
        if (Sub.superClass && Sub.superClass.defConfig) {
            Sub.defConfig = $.extend({},
            Sub.superClass.defConfig, Sub.defConfig)
        }
        config = cases.defConfig = $.extend({},
        Sub.defConfig, config);
        if (config['expands']) {
            $.extend(cases, config['expands'])
        }
        if (Sub.superClass) {
            Sub.superClass.call(cases, config)
        }
        if ($.isFunction(Sub.prototype.init)) {
            var isSubSuper = cases.constructor === Sub;
            isSubSuper && $.isFunction(config[BEFOREINIT]) && config[BEFOREINIT].call(cases, config);
            Sub.prototype.init.call(cases, config);
            isSubSuper && $.isFunction(config[AFTERINIT]) && config[AFTERINIT].call(cases, config);
            if (isSubSuper) {
                for (var i = 0,
                len = cases._inits.length; i < len; i++) {
                    cases._inits[i].call(cases, cases.defConfig)
                }
            }
        }
    };
    var Class = function(pros, superClass) {
        var Main = function(config) {
            var me = this;
            me._inits = [];
            utilConstructor(Main, me, config)
        };
        if (arguments.length < 2) {
            Main.prototype = pros
        } else {
            var Cons = function() {};
            Cons.prototype = superClass.prototype;
            Main.prototype = new Cons();
            $.extend(Main.prototype, pros);
            Main.superClass = superClass
        }
        Main.prototype.constructor = Main;
        return Main
    };
    host[name] = Class
})(dsgame, "Class", jQuery);

(function(host, name, undefined) {
    var stopEvent = function() {
        this._isStop = true
    };
    var pros = {
        init: function(config) {
            this._events = {}
        },
        addEvent: function(name, fn) {
            if (!fn || Object.prototype.toString.call(fn) !== "[object Function]") {
                throw "Event.addEvent\u7B2C\u4E8C\u4E2A\u53C2\u6570\u5FC5\u987B\u662F\u51FD\u6570";
            }
            var me = this,
            _evs = me._events;
            _evs[name] = _evs[name] || [];
            _evs[name].push(fn)
        },
        removeEvent: function(name, fn) {
            var me = this,
            _evs = me._events;
            if (!_evs[name]) {
                return
            }
            if (!fn) {
                delete _evs[name];
                return
            }
            var fns = _evs[name],
            i = fns.length;
            while (i) {
                i--;
                if (fns[i] === fn) {
                    fns.splice(i, 1)
                }
            }
            if (!fns.length) {
                delete _evs[name]
            }
        },
        fireEvent: function(name) {
            var me = this,
            _evs = me._events;
            if (!_evs[name]) {
                return
            }
            var fns = _evs[name];
            if (fns._isStop) {
                delete fns._isStop;
                return
            }
            var i = 0,
            len = fns.length,
            ev = {
                type: name,
                data: me,
                stopEvent: stopEvent
            },
            args = [ev].concat(Array.prototype.slice.call(arguments, 1));
            for (var i = 0; i < len; i++) {
                if (ev._isStop || fns[i].apply(me, args) === false) {
                    ev._isStop = false;
                    return
                }
            }
        },
        stopEvent: function(name) {
            var me = this,
            _evs = me._events;
            if (!_evs[name]) {
                return
            }
            _evs[name]._isStop = true
        }
    };
    var Main = host.Class(pros);
    host[name] = Main
})(dsgame, "Event");

(function(host, name, $, undefined) {
    var Main = function() {};
    Main.win = $(window);
    Main.doc = $(document);
    Main.isIE = !!document.all;
    Main.isIE6 = window.ActiveXObject && navigator.userAgent.toLowerCase().match(/msie ([\d.]+)/)[1] == 6.0 ? true: false;
    Main.toViewCenter = function(el) {
        Main.toViewCenter.fn(el);
        Main.win.bind('resize',
        function() {
            Main.toViewCenter.fn(el)
        })
    };
    Main.toViewCenter.fn = function(el) {
        var el = $(el),
        w = el.width(),
        h = el.height(),
        allw = Main.win.width(),
        allh = Main.win.height(),
        scrollWidth = Main.isIE6 ? Main.win.scrollLeft() : 0,
        scrollHeight = Main.isIE6 ? Main.win.scrollTop() : 0;
        el.css({
            left: allw / 2 - w / 2 + scrollWidth,
            top: allh / 2 - h / 2 + scrollHeight
        })
    }
    Main.startFixed = function(el, time) {
        var el = $(el),
        fn,
        time = time || 500,
        top = parseInt(el.css('top')),
        sTop = Main.win.scrollTop(),
        _sTop = sTop,
        left = parseInt(el.css('left')),
        sLeft = Main.win.scrollLeft(),
        _sLeft = sLeft;
        fn = function() {
            var h = el.height(),
            w = el.width(),
            allw = Main.win.width(),
            allh = Main.win.height();
            _sTop = Main.win.scrollTop();
            _sLeft = Main.win.scrollLeft();
            el.stop();
            el.animate({
                top: allh / 2 - h / 2 + _sTop
            },
            50);
            sTop = _sTop;
            el.animate({
                left: allw / 2 - w / 2 + _sLeft
            },
            50);
            sLeft = _sLeft
        };
        return new host['Timer']({
            time: time,
            fn: fn
        });
    };
	Main.getRandom = function(n, m){
		return Math.floor(Math.random()*(m - n + 1) + n);
	};
    Main.getByteLen = function(str) {
        return str.replace(/[^\x00-\xff]/g, 'xx').length
    };
    Main.getParam = function(name) {
        var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
        if (reg.test(location.href)) {
            return unescape(RegExp.$2.replace(/\+/g, " "))
        } else {
            return null
        }
    };
    Main.template = function(tpl, data) {
        var me = this,
        o = data,
        p, reg;
        for (p in o) {
            if (o.hasOwnProperty(p)) {
                reg = RegExp('<#=' + p + '#>', 'g');
                tpl = tpl.replace(reg, o[p])
            }
        }
        return tpl
    };
    Main.formatMoney = function(num, digit) {
        var num = Number(num),
            digit = (digit == undefined || digit < 0) ? 2 : digit,
            re = /(-?\d+)(\d{3})/;
        if (Number.prototype.toFixed) {
            num = (num).toFixed(digit)
        } else {
            num = Math.round(num * Math.pow(10,digit)) / Math.pow(10,digit)
        }
        num = '' + num;
        while (re.test(num)) {
            num = num.replace(re, "$1,$2")
        }
        return num
    };
	
	Main.formatMoneySmall = function(num, digit) {
        var num = Number(num),
            digit = (digit == undefined || digit < 0) ? 2 : digit,
            re = /(-?\d+)(\d{3})/;
        if (Number.prototype.toFixed) {
            num = (num).toFixed(digit)
        } else {
            num = Math.round(num * Math.pow(10,digit)) / Math.pow(10,digit)
        }
        num = '' + num;
		var strs = [];
		strs = num.split(".");
		num = strs[0];
        while (re.test(num)) {
            num = num.replace(re, "$1,$2")
        }
		if(strs.length>1){
			num = num + '.<small>' +strs[1] + '</small>';
		} 
		
        return num
    };
    host[name] = Main
})(dsgame, "util", jQuery);