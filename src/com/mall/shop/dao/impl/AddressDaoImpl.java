package com.mall.shop.dao.impl;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Address;
import com.mall.shop.dao.AddressDao;
import com.mall.shop.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    @Override
    public List<Address> addressList(PageBean pageBean) {
        List<Address> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from address limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, (pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(2, pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setPhone(rs.getString("phone"));
                address.setAddress(rs.getString("address"));
                address.setName(rs.getString("name"));
                address.setUserId(rs.getInt("user_id"));

                list.add(address);
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
    public List<Address> addressAll() {
        List<Address> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from address ";

        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setPhone(rs.getString("phone"));
                address.setAddress(rs.getString("address"));
                address.setName(rs.getString("name"));
                address.setUserId(rs.getInt("user_id"));

                list.add(address);
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
    public long addressReadCount() {
        String sql = "select count(*) as count from address";
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
    public boolean addressDel(int addressId) {
        String sql = "delete from address where id = ?";
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, addressId);
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
    public boolean addressBatDelById(String ids) {
        String sql = "delete from address where id in(" + ids + ")";
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
    public boolean findAddressByName(String addressName) {
        return false;
    }

    @Override
    public Address findAddressById(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from address where id = ?";
        Address address = new Address();
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {


                address.setId(rs.getInt("id"));
                address.setPhone(rs.getString("phone"));
                address.setAddress(rs.getString("address"));
                address.setName(rs.getString("name"));
                address.setUserId(rs.getInt("user_id"));

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
        return address;
    }
    @Override
    public Address findAddressByUserId(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from address where user_id = ?";
        Address address = new Address();
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                address.setId(rs.getInt("id"));
                address.setPhone(rs.getString("phone"));
                address.setAddress(rs.getString("address"));
                address.setName(rs.getString("name"));
                address.setUserId(rs.getInt("user_id"));

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
        return address;
    }

    @Override
    public boolean addressAdd(Address address) {
        String sql = "insert into address( phone,address,name,user_id )" +
                "values ( ? ,? ,? ,?)";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, address.getPhone());
            pstmt.setString(2, address.getAddress());
            pstmt.setString(3, address.getName());
            pstmt.setInt(4, address.getUserId());

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
    public boolean addressUpdate(Address address) {
        String sql = "update address set  phone=?,address=?,name=?,user_id=?  where id=?";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, address.getPhone());
            pstmt.setString(2, address.getAddress());
            pstmt.setString(3, address.getName());
            pstmt.setInt(4, address.getUserId());

            pstmt.setInt(5, address.getId());
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
