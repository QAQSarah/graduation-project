<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>用户信息</title>
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
		<div class="message">用户信息</div>
		<div id="darkbannerwrap"></div>
		<form class="layui-form" id="registerform" method="post" enctype="multipart/form-data">
			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>用户名
				</label>
				<div class="layui-input-inline">
				<input hidden="hidden" value="${user.id}" id="userId"/>
					<input type="text" id="L_username" name="name" required="required"
						autocomplete="off" class="layui-input"  readonly="readonly" disabled="disabled" value="${user.name}"><span id="spinfo1"></span>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>邮箱
				</label>
				<div class="layui-input-inline">
					<input type="text" placeholder="请输入邮件"  readonly="readonly" disabled="disabled"  required="required"
						class="layui-input" id="email" name="email" value="${user.exmail}"><span
						id="spinfo"></span>
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>密码
				</label>
				<div class="layui-input-inline">
					<input type="password" id="L_pass" name="password"
						 required="required" autocomplete="off"
						class="layui-input" value="${user.password}">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> <span
					class="x-red">*</span>确认密码
				</label>
				<div class="layui-input-inline">
					<input type="password" id="L_repass" name="repassword"
						required="required" autocomplete="off" class="layui-input" value=""><span
						id="spinfo2"></span>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label> <input
					type="button" class="layui-submit" value="确认" id="registerId" />

			</div>
		</form>
				
	</div>

	<!-- 底部结束 -->
	<script>
	$("#registerId").click(function (){
		var username=$("#L_username").val();
		var email=$("#email").val();
		var password=$("#L_pass").val();
		var userimg=$("#imgUrl").val();
	var id=$("#userId").val();
		var user={
			"id":id,
			"name":username,
         	"exmail":email,
         	"password":password,
         	};
			$.ajax({
	            url:"/it_web/editUser.do",
	            type:"POST",
	            dataType:"html",
	            data:user,
	            success:function(data){
	            	alert(data);
	            	setTimeout("location.reload();",500);
	            },
	            error:function(data){
	            	alert("保存失败!");
	            }
	         } );
	    
		});
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