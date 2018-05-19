$(function(){
	tmplItemVisibleSize=4;
	var  templName=$("#templateName").val();
	var $templListItem=$("#tmplList .list>ul>li");
	var templItem=$templListItem.find(".list-item").filter(function(index){
		return $(this).data("templname")==templName;
	});
	
	var templIndex=templItem.closest("li").index();
	//如果没有找到对应的模版，当前使用的模版已经不可以再次选择
	//在模版列表里添加一个模版项，显示当前选择的模版
	if(templIndex==-1){
		templIndex=0;
		var templTitle="当前使用模版";
		//复制第一个模版
		var $tempLi=$templListItem.eq(0).clone();
		var $tempImg=$tempLi.find(".list-item>img");
		var cloneTemplName=$tempLi.find(".list-item").data("templname");
		//更改图片路径和模版名称
		var templImgSrc=$tempImg.attr("src");
		templImgSrc=templImgSrc.replace(new RegExp(cloneTemplName,'g'),templName);
		$tempImg.attr("src",templImgSrc);
		$tempLi.find("#divTemplname>label").attr("title",templTitle).text(templTitle);
		$tempLi.find(".list-item")
				.data("templtitle",templTitle)
				//设置templname
				.data("templname",templName);
		//把复制的模版插入到模版列表第一个
		$templListItem.eq(0).before($tempLi);
		templItem=$tempLi;
	}
	//如果当前模版为模版列表后几个
	if(templIndex>=$templListItem.length-tmplItemVisibleSize){
		templIndex=$templListItem.length-tmplItemVisibleSize;
	}
	templItem.closest("li").find("input.templateName").attr("checked","checked");
	selectTmpl(templItem.closest("li"));
	
	$("#tmplList .list input.templateName").change(function(){
		if($(this).is(":checked")){
			selectTmpl($(this).closest("li"));
		}
	});
	
	$("#tmplList .list").jCarouselLite({
		btnNext: "#subNavNext",
		btnPrev: "#subNavPrev",
		circular:false,
		visible:tmplItemVisibleSize, 
		start:templIndex,
		scroll:1,
		speed: 100,
		afterEnd:function(lis){
			if(lis&&lis[0]){
				setTmplNavBtnEnabel($(lis[0]));
			}
		}
	});
	//当前选中的模板项
	var selectTmplLi=$("#tmplList .list ul li:eq("+templIndex+")");
	//显示选中的模板名称templtitle
	var templtitle=selectTmplLi.find(".list-item").data("templtitle");
	setTmplNavBtnEnabel(selectTmplLi);
//	$("#divTemplname label").text(templtitle);

	//获取微信登陆方式的checkbox
	var weixinCategory=$("#loginType .loginCategoryItem").filter(function(index){
		return $(this).find(".chkCategoryItem").data("authname").indexOf("weixin")>=0;
	});
	weixinCategory.find(".chkCategoryItem").change(function(){
		setWeixinSettingVisible($(this));
	});
	setWeixinSettingVisible(weixinCategory.find(".chkCategoryItem"));
});
function selectTmpl($li){
	$("#tmplImgDiv img").attr("src",$li.find("img").attr("src"));
	var templName=$li.find(".list-item").data("templname");
	var templTitle=$li.find(".list-item").data("templtitle");
	var templNote=$li.find(".list-item").data("templnote");
	var templQQ = $li.find(".list-item").data("qq");
	var templWX = $li.find(".list-item").data("weixin");
	var templWB = $li.find(".list-item").data("sina");
	var templMobile = $li.find(".list-item").data("mobile");
	var templType = $li.find(".list-item").data("type");
	$(".list-item").removeClass("selected");
	$li.find(".list-item").addClass("selected");
	$("#templateName").val(templName);
	$("#templateNote").text(templNote);
	if(templQQ == 0){
		$("#auth-login-qq").show();
	}else{
		$("#auth-login-qq").hide();
	}
	
	if(templWX == 0){
		$("#auth-login-weixin").show();
	}else{
		$("#auth-login-weixin").hide();
	}
	
	if(templWB == 0){
		$("#auth-login-sina").show();
	}else{
		$("#auth-login-sina").hide();
	}
	
	if(templMobile == 0){
		$("#auth-login-mobile").show();
	}else{
		$("#auth-login-mobile").hide();
	}
	if(templType == 4){
		$("#setting").hide("100");
		$("#gointemplateEdit").show("100");
	}else if($("#setting").is(":hidden")){
		$("#gointemplateEdit").hide("100");
		$("#setting").show("100");
	}
}
/**
 * 设置模板列表的导航按钮是否可用
 * @param li 当前的模板项
 */
