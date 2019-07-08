<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>IT信息资源管理</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" href="/it_web/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="/it_web/css/font.css">
<link rel="stylesheet" href="/it_web/css/xadmin.css">
<style>
.box {
	position: relative;
	border: 1px solid #ccc;
}

.box::before {
	content: attr(title);
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	-webkit-transform: translate(-50%, -50%);
	padding: 0 10px;
	background-color: #fff;
}
</style>
<script type="text/javascript"
	src="/it_web/bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="/it_web/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="/it_web/js/xadmin.js"></script>
<script src="/it_web/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<!-- <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script> -->
<script
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>

</head>

<body class="layui-anim layui-anim-up">
	<div class="x-body">
		<div class="layui-row">
			<div style="float: left; width: 45%; margin-right: 5%">
				<lable> <b>标题:</b></lable>
				<br>
				<input hidden="hidden" id="articleId" value="${article.id}"/> <input type="text" id="sreachName" placeholder="请输入文章标题"
					autocomplete="off" class="layui-input" style="width: 100%" value="${article.title}">
			</div>
			<div style="float: left; width: 45%; margin-right: 5%">
				<lable> <b>作者:</b></lable>
				<br> <input type="text" id="sreachAuther" placeholder="请输入文章作者"
					autocomplete="off" class="layui-input" style="width: 100%" value="${article.auther}">
			</div>
		</div>
		<hr>
		<div>
			<div class="box" title="文章正文">
				<center>
					<div>
						<textarea rows="40" cols="130" id="content" >${article.content}</textarea>
					</div>
				</center>
			</div>
		</div>
		<div>
			<label>文章分类</label> <select id="categoryId">
				<option value="">--请选择- -</option>
				<c:forEach items="${articleCategorys}" var="category">
					<option value="${category.id}">${category.categoryname}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<input type="button" value="修改" id="tjbutton" />
		</div>
	</div>
	<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;

			//执行一个laydate实例
			laydate.render({
				elem : '#start' //指定元素
			});

			//执行一个laydate实例
			laydate.render({
				elem : '#end' //指定元素
			});
		});
		$("#tjbutton").click(function() {
			var title = $("#sreachName").val();
			var auther = $("#sreachAuther").val();
			var content = $("#content").val();
			var categoryId = $("#categoryId").val();
			var id=$("#articleId").val();
			$.post("/it_web/article/editArticle.do", {
				id:id,
				title : title,
				author : auther,
				content : content,
				categoryId : categoryId
			}, function(data) {
				alert(data);
				setTimeout("window.location.reload()",500);
			});
		});
	</script>

</body>

</html>