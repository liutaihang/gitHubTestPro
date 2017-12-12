//绑卡查询
var CustomersData =
{
    Rows: [
        {
            "month": "绑卡查询",
            "ddbs": "",
            "jyje": "",
            "yhkbs": "",
            "sbed": "",
            "sbsyed": "",
            "zffs": "",
            "zflx": "",
            "bs": "",
            "ddzymc": "",
            "jyrq": "",
            "ddzt": "",
            "yybs": "",
            "yhmc": '',
            "bankName": ""
        },
        {
            "month": "绑卡查询",
            "ddbs": "",
            "jyje": "",
            "yhkbs": "",
            "sbed": "",
            "sbsyed": "",
            "zffs": "",
            "zflx": "",
            "bs": "",
            "ddzymc": "",
            "jyrq": "",
            "ddzt": "",
            "yybs": "",
            "yhmc": '',
            "bankName": ""
        }
    ],
    Total: 3
};
//用户查询
var CustomersData1 =
{
    Rows: [
        {
            "yhbm": "001",
            "loginName": "用户查询",
            "phoNum": "",
            "realName": "",
            "yhzt": "",
            "lastLoginTime": "",
            "zcsj": ""
        }
    ],
    Total: 5
};
//绑卡查询
var CustomersData2 =
{
    Rows: [
        {
            "ksx": "借记卡",
            "kh": "绑卡查询",
            "kzt": "",
            "sbkyed": "",
            "sbhked": "",
            "name": "",
            "pho": "",
            "cjtime": "",
            "idNumber": ""
        }
    ],
    Total: 5
};

var CustomersData3 =
{
    Rows: [
        {
            "yonghuid_4": "001",
            "zcshouji": "12345678901",
            "ywlx_4": "123",
            "ywzt_4": "456",
            "dqdydbm": "12357890999",
            "dqdydms": "正常",
            "slsj": "2017/5/9",
            "wgsj": "2017/5/19",
            "xtgzh": "156357883344356"

        }
    ],
    Total: 5
};
var CustomersData4 =
{
    Rows: [
        {
            "ID_5": "001",
            "yhbh_5": "12345",
            "dbsID": "1479",
            "dbsmc": "~~",
            "yhkh_5": "12357890999",
            "yhmc_5": "工商",
            "dksj_5": "2017/5/9",
            "szsj_5": "2017/5/19"
        }
    ],
    Total: 5
};

var CustomersData5 =
{
    Rows: [
        {
            "ID_6": "001",
            "yhbh_6": "12345",
            "zsxm": "1479",
            "zjhm": "~~",
            "ylsj": "12357890999",
            "yhmc_6": "工商",
            "yhkh_6": "2017/5/9",
            "ywzt": "2017/5/19",
            "dqdydbm": "11111111111",
            "dqdydms": "工商~~",
            "slsj": "2017/5/9",
            "xtgzh": "21567388700-"
        }
    ],
    Total: 5
};
//业务查询字段
var businessStatus = "";
var creatPho= "";
var businessType= "";
var refId= "";
var acceptDateStart= "";
var acceptDateEnd= "";
var orderId = "";

function businessSearch(){
	businessStatus = document.getElementById("businessStatus").value;
	creatPho = document.getElementById("creatPho").value;
	businessType = document.getElementById("businessType").value;
	refId = document.getElementById("refId").value;
	acceptDateStart = document.getElementById("acceptDateStart").value;
	acceptDateEnd = document.getElementById("acceptDateEnd").value;
	orderId = document.getElementById("orderId").value;
}

//自动代扣用户信息查询字段
var resName = "";//代办商名称
var resId = "";//	代办商ID
var createTimeStart = "";//设置日期:起始
var createTimeEnd = "";
var phoneNo = "";//注册手机号
var bankNo = "";//银行卡号


function autoSearch(){
	resName = document.getElementById("resName").value;
	resId = document.getElementById("resId").value;
	createTimeStart = document.getElementById("createTimeStart").value;
	createTimeEnd = document.getElementById("createTimeEnd").value;
	phoneNo = document.getElementById("phoneNo").value;
	bankNo = document.getElementById("bankNo").value;
}