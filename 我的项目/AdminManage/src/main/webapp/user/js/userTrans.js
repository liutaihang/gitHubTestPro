//function functionname() {
//֧����ʽ
//if (payMode == '0') {
//    alert('����֧��')
//} else if (payMode == '1') {
//    alert('�Զ�֧��')
//}
//֧������
//if (payType == '01') {
//    alert('���п��˻�֧��')
//} else if (payType == '02') {
//    alert('���п�ɨ��֧��')
//}
//����״̬
//    if (TRADE_STATUS = 'Finished') {
//        alert('���׳ɹ�')
//    } else if (TRADE_STATUS = 'Fail') {
//        alert('����ʧ��')
//    } else if (TRADE_STATUS = 'Closed') {
//        alert('���׹ر�')
//    } else if (TRADE_STATUS = 'Created') {
//        alert('�ȴ�֧��')
//    }
//}

//function query(res) {
//    var url = "http://180.76.171.32:8026/support/gw";
//    var data = {
//        service: "getAllForSupport"
//    };
//    ajaxRequest({
//        url: url,
//        data: JSON.stringify(data),
//        success: function (res) {
//            console.log(res);
//        },
//        fail: function (res) {
//            console.log(res);
//            ErroAlert(res);
//        }
//    });
//}
//
//$(document).ready(function () {
//    query();
//});
var tempNum,pageStatus;
var transStatus = "";
var bankNo = "";
var orderId = "";
var serv = "";
var txnAmtStart = "";
var txnAmtEnd = "";
var resId = "";
var payMode = "";
var refId = "";
var acceptDateStart = "";
var acceptDateEnd = "";
var bType = "";
function query(){
	pageStatus = false;
	transStatus = document.getElementById("tradeStatus").value;
	bankNo = document.getElementById("bankNo").value;
	orderId = document.getElementById("orderId").value;
	serv = document.getElementById("serv").value;
	txnAmtStart = document.getElementById("txnAmtStart").value;
	txnAmtEnd = document.getElementById("txnAmtEnd").value;
	resId = document.getElementById("resId").value;
	payMode = document.getElementById("payMode").value;
	refId = document.getElementById("refId").value;
	acceptDateStart = document.getElementById("acceptDateStart").value;
	acceptDateEnd = document.getElementById("acceptDateEnd").value;
    if(!$.isBlank(serv) && serv == "gzMobileRiskControl|bType"){
    		bType = "bType";
    		serv = "gzMobileRiskControl";
    }else{
		bType = "";
    }
}
var header = window.document.location.href;
header = header.substring(0,header.indexOf(header.split("/", 5)[4], 1));

(function($){
	  $.isBlank = function(obj){
	    return(!obj || $.trim(obj) === "");
	  };
	})(jQuery);

