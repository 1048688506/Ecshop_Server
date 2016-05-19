package com.ecshop.commodity.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.GoodsDao;
import com.ecshop.commodity.model.Goods;


/**
 * 
 * @author NYXSWL02
 *
 */

@Service("goodsService")
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	public List<Goods> findGoods(Map<String, Object> map) {

		return goodsDao.findGoods(map);
	}

	public List<Goods> findGoods_1(Map<String, Object> map) {

		return goodsDao.findGoods_1(map);
	}

	public List<Goods> findGoods_2(Map<String, Object> map) {

		return goodsDao.findGoods_2(map);
	}
}
