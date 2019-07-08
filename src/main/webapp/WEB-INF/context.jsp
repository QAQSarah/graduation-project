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
<div >
<c:forEach items="${articleList}" var="article">
	<div class="list_one">
		<div class="image">
			<img src="${article.articleImg.imgurl}" width="100px" height="80px" />
		</div>
		<div>
			<a href="javascript:void(0)" onclick="showPage(${article.id})"><h4
					id="${article.id}" class="title">${article.title}</h4> </a> 
					<input name="cid" class="cid" value="${article.categoryId}" hidden="hidden"/>
			<p class="author">${article.auther}&nbsp;&nbsp;·
				&nbsp;&nbsp;<span id="atime">${article.createtime}</span>
			</p>
		</div>
	</div>
</c:forEach>
<center>
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<c:if test="${requestScope.pageBean.totalPage>1}">
								<li><a href="javascript:void(0)"
									onclick="showByPagePrevious(${requestScope.pageBean.pageNum})" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
								<c:forEach begin="${requestScope.pageBean.start}"
									end="${requestScope.pageBean.end}" step="1" var="i">
									<li><a href="javascript:void(0)"
									onclick="showByPage(${i})" >${i}</a></li>
								</c:forEach>
								<li><a href="javascript:void(0)"
									onclick="showByPageNext(${requestScope.pageBean.pageNum},${requestScope.pageBean.end})" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</c:if>

						</ul>
					</nav>
					</center>
</div>
</body>
<script type="text/javascript">
function showByPage(id){
	cid=$(".cid").val();
	$.post("/it_web/showByCategory.do?id="+cid+"&pageNum="+id,function(data){
		$("#showContext").html(data);
		})
	//window.location.href="/it_web/showByCategory.do?id="+cid+"&pageNum="+id;
	
}
function showByPagePrevious(pageNum){
	var num=parseInt(pageNum)-1;
	cid=$(".cid").val();
	if(num>=1){
		$.post("/it_web/showByCategory.do?id="+cid+"&pageNum="+num,function(data){
			$("#showContext").html(data);
			})
		//window.location.href="/it_web/showByCategory.do?id="+cid+"&pageNum="+num;
	}else{
		confirm("当前已经是最前页");
	}
	
}
function showByPageNext(pageNum,pageNumEnd){
	var num=parseInt(pageNum)+1;
	cid=$(".cid").val();
	if(num<=pageNumEnd){
		$.post("/it_web/showByCategory.do?id="+cid+"&pageNum="+num,function(data){
			$("#showContext").html(data);
			})
		//window.location.href="/it_web/showByCategory.do?id="+cid+"&pageNum="+num;
	}else{
		confirm("后面没有信息咯........");
	}
}

</script>
</html>
