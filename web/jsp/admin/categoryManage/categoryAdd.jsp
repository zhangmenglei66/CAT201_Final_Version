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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Category</title>
    <link rel="stylesheet" type="text/css" href="bs/css/bootstrap.css">
    <script type="text/javascript" src="bs/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="bs/validform/style.css">
    <script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: #eee;
        }
    </style>
</head>
<body>
<div class="container">
    <c:if test="${!empty categoryMessage}">
        <h3 class="text-center">${categoryMessage}</h3>
    </c:if>
    <h2 class="text-center">Add Category</h2>
    <form id="categoryAddForm" class="form-horizontal" action="jsp/admin/CategoryManageServlet?action=add"
          method="post">
        <div class="form-group">
            <label for="cateName" class="col-sm-2 col-sm-offset-2 control-label">Category name</label>
            <div class="col-sm-4 ">
                <input type="text" name="cateName" id="cateName" class="form-control" required/>
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
