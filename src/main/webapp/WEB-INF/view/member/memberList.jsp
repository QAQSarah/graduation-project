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
	<div class="x-nav">
		<span style="color: green;"> <a href="javascript:void(0)">首页</a>
			<b>/</b><a href="javascript:void(0)">用户管理</a><b>/</b> <a> <cite>用户列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so">
				<div style="float: left; width: 40%">
					<lable> <b>用户名:</b></lable>
					<br> <input type="text" id="sreachName" placeholder="请输入用户名"
						autocomplete="off" class="layui-input" style="width: 90%">
				</div>
				<div style="float: left; width: 40%">
					<lable> <b>邮箱账号:</b></lable>
					<br> <input type="text" id="sreachEmail" placeholder="请输入邮箱账号"
						autocomplete="off" class="layui-input" style="width: 90%" />
				</div>
				<div style="float: left; width: 36%">
					<label><b>状态:</b></label> <select id="sreachStatus"
						style="display: block; float: left;">
						<option value="" selected="selected">全部</option>
						<option value="1">已激活</option>
						<option value="0">已注销</option>
					</select>
				</div>
			</form>
			<div style="float: right; margin: 50px;">
				<button class="layui-btn">
					<a href="javascript:void(0)" onclick="sreachUser();"><i
						class="layui-icon">&#xe615;</i></a>
				</button>
			</div>
		</div>
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加用户','addUser.do',1000,600)">
			<i class="layui-icon"></i>添加
		</button>
		<span class="x-right" style="line-height: 40px">共有数据：${requestScope.pageBean.totalRecord}条</span>
		</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th hidden="hidden">
						<div class="layui-unselect header layui-form-checkbox"
							lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th>用户名</th>
					<th>邮箱</th>
					<th>加入时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td hidden="hidden">
							<div class="layui-unselect layui-form-checkbox"
								lay-skin="primary" data-id='2'>
								<i class="layui-icon">&#xe605;</i>
							</div>
						</td>
						<td>${user.name }</td>
						<td>${user.exmail}</td>
						<td>${user.createtime }</td>
						<c:if test="${user.status==1}">
							<td class="td-status"><span
								class="layui-btn layui-btn-normal layui-btn-mini">已激活</span></td>
						</c:if>
						<c:if test="${user.status==0}">
							<td class="td-status"><span
								class="layui-btn layui-btn-disabled layui-btn-mini">已停用</span></td>
						</c:if>
						<c:if test="${user.id!=1}">
							<td class="td-manage"><c:if test="${user.status==1}">
									<a onclick="member_stop(this,${user.id })" href="javascript:;"
										title="启用"> <i class="layui-icon">&#xe601;</i>
								</c:if> <c:if test="${user.status==0}">
									<a onclick="member_stop(this,${user.id })" href="javascript:;"
										title="停用"> <i class="layui-icon">&#xe62f;</i>
								</c:if> </a> <a title="编辑"
								onclick="x_admin_show('编辑','toUserInfo.do?id='+${user.id},1000,600)"
								href="javascript:;"> <i class="layui-icon">&#xe642;</i>
							</a> <a title="删除" onclick="member_del(this,${user.id})"
								href="javascript:;"> <i class="layui-icon">&#xe640;</i>
							</a></td>
						</c:if>
						<c:if test="${user.id==1}">
							<td class="td-manage">仅限查看</td>
						</c:if>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page">
			<div>
				<c:if test="${requestScope.pageBean.totalPage>1}">
					<a class="prev" href="javascript:void(0)"
						onclick="showByPagePrevious(${requestScope.pageBean.pageNum})">&lt;&lt;</a>
					<c:forEach begin="${requestScope.pageBean.start}"
						end="${requestScope.pageBean.end}" step="1" var="i">
						<a class="num" href="javascript:void(0)"
							onclick="showByPage(${i})">${i}</a>
						<!-- <span
					class="current">2</span> -->
					</c:forEach>
					<a class="next" href="javascript:void(0)"
						onclick="showByPageNext(${requestScope.pageBean.pageNum},${requestScope.pageBean.end})">&gt;&gt;</a>
				</c:if>
			</div>
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

		/*用户-停用*/
		function member_stop(obj, id) {
			layer.confirm('确认要进行此操作吗？', function(index) {
				if ($(obj).attr('title') == '启用') {
					//发异步把用户状态进行更改
					$(obj).attr('title', '停用')
					$(obj).find('i').html('&#xe62f;');
					$.post("editUserStatus.do",{id:id,type:"add"},function(data){
						var msg = data.msg;
						if("success"==msg){
							layer.msg('已停用!', {
								icon : 5,
								time : 1000
							});
							$.post("EditMenber.do", function(data) {
								$("#tab用户列表").html(data);
							});
						}
					});
				} else {
					$(obj).attr('title', '启用')
					$(obj).find('i').html('&#xe601;');
					$.post("editUserStatus.do",{id:id,type:"delete"},function(data){
						var msg = data.msg;
						if("success"==msg){
							layer.msg('已激活!', {
								icon : 5,
								time : 1000
							});
							$.post("EditMenber.do", function(data) {
								$("#tab用户列表").html(data);
							});
						}
					});
				}

			});
		}
		
		
		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$.post("deleteUser.do",{id:id},function(data){
						layer.msg(data, {
							icon : 1,
							time : 1000
						});
						$.post("EditMenber.do", function(data) {
							$("#tab用户列表").html(data);
						});
					
									});
				
				

			});
		}
		function showByPage(id){
			cid=$(".cid").val();
			$.post("/it_web/EditMenber.do?pageNum="+id,function(data){
				$("#tab用户列表").html(data);
				})
		}
		function showByPagePrevious(pageNum){
			var num=parseInt(pageNum)-1;
			cid=$(".cid").val();
			if(num>=1){
				$.post("/it_web/EditMenber.do?pageNum="+num,function(data){
					$("#tab用户列表").html(data);
					})
			}else{
				confirm("当前已经是最前页");
			}
			
		}
		function showByPageNext(pageNum,pageNumEnd){
			var num=parseInt(pageNum)+1;
			cid=$(".cid").val();
			if(num<=pageNumEnd){
				$.post("/it_web/EditMenber.do?pageNum="+num,function(data){
					$("#tab用户列表").html(data);
					})
			}else{
				confirm("后面没有信息咯........");
			}
		}
		
		
		function sreachUser(){
			var name=$("#sreachName").val();
			var exmail=$("#sreachEmail").val();
			var status=$("#sreachStatus").val();
			$.post("SreachMenber.do",{
				name:name,
				exmail:exmail,
				status:status
		}, function(data) {
				$("#tab用户列表").html(data);
			});
			
		}
	</script>

</body>

</html>