<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>立项查询</title>
    <link rel="stylesheet" href="static/css/lx/SelectLx.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="static/js/lx/SelectLx.js"></script>
    <style>
        .layui-input:focus,
        .layui-textarea:focus {
            border-color: #2989b6 !important;
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
        #form label {
            width: 90px;
        }
        .layui-form-item .layui-input-inline {
            width: 60%;
        }
        #form button {
            width: 80px;
        }
        .trcolor {
            background-color: #b0eeff !important;
        }
    </style>

    <script type="text/javascript">
        function checkDel(lid) {
            layer.confirm('是否删除该条信息？', {
                btn: ['确定', '取消']
            }, function () {
                window.location.href = "DeleteLx?lid=" + lid;
            });
        }

        function changeColorIn(obj) {
            obj.className = "trcolor"
        }

        function changeColorOut(obj) {
            obj.className = ""
        }


    </script>
</head>
<body>
<div class="main-box">

    <form class="layui-form" action="SelectLxInfo">
        <div class="head">
            <table id="form" width="100%" align="center" onsubmit="return false">
                <tr height="50px">
                    <td width="25%">
                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">项目编号：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="lid" lay-verify="required" placeholder="请输入项目编号"
                                       autocomplete="off" class="layui-input" value="${lid}">
                            </div>
                        </div>

                    </td>
                    <td width="25%">
                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">项目名称：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="lname" lay-verify="required" placeholder="请输入项目名称"
                                       autocomplete="off" class="layui-input" value="${lname}">
                            </div>
                        </div>
                        <!-- <label> 项目编号：</label>
                        <input type="text" placeholder="请输入项目编号" autocomplete="off"> -->
                    </td>
                    <td width="25%">

                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">项目级别：</label>
                            <div class="layui-input-inline">
                                <select id="ltype" name="ltype" lay-verify="required" lay-search
                                        lay-filter="ltype">
                                    <option value="all">全部</option>
                                    <option value="国家级">国家级</option>
                                    <option value="省级">省级</option>
                                    <option value="校级">校级</option>

                                </select>
                            </div>
                        </div>
                    </td>
                    <td align="right">
                        <button type="submit">查询</button>
                        <button type="button" onclick="window.location.href='DownloadLx'">导出附件</button>
                        <button type="button" onclick="window.location.href='ExportLxExcel'">导出Excel</button>
                    </td>
                </tr>
            </table>
        </div>
        <!--head-->

        <div class="kuang" style="padding-bottom: 30px;">
            <table class="main_table" id="sum">
                <tr>
                    <th>序号</th>
                    <th>项目编号</th>
                    <th>项目名称</th>
                    <th>项目级别</th>
                    <th>负责人</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>经费</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${llist}" var="n" varStatus="state">
                    <tr onmousemove="changeColorIn(this)" onmouseout="changeColorOut(this)">
                        <td>1</td>
                        <td>${n.lid}</td>
                        <td>${n.lname}</td>
                        <td>${n.ltype}</td>
                        <td>${n.lperson}</td>
                        <td>${lbtime[state.count-1]}</td>
                        <td>${letime[state.count-1]}</td>
                        <td>${n.lmoney}</td>
                        <td>
                            <a href="DownloadLxByLid?lid=${n.lid}">下载</a>
                            &nbsp;|&nbsp;
                            <a href="UpdateLx?lid=${n.lid}">编辑</a>
                            &nbsp;|&nbsp;
                            <a href="javaScript:checkDel('${n.lid}');">删除</a>
                        </td>
                    </tr>
                </c:forEach>


            </table>
        </div>
    </form>

</div>
<!--all-->
</body>
<script src="static/layform/layform.js"></script>
<script>
    $('#ltype').siblings("div.layui-form-select").find('dl').find("dd[lay-value='${ltype}']").click();
</script>
</html>