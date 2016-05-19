package com.ecshop.commodity.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.GcategoryDao;
import com.ecshop.commodity.model.Gcategory;
import com.ecshop.commodity.model.Goods;
/**
 * 
 * @author NYXSWL02
 *
 */

@Service("gcategoryService")
public class GcategoryService {
	
	@Autowired
	private GcategoryDao gcategoryDao;
	
	public List<Gcategory> findGcategory(Map<String, Object> map) {
		
		return gcategoryDao.findGcategory(map);
	}

	public List<Gcategory> finListById(Integer store_id) {
		return gcategoryDao.finListById(store_id);
	}

	public List<Goods> findGoodsBySidAndCid(Map<String,Object> map) {
		return gcategoryDao.findGoodsBySidAndCid(map);
	}

	public void saveCollectStore(Map<String ,Object> map) {
		gcategoryDao.saveCollectStore(map);
	}

	public List<Goods> findGoodsBySid(Map<String, Object> map) {
		return gcategoryDao.findGoodsBySid(map);
	}
}
