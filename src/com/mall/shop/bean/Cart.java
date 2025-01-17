package com.mall.shop.bean;


public class Cart {

	/** id */
	private Integer id;
	/** User ID */
	private Integer userId;
	/** Product id */
	private Integer productId;
	/** Quantity: */
	private Integer cartNum;
	/** 状态 */
	private String status;
	/** 订单id */
	private Integer orderId;

	private Product product;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setCartNum(Integer cartNum) {
		this.cartNum = cartNum;
	}

	public Integer getCartNum() {
		return this.cartNum;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart(Integer userId, Integer productId, Integer cartNum, String status, Integer orderId) {
		this.userId = userId;
		this.productId = productId;
		this.cartNum = cartNum;
		this.status = status;
		this.orderId = orderId;
	}

	public Cart() {
	}

	public Cart(Integer id, Integer userId, Integer productId, Integer cartNum, String status, Integer orderId) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.cartNum = cartNum;
		this.status = status;
		this.orderId = orderId;
	}

}
