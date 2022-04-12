var today = new Date();
var year = today.getFullYear();
var month = today.getMonth();
var date = today.getDate();

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


$(function () {

    var $xueshu = $(".xueshu");
    layui.use(['form'], function () {
        var form = layui.form;
        form.on('radio', function (data) {
            if (data.value == 0) {
                $xueshu.eq(1).html('');
                $xueshu.eq(0).html('<div class="layui-form-item">\n' +
                    '                <label class="layui-form-label">软件名称:</label>\n' +
                    '                <div class="layui-input-block">\n' +
                    '                    <input type="text" name="swname" required lay-verify="required" placeholder="请输入软件名称"\n' +
                    '                           autocomplete="off"\n' +
                    '                           class="layui-input">\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '            <div class="layui-form-item">\n' +
                    '                <label class="layui-form-label">著作权人:</label>\n' +
                    '                <div class="layui-input-block">\n' +
                    '                    <input type="text" name="swperson" required lay-verify="required" placeholder="请输入著作权人"\n' +
                    '                           autocomplete="off"\n' +
                    '                           class="layui-input">\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '            <div class="layui-form-item">\n' +
                    '                <label class="layui-form-label">登&nbsp;&nbsp;记&nbsp;号:</label>\n' +
                    '                <div class="layui-input-block">\n' +
                    '                    <input type="text" name="swid" required lay-verify="required" placeholder="请输入登记号"\n' +
                    '                           autocomplete="off"\n' +
                    '                           class="layui-input">\n' +
                    '                </div>\n' +
                    '            </div>');
                // $work_table[1].style.display = "none";
                // $work_table[0].style.display = "block";
            } else {

                $xueshu.eq(0).html('');
                $xueshu.eq(1).html('<div class="layui-form-item">\n' +
                    '          <label class="layui-form-label">著作名称:</label>\n' +
                    '          <div class="layui-input-block">\n' +
                    '            <input type="text" name="awname" required lay-verify="required" placeholder="请输入著作名称" autocomplete="off"\n' +
                    '              class="layui-input">\n' +
                    '          </div>\n' +
                    '        </div>\n' +
                    '        <div class="layui-form-item">\n' +
                    '          <label class="layui-form-label">出&nbsp;&nbsp;版&nbsp;&nbsp;社:</label>\n' +
                    '          <div class="layui-input-block">\n' +
                    '            <input type="text" name="awpress" required lay-verify="required" placeholder="请输入出版社" autocomplete="off"\n' +
                    '              class="layui-input">\n' +
                    '          </div>\n' +
                    '        </div>\n' +
                    '        <div class="layui-form-item">\n' +
                    '          <label class="layui-form-label">编&nbsp;&nbsp;辑&nbsp;&nbsp;人:</label>\n' +
                    '          <div class="layui-input-block">\n' +
                    '            <input type="text" name="awperson" required lay-verify="required" placeholder="请输入编辑人" autocomplete="off"\n' +
                    '              class="layui-input">\n' +
                    '          </div>\n' +
                    '        </div>\n' +
                    '        <div class="time layui-form-item">\n' +
                    '          <label class="layui-form-label">发表时间:</label>\n' +
                    '           <div>' +
                    '                <select id="year" name="year" onchange="doChange()"></select>\n' +
                    '                &nbsp年&nbsp\n' +
                    '                <select id="month" name="month" onchange="doChange()"></select>\n' +
                    '                &nbsp月&nbsp\n' +
                    '                <select id="date" name="day"></select>\n' +
                    '                &nbsp日&nbsp\n' +
                    '           </div>' +

                    '        </div>');

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
                $("select").show();
            }

        });
    });


})
