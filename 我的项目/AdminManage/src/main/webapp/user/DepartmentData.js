var DepartmentData = {
    Rows: [{"DepartmentID": 1, "DepartmentName": "主席", "DepartmentRemark": null}, {
        "DepartmentID": 2,
        "DepartmentName": "研发中心",
        "DepartmentRemark": null
    }, {"DepartmentID": 3, "DepartmentName": "销售部", "DepartmentRemark": null}, {
        "DepartmentID": 4,
        "DepartmentName": "市场部",
        "DepartmentRemark": null
    }, {"DepartmentID": 5, "DepartmentName": "顾问组", "DepartmentRemark": null}], Total: 5
};

var tempUserInfo;//用户信息


/**
 * 修改密码
 */
function updatePwd(){
	if($.isBlank($("#first").val()) && $.isBlank($("#first").val())){
		$.ligerDialog.warn("密码不能为空!");
		return;
	}
	if(!isEquel("first", "second")){
		$.ligerDialog.warn("输入密码不一致，请重新输入！");
		return;
	}
	var id = tempUserInfo.id;
	var pwd = $("#second").val();
	$.ajax({
	  url: header + "user/setPwd.do",
	  type: "POST",
	  async: true,
	  data: {
	      "pwd": pwd,
	      "id" : id
	  },
	  dataType: 'json',
	  success: function (data, status) {
	      console.info(data);
	      console.info(status);
	      if(!$.isBlank(data.data)){
	    	  if(data.data == 'donot'){
	    		  $.ligerDialog.warn("不能修改当前用户密码！");
	    		  return;
	    	  }
	    	  $.ligerDialog.success(data.data);
	    	  window.location.reload();
	      }
	  },
	  error: function(data){
		  console.info(data);
	  }
	});
	console.info(data);
}

function showDiv(){
	if($.isBlank(tempUserInfo)){
     	$.ligerDialog.warn("请先选择用户");
     	return;
     }
	//让用户只能选择一行
	if(!checkRows()){
		return;
	}
     $("#oldPwd").val(tempUserInfo.password);
	document.getElementById("changeInformation").style.display = 'block';
}

function closeDiv(){
	document.getElementById("changeInformation").style.display = 'none';
}

/**
 * 判断值是否相等
 * @param {Object} id1
 * @param {Object} id2
 */
function isEquel(id1, id2){
	var temp2 = document.getElementById(id2).value;
	var temp1 = document.getElementById(id1).value;
	if(temp1 == temp2){
		return true;
	}else {
		document.getElementById(id2).value = "";
		document.getElementById(id2).focus();
		return false;
	}
	
}

function verifyPwd(){
	var temp = $("#first");
	if(!$.isBlank(temp.val())){
		var rege = /[a-zA-Z0-9]{6,}/;
		if(!rege.test(temp.val())){
			$.ligerDialog.warn("密码必须为六位数以上的数字和字母的组合！");
			temp.val("");
		}
	}
}
