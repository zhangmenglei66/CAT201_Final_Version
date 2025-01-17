package com.mall.shop.dao.impl;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.User;
import com.mall.shop.dao.UserDao;
import com.mall.shop.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 创建时间：2017年10月24日 下午7:26:31
 */
public class UserDaoImpl implements UserDao {
    //获取总记录数
    @Override
    public long userReadCount() {
        //1.定义参数化的sql语句
        String sql = "select count(*) as count from user";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    //获取用户列表（分页显示）
    @Override
    public List<User> userList(PageBean pageBean) {
        List<User> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from user limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, (pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(2, pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setBalance(rs.getDouble("balance"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setSex(rs.getString("sex"));
                user.setNickname(rs.getString("nickname"));

                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //查找username是否存在
    @Override
    public boolean findUser(String username) {
        String sql = "select * from user where username = ?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        boolean flag = false;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    //根据id获取一个用户的信息
    @Override
    public User findUser(Integer id) {
        User user = new User();
        //1.定义参数化的sql语句
        String sql = "select * from user where id = ?";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setBalance(rs.getDouble("balance"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setSex(rs.getString("sex"));
                user.setNickname(rs.getString("nickname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    //增加用户
    @Override
    public boolean userAdd(User user) {
        //1.定义参数化的sql语句
        String sql = "insert into user(username,password,balance,sex,phone,email,nickname)" +
                "values(?,?,?,?,?,?,?)";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setDouble(3, user.getBalance());
            pstmt.setString(4, user.getSex());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getNickname());
            i = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return i > 0 ? true : false;
    }

    //Update用户
    @Override
    public boolean userUpdate(User user) {
        //定义参数化的sql语句

        String sql = "update user set balance=?,sex=?,phone=?,email=?,nickname=? where id=?";
        //2.调用数据库工具类执行Update语句
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);

            pstmt.setDouble(1, user.getBalance());
            pstmt.setString(2, user.getSex());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getNickname());
            pstmt.setInt(6, user.getId());
            i = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i > 0 ? true : false;
    }

    //根据idDelete一个用户
    @Override
    public boolean delUser(int id) {
        //定义参数化的sql语句
        String sql = "delete from user where id = ?";
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i > 0 ? true : false;
    }


    //根据一组id字符串Batch Delete用户
    @Override
    public boolean batDelUser(String ids) {
        //定义参数化的sql语句
        String sql = "delete from user where id in(" + ids + ")";
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i > 0 ? true : false;
    }

    //Login
    @Override
    public User userLogin(User user) {
        User user1 = new User();
        //定义参数化的sql语句
        String sql = "select * from user where username = ? and password = ?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        boolean flag = false;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user1.setId(rs.getInt("id"));
                user1.setBalance(rs.getDouble("balance"));
                user1.setPassword(rs.getString("password"));
                user1.setUsername(rs.getString("username"));
                user1.setEmail(rs.getString("email"));
                user1.setPhone(rs.getString("phone"));
                user1.setSex(rs.getString("sex"));
                user1.setNickname(rs.getString("nickname"));
                flag = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!flag) {
            user1 = null;
        }
        return user1;
    }

    @Override
    public int usernamecheck(String user_name) {
        String sql = "select count(*) as count from user where username=?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, user_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.close(DbUtil.getConn(), pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }


}
