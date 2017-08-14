<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/bootstrap.css">
	<style type="text/css">
		.container{
			height: 100px;
			background-color: aqua;
		}
		.row{
			background-color: fuchsia;
			height:80%;
		}
		.div{
			width: 200px;
		}
		.red{
			background-color: red;
		}
	</style>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">菜鸟教程</a>
		</div>
		<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">第一个</a></li>
				<li><a href="#">第二个</a></li>
				<li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Java <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">jmeter</a></li>
                    <li><a href="#">EJB</a></li>
                    <li><a href="#">Jasper Report</a></li>
                    <li class="divider"></li>
                    <li><a href="#">分离的链接</a></li>
                    <li class="divider"></li>
                    <li><a href="#">另一个分离的链接</a></li>
                </ul>
            </li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right"> 
	            <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li> 
	            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li> 
	        </ul> 
		</div>
	</div>
</nav>
<div style="display: inline;position:absolute" class="div red">
	<div class="div">标题</div>
	<div class="div" style="display: none;">1</div>
	<div class="div" style="display: none;">1</div>
</div>
<div id="main" class="container">
	<div id="h2" style="position:absolute;padding-left: 100px">
		<label>Hello World!</label>
	</div>
	<button onclick="start()" class="btn btn-primary">start</button>

	<div id="d1" class="row">动画</div>
	<div id="d2" style="background-color: gray;display: none;" class="row">动画</div>
	<div id="d3" class="row" style="background-color: green;display: none;">动画</div>
</div>
<button id="submit" class="btn btn-primary">controller</button>
<h1><a class="font" href="htmlT/Consumer.html">跳转</a></h1>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/test.js"></script>
	<script type="text/javascript" src="js/pro.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var div2 = $("#d2");var div3 = $("#d3");
		
		$("#main").hover(
				function(){
					div2.fadeIn(10);
					div3.fadeIn(10);
				},
				function(){
					div2.fadeOut(10);
					div3.fadeOut(10);
				});
		
		$("div").css("border-radius","10px");
	});
		
	function start(){
		console.info(window.document.location.href);
		$("#h2").animate({left:'+=500px',opacity:'0.99'},"slow");
		$("#h2").animate({fontSize:'+=10em'},"solw");
		$("#h2").animate({left:'-=500px',opacity:'0.99'},"slow");
		$("#h2").animate({fontSize:'-=10em'},"solw");
	}
		
	
	</script>
</html>
