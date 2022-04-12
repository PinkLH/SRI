<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>专利查询</title>
    <link rel="stylesheet" href="static/css/patent/SelectPatent.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="static/js/patent/SelectPatent.js"></script>
    <style>
        .layui-input:focus,
        .layui-textarea:focus {
            border-color: #2989b6 !important;
        }

        .layui-form-select dl dd.layui-this {
            background-color: #2989b6;
            color: #fff;
        }
        .layui-form-label{
            padding: 9px 0px;
            font-size: 13px;
        }
        .layui-form-item .layui-input-inline{
            width: 55%;
        }
        .trcolor{
            background-color: #b0eeff !important;
        }
    </style>
    <script type="text/javascript">
        function checkDel(pid) {
            layer.confirm('是否删除该条信息？', {
                btn: ['确定', '取消']
            }, function () {
                window.location.href = "DeletePatent?pid=" + pid;
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
    <div class="main-box">
        <form class="layui-form" action="SelectPatentInfo">
            <div class="head">
                <table id="form" width="100%" align="center" onsubmit="return false">
                    <tr height="50px">
                        <td width="25%">
                            <div class="layui-form-item" style="margin-bottom: 0px;">
                                <label class="layui-form-label">专利号/申请号：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="pid" lay-verify="required" value="${pid}"
                                        placeholder="请输入专利号/申请号" autocomplete="off" class="layui-input" style="width: 100%;font-size: 12px;padding-right: 0px;" >
                                </div>
                            </div>
                        </td>
                        <td width="25%">
                            <div class="layui-form-item" style="margin-bottom: 0px;">
                                <label class="layui-form-label" >专利名称：</label>
                                <div class="layui-input-inline" >
                                    <input type="text" name="pname" lay-verify="required" placeholder="请输入专利名称"
                                        autocomplete="off" class="layui-input" style="font-size: 12px;width: 100%;" value="${pname}">
                                </div>
                            </div>
                        </td>

                        <td align="right">
                            <button type="submit" class="thesis-search action-button">查询</button>
                            &nbsp;&nbsp;
                            <button type="button" class="thesis-out action-button" onclick="window.location.href='DownloadPatent'">导出</button>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
            <div class="info_table" style="padding-bottom: 30px;">
                <table id="sum">
                    <tr>
                        <th>序号</th>
                        <th>专利号/申请号</th>
                        <th>专利名称</th>
                        <th>专利申请时间/授权时间</th>
                        <th>专利状态</th>
                        <th>专利权人</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${plist}" var="n" varStatus="state">
                        <tr onmousemove="changeColorIn(this)" onmouseout="changeColorOut(this)">
                            <td>1</td>
                            <td>${n.pid}</td>
                            <td>${n.pname}</td>
                            <td>${ptime[state.count-1]}</td>
                            <td>${n.pstate}</td>
                            <td>${n.ppatentee}</td>
                            <td>
                                <a href="DownloadPatentByPid?pid=${n.pid}">下载</a>
                                &nbsp;|&nbsp;
                                <a href="UpdatePatent?pid=${n.pid}">编辑</a>
                                &nbsp;|&nbsp;
                                <a href="javaScript:checkDel('${n.pid}');">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>


    </div>
</body>
<script src="static/layform/layform.js"></script>

</html>