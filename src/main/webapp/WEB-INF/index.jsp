<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>主页</title>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.css" />
<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.css" />
<link rel="stylesheet" href="css/main.css" />
</head>
<body class="bc_color">
	<div class="header"></div>
	<!--  主体-->
	<section>
		<div class="container">
			<div class="row" id="withoutline">
				<div class="col-md-8 col-xs-12">
					<nav>
						<ul class="nav navbar-nav second_title" id="second_title">
							<li id="rem"><a href="javascript:void(0)">推荐</a></li>
							<li class="" id="hot"><a href="javascript:void(0)">热门</a></li>
							<c:forEach items="${categoryList}" var="category">
								<li><a href="javascript:void(0)"
									onclick="seleteByCategory(this.id)" id="${category.id }">${category.categoryname }</a></li>
							</c:forEach>
							<li class="sideline" style="left: 248px; width: 36px;"></li>
						</ul>
					</nav>
				</div>
				<div class="col-md-4 col-md-pull-1 hidden-xs"></div>
			</div>
			<!--结果显示列表-->
			<div class="row">
				<div class="col-md-8" id="showContext"></div>
				<!--登录框或个人框-->
				<div class="col-md-4">
					<div class="thumbnail">
						<div class="caption">
							<div class="panel panel-success" id="loginmodel">
								<div class="panel-heading">
									<h3 class="panel-title text-center"></h3>

								</div>
								<div class="panel-body text-center">
									<p>
										<small>拥有账号，获得更多资讯</small>
									</p>
									<button class="btn btn-success" style="width: 100%"
										id="register">立即注册</button>
									<div>
										<p>
											<br /> <small>已有账号？</small> <a href="#" data-toggle="modal"
												data-target="#myModal">登录</a><br />
										</p>
									</div>
								</div>
							</div>
							<div class="panel panel-success" id="userinfo">
								<div class="panel-heading">
								<input  type="text" id="userid" name="userid" hidden="hidden"/>
									<h3 class="panel-title text-center" id="showusername"></h3>

								</div>
								<div class="panel-body text-center">
									<img alt="用户头像" src="" width="100px" height="100px"
										id="showtoux">
									<div>
										<p>
											<br /><span
												style="width: 30%; height: 5%; float: left; display: block;" id="loveBZ"><i
												class="fa fa-heart-o" aria-hidden="true" id="loveBZ1"></i><br /> <small><a href="javascript:void(0)">喜欢</a></small></span>
											<span
												style="width: 30%; height: 5%; float: left; display: block;" id="PinglunBZ"><i
												class="fa fa-comments-o" aria-hidden="true" id="PingLunBZ1"></i><br /> <small><a href="javascript:void(0)">评论</a></small></span>
											<span
												style="width: 30%; height: 5%; float: left; display: block;" id="shouCBZ"><i
												class="fa fa-folder-o" aria-hidden="true" id="shouCBZ1"></i><br /> <small><a href="javascript:void(0)">收藏</a></small></span>
												<br/>
										</p>
									</div>
									<br />
									<div>
										<hr />
										<button id="vipuser" class="btn btn-success"
											style="width: 100%">前往后台管理</button>
									</div>
								</div>
							</div>
							<div class="thumbnail">
								<div class="caption">
									<p id="showRole"></p>
									<p id="joinTime"></p>
									<input id="showpassword" type="hidden" value=""></input>
								</div>
							</div>
						</div>
					</div>

				</div>
	</section>

	<!--模态框-->
	<div class="modal fade" id="myModal" xdata-backdrop="false"
		data-keyboard="true" xdata-show="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title text-center">用户登录</h4>
				</div>
				<div class="modal-body">
					<form action="">
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user"></i></span>
								<input class="form-control" type="text" id="username"
									name="username" placeholder="邮箱/用户名" />
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input class="form-control" type="password" id="password"
									name="password" placeholder="请输入密码" />
							</div>
						</div>
						<div class="form-group">
							<input id="rember" type="checkbox" /> <label for="rember">记住我</label>
							<a class="pull-right" href="forgetting.do">忘记密码</a>
						</div>
						<div class="form-group">
							<div class="btn btn-primary btn-block" id="loginButton">登录</div>
						</div>
						<p>
							还没有账号？ <a href="register.do">立即注册!</a>
						</p>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="myclose1">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--脚部  -->
	<div class="footer"></div>
</body>
<script src="bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<!-- <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script> -->
<script
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script>
	$(document).ready(function() {
				$("#rem").css("background-color","#eee"); 
				$.post("showR.do",function(data){
					$("#showContext").html(data);
				});
				$(".header").load("header.jsp");
				$(".footer").load("footer.jsp");
				$("#userinfo").hide();
				/* var cid=$(".cid").val();
				$("#"+cid).css("background-color","#eee")   */
				 	});
	$("#register").click(function() {
		$(window).attr('location', 'register.do');
		
	});
	$("#loginButton").click(
			function() {
				var name = $("#username").val();
				var password = $("#password").val();
				if(name==""||password==""){
					alert("用户名或者密码不能为空!");
					return;
				}
				var user = "";
				if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($(
						"#username").val()) == false) {
					user = {
						"name" : name,
						"password" : password
					}

				} else {
					user = {
						"exmail" : name,
						"password" : password
					}
				}
				$.ajax({
					url : "/it_web/login.do",
					type : "POST",
					dataType : "json",
					data : user,
					success : function(data) {
						$("#myclose1").click();
						$("#myclose1").click();
		      			var msg = data.msg;
						if ("管理员" == msg) {
							$("#loginmodel").hide();
							$("#userinfo").show();
							$("#vipuser").show();
						} 
						if (msg == "普通用户") {
							$("#loginmodel").hide();
							$("#vipuser").hide();
							$("#userinfo").show();
						} 
						if (msg == "访客") {
							alert("很抱歉,没有该用户");
						}
						if(msg==""){
							alert("该账号已被停用");
						}
						var user=data.user;
						$("#showtoux").src=user.imgurl;
						$('#showtoux').attr("src",user.imgurl);
						$('#showusername').html(user.name);
						$('#userid').val(user.id);
						var Identity="目前身份:";
						Identity=Identity+data.msg;
						$('#showRole').html(Identity);
						var joinTime="加入时间:";
						var newTime = (new Date(user.createtime)).Format("yyyy-MM-dd hh:mm:ss.S") //就得到普通的时间了 
						joinTime=joinTime+newTime;
						$('#joinTime').html(joinTime);
						$('#showpassword').attr("value",user.password);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest.status);
						alert(XMLHttpRequest.readyState);
						alert(textStatus);
					}
				});
			});

	/*
			js由毫秒数得到年月日
			使用： (new Date(data[i].creationTime)).Format("yyyy-MM-dd hh:mm:ss.S")
			*/
			Date.prototype.Format = function (fmt) { //author: meizz
			    var o = {
			        "M+": this.getMonth() + 1, //月份
			        "d+": this.getDate(), //日
			        "h+": this.getHours(), //小时
			        "m+": this.getMinutes(), //分
			        "s+": this.getSeconds(), //秒
			        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
			        "S": this.getMilliseconds() //毫秒
			    };
			    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			    for (var k in o)
			        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			    return fmt;
			};
			
	$("#vipuser").click(function(){
		var username=$("#showusername").html();
		var password=$('#showpassword').val();
		var user='';
		user = {
			"name" : username,
			"password" : password
		}
		$.ajax({
			type : "post",
			url : "/it_web/toManagement.do",
			dataType: 'json',
			data:user,
			success : function (data) {
			document.write("<form action=\"tot.do\" method=\"post\" name='formhid' style='display:none'>");  
			document.write("<input type=hidden name='name' value='"+data.user.name+"'/>"); 
			document.write("<input type=hidden name='password' value='"+data.user.password+"'/>"); 
			document.write("<input type=hidden name='imgurl' value='"+data.user.imgurl+"'/>"); 
			document.write("</form>");  
			document.formhid.submit();
			}
			    });
	});
	function seleteByCategory(id){
		$("#rem").css("background-color","");
		$("#loveBZ1").removeClass("fa fa-heart").addClass("fa fa-heart-o");
		$("#PingLunBZ1").removeClass("fa fa-comments").addClass("fa fa-comments-o");
		$("#shouCBZ1").removeClass("fa fa-folder").addClass("fa fa-folder-o");
		$.post("showByCategory.do?id="+id,function(data){
			$("#showContext").html(data);
			})
	}
	function showPage(id){
		$.post('showPage.do?id='+id,function(data){
			$("#showContext").html(data);
			})
	}
	$("#hot").click(function(){
		$("#loveBZ1").removeClass("fa fa-heart").addClass("fa fa-heart-o");
		$("#PingLunBZ1").removeClass("fa fa-comments").addClass("fa fa-comments-o");
		$("#shouCBZ1").removeClass("fa fa-folder").addClass("fa fa-folder-o");
		$("#rem").css("background-color",""); 
		$.post("show_hot_article.do",function(data){
	$("#showContext").html(data);
	})});
	$("#rem").click(function(){
		$("#loveBZ1").removeClass("fa fa-heart").addClass("fa fa-heart-o");
		$("#PingLunBZ1").removeClass("fa fa-comments").addClass("fa fa-comments-o");
		$("#shouCBZ1").removeClass("fa fa-folder").addClass("fa fa-folder-o");
		$("#rem").css("background-color","#eee"); 
		$("#hot").css("background-color",""); 
		$.post("showR.do",function(data){
	$("#showContext").html(data);
	})});
	$("#loveBZ").click(function(){
	var uid=$("#userid").val();
	$.post("findLikes.do",{userId:uid},function(data){
		$("#rem").css("background-color",""); 
		$("#hot").css("background-color",""); 
		$("#loveBZ1").removeClass("fa fa-heart-o").addClass("fa fa-heart");
		$("#PingLunBZ1").removeClass("fa fa-comments").addClass("fa fa-comments-o");
		$("#shouCBZ1").removeClass("fa fa-folder").addClass("fa fa-folder-o");
		$("#showContext").html(data);
		});
	});
	$("#PinglunBZ").click(function(){
		var uid=$("#userid").val();
		$.post("findComment.do",{userId:uid},function(data){
			$("#rem").css("background-color",""); 
			$("#hot").css("background-color",""); 
			$("#loveBZ1").removeClass("fa fa-heart").addClass("fa fa-heart-o");
			$("#PingLunBZ1").removeClass("fa fa-comments-o").addClass("fa fa-comments");
			$("#shouCBZ1").removeClass("fa fa-folder").addClass("fa fa-folder-o");
			$("#showContext").html(data);
			});
		});
	$("#shouCBZ").click(function(){
		var uid=$("#userid").val();
		$.post("findC.do",{userId:uid},function(data){
			$("#rem").css("background-color",""); 
			$("#hot").css("background-color",""); 
			$("#loveBZ1").removeClass("fa fa-heart").addClass("fa fa-heart-o");
			$("#PingLunBZ1").removeClass("fa fa-comments").addClass("fa fa-comments-o");
			$("#shouCBZ1").removeClass("fa fa-folder-o").addClass("fa fa-folder");
			$("#showContext").html(data);
			});
		});
</script>

</html>
