<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IT信息资源管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
	<div class="x-body layui-anim layui-anim-up">
		<blockquote class="layui-elem-quote">
			欢迎管理员： <span class="x-red">admin</span>！当前时间:<span id="newTime"></span>
		</blockquote>
		<fieldset class="layui-elem-field">
			<legend>数据统计</legend>
			<div class="layui-field-box">
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-body">
							<div class="layui-carousel x-admin-carousel x-admin-backlog"
								lay-anim="" lay-indicator="inside" lay-arrow="none"
								style="width: 100%; height: 90px;">
								<div carousel-item="">
									<ul class="layui-row layui-col-space10 layui-this">
										<li class="layui-col-xs2"><a href="javascript:;"
											class="x-admin-backlog-body">
												<h3>文章数</h3>
												<p>
													<cite>${articleCount}</cite>
												</p>
										</a></li>
										<li class="layui-col-xs2"><a href="javascript:;"
											class="x-admin-backlog-body">
												<h3>用户数</h3>
												<p>
													<cite>${userCount}</cite>
												</p>
										</a></li>
										<li class="layui-col-xs2"><a href="javascript:;"
											class="x-admin-backlog-body">
												<h3>收藏数</h3>
												<p>
													<cite>${collectCount }</cite>
												</p>
										</a></li>
										<li class="layui-col-xs2"><a href="javascript:;"
											class="x-admin-backlog-body">
												<h3>评论数</h3>
												<p>
													<cite>${commentCount}</cite>
												</p>
										</a></li>
										<li class="layui-col-xs2"><a href="javascript:;"
											class="x-admin-backlog-body">
												<h3>点赞数</h3>
												<p>
													<cite>${likeCount }</cite>
												</p>
										</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<blockquote class="layui-elem-quote layui-quote-nm">感谢layui,jquery,本系统页面显示由x-admin提供技术支持。</blockquote>
	</div>
	<script>
	$(document).ready(function() {
		var newTime = (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") //就得到普通的时间了 
		$('#newTime').html(newTime);
		 	});
	
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
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
		
	</script>
</body>
</html>