$(function(){
	initMap();
	if($("#deviceList ul li").length>0){
		$("#deviceList").jCarouselLite({
			btnNext: "#subNavNext",
			btnPrev: "#subNavPrev",
			circular:false,
			visible:6
		});
	}else{
		$(".noDevice").show();
		$(".deviceListContainer").hide();
	}
	$("#btnShowUpdateFirmware").click(function(e){
		e.preventDefault();
		$("#fieldsetFirmwareUpdateForm").show();
		$("#fieldsetFirmwareUpdate").hide();
	});
	
	$(".deviceItem").click(function(){
		$(".deviceItem").removeClass("selected");
		var deviceId=$(this).data("deviceid");
		$(this).addClass("selected");
		getDeviceInfo(deviceId);
	});
	/* 设置模板状态 */
	$("#templateSwitch").click(function(){
		var id=$(".deviceItem.selected").data("deviceid");
		var iOpenTemplate=$(this).data("iOpenTemplate");
		var sMacName=$("#sMacName").val();
		$.ajax({
			url:"action/updateDeviceOtherInfo",
			type:"POST",
			data:{
				id:id,
				iOpenTemplate:iOpenTemplate,
				sMacName:sMacName
			},
			dataType:"JSON",
			success:function(data){
				if(data.errno==0){
					getDeviceInfo(id);
				}else{
					alertMsg(data.errMsg);
				}
			}
		});
	});
	
	//初始隐藏设置时长表单
	$("#displaySessionTime").show();
	$("#setSessionTime").hide();
	//显示设置免费时长表单
	$("#openSetOpenSessionDiv").click(function(){
		$("#displaySessionTime").hide();
		$("#setSessionTime").show();
	});
	$("#btnSetOpenSession").click(function(){
		setSessiongTime();
	});
	//默认显示第一个设备的信息
	$(".deviceItem:first").click();
	 $('.form_datetime').datetimepicker({
        language:'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
    }).on('hide', function(ev){
    	$('.form_datetime').data("timestamp",ev.date.valueOf()-8*60*60*1000);	//ev.date比选择的时间多出8小时
    });
    //重启路由
    $("#btnResetRouter").click(function(){
    	var id=$(".deviceItem.selected").data("deviceid");
    	var sUserName=$("#sUserName").val();
    	$.ajax({
			url:"action/deviceRestart",
			type:"POST",
			data:{
				sUserName:sUserName
			},
			dataType:"JSON",
			success:function(data){
				if(data.errno==0){
					getDeviceInfo(id);
				}else{
					alertMsg(data.errMsg);
				}
			}
		});
    });
    //取消设备升级表单
    $("#btnCancelUpdateFirmware").click(function(e){
    	e.preventDefault();
		$("#fieldsetFirmwareUpdateForm").hide();
		$("#fieldsetFirmwareUpdate").show();
    });
    //设备升级
    $("#btnUpdateFirmware").click(function(e){
    	e.preventDefault();
    	var id=$(".deviceItem.selected").data("deviceid");
    	var updateTime=$('.form_datetime').data("timestamp");
    	var sUserName=$("#sUserName").val();
    	var sModel=$("#sModel").val();
    	var newFirmwareVer=$("#newFirmwareVer").val();
    	if(!updateTime){
    		$("#selectUpdateTime-text-help").text("请填写升级时间。").css("color","red");
    		return;
    	}else{
    		$("#selectUpdateTime-text-help").css("color","inherit")
    	}
    	//计算间隔时间，单位：分钟
    	var intervalMinutes =(updateTime-new Date().getTime())/1000/60;
    	
    	if(intervalMinutes<30){
    		$("#selectUpdateTime-text-help").text("升级时间必须大于当前时间30分钟。 ").css("color","red");
    		return;
    	}else{
    		$("#selectUpdateTime-text-help").css("color","inherit")
    	}
    	updateTime=new Date(updateTime);
    	var updateTimeTxt=$("#dtp_updateTime").val();
    	$.ajax({
			url:"action/upgradeFirmware",
			type:"POST",
			data:{
				sDateUpdate:updateTime.getMinutes()+":"+updateTime.getHours(),
				sUserName:sUserName,
				sModel:sModel,
				sUpdateVer:newFirmwareVer,
				dExpireTime:updateTimeTxt
			},
			dataType:"JSON",
			success:function(data){
				if(data.errno==0){
					alertMsg("升级指令下发成功，稍后设备会自动升级！",function(){
						getDeviceInfo(id);
					});
				}else{
					alertMsg(data.errMsg);
				}
			}
		});
    });
	$("#address").keypress(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
    	var key = e.which; //e.which是按键的值
    	if (key == 13) {
        	Address2Geocoder();
    	}
	});
	//取消按钮按下刷新界面
	$("#btnResetForm").off("click");
	$("#btnResetForm").click(function(e){
		e.preventDefault();
		var deviceId=$(".deviceItem.selected").data("deviceid");
		getDeviceInfo(deviceId)
	});
});
/* 获取设备信息 */
function getDeviceInfo(deviceId){
	$.ajax({
			url:"action/getDeviceInfo",
			type:"POST",
			data:{id:deviceId},
			dataType:"JSON",
			success:function(data){
				$(".sNickName").text(data.sNickName);
				$("#sNickName").val(data.sNickName);
				$("#sAddress").val(data.sAddress);
				$("#address").val(data.sAddress);
				$("#fLongitude").val(data.fLongitude);
				$("#fLatitude").val(data.fLatitude);
				$("#iSessionTime").text((data.iSessionTime/60).toFixed(0));
				$("#sMacName").val(data.sMacName);
				$("#txtSessiongTime").val((data.iSessionTime/60).toFixed(0));
				$("#sUserName").val(data.sUserName);
				$("#sModel").val(data.sModel);
				$("#sMacName").val(data.sMacName);
				Address2Geocoder();
				$("#fieldsetFirmwareUpdate").show();
				$("#fieldsetFirmwareUpdateForm").hide();
	
				if(data.sModel==""||data.sModel==false||data.iStatus==""){
					$("#rejectUpdate").show().text("设备未初始化，暂不支持升级。");
					$("#allowUpdate").hide();
				}else{
					if(data.newFirmwareVer.indexOf(data.sCurVer)>=0){
						$("#rejectUpdate").show().text("固件已经是最新版本。");
						$("#allowUpdate").hide();
					}else{
						var sCurVarNo=data.sCurVer.split(".");
						var newFirmwareVerNo=data.newFirmwareVer.split("-")[0].split(".");
						var isFallback=false;
						for(var i=0;i<sCurVarNo.length;i++){
							if(sCurVarNo[i]>newFirmwareVerNo[i]){
								isFallback=true;
							}
						}
						if(data.iStatus==0){
							$("#rejectUpdate").hide().text();
							$("#allowUpdate").show();
						}else if(data.iStatus==1||data.iStatus==2){
							$("#rejectUpdate").show().text("已下发升级指令，请勿关机！");
							$("#allowUpdate").hide();
						}else if(data.iStatus==3){
							$("#rejectUpdate").show().text("固件已经是最新版本。");
							$("#allowUpdate").hide();
						}else{
							$("#rejectUpdate").show().text("老版本暂不提供升级功能！");
							$("#allowUpdate").hide();
						}
						if(isFallback){
							$("#rejectUpdate").show().text("固件已经是最新版本。");
							$("#allowUpdate").hide();
						}
					}
				
				}
				if(data.iOpenTemplate==0){
					$("#iOpenTemplate").text("开启");
					$("#templateSwitch").data("iOpenTemplate",-1).text("点击关闭");
				}else{
					$("#iOpenTemplate").text("关闭");
					$("#templateSwitch").data("iOpenTemplate",0).text("点击开启");
				}
				if(data.restartStatus=="1"){
					$("#restartStatus").text("重启初始化中");
					$("#btnResetRouter").hide();
				}else if(data.restartStatus=="2"){
					$("#restartStatus").text("重启指令已下发");
					$("#btnResetRouter").hide();
				}else{
					$("#restartStatus").text("正常");
					$("#btnResetRouter").show();
				}
				$("#sCurVer").text(data.sCurVer);
				$(".newFirmwareVer").text(data.newFirmwareVer);
				$("#newFirmwareVer").val(data.newFirmwareVer);
			}
		});
}
/* 设置免费时长 */
function setSessiongTime(){
	var id=$(".deviceItem.selected").data("deviceid");
		var iSessionTime=$("#txtSessiongTime").val();
		var sMacName=$("#sMacName").val();
		if(!checkTextExist("txtSessiongTime","免费时长不能为空",true)){
			return false;
		}
		if(!checkTextLength("txtSessiongTime",7,"免费时长不能超过7位数",true)){
			return false;
		}
		$.ajax({
			url:"action/updateDeviceOtherInfo",
			type:"POST",
			data:{
				id:id,
				iSessionTime:iSessionTime,
				sMacName:sMacName
			},
			dataType:"JSON",
			success:function(data){
				if(data.errno==0){
					getDeviceInfo(id);
					$("#displaySessionTime").show();
					$("#setSessionTime").hide();
				}else{
					alertMsg(data.errMsg);
				}
			}
		});
}
function updateDeviceInfo(){
//	alert(1);
	var id=$(".deviceItem.selected").data("deviceid");
	var sNickName = $("#sNickName").val();
	var realLength = getStrLength(sNickName);
//	if(realLength>32){
//		alertMsg("请输入小于8个汉字或者小于16个其他字符");
//		return false;
//	}
	var sAddress;
	if(pdd!= $("#address").val()){
		sAddress = $("#address").val();
	}else{
		sAddress = $("#sAddress").val();
	}
	var fLongitude = $("#fLongitude").val();
	var fLatitude = $("#fLatitude").val();
	var sUserName = $("#sUserName").val();
	var sMacName = $("#sMacName").val();
	$.ajax({
		url:"action/updateDeviceInfo",
		type:"POST",
		data:{
			id:id,
			sNickName:sNickName,
			sAddress:sAddress,
			fLongitude:fLongitude,
			fLatitude:fLatitude,
			sUserName:sUserName,
			sMacName:sMacName
		},
		dataType:"json",
		success:function(data){
			if(data.errno==0){
				alertMsg("设备信息修改成功！",function(){
					$("#tempForm").attr("action","action/getDeviceList");
					$("#tempForm").submit();
				});
			}else{
				alertMsg(data.info);
			}
		}
	});
}
/** 地图  */
var map;
var marker;
var iZoom = 15; //放大级别
var  pdd;
function initMap(){
	map = new BMap.Map("map"); // 创建地图实例
	marker = null;
	iZoom = 15; //放大级别
	var point = new BMap.Point(116.404, 39.915); // 创建点坐标
	map.centerAndZoom(point, iZoom);	// 初始化地图，设置中心点坐标和地图级别
	map.enableScrollWheelZoom();	//启用滚轮放大缩小
	map.addControl(new BMap.NavigationControl());
	map.addControl(new BMap.OverviewMapControl());
	map.addControl(new BMap.MapTypeControl());
	map.addEventListener("click", function(e){  
		setMarker(e.point.lng, e.point.lat);
	}); 
}
/** 
 * 显示经纬度
 */
