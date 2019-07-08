<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>显示路线的详情页面</title>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.css" />
<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.css" />
<link rel="stylesheet" href="css/main.css" />
</head>
<body>
	<table style="box-shadow: #A67D3D 0px 0px 10px; border: #eee 1px solid; border-radius:10px;">
		<thead>
			<tr>
				<th
					style=" border-bottom: #eee 1px solid; border-right:#eee 1px solid ;background-color: rgba(255, 235, 205, 0.8); text-align: center;"
					width="450px">课程名</th>
				<th
					style=" border-bottom: #eee 1px solid; background-color: rgba(255, 235, 205, 0.8); text-align: center;"
					width="450px">下载路径</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${ls.size()==0}">
				<tr>
					<td style="border-bottom: #eee 1px solid; border-right:#eee 1px solid ;text-align: center;" colspan="2">暂无数据</td>
				</tr>
			</c:if>
			<c:forEach items="${ls}" var="lean">
				<tr>
					<td style="border-bottom: #eee 1px solid; border-right:#eee 1px solid ; text-align: center;">${lean.name }</td>
					<td style="border-bottom: #eee 1px solid; text-align: center;">${lean.downloadurl }</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</body>
<script src="bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
</html>