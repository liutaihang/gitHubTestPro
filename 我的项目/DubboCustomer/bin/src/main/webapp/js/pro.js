(function($){
  $.isBlank = function(obj){
    return(!obj || $.trim(obj) === "");
  };
})(jQuery);

$(document).ready(function(){
	var button = $("#submit");
	button.click(function(){
		var header = window.document.location.href;
		var pathname = window.document.location.pathname;
		var num = header.indexOf(pathname);
		console.info("0.0 " + header + "\n" + pathname + "\n" + num + "  " + header.substring(num+1));
		var url = 'people/tests.do';
		console.info(url);
		$.ajax({
		    url:url,
		    type:'GET', //GET
		    async:true,    //或false,是否异步
		    data:{
		        "name":"name1","type":"朝阳","price":18
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
//		    cache:false,
		   /* beforeSend:function(xhr){
		        console.log(xhr)
		        console.log('发送前')
		    },*/
		    success:function(data,status){
		    	console.log('发送后');
		    	console.log(data,status);
		    	console.log(data.name);
		    	$(".font").html(data.name);
		    }
		   /* error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }*/
		})
	});
});