package com.mall.shop.dao;


import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.User;

import java.util.List;

/**
* @version 创建时间：2017年10月24日 下午7:19:14
*/
public interface UserDao {

	//获取总记录数
	long userReadCount();
	//获取用户列表（分页显示）
	List<User> userList(PageBean pageBean);
	//查找username是否存在
	boolean findUser(String userName);
	//根据id获取一个用户的信息
	User findUser(Integer id);
	//增加用户
	boolean userAdd(User user);
	//Update用户
	boolean userUpdate(User user);
	//根据idDelete一个用户
	boolean delUser(int id);
	//根据一组id字符串Batch Delete用户
	boolean batDelUser(String ids);
	//登陆
	User userLogin(User user);
	int usernamecheck(String user_name);


}
