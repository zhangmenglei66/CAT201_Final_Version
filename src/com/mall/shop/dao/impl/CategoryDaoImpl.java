package com.mall.shop.dao.impl;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Category;
import com.mall.shop.dao.CategoryDao;
import com.mall.shop.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> categoryList(PageBean pageBean) {
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from category limit ? ,?";

        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, (pageBean.getCurPage() - 1) * pageBean.getMaxSize());
            pstmt.setInt(2, pageBean.getMaxSize());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setCateParentId(rs.getInt("cate_parent_id"));

                list.add(category);
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
    public List<Category> categoryAll() {
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from category ";
        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setCateParentId(rs.getInt("cate_parent_id"));
                list.add(category);
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
    public List<Category> categoryFather() {
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from category where cate_parent_id = 0";
        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setCateParentId(rs.getInt("cate_parent_id"));
                list.add(category);
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
    public List<Category> categoryChildren(int fid) {
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from category where cate_parent_id = ?";
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, fid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setCateParentId(rs.getInt("cate_parent_id"));
                list.add(category);
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
    public long categoryReadCount() {
        String sql = "select count(*) as count from category";
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
    public boolean categoryDel(int categoryId) {
        String sql = "delete from category where id = ?";
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, categoryId);
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
    public boolean categoryBatDelById(String ids) {
        String sql = "delete from category where id in(" + ids + ")";
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
    public boolean findCategoryByName(String categoryName) {
        return false;
    }

    @Override
    public Category findCategoryById(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from category where id = ?";
        Category category = new Category();
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setCateParentId(rs.getInt("cate_parent_id"));
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
        return category;
    }

    @Override
    public boolean categoryAdd(Category category) {
        String sql = "insert into category( cate_name,cate_parent_id )" +
                "values ( ? ,?)";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, category.getCateName());
            pstmt.setInt(2, category.getCateParentId());

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
    public boolean categoryUpdate(Category category) {
        String sql = "update category set  cate_name=?,cate_parent_id=?  where id=?";

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            pstmt = DbUtil.getPstmt(sql);
            pstmt.setString(1, category.getCateName());
            pstmt.setInt(2, category.getCateParentId());

            pstmt.setInt(3, category.getId());
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

    public List<Category> findAllParentCate(){
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from category where cate_parent_id =0 ";
        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setCateParentId(rs.getInt("cate_parent_id"));
                list.add(category);
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


    public List<Category> findAllChildCate(){
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //1.定义参数化的sql语句
        String sql = "select * from category where cate_parent_id !=0 ";
        try {
            pstmt = DbUtil.getPstmt(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setCateParentId(rs.getInt("cate_parent_id"));
                list.add(category);
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
