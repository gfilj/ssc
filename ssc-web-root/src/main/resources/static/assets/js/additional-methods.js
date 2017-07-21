/*!
 * jQuery Validation Plugin v1.13.1
 *
 * http://jqueryvalidation.org/
 *
 * Copyright (c) 2014 Jörn Zaefferer
 * Released under the MIT license
 */
(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "./jquery.validate"], factory );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * 以sobet_开头的表示SOBET增加的自定义验证方法
 */
$.validator.addMethod("sobet_password", function(value, element) {
	if(value.length<8 || value.length>16){
		return false;
	}
	//return this.optional(element) || /^\d+[A-Z]+|[A-Z]+\d+|\d+[a-z]+|[a-z]+\d+$/.test(value);
	return this.optional(element) || /^((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/.test(value);
}, "8~16位，包含数字、字母");

$.validator.addMethod("sobet_username", function(value, element) {
	if(value.length<5 || value.length>20){
		return false;
	}
	return this.optional(element) || /^[a-zA-Z_]{1}\w{4,19}$/.test(value);
}, "字母或下划线开头，5~20位，可以包含数字");

$.validator.addMethod("sobet_cardno_", function(value, element) {
	if(value.length<19 || value.length>26){
		return false;
	}else {
		return true;
	}
	return this.optional(element);
}, "银行卡号为16-21位数字");
//提款数目不能为负数
$.validator.addMethod("sobet_drawPwd", function(value, element) {
	var balanceUse = Number($("#balanceUse").text());
	if(value > balanceUse){
		return false;
	}else{
		return true;
	}
	return this.optional(element);
}, "提现金额不能大于可用余额");

$.validator.addMethod("sobet_limit_min", function(value, element) {
	var minV = Number($("#account .page-tips ul.service li em").eq(0).text());
	var maxV = Number($("#account .page-tips ul.service li em").eq(1).text());
	if(value < minV){
		return false;
	}else{
		return true;
	}
	return this.optional(element);
},"提现金额小于最小额度");

$.validator.addMethod("sobet_limit_max", function(value, element) {
	var minV = Number($("#account .page-tips ul.service li em").eq(0).text());
	var maxV = Number($("#account .page-tips ul.service li em").eq(1).text());
	if(value > maxV){
		return false;
	}else{
		return true;
	}
	return this.optional(element);
},"提现金额大于最大额度");

$.validator.addMethod("sobet_charge_min", function(value, element) {
	var minV = Number($("#eachMinM").html());
	if(value < minV){
		return false;
	}else{
		return true;
	}
	return this.optional(element);
},"充值金额不能小于单笔最小金额");

$.validator.addMethod("sobet_charge_max", function(value, element) {
	var maxV = Number($("#eachMaxM").html());
	if(value > maxV){
		return false;
	}else{
		return true;
	}
	return this.optional(element);
},"充值金额不能大于单笔最大金额");

$.validator.addMethod("sobet_chargeDay_max", function(value, element) {
	var maxV = Number($("#dayMaxM").html());
	if(value > maxV){
		return false;
	}else{
		return true;
	}
	return this.optional(element);
},"充值金额不能大于每天最高存款");

$.validator.addMethod("sobet_transM", function(value, element) {
	//小数点后最多只能输入两位
	// return this.optional(element) || /^\d+(\.\d{2})?$/.test(value);
	return this.optional(element) || /^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
}, "请输入正确的金额");

$.validator.addMethod("sobet_floatlimit", function(value, element) {
	//小数点后最多只能输入两位
	return this.optional(element) || /^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
}, "请输入正确的金额");

$.validator.addMethod("sobet_number", function(value, element) {
	//小数点后最多只能输入两位
	if(!/^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value)){
		return false;
	}else{
		return true;
	}
	return this.optional(element);
}, "请输入正确的金额");
//如果是readonly属性的验证

}));