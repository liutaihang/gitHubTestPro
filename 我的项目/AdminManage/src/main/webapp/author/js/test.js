(function($){
  $.isBlank = function(obj){
    return(!obj || $.trim(obj) === "");
  };
})(jQuery);

count = function (mas, mas1){
	var num = mas + mas1;
	return num;
}

clik = function (content){
	$("input[name='type']").val(name);
	console.log("name:" + type);
}

function al(log){
	var text = $("textarea[cols]").html();
	alert(text + "---" + log);
}
test = function(){
	console.log(
			"name:[" + name + "]-|type:[" + name + "]-|habit:[" + habit + "]-|food:[" + food + "]-|place:[" + environment + "]"
	);
}


A = function(url, permission, description){
	var header = window.document.location.href;
	console.info(header.indexOf("/", 4));
	header = header.substring(0,header.indexOf(header.split("/", 5)[4], 1));
	var pathname = window.document.location.pathname;
	console.log(header + "\n" + pathname);
	$.ajax({
		url:header + "auth/add.do",
		type:"POST",
		async:true,
		data:{
			"url" : url,
			"remark" : permission,
			"description" : description
		},
		dataType:'json',
		success:function(data,status){
			console.info(data);
			$("textarea[name='url']").val("");
			$("textarea[name='permission']").val("");
			$("textarea[name='description']").val("");
//			$("input[name='food']").val("");
//			$("input[name='place']").val("");
			console.log(status);
		},
		error:function(data){
			console.info(data);
		}
		
	});
};

findAll = function(){
	var header = window.document.location.href;
	var pathname = window.document.location.pathname;
	header = header.substring(0,header.indexOf(header.split("/", 5)[4], 1));
	console.log(header + "\n" + pathname);
		$.ajax({
		url:header + "auth/findAll.do",
		type:"GET",
		async:true,
		dataType:'json',
		success:function(data,status){
			console.info(data.data.length);
			console.log(status);
			foreach(data.data);
		}
	});
};

foreach = function(data){
	console.log(data != undefined && data != "");
	if(data != undefined && data != ""){
		content = "";
		for (var int = 0; int < data.length; int++) {
			content += "<tr>" +
							"<td>" + data[int].description + "</td>" +
							"<td>" + data[int].remark + "</td>" +
							"<td>" + data[int].url + "</td>" +
						"</tr>"
		};
		$(".target").html(content);
	}
};