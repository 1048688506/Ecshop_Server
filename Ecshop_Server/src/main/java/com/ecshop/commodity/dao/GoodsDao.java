package com.ecshop.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecshop.commodity.model.Goods;




/**
 * 
 * @author NYXSWL02
 * 用户信息的InforDao接口
 * 
 *
 */
@Component("goodsDao")
public interface GoodsDao {
	
	List<Goods> findGoods(Map<String, Object> map);
	
	List<Goods> findGoods_1(Map<String, Object> map);
	
	List<Goods> findGoods_2(Map<String, Object> map);
	
}
