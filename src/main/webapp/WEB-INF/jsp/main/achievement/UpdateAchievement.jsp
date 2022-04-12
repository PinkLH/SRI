<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改成果</title>
    <script type="text/javascript" src="static/js/achievement/UpdateAchievement.js"></script>
    <link rel="stylesheet" href="static/css/achievement/UpdateAchievement.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <style>
        .layui-input:focus,
        .layui-textarea:focus {
            border-color: #2989b6 !important;
        }
        .layui-input:hover {
            box-shadow: 0px 0px 10px #dddddd;
        }
        .layui-form-radio:hover *,
        .layui-form-radioed,
        .layui-form-radioed > i {
            color: #2989b6;
        }

        input {
            box-shadow: 0px 0px 20px #dddddd;
        }

        select {
            border-radius: 5px;
            border: 0px;
            font-size: 18px;
            height: 30px;
            box-shadow: 0px 0px 10px #dddddd;
        }
    </style>

    <script type="text/javascript">
        function confirmUpdate(url) {
            layer.confirm('你确定修改吗？', {
                btn: ['确定', '取消'] //按钮
            }, function (index) {
                layer.close();
                $("#searchForm").attr("action", url);
                $("#searchForm").submit();
                layer.close(index);
            }, function () {
            });
            return false;
        }

        function showInfo(){
            $("#year").find("option[value = '${year}']").attr("selected", true);
            $("#month").find("option[value = '${month}']").attr("selected", true);
            $("#date").find("option[value = '${day}']").attr("selected", true);
            $("#alevelRadio").find("input[type=radio][value='${achievement.alevel}']").next().click();
            $("#atypeRadio").find("input[type=radio][value='${achievement.atype}']").next().click();
        }
    </script>
</head>

<body>
<div class="article">
    <form id="searchForm" class="layui-form" action="" method="post" enctype="multipart/form-data">
        <input type="hidden" name="uid" value="${achievement.uid}">
        <input type="hidden" name="aid" value="${achievement.aid}">
        <input type="hidden" name="aaddress" value="${achievement.aaddress}">
        <div class="layui-form-item">
            <label class="layui-form-label">成果名称：</label>
            <div class="layui-input-block">
                <input type="text" name="aname" required lay-verify="required" placeholder="请输入成果名称"
                       autocomplete="off" class="layui-input" value="${achievement.aname}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">授予单位：</label>
            <div class="layui-input-block">
                <input type="text" name="aunit" required lay-verify="required" placeholder="请输入授予单位"
                       autocomplete="off" class="layui-input" value="${achievement.aunit}">
            </div>
        </div>
        <div class="time">
            <p>
                <label style="font-size: 17px;font-weight: 400;">获得时间：&nbsp&nbsp</label>
                <select name="year" id="year" onchange="doChange()"></select>
                &nbsp年&nbsp
                <select name="month" id="month" onchange="doChange()"></select>
                &nbsp月&nbsp
                <select name="day" id="date"></select>
                &nbsp日&nbsp
            </p>
        </div>
        <br>
        <div class="layui-form-item">
            <label class="layui-form-label">成果级别：</label>
            <div id="alevelRadio" class="layui-input-inline" style="margin-left: 110px;width: 600px;">
                <input type="radio" name="alevel" value="国家级" title="国家级" checked>
                <input type="radio" name="alevel" value="市级" title="市级">
                <input type="radio" name="alevel" value="校级" title="校级">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">成果类型：</label>
            <div id="atypeRadio" class="layui-input-inline" style="margin-left: 110px;width: 600px;">
                <input type="radio" name="atype" value="竞赛" title="竞赛" checked>
                <input type="radio" name="atype" value="科研" title="科研">
                <input type="radio" name="atype" value="教学成果" title="教学成果">
            </div>
        </div>


        <div class="doc">
            <p style="font-size: 17px;display: inline;">添加附件：&nbsp;&nbsp;&nbsp;</p>
            <input class="layui-input" type="file" id="inputfile" name="uploadFile" required lay-verify="required"
                   style="display: inline-block;width: 500px;padding: 7px;border: 1px solid #fff">
            <br>
        </div>
        <div class="button-frame">
            <iframe id="preview" title="Inline Frame Example" style="margin-left: 15px;" width="100%" height="250px"
                    src="static/previewName/previewName.html"></iframe>
        </div>

        <script>
            function getFileName(o) {
                var pos = o.lastIndexOf("\\");
                return o.substring(pos + 1);
            }

            $('#inputfile').change(function () {
                var file = $("#inputfile").val();
                var fileName = getFileName(file);
                var extName = file.substr(file.lastIndexOf(".") + 1).toLowerCase();
                var _URL = window.URL || window.webkitURL;
                var objectURL = _URL.createObjectURL(this.files[0]);
                if (extName == "jpg" || extName == "jpeg" || extName == "png" || extName == "gif" || extName == "pdf") {
                    $("#preview").attr("src", objectURL);
                    _URL.revokeObjectURL(objectURL);
                } else {

                    $("#preview").contents().find("html").html('<head>'
                        + '<meta charset="UTF-8">'
                        + '<meta http-equiv="X-UA-Compatible" content="IE=edge">'
                        + '<meta name="viewport" content="width=device-width, initial-scale=1.0">'
                        + '<title>Document</title>'
                        + '</head>'
                        + '<body>'
                        + '<h1 id="name">请选择文件</h1>'
                        + '</body>');
                    $("#preview").contents().find("#name").html("你已选择：" + fileName);

                }
            });

        </script>

        <div class="foot">
            <div class="layui-form-item1">
                <div class="layui-input-block">
                    <button type="button" onclick="confirmUpdate('UpdateAchievementInfo')" class="layui-btn" lay-submit
                            lay-filter="formDemo">确认修改
                    </button>
                </div>
            </div>
        </div>
    </form>


</div>
<!--all-->

</body>

<script src="static/layform/layform.js"></script>

</html>