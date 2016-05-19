package com.ecshop.commodity.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecshop.commodity.model.Collect;
import com.ecshop.commodity.service.CollectService;
import com.ecshop.utils.PageData;
import com.ecshop.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="1048688506@qq.com">Yang</a>
 * @version  Date: 2016年5月5日 上午9:58:59
 * @serial 1.0
 * @since 2016年5月5日 上午9:58:59
 */
@Controller
@RequestMapping("/collect")
public class CollectController {
	
	
	@Autowired
	private CollectService collectService;
	/**
	 * 查询该用户下有没有收藏该商品
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/getCollectInfo")
	public String getCollectInfo(HttpServletResponse response,@RequestParam("user_id") String user_id,@RequestParam("goods_id") String goods_id){
		 
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user_id", user_id);
		map.put("goods_id", goods_id);
		Collect collect = collectService.findAddressInfo(map);
		String [] str = null;
		
		if(null == collect)
		{
			str = new String[]{"0"};
		}else
		{
			str = new String[]{"1"};
		}
		
		JSONObject result=new JSONObject();
        
	    JSONArray jsonArray=JSONArray.fromObject(str);
	    
		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 收藏该商品
	 * http://localhost:8080/ecshop/collect/insertCollect.do?user_id=?goods_id=?
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/insertCollect")
	public String insertCollectInfo(HttpServletResponse response,@RequestParam("user_id") String user_id,@RequestParam("goods_id") String goods_id){
		 
		
		Collect collect = new Collect();
		collect.setUser_id(Integer.parseInt(user_id));
		collect.setLtem_id(Integer.parseInt(goods_id));
		//collect.setAdd_time((int)System.currentTimeMillis());
		collect.setAdd_time(0);
		
		int num = collectService.insertCollect(collect);
		
		
		System.out.println(num);
		
		JSONObject result=new JSONObject();
        
	    JSONArray jsonArray=JSONArray.fromObject(num);
	    
		result.put("num", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除收藏该商品
	 * http://localhost:8080/ecshop/collect/deleteCollect.do?user_id=?goods_id=?
	 * 
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteCollect")
	public String deleteCollectInfoById(HttpServletResponse response,@RequestParam("user_id") String user_id,@RequestParam("goods_id") String goods_id){
		 
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user_id", user_id);
		map.put("goods_id", goods_id);
		
		int num = collectService.deleteCollect(map);
		
		
		JSONObject result=new JSONObject();
        
	    JSONArray jsonArray=JSONArray.fromObject(num);
	    
		result.put("num", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取用户收藏的商品
	 * 请求示例：localhost:8080/Ecshop_Server/collect/findAllUserGoodsOrStore.do?user_id=?
	 * 查看用户收藏的商品
	 * @param response
	 * @param user_id 用户ID 必传参数
	 * @param type 查询类型 是收藏的店铺还是商品 可选值（store / goods）必传参数
	 * @return
	 */
	@RequestMapping(value="/findAllUserGoodsOrStore")
	public String getAllUserGoodsOrStore(HttpServletResponse response,@RequestParam(value="user_id",required=false)String user_id,@RequestParam(value="type",required=false)String type){
		PageData pd = new PageData() ;
		List<PageData> list = null ;
		if(user_id!=null&&user_id!=""&&type!=null&&type!=""){
			pd.put("type", type);
			pd.put("user_id", Integer.parseInt(user_id));
		}
		if(pd.get("type").equals("goods")){
			 list = collectService.findAllUserGoods(pd);
		}else if(pd.get("type").equals("store")){
			 list = collectService.findAllUserStores(pd);
		}
		
		JSONObject result=new JSONObject();
        
	    JSONArray jsonArray=JSONArray.fromObject(list);
	    
	     result.put("list", jsonArray) ;
	     try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null ;
	}
	
}
