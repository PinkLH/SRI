<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.request.contextPath}/SRI">
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>论文修改</title>
	<script type="text/javascript" src="static/js/thesis/UpdateThesis.js"></script>
	<link rel="stylesheet" href="static/css/thesis/UpdateThesis.css">
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
	.layui-form-radioed > i {
		color: #2989b6;
	}

	input {
		box-shadow: 0px 0px 20px #dddddd;
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
	function confirmUpdate(url) {
		layer.confirm('你确定修改吗？', {
			btn: ['确定', '取消'] //按钮
		}, function (index) {
			layer.close();
			$("#searchForm").attr("action", url);
			$("#searchForm").submit();
			layer.close(index);
		});
		// return false;
	}

	function showInfo(){
		$("#year").find("option[value = '${year}']").attr("selected", true);
		$("#month").find("option[value = '${month}']").attr("selected", true);
		$("#date").find("option[value = '${day}']").attr("selected", true);
		$("#ttypeRadio").find("input[type=radio][value='${thesis.ttype}']").next().click();
	}
</script>
<body>
<div class="article">
	<form id="searchForm" class="layui-form" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" name="uid" value="${thesis.uid}">
		<input type="hidden" name="tid" value="${thesis.tid}">
		<input type="hidden" name="taddress" value="${thesis.taddress}">
		<div class="layui-form-item">
			<label class="layui-form-label">论文名称：</label>
			<div class="layui-input-block">
				<input type="text" name="tname" required lay-verify="required" placeholder="请输入论文名称"
					   autocomplete="off" class="layui-input" value="${thesis.tname}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">发表期刊：</label>
			<div class="layui-input-block">
				<input type="text" name="tperiodical" required lay-verify="required" placeholder="请输入发表期刊"
					   autocomplete="off" class="layui-input" value="${thesis.tperiodical}">
			</div>
		</div>
		<div class="time">
			<p>
				发表时间：&nbsp&nbsp
				<select name="year" id="year" onchange="doChange()"></select>
				&nbsp年&nbsp
				<select name="month" id="month" onchange="doChange()"></select>
				&nbsp月&nbsp
				<select name="day" id="date"></select>
				&nbsp日&nbsp
			</p>
		</div>
		<br>
		<div class="layui-form-item">
			<label class="layui-form-label">论文类别：</label>
			<div id="ttypeRadio" class="layui-input-inline" style="margin-left: 110px;width: 600px;">
				<input type="radio" name="ttype" value="一区" title="一区" checked>
				<input type="radio" name="ttype" value="二区" title="二区">
				<input type="radio" name="ttype" value="三区,四区" title="三区,四区">
				<input type="radio" name="ttype" value="公开区" title="公开区">
				<input type="radio" name="ttype" value="核心区" title="核心区">
				<input type="radio" name="ttype" value="IE会议论文" title="IE会议论文">
			</div>
		</div>
		<div class="doc">
			<p style="font-size: 17px;display: inline;">添加附件：&nbsp;&nbsp;</p>
			<input class="layui-input" type="file" id="inputfile" name="uploadFile" required lay-verify="required"
				   style="display: inline-block;width: 500px;padding: 7px;border: 1px solid #fff">
			<br>
		</div>
		<div class="bottom-frame">
			<iframe id="preview" title="Inline Frame Example" style="border-radius: 5px;border: 2px solid #ccc;padding: 5px;margin-left: 15px;" width="100%" height="400px"
					src="static/previewName/previewName.html"></iframe>
		</div>
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
					<button type="button" onclick="confirmUpdate('UpdateThesisInfo')" class="layui-btn" lay-submit
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