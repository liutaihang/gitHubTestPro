var SYS_DICT_URL = ctx + "/sys/dicts/listData";
var img = new Image();
var canvas = document.createElement('canvas');

//获取字典标签
function getDictLabel(data, value, defaultValue) {
    for (var i = 0; i < data.length; i++) {
        var row = data[i];
        if (row.value == value) {
            return row.label;
        }
    }
    return defaultValue;
}

function convertPrice(price) {
    var data = parseInt(price);
    if (isNaN(data)) {
        return "";
    } else {
        return data;
    }
}

function isNumber(data) {
    return !isNaN(parseInt(data));
}

function isNotEmpty(data) {
    return !_.isEmpty(data);
}

function isEmpty(data) {
    return _.isEmpty(data);
}

function filterEmpty(data) {
    if (data == null || data == undefined)
        data = "";
    return data;
}

function setTemplateInterpolate() {
    if (_ && _.templateSettings) {
        _.templateSettings = {
            interpolate: /\{\{(.+?)\}\}/g
        };
    }
}

function renderUniform(row) {
    $(row).find("input[type='checkbox']").uniform();
}

function enableSelectAll(table) {
    $(table).find('.group-checkable').change(function () {
        var set = jQuery(this).attr("data-set");
        var checked = jQuery(this).is(":checked");
        jQuery(set).each(function () {
            if (checked) {
                $(this).attr("checked", true);
                $(this).parents('tr').addClass("active");
            } else {
                $(this).attr("checked", false);
                $(this).parents('tr').removeClass("active");
            }
        });
        jQuery.uniform.update(set);
    });

    $(table).on('change', 'tbody tr .checkboxes', function () {
        $(this).parents('tr').toggleClass("active");
    });
}

function getChecked(tableId) {
    var checked = $('#' + tableId + ' tbody input[type="checkbox"]:checked');
    if (checked == null || checked.length == 0) {
        showMessage("请选中一行！");
        return;
    } else if (checked.length > 1) {
        showMessage("只能选中一行！");
        return;
    }
    return checked;
}

function getMultipleChecked(tableId) {
    var checked = $('#' + tableId + ' tbody input[type="checkbox"]:checked');
    if (checked == null || checked.length == 0) {
        showMessage("请至少选中一行！");
        return;
    }
    return checked;
}


function loadSysDict(type, callback) {
    return $.ajax({
        url: SYS_DICT_URL,
        async: false,
        data: "type=" + type,
        success: function (data) {
            if (callback) {
                callback(data);
            }
        }
    });
}


function bindRowId(row, data, index) {
    $("input[name]", row).each(function () {
        var name = $(this).attr("name");
        if (name.indexOf("_") == -1) {
            if (data.rowId) {
                $(this).attr("name", name + "_" + data.rowId);
            } else {
                $(this).attr("name", name + "_" + uuid.v1());
            }
        }
    })
}

function addInputChangeEvent(tableId) {
    var table = $(tableId).DataTable();
    table.on('change', "input", function (e) {
        if (e.currentTarget.type == 'checkbox') {
            return;
        }
        var td = $(this).parent("td");
        table.cell(td).data(this.value).draw();
    });
}

function addSelectChangeEvent(tableId) {
    var table = $(tableId).DataTable();
    table.on('change', "select", function (e) {
        var option = $(this).find(":selected");
        var td = $(this).parent("td");
        var data = table.cell(td).data();
        //字典对象类型
        if (_.isObject(data)) {
            data.id = option.val();
            data.label = option.html();
        } else {
            data = option.val();
        }
        table.cell(td).data(data).draw();
    });
}

function addDeleteRowEvent(tableId) {
    var table = $(tableId).DataTable();
    table.on('click', '.delete', deleteRow);
}

function generateSelectEl(sysDicts, row, cell) {
    var select = $("<select class='form-control " + cell + "' name='" + cell + "'>");
    if (isNotEmpty(sysDicts)) {
        for (var i = 0; i < sysDicts.length; i++) {
            var sysDict = sysDicts[i];
            var option = $("<option>")
                .val(sysDict.id)
                .html(sysDict.label);
            //默认选第一个
            if (row[cell] == null && i == 0) {
                row[cell] = {
                    id: sysDict.id,
                    label: sysDict.label
                };
                option.attr("selected", "selected");
            } else if (row[cell] == sysDict.id || row[cell].id == sysDict.id) {
                option.attr("selected", "selected");
            }
            select.append(option);
        }
    }
    return select.prop("outerHTML");
}

function rotate(imgId, angle) {
    var img = document.getElementById(imgId);
    var ctx = canvas.getContext('2d');
    var width = img.naturalWidth;
    var height = img.naturalHeight;
    canvas.width = height;
    canvas.height = width;
    //ctx.fillStyle = '#fff';
    //ctx.fillRect(0, 0, canvas.width, canvas.height);
    if(angle==90){
        ctx.rotate(90 * Math.PI / 180);
        ctx.drawImage(img, 0, -height);
    }else{
        ctx.rotate(-90 * Math.PI / 180);
        ctx.drawImage(img, -width, 0);
    }

    img.src = canvas.toDataURL('image/jpeg');
}

