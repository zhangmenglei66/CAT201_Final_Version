<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<header>
    <%@include file="header.jsp"%>
</header>

<main class="cart-container">
    <h1>Shopping Cart</h1>

    <div class="cart-table">
        <div class="cart-header">
            <div>Product</div>
            <div>Price</div>
            <div>Quantity</div>
            <div>Total</div>
            <div>Actions</div>
        </div>

        <c:forEach items="${cart}" var="item">
            <div class="cart-item">
                <div class="product-info">
                    <input type="checkbox" name="cartItem" value="${item.id}">
                    <a href="${pageContext.request.contextPath}/products/${item.productId}">
                        <img src="/images/${item.product.productImage}" alt="${item.product.productName}"
                             class="product-image">
                        <span class="product-name">${item.product.productName}</span>
                    </a>
                </div>

                <div class="price">$${item.product.productPrice}</div>

                <div class="quantity-controls">
                    <button class="decrease">-</button>
                    <span class="quantity" data-cart-id="${item.id}">${item.cartNum}</span>
                    <button class="increase">+</button>
                </div>

                <div class="total">$${item.product.productPrice * item.cartNum}</div>

                <div class="actions">
                    <button class="delete-item" data-cart-id="${item.id}">Remove</button>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty cart}">
            <div class="empty-cart">
                <p>Your cart is empty</p>
                <a href="${pageContext.request.contextPath}/shop" class="continue-shopping">Continue Shopping</a>
            </div>
        </c:if>

        <div class="cart-footer">
            <div class="cart-actions">
                <label>
                    <input type="checkbox" id="selectAll">
                    Select All
                </label>
                <button id="deleteSelected">Delete Selected</button>
            </div>

            <div class="cart-summary">
                <span>Items: <span id="itemCount">0</span></span>
                <span>Total: $<span id="totalAmount">0.00</span></span>
                <button onclick="checkout()" class="checkout-button">Checkout</button>
            </div>
        </div>
    </div>
</main>

<footer>
    <div class="benefits">
        <div class="benefit-item">
            <img src="${pageContext.request.contextPath}/img/return.png" alt="Returns">
            <span>7-Day Returns</span>
        </div>
        <div class="benefit-item">
            <img src="${pageContext.request.contextPath}/img/exchange.png" alt="Exchange">
            <span>15-Day Exchange</span>
        </div>
        <div class="benefit-item">
            <img src="${pageContext.request.contextPath}/img/shipping.png" alt="Shipping">
            <span>Free Shipping Over $599</span>
        </div>
    </div>
</footer>

<script>
    function checkout() {
        const selectedItems = Array.from(document.querySelectorAll('input[name="cartItem"]:checked'))
            .map(checkbox => checkbox.value)
            .join(',');

        if (selectedItems) {
            window.location.href = "${pageContext.request.contextPath}/checkout?items=" + selectedItems;
        } else {
            alert("Please select items to checkout");
        }
    }
</script>

<script src="${pageContext.request.contextPath}/js/cart.js"></script>
</body>
</html>