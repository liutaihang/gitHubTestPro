var SYS_DICT_URL = ctx + "/sys/dicts/listData";

// 恢复提示框显示
function resetTip() {
    top.$.jBox.tip.mess = null;
}

// 关闭提示框
function closeTip() {
    top.$.jBox.closeTip();
}

function defaultVal(v, d) {
    if (v == null) {
        if (d != null) {
            return d;
        } else {
            return '';
        }
    } else {
        return v;
    }
}

function showMessage(mess, icon) {
    if (isEmpty(mess)) {
        return;
    }
    var msg = "";
    if (_.isArray(mess)) {
        for (var i = 0; i < mess.length; i++) {
            msg += mess[i];
            if (i < mess.length - 1) {
                msg += "<br />";
            }
        }
    } else {
        msg = mess;
    }

    if (icon == null) {
        icon = 'success';
    }

    resetTip();
    top.$.jBox.tip(msg, icon, {opacity: 0});
}

function showAjaxErrorMessage(mess, data) {
    if (isEmpty(mess)) {
        return;
    }
    var msg;
    if (data.responseJSON && data.responseJSON.errMsg) {
        msg = mess + "："+ data.responseJSON.errMsg;
    } else {
        msg = mess + "！";
    }

    resetTip();
    top.$.jBox.tip(msg, "error", {opacity: 0, timeout: 2000});
}

var chnNumbers = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九");
function showChnNumber(index) {
    if (index >= 0 && index < chnNumbers.length) {
        document.write(chnNumbers[index]);
    }
}

// 确认对话框
function confirmx(mess, href, closed) {
    bootbox.confirm({
        message: mess,
        title: '确认',
        animate: false,
        callback: function (result) {
            if (result === true) {
                if (typeof href == 'function') {
                    href();
                } else {
                    window.location.href = href;
                }
            } else {
                if (typeof closed == 'function') {
                    closed();
                }
            }
        }
    })
    return false;
}


function cookie(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }

}

function bindDatepicker(row, data, index) {
    $(".date-picker", row).datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd',
        language: 'zh-CN'
    });
}

var Common = function () {
    // public functions
    return {

        //main function
        init: function () {
            //datatables 默认参数设置
            $.extend(true, $.fn.dataTable.defaults, {
                searching: false,//不需要搜索框
                lengthChange: false,//不需要改变分页的一页记录数
                pageLength: 30,//一页记录数默认从10改成30
                ordering: true,//默认可以排序
                processing: true,
                pagingType: 'full_numbers',//默认的分页展示类型。
                "language": {
                    "url": ctxStatic + "/plugins/datatables/language-Zh.json"
                },
                "columnDefs": [
                    {"defaultContent": "", "targets": "_all"}
                ]
            });
            $.fn.dataTable.ext.errMode = 'throw'

            //日期组件的默认
            if (jQuery().datepicker) {
                $('.date-picker').datepicker({
                    rtl: App.isRTL(),
                    autoclose: true,
                    format: 'yyyy-mm-dd',
                    language: 'zh-CN'
                });
                $('body').removeClass("modal-open");
            }

            $('#searchForm input').keypress(function (e) {
                if (e.which == 13) {
                    search();
                    return false;
                }
            });

            //Jquery validate的默认设置
            jQuery.validator.setDefaults({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true,
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    error.insertAfter(element.closest('.invalid-place'));
                },

                submitHandler: function (form) {
                    $(".disable-after-click").prop('disabled', true);
                    Common.loading('正在提交，请稍等...');
                    form.submit();
                }
            });

            setTemplateInterpolate();
        },

        // 获取URL地址参数
        getQueryString: function (name, url) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            if (!url || url == "") {
                url = window.location.search;
            } else {
                url = url.substring(url.indexOf("?"));
            }
            r = url.substr(1).match(reg)
            if (r != null) return unescape(r[2]);
            return null;
        },

        // 显示加载框
        loading: function (mess) {
            if (mess == undefined || mess == "") {
                mess = "正在提交，请稍等...";
            }
            resetTip();
            top.$.jBox.tip(mess, 'loading', {opacity: 0});
        },


        cookie: function (name, value, options) {
            if (typeof value != 'undefined') { // name and value given, set cookie
                options = options || {};
                if (value === null) {
                    value = '';
                    options.expires = -1;
                }
                var expires = '';
                if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
                    var date;
                    if (typeof options.expires == 'number') {
                        date = new Date();
                        date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
                    } else {
                        date = options.expires;
                    }
                    expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
                }
                var path = options.path ? '; path=' + options.path : '';
                var domain = options.domain ? '; domain=' + options.domain : '';
                var secure = options.secure ? '; secure' : '';
                document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
            } else { // only name given, get cookie
                var cookieValue = null;
                if (document.cookie && document.cookie != '') {
                    var cookies = document.cookie.split(';');
                    for (var i = 0; i < cookies.length; i++) {
                        var cookie = jQuery.trim(cookies[i]);
                        // Does this cookie string begin with the name we want?
                        if (cookie.substring(0, name.length + 1) == (name + '=')) {
                            cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                            break;
                        }
                    }
                }
                return cookieValue;
            }

        }
    };

}();
