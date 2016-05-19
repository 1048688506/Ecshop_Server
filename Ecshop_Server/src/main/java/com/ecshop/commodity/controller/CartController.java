package com.ecshop.commodity.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecshop.commodity.service.CartService;
import com.ecshop.utils.PageData;
import com.ecshop.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 购物车接口类
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="mailto:Hoindbq@163.com">Holin Ding</a>
 * @version  Date: 2016年4月29日 上午9:23:58
 * @serial 1.0
 * @since 2016年4月29日 上午9:23:58
 */
@Controller
@RequestMapping(value="/appuser")
public class CartController {
	
	@Autowired
	private CartService cartService ;
	
	/**
	 * 添加商品到购物车，参数为必填参数
	 * 请求示例：http://localhost:8080/Ecshop_Server/appuser/saveCart.do?user_id=1&store_id=1&goods_id=1&goods_name=%E7%9A%AE%E9%9E%8B&spec_id=1&price=200&quantity=1&goods_image=/d/image&specification=%E7%BA%A2%E8%89%B2
	 * @param response
	 * @param user_id 用户ID
	 * @param stroe_id 商铺ID 
	 * @param good_id 商品ID
	 * @param goods_name 商品名称
	 * @param spec_id 规格ID
	 * @param price 商品价格
	 * @param quantity 商品数量
	 * @param goods_image 商品图片地址
	 * @param specification 商品详细说明
	  * 请求返回参数描述：
	 * result : 02 请求服务器成功
	 * result :03  请求传递参数不正确
	 * result :01  服务器异常
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveCart")
	public String saveCart(HttpServletResponse response,@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="store_id",required=false) String store_id,@RequestParam(value="goods_id",required=false) String goods_id ,
			@RequestParam(value="goods_name",required=false)String goods_name,@RequestParam(value="spec_id",required=false)String spec_id,
			@RequestParam(value="price",required=false)String price,@RequestParam(value="quantity",required=false)String quantity,
			@RequestParam(value="goods_image",required=false)String goods_image,@RequestParam(value="specification",required=false)String specification) throws Exception{
		 	PageData  pd= new PageData() ;
		 	JSONObject result = new JSONObject() ;
		 	if(user_id!=null&&user_id!=""&&store_id!=null&&store_id!=""&&goods_id!=null&&goods_id!=""&&spec_id!=null&&spec_id!=""){
		 	pd.put("user_id", Integer.parseInt(user_id)) ;
		 	pd.put("store_id", Integer.parseInt(store_id)) ;
		 	pd.put("goods_id", Integer.parseInt(goods_id)) ;
		 	pd.put("spec_id", Integer.parseInt(spec_id)) ;
		 	pd.put("price", Integer.parseInt(price)) ;
		 	pd.put("goods_name",new String(goods_name.getBytes("ISO-8859-1"), "utf8") ) ;
		 	pd.put("quantity", Integer.parseInt(quantity)) ;
		 	pd.put("goods_image", goods_image) ;
		 	pd.put("specification",new String(specification.getBytes("ISO-8859-1"), "utf8") ) ;
		 	try {
		 		cartService.saveCart(pd);
		 		result.put("result", 02);//加入购物车成功
			} catch (Exception e) {
				e.printStackTrace();
				result.put("result", 01);//服务器异常
			}finally {
				ResponseUtil.write(response, result) ;
			}
			}else{
		 		result.put("result", 03);//参数不合法
		 	}
		 	ResponseUtil.write(response, result) ;
		return null ;
	}
	/**
	 * 请求示例：http://localhost:8080/Ecshop_Server/appuser/getCartByUserId.do?user_id=404
	 * 查看购物车接口
	 * @param response
	 * @param cart_id
	 * @return
	 */
	@RequestMapping(value = "/getCartByUserId")
	public String getCartByUserId(HttpServletResponse response,@RequestParam(value="user_id",required=false) String user_id){
		PageData  pd= new PageData() ;
	 	JSONObject result = new JSONObject() ;
		if(user_id!=null&&user_id!=""){
			pd.put("user_id", Integer.parseInt(user_id)) ;
		}
		List<PageData> list = 	cartService.getCartByCartId(pd);
		JSONArray  jsonArray = JSONArray.fromObject(list);
		result.put("list",jsonArray) ;
		try {
			ResponseUtil.write(response, result);
	} catch (Exception e) {
			e.printStackTrace();
	}
		return null ;
	}
	
}
