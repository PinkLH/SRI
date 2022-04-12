var today = new Date();
var year = today.getFullYear();
var month = today.getMonth();
var date = today.getDate();

window.onload = function () {
    for (var i = year + 100; i >= year - 100; i--) {
        var option = new Option(i, i);
        // 如果年份等于当前年份 就默认选择
        if (i == year) {
            option.selected = true;
        }
        // 添加到id为year的select中
        ID('year').add(option);
    }
    // 循环月份
    for (var j = 1; j <= 12; j++) {
        var option = new Option(j, j);
        if ((j - 1) == month) {
            option.selected = true;
        }
        ID('month').add(option);
    }
    // 页面加载调用doChange事件
    doChange();

    $("select").next().remove();
}

function doChange() {
    // 把ID('date')中的option长度变为0
    ID('date').options.length = 0;
    var length = 31;
    // 获取月份
    var mon = ID('month').value;
    // 如果是2月
    if (mon == 2) {
        // 平年就是28
        length = 28;
        // 获取年份
        var num = ID('year').value;
        // 判断是不是闰年 闰年就是29
        if ((num % 4 == 0 && num % 100 != 0) || num % 400 == 0) {
            length++;
        }
    }
    // 定义正则判断月份 4 6 9 11 月
    var reg = /[469]|^11$/;
    // 符合条件就是30天
    if (reg.test(mon)) {
        length = 30;
    }
    // 然后循环 把值塞进去
    for (var k = 1; k <= length; k++) {
        var option = new Option(k, k);
        if (k == date && mon == month + 1) {
            option.selected = true;
        }
        ID('date').add(option);
    }
}
// 封装获取id的简化函数        
function ID(id) {
    return document.getElementById(id);
}
function addRow() {
    var showHtml = $("#show").prop("outerHTML");
    $("#show").before(showHtml);
}

function deleteRow(obj){
    if($(".show").length > 1){
        $(obj).parent().parent("#show").remove();
    }else{
        alert("不能再少了！");
    }
}

