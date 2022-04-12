function sunbmitfun(url) {
    var $upwd = $("#upwd");
    var $confirmUpwd = $("#confirmUpwd");
    var $utel = $("#utel");
    if ($upwd.val() == "" && $utel.val() == "") {
        layer.alert("请输入联系方式或密码！");
    } else {
        if ($utel.val() == "") {
            if ($upwd.val() == $confirmUpwd.val()) {
                layer.confirm("确认提交密码吗？", {
                    btn: ['确定', '取消']
                },function (index){
                    layer.close();
                    $("#form1").attr("action", url);
                    $("#form1").submit();
                    layer.close(index);
                });
            } else {
                layer.alert("请确认密码！");
            }
        } else if ($upwd.val() == "" && $confirmUpwd.val() == "") {
            layer.confirm("确认提交联系方式'" + $utel.val() + "'吗？", {
                btn: ['确定', '取消']
            },function (index){
                layer.close();
                $("#form1").attr("action", url);
                $("#form1").submit();
                layer.close(index);
            })
        } else {
            if ($upwd.val() == $confirmUpwd.val()) {
                layer.confirm("确认提交联系方式和密码吗？", {
                    btn: ['确定', '取消']
                },function (index){
                    layer.close();
                    $("#form1").attr("action", url);
                    $("#form1").submit();
                    layer.close(index);
                })
            } else {
                layer.alert("请确认密码！");
            }
        }
    }
}