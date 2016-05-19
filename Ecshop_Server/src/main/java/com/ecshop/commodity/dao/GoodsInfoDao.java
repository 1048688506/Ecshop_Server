package com.ecshop.commodity.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.ecshop.commodity.model.GoodsInfo;




/**
 * 
 * @author NYXSWL02
 * 用户信息的InforDao接口
 * 
 *
 */
@Component("goodsInfoDao")
public interface GoodsInfoDao {
	
	List<GoodsInfo> findGoodsInfo(int goodsId);

	List<Map<String, Object>> findGoodsInfoByIdAndSort(Map<String, Object> map);

	List<Map<String, Object>> getGoodsComments(Map<String, Object> map);

	List<Map<String, Object>> findGoodsSpecById(Map<String, Object> map);

	List<Map<String, Object>> findGoodsByLikeGName(Map<String, Object> map);
	
}
