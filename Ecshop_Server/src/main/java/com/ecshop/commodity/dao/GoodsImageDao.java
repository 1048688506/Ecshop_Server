package com.ecshop.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface GoodsImageDao {

	public List<Map<String, Object>> findGoodsImageById(Map<String, Object> map) ;

}
