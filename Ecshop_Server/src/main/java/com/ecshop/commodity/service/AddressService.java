package com.ecshop.commodity.service;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecshop.commodity.dao.AddressDao;
import com.ecshop.commodity.model.Address;


/**
 * 
 * @author NYXSWL02
 *
 */

@Service("addressService")
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	public List<Address> findAddressInfo(int user_id) {
		
		return addressDao.findAddressByUserId(user_id);
	}

	public List<Address> exportExcel() {
		return addressDao.getAll() ;
	}
}
