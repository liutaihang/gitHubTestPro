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
foreach = function(data){
//	console.log($(".target").html("<tr><td>" +  + "</td>" +
//										"<td>"+ +"</td>" +
//										"<td>" + + "</td>" +
//										"<td>" + + "</td>" +
//										"<td>" + + "</td>" +
//									"</tr>"));
	console.log(data.data.data.length);
	console.log($(".target").html("<tr><td>type</td>" +
										"<td>name</td>" +
										"<td>habit</td>" +
										"<td>food</td>" +
										"<td>palce</td>" +
									"</tr>"));
}