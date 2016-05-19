package com.ecshop.commodity.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.GoodsInfoDao;
import com.ecshop.commodity.model.GoodsInfo;


/**
 * 
 * @author NYXSWL02
 *
 */

@Service("goodsInfoService")
public class GoodsInfoService {

	@Autowired
	private GoodsInfoDao goodsInfoDao;

	public List<GoodsInfo> findGoodsInfo(int goodsId) {

		return goodsInfoDao.findGoodsInfo(goodsId);
	}

	public List<Map<String ,Object>> findGoodsInfoByIdAndSort(Map<String, Object> map) {
		return goodsInfoDao.findGoodsInfoByIdAndSort(map);
	}

	public List<Map<String, Object>> getGoodsComments(Map<String, Object> map) {
		return goodsInfoDao.getGoodsComments(map);
	}

	public List<Map<String, Object>> findGoodsSpecById(Map<String, Object> map) {

		return goodsInfoDao.findGoodsSpecById(map);
	}

	public List<Map<String, Object>> findGoodsByLikeGName(Map<String, Object> map) {
		return goodsInfoDao.findGoodsByLikeGName(map);
	}
}
