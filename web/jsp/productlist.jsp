<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head lang="en">
    <meta charset="utf-8"/>
    <c:if test="${catef.cateName!=null}">
        <title>${catef.cateName}</title>
    </c:if>
    <c:if test="${catef.cateName==null}">
        <title>${cate.cateName}</title>
    </c:if>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
</head>
<body>
<div class="head">
    <%@include file="header.jsp"%>
</div>
    <div class="banner"><a href="#"><img src="${pageContext.request.contextPath}/img/banner3.jpg"/></a></div>
    <div class="address">
        <div class="wrapper clearfix"><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=base">Home</a><span>/</span>
            <c:if test="${catef.cateName!=null}">
            <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=selectproduct&fid=${catef.id}">${catef.cateName}</a><span>/</span>
            <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=selectproduct&cid=${cate.id}" class="on">${cate.cateName}</a></div>
        </c:if>
        <c:if test="${catef.cateName==null}">
            <a href="">${cate.cateName}</a>
        </c:if>
    </div>
    <div class="current">
        <div class="wrapper clearfix">
            <h3 class="fl">${cate.cateName}</h3>
        </div>
    </div>
    <ul class="proList wrapper clearfix">
        <c:forEach var="product" items="${product}">
            <c:if test="${product.productStatus==1}">
            <li>
                <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=productdetails&product_id=${product.id}">
                    <dl>
                        <dt><img src="/photo/${product.productImage}"></dt>
                        <dd>${product.productName}</dd>
                        <dd></dd>
                        <dd>￥${product.productPrice}0</dd>
                    </dl>
                </a>
            </li>
        </c:if>
        </c:forEach>
    </ul>
<c:if test="${sessionScope.user.username!=null}">
<div class="gotop">
    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=findcart">
        <dl>
            <dt><img src="${pageContext.request.contextPath}/img/gt1.png"/></dt>
            <dd>去购<br/>物车</dd>
        </dl>
    </a>
    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=mydata">
        <dl>
            <dt><img src="${pageContext.request.contextPath}/img/gt3.png"/></dt>
            <dd>个人<br/>中心</dd>
        </dl>
    </a><a href="#" class="toptop" style="display: none;">
    <dl>
        <dt><img src="${pageContext.request.contextPath}/img/gt4.png"/></dt>
        <dd>返回<br/>顶部</dd>
    </dl>
</a>
</div>
</c:if>
    <div class="footer">
        <div class="top">
            <div class="wrapper">
                <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot1.png"/></a><span class="fl">7-day no-questions-asked return policy</span>
                </div>
                <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot2.png"/></a><span class="fl">15-day free exchange</span>
                </div>
                <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot3.png"/></a><span class="fl">Free shipping on orders over 599</span>
                </div>
                <div class="clearfix"><a href="#2" class="fl"><img src="${pageContext.request.contextPath}/img/foot4.png"/></a><span class="fl">Mobile special services</span>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/nav.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/cart.js" type="text/javascript" charset="utf-8"></script>
</body>

</html>
