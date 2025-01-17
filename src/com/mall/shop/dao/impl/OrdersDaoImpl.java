package com.mall.shop.dao.impl;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Orders;
import com.mall.shop.dao.OrdersDao;
import com.mall.shop.util.DbUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public List<Orders> ordersList(PageBean pageBean) {
        List<Orders> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from orders limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, (pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(2, pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Orders orders = new Orders();
                orders.setId(rs.getInt("id"));
                orders.setOrderNo(rs.getString("order_no"));
                orders.setProductId(rs.getInt("product_id"));
                orders.setUserId(rs.getInt("user_id"));
                orders.setNum(rs.getInt("num"));
                orders.setPrice(rs.getDouble("price"));
                orders.setCreateTime(rs.getTimestamp("create_time"));
                orders.setStatus(rs.getInt("status"));
                orders.setAddress(rs.getString("address"));
                orders.setPhone(rs.getString("phone"));
                orders.setRealname(rs.getString("realname"));

                list.add(orders);
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

    @Override
    public List<Orders> myordersList(int userId,PageBean pageBean) {
        List<Orders> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from orders where user_id=? limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, (pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(3, pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Orders orders = new Orders();
                orders.setId(rs.getInt("id"));
                orders.setOrderNo(rs.getString("order_no"));
                orders.setProductId(rs.getInt("product_id"));
                orders.setUserId(rs.getInt("user_id"));
                orders.setNum(rs.getInt("num"));
                orders.setPrice(rs.getDouble("price"));
                orders.setCreateTime(rs.getDate("create_time"));
                orders.setStatus(rs.getInt("status"));
                orders.setAddress(rs.getString("address"));
                orders.setPhone(rs.getString("phone"));
                orders.setRealname(rs.getString("realname"));

                list.add(orders);
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

    @Override
    public List<Orders> ordersAll() {
        List<Orders> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from orders ";

        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Orders orders = new Orders();
                orders.setId(rs.getInt("id"));
                orders.setOrderNo(rs.getString("order_no"));
                orders.setProductId(rs.getInt("product_id"));
                orders.setUserId(rs.getInt("user_id"));
                orders.setNum(rs.getInt("num"));
                orders.setPrice(rs.getDouble("price"));
                orders.setCreateTime(rs.getDate("create_time"));
                orders.setStatus(rs.getInt("status"));
                orders.setAddress(rs.getString("address"));
                orders.setPhone(rs.getString("phone"));
                orders.setRealname(rs.getString("realname"));

                list.add(orders);
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

    @Override
    public long ordersReadCount() {
        String sql = "select count(*) as count from orders";
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

    @Override
    public long myordersReadCount(int userId) {
        String sql = "select count(*) as count from orders where user_id=?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, userId);
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


    @Override
    public boolean ordersDel(int ordersId) {
        String sql = "delete from orders where id = ?";
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, ordersId);
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

    @Override
    public boolean ordersBatDelById(String ids) {
        String sql = "delete from orders where id in(" + ids + ")";
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

    @Override
    public boolean findOrdersByName(String ordersName) {
        return false;
    }

    @Override
    public Orders findOrdersById(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from orders where id = ?";
        Orders orders = new Orders();
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {


                orders.setId(rs.getInt("id"));
                orders.setOrderNo(rs.getString("order_no"));
                orders.setProductId(rs.getInt("product_id"));
                orders.setUserId(rs.getInt("user_id"));
                orders.setNum(rs.getInt("num"));
                orders.setPrice(rs.getDouble("price"));
                orders.setCreateTime(rs.getTimestamp("create_time"));
                orders.setStatus(rs.getInt("status"));
                orders.setAddress(rs.getString("address"));
                orders.setPhone(rs.getString("phone"));
                orders.setRealname(rs.getString("realname"));

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
        return orders;
    }

    @Override
    public boolean ordersAdd(Orders orders) {
        String sql = "insert into orders( order_no,product_id,user_id,num,price,create_time,status,address,phone,realname )" +
                "values ( ? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, orders.getOrderNo());
            pstmt.setInt(2, orders.getProductId());
            pstmt.setInt(3, orders.getUserId());
            pstmt.setInt(4, orders.getNum());
            pstmt.setDouble(5, orders.getPrice());
            pstmt.setTimestamp(6, new Timestamp(orders.getCreateTime().getTime()));
            pstmt.setInt(7, orders.getStatus());
            pstmt.setString(8, orders.getAddress());
            pstmt.setString(9, orders.getPhone());
            pstmt.setString(10, orders.getRealname());

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

    @Override
    public boolean ordersUpdate(Orders orders) {
        String sql = "update orders set  order_no=?,product_id=?,user_id=?,num=?,price=?,create_time=?,status=?,address=?,phone=?,realname=?  where id=?";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, orders.getOrderNo());
            pstmt.setInt(2, orders.getProductId());
            pstmt.setInt(3, orders.getUserId());
            pstmt.setInt(4, orders.getNum());
            pstmt.setDouble(5, orders.getPrice());
            pstmt.setTimestamp(6, new Timestamp(orders.getCreateTime().getTime()));
            pstmt.setInt(7, orders.getStatus());
            pstmt.setString(8, orders.getAddress());
            pstmt.setString(9, orders.getPhone());
            pstmt.setString(10, orders.getRealname());

            pstmt.setInt(11, orders.getId());
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
}