function setTmplNavBtnEnabel(li){
	
	if(li.is(":first-child")){
		$(".templListContainer .subNavPrev").addClass("disableNavPrev");
	}else{
		$(".templListContainer .subNavPrev").removeClass("disableNavPrev");
	}
	if(li.index()+tmplItemVisibleSize==li.parent().find("li").length){
		$(".templListContainer .subNavNext").addClass("disableNavNext");
	}else{
		$(".templListContainer .subNavNext").removeClass("disableNavNext");
	}
}
/**
 * 微信设置界面是否显示
 * @param $weixinCategory
 */
function setWeixinSettingVisible($weixinCategory){
	if($weixinCategory.is(":checked")){
		$("#weixinSetting").show().find("input,select").attr("disabled",false);
	}else{
		$("#weixinSetting").hide().find("input,select").attr("disabled",true);
	}
}
function updateAgent(){
	
	if($("#templateName").val()==""){
		alertMsg("请选择模版");
		return;
	}
	
	var time = $("#countDown").val();
	if(time == null || time == ""){
		alertMsg("倒计时不能为空");
		$("#countDown").focus();
		return;
	}else{
		var reg = /^\d+$/;
		if (!reg.test(time)){
			alertMsg("倒计时格式不对，请填写合法的数字");
			$("#countDown").focus();return;
		}
		if(time<0){
			alertMsg("倒计时不能小于0");
			$("#countDown").focus();
			return;
		}
		if(parseInt(time)>999){
			alert("不能超过999秒");
			$("#countDown").focus();
			return;
		}
	}
	
	var url = $("#url").val();
	if(url != null && url != ""){
		if(!url.StartWith("http")){
			alertMsg("指向url必须以'http://'开头");
			return;
		}
	}
	
	var weiXinNo,weiXinSendCycle;
	if($("#weixinSetting").is(":visible")){
		weiXinNo = $("#weiXinNo").val();
		weiXinSendCycle = $("#weiXinSendCycle").val();
		if(weiXinNo==null || $.trim(weiXinNo) == ""){
			alertMsg("微信帐号不能为空");
			return;
		}
	}
	
	var lcs = "";
	$("input[name='sLoginCategory']:checked").each(function(){
		lcs += $(this).val() + ",";
	});
	if(lcs.length > 0){
		lcs = lcs.substring(0,lcs.length-1);
		$("#loginCategory").val(lcs);
	}else{
		/**
		 * 当非一键登录时判断必须选择一个登陆方式，否则选择一键登录
		 */
		var iSeleOpenQuickLogin = $("#seleOpenQuickLogin").val();
		if(iSeleOpenQuickLogin == '-1'){
			alertMsg("必须选择一种认证方式");
			return;
		}
	}
	var count = $("#loganCount").html();
	if(count > 1000){
		alertMsg("快速链接字数超过限制，最多不能超过1000个字符");
		return;
	}
	var pars = $("#updateAgentForm").serialize();
	$.ajax({
		url:"action/updateUserAgent",
		type:"POST",
		data:pars,
		dataType:"json",
		success:function(data){
			if(data.errno==0){
				showHtmlModal("",$("#SaveResultDiv").html(),"SaveResultContainer",{
					onCancel:function(){
						location.reload();
					}
				})
			}else{
				alertMsg(data.info);
			}
		}
	});
}