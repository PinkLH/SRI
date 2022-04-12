function color() {
    var $aLi = $('tr');
    for (var i = 1; i < $aLi.length; i++) {
        if (i % 2 == 0)
            $aLi[i].style.background = '#fff';
        else
            $aLi[i].style.background = '#eee';
    }
}

window.onload = function(){
    var oTable = document.getElementById("sum");
    for(var i=1;i<oTable.rows.length;i++){
    oTable.rows[i].cells[0].innerHTML = (i);
}
}

$(function () {
    color();
})