<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>成果查询</title>
    <link rel="stylesheet" href="static/css/achievement/SelectAchievement.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="static/js/achievement/SelectAchievement.js"></script>
    <style>
        .layui-input:focus,
        .layui-textarea:focus {
            border-color: #2989b6 !important;
        }

        .layui-form-select dl dd.layui-this {
            background-color: #2989b6;
            color: #fff;
        }

        .layui-form-label {
            padding: 9px 0px;
            font-size: 13px;
        }

        .layui-form-item .layui-input-inline {
            width: 55%;
            font-size: 14px;
        }

        select {
            border-radius: 5px;
            border: 0px;
            font-size: 18px;
            height: 30px;
            box-shadow: 0px 0px 10px #dddddd;
        }
        .trcolor{
            background-color: #b0eeff !important;
        }
    </style>
    <script type="text/javascript">
        function checkDel(aid) {
            layer.confirm('是否删除该条信息？', {
                btn: ['确定', '取消']
            }, function () {
                window.location.href = "DeleteAchievement?aid=" + aid;
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
    <form id="form1" class="layui-form" action="SelectAchievementInfo">
        <div class="head">
            <table id="form" width="100%" align="center" onsubmit="return false">
                <tr height="50px">
                    <td width="18%">
                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">名称搜索：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="aname" lay-verify="required" placeholder="请输入搜索内容"
                                       autocomplete="off" class="layui-input" style="width: 100%;font-size: 12px;" value="${aname}">
                            </div>
                        </div>
                    </td>

                    <td width="18%" align="left">
                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">成果级别：</label>
                            <div class="layui-input-inline">
                                <select id="alevel" name="alevel" lay-verify="required" lay-search
                                        lay-filter="test">
                                    <option value="all">全部</option>
                                    <option value="国家级">国家级</option>
                                    <option value="市级">市级</option>
                                    <option value="校级">校级</option>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td width="18%" align="left">
                        <div class="layui-form-item" style="margin-bottom: 0px;">
                            <label class="layui-form-label">成果类型：</label>
                            <div class="layui-input-inline">
                                <select id="atype" name="atype" lay-verify="required" lay-search
                                        lay-filter="test">
                                    <option value="all">全部</option>
                                    <option value="竞赛">竞赛</option>
                                    <option value="科研">科研</option>
                                    <option value="教学成果">教学成果</option>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td width="30%"></td>
                    <td align="right">
                        <button type="submit" class="thesis-search action-button">查询</button>
                        <button type="button" class="thesis-out action-button"
                                onclick="window.location.href='DownloadAchievement'">导出
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
                <th>成果名称</th>
                <th>级别</th>
                <th>类型</th>
                <th>授予单位</th>
                <th>获得时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${alist}" var="n" varStatus="state">
                <tr onmousemove="changeColorIn(this)" onmouseout="changeColorOut(this)">
                    <td>1</td>
                    <td>${n.aname}</td>
                    <td>${n.alevel}</td>
                    <td>${n.atype}</td>
                    <td>${n.aunit}</td>
                    <td>${atime[state.count-1]}</td>
                    <td>
                        <a href="DownloadAchievementByAid?aid=${n.aid}">下载</a>
                        &nbsp;|&nbsp;
                        <a href="UpdateAchievement?aid=${n.aid}">编辑</a>
                        &nbsp;|&nbsp;
                        <a href="javaScript:checkDel(${n.aid})">删除</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>


</div>
</body>
<script src="static/layform/layform.js"></script>
<script>
    $('#atype').siblings("div.layui-form-select").find('dl').find("dd[lay-value='${atype}']").click();
    $('#alevel').siblings("div.layui-form-select").find('dl').find("dd[lay-value='${alevel}']").click();
</script>
</html>