package com.mall.shop.dao;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Orders;
import java.util.List;

public interface OrdersDao {
	//订单分页列表
    List<Orders> ordersList(PageBean pb);

    List<Orders> myordersList(int userId,PageBean pb);
	//Order List
    List<Orders> ordersAll();
    //统计总数
    long ordersReadCount();
    long myordersReadCount(int userId);
    //订单Delete
    boolean ordersDel(int ordersId);
    //订单Batch Delete
    boolean ordersBatDelById(String ids);
    //查找订单名称
    boolean findOrdersByName(String ordersName);
    Orders findOrdersById(int id);
    //增加订单
    boolean ordersAdd(Orders orders);
    //Edit订单
    boolean ordersUpdate(Orders orders);

}
