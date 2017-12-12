var header = window.document.location.href;
header = header.substring(0,header.indexOf(header.split("/", 5)[4], 1));
var allPage, tempNum;
var tempVar;
(function($){
	  $.isBlank = function(obj){
	    return(!obj || $.trim(obj) === "");
	  };
	})(jQuery);
function jurisdiction() {
	var upordel = document.getElementById("updordel");
	
    document.getElementById("choice").style.display = "block";
    //var phone = document.getElementById("pho");
    //var realName = document.getElementById("name");
    //phone.readOnly  = false;
    //realName.readOnly = false;
    //phone.value = "";
    //realName.value = "";
}

function closeChoice() {
    document.getElementById("choice").style.display = "none";
}

function closeSet() {
    document.getElementById("popup").style.display = "none";
}

function jurisdiction_1(rowData) {
	var upordel = document.getElementById("updordel");
	if(upordel){
		upordel.setAttribute("value", "UPDATE_WHITE");
	}
    document.getElementById("choice").style.display = "block";
    var phone = document.getElementById("pho");
    var realName = document.getElementById("name");
    var status = document.getElementById("Status");
    var userId = document.getElementById("userId");
    var pho = phone.value = rowData.loginName;
    var name = realName.value = rowData.userRealName;
    var data_1 = status.value = rowData.rsrvStr2;
    userId.value = rowData.userId;
    phone.readOnly  = true;
    realName.readOnly = true;

}

function submit(){
	$.ajax({
		url:header + "resource/add.do",
		type:"POST",
		async:true,
		data:{
			"description":$("input[name='description']").val(),
			"url":$("input[name='url']").val(),
			"remark":$("input[name='remark']").val()
		},
		dataType:'json',
		success:function(data,status){
			closeChoice();
			console.info(data);
			console.info(status);
			window.location.reload();
		}
	});
}
function authSubmit(){
	$.ajax({
		url:header + "user/add.do",
		type:"POST",
		async:true,
		data:{
			"nickName":$("input[name='nickname']").val(),
			"userName":$("input[name='account']").val(),
			"state":$("input[name='other']").val()
		},
		dataType:'json',
		success:function(data,status){
			console.info(data);
			console.info(status);
			window.location.reload();
		}
	});
}
function del(ids,url){
	$.ajax({
		url:header + url,
		type:"POST",
		data:{ids : ids},
		dataType:'json',
		success:function(data,status){
			console.info(data.data);
			init(data.data);
			window.location.reload();
			alert(data.data);
		}
	}); 
}

function dele(data,url){
	$.ajax({
		url:header + url,
		type:"POST",
		data:data,
		dataType:'json',
		success:function(data,status){
			console.info(data.data);
			init(data.data);
			window.location.reload();
			alert(data.data);
		}
	}); 
}
function roleSubmit(){
	console.info($("input[name='name']").val());
	$.ajax({
		url:header + "role/add.do",
		type:"POST",
		async:true,
		data:{
			"nickName":$("input[name='name']").val(),
			"roleName":$("input[name='userName']").val()
		},
		dataType:'json',
		success:function(data,status){
			console.info(data);
			console.info(status);
			window.location.reload();
		},
		error:function(data){
			console.info(data);
			window.location.reload();
		}
	});
}

function SuccessAlert(e) {
    var index = layer.alert(e, {
        //icon: 5,
        time: 2000,
        offset: 't',
        closeBtn: 0,
        title: '提示信息',
        btn: [],
        anim: 2,
        shade: 0
    });
    layer.style(index, {
        color: '#00CD66'
    });
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function judgeTime(acceptDateStart, acceptDateEnd){
	 if($.isBlank(acceptDateStart) && !$.isBlank(acceptDateEnd)){
		 ErroAlert("请输入开始日期！");
		 return true;
	 }
	 if($.isBlank(acceptDateEnd) && !$.isBlank(acceptDateStart)){
		 ErroAlert("请输入截止日期！");
		 return true;
	 }
	 
    if(!$.isBlank(acceptDateEnd) && !$.isBlank(acceptDateStart)){
   	 var stim = new Date(acceptDateStart).getTime();
   	 var etim = new Date(acceptDateEnd).getTime();
   	 var nowTim = new Date().getTime();
   	 console.info(acceptDateStart + " ==> " + stim);
        console.info(acceptDateEnd + " ==> " + etim);
        console.info(acceptDateStart.split(" ", 1)[0]);
		 if(etim > nowTim){
   		 ErroAlert("截止日期不能在当前日期之后！");
   		 return true;
        }
        if(etim < stim){
   		 ErroAlert("截止日期不能在开始日期之前！");
       	 return true;
        }
    }
}

function refre(){
	ScriptManager.RegisterStartupScript(this,this.GetType(),"test","alert('成功！');",true);
	window.location.reload();
}

//验证时间格式
function isTime(time){
	var pattern = /^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$/;
	return pattern.test(time);
}

//验证身份证 
function isCardNo(card) { 
 var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
 return pattern.test(card); 
} 

// 验证手机号
function isPhoneNo(phone) { 
 var pattern = /^1[34578]\d{9}$/; 
 return pattern.test(phone); 
}

// 验证中文名称
function isChinaName(name) {
	var pattern = /^[\u4E00-\u9FA5]{1,6}$/;
	return pattern.test(name);
} 


function getTopWinow() {
    var p = window;
    while (p != p.parent) {
        p = p.parent;
    }
    return p;
}

function downLoadExcel(data){
	console.info(data);
    var ser_url = "http://58.42.236.252:7444/AdminManage/excel/";
    var local_url = "http://localhost:8080/AdminManage/excel/";
    var baidu_url = "http://180.76.171.32:8089/AdminManage/excel/";
    if(!$.isBlank(data.data.url)){
 	   $("#hidden").attr("href", local_url + data.data.url);
 	   console.info(local_url + data.data.url);
 	   console.info($("#hidden"));
 	   $("#hidden").attr("download", data.data.url);
 	  document.getElementById("hidden").click();
    }
    SuccessAlert(data.data.msg);
}

