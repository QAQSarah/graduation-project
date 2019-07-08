<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<nav class="navbar navbar-default nav_bcolor">
	<div class="container">
		<div class="navbar-header">

			<a href="javascript:void(0)" class="navbar-brand" onclick="showWeb()"><!-- <b><i>&nbsp;&nbsp;I&nbsp;&nbsp;T</i></b>
				<br />
			<i>信息资源</i></a> -->
			<img alt="IT信息资源网" src="images/logo.jpg" width="150px" height="60px">
			<button class="navbar-toggle" data-toggle="collapse"
				data-target="#myMenu">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="collapse navbar-collapse  " id="myMenu">
			<ul class="nav navbar-nav">
				<li class="frist_trile" onclick="showWeb()"><a
					href="javascript:void(0)">文章 </a></li>
				<li class="frist_trile"><a href="javascript:void(0);" id="line">学习路线</a></li>
			</ul>

			<!--//表单/-->
			<form class="search bar7 navbar-right"
				action="">
				<input type="text" placeholder="请输入您要搜索的内容..." name="title" id="STC">
				<button type="button" id="ST"></button>
			</form>
			<ul class="nav navbar-nav navbar-form navbar-right text-center">
				<li><a href="register.do">注册</a></li>
				<li class="hidden-xs"><a href="#">|</a></li>
				<li data-toggle="modal" data-target="#myModal"><a href="#">登录</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
<script type="text/javascript">
$("#ST").click(function(){
	var c=$("#STC").val();
	$.post("article/findArticles.do",{title:c},function(data){
$("#showContext").html(data);
})});
$("#line").click(function(){
	$.post("line/findCategory.do",function(data){
		$("#withoutline").hide();
		$("#showContext").html(data);
		});
});
function showWeb(){
	$("#loveBZ1").removeClass("fa fa-heart").addClass("fa fa-heart-o");
	$("#PingLunBZ1").removeClass("fa fa-comments").addClass("fa fa-comments-o");
	$("#shouCBZ1").removeClass("fa fa-folder").addClass("fa fa-folder-o");
	$("#rem").css("background-color","#eee"); 
	$("#hot").css("background-color",""); 
	location.href="show.do";
	/* $.post("showR.do",function(data){
$("#showContext").html(data);
}); */
}
</script>
