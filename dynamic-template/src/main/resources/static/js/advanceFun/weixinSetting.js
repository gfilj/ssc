$(function(){
	
});
function updateWeiXin(){
	var weiXinNo = $("#weiXinNo").val();
	var weiXinSendCycle = $("#weiXinSendCycle").val();
	if(weiXinNo==null || $.trim(weiXinNo) == ""){
		alertMsg("微信帐号不能为空",function(){return;});
	}
	var pars = "weiXinNo=" + weiXinNo + "&weiXinSendCycle=" + weiXinSendCycle;
	$.ajax({
		url:"action/updateWeiXin",
		type:"POST",
		data:pars,
		dataType:"json",
		success:function(data){
			if(data.errno==0){
				alertMsg("修改成功！",function(){
					$("#tempForm").attr("action","action/saveWeixinSetting");
					$("#tempForm").submit();
				});
			}else{
				alertMsg(data.info);
			}
		},
		error: function (data, status, e)//服务器响应失败处理函数
		{
			alertMsg("服务器忙，请稍后重试");
		}
	});
}
