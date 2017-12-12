var orderId; //订单id
var resId;	 //代办商id
var tempDate;//开始时间
var line; //行数

/**
 * 日志查询
 */
function SearchQuery(){
	orderId = document.getElementById("orderId").value;
	resId = document.getElementById("resId").value;
	tempDate = document.getElementById("searchDate").value;
	if(isOk()){
		return;
	}
	$("#logData").html(tempDate + resId + orderId);
	var uri = header + "error/errorlog.do";
	$.ajax({
		url:uri,
		type:"POST",
		async:true,
		data:{
			"orderId" : orderId,
			"resId" : resId,
			"tempDate" : tempDate
		},
		success: function(data,status){
			if($.isBlank(data)){
				$("#logData").html("暂无数据！");
				return;
			}
			$("#logData").html(data);
		}
	});
	
}

var isOk = function verify(){
	var searchDate = $("#searchDate");
	var tempOrderId = $("#orderId");
	if($("#searchDate").val().length != 0 && 
			isTime($("#searchDate").val()) == false){
		str = '日期格式不正确';
		$("#searchDate").val("");
		searchDate.attr("placeholder", str);
		searchDate.attr("class", "laydate-icon invalid");
		   $('#searchDate').focus();
		   return true;
	}
	if(!$.isBlank(searchDate.val())){
		var nowDate = new Date().getTime();
		var searchTemp = new Date(searchDate.val()).getTime();
		if(nowDate < searchTemp){
			str = '不能大于当前时间';
			$("#searchDate").val("");
			searchDate.attr("placeholder", str);
			searchDate.attr("class", "laydate-icon invalid");
			return true;
		}
	}
	var pattern = /[a-zA-Z]{2}\d{16}/;
	if(!$.isBlank(tempOrderId.val())){
		if(!pattern.test(tempOrderId.val())){
			str = '订单编号输入错误';
			tempOrderId.val("");
			tempOrderId.attr("placeholder", str);
			tempOrderId.attr("class", "invalid");
			return true;
		}
	}
	return false;
}

