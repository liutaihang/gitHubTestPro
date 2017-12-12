<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String path = request.getContextPath();
// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
    String url = "http://58.42.236.252:7444/AdminManage/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>归集通后台管理系统</title>
    <link href="css/default.css" rel="stylesheet"/>
    <!--必要样式-->
    <link href="css/styles.css" rel="stylesheet"/>
    <link href="css/demo.css" rel="stylesheet"/>
    <link href="css/loaders.css" rel="stylesheet"/>
    <link href="layui/css/layui.css" rel="stylesheet"/>
    <link rel="icon" href="img/head.png">
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script src="HttpUtil.js" type="text/javascript"></script>

    <script type="text/javascript">
        function getTopWinow() {
            var p = window;
            while (p != p.parent) {
                p = p.parent;
            }
            return p;
        }

        var top = getTopWinow();
        if (window != top) {
            top.location.href = location.href;
        }
    </script>
</head>
<body>
<div class='login'>
    <div class='login_title'>
        <span>管理员登录</span>
    </div>
    <form action="<%=basePath %>user/login.do" method="post" id="form">

        <div class='login_fields'>
            <div class='login_fields__user'>
                <div class='icon'>
                    <img alt="" src='img/user_icon_copy.png'>
                </div>
                <input name="userName" placeholder='用户名' maxlength="16" type='text' autocomplete="off" value=""/>

                <div class='validation'>
                    <img alt="" src='img/tick.png'>
                </div>
            </div>
            <div class='login_fields__password'>
                <div class='icon'>
                    <img alt="" src='img/lock_icon_copy.png'>
                </div>
                <input name="password" placeholder='密码' maxlength="16" type='text' autocomplete="off">
                <div class='validation'>
                    <img alt="" src='img/tick.png'>
                </div>
            </div>
            <div class='login_fields__password'>
                <div class='icon'>
                    <img alt="" src='img/key.png'>
                </div>

                <input name="code" placeholder='验证码' maxlength="4" type='text' autocomplete="off">

                <div class='validation' style="opacity: 1; right: -5px;top: -3px;">
                    <canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
                </div>
            </div>

            <div class='login_fields__submit'>
                <input id="button" type='submit' onclick="return false" value='登录'>
            </div>
            <div style="margin-top: 40px;margin-left: 20px">
                <font id="error" color="red">${errorMsg }</font>
            </div>
        </div>
    </form>

    <div class='success'>
    </div>
    <div class='disclaimer'>
        <p>欢迎登陆归集通管理系统</p>
    </div>
</div>
<div class='authent'>
    <div class="loader" style=";height: 44px;width: 44px;margin-left: 28px;">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <p>认证中...</p>
</div>
<div class="OverWindows"></div>
<script type="text/javascript" src='js/stopExecutionOnTimeout.js?t=1'></script>
<script src="layui/layui.js" type="text/javascript"></script>
<script src="js/Particleground.js" type="text/javascript"></script>
<script src="js/Treatment.js" type="text/javascript"></script>
<script src="js/jquery.mockjax.js" type="text/javascript"></script>
<script type="text/javascript">
    var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持
    var ajaxmockjax = 1;//是否启用虚拟Ajax的请求响 0 不启用 1 启用
    //账号密码
    var truelogin = "";
    var truepwd = "";
    //    window.onload = function loadi() {
    //        //top.window;
    //    }

    var CodeVal = 0;
    (function ($) {
        $.isBlank = function (obj) {
            return (!obj || $.trim(obj) === "");
        };
    })(jQuery);
    Code();

    function Code() {
        if (canGetCookie == 1) {
            createCode("AdminCode");
            var AdminCode = getCookieValue("AdminCode");
            showCheck(AdminCode);
        } else {
            showCheck(createCode(""));
        }
    }

    function showCheck(a) {
        CodeVal = a;
        var c = document.getElementById("myCanvas");
        var ctx = c.getContext("2d");
        ctx.clearRect(0, 0, 1000, 1000);
        ctx.font = "80px 'Hiragino Sans GB'";
        ctx.fillStyle = "#E8DFE8";
        ctx.fillText(a, 0, 100);
    }

    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            $('input[type="submit"]').click();
        }
    });
    //粒子背景特效
   /*  $('body').particleground({
        dotColor: '#E8DFE8',
        lineColor: '#133b88'
    }); */
    $('input[name="password"]').focus(function () {
        $(this).attr('type', 'password');
    });
    $('input[type="text"]').focus(function () {
        $(this).prev().animate({'opacity': '1'}, 200);
    });
    $('input[type="text"],input[type="password"]').blur(function () {
        $(this).prev().animate({'opacity': '.5'}, 200);
    });
    $('input[name="userName"],input[name="password"]').keyup(function () {
        var Len = $(this).val().length;
        if (!$(this).val() == '' && Len >= 5) {
            $(this).next().animate({
                'opacity': '1',
                'right': '30'
            }, 200);
        } else {
            $(this).next().animate({
                'opacity': '0',
                'right': '20'
            }, 200);
        }
    });
    //非空验证---验证码
    /* var open = 0;
    document.querySelector('#button').addEventListener('click',function(){
    var button=this;
    if(code == '' || code.length != 4){
    button.setAttribute('disabled',true);
    }
    },false); */
    var errorInfo = document.getElementById("error");
    var button = document.querySelector('#button');
    button.onclick = function (even) {
        var login = $('input[name="userName"]').val();
        var pwd = $('input[name="password"]').val();
        var code = $('input[name="code"]').val();
        var JsonData = {login: login, pwd: pwd};
        code = "" + code;
        CodeVal = "" + CodeVal;
        if (login == '') {
            //ErroAlert('请输入您的账号');
            errorInfo.innerHTML = "请输入您的账号";
            return false;
        } else if (pwd == '') {
            //ErroAlert('请输入密码');
            errorInfo.innerHTML = "请输入密码";
            return false;
        } else if (code == '' || code.length != 4) {
            errorInfo.innerHTML = "请输入验证码";
            return false;
        } else if (code.toUpperCase() != CodeVal.toUpperCase()) {
            errorInfo.innerHTML = "验证码不正确";
            return false;
        } else {
            $('.login').addClass('test'); //倾斜特效
            setTimeout(function () {
                $('.login').addClass('testtwo'); //平移特效
            }, 300);
            setTimeout(function () {
                $('.authent').show().animate({right: -320}, {
                    easing: 'easeOutQuint',
                    duration: 600,
                    queue: false
                });
                $('.authent').animate({opacity: 1}, {
                    duration: 200,
                    queue: false
                }).addClass('visible');
            }, 500);
            return true;
            $("form").submit();
        }
    };
    var fullscreen = function () {
        elem = document.body;
        if (elem.webkitRequestFullScreen) {
            elem.webkitRequestFullScreen();
        } else if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.requestFullScreen) {
            elem.requestFullscreen();
        } else {
            //浏览器不支持全屏API或已被禁用
        }
    };
    if (ajaxmockjax == 1) {
        $.mockjax({
            url: 'Ajax/Login',
            status: 200,
            responseTime: 50,
            responseText: {"Status": "ok", "Text": "登陆成功<br /><br />欢迎回来"}
        });
        $.mockjax({
            url: 'Ajax/LoginFalse',
            status: 200,
            responseTime: 50,
            responseText: {"Status": "Erro", "Erro": "账号名或密码或验证码有误"}
        });
    }

</script>
</body>
</html>

