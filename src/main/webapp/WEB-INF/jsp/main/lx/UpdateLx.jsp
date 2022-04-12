<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.request.contextPath}/SRI">
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>立项修改</title>
	<script src="static/js/jq/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="static/js/lx/UpdateLx.js"></script>
	<link rel="stylesheet" href="static/css/lx/UpdateLx.css">
	<link rel="stylesheet" href="static/layform/layform.css">

	<style>
		th,
		table tr td {
			border: 1px solid #ccc;
		}

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

		.layui-btn {
			padding: 0 2%;
		}

		select {
			border-radius: 5px;
			border: 0px;
			font-size: 18px;
			height: 30px;
			box-shadow: 0px 0px 10px #dddddd;
		}
	</style>

	<script type="text/javascript">
		function confirmAdd(url) {
			var lmoneyVal = $("#lmoney").val();
			var n = /^[0-9]*$/;
			if (n.test(lmoneyVal)){
				layer.confirm('你确定修改吗？', {
					btn: ['确定', '取消'] //按钮
				}, function (index) {
					layer.close();
					$("#searchForm").attr("action", url);
					$("#searchForm").submit();
					layer.close(index);
				});
				// return false;
			}else {
				layer.alert('经费只能是数字！');
			}

		}
		function showInfo(){
			$("#year").find("option[value = '${leyear}']").attr("selected", true);
			$("#month").find("option[value = '${lemonth}']").attr("selected", true);
			$("#date").find("option[value = '${leday}']").attr("selected", true);
			$("#years").find("option[value = '${lbyear}']").attr("selected", true);
			$("#months").find("option[value = '${lbmonth}']").attr("selected", true);
			$("#dates").find("option[value = '${lbday}']").attr("selected", true);
			$("#ltypeRadio").find("input[type=radio][value='${lx.ltype}']").next().click();
		}
	</script>
</head>

<body>
<div class="all">

	<form id="searchForm" class="layui-form" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" name="oldLid" value="${lx.lid}">
		<input type="hidden" name="uid" value="${lx.uid}">
		<input type="hidden" name="laddress" value="${lx.laddress}">
		<div class="layui-form-item">
			<label class="layui-form-label">项目编号：</label>
			<div class="layui-input-block">
				<input type="text" name="lid" required lay-verify="required" placeholder="请输入项目编号"
					   autocomplete="off" class="layui-input" value="${lx.lid}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">项目名称：</label>
			<div class="layui-input-block">
				<input type="text" name="lname" required lay-verify="required" placeholder="请输入项目名称"
					   autocomplete="off" class="layui-input" value="${lx.lname}">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">项目级别：</label>
			<div class="layui-input-block" id="ltypeRadio">
				<input type="radio" name="ltype" value="国家级" title="国家级" checked>
				<input type="radio" name="ltype" value="省级" title="省级">
				<input type="radio" name="ltype" value="校级" title="校级">
			</div>
		</div>

		<div class="time">
			<p>
				开始时间：&nbsp&nbsp&nbsp <select name="lbyear" id="years" onchange="doChange1()"></select>
				&nbsp年&nbsp
				<select name="lbmonth" id="months" onchange="doChange1()"></select>
				&nbsp月&nbsp
				<select name="lbday" id="dates"></select>
				&nbsp日&nbsp
			</p>
			<p>
				结束时间：&nbsp&nbsp&nbsp
				<select name="leyear" id="year" onchange="doChange()"></select>
				&nbsp年&nbsp
				<select name="lemonth" id="month" onchange="doChange()"></select>
				&nbsp月&nbsp
				<select name="leday" id="date"></select>
				&nbsp日&nbsp
			</p>
		</div>


		<!--time-->

		<div class="layui-form-item">
			<label class="layui-form-label">经&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp费:</label>
			<div class="layui-input-block">
				<input type="text" id="lmoney" name="lmoney" required lay-verify="required" placeholder="单位（元）"
					   autocomplete="off" class="layui-input" value="${lx.lmoney}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">负&nbsp&nbsp责&nbsp&nbsp人:</label>
			<div class="layui-input-block">
				<input type="text" name="lperson" required lay-verify="required" placeholder="请输入负责人姓名"
					   autocomplete="off" class="layui-input" value="${lx.lperson}">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label"><br>项目成员：</label>
			<div class="layui-input-block">
				<table align="center" width="100%" id="tab">
					<tr>
						<th>工号</th>
						<th>姓名</th>
						<th>操作</th>
					</tr>

					<c:if test="${lps == '[]'}">
						<tr id="show" class="show">
							<td>
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="text" name="lpid" required lay-verify="required"
											   placeholder="请输入成员工号" autocomplete="off" class="layui-input">
									</div>
								</div>
							</td>
							<td>
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="text" name="lpname" required lay-verify="required"
											   placeholder="请输入成员姓名" autocomplete="off" class="layui-input">
									</div>
								</div>
							</td>

							<td width="30%">
								<button type="button" class="layui-btn layui-btn-primary" onclick="addRow()">添加</button>
								<button type="button" class="layui-btn layui-btn-primary" onclick="deleteRow(this)">删除</button>
							</td>
						</tr>
					</c:if>
					<c:if test="${lps != '[]'}">
						<c:forEach items="${lps}" var="lp">

							<tr id="show" class="show">
								<td>
									<div class="layui-form-item">
										<div class="layui-input-inline">
											<input type="text" name="lpid" required lay-verify="required"
												   placeholder="请输入成员工号" autocomplete="off" class="layui-input" value="${lp.lpid}">
										</div>
									</div>
								</td>
								<td>
									<div class="layui-form-item">
										<div class="layui-input-inline">
											<input type="text" name="lpname" required lay-verify="required"
												   placeholder="请输入成员姓名" autocomplete="off" class="layui-input" value="${lp.lpname}">
										</div>
									</div>
								</td>

								<td width="30%">
									<button type="button" class="layui-btn layui-btn-primary" onclick="addRow()">添加</button>
									<button type="button" class="layui-btn layui-btn-primary" onclick="deleteRow(this)">删除</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>

				</table>
			</div>
		</div>

		<div class="wenjian">
			<p style="font-size: 17px;display: inline;">添加附件：&nbsp;&nbsp;</p>
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
					<button type="button" class="layui-btn" onclick="confirmAdd('UpdateLxInfo')" lay-submit
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