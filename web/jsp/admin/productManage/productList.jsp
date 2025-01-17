<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<html>
<head>
    <base href="${basePath}">
    <meta charset="UTF-8">
    <title>Product 管理界面</title>
    <link rel="stylesheet" href="/bs/css/bootstrap.css">
    <script type="text/javascript" src="/bs/js/jquery.min.js"></script>
    <script type="text/javascript" src="/bs/js/bootstrap.js"></script>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: #eee;
        }

        .content {
            padding: 20px 40px 0 40px;
        }

        .page-div {
            display: inline-block;
            width: 110px;
        }

        .homePage, .prePage, .page-go, .nextPage, .lastPage {
            border-radius: 15px;
            color: #337ab7;
        }

        .pager {
            padding: 0 20px;
        }

        #page-input {
            display: inline-block;
            width: 60px;
            border-radius: 10px;
        }

        .funbtn {
            margin: 10px 0;
        }

        .funbtn a {
            margin-right: 10px;
        }

        .showpic {
            height: 60px;
            width: 90px;
            margin-top: 3px;
            margin-bottom: 3px;
        }

    </style>
</head>
<body>
<c:if test="${!empty productMessage}">
    <h3 class="text-center">${productMessage}</h3>
</c:if>
<h2 class="text-center">Product List</h2>
<div class="container-fulid content">
    <div class="funbtn">
        <a id="batDel" class="btn btn-danger" href="javascript:void(0)">Batch Delete</a>
        <a class="btn btn-info" href="jsp/admin/ProductManageServlet?action=toadd">Add Product</a>
    </div>
    <table class="table table-striped table-hover">
        <tr class="info">
            <th>
                <input type="checkbox" id="batDelChoice">
                <label for="batDelChoice"> Select/Deselect all</label>
            </th>
            <th>Product name</th>
            <th>Product description</th>
            <th>Price</th>
            <th></th>
            <th>Primary category</th>
            <th>Secondary category</th>
            <th>Product Image</th>
            <th>Product Status</th>


            <th width="140px;">Operate</th>
        </tr>
        <c:choose>
            <c:when test="${!empty productList}">
                <c:forEach items="${productList}" var="i" varStatus="n">
                    <tr>
                        <td class="noClick"><input type="checkbox" name="choice" value="${i.id}">
                        <td>${i.productName}</td>
                        <td>${i.productDescription}</td>
                        <td>${i.productPrice}</td>
                        <td>${i.productStock}</td>
                        <td>${i.productFidName}</td>
                        <td>${i.productCidName}</td>
                        <td><img id="img" class="showpic" src="photo/${i.productImage}"/></td>
                        <td><c:if test="${i.productStatus eq 1}">Put on shelf</c:if>
                            <c:if test="${i.productStatus eq 0}">Remove from shelf</c:if>
                        </td>


                        <td class="noClick">
                            <a class="btn btn-info btn-xs" href="jsp/admin/ProductManageServlet?action=edit&id=${i.id}">Edit</a>
                            <a class="btn btn-danger btn-xs" href="jsp/admin/ProductManageServlet?action=del&id=${i.id}"
                               onclick="javascript:return confirm('Confirm to Delete吗？');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="8"><h4 class="text-center">当前无更多Product 信息</h4></td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>

    <ul class="pager">
        <li>
            <button class="homePage btn btn-default btn-sm">Home</button>
        </li>
        <li>
            <button class="prePage btn btn-sm btn-default">Previous Page</button>
        </li>
        <li>Total ${pageBean.pageCount} Page | Now ${pageBean.curPage} Page</li>
        <li>
            To
            <div class="input-group form-group page-div">
                <input id="page-input" class="form-control input-sm" type="text" name="page"/>
                <span>
					<button class="page-go btn btn-sm btn-default">GO</button>
				</span>
            </div>
        </li>
        <li>
            <button class="nextPage btn btn-sm btn-default">Next Page</button>
        </li>
        <li>
            <button class="lastPage btn btn-sm btn-default">Last Page</button>
        </li>
    </ul>
</div>
<script type="text/javascript">
    //按钮禁用限制
    if ("${pageBean.curPage}" == 1) {
        $(".homePage").attr("disabled", "disabled");
        $(".prePage").attr("disabled", "disabled");
    }
    if ("${pageBean.curPage}" == "${pageBean.pageCount}") {
        $(".page-go").attr("disabled", "disabled");
        $(".nextPage").attr("disabled", "disabled");
        $(".lastPage").attr("disabled", "disabled");
    }
    //按钮事件
    $(".homePage").click(function () {
        window.location = "${bsePath}jsp/admin/ProductManageServlet?action=list&page=1";
    })
    $(".prePage").click(function () {
        window.location = "${basePath}jsp/admin/ProductManageServlet?action=list&page=${pageBean.prePage}";
    })
    $(".nextPage").click(function () {

        window.location = "${basePath}jsp/admin/ProductManageServlet?action=list&page=${pageBean.nextPage}";
    })
    $(".lastPage").click(function () {

        window.location = "${basePath}jsp/admin/ProductManageServlet?action=list&page=${pageBean.pageCount}";
    })
    //控制页面跳转范围
    $(".page-go").click(function () {
        var page = $("#page-input").val();
        var pageCount =${pageBean.pageCount};
        if (isNaN(page) || page.length <= 0) {
            $("#page-input").focus();
            alert("请输入数字页码");
            return;
        }
        if (page > pageCount || page < 1) {
            alert("输入的页码超出范围！");
            $("#page-input").focus();
        } else {
            window.location = "${basePath}jsp/admin/ProductManageServlet?action=list&page=" + page;
        }
    })

    //批量选中
    $("#batDelChoice").change(function () {
        if (!$("input[name='choice']").prop("checked")) {
            $(this).prop("checked", true);
            $("input[name='choice']").prop("checked", true);

        } else {
            $(this).removeProp("checked");
            $("input[name='choice']").removeProp("checked");
        }
    })


    //Batch Delete
    $("#batDel").click(function () {
        var choices = $("input:checked[name='choice']");
        var ids = "";
        for (i = 0; i < choices.length; i++) {
            if (i != choices.length - 1) {
                ids += choices.eq(i).val() + ",";
            } else {
                ids += choices.eq(i).val();
            }
        }
        if (ids == "") {
            alert("请勾选要Delete的内容！");
            return;

        }
        if (confirm("Confirm to Delete" + choices.length + "条Product 吗？")) {

            window.location = "${basePath}jsp/admin/ProductManageServlet?action=batDel&ids=" + ids;

        }
    })


</script>
</body>
</html>
