var loginName = "";
var phoneNo = "";
var realName = "";
var userId = "";
var cardNo = "";
var createTimeStart = "";
var createTimeEnd = "";
/**
 * 用户查询
 */
function queryUser() {

    loginName = document.getElementById("loginName").value;
    phoneNo = document.getElementById("phoneNo").value;
    realName = document.getElementById("realName").value;
    userId = document.getElementById("userId").value;
    cardNo = document.getElementById("cardNo").value;
    createTimeStart = document.getElementById("createTimeStart").value;
    createTimeEnd = document.getElementById("createTimeEnd").value;
}


function findUser() {
    pho = document.getElementById("phoneNo").value;
    if (!(/^1[34578]\d{9}$/.test(pho))) {
        alert("手机号码有误，请重填");
        return false;
    }
}


