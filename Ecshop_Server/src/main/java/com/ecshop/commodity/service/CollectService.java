package com.ecshop.commodity.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.CollectDao;
import com.ecshop.commodity.model.Collect;
import com.ecshop.utils.PageData;

/**
 * 
 * @author NYXSWL02
 *
 */

@Service("collectService")
public class CollectService {
	
	@Autowired
	private CollectDao collectDao;
	
	public Collect findAddressInfo(Map<String, Object> map) {
		
		return collectDao.findCollectById(map);
	}
	public int insertCollect(Collect collect){
		
		return collectDao.insertCollect(collect);
	}
	
	public int deleteCollect(Map<String, Object> map){
		return collectDao.deleteCollectById(map);
	}
	public List<PageData> findAllUserGoods(PageData pd) {
		return collectDao.findAllUserGoods(pd);
	}
	public List<PageData> findAllUserStores(PageData pd) {
		return collectDao.findAllUserStores(pd);
	}
}
