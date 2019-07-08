<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>找回密码</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="/it_web/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="/it_web/css/font.css">
<link rel="stylesheet" href="/it_web/css/xadmin.css">
<script type="text/javascript"
	src="/it_web/bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="/it_web/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="/it_web/js/xadmin.js"></script>

</head>
<body class="login-bg">
	
	<div class="login layui-anim layui-anim-up">
	<label  style="float: right; margin: -20px 50px 0 0;"><a href="show.do"><b>返回主页</b></a> </label>
		<div class="message">找回密码</div>
		<div id="darkbannerwrap"></div>
		<form class="layui-form"  method="post" action="resetPassword.do" >
			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>用户名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="L_username" name="name" required="required"
						autocomplete="off" class="layui-input"><span id="spinfo1"></span>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>邮箱
				</label>
				<div class="layui-input-inline">
					<input type="text" placeholder="请输入邮件" required="required"
						class="layui-input" id="email" name="email"><span
						id="spinfo"></span>
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>新密码
				</label>
				<div class="layui-input-inline">
					<input type="password" id="L_pass" name="password"
						 required="required" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> <span
					class="x-red">*</span>确认密码
				</label>
				<div class="layui-input-inline">
					<input type="password" id="L_repass" name="repassword"
						required="required" autocomplete="off" class="layui-input"><span
						id="spinfo2"></span>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label> <input
					type="submit" class="layui-submit" value="确认" id="registerId" />

			</div>
		</form>
				
	</div>

	<!-- 底部结束 -->
	<script>
			//自定义验证规则
			 $(function () {
            var state = false;
            $("#email").focus(function () {
                if (state == false) {
                    $(this).val('');
                }
            });
            $("#email").blur(function () {
                    if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($(this).val()) == false) {
                        $("#spinfo").text("邮箱格式不正确，请重新填写");
                        $("#spinfo").css("color","red");
                        $(this).focus();
                    }
                    else {
                        $("#spinfo").text('');
                        state=true;
                    }
            });
	});
			 $(function () {
		            var state = false;
		            $("#L_username").focus(function () {
		                if (state == false) {
		                    $(this).val('');
		                }
		            });
		            $("#L_username").blur(function () {
		                    if (!/[@#\$%\^&\*]+/g.test($(this).val()) == false) {
		                        $("#spinfo1").text("含有特殊字符，请重新填写");
		                        $("#spinfo1").css("color","red");
		                        $(this).focus();
		                    }
		                    else {
		                        $("#spinfo1").text('');
		                        state=true;
		                    }
		            });
			});
			 $(function () {
		            $("#L_repass").blur(function () {
		                    if (($("#L_repass").val()) != ($("#L_pass").val()) ) {
		                        $("#spinfo2").text("重复密码和密码不同");
		                        $("#spinfo2").css("color","red");
		                        $("#L_repass").focus();
		                    }
		                    else {
		                        $("#spinfo2").text('');
		                        state=true;
		                    }
		            });
			});
	</script>
</body>
</html>