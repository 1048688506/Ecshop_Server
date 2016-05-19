package com.ecshop.commodity.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ecshop.commodity.model.Address;



/**
 * 
 * @author NYXSWL02
 * 用户信息的InforDao接口
 * 
 *
 */
@Component("addressDao")
public interface AddressDao {
	
	List<Address> findAddressByUserId(int user_id);

	List<Address> getAll();
	
}
