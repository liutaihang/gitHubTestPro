<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="./css/welcome.css" rel="stylesheet">
    <link rel="stylesheet" href="./layui/css/modules/layer/default/layer.css">
    <script type=text/javascript src="./lib/jquery/jquery-1.9.0.min.js"></script>
    <script src="./lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script type="text/javascript" src="./lib/ligerUI/js/plugins/ligerComboBox.js"></script>
    <script type=text/javascript src="./lib/ligerUI/js/plugins/ligerGrid.js"></script>
    <script src="./layui/lay/modules/layer.js" type="text/javascript"></script>
    <script type=text/javascript src="./lib/ligerUI/js/core/base.js"></script>
    <script src="./js/jurisdiction.js" type="text/javascript"></script>
    <script src="./js/Treatment.js" type="text/javascript"></script>
    <style type="text/css">
	    html, body {
	            width: 100%;
	            height: 100%;
	        }
    
        #cs {
            position: relative;
            display: inline-block;
            margin: auto;
        }
        #welcome {
            width: 500px;
            height: 200px;
            color: white;
            text-align: center;
            /*background: white;*/
            border-radius: 10px;
            position: absolute;
            top: 200px;
            left: 400px;
        }
        #cs {
            width: 100%;
            height: 100%;
            margin: auto;
            overflow: hidden;
            /*background: url("img/bj_1.jpg");*/
        }
    </style>
    <script type="text/javascript">
        function changeInfor() {
            // document.getElementById("updateInfo").style.display = "block";
            document.getElementById("changeInformation").style.display = "block";
            document.getElementById("information").style.display = "none";
        }

        function cancel() {
            // document.getElementById("updateInfo").style.display = "none";
            document.getElementById("changeInformation").style.display = "none";
            document.getElementById("information").style.display = "block";
        }
    </script>

</head>

<body style="margin:0; padding:0; width: 100%;height: 100%;">
<!--<div id="bg"></div>-->
<div id="cs">
    <img style="width:100%;display: block;" src="img/bj_1.jpg">

    <div id="welcome">
        <p style="font-size: 50px">欢迎登陆!</p>

        <p style="font-size: 24px">归集通管理系统</p>
    </div>
    <div id="information">
        <div id="top">
            <img src="img/ts_r.png">

            <div class="left">管理员</div>
            <div class="right" onclick="changeInfor()">修改密码</div>
        </div>
        <div class="number">账号：<span id="account"></span></div>
        <!-- <div class="name">角色：<span id="nickName"></span></div> -->
    </div>
    <div id="changeInformation">
        <table>
            <!-- <tr>
                <td class="td_1">
                    角色：
                </td>
                <td>
                    <input type="text" placeholder="请输入昵称" class="inp" id="newnickName" readonly="readonly">
                </td>
            </tr> -->
            <tr>
                <td class="td_1">
                    输入密码：
                </td>
                <td>
                    <input type="password" id="first" placeholder="请输入密码" class="inp">
                </td>
            </tr>
            <tr>
                <td class="td_1">
                    再次输入：
                </td>
                <td>
                    <input type="password" id="second" placeholder="请再次输入密码" class="inp">
                </td>
            </tr>
        </table>
        <button onclick="update()" id="sub">提交</button>
        <button id="cancel" onclick="cancel()">取消</button>

    </div>
</div>
<script type="text/javascript">
    var local = window.location.href;
    var head = local.substring(0, local.indexOf(local.split("/", 5)[4], 1));
    var url = local.substring(0, local.indexOf(local.split("/", 5)[4], 1)) + "user/update.do";
    var verify = "user/verifyPwd.do";

    function update() {
        //var nickName = document.getElementById("newnickName").value;
        var oldPwd = document.getElementById("first").value;
        var newPwd = document.getElementById("second").value;
        /* if ($.isBlank(nickName)) {
            SuccessAlert("请输入昵称！");
            return;
        } */
        if ($.isBlank(oldPwd)) {
            SuccessAlert("请输入密码！");
            return;
        }
        if ($.isBlank(newPwd)) {
            SuccessAlert("请确认输入密码！");
            return;
        }
        if (oldPwd != newPwd) {
            SuccessAlert("两次输入密码不一致！");
            document.getElementById("second").value = "";
            return;
        }
        var user = {
            //"nickName": nickName,
            "password": newPwd
        };
        $.ajax({
            url: url,
            type: "post",
            data: {"uservo": JSON.stringify(user)},
            dataType: "json",
            success: function (data) {
                console.info(data);
                confirm(data.data);
                window.location.reload();
            }
        });
    }

    $(init_userInfo());

    function init_userInfo() {
        var user;
        $.ajax({
            url: head + "user/wel.do",
            type: "get",
            data: {},
            dataType: "json",
            success: function (data) {
                console.info(data);
                user = data.data;
                if ($.isBlank(user)) {
                    ErroAlert("用户为登录！");
                } else {
                    $("#account").html(user.userName);
                    $("#nickName").html(user.nickName);
                    $("#newnickName").val(user.nickName);
                }
            }
        });
    }
</script>

</body>
</html>