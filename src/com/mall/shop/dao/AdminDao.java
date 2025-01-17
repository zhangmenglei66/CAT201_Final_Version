package com.mall.shop.dao;

import com.mall.shop.bean.Admin;
import com.mall.shop.bean.PageBean;

import java.util.List;

public interface AdminDao {
    //用户Login
    Admin userLogin(Admin admin);
    //获取总记录数
    long readCount();
    //获取用户列表（分页显示）
    List<Admin> userList(PageBean pageBean);
    //增加用户
    boolean userAdd(Admin admin);
    //Update用户
    boolean userUpdate(Admin admin);
    //根据id获取一个用户的信息
    Admin findUser(Integer id);
    //查找username是否存在
    boolean findUser(String username);
    //根据idDelete一个用户
    boolean delUser(int id);
    //根据一组id字符串Batch Delete用户
    boolean batDelUser(String ids);

}
