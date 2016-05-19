package com.ecshop.commodity.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ecshop.commodity.model.Collect;
import com.ecshop.utils.PageData;



/**
 * 
 * @author NYXSWL02
 * 用户信息的InforDao接口
 * 
 *
 */
@Component("collectDao")
public interface CollectDao {
	
	Collect findCollectById(Map<String, Object> map);
	
	int insertCollect(Collect collect);
	
	int deleteCollectById(Map<String, Object> map);
	
	List<PageData> findAllUserGoods(PageData pd);

	List<PageData> findAllUserStores(PageData pd);
	
}
