package com.ecshop.commodity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecshop.commodity.service.GoodsImageService;
import com.ecshop.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * TODO 商品图片接口
 * <p/>
 * 
 * @author <a href="1048688506@qq.com">Yang</a>
 * @version  Date: 2016年4月20日 上午9:37:13
 * @serial 1.0
 * @since 2016年4月20日 上午9:37:13
 */
@Controller
@RequestMapping(value="/appuser")
public class GoodsImageController {
	@Autowired
	private GoodsImageService goodsImageService ;
	/**
	 * 接口返回值协议
	 * 03 参数不合法
	 * @param response
	 * @param Goods_id
	 * @return  该商品对应的缩略图地址列表 
	 * @throws Exception
	 */
	@RequestMapping(value = "/getGoodsImageById")
	public  String  getGoodsImageById(HttpServletResponse response ,@RequestParam(value="Goods_id",required = false) String Goods_id ) throws Exception{
		Map<String ,Object> map  = new HashMap<String ,Object>() ;	
		JSONObject result  = new JSONObject() ;
		if(Goods_id!=null&&Goods_id!=""){
				int goods_id = Integer.parseInt(Goods_id) ; 
				map.put("goods_id", goods_id) ;
				List<Map<String ,Object>> list  = goodsImageService.findGoodsImageById(map) ;
				JSONArray jsonaArray =JSONArray.fromObject(list)  ;
				result.put("list", jsonaArray) ;
				try {
					ResponseUtil.write(response, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
				}else{
					result.put("result", 03) ;//请求参数不合法
					ResponseUtil.write(response, result);
				}
				return null; 
	}
	
	
	
}
