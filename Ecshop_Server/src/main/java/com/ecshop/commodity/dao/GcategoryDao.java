package com.ecshop.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecshop.commodity.model.Gcategory;
import com.ecshop.commodity.model.Goods;
/**
 * 
 * @author NYXSWL02
 * 用户信息的InforDao接口
 * 
 *
 */
@Component("hcategoryDao")
public interface GcategoryDao {
	
	List<Gcategory> findGcategory(Map<String, Object> map);

	List<Gcategory> finListById(Integer store_id);

	List<Goods> findGoodsBySidAndCid(Map<String,Object> map);

	void saveCollectStore(Map<String ,Object> map);

	List<Goods> findGoodsBySid(Map<String, Object> map);
	
}
