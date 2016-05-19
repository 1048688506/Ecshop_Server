package com.ecshop.commodity.controller;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecshop.commodity.model.GoodsInfo;
import com.ecshop.commodity.service.GoodsInfoService;
import com.ecshop.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 * http://localhost:8080/ecshop/goodsInfo/getGoodsInfo.do?goods_id=100
 * 根据商品ID查询商品信息
 * @author NYXSWL02
 */

@Controller
@RequestMapping("/goodsInfo")
public class GoodsInfoController {
	
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	/**
	 * 获取商品分类
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/getGoodsInfo")
	public String getAddressById(HttpServletResponse response,@RequestParam("goods_id") String goods_id){
		 
		int goodsId  = Integer.parseInt(goods_id);
		List<GoodsInfo> addressInfo=goodsInfoService.findGoodsInfo(goodsId);
		JSONObject result=new JSONObject();
        
	    JSONArray jsonArray=JSONArray.fromObject(addressInfo);
	    
		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据商铺的D获取商铺下所有商品，根据排序字段获取 该商铺以该排序字段的排序方式下商品
	 * 请求示例 ：http://localhost:8080/Ecshop_Server/goodsInfo/getGoodsInfoByIdAndSort.do?Store_id=517&sort=pricedesc
	 * 要严格按照示例格式写请求
	 * @param response
	 * @param Store_id 首次请求必传（商铺的ID）
	 * @param sort         首次请求必传（排序的字段） 示例：priceasc或者pricedesc 服务器会自动解析
	 *  priceasc   = price + asc(升序)   服务器会以 price 以商品价格升序排序
	 *  pricedesc = price + asc(降序)   服务器会以 price 以商品价格降序排序
	 *  1   排序分类：默认按店铺的信用度排序 （不传入排序字段）
	 *  2  按商品价格排序 （升序和降序两种）
	 *  3   按商品的销量排序（只有一种，默认是降序例 ：sales desc）
	 *  4  按评论次数排序（只有一种，默认是降序例 ：comments desc）
	 * @return 商品列表
	 */
	@RequestMapping(value = "/getGoodsInfoByIdAndSort")
	public String getGoodsInfoByIdAndSort(HttpServletResponse response , @RequestParam(value="Store_id",required =false)String Store_id,
			@RequestParam(value = "sort",required=false) String sort){
			int store_id = Integer.parseInt(Store_id);
			if("default".equals(sort))
			{
				sort = "s.praise_rate desc"; //默认按店铺的信用度排序 
			}
			if("priceasc".equals(sort)) 
			{
				sort = "g.price asc"; //按商品价格排序
			}
			if("pricedesc".equals(sort)) 
			{
				sort = "g.price desc"; //按商品价格排序
			}
			if("sales".equals(sort))
			{
			    sort ="gs.sales desc"; //按商品的销量排序
			}
			if("comments".equals(sort))
			{
			    sort ="gs.comments desc"; //按评论次数排序
			}
			Map <String ,Object> map = new HashMap<String ,Object>();
			JSONObject result = new JSONObject() ;
			map.put("store_id", store_id);
			map.put("sort",sort) ;
			List<Map<String ,Object>> list = goodsInfoService.findGoodsInfoByIdAndSort(map) ;
			JSONArray jsonarray = JSONArray.fromObject(list) ;
			result.put("result", jsonarray) ;
			
			try {
				ResponseUtil.write(response, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null ;
	}
	/**
	 * 该接口主要用于获取被购买的商品的信息
	 * @param response
	 * @param Goods_id 商品ID
	 * 请求示例 ：http://localhost:8080/Ecshop_Server/goodsInfo/getGoodsComments.do?Goods_id=391
	 * @since 2016年4月21日 上午10:21:00
	 * @return
	 */
	@RequestMapping(value="/getGoodsComments")
	public String getGoodsComments(HttpServletResponse response ,@RequestParam (value ="Goods_id",required = false) String Goods_id){
		int goods_id = Integer.parseInt(Goods_id);
		
		Map <String ,Object> map = new HashMap<String ,Object>() ;
		
		JSONObject result = new JSONObject();
		
		map.put("goods_id",goods_id) ;
		
		List<Map<String,Object>> list  = goodsInfoService.getGoodsComments(map);
		
		JSONArray jsonarray = JSONArray.fromObject(list);
	
		result.put("list", jsonarray); 
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	/**
	 * 该接口用于通过商品的ID获取商品商品的规格
	 * 请求示例：http://localhost:8080/Ecshop_Server/goodsInfo/getGoodsSpecById.do?Goods_id=548
	 * @param response
	 * @param Goods_id 商品ID 比传入参数
	 * @return 返回该商品的规格列表
	 */
	@RequestMapping(value = "/getGoodsSpecById")
	public String getGoodsSpecById(HttpServletResponse response , @RequestParam (value ="Goods_id",required=false) String Goods_id){
		int goods_id = Integer.parseInt(Goods_id) ;
		
		Map <String ,Object> map = new HashMap<String ,Object>() ;
		
		JSONObject result = new JSONObject() ;
		
		map.put("goods_id", goods_id) ;
		
		List<Map<String ,Object>>  list = goodsInfoService. findGoodsSpecById(map) ;
		
		JSONArray  jsonarray = JSONArray.fromObject(list);
		
		result.put("result", jsonarray) ;
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 模糊搜索 通过商品名称中的关键字获取商品的信息
	 * 请求示例：http://localhost:8080/Ecshop_Server/goodsInfo/getGoodsByLikeGName.do?goods_name=媚音
	 * @param response
	 * @param goods_name 商品命中的关键字
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/getGoodsByLikeGName")
	public String getGoodsByLikeGName(HttpServletResponse response,@RequestParam(value="goods_name",required = false)String goods_name ) throws UnsupportedEncodingException{
		String goods_tags = new String(goods_name.getBytes("ISO-8859-1"), "utf8") ;
		Map <String ,Object> map = new HashMap<String ,Object>() ;
		JSONObject result = new JSONObject() ;
		map.put("goods_tags", goods_tags) ;
		List<Map<String ,Object>>  list = goodsInfoService.findGoodsByLikeGName(map);
		JSONArray  jsonarray = JSONArray.fromObject(list);
		result.put("result", jsonarray) ;
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null ;
	}
}
