var web = null;
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
function opendChannel(){
    if(web == null || web == undefined){
        if ("WebSocket" in window) {
            console.log("浏览器支持 websocket");
            web = new WebSocket('ws://localhost:8080/websocket');
            aclass('text-primary', 'NORMAL');
        } else {
            alert("该浏览器不支持websocket")
        }
    }else {
        alert("已连接！")
    }
}

web.onopen = function (ev) {
    web.send("发送数据");
    console.info(ev);
    console.log("数据发送中！！");
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
function send() {
    if(web == null || web == undefined){
        alert("未连接！")
    }else {
        web.send($("#text").val());
    }
}
function close() {
    aclass('text-danger', 'CLOSE');
    console.info('close');
    web.close();
}