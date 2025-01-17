package com.mall.shop.dao.impl;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Product;
import com.mall.shop.dao.ProductDao;
import com.mall.shop.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> productList(PageBean pageBean) {
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select a.id,a.product_name,a.product_description,a.product_price,a.product_stock,a.product_fid,a.product_cid,a.product_image,a.product_status ,b.cate_name as productcidname,c.cate_name as productfidname from product a \n" +
                "left join category b on a.product_cid = b.id left join category c on a.product_fid = c.id  limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, (pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(2, pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDescription(rs.getString("product_description"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductFid(rs.getInt("product_fid"));
                product.setProductCid(rs.getInt("product_cid"));
                product.setProductCidName(rs.getString("productcidname"));
                product.setProductFidName(rs.getString("productfidname"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductStatus(rs.getInt("product_status"));
                list.add(product);
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
    public List<Product> productAll() {
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from product ";
        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDescription(rs.getString("product_description"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductFid(rs.getInt("product_fid"));
                product.setProductCid(rs.getInt("product_cid"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductStatus(rs.getInt("product_status"));

                list.add(product);
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
    public long productReadCount() {
        String sql = "select count(*) as count from product";
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
    public boolean productDel(int productId) {
        String sql = "delete from product where id = ?";
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, productId);
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
    public boolean productBatDelById(String ids) {
        String sql = "delete from product where id in(" + ids + ")";
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
    public boolean findProductByName(String productName) {
        return false;
    }

    @Override
    public Product findProductById(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from product where id = ?";
        Product product = new Product();
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDescription(rs.getString("product_description"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductFid(rs.getInt("product_fid"));
                product.setProductCid(rs.getInt("product_cid"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductStatus(rs.getInt("product_status"));
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
        return product;
    }

    @Override
    public boolean productAdd(Product product) {
        String sql = "insert into product( product_name, product_description, product_price, product_stock, product_fid, product_cid, product_image, product_status)" +
                "values ( ?,?,?,?,?,?,?,?)";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductDescription());
            pstmt.setDouble(3, product.getProductPrice());
            pstmt.setInt(4, product.getProductStock());
            pstmt.setInt(5, product.getProductFid());
            pstmt.setInt(6, product.getProductCid());
            pstmt.setString(7, product.getProductImage());
            pstmt.setInt(8, product.getProductStatus());

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
    public boolean productUpdate(Product product) {
        String sql = "update product set  product_name=?, product_description=?, product_price=?, product_stock=?, product_fid=?, product_cid=?, product_image=?, product_status=? where id=?";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductDescription());
            pstmt.setDouble(3, product.getProductPrice());
            pstmt.setInt(4, product.getProductStock());
            pstmt.setInt(5, product.getProductFid());
            pstmt.setInt(6, product.getProductCid());
            pstmt.setString(7, product.getProductImage());
            pstmt.setInt(8, product.getProductStatus());

            pstmt.setInt(9, product.getId());
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
    public List<Product> findAllfproduct(Integer product_fid) {
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from product where product_fid=? ";
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, product_fid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDescription(rs.getString("product_description"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductFid(rs.getInt("product_fid"));
                product.setProductCid(rs.getInt("product_cid"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductStatus(rs.getInt("product_status"));

                list.add(product);
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
    public List<Product> findAllcproduct(Integer product_cid) {
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from product where product_cid=? ";
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, product_cid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDescription(rs.getString("product_description"));
                product.setProductPrice(rs.getDouble("product_price"));
                product.setProductStock(rs.getInt("product_stock"));
                product.setProductFid(rs.getInt("product_fid"));
                product.setProductCid(rs.getInt("product_cid"));
                product.setProductImage(rs.getString("product_image"));
                product.setProductStatus(rs.getInt("product_status"));

                list.add(product);
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
}
