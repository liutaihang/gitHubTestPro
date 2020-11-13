var web = null;
var user = {};

Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
/**
 *
 * @param style
 * @param temp
 */
function aclass (style, temp){
    var status = $("#sta");
    status.removeClass();
    status.addClass(style);
    console.info(status.val());
}
function danger(){
    aclass("text-danger", "DANGER")
}

function defult(){
    aclass("text-warning", "DEFULT")
}
function normal(){
    aclass("text-primary", "NORMAL")
}
function opendWebChannel(){
    if(web == null || web == undefined){
        if ("WebSocket" in window) {
            console.log("浏览器支持 websocket");
            web = new WebSocket('ws://localhost:8080/websocket');
            aclass('text-primary', 'NORMAL');


            web.onopen = function (ev) {
                console.info(ev);
                console.log("数据发送中！！");
                web.send("发送数据");
            };

            web.onmessage = function (ev) {
                var recevied_msg = ev.data;
                console.info("发送中！")
                console.log(recevied_msg);
            };
            web.onclose = function (ev) {
                aclass('text-danger', 'CLOSE');
                alert("连接关闭！")
                console.info(ev);
            };
            web.onerror = function () {
                alert('websocket通信发生错误！');
            }
        } else {
            alert("该浏览器不支持websocket")
        }
    }else {
        alert("已连接！")
    }
}

let msg = {
    msgType : "1",
    msgContent: "",
    msgTime: new Date().Format("yyyy-MM-dd HH:mm:ss")
}

function send() {
    if(web == null || web == undefined){
        alert("未连接！")
    }else {
        if(web.readyState != 1){
            web = null;
            opendWebChannel();
        }
        web.send($("#text").val());
    }
}
function closeWebClient() {
    aclass('text-danger', 'CLOSE');
    console.info('close');
    web.close();
    web = null;
}