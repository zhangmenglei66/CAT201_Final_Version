package com.mall.shop.dao;

import com.mall.shop.bean.PageBean;
import com.mall.shop.bean.Address;
import java.util.List;

public interface AddressDao {
	//Shipping address分页列表
    List<Address> addressList(PageBean pb);
	//Shipping Address List
    List<Address> addressAll();
    //统计总数
    long addressReadCount();
    //Shipping addressDelete
    boolean addressDel(int addressId);
    //Shipping addressBatch Delete
    boolean addressBatDelById(String ids);
    //查找Shipping address名称
    boolean findAddressByName(String addressName);
    Address findAddressById(int id);
    Address findAddressByUserId(int id);
    //Add Shipping address
    boolean addressAdd(Address address);
    //EditShipping address
    boolean addressUpdate(Address address);

}
