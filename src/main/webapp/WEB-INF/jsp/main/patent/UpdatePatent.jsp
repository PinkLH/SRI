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
	<title>专利修改</title>
	<script type="text/javascript" src="static/js/patent/UpdatePatent.js"></script>
	<link rel="stylesheet" href="static/css/patent/UpdatePatent.css">
	<link rel="stylesheet" href="static/layform/layform.css">
	<script src="static/js/jq/jquery-3.5.1.min.js"></script>

</head>
<style>
	.layui-input:focus,
	.layui-textarea:focus {
		border-color: #2989b6 !important;
	}
	.layui-input:hover {
		box-shadow: 0px 0px 10px #dddddd;
	}
	.layui-form-radio:hover *,
	.layui-form-radioed,
	.layui-form-radioed>i {
		color: #2989b6;
	}

	input{
		box-shadow: 0px 0px 20px #dddddd;
	}
	.layui-btn{
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
		layer.confirm('你确定修改吗？', {
			btn: ['确定', '取消'] //按钮
		}, function (index) {
			layer.close();
			$("#searchForm").attr("action", url);
			$("#searchForm").submit();
			layer.close(index);
		});
	}

	function showInfo(){
		$("#year").find("option[value = '${pyear}']").attr("selected", true);
		$("#month").find("option[value = '${pmonth}']").attr("selected", true);
		$("#date").find("option[value = '${pday}']").attr("selected", true);
		$("#pstate").find("input[type=radio][value='${patent.pstate}']").next().click();
	}
</script>
<body>
<div class="article">
	<form id="searchForm" class="layui-form" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" name="oldPid" value="${patent.pid}">
		<input type="hidden" name="uid" value="${patent.uid}">
		<input type="hidden" name="paddress" value="${patent.paddress}">
		<div class="layui-form-item">
			<label class="layui-form-label">专利状态：</label>
			<div id="pstate" class="layui-input-inline" style="margin-left: 110px;width: 600px;">
				<input type="radio" name="pstate" value="申请" title="申请" checked>
				<input type="radio" name="pstate" value="授权" title="授权">
			</div>
		</div>
		<div class="patentNum">
			<div class="layui-form-item">
				<label class="layui-form-label">申请号/专利号：</label>
				<div class="layui-input-block">
					<input type="text" name="pid" value="${patent.pid}" required lay-verify="required" placeholder="请输入申请号/专利号"
						   autocomplete="off" class="layui-input" style="width: 300px;margin-left: 40px;">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">专利名称：</label>
			<div class="layui-input-block">
				<input type="text" name="pname" value="${patent.pname}" required lay-verify="required" placeholder="请输入专利名称"
					   autocomplete="off" class="layui-input" style="width: 400px;">
			</div>
		</div>
		<div class="patentTime">
			<div class="time">
				<p>
					<label style="font-size: 17px;font-weight: 400;">专利申请时间/专利授权时间：</label>
					<select name="year" id="year" onchange="doChange()"></select>
					&nbsp年&nbsp
					<select name="month" id="month" onchange="doChange()"></select>
					&nbsp月&nbsp
					<select name="day" id="date"></select>
					&nbsp日&nbsp
				</p>
			</div>
		</div>
		<div class="layui-form-item" style="width: 876px;">
			<label class="layui-form-label"><br>专利发明人：</label>
			<div class="layui-input-block">
				<table align="center" width="100%" id="tab">
					<tr>
						<th>工号</th>
						<th>姓名</th>
						<th>操作</th>
					</tr>
					<c:if test="${pps == '[]'}">
						<tr id="show" class="show">
							<td>
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="text" name="ppid" required lay-verify="required"
											   placeholder="请输入成员工号" autocomplete="off" class="layui-input">
									</div>
								</div>
							</td>
							<td>
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="text" name="ppname" required lay-verify="required"
											   placeholder="请输入成员姓名" autocomplete="off" class="layui-input">
									</div>
								</div>
							</td>
							<td>
								<button type="button" class="layui-btn layui-btn-primary" onclick="addRow()">添加</button>
								<button type="button" class="layui-btn layui-btn-primary" onclick="deleteRow(this)">删除</button>
							</td>
						</tr>
					</c:if>
					<c:if test="${pps != '[]'}">
						<c:forEach items="${pps}" var="pp">
							<tr id="show" class="show">
								<td>
									<div class="layui-form-item">
										<div class="layui-input-inline">
											<input type="text" name="ppid" required lay-verify="required"
												   placeholder="请输入成员工号" autocomplete="off" class="layui-input" value="${pp.ppid}">
										</div>
									</div>
								</td>
								<td>
									<div class="layui-form-item">
										<div class="layui-input-inline">
											<input type="text" name="ppname" required lay-verify="required"
												   placeholder="请输入成员姓名" autocomplete="off" class="layui-input" value="${pp.ppname}">
										</div>
									</div>
								</td>
								<td>
									<button type="button" class="layui-btn layui-btn-primary" onclick="addRow()">添加</button>
									<button type="button" class="layui-btn layui-btn-primary" onclick="deleteRow(this)">删除</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>

				</table>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">专利权人：</label>
			<div class="layui-input-block">
				<input type="text" name="ppatentee" value="${patent.ppatentee}" required lay-verify="required" placeholder="请输入专利权人"
					   autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="doc">
			<p style="font-size: 17px;display: inline;">添加附件：&nbsp;&nbsp;</p>
			<input class="layui-input" type="file" id="inputfile" name="uploadFile" required lay-verify="required"
				   style="display: inline-block;width: 500px;padding: 7px;border: 1px solid #fff">
			<br>
		</div>
		<div class="bottom-frame"></div>
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
		<div class="foot">

			<div class="layui-form-item1">
				<div class="layui-input-block">
					<button type="button" class="layui-btn" onclick="confirmAdd('UpdatePatentInfo')" lay-submit
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