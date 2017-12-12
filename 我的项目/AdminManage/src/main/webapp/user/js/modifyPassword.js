
function preservation() {
    var pwdNew = document.getElementById('newPassword').value;
    var pwdTwo = document.getElementById('inputAgainPassword').value;
    var loginName = document.getElementById('loginName').value;
    var inputpho = document.getElementById('inputpho').value;
    var oldPassword = document.getElementById('oldPassword').value;
    var inputChannelName = document.getElementById('inputChannelName').value;
    var inputemail = document.getElementById('inputemail').value;
    console.log(pwdNew);

    if (pwdNew != pwdTwo) {
        alert('重置后两次输入的密码不一致');
    }
}
function reset() {

}
