<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
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
<script type="text/javascript"
	src="/it_web/bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="/it_web/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="/it_web/js/xadmin.js"></script>
<script src="/it_web/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<!-- <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script> -->
<script
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>

</head>

<body>
	<div>
		<!-- 顶部开始 -->
		<div class="container">
			<div class="logo">
				<a href="#">IT信息资源管理</a>
			</div>
			<div class="left_open">
				<i title="展开左侧栏" class="iconfont">&#xe699;</i>
			</div>
			<ul class="layui-nav right" lay-filter="">
				<li class="layui-nav-item"><img alt="用户头像" style="border-radius:50%; overflow:hidden" src="${user.imgurl}" width="40px" height="40px"
										></li>
				<li class="layui-nav-item"><a href="javascript:void(0)">${user.name}</a>
				</li>
				<li class="layui-nav-item to-index"><a href="/it_web/show.do">前台首页</a>
				</li>
			</ul>

		</div>
		<!-- 顶部结束 -->
		<!-- 中部开始 -->
		<!-- 左侧菜单开始 -->
		<div class="left-nav">
			<div id="side-nav">
				<ul id="nav">
					<li><a _href="javascript:void(0)"> <i class="iconfont">&#xe6b8;</i>
							<cite>用户管理</cite> <i class="iconfont nav_right">&#xe697;</i>
					</a>
						<ul class="sub-menu">
							<li><a _href="javascript:void(0)" id="memberlist"> <i
									class="iconfont">&#xe6a7;</i> <cite>用户列表</cite>

							</a></li>
						</ul></li>
					<li><a href="javascript:void(0)"> <i class="iconfont">&#xe723;</i>
							<cite>文章管理</cite> <i class="iconfont nav_right">&#xe697;</i>
					</a>
						<ul class="sub-menu">
							<li><a _href="javascript:void(0)" id="articleList"> <i class="iconfont">&#xe6a7;</i>
									<cite>文章列表</cite>
							</a></li>
							<li><a _href="javascript:void(0)" id="articleCategoryList"> <i class="iconfont">&#xe6a7;</i>
									<cite>文章分类列表</cite>
							</a></li>
							<li><a _href="javascript:void(0)" id="articleImgList"> <i class="iconfont">&#xe6a7;</i>
									<cite>文章图片列表</cite>
							</a></li>
						</ul></li>
						
					
					<li><a href="javascript:;"> <i class="iconfont">&#xe723;</i>
							<cite>评论管理</cite> <i class="iconfont nav_right">&#xe697;</i>
					</a>
						<ul class="sub-menu">
							<li><a _href="javascript:void(0)" id="commentList"> <i class="iconfont">&#xe6a7;</i>
									<cite>评论列表</cite>
							</a></li>
						</ul></li>
					<li><a href="javascript:;"> <i class="iconfont">&#xe726;</i>
							<cite>学习路线管理</cite> <i class="iconfont nav_right">&#xe697;</i>
					</a>
						<ul class="sub-menu">
							<li><a _href="javascript:void(0)" id="leanList"> <i class="iconfont">&#xe6a7;</i>
									<cite>学习资源列表</cite>
							</a></li>
							<li><a _href="javascript:void(0)" id="leanCategoryList"> <i class="iconfont">&#xe6a7;</i>
									<cite>学习资源类别列表</cite>
							</a></li>
						</ul></li>
					<li><a href="javascript:;"> <i class="iconfont">&#xe6b4;</i>
							<cite>回复数据管理</cite> <i class="iconfont nav_right">&#xe697;</i>
					</a>
						<ul class="sub-menu">
							<li><a _href="javascript:void(0)" id="reponseList"> <i class="iconfont">&#xe6a7;</i>
									<cite>回复数据列表</cite>
							</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!-- <div class="x-slide_left"></div> -->
		<!-- 左侧菜单结束 -->
		<!-- 右侧主体开始 -->
		<div class="page-content">
			<div class="layui-tab tab" lay-filter="xbs_tab"
				lay-allowclose="false">
				<ul class="layui-tab-title">
					<li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<div id="weclome"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- 右侧主体结束 -->
		<!-- 中部结束 -->
		<!-- 底部开始 -->
		<div class="footer"
			style="position: fixed; bottom: 0.1%; background-color: rgb(10, 162, 132); color: white; width: 100%">
			<br />
			<div class="copyright" align="center">Copyright ©2019 lanqian</div>
			<br>

		</div>
	</div>
</body>
<!-- 底部结束 -->
<script>
	$(document).ready(function() {
		$.post("adminMrg/toWeclome.do", function(data) {
			$("#weclome").html(data);
		});

	});
	$("#memberlist").click(function() {
		$.post("EditMenber.do", function(data) {
			$("#tab用户列表").html(data);
		});
	});
	$("#articleList").click(function() {
		$.post("article/articleList.do", function(data) {
			$("#tab文章列表").html(data);
		});
	});
	$("#articleCategoryList").click(function() {
		$.post("category/articleCategoryList.do", function(data) {
			$("#tab文章分类列表").html(data);
		});
	});
	$("#articleImgList").click(function() {
		$.post("articleImgList.do", function(data) {
			$("#tab文章图片列表").html(data);
		});
	});
	$("#commentList").click(function() {
		$.post("commentList.do", function(data) {
			$("#tab评论列表").html(data);
		});
	});
	$("#reponseList").click(function() {
		$.post("reponseList.do", function(data) {
			$("#tab回复数据列表").html(data);
		});
	});
	$("#leanList").click(function() {
		$.post("line/leanList.do", function(data) {
			$("#tab学习资源列表").html(data);
		});
	});
	$("#leanCategoryList").click(function() {
		$.post("line/leanCategoryList.do", function(data) {
			$("#tab学习资源类别列表").html(data);
		});
	});
</script>

</html>