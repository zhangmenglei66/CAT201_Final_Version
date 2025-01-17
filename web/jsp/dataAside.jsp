<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="zuo fl">
    <h3><a href="#"><img src="../img/tx.png"/></a>
        <p class="clearfix">
            <c:if test="${sessionScope.user.nickname!=null}">
                <span class="fl">[${sessionScope.user.nickname}]</span>
                <span class="fr" onclick="location.href='${pageContext.request.contextPath}/UserServlet?action=Logout'">[退出Login]</span>
            </c:if>
        </p>
    </h3>
    <div>
        <ul>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=findcart">我的购物车</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=myorder">我的订单</a></li>
        </ul>
        <ul>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=changedata">个人信息</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/IndexServlet?action=address">Shipping address</a></li>
        </ul>
    </div>
</div>

