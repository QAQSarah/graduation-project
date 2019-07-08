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
		<a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:void(0);" onclick="reloadInfo()" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加图片','toAddArticleImg.do',600,600)">
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
					<th>图片</th>
					<th>所属文章</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${imgs}" var="img">
					<tr>
						<td hidden="hidden">
							<div class="layui-unselect layui-form-checkbox"
								lay-skin="primary" data-id='2'>
								<i class="layui-icon">&#xe605;</i>
							</div>
						</td>
						<td><img src="${img.imgurl }" width="100px" height="100px"/></td>
						<td>${img.articleName}</td>
						<td class="td-manage"><a title="编辑"
							onclick="x_admin_show('编辑','/it_web/toEditArticleImg.do?id='+${img.id},1000,600)"
							href="javascript:void(0)"> <i class="layui-icon">&#xe642;</i>
						</a> <a title="删除" onclick="member_del(this,${img.id})"
							href="javascript:void(0)"> <i class="layui-icon">&#xe640;</i>
						</a></td>
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

		
		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$.post("/it_web/deleteArticleImg.do",{id:id},function(data){
							layer.msg(data, {
								icon : 1,
								time : 1000
							});
							$.post("/it_web/articleImgList.do", function(data) {
								$("#tab文章图片列表").html(data);
							});
				});
			});
		}
		function showByPage(id){
			cid=$(".cid").val();
			$.post("/it_web/articleImgList.do?pageNum="+id,function(data){
				$("#tab文章图片列表").html(data);
				})
		}
		function showByPagePrevious(pageNum){
			var num=parseInt(pageNum)-1;
			cid=$(".cid").val();
			if(num>=1){
				$.post("/it_web/articleImgList.do?pageNum="+num,function(data){
					$("#tab文章图片列表").html(data);
					})
			}else{
				confirm("当前已经是最前页");
			}
			
		}
		function showByPageNext(pageNum,pageNumEnd){
			var num=parseInt(pageNum)+1;
			cid=$(".cid").val();
			if(num<=pageNumEnd){
				$.post("/it_web/articleImgList.do?pageNum="+num,function(data){
					$("#tab文章图片列表").html(data);
					})
			}else{
				confirm("后面没有信息咯........");
			}
		}
		function reloadInfo(){
			$.post("articleImgList.do", function(data) {
				$("#tab文章图片列表").html(data);
			});
		}
	</script>

</body>

</html>