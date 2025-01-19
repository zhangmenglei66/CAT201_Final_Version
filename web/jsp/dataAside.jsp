<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="zuo fl">
    <h3><a href="#"><img src="../img/tx.png"/></a>
        <p class="clearfix">
            <c:if test="${sessionScope.user.nickname!=null}">
                <span class="fl">[${sessionScope.user.nickname}]</span>
                <span class="fr" onclick="location.href='${pageContext.request.contextPath}/UserServlet?action=Logout'">[LogoutLogin]</span>
            </c:if>
        </p>
    </h3>
    <div>
        <ul>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=findcart">My shopping cart</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=myorder">My Order</a></li>
        </ul>
        <ul>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=changedata">Personal Information</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=address">Shipping address</a></li>
        </ul>
    </div>
</div>

