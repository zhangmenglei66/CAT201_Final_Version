package com.mall.shop.dao.impl;

import com.mall.shop.bean.Admin;
import com.mall.shop.bean.PageBean;
import com.mall.shop.dao.AdminDao;
import com.mall.shop.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin userLogin(Admin admin) {
        boolean flag=false;
        Admin admin1 =null;
        String sql="select * from admin where username=? and password=?";
        ResultSet rs = null;
        PreparedStatement pstmt =null;
        //1.定义参数化的sql语句

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1,admin.getUsername());
            pstmt.setString(2,admin.getPassword());
            rs = pstmt.executeQuery();

            while(rs.next()) {
              admin.setId(rs.getInt("id"));
                admin1=admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return admin1;
    }

    @Override
    public long readCount() {
        String sql = "select count(*) as count from admin";
        ResultSet rs = null;
        PreparedStatement pstmt =null;
        int count=0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                count=  rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public List<Admin> userList(PageBean pageBean) {
        List<Admin> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt =null;
        //1.定义参数化的sql语句
        String sql = "select * from admin limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1,(pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(2,pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setPassword(rs.getString("password"));
                admin.setUsername(rs.getString("username"));
                admin.setName(rs.getString("name"));

                list.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean userAdd(Admin admin) {
        //1.定义参数化的sql语句
        String sql = "insert into admin(username,password,name)" +
                "values(?,?,?)";

        ResultSet rs = null;
        PreparedStatement pstmt =null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1,admin.getUsername());
            pstmt.setString(2,admin.getPassword());
            pstmt.setString(3,admin.getName());
            i =  pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return i > 0 ? true : false;
    }

    @Override
    public boolean userUpdate(Admin admin) {
        String sql  = "update admin set name=?,password=? where id=?";
        //2.调用数据库工具类执行Update语句
        PreparedStatement pstmt =null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);

            pstmt.setString(1,admin.getName());
            pstmt.setString(2,admin.getPassword());
            pstmt.setInt(3,admin.getId());
            i =  pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i > 0 ? true : false;
    }

    @Override
    public Admin findUser(Integer id) {
        Admin admin = new Admin();
        //1.定义参数化的sql语句
        String sql = "select * from admin where id = ?";

        ResultSet rs = null;
        PreparedStatement pstmt =null;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setPassword(rs.getString("password"));
                admin.setUsername(rs.getString("username"));
                admin.setName(rs.getString("name"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return admin;
    }

    @Override
    public boolean findUser(String username) {
        return false;
    }

    @Override
    public boolean delUser(int id) {
        //定义参数化的sql语句
        String sql = "delete from admin where id = ?";
        PreparedStatement pstmt =null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1,id);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("i = " + i);
        return i > 0 ? true : false;
    }

    @Override
    public boolean batDelUser(String ids) {
        String sql = "delete from admin where id in(" + ids + ")";
        PreparedStatement pstmt =null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.close(DbUtil.getConn(),pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i > 0 ? true : false;
    }
}
