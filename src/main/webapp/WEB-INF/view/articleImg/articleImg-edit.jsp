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
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
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
	<form class="layui-form" id="registerform" method="post"
			enctype="multipart/form-data">
		<div>
		<input hidden="hidden" name="id" value="${articleImg.id}"/>
			<div class="box" title="文章图片">
				<center>
					<div style="margin: 20px">
						<div class="layui-input-inline">
							<div>
								<img id="ImgPr" width="120" height="120"  name="imgUrl" src="${articleImg.imgurl}"/>
							</div>
							<input id="imgUrl" name="file" type="file" />
						</div>
					</div>
				</center>
			</div>
		</div>
		<div>
			<label>文章分类</label> <select id="categoryId" name="articleId">
				<option value="">--请选择- -</option>
				<c:forEach items="${articles}" var="article">
					<option value="${article.id}">${article.title}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<input type="button" value="添加" id="tjbutton" />
		</div>
		</form>
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
			var userimg = $("#imgUrl").val();
			var categoryId = $("#categoryId").val();
			var registerform = $("#registerform");
			registerform.attr("action", "/it_web/editArticleImg.do");
			registerform.submit();
		});
		/*  上传图片实时查看 */
		jQuery.fn
				.extend({
					uploadPreview : function(opts) {
						var _self = this, _this = $(this);
						opts = jQuery.extend({
							Img : "ImgPr",
							Width : 100,
							Height : 100,
							ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
							Callback : function() {
							}
						}, opts || {});
						_self.getObjectURL = function(file) {
							var url = null;
							if (window.createObjectURL != undefined) {
								url = window.createObjectURL(file)
							} else if (window.URL != undefined) {
								url = window.URL.createObjectURL(file)
							} else if (window.webkitURL != undefined) {
								url = window.webkitURL.createObjectURL(file)
							}
							return url
						};
						_this
								.change(function() {
									if (this.value) {
										if (!RegExp(
												"\.(" + opts.ImgType.join("|")
														+ ")$", "i").test(
												this.value.toLowerCase())) {
											alert("选择文件错误,图片类型必须是"
													+ opts.ImgType.join("，")
													+ "中的一种");
											this.value = "";
											return false
										}
										//高版本Jquey使用  if ($.support.leadingWhitespace)
										if ($.support.leadingWhitespace) { //低版本jquery中使用$.browser.msie
											try {
												$("#" + opts.Img)
														.attr(
																'src',
																_self
																		.getObjectURL(this.files[0]))
											} catch (e) {
												var src = "";
												var obj = $("#" + opts.Img);
												var div = obj.parent("div")[0];
												_self.select();
												if (top != self) {
													window.parent.document.body
															.focus()
												} else {
													_self.blur()
												}
												src = document.selection
														.createRange().text;
												document.selection.empty();
												obj.hide();
												obj
														.parent("div")
														.css(
																{
																	'filter' : 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
																	'width' : opts.Width
																			+ 'px',
																	'height' : opts.Height
																			+ 'px'
																});
												div.filters
														.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
											}
										} else {
											$("#" + opts.Img)
													.attr(
															'src',
															_self
																	.getObjectURL(this.files[0]))
										}
										opts.Callback()
									}
								})
					}
				});
		$("#imgUrl").uploadPreview({
			Img : "ImgPr"
		});
	</script>

</body>

</html>