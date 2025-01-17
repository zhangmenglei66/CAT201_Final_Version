<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Admin</title>
<link rel="stylesheet" type="text/css" href="css/login/login.css" />
<script type="text/javascript">
	function checkForm(){
		var username=document.getElementById("username");
		var password=document.getElementById("password");
		if(username.value.length<=0){
			alert("请输入username！");
			username.focus();
			return false;
		}
		if(password.value.length<=0){
			alert("请输入password！");
			password.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<c:if test="${!empty infoList}">
	<c:forEach items="${infoList}" var="i">
		<script type="text/javascript">
			alert("${i}")
		</script>
	</c:forEach>
</c:if>
	<h1 id="title">
		 E-commerce backend management system&nbsp;
	</h1>
	<div id="login">
		<form action="jsp/admin/LoginServlet" method="post"
			onsubmit="javascript:return checkForm()">
			<p>
				<input type="text" name="username" id="username" placeholder="username">
			</p>
			<p>
				<input type="password" name="password" id="password"
					placeholder="password">
			</p>
			<p>
				<input type="submit" id="submit" value="Login">
			</p>
		</form>
	</div>
</body>
</html>
