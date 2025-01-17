package com.mall.shop.bean;

import java.math.BigDecimal;

/**
 * 分类
 */
public class Category {

	/** Secondary category */
	private Integer id;
	/** Category name */
	private String cateName;
	/** Category ID */
	private Integer cateParentId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateParentId(Integer cateParentId) {
		this.cateParentId = cateParentId;
	}

	public Integer getCateParentId() {
		return this.cateParentId;
	}

	public Category(String cateName, Integer cateParentId) {
		this.cateName = cateName;
		this.cateParentId = cateParentId;
	}

	public Category() {
	}

	public Category(Integer id, String cateName, Integer cateParentId) {
		this.id = id;
		this.cateName = cateName;
		this.cateParentId = cateParentId;
	}

}
