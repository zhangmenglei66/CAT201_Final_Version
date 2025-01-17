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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>增加</title>
	<link rel="stylesheet" type="text/css" href="bs/css/bootstrap.css">
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="bs/validform/style.css">
	<script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script>
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
	</style>
</head>
<body>
	<div class="container">
		<c:if test="${!empty cartMessage}">
			<h3 class="text-center">${cartMessage}</h3>
		</c:if>
		<h2 class="text-center">增加</h2>
		<form id="cartAddForm" class="form-horizontal" action="jsp/admin/CartManageServlet?action=add" method="post" >
						<div class="form-group">
				<label for="userId" class="col-sm-2 col-sm-offset-2 control-label">User ID</label>
				<div class="col-sm-4 ">
					<input type="text" name="userId" id="userId" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="productId" class="col-sm-2 col-sm-offset-2 control-label">Product id</label>
				<div class="col-sm-4 ">
					<input type="text" name="productId" id="productId" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="cartNum" class="col-sm-2 col-sm-offset-2 control-label">Quantity:</label>
				<div class="col-sm-4 ">
					<input type="text" name="cartNum" id="cartNum" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="status" class="col-sm-2 col-sm-offset-2 control-label">状态</label>
				<div class="col-sm-4 ">
					<input type="text" name="status" id="status" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="orderId" class="col-sm-2 col-sm-offset-2 control-label">订单id</label>
				<div class="col-sm-4 ">
					<input type="text" name="orderId" id="orderId" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>

			<div class="form-group">
					<label class="col-sm-2 col-sm-offset-2 control-label">
						<input class="btn btn-success btn-block" type="submit" value="Submit">
					</label>
					<label class="col-sm-2 control-label">
						<input class="btn btn-warning btn-block" type="reset" value="Reset">
					</label>
				</div>

		</form>

	</div>


</body>
</html>
