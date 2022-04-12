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
    <title>论文查询</title>
    <link rel="stylesheet" href="static/css/thesis/SelectThesis.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="static/js/thesis/SelectThesis.js"></script>
    <style>
        .layui-input:focus,
        .layui-textarea:focus {
            border-color: #2989b6 !important;
        }

        .layui-form-select dl dd.layui-this {
            background-color: #2989b6;
            color: #fff;
        }
        .trcolor{
            background-color: #b0eeff !important;
        }
    </style>
    <script type="text/javascript">
        function checkDel(tid) {
            layer.confirm('是否删除该条信息？', {
                btn: ['确定', '取消']
            }, function () {
                window.location.href = "DeleteThesis?tid=" + tid;
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
    <form id="form1" class="layui-form" action="SelectThesisInfo">
        <div class="head">
            <table id="form" width="100%" align="center" onsubmit="return false">
                <tr height="50px">
                    <td width="40%">
                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">论文名称：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="tname" lay-verify="required" placeholder="请输入论文名称"
                                       autocomplete="off" class="layui-input" value="${tname}">
                            </div>
                        </div>
                    </td>

                    <td width="40%" align="left">

                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">论文类别：</label>
                            <div class="layui-input-inline">
                                <select id="ttype" name="ttype" lay-verify="required" lay-search
                                        lay-filter="test">
                                    <option value="all">全部</option>
                                    <option value="一区">一区</option>
                                    <option value="二区">二区</option>
                                    <option value="三区/四区">三区/四区</option>
                                    <option value="公开区">公开区</option>
                                    <option value="核心区">核心区</option>
                                    <option value="IE会议论文">IE会议论文</option>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td align="right">
                        <button type="submit" class="thesis-search action-button">查询</button>
                        <button type="button" class="thesis-out action-button"
                                onclick="window.location.href='DownloadThesis'">导出
                        </button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <div class="info_table" style="padding-bottom: 30px;">
        <table id="sum">
            <tr>
                <th>序号</th>
                <th>论文名称</th>
                <th>发表时间</th>
                <th>论文类别</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${tlist}" var="n" varStatus="state">
                <tr onmousemove="changeColorIn(this)" onmouseout="changeColorOut(this)">
                    <td>1</td>
                    <td>${n.tname}</td>
                    <td>${ttime[state.count-1]}</td>
                    <td>${n.ttype}</td>
                    <td>
                        <a href="DownloadThesisByTid?tid=${n.tid}">下载</a>
                        &nbsp;|&nbsp;
                        <a href="UpdateThesis?tid=${n.tid}">编辑</a>
                        &nbsp;|&nbsp;
                        <a href="javaScript:checkDel(${n.tid})">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</div>
</body>
<script src="static/layform/layform.js"></script>
<script>
    $('#ttype').siblings("div.layui-form-select").find('dl').find("dd[lay-value='${ttype}']").click();
</script>
</html>