let searchData = {
    mainUrl:"http://47.114.147.221/",
    category:{
        video:{
            name:"视频",
            searchName:"ysname",
            searchUrl:"ysurl"
        },
        story:{
            name:"小说",
            searchName:"",
            searchUrl:""
        },
        cartoon:{
            name:"漫画",
            searchName:"",
            searchUrl:""
        }
    }
};

function search_one() {
    let searchKey = $(".searchKey").val();
    if(searchKey || searchKey == ""){
        tableInit("t3");
    }

    if(searchKey){
        $.ajax({
            type:"GET",
            url:searchData.mainUrl + "?" + searchData.category.video.searchName + "=" + searchKey,
            async:false,
            data:{},
            success(data){
                data = JSON.parse(data);
                console.info(data);
                tableInit("t3", data.list);
            }
        });
    }
}

function tableInit(tabId, data) {
    let initData = [];
    if(data && data.length > 0){
        for (let i = 0; i < data.length; i++) {
            let tds =
            tableTdInit(data[i].name, "") +
            tableTdInit(data[i].genre, "") +
            tableTdInit(data[i].time, "") +
            tableTdInit("<i class='fa fa-reorder'></i>", "data-toggle='modal' data-target='#data_detail' onclick='search_two(" +"\"" + data[i].url + "\"" + ")'") ;

            initData.push(tds);
        }
    }else{
        initData .push("<td colspan='4'>暂无数据</td>");
    }

    $("#" + tabId).html(tableTrInit(initData));
}

function tableTdInit(data, property) {
    return "<td " + property +">" + data + "</td>"
}

function tableTrInit(data) {
    let res = "";
    if(data && data.length > 0){
        for (let i = 0; i < data.length; i++) {
            res += "<tr>" + data[i] + "</tr>"
        }
    }
    return res;
}

function search_two(url) {
    url = searchData.mainUrl + "?" + searchData.category.video.searchUrl + "=" + url;
    let data = ajaxFunc("GET", url, {});
    console.info(data);

    $(".movie_name").html(data.data.name);
    $(".director").html(data.data.director);
    $(".Release").html(data.data.Release);
    $(".region").html(data.data.region);
    $(".genre").html(data.data.genre);
    $(".Language").html(data.data.Language);
    $(".introduce").html(data.data.introduce);
    $(".performer").html(data.data.performer);
    $(".cover").attr("src", data.data.cover);
    $(".download").attr("href", data.list[0].download);
    $(".m3u8").attr("href", data.list[0].m3u8url);
    $(".online").attr("href", data.list[0].onlineurl);


}

function ajaxFunc(type, url, data) {
    let res ;
    $.ajax({
        type:type,
        url:url,
        data:data,
        async:false,
        success(data){
            res = JSON.parse(data);
        }
    });
    return res;
}

