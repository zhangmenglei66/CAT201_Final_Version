package com.mall.shop.bean;

import java.util.Map;

/**
 * 管理员用户类
 * @author thuih
 *
 */
public class Admin {
	private Integer id;				//用户编号
	private String username;			//username
	private String password;			//用户password
	private String name;				//用户Name

	public Admin() {}

	public Admin(String username, String password) {
		this.username=username;
		this.password=password;
	}

	public Admin(Integer id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}


	public Admin(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public Admin(Map<String,Object> map) {
		this.id = (Integer) map.get("id");
		this.username=(String) map.get("username");
		this.password = (String) map.get("password");
		this.name = (String) map.get("name");
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
