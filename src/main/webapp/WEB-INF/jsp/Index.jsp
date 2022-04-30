<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
    <link rel="icon" href="static/images/favicon.png" mce_href="static/images/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="static/css/login/Index.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script src="static/js/login/Index.js"></script>
    <link rel="stylesheet" href="static/font/login/iconfont.css">
</head>

<body>
<div class="box">
    <nav>
        <!-- logo图片 -->
        <div class="logo">
            <a href="javascript:void(0)"><img src="static/images/login/logoSRI.svg" alt=""></a>
        </div>
        <!-- 左侧列表 -->
        <div id="navBox" class="nav-box">
            <div class="MainPage-box main_box">
                <a target="iframe" href="UpdateIndex">
                    <div class="Main main_info" onclick="upList()">
                        <span class="title"><span class="iconfont icon-zhuye"> </span>主页</span>
                    </div>
                </a>
            </div>
            <div id="LX-box" class="LX-box main_box">
                <a target="iframe" href="SelectLx">
                    <div class="LX main_info" onclick="downList(1)"><span class="title"><span
                            class="iconfont icon-lixiangxiangmu"> </span>立项</span></div>
                </a>
                <span class="iconfont icon-xiangyou1 icon_right"></span>
                <div class="Main-list">
                    <ul>
                        <li><a target="iframe" href="SelectLx" class="li-a">查询立项</a></li>
                        <li><a target="iframe" href="AddLx" class="li-a">添加立项</a></li>
                    </ul>
                </div>

            </div>
            <div id="HX-box" class="HX-box main_box">
                <a target="iframe" href="SelectHx">
                    <div class="HX main_info" onclick="downList(2)"><span class="title"><span
                            class="iconfont icon-xiangmu1"> </span>横项</span><span
                            class="iconfont icon-xiangyou1 icon_right"></span></div>
                </a>
                <div class="Main-list">
                    <ul>
                        <li><a target="iframe" href="SelectHx" class="li-a">查询横项</a></li>
                        <li><a target="iframe" href="AddHx" class="li-a">添加横项</a></li>
                    </ul>
                </div>

            </div>
            <div id="LW-box" class="LW-box main_box">
                <a target="iframe" href="SelectThesis">
                    <div class="LW main_info" onclick="downList(3)"><span class="title"><span
                            class="iconfont icon-fabiaolunwen"> </span>论文</span><span
                            class="iconfont icon-xiangyou1 icon_right"></span>
                    </div>
                </a>

                <div class="Main-list">
                    <ul>
                        <li><a target="iframe" href="SelectThesis" class="li-a">查询论文</a></li>
                        <li><a target="iframe" href="AddThesis" class="li-a">添加论文</a></li>
                    </ul>
                </div>

            </div>
            <div id="ZZ-box" class="ZZ-box main_box">
                <a target="iframe" href="SelectWork">
                    <div class="ZZ main_info" onclick="downList(4)"><span class="title"><span
                            class="iconfont icon-zhaozhuanli"> </span>著作</span><span
                            class="iconfont icon-xiangyou1 icon_right"></span></div>
                </a>

                <div class="Main-list">
                    <ul>
                        <li><a target="iframe" href="SelectWork" class="li-a">查询著作</a></li>
                        <li><a target="iframe" href="AddWork" class="li-a">添加著作</a></li>
                    </ul>
                </div>
            </div>
            <div id="ZL-box" class="ZL-box main_box">
                <a target="iframe" href="SelectPatent">
                    <div class="ZL main_info" onclick="downList(5)"><span class="title"><span
                            class="iconfont icon-xiangmu"> </span>专利</span><span
                            class="iconfont icon-xiangyou1 icon_right"></span></div>
                </a>

                <div class="Main-list">
                    <ul>
                        <li><a target="iframe" href="SelectPatent" class="li-a">查询专利</a></li>
                        <li><a target="iframe" href="AddPatent" class="li-a">添加专利</a></li>
                    </ul>
                </div>
            </div>
            <div id="CG-box" class="CG-box main_box">
                <a target="iframe" href="SelectAchievement">
                    <div class="CG main_info" onclick="downList(6)"><span style="width: 120px;" class="title"><span
                            class="iconfont icon-keyanchengguohuojiang"> </span>成果获奖</span><span
                            class="iconfont icon-xiangyou1 icon_right" style="margin-left: 30px;"></span></div>
                </a>

                <div class="Main-list">
                    <ul>
                        <li><a target="iframe" href="SelectAchievement" class="li-a">查询成果获奖</a></li>
                        <li><a target="iframe" href="AddAchievement" class="li-a">添加成果获奖</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
    <div class="right">
        <div class="nav-top">
            <div class="Main-title">
                <a>欢迎您：${sessionScope.user.uname}</a>
            </div>
            <button class="out" type="button" onclick="window.location.href='DownloadAllInfo'">全部信息导出</button>
            <div class="User">
                <div class="Picture">
                    <img src="static/images/login/头像.png" alt="">
                </div>
                <div class="UserName">
                    ${sessionScope.user.uname}
                </div>
                <div class="IconDown">
                    <span class="iconfont icon-xiangyou1 info_icon"></span>
                </div>
            </div>
            <div id="UserChoice" class="UserChoice">
                <div class="Choice1">
                    <a id="Choice1-a" target="iframe" href="UpdateUserInfo">修改个人信息</a>
                </div>
                <div class="Choice2">
                    <a href="loginout">注销</a>
                </div>
            </div>
        </div>
        <div class="page">
            <div class="page-contant">
                <iframe name="iframe" src="UpdateIndex" frameborder="0">

                </iframe>
            </div>
        </div>

    </div>
</div>
<div id="mask" class="mask">

</div>
</body>

</html>