<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>著作查询</title>
    <link rel="stylesheet" href="static/css/work/SelectWork.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="static/js/work/SelectWork.js"></script>
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

        body {
            background-color: #fff;
        }
        #form button {
            width: 80px;
        }
        .trcolor{
            background-color: #b0eeff !important;
        }
    </style>
    <script>
        function checkDelAwork(awid) {
            layer.confirm('是否删除该条信息？', {
                btn: ['确定', '取消']
            }, function () {
                window.location.href = "DeleteAwork?awid=" + awid;
            });
        }
        function checkDelSwork(swid) {
            layer.confirm('是否删除该条信息？', {
                btn: ['确定', '取消']
            }, function () {
                window.location.href = "DeleteSwork?swid=" + swid;
            });
        }
        function changeColorIn(obj){
            obj.className="trcolor"
        }
        function changeColorOut(obj){
            obj.className=""
        }
    </script>
</head>

<body>
<div class="all">

    <form class="layui-form" action="SelectWorkInfo">
        <div class="head">
            <table id="form" width="100%" align="center">
                <tr height="50px">
                    <td width="30%">
                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">名称搜索：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="wname" lay-verify="required" placeholder="请输入名称"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </td>

                    <td width="30%" align="left">

                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">项目级别：</label>
                            <div class="layui-input-inline">
                                <select id="work_type" name="wtype" lay-verify="required" lay-search lay-filter="test">
                                    <option value="0">软件著作</option>
                                    <option value="1">学术著作</option>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td align="right">
                        <button type="submit">查询</button>
                        <button type="button" onclick="window.location.href='DownloadWork'">导出附件</button>
                        <button type="button" onclick="window.location.href='ExportWorkExcel'">导出Excel</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <!--head-->

    <div class="kuang" style="padding-bottom: 30px;">
        <table border="1" id="sum">
            <tr>
                <th>序号</th>
                <th>软件名称</th>
                <th>登&nbsp;记&nbsp;号</th>
                <th>著作权人</th>
                <th>操&nbsp;&nbsp;&nbsp;作</th>
            </tr>
            <c:forEach items="${swlist}" var="n">
                <tr onmousemove="changeColorIn(this)" onmouseout="changeColorOut(this)">
                    <td>1</td>
                    <td>${n.swname}</td>
                    <td>${n.swid}</td>
                    <td>${n.swperson}</td>
                    <td>
                        <a href="#">下载</a>
                        &nbsp;|&nbsp;
                        <a href="UpdateSwork?swid=${n.swid}">编辑</a>
                        &nbsp;|&nbsp;
                        <a href="javaScript:checkDelSwork(${n.swid})">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="kuang" style="display: none;">
        <table border="1" id="num">
            <tr>
                <th>序号</th>
                <th>著作名称</th>
                <th>出版日期</th>
                <th>出&nbsp;版&nbsp;社</th>
                <th>编&nbsp;辑&nbsp;人</th>
                <th>操&nbsp;&nbsp;&nbsp;作</th>
            </tr>
            <c:forEach items="${awlist}" var="n" varStatus="state">
                <tr onmousemove="changeColorIn(this)" onmouseout="changeColorOut(this)">
                    <td>1</td>
                    <td>${n.awname}</td>
                    <td>${awtime[state.count-1]}</td>
                    <td>${n.awpress}</td>
                    <td>${n.awperson}</td>
                    <td>
                        <a href="#">下载</a>
                        &nbsp;|&nbsp;
                        <a href="UpdateAwork?awid=${n.awid}">编辑</a>
                        &nbsp;|&nbsp;
                        <a href="javaScript:checkDelAwork(${n.awid})">删除</a>
                    </td>
                </tr>
            </c:forEach>


        </table>
    </div>
    <br>


</div>
<!--all-->
</body>
<script src="static/layform/layform.js"></script>

</html>