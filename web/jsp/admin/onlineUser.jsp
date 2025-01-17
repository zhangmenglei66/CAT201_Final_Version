<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>Online User Management Interface</title>
	<style type="text/css">
		body{
			padding:0;
			margin:0;
			background:#eee;
			text-align:center;
		}
	</style>
</head>
<body>
	<h2>Online User List</h2>
	<hr>
	<c:if test="${!empty olUser}">
		<c:forEach items="${olUser}" var="i">
			${i},
		</c:forEach>
	</c:if>
</body>

</html>
