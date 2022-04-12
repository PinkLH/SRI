var flag = [true, true, true, true, true, true, true];
var flagIcon = [true, true, true, true, true, true, true];
var test = document.getElementsByClassName('main_box');
var icon = document.getElementsByClassName('icon_right');

function upList() {
    for (let i = 1; i < test.length; i++) {
        test[i].style.height = '60px';
        test[i].style.background = '#FFFFFF';

        flag[i] = true;
    }
    for (let x = 0; x < icon.length; x++) {
        if (flagIcon[x] === false) {
            icon[x].style.transform = "rotate(0deg)";
            flagIcon[x] = true;
        }
    }
}

function downList(n) {
    if (flag[n] === true && flagIcon[n - 1] === true) {
        upList();
        test[n].style.height = '120px';
        test[n].style.background = '#D7EAFB';
        icon[n - 1].style.transform = "rotate(90deg)";
        flag[n] = false;
        flagIcon[n - 1] = false;
    } else {
        test[n].style.height = '60px';
        icon[n - 1].style.transform = "rotate(0deg)";
        flag[n] = true;
        flagIcon[n - 1] = true;
    }

}

function liback() {
    var li_a = document.getElementsByClassName('li-a');
    for (let j = 0; j < li_a.length; j++) {
        li_a[j].style.color = "#333333";
    }
}

$(document).ready(function () {
    var info = true;
    $(".User").click(function () {
        $(".UserChoice").toggle(100);
        if (info === true) {
            $(".info_icon").css("transform", "rotate(90deg)");
            info = false;
        } else {
            $(".info_icon").css("transform", "rotate(0deg)");
            info = true;
        }
    });
    $(".li-a").click(function () {
        liback();
        $(this).css("color", "#2989b6");
    });
    $(".main_info").click(function () {
        liback();
    });

});
