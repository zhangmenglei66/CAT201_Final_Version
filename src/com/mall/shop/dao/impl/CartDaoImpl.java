package com.mall.shop.dao.impl;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Cart;
import com.mall.shop.dao.CartDao;
import com.mall.shop.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    @Override
    public List<Cart> cartList(PageBean pageBean) {
        List<Cart> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from cart limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, (pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(2, pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setCartNum(rs.getInt("cart_num"));
                cart.setStatus(rs.getString("status"));
                cart.setOrderId(rs.getInt("order_id"));

                list.add(cart);
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
    public List<Cart> cartAll() {
        List<Cart> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from cart ";

        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setCartNum(rs.getInt("cart_num"));
                cart.setStatus(rs.getString("status"));
                cart.setOrderId(rs.getInt("order_id"));

                list.add(cart);
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
    public long cartReadCount() {
        String sql = "select count(*) as count from cart";
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
    public boolean cartDel(int cartId) {
        String sql = "delete from cart where id = ?";
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, cartId);
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
    public boolean cartBatDelById(String ids) {
        String sql = "delete from cart where id in(" + ids + ")";
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
    public boolean findCartByName(String cartName) {
        return false;
    }

    @Override
    public Cart findCartById(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from cart where id = ?";
        Cart cart = new Cart();
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setCartNum(rs.getInt("cart_num"));
                cart.setStatus(rs.getString("status"));
                cart.setOrderId(rs.getInt("order_id"));

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
        return cart;
    }

    @Override
    public List<Cart> findCartByUserId(int userid) {
        List<Cart> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from cart where user_id=?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setCartNum(rs.getInt("cart_num"));
                cart.setStatus(rs.getString("status"));
                cart.setOrderId(rs.getInt("order_id"));
                list.add(cart);
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
    public boolean cartAdd(Cart cart) {
        String sql = "insert into cart( user_id,product_id,cart_num,status,order_id )" +
                "values ( ? ,? ,? ,? ,?)";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, cart.getUserId());
            pstmt.setInt(2, cart.getProductId());
            pstmt.setInt(3, cart.getCartNum());
            pstmt.setString(4, cart.getStatus());
            pstmt.setInt(5, 0);

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
    public boolean cartUpdate(Cart cart) {
        String sql = "update cart set  user_id=?,product_id=?,cart_num=?,status=?,order_id=?  where id=?";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, cart.getUserId());
            pstmt.setInt(2, cart.getProductId());
            pstmt.setInt(3, cart.getCartNum());
            pstmt.setString(4, cart.getStatus());
            pstmt.setInt(5, cart.getOrderId());

            pstmt.setInt(6, cart.getId());
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
    public Integer checkcart(Integer user_id, Integer product_id) {
        String sql = "select count(*) as count from cart where user_id=? and product_id=?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, product_id);
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
    public void updatecart_num(Integer user_id, Integer product_id, Integer cart_num) {
        String sql = "update cart set cart_num=? where user_id=? and product_id=?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1,cart_num);
            pstmt.setInt(2, user_id);
            pstmt.setInt(3, product_id);
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
    }

    @Override
    public void cart_numchange(Integer cart_id, Integer count) {
        String sql = "update cart set cart_num=? where id=?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1,count);
            pstmt.setInt(2, cart_id);
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
    }

    @Override
    public Cart searchcartrecord(Integer cart_id) {
        return null;
    }
}
