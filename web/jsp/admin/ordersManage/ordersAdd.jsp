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
	<title>订单增加</title>
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
		<c:if test="${!empty ordersMessage}">
			<h3 class="text-center">${ordersMessage}</h3>
		</c:if>
		<h2 class="text-center">订单增加</h2>
		<form id="ordersAddForm" class="form-horizontal" action="jsp/admin/OrdersManageServlet?action=add" method="post" >
						<div class="form-group">
				<label for="orderNo" class="col-sm-2 col-sm-offset-2 control-label">Order ID</label>
				<div class="col-sm-4 ">
					<input type="text" name="orderNo" id="orderNo" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="productId" class="col-sm-2 col-sm-offset-2 control-label">产品Id</label>
				<div class="col-sm-4 ">
					<input type="text" name="productId" id="productId" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="userId" class="col-sm-2 col-sm-offset-2 control-label">User ID</label>
				<div class="col-sm-4 ">
					<input type="text" name="userId" id="userId" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="num" class="col-sm-2 col-sm-offset-2 control-label">Product quantity</label>
				<div class="col-sm-4 ">
					<input type="text" name="num" id="num" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="price" class="col-sm-2 col-sm-offset-2 control-label">Total product price</label>
				<div class="col-sm-4 ">
					<input type="text" name="price" id="price" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="createTime" class="col-sm-2 col-sm-offset-2 control-label">Order creation time</label>
				<div class="col-sm-4 ">
					<input type="text" name="createTime" id="createTime" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="status" class="col-sm-2 col-sm-offset-2 control-label">Order status</label>
				<div class="col-sm-4 ">
					<input type="text" name="status" id="status" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="address" class="col-sm-2 col-sm-offset-2 control-label">Shipping address</label>
				<div class="col-sm-4 ">
					<input type="text" name="address" id="address" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="phone" class="col-sm-2 col-sm-offset-2 control-label">Recipient‘s telephone</label>
				<div class="col-sm-4 ">
					<input type="text" name="phone" id="phone" class="form-control" required/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
					<div class="form-group">
				<label for="realname" class="col-sm-2 col-sm-offset-2 control-label">Recipient‘s name</label>
				<div class="col-sm-4 ">
					<input type="text" name="realname" id="realname" class="form-control" required/>
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
