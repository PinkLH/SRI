<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改个人信息</title>
    <script type="text/javascript" src="static/js/jq/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="static/css/index/UpdateUserInfo.css">
    <link rel="stylesheet" href="static/layform/layform.css">
    <style type="text/css">
        .layui-input:focus,
        .layui-textarea:focus {
            border-color: #2989b6 !important;
        }

        body {
            background-color: #fff;
        }

        input {
            box-shadow: 0px 0px 20px #dddddd;
        }
    </style>
</head>

<body>
<div class="all">
    <div class="shang">

        <div class="zuo">
            <form id="form1" class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">工号：</label>
                    <div class="layui-input-block">
                        <input type="text" disabled="disabled" value="${sessionScope.user.uid}" autocomplete="off"
                               class="layui-input" style="color: #aaa">
                        <input name="uid" type="hidden" value="${sessionScope.user.uid}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名：</label>
                    <div class="layui-input-block">
                        <input type="text" disabled="disabled" value="${sessionScope.user.uname}" autocomplete="off"
                               class="layui-input" style="color: #aaa">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">修改联系方式：</label>
                    <div class="layui-input-block">
                        <input type="text" id="utel" name="utel" required lay-verify="required" placeholder="请输入联系方式"
                               value="${sessionScope.user.utel}" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">修改密码：</label>
                    <div class="layui-input-block">
                        <input type="password" id="upwd" name="upwd" required lay-verify="required" placeholder="请输入密码"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码：</label>
                    <div class="layui-input-block">
                        <input type="password" id="confirmUpwd" name="confirmUpwd" required lay-verify="required"
                               placeholder="请确认密码" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-input-block">
                        <p style="width: 88%;float: right; color:red;">注：不需要修改的可以不填！</p>
                    </div>

                </div>

            </form>
            <div class="xia">

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn" onclick="sunbmitfun('UpdateUserInfoForm')">修改</button>
                    </div>
                </div>
            </div>

        </div>

        <!--zuo-->

        <div class="you">
            <div class="img">
                <img src="static/images/index/头像.png" width="100%" style="text-align: center;">
            </div>
        </div>

    </div>
    <!--shang-->
</div>
</body>
<script src="static/layform/layform.js"></script>
<script type="text/javascript" src="static/js/index/UpdateUserInfo.js"></script>
</html>