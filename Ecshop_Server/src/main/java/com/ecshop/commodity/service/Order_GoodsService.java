package com.ecshop.commodity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.Order_GoodsDao;
/**
 * 
 * TODO 订单商品接口服务层
 * <p/>
 * 
 * @author<a href="1048688506@qq.com">Yang </a>
 * @version  Date: 2016年5月4日 上午10:33:25
 * @serial 1.0
 * @since 2016年5月4日 上午10:33:25
 */
@Service("Order_GoodsService")
public class Order_GoodsService {

	@Autowired
	private Order_GoodsDao dao ;
}
