package com.mall.shop.dao;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Product;
import java.util.List;

public interface ProductDao {
	//Product 分页列表
    List<Product> productList(PageBean pb);
	//Product List
    List<Product> productAll();
    //统计总数
    long productReadCount();
    //Product Delete
    boolean productDel(int productId);
    //Product Batch Delete
    boolean productBatDelById(String ids);
    //查找Product name
    boolean findProductByName(String productName);
    Product findProductById(int id);
    //Add Product
    boolean productAdd(Product product);
    //EditProduct 
    boolean productUpdate(Product product);

    public List<Product>findAllfproduct(Integer product_fid);
    public List<Product>findAllcproduct(Integer product_cid);

}