function showPoint(flng, flat){
	setPoint(flng, flat);
	Geocoder2Address(flng, flat);
};

function setPoint(flng, flat){
	$("#fLongitude").val(flng);
	$("#fLatitude").val(flat);
	
};
/* 
 * 根据坐标点方向解析地址
 */
function Geocoder2Address(flng, flat){
	if(flng == null || flng == '' || flat == null || flat == ''){
		return;
	}
	var myGeo = new BMap.Geocoder();    
	// 根据坐标得到地址描述
	myGeo.getLocation(new BMap.Point(flng, flat), function(result){    
		pdd=result.address;
		if (result){    
		  $("#address").val(result.address); 
		  $("#sAddress").val(result.address);
		}
	});
};

/*
 * 根据地址方向解析为坐标点
 */
function Address2Geocoder(){
	var sAddress = $("#address").val();
	// 创建地址解析器实例 
	var myGeo = new BMap.Geocoder();  
	// 将地址解析结果显示在地图上，并调整地图视野
	myGeo.getPoint(sAddress, function(point){  
	 if (point) {  
	 	setMarkerNotChangeAddress(point.lng, point.lat);
//	 	setPoint(point.lng, point.lat);
	 	$("#address").val(sAddress);
	 	$("#sAddress").val(sAddress);
	 }  
	}, "");  
};

/*
 * 在地图上生成对应的标记点
 */
function setMarkerNotChangeAddress(flng, flat){
	map.removeOverlay(marker); 
	var fPoint = new BMap.Point(flng, flat);
	//showPoint(flng, flat);
	marker = new BMap.Marker(fPoint);
	marker.enableDragging();
	//拖动事件
	marker.addEventListener("dragend", function(e){
		setMarker(e.point.lng, e.point.lat);
	}); 
	map.addOverlay(marker);
	map.centerAndZoom(fPoint, iZoom);
};
/*
 * 在地图上生成对应的标记点
 */
function setMarker(flng, flat){
	map.removeOverlay(marker); 
	var fPoint = new BMap.Point(flng, flat);
	showPoint(flng, flat);
	marker = new BMap.Marker(fPoint);
	marker.enableDragging();
	//拖动事件
	marker.addEventListener("dragend", function(e){
		setMarker(e.point.lng, e.point.lat);
	}); 
	map.addOverlay(marker);
	map.centerAndZoom(fPoint, iZoom);
};
//获取字符串实际长度
function getStrLength(str){
	var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128){
        	realLength += 1;
        }else{
        	realLength += 2;
        }
    }
    return realLength;
}