package com.mall.shop.dao;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Cart;
import java.util.List;

public interface CartDao {
	//分页列表
    List<Cart> cartList(PageBean pb);
	//列表
    List<Cart> cartAll();
    //统计总数
    long cartReadCount();
    //Delete
    boolean cartDel(int cartId);
    //Batch Delete
    boolean cartBatDelById(String ids);
    //查找名称
    boolean findCartByName(String cartName);
    Cart findCartById(int id);

    List<Cart> findCartByUserId(int id);
    //增加
    boolean cartAdd(Cart cart);
    //Edit
    boolean cartUpdate(Cart cart);

    public Integer checkcart(Integer user_id,Integer product_id);
    public void updatecart_num(Integer user_id,Integer product_id,Integer cart_num);

    public void cart_numchange(Integer cart_id,Integer count);

    public Cart searchcartrecord(Integer cart_id);

}
