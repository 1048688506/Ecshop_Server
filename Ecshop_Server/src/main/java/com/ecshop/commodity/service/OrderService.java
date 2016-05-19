package com.ecshop.commodity.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.OrderDao;
import com.ecshop.commodity.model.EcmOrder;
import com.ecshop.utils.PageData;
/**
 * 订单接口服务层
 * @author Yang
 *
 */
@Service("orderService")
public class OrderService {
	@Autowired
	private OrderDao dao ;
	
	public List<EcmOrder> getOrderInfoByUserId(int userId,byte status) {
		
		return dao.findOrderById(userId,status) ;
	}

	public List<PageData> listAll() {
		return dao.findOrderAll();
	}

	public List<PageData> exportExcel(PageData pd) {
		return dao.forExcel(pd);
	}

	public List<Map<String, Object>> getOrderList(PageData pd) {
		return dao.getOrderList(pd);
	}

	public List<PageData> findOrderListByUserIdAndStatus(PageData pd) {
		return dao.findOrderListByUserIdAndStatus(pd);
	}
}
