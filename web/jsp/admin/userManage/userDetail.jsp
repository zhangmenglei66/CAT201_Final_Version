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
	<title>Product Details Page</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}

		.container .row{
			line-height: 30px;
			htight:30px;
		}

	</style>
</head>
<body>
	<h2 class="text-center">User Detail</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-2 text-right">User ID</div>
			<div class="col-md-10">${userInfo.id}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">username</div>
			<div class="col-md-10">${userInfo.username}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">password</div>
			<div class="col-md-10">${userInfo.password}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">Name</div>
			<div class="col-md-10">${userInfo.nickname}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">Gender</div>
			<div class="col-md-10">${userInfo.sex}</div>
		</div>

		<div class="row">
			<div class="col-md-2 text-right">Telephone</div>
			<div class="col-md-10">${userInfo.phone}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">Email</div>
			<div class="col-md-10">${userInfo.email}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">Balance</div>
			<div class="col-md-10">${userInfo.balance}</div>
		</div>

	</div>
</body>
</html>
