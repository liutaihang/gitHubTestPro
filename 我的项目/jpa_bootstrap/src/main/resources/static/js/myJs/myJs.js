/**
 * add
 */
function demo() {
    var name = $("#name").val();
    var content = $("#content").val();
    var something = $("#something").val();
    $.ajax({
        type: "POST",
        url: "/demo",
        data: {
            name: name,
            something: something,
            content: content
        },
        success: function (data) {
            console.info(data);
            pagination(document.getElementById("hidden"));
            $("#name").val("");
            $("#content").val("");
            $("#something").val("");
            alert("添加成功!");
        },
        error: function (data) {
            console.info(data);
            console.log("URL --> [" + data.responseURL + "] TEXT --> [" + data.responseText + "] JSON --> [" + data.responseJSON + "]")
            console.info(data.responseText);
            if (isNotBlank(data.responseText)) {
                alert(data.responseText);
            }
        }
    });
}


var isNotBlank = function (args) {
    if (args == null || args == undefined || args == "") {
        return false;
    }
    return true;
}

/**
 * table
 */
function viewData() {
    var table = $("#t2");
    $.ajax({
        type: "GET",
        url: "/viewData",
        data: {},
        success: function (data) {
            alert(data[0].content);
            console.info(data);
            // var temp = JSON.parse(data);
            // var tempStr = "";
            // for (i = 0; i < temp.length; i++) {
            //     tempStr += "<tr><td>" + temp[i].id + "</td>" + "<td>" + temp[i].content + "</td>" + "<td>" +
            //         temp[i].name + "</td>" + "<td>" + temp[i].something + "</td></tr>";
            // }
            // table.html(tempStr);
        }
    });
}

// viewData();

/**
 * pagination
 */
function pagination(element) {
    var table = $("#t2");
    var pageElemnt = $("#page");
    var page = 0;
    if (element) {
        page = element.innerText - 1;
        console.info(element.innerText);
        if (element.innerText == "first") {
            page = 0;
        }
        if (element.innerText == "end") {
            page = parseInt($("#hidden").val()) - 1;
        }
        if (page < 0) {
            page = 0;
        }
    }
    var url = "/page/" + page + "/" + 10
    $.ajax({
        url: url,
        type: "GET",
        data: {},
        success: function (data) {
            console.info(data);
            var list = data.data;
            var tempStr = "";
            for (i = 0; i < list.length; i++) {
                tempStr += "<tr><td>" + list[i].id + "</td>" + "<td>" + list[i].content + "</td>" + "<td>" +
                    list[i].name + "</td>" + "<td>" + list[i].something + "</td><td><button onclick='del(this)' class='btn-danger badge'>删除</button>&nbsp;" +
                    "<button onclick='update(this)' class='btn-danger badge' data-toggle='modal' data-target='#my'>修改</button></td></tr>";
            }
            table.html(tempStr);
            var totalsize = totalPage(data.size);
            var tempPage = "<li><a href='#' onclick='pagination(this)'>first</a></li>";
            for (i = 0; i < totalsize; i++) {
                tempPage += "<li><a href='#' onclick='pagination(this)'>" + (i + 1) + "</a></li>";
            }
            tempPage += "<li><a href='#' onclick='pagination(this)'>end</a></li>";
            $("#hidden").val(totalsize);
            pageElemnt.html(tempPage);
        },
        error: function success(data) {
            console.info(data);
        }
    });
}

pagination();

/**
 * 计算总页数
 */
function totalPage(num) {
    var temp = num / 10;
    if (num % 10 != 0) {
        temp += 1
    }
    return parseInt(temp);
}

function del(data) {
    var data = data.parentNode.parentNode.childNodes;
    console.log(data[0].innerText);
    var url = 'del/' + data[0].innerText;
    $.ajax({
        url: url,
        type: "POST",
        data: {},
        success: function (data) {
            console.info(data);
            if (isNotBlank(data.msg)) {
                pagination();
                alert(data.msg);
            }
        },
        error: function (data) {
            if (isNotBlank(JSON.parse(data.responseText).message)) {
                alert(JSON.parse(data.responseText).message);
            }
        }
    });
}

function update(data) {
    var data = data.parentNode.parentNode.childNodes;
    var temp = [$("#id"), $("#nam"), $("#ctt"), $("#smt")];
    console.info(temp);
    for (var i = 0; i < data.length; i++) {
        if (temp.length > i) {
            temp[i].val(data[i].innerText);
        }
        console.info(data[i].innerText);
    }
}

function makeUpdate() {
    var tempName = $("#nam");
    var tempContent = $("#ctt");
    var tempSomething = $("#smt");
    var uri = "update";
    $.ajax({
        url: url,
        type: "GET",
        data: {
            name: tempName.innerText,
            content: tempContent.innerText,
            something: tempSomething.innerText
        },
        success: function (data) {
            console.info(data);
            alert(data.msg);
        },
        error: function (data) {
            if (JSON.parse(data.responseText).message == "demoDto.property.null.error") {
                alert("修改数据不能为空");
            }
        }
    });
}

function closeDIV(data) {
    document.getElementById("").setAttribute("display", "none");
}

$("#sub").click(function () {
    sub();
});

function sub() {
    var formData = new FormData();
    var url = "/testd";
    let file = $("#upload")[0].files[0];
    if(file){
        formData.append("file", file);
    }else{
        alert("文件为空")
    }
    formData.append("ces", "测试");
    formData.append("ll", "l来了");
    fileUpload(formData, url);
}

function fileUpload(fromData, url) {
    $.ajax({
        url:url,
        type:"post",
        data:fromData,
        contentType:false,
        processData:false,
        dataType:"json",
        mimeType:"multipart/form-data",
        success:function (data) {
            console.info(data);
        }
    });
}