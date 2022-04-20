function color() {
    var $aLi = $('tr');
    for (var i = 1; i < $aLi.length; i++) {
        if (i % 2 == 0)
            $aLi[i].style.background = '#fff';
        else
            $aLi[i].style.background = '#eee';
    }
}

$(function () {

    var oTable = document.getElementById("num");
    var x = document.getElementById("sum");
    for (var i = 1; i < oTable.rows.length; i++) {
        oTable.rows[i].cells[0].innerHTML = (i);
    }
    for (var n = 1; n < x.rows.length; n++) {
        x.rows[n].cells[0].innerHTML = (n);
    }

    var $work_table = $(".kuang");
    layui.use(['form'], function () {
        var form = layui.form;
        form.on('select(test)', function (data) {
            if (data.value == 0) {
                $work_table[1].style.display = "none";
                $work_table[0].style.display = "block";
            } else {
                $work_table[0].style.display = "none";
                $work_table[1].style.display = "block";
            }
        });
    });
    color();
    showInfo();
})

