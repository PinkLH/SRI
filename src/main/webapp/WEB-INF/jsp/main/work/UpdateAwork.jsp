<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>著作增加</title>
    <link rel="stylesheet" href="static/css/work/AddWork.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="static/js/work/UpdateWork.js"></script>
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

        .layui-form-select dl dd.layui-this {
            background-color: #2989b6;
            color: #fff;
        }

        .layui-form-label {
            font-size: 16.8px;
        }

        select {
            border-radius: 5px;
            border: 0px;
            font-size: 18px;
            height: 30px;
            box-shadow: 0px 0px 10px #dddddd;
        }
    </style>
    <script>
        function confirmAdd(url) {
            layer.confirm('你确定修改吗？', {
                btn: ['确定', '取消'] //按钮
            }, function (index) {
                layer.close();
                $("#searchForm").attr("action", url);
                $("#searchForm").submit();
                layer.close(index);
            });
        }
        function showInfo(){
            $("#year").find("option[value = '${year}']").attr("selected", true);
            $("#month").find("option[value = '${month}']").attr("selected", true);
            $("#date").find("option[value = '${day}']").attr("selected", true);
        }
    </script>
</head>

<body>
<div class="all">
    <form id="searchForm" class="layui-form" action="" method="post" enctype="multipart/form-data">
        <input type="hidden" name="awid" value="${awork.awid}">
        <input type="hidden" name="awaddress" value="${awork.awaddress}">
        <input type="hidden" name="uid" value="${awork.uid}">
        <div class="xueshu" id="xueshu">
            <div class="layui-form-item">
                <label class="layui-form-label">著作名称:</label>
                <div class="layui-input-block">
                    <input type="text" name="awname" required="" lay-verify="required" placeholder="请输入著作名称"
                           autocomplete="off" class="layui-input" value="${awork.awname}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">出&nbsp;&nbsp;版&nbsp;&nbsp;社:</label>
                <div class="layui-input-block">
                    <input type="text" name="awpress" required="" lay-verify="required" placeholder="请输入出版社"
                           autocomplete="off" class="layui-input" value="${awork.awpress}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">编&nbsp;&nbsp;辑&nbsp;&nbsp;人:</label>
                <div class="layui-input-block">
                    <input type="text" name="awperson" required="" lay-verify="required" placeholder="请输入编辑人"
                           autocomplete="off" class="layui-input" value="${awork.awperson}">
                </div>
            </div>
            <div class="time layui-form-item">
                <label class="layui-form-label">发表时间:</label>
                <div class="layui-input-block">
                    <select id="year" name="year" onchange="doChange()"></select>
                    &nbsp年&nbsp
                    <select id="month" name="month" onchange="doChange()"></select>
                    &nbsp月&nbsp
                    <select id="date" name="day"></select>
                    &nbsp日&nbsp
                </div>

            </div>

        </div>

        <div class="wenjian layui-form-item">
            <label class="layui-form-label">添加附件:</label>
            <input class="layui-input" type="file" id="inputfile" name="uploadFile" required lay-verify="required"
                   style="display: inline-block;width: 500px;padding: 7px;border: 1px solid #fff">
            <br>
        </div>
        <iframe id="preview" title="Inline Frame Example"
                style="border-radius: 5px;border: 2px solid #ccc;padding: 5px;margin-left: 15px;"
                width="100%"
                height="250px"
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
                    <button type="button" class="layui-btn" onclick="confirmAdd('UpdateAworkInfo')" lay-submit
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