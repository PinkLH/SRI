<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="icon" href="static/images/favicon.png" mce_href="static/images/favicon.png" type="image/x-icon" />
    <link rel="stylesheet" href="static/css/login/Login.css">
    <link rel="stylesheet" href="static/font/login/iconfont.css">
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <script src="static/js/login/Login.js"></script>
</head>

<body>
    <header>
        <a target="_blank" href="http://www.cqwu.net/"><img id="logo" src="static/images/login/loginLogo.svg" alt=""></a>
        <div id="schoolweb"><a target="_blank" href="http://www.cqwu.net/">学校官网</a></div>
    </header>
    <article>
        <div id="title">
            <h1>科研信息管理系统</h1>
            <br>
            <h2>Scientific research information<br>management system</h2>
        </div>
        <form id="form1" action="loginform" method="post">
            <div id="login">
                <h1>登 录</h1>

                <div id="id">
                    <span class="iconfont icon-yonghu"></span>
                    <input onkeydown="enterHandler(window.event);" type="text" id="number" name="number" placeholder="请输入工号或电话" style="outline:none;border:0">
                </div>
                <div id="password">
                    <span class="iconfont icon-ai-password"></span>
                    <input onkeydown="enterHandler(window.event);" type="password" id="upwd" name="upwd" placeholder="请输入密码" style="outline:none;border:0">
                </div>
                <div class="choice">
                	<input onkeydown="enterHandler(window.event);" id="user" type="radio" name="utype" value="user" checked><span>用户</span>
                    <input onkeydown="enterHandler(window.event);" id="administer" type="radio" name="utype" value="admin"><span>管理员</span>
                </div>
                <input id="submitb" type="button" value="登   录"/>
				<h4 id="error" style="color: red; margin: 10px 0px"></h4>
            </div>
        </form>
        <script>
				var f = document.getElementById("form1");
				var number = document.getElementById("number");
				var upwd = document.getElementById("upwd");
				var submitbtn = document.getElementById("submitb");

                function enterHandler(event)
                {
                    var keyCode = event.keyCode ? event.keyCode
                        : event.which ? event.which
                            : event.charCode;
                    if (keyCode == 13) {
                        submitbtn.click();
                    }
                }

				submitbtn.onclick = function(){
					if(number.value == "" && upwd.value != ""){
						$("#error").html("<span>请输入工号或电话</span>");
					}else if(number.value != "" && upwd.value == ""){
						$("#error").html("<span>请输入密码</span>");
					}else{
						var formParam = $("#form1").serialize();
						//alert(formParam);
			       		$.ajax({
			       			type:"post",
			       			url:"loginformAjax",
			       			dataType:"json",
			       			data:formParam,
			       			cache:false,
			       			success:function(data){
			       				if(data.info == "loginError"){
			       					$("#error").html("<span>"+data.msg+"</span>");
			       				}else{
			       					$("#error").html("");
					           		f.submit();
			       				}
			       			},
			        		error:function(){
			                 console.log("提交ajax函数异常");
			             	}
			       		});
					}
				}
		</script>
    </article>

    <footer>
        <div id="foot">
            <p>Copyright<span class="iconfont icon-banquan" style="margin-left: 3px;margin-right: 3px;"></span>
                Chongqing University Of Arts And Sciences All Rights Reserved</p>
        </div>
    </footer>
</body>

</html>