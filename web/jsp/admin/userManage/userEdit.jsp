<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>User Edit</title>
    <link rel="stylesheet" href="bs/css/bootstrap.css">
    <script type="text/javascript" src="bs/js/jquery.min.js"></script>
    <script type="text/javascript" src="bs/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="bs/validform/style.css">
    <script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="js/admin/userManage/userEdit.js"></script>
</head>
<body>
<c:if test="${!empty userMessage}">
    <h3 class="text-center">${userMessage}</h3>
</c:if>
<div class="container">
    <h2 class="text-center">User Edit</h2>
    <form id="myForm" action="jsp/admin/UserManageServlet?action=update" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${userInfo.id}">
        <div class="form-group">
            <label for="username" class="col-md-2 col-md-offset-2 control-label">username：</label>
            <div class="col-md-4">
                <input type="text" id="username" name="username" class="form-control" value="${userInfo.username }"
                       readonly="readonly">
            </div>

        </div>
        <div class="form-group">
            <label for="passWord" class="col-md-2 col-md-offset-2 control-label">password：</label>
            <div class="col-md-4">
                <input type="password" name="password" id="password" class="form-control" value="${userInfo.password }"
                       readonly="readonly">
            </div>
            <div class="col-md-4">
                <span class="Validform_checktip"></span>
            </div>
        </div>

        <div class="form-group">
            <label for="nickname" class="col-md-2 col-md-offset-2 control-label">Name：</label>
            <div class="col-md-4">
                <input type="text" id="nickname" name="nickname" class="form-control" value="${userInfo.nickname }">
            </div>
            <div class="col-md-4">
                <span class="Validform_checktip"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 col-md-offset-2 control-label">Gender：</label>
            <div class="col-md-4 ">
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sex"
                           <c:if test="${userInfo.sex eq 'Man'}">checked="checked"</c:if> class="pr1" value="Man">Man
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex"
                           <c:if test="${userInfo.sex eq 'Woman'}">checked="checked"</c:if> class="pr1" value="Woman">Woman
                </label>
            </div>
        </div>

        <div class="form-group">
            <label for="phone" class="col-md-2 col-md-offset-2 control-label">Telephone：</label>
            <div class="col-md-4">
                <input type="phone" id="phone" name="phone" class="form-control" value="${userInfo.phone }">
            </div>
            <div class="col-md-4">
                <span class="Validform_checktip"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-md-2 col-md-offset-2 control-label">Email：</label>
            <div class="col-md-4">
                <input type="email" id="email" name="email" class="form-control" value="${userInfo.email }">
            </div>
            <div class="col-md-4">
                <span class="Validform_checktip"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="balance" class="col-md-2 col-md-offset-2 control-label">Account Balance：</label>
            <div class="col-md-4">
                <input type="text" id="balance" name="balance" class="form-control" value="${userInfo.balance }">
            </div>
            <div class="col-md-4">
                <span class="Validform_checktip"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2  control-label col-md-offset-2">
                <input class="btn btn-success btn-block" type="submit" value="Update">
            </label>
            <label class="col-md-2 control-label">
                <input class="btn btn-warning btn-block" type="reset" value="Reset">
            </label>
        </div>
    </form>
</div>
</body>
</html>
