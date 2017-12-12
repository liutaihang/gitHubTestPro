<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
    String url = "http://58.42.236.252:7444/AdminManage/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
    <script type="text/javascript" src="author/js/jquery.min.js"></script>
    <link rel="stylesheet" href="./user/css/upload.css">
</head>
<body>
<form method="post" action="<%=basePath %>file/upload.do" enctype="multipart/form-data" id="upload">
    文件上传：<input style="font-size: 13px" id="file" type="file" name="file" accept=".apk"><br>
    <input style="font-size: 13px" type="submit" value="上传" id="sub">
    <button id="return">
        <a href="${pageContext.request.contextPath }/user/manageSystem/versionManagement.htm">
            返回
        </a>
    </button>
</form>
</body>
<script type="text/javascript">
   /*  var local = window.location.href;
    var str = local.substring(0, local.indexOf(local.split("/", 5)[4], 1)) + "user/login.do";
    console.info(str);
    var url = "file/upload.do";
    var formData = new FormData();
    formData.append('file', $("#file").prop('files'));

    function init() {
        var files = $("#file").prop('files');
        var reader = new FileReader();//新建一个FileReader
        reader.readAsText(files[0], "UTF-8");//读取文件
//var files = document.getElementById("uploadForm").files;
        console.info(files);
        reader.onload = function (evt) {
            var fileString = evt.target.result;
            console.info(fileString);
        };
        $.ajax({
            url: url,
            type: 'POST', //GET
            cache: false, //缓存
            data: formData,
// timeout:5000, //超时时间
            dataType: 'json', //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, status) {
                console.log('发送后');
                console.log(data, status);
            },
            error: function (returnData) {
                console.info("returnData:" + returnData);
            }
        });
    } */

</script>
</html>