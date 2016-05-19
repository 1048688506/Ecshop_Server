package com.ecshop.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecshop.commodity.model.EcmOrder;
import com.ecshop.utils.PageData;
/**
 * 订单接口数据交互层
 * @author Yang
 *
 */
@Component("orderDao")
public interface OrderDao {

	public List<EcmOrder> findOrderById(int userId,byte status) ;

	public List<PageData> findOrderAll();


	public List<Map<String, Object>> getOrderList(PageData pd);

	public List<PageData> forExcel(PageData pd);

	public List<PageData> findOrderListByUserIdAndStatus(PageData pd);

		
}
