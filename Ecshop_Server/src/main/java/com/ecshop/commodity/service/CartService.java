package com.ecshop.commodity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.CartDao;
import com.ecshop.utils.PageData;

/**
 * 购物车接口服务类
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="mailto:Hoindbq@163.com">Holin Ding</a>
 * @version  Date: 2016年4月29日 上午9:25:21
 * @serial 1.0
 * @since 2016年4月29日 上午9:25:21
 */
@Service("cartService")
public class CartService {
	@Autowired
	private  CartDao dao ;

	public void saveCart(PageData pd) {
		dao.saveCart(pd) ;
	}

	public List<PageData> getCartByCartId(PageData pd) {
		return dao.getCartByCartId(pd);
	}
}
