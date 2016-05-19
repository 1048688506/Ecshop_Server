package com.ecshop.commodity.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecshop.commodity.service.CartService;
import com.ecshop.commodity.service.Order_GoodsService;
import com.ecshop.utils.PageData;

/**
 * 
 * TODO 订单商品接口
 * <p/>
 * 
 * @author <a href="1048688506@qq.com">Yang </a>
 * @version  Date: 2016年5月4日 上午10:30:52
 * @serial 1.0
 * @since 2016年5月4日 上午10:30:52
 */
@Controller
@RequestMapping(value="/appuser")
public class Order_GoodsController {
	
	@Autowired
	private Order_GoodsService order_GoodsService ;
	
	@Autowired
	private CartService cartService ;
	/**
	 * 保存订单商品接口
	 * @return
	 */
	@RequestMapping(value="/saveOrder_GoodsInfo")
	public String saveOrder_GoodsInfo(HttpServletResponse response,@ RequestParam(value="rec_id",required=false)String rec_id){
		
		
		
		return null ;
	}
	
	
	
	
	
}
