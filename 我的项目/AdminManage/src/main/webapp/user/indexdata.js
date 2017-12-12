var indexData = [{
    text: '综合查询',
    isexpand: false,
    children: [{
        url: "query/userTrans.htm",
        text: "用户交易"
    },
        {
            url: "query/userQuery.htm",
            text: "用户查询"
        },
        {
            url: "query/bindingCardQuery.htm",
            text: "绑卡查询"
        },
        {
            url: "manageSystem/versionManagement.htm",
            text: "版本管理"
        }
    ]
},
    {
        text: '用户',
        isexpand: false,
        children: [
            {
                url: "query/grayscaleUpgrade.htm",
                text: "用户灰度升级"
            }, {
                url: "query/whiteList.htm",
                text: "白名单用户"
            }
        ]
    }
    ,

    {
        text: '修改管理员信息',
        isexpand: false,
        children: [{
            url: "modifyPassword/modifyPassword.htm",
            text: "修改管理员信息"
        }, {
            url: "manageSystem/administratorQuery.htm",
            text: "系统管理员查询"
        }, {
            url: "list/authorityDistribution.htm",
            text: "用户列表"
        }


        ]
    }
];




function getMenus() {
    $.cookie('userRole');
}