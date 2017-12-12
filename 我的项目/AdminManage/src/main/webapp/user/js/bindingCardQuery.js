/*function name() {
//������
    if (accType == "DEBIT") {
        alert("��ǿ�");
    } else if (accType == "CREDIT") {
        alert("��ǿ�");
    } else if (accType == "VENTURE") {
        alert("����");
    }


    //��״̬
    if (cardState == "0") {
        alert("ʧЧ");
    } else if (cardState == "1") {
        alert("����Ч");
    } else if (cardState == "2") {
        alert("��Ч");
    } else if (cardState == "3") {
        alert("����");
    }

}*/

var userId = "";//用户编号
var userRealName = "";//真实姓名
var cardId = "";//身份证号
var bankNo = "";
var cardState = "";//状态
var bindTimeStart = "";//绑定时间
var bindTimeEnd = "";//绑定时间
var phoneNo = "";
function bindSearch(){
	userId = document.getElementById("userId").value;
	userRealName = document.getElementById("userRealName").value;
	cardId = document.getElementById("cardId").value;
	bankNo = document.getElementById("bankNo").value;
	cardState = document.getElementById("cardState").value;
	bindTimeStart = document.getElementById("bindTimeStart").value;
	bindTimeEnd = document.getElementById("bindTimeEnd").value;
	phoneNo = document.getElementById("phoneNo").value;
}
