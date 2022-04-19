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
    <title>横向查询</title>
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="static/layform/layform.css">
    <link rel="stylesheet" href="static/css/hx/SelectHx.css">
    <script type="text/javascript" src="static/js/hx/SelectHx.js"></script>
    <style>
        /* input:focus{
        border-color: #2989b6 !important;
      } */
        .layui-input:focus,
        .layui-textarea:focus {
            border-color: #2989b6 !important;
        }

        .layui-form-item {
            margin-bottom: 0px;
        }

        .layui-form-item .layui-input-inline {
            width: 45%;
        }
        #form button {
            width: 80px;
        }
        .trcolor{
            background-color: #b0eeff !important;
        }
    </style>
    <script type="text/javascript">
        function checkDel(hid) {
            layer.confirm('是否删除该条信息？', {
                btn: ['确定', '取消']
            }, function () {
                window.location.href = "DeleteHx?hid=" + hid;
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
    <form class="layui-form" action="SelectHxInfo">
        <div class="head">
            <table id="form" width="100%" align="center">
                <tr height="50">

                    <td width="35%">
                        <div class="layui-form-item">
                            <label class="layui-form-label">合同名称：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="hname" lay-verify="required" placeholder="请输入合同名称"
                                       autocomplete="off" class="layui-input" value="${hname}">
                            </div>
                        </div>
                    </td>
                    <td width="35%">
                        <div class="layui-form-item">
                            <label class="layui-form-label">合作对象：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="hobject" lay-verify="required" placeholder="请输入合作对象"
                                       autocomplete="off" class="layui-input" value="${hobject}">
                            </div>
                        </div>
                    </td>
<%--                    <td width="10%"></td>--%>
                    <td align="right">
                        <button type="submit">查询</button>
                        <button type="button" onclick="window.location.href='DownloadHx'">导出附件</button>
                        <button type="button" onclick="window.location.href='ExportHxExcel'">导出Excel</button>
                    </td>
                </tr>
            </table>

        </div>
    </form>
    <div class="z"></div>
    <div class="kuang" style="padding-bottom: 30px;">
        <table border="1" id="num">
            <tr>
                <th>序号</th>
                <th>合同名称</th>
                <th>合作对象</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>经&nbsp&nbsp&nbsp费(元)</th>
                <th>操&nbsp&nbsp&nbsp作</th>
            </tr>
            <c:forEach items="${hlist}" var="n" varStatus="state">
                <tr onmousemove="changeColorIn(this)" onmouseout="changeColorOut(this)">
                    <td>1</td>
                    <td>${n.hname}</td>
                    <td>${n.hobject}</td>
                    <td>${hbtime[state.count-1]}</td>
                    <td>${hetime[state.count-1]}</td>
                    <td>${n.hmoney}</td>
                    <td>
                        <a href="DownlodHxByHid?hid=${n.hid}">下载</a>
                        &nbsp;|&nbsp;
                        <a href="UpdateHx?hid=${n.hid}">编辑</a>
                        &nbsp;|&nbsp;
                        <a href="javaScript:checkDel(${n.hid})">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</div>
<!--all-->
</body>

<script src="static/layform/layform.js"></script>

</html>