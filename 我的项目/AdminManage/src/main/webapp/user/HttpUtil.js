function ajaxRequest(req) {
    var url = req.url;
    var type = 'POST';
    if (req.type) {
        type = req.type;
    }
    var data = req.data;
    var headers = {
        'Content-Type': 'application/json;charset=utf-8'
    };
    if (req.headers) {
        headers = req.headers;
    }
    $.ajax({
        url: url,
        type: type,
        headers: headers,
        data: data,
        success: function (res) {
            console.log(res);
            var results = splitResult(res);
            if (results[0] === "SUCCESS") {
                req.success(results[1]);
            } else {
                req.fail(results[1]);
            }
        },
        fail: function (res) {
            console.log(res);
            req.fail('�����쳣');
        }
    });
}

function splitResult(result) {
    var datas = result.splite('|');
    var results = [];
    if (datas[0] === 1) {
        results[0] = 'SUCCESS';
        results[1] = datas[1];
    } else {
        results[0] = 'FAILED';
        results[1] = datas[2];
    }
    return results;
}