package com.ecshop.commodity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecshop.commodity.model.Gcategory;
import com.ecshop.commodity.model.Goods;
import com.ecshop.commodity.service.GcategoryService;
import com.ecshop.commodity.service.GoodsService;
import com.ecshop.utils.DateUtil;
import com.ecshop.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * http://localhost:8080/ecshop/gcategory/getGcategory.do
 * http://localhost:8080/ecshop/gcategory/Goods.do
 * @author NYXSWL02
 */
@Controller
@RequestMapping("/gcategory")
public class GcategoryController {
	
	@Autowired
	private GcategoryService gcategoryService;
	
	@Autowired
	private GoodsService goodsService;
	/**
	 * 获取商品分类
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/getGcategory")
	public String getList(HttpServletResponse response){
		 
		List<Gcategory> gcategoryList=gcategoryService.findGcategory(null);
		JSONObject result=new JSONObject();

	    JSONArray jsonArray=JSONArray.fromObject(gcategoryList);
	    
		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据店铺ID获取店铺商品分类
	 * 请求示例：localhost:8080/Ecshop_Server/getListById.do?Store_id=1
	 * @param response
	 * @param Store_id 必填参数
	 */
	@RequestMapping(value = "/getGcategoryGoodListById")
	public void getListById(HttpServletResponse response,@RequestParam("Store_id") String Store_id ){
			Integer store_id = Integer.parseInt(Store_id) ;
			JSONObject result =  new JSONObject() ;
			List<Gcategory> list = gcategoryService.finListById(store_id) ;
			JSONArray jsonArray = JSONArray.fromObject(list) ;
			result.put("list", jsonArray);
			try {
					ResponseUtil.write(response, result);
			} catch (Exception e) {
					e.printStackTrace();
			}
			
	}
	/**
	 * 请求返回参数描述：
	 * result : 02 请求服务器成功
	 * result :03  请求传递参数不正确
	 * result :01  服务器异常
	 * 根据店铺ID，收藏店铺
	 * @param response
	 * @param Item_id
	 * @param Item_type
	 * @param User_id
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveCollectStore")
	public void saveCollectStore(HttpServletResponse response ,@RequestParam(value="Item_id",required = false) String Item_id,
			@RequestParam(value="Item_type",required = false) String Item_type,
			@RequestParam(value="User_id",required = false) String User_id) throws Exception{
		JSONObject result  = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>() ;
		if(Item_id!=null&&Item_id!=""&&Item_type!=null&&Item_type!=""&&User_id!=null&&User_id!=""){
			Integer item_id = Integer.parseInt(Item_id);
			Integer user_id = Integer.parseInt(User_id);
			map.put("item_id", item_id) ;
			map.put("user_id", user_id);
			map.put("type", Item_type) ;
			map.put("add_time",Integer.parseInt(DateUtil.getDays())) ;
			try{
					gcategoryService.saveCollectStore(map) ;
					result.put("result", "02") ;
			}catch(Exception e){
				e.printStackTrace();
				result.put("result", "01");//服务器异常
			}finally {
				ResponseUtil.write(response, result) ;
			}
		}else{
			result.put("result", "03");//传递参数不正确
			ResponseUtil.write(response, result) ;
		}
		
	} 
		
		
	/**
	 * 根据店铺ID 分类ID获取该店铺分类下的所有商品
	 * @param response
	 * @param Store_id
	 * @param Cate_id
	 */
	@RequestMapping(value = "/getGoodsList")
	public void getGoodsBySidAndCid(HttpServletResponse response ,@RequestParam("Store_id") String Store_id,
			@RequestParam("Cate_id") String Cate_id	){
		Integer store_id = Integer.parseInt(Store_id);
		Integer cate_id  = Integer.parseInt(Cate_id);
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("store_id",store_id) ;
		map.put("cate_id", cate_id);
		
		JSONObject result = new JSONObject() ;
		List<Goods> goodList = gcategoryService.findGoodsBySidAndCid(map) ;
		JSONArray jsonArray = JSONArray.fromObject(goodList) ;
		result.put("list", jsonArray);
		try {
				ResponseUtil.write(response, result);
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	/**
	 * 根据店铺ID获取该店铺下所有的商品
	 * @param response
	 * @param Store_id
	 */
	@RequestMapping(value = "/getGoodsBySid")
	public void getGoodsBySid(HttpServletResponse response ,@RequestParam("Store_id") String Store_id){
		Integer store_id = Integer.parseInt(Store_id);
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("store_id",store_id) ;
		JSONObject result = new JSONObject() ;
		List<Goods> goodList = gcategoryService.findGoodsBySid(map) ;
		JSONArray jsonArray = JSONArray.fromObject(goodList) ;
		result.put("list", jsonArray);
		try {
				ResponseUtil.write(response, result);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	
	/**
	 * http://localhost:8080/ecshop/gcategory/Goods.do
	 * 根据三级分类ID,查出所有属于商品
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/Goods")
	public String getGoodsList(HttpServletResponse response,@RequestParam("cate_id") String cate_id){
		 
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(null == cate_id || "".equals(cate_id))map.put("cate_id", null);
		else map.put("cate_id", cate_id);

		List<Goods> GoodsList=goodsService.findGoods(map);
		
		JSONObject result=new JSONObject();

	    JSONArray jsonArray=JSONArray.fromObject(GoodsList);
	    
		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * http://localhost:8080/ecshop/gcategory/Goodsby1.do?cate_id=10&sort=comments
	 * 根据一级分类ID,查出所有属于商品，并进行排序
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/Goodsby1")
	public String getGoodsListBy1(HttpServletResponse response,@RequestParam("cate_id") String cate_id,@RequestParam("sort") String sort){
		 
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
		    sort ="gs.comments desc"; //按评论次数排序d
		}
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(null == cate_id || "".equals(cate_id))map.put("cate_id", null);
		else map.put("cate_id", cate_id);
		
		map.put("sort", sort);
		List<Goods> GoodsList=goodsService.findGoods_1(map);
		
		JSONObject result=new JSONObject();

	    JSONArray jsonArray=JSONArray.fromObject(GoodsList);
	    
		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * http://localhost:8080/ecshop/gcategory/Goodsby2.do
	 * 根据二级分类ID,查出所有属于商品
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/Goodsby2")
	public String getGoodsListBy2(HttpServletResponse response,@RequestParam("cate_id") String cate_id){
		 
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(null == cate_id || "".equals(cate_id))map.put("cate_id", null);
		else map.put("cate_id", cate_id);

		List<Goods> GoodsList=goodsService.findGoods_2(map);
		
		JSONObject result=new JSONObject();

	    JSONArray jsonArray=JSONArray.fromObject(GoodsList);
	    
		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
