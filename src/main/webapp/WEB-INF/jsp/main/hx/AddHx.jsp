<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>横向增加</title>
    <script type="text/javascript" src="static/js/hx/AddHx.js"></script>
    <link rel="stylesheet" href="static/css/hx/AddHx.css">
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
        select {
            border-radius: 5px;
            border: 0px;
            font-size: 18px;
            height: 30px;
            box-shadow: 0px 0px 10px #dddddd;
        }
    </style>
    <script type="text/javascript">
        function confirmAdd(url) {
            var hmoneyVal = $("#hmoney").val();
            var n = /^[0-9]*$/;
            if (n.test(hmoneyVal)){
                layer.confirm('你确定添加吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function (index) {
                    layer.close();
                    $("#searchForm").attr("action", url);
                    $("#searchForm").submit();
                    layer.close(index);
                });
                // return false;
            }else {
                layer.alert('经费只能是数字！');
            }

        }
    </script>
</head>

<body>
<div class="all">

    <form id="searchForm" class="layui-form" action="" method="post" enctype="multipart/form-data">
        <div class="layui-form-item">
            <label class="layui-form-label">合同名称：</label>
            <div class="layui-input-block">
                <input type="text" name="hname" required lay-verify="required" placeholder="请输入合同名称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">合作对象：</label>
            <div class="layui-input-block">
                <input type="text" name="hobject" required lay-verify="required" placeholder="请输入合作对象"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="time">
            <p>
                开始时间：&nbsp&nbsp&nbsp <select name="hbyear" id="years" onchange="doChange1()"></select>
                &nbsp年&nbsp
                <select name="hbmonth" id="months" onchange="doChange1()"></select>
                &nbsp月&nbsp
                <select name="hbday" id="dates"></select>
                &nbsp日&nbsp
            </p>
            <p>
                结束时间：&nbsp&nbsp&nbsp
                <select name="heyear" id="year" onchange="doChange()"></select>
                &nbsp年&nbsp
                <select name="hemonth" id="month" onchange="doChange()"></select>
                &nbsp月&nbsp
                <select name="heday" id="date"></select>
                &nbsp日&nbsp
            </p>
        </div>


        <!--time-->

        <div class="layui-form-item">
            <label class="layui-form-label">经&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp费:</label>
            <div class="layui-input-block">
                <input type="text" id="hmoney" name="hmoney" required lay-verify="required" placeholder="单位（元）"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="wenjian">
            <p style="display: inline;">合同电子版：&nbsp;&nbsp;</p>
            <input class="layui-input" type="file" id="inputfile" name="uploadFile" required lay-verify="required"
                   style="display: inline-block;width: 500px;padding: 7px;border: 1px solid #fff">
            <br>
        </div>

        <iframe id="preview" title="Inline Frame Example"
                style="border-radius: 5px;border: 2px solid #ccc;padding: 5px;margin-left: 15px;"
                width="100%"
                height="400px"
                src="static/previewName/previewName.html">
        </iframe>
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

        <div class="xia">

            <div class="layui-form-item1">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" onclick="confirmAdd('AddHxInfo')" lay-submit
                            lay-filter="formDemo">确认添加
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