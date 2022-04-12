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
    var oTable = document.getElementById("num");
    for(var i=1;i<oTable.rows.length;i++){
    oTable.rows[i].cells[0].innerHTML = (i);
}
}

$(function () {
    color();
})

function addRow() {
    var index = 0;//初始下标
    var indexArr= new Array();
    index++;
    indexArr.push(index);

    var showHtml = $("#show").html();
    var html = "<tr id='tr##'>"+showHtml+"</tr>";
    html = html.replace(/##/g,index);//把##替换成数字

    $("#show").before($(html));

    console.log("当前下标数组",indexArr);}


    function deleteRow(inde){
        $("#tr" + inde).remove();
        var a = indexArr.indexOf(parseInt(inde));
 
        if (a > -1) {
            indexArr.splice(a, 1);
            console.log("当前下标数组",indexArr);
        }
 
    }