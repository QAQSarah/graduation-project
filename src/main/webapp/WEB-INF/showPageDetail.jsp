<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>文章详情页面</title>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.css" />
<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.css" />
<link rel="stylesheet" href="css/main.css" />
<style type="text/css">
p {
	text-indent: 2em; /*em是相对单位，2em即现在一个字大小的两倍*/
}
</style>
</head>
<body class="bc_color">
	<div>
		<center>
			<h3>
				<i>${article.title}</i>
			</h3>
			<p>
				<i>${article.auther}</i>&nbsp; &nbsp;&nbsp;&nbsp;<b>. </b>&nbsp;&nbsp;&nbsp;&nbsp;
				<i>${article.createtime }</i>
			</p>
			<hr />
			<c:if test="${not empty articleImgs }">
				<div class="middle_right">
					<div id="lunbobox">
						<div id="toleft">&lt;</div>
						<div class="lunbo">

							<c:forEach items="${articleImgs }" var="img">
								<a href="#"><img src="${img.imgurl}" width="100%"
									height="400px"></a>
							</c:forEach>
						</div>
						<div id="toright">&gt;</div>

						<ul>
							<c:forEach items="${articleImgs }" var="img">
								<li></li>
							</c:forEach>
						</ul>

						<span></span>
					</div>
				</div>
			</c:if>
		</center>
		<div>${article.content}</div>
		<div style="width: 100%; padding: 5%; margin-left: 20%">
			<div style="width: 50%; height: 5%; float: left;">
				<i class="fa fa-heart-o fa-2x" aria-hidden="true" id="loveA"></i> <br />
				<small>喜欢</small>
			</div>
			<div style="width: 50%; height: 5%; float: left;">
				<i class="fa fa-folder-o fa-2x" aria-hidden="true" id="CA"></i><br />
				<small>收藏</small>
			</div>
		</div>
		<br>
		<div id="commentarea" style="float: left;">
			<fieldset>
				<legend>
					<b><i>评论区</i></b>
				</legend>
				<div>
					<c:forEach items="${commentList}" var="comment">
						<div style="border: 1px solid #eee;">
							<div>
								<%-- <input hidden="hidden" id="cid" value="${comment.id}"> --%>
								<p>${comment.comtext}</p>
								<span style="color: #238E23; float: right;">By:${comment.fuser}</span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;评论时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${comment.time}</span>
							</div>
							<div style="margin: 20px;">
								<c:forEach items="${comment.reponses}" var="reponse">
									<p style="color: #A8A8A8;">${reponse.context}</p>
									<small style="color: #238E23; float: right;">By:${reponse.fuser}</small>
									<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回复时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${reponse.time}</small>
								</c:forEach>
							</div>
							<div>
								<p style="float: right; margin-right: 10px; color: #7093DB"
									onclick="reply(${comment.id})">
									<a href="javascript:void(0);">回复</a>
								</p>
								<textarea style='display: none; resize: none;'
									id='reply${comment.id}' rows="3" cols="100"></textarea>
								<input style='display: none;' id='rs${comment.id}'
									onclick="rs(${comment.id})" value="发表" type="button" />
							</div>
						</div>
						<br>
						<br>
					</c:forEach>
					<form>
						<input type="text" id="aId" value="${article.id}" hidden="hidden">
						<textarea id="ccontext" rows="3" cols="100"
							placeholder="欢迎评论......" style="resize: none"></textarea>
						<input type="button" value="评论" id="pinglun">
					</form>
				</div>
			</fieldset>
		</div>
</body>
<script src="bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script>
	///轮播
	$(function() {
		$('#toright').hover(function() {
			$("#toleft").hide()
		}, function() {
			$("#toleft").show()
		})
		$('#toleft').hover(function() {
			$("#toright").hide()
		}, function() {
			$("#toright").show()
		})
	})

	var t;
	var index = 0;
	/////自动播放
	t = setInterval(play, 3000)

	function play() {
		index++;
		if (index > 4) {
			index = 0
		}
		// console.log(index)
		$("#lunbobox ul li").eq(index).css({
			"background" : "#999",
			"border" : "1px solid #ffffff"
		}).siblings().css({
			"background" : "#cccccc",
			"border" : ""
		})

		$(".lunbo a ").eq(index).fadeIn(1000).siblings().fadeOut(1000);
	};

	///点击鼠标 图片切换
	$("#lunbobox ul li").click(function() {

		//添加 移除样式
		//$(this).addClass("lito").siblings().removeClass("lito"); //给当前鼠标移动到的li增加样式 且其余兄弟元素移除样式   可以在样式中 用hover 来对li 实现
		$(this).css({
			"background" : "#999",
			"border" : "1px solid #ffffff"
		}).siblings().css({
			"background" : "#cccccc"
		})
		var index = $(this).index(); //获取索引 图片索引与按钮的索引是一一对应的
		// console.log(index);

		$(".lunbo a ").eq(index).fadeIn(1000).siblings().fadeOut(1000); // siblings  找到 兄弟节点(不包括自己）
	});

	$("#toright").click(function() {
		index++;
		if (index > 4) {
			index = 0
		}
		console.log(index);
		$(this).css({
			"opacity" : "0.5"
		})
		$("#lunbobox ul li").eq(index).css({
			"background" : "#999",
			"border" : "1px solid #ffffff"
		}).siblings().css({
			"background" : "#cccccc"
		})
		$(".lunbo a ").eq(index).fadeIn(1000).siblings().fadeOut(1000); // siblings  找到 兄弟节点(不包括自己）
	});
	$("#toleft,#toright").hover(function() {
		$(this).css({
			"color" : "black"
		})
	}, function() {
		$(this).css({
			"opacity" : "0.3",
			"color" : ""
		})
	})
	$("#loveA").click(
			function() {
				var uid = $("#userid").val();
				var articleid = $("#aId").val();
				if (!uid) {
					alert("您未登录不能设置为喜欢的哦!");
					return;
				} else {
					toggleBtn('#loveA', 'fa fa-heart fa-2x',
							'fa fa-heart-o fa-2x', 'class');
					if ($("#loveA").attr('class') == "fa fa-heart fa-2x") {
						likes = {
							"articleid" : articleid,
							"uid" : uid
						}
						$.ajax({
							url : "/it_web/addLikes.do",
							type : "POST",
							dataType : "json",
							data : likes,
							success : function(data) {
								var msg = data.msg;
								alert(msg);

							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								alert(XMLHttpRequest.status);
								alert(XMLHttpRequest.readyState);
								alert(textStatus);
							}
						});
					} else {
						likes = {
							"articleid" : articleid,
							"uid" : uid
						}
						$.ajax({
							url : "/it_web/removeLikes.do",
							type : "POST",
							dataType : "json",
							data : likes,
							success : function(data) {
								var msg = data.msg;
								alert(msg);

							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								alert(XMLHttpRequest.status);
								alert(XMLHttpRequest.readyState);
								alert(textStatus);
							}
						});
					}
				}

			});
	$("#CA").click(
			function() {
				var uid = $("#userid").val();
				var articleid = $("#aId").val();
				if (!uid) {
					alert("您未登录不能收藏哦!");
					return;
				} else {
					toggleBtn('#CA', 'fa fa-folder fa-2x',
							'fa fa-folder-o fa-2x', 'class');
					if ($("#CA").attr('class') == "fa fa-folder fa-2x") {
						collect = {
							"articleid" : articleid,
							"uid" : uid
						}
						$.ajax({
							url : "/it_web/addCollet.do",
							type : "POST",
							dataType : "json",
							data : collect,
							success : function(data) {
								var msg = data.msg;
								alert(msg);

							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								alert(XMLHttpRequest.status);
								alert(XMLHttpRequest.readyState);
								alert(textStatus);
							}
						});
					} else {
						collect = {
							"articleid" : articleid,
							"uid" : uid
						}
						$.ajax({
							url : "/it_web/removeCollet.do",
							type : "POST",
							dataType : "json",
							data : collect,
							success : function(data) {
								var msg = data.msg;
								alert(msg);

							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								alert(XMLHttpRequest.status);
								alert(XMLHttpRequest.readyState);
								alert(textStatus);
							}
						});
					}
				}

			});
	function toggleBtn(el, classA, classB, type) {
		var that = $(el);
		if (type == "class") {
			if (that.hasClass(classA)) {
				that.removeClass(classA).addClass(classB);
			} else {
				that.removeClass(classB).addClass(classA);
			}
		} else {
			if (that.text() == classA) {
				that.text(classB);
			} else {
				that.text(classA);
			}
		}
	}
	$("#pinglun").click(function() {
		var context = $("#ccontext").val();
		var articleid = $("#aId").val();
		var uid = $("#userid").val();
		var comment={
				"articleid" : articleid,
				"fromUid" : uid,
				"content":context
		}
		if (!uid) {
			alert("您未登录不能评论哦!");
			return;
		} else if(context== "undefined" || context == null || context == ""){
			alert("评论内容不正确,无法提交!");
		}else{
			$.ajax({
				url : "/it_web/addComment.do",
				type : "POST",
				dataType : "json",
				data :comment,
				success : function(data) {
					var msg = data.msg;
					alert(msg);
					$.post("showPage.do?id="+articleid,function(data){
						$("#showContext").html(data);
					});

				},
				error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				}
			});
		}
	});
	function reply(cid){
		var articleid = $("#aId").val();
		var uid = $("#userid").val();
		var cid=cid;
		if(!uid){
			alert("未登录不能进行回复!");
			return ;
		}else{
			if(document.getElementById('reply'+cid).style.display=='none'){
			document.getElementById('reply'+cid).style.display = 'block';
			document.getElementById('rs'+cid).style.display = 'block';
			}else{
				document.getElementById('reply'+cid).style.display = 'none';
				document.getElementById('rs'+cid).style.display = 'none';
			}
		}
	
	}
	function rs(cid){
		var articleid = $("#aId").val();
		var uid = $("#userid").val();
		var cid=cid;
		var rid='reply'+cid;
		var rcontext=$("#"+rid).val();
		var response={
				commentid:cid,
				fromUid:uid,
				content:rcontext
		}
		$.ajax({
			url : "/it_web/addResponse.do",
			type : "POST",
			dataType : "json",
			data :response,
			success : function(data) {
				var msg = data.msg;
				alert(msg);
				$.post("showPage.do?id="+articleid,function(data){
					$("#showContext").html(data);
				});

			},
			error : function(XMLHttpRequest, textStatus,
					errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
</script>

</html>
