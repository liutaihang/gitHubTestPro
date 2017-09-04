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


A = function(name, type, habit, food, environment){
	console.log("name : " + name);
	$.ajax({
		url:"animal/add.do",
		type:"POST",
		async:true,
		data:{
			name : name,
			type : type,
			habit : habit,
			food : food,
			environment : environment
		},
		dataType:'json',
		success:function(data,status){
			console.info(data);
			console.log(status);
		}
		
	});
}

findAll = function(){
		$.ajax({
		url:"animal/findAll.do",
		type:"POST",
		async:true,
	//	data:{
	//		name : name,
	//		type : type,
	//		habit : habit,
	//		food : food,
	//		environment : environment
	//	},
		dataType:'json',
		success:function(data,status){
			console.info(data);
			console.log(status);
			foreach(data);
		}
		
	});
}

foreach = function(data){
	console.log(data != undefined && data != "");
	if(data != undefined && data != ""){
		console.log(data.data.data[0].environment);
		content = "";
		for (var int = 0; int < data.data.data.length; int++) {
			content += "<tr>" +
							"<td>" + data.data.data[int].environment + "</td>" +
							"<td>" + data.data.data[int].food + "</td>" +
							"<td>" + data.data.data[int].habit + "</td>" +
							"<td>" + data.data.data[int].name + "</td>" +
							"<td>" + data.data.data[int].type + "</td>" +
						"</tr>"
		};
		$(".target").html(content);
	}
}