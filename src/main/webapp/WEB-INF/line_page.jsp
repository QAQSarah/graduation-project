<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>学习路线</title>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.css" />
<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.css" />
<link rel="stylesheet" href="css/main.css" />
</head>
<body>
	<c:forEach items="${leanlines }" var="leanline">
		<a href="javascript:void(0)"><div
			style="border-radius: 10px; box-shadow: #ccc 0px 0px 10px; padding: 10px; margin: 20px;">
			<div onclick="findLeanLine(${leanline.id})">
				<center>
					<h3 style="color: green;">
						<i>${leanline.name }</i>
					</h3>
				</center>
				<hr>
				<p>${leanline.introduce}</p>
			</div>
		</div>
		</a>
	</c:forEach>
</body>
<script src="bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<!-- <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script> -->
<script
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="text/javascript">
	function findLeanLine(id){
		alert(id);
		$.post("line/findLeanLine.do",{id:id},function(data){
			$("#rem").css("background-color",""); 
			$("#hot").css("background-color",""); 
			$("#loveBZ1").removeClass("fa fa-heart").addClass("fa fa-heart-o");
			$("#PingLunBZ1").removeClass("fa fa-comments").addClass("fa fa-comments-o");
			$("#shouCBZ1").removeClass("fa fa-folder-o").addClass("fa fa-folder");
			$("#showContext").html(data);
			});
	}
</script>
</html>
