package com.ecshop.commodity.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.GoodsImageDao;

@Service("goodsImageService")
public class GoodsImageService {
	@Autowired
	private GoodsImageDao goodsImageDao ;
	
	
	public List<Map<String ,Object>> findGoodsImageById(Map<String, Object> map) {
		return goodsImageDao.findGoodsImageById(map);
	}

	
	
	
	
	
}
