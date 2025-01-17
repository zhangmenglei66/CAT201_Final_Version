package com.mall.shop.dao;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Category;
import java.util.List;

public interface CategoryDao {
	//分类分页列表
    List<Category> categoryList(PageBean pb);
	//Category List
    List<Category> categoryAll();
    List<Category> categoryFather();
    List<Category> categoryChildren(int fid);
    //统计总数
    long categoryReadCount();
    //分类Delete
    boolean categoryDel(int categoryId);
    //分类Batch Delete
    boolean categoryBatDelById(String ids);
    //查找Category name
    boolean findCategoryByName(String categoryName);
    Category findCategoryById(int id);
    //增加分类
    boolean categoryAdd(Category category);
    //Edit分类
    boolean categoryUpdate(Category category);

    public List<Category> findAllParentCate();

    public List<Category> findAllChildCate();

}
