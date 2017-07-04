var ctx = "/hz",
    keyWords = 'db2',
    platform = 'db2';

var _skipOnlineLottery=1 //这个选项完全没有影响 我的游戏中展示的游戏

// main game 配置
var alllotteryG = {
  "1": {"cn": "重庆时时彩","cls": "ball purple"},
  "2": {"cn": "广东11选5","cls": "ball orange"},
  "3": {"cn": "3D福彩","cls": "ball red"},
  "4": {"cn": "多宝25分彩","cls": "smallball"},
  "5": {"cn": "多宝211选5","cls": "smallball"},
  "6": {"cn": "泰国1.5分彩","cls": "ball purple"},
  "7": {"cn": "江西11选5","cls": "ball orange"},
  "8": {"cn": "新疆时时彩","cls": "ball purple"},
  "9": {"cn": "山东11选5","cls": "ball orange"},
  "11": {"cn": "新腾讯分分彩","cls": "smallball"},
  "14": {"cn": "排列三排列五","cls": "ball red"},
  "15": {"cn": "江苏快3","cls": "ball red"},
  "19": {"cn": "辽宁11选5","cls": "ball red"},
  "20": {"cn": "上海11选5","cls": "ball orange"},
  "21": {"cn": "香港时时彩","cls": "ball orange"},
  "23": {"cn": "日本时时彩","cls": "ball purple"},
  "24": {"cn": "安徽快3","cls": "ball red"},
  "26": {"cn": "湖北快3","cls": "ball red"},
  "27": {"cn": "内蒙快3","cls": "ball red"},
  "29": {"cn": "天津时时彩","cls": "ball purple"},
  "31": {"cn": "韩国1.5分彩","cls": "ball purple"},
  "32": {"cn": "北京PK10","cls": "ball purple"},
  "33": {"cn": "加拿大3.5分彩","cls": "ball purple"},
  "34": {"cn": "新加坡2分彩","cls": "ball purple"},
  "36": {"cn": "东京1.5分彩","cls": "ball purple"},
  "37": {"cn": "河内5分彩","cls": "ball purple"},
  "39": {"cn": "北京快乐8","cls": "ball purple"},
  "40": {"cn": "台湾宾果","cls": "ball purple"},
  "41": {"cn": "京都1.5分彩","cls": "ball purple"},
  "44": {"cn": "腾讯分分彩","cls": "ball purple"},
  "45": {"cn": "韩国1分彩","cls": "ball purple"},
  "46": {"cn": "泰国30秒","cls": "ball red"},
  "61": {"cn": "巴黎11选5","cls": "smallball"},
  "#": {"cn": "多宝2秒秒彩","cls": "smallball disabled"}
}

var _cnDict = {'db2':'多宝'};//台名
var _lazyImageUrl = '/static/mobile/theme'+(typeof platform != 'undefined' ? '/'+platform : '/')+'/images/topbg.png';//延迟加载背景图
/*var _lazyMLoginImageUrl = '/static/theme/'+keyWords+'/images/login/login_bg_high.jpg';//延迟加载手机登录背景图*/
var _dictoryMap = {'db2':'/static/m/tpl/db2'};//手机APP TPL目录
var _isHaveThird = 0;//是否有第三方,0没有1有
var _lotteryMode = 1;//1，1元模式，2：两元模式
var _withdrawTimeRange = {
  begin: "11:00:00",beginCn:"",
  end: "02:00:00",endCn:"",
  crossday: 1 //1为跨日，0为不跨日
}
//var _skipOnlineLottery=0;//不读取接口的在售彩种列举，直接显示jsp的彩种
var _allsites = {
  'db2':'#,#'.split(',')
} //测速网址配置
var _displaymax ={'db2':4};//测速网址最大显示数量
var _mobileMmc = 1;//不定义此变量时为不显示移动端秒秒彩
var _skipfirst = 0;//是否忽略第一次测试，1为忽略，0为不忽略
var _switchCall = function() {
  window.location.reload();//切换手机端PC端后的回调事件3a.util.js
}
//var _isCompressingPost = 1;//为undefined时不压缩提交参数，存在时压缩参数
var hotlink = "https://kf1.learnsaas.com/chat/chatClient/chatbox.jsp?companyID=819082&configID=64920&jid=6634132239&s=1"; //客服链接
var about = ' 多宝娱乐2是一家有着境外实体产业支撑的在线娱乐平台，公司的产业涉足酒店，旅游，KTV，赌场以及线上娱乐等业务。公司在行业内深耕多年，积累了足够多的行业经验与服务理念。在加强线下服务的同时，公司也在不断的拓展线上产业，为客户提供多元化的服务与享受。 多宝娱乐2是业内首家真正境外娱乐平台，不仅仅体现在办公境外化上，公司的法人也为境外人士，且平台也真正以公司化操作运营，并在菲律宾取得了合法营业执照，为平台的长久发展提供了保证。';
var repeatTime = 3,repeatCount= 0;//彩种暂停销售时的重试次数
var defaultSoundMute = false;//默认开启声音，不静音提示;
var defaultNonpush = false;//默认开启推送提示，nonpush为空时推送，不为空时不推送;

//下载中心页面-下载地址
var android_href = 'https://fir.im/g1eh?utm_source=fir&utm_medium=qr';
var ios_href = 'https://dafuvip.com/732IFf';
var pc_href = '/cms/upload/app/duobao_setup_1.0.0.0.exe';
// var pc_href = false;
//定时器管理对象
var LoopManage = LoopManage || {
    refreshMoney: null,
    refreshIssus: null,
    refreshBonus: null,
    refreshSetting: null,
    refreshTime: null
};
//充值额度
var rechargeQuota = {
	lefu : {
		wechat : {
			min : "50.0",
			max : "3000.0"
		},
		zhifubao : {
			min : "50.0",
			max : "999.0"
		}
	},
	xinbei : {
		zhifubao : {
			min : "50.0",
			max : "999.0"
		},
	},
	tonghui : {
		wechat : {
			min : "50.0",
			max : "3000.0"
		}
	},
	//扫码 微信-支付宝
    scan : {
		wechat : {
			min : "100.0",
			max : "2000.0"
		},
		zhifubao : {
			min : "100.0",
			max : "3000.0"
		}
	}
}
//走势图
var LtChsDict = {
  "1" : "重庆时时彩","2" : "广东11选5","3" : "3D福彩","4" : _cnDict[keyWords] + "5分彩","5" : _cnDict[keyWords] + "11选5","7" : "江西11选5","8" : "新疆时时彩","9" : "山东11选5","11" : "新腾讯分分彩","13" : "重庆龙虎豹","14" : "排列三/排列五",
  "15" : "江苏快3","16" : "吉林快3","17" : "江苏骰宝","19" : "辽宁11选5","20" : "上海11选5","21" : "香港时时彩","22" : "香港11选5","23" : "日本时时彩","24" : "安徽快3","25" : "广西快3","26" : "湖北快3",
  "27" : "内蒙快3","29" : "天津时时彩","31" : "韩国1.5分彩","32" : "北京PK10","34" : "新加坡2分彩","36" : "东京1.5分彩","39" : "北京快乐8","40" : "台湾宾果","6" : "泰国1.5分彩","43" : "江苏11选5","44" : "腾讯分分彩","61" : "巴黎11选5","41" : "京都1.5分彩"
};