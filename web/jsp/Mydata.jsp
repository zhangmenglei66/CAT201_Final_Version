<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>Personal center</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygxin.css"/>
</head>
<body>
<div class="head ding">
    <%@include file="header.jsp"%>
</div>
    <div class="address mt" id="add">
        <div class="wrapper clearfix"><a href="IndexServlet?action=base" class="fl">Home</a><span>/</span><a href="javascript:void(0);" class="on">Personal center</a>
        </div>
    </div>
<div class="Bott">
    <div class="wrapper clearfix">
        <%@include file="dataAside.jsp"%>
        <div class="you fl">
            <div class="tx clearfix">
                <div class="fl clearfix"><a href="#" class="fl"><img src="${pageContext.request.contextPath}/img/tx.png"/></a>
                    <p class="fl"><span>${sessionScope.user.nickname}</span><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=changedata">Edit personal information></a></p></div>
                <div class="fr">Bind email：${sessionScope.user.email}</div>
            </div>
            <div class="bott">
            </div>
        </div>
    </div>
</div>
<div class="float-nav">
    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=findcart" class="nav-item">
        <img src="${pageContext.request.contextPath}/img/cart-icon.png" alt="Cart">
        <span>Cart</span>
    </a>

    <a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=mydata" class="nav-item">
        <img src="${pageContext.request.contextPath}/img/profile-icon.png" alt="Profile">
        <span>Profile</span>
    </a>

    <button class="nav-item back-to-top" id="backToTop">
        <img src="${pageContext.request.contextPath}/img/arrow-up-icon.png" alt="Top">
        <span>Top</span>
    </button>
</div>
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
<script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
