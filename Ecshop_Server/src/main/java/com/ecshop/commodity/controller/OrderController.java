package com.ecshop.commodity.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecshop.commodity.model.EcmOrder;
import com.ecshop.commodity.service.OrderService;
import com.ecshop.utils.DateUtil;
import com.ecshop.utils.ObjectExcelView;
import com.ecshop.utils.PageData;
import com.ecshop.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订单接口
 * 
 * @author Yang
 *
 */
@RequestMapping(value = "/appuser")
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	
	
/*	public String saveOrders(HttpServletResponse response ){
		PageData pd  = new PageData();
		JSONObject result = new JSONObject();
		
		
		
		
		
		return null ;
	}*/
	
	
	
	
	/**
	 * 类接口说明 此接口接受两个参数 user_id status 
	 *  请求结果为查询类实体
	 * 
	 * @param user_id
	 * @param status
	 * @return void
	 */
	@RequestMapping(value = "/getOrderByUserId")
	@ResponseBody
	public void getOrderByUserId(HttpServletResponse response, @RequestParam("user_id") String user_id,
			@RequestParam("status") byte status) {

		int userId = Integer.parseInt(user_id);
		List<EcmOrder> ecmOrder = orderService.getOrderInfoByUserId(userId, status);
		JSONObject result = new JSONObject();

		JSONArray jsonArray = JSONArray.fromObject(ecmOrder);

		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView forExcel(HttpServletResponse response,
			@RequestParam(value = "buyer_name", required = false) String buyer_name,
			@RequestParam(value = "payment_name", required = false) String payment_name,
			@RequestParam(value = "order_sn", required = false) String order_sn,
			@RequestParam(value = "seller_name", required = false) String seller_name,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "startPrice", required = false) String startPrice,
			@RequestParam(value = "endPrice", required = false) String endPrice,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime) throws ParseException{
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		int startPrices = 0;
		int endPrices = 0;
		int startTimes = 0;
		int endTimes = 0;

		if (startPrice != null && startPrice != "") {
			startPrices = Integer.parseInt(startPrice);
		}
		if (endPrice != null && endPrice != "") {
			endPrices = Integer.parseInt(endPrice);

		}
		if (startTime != null && startTime != "") {
			startTimes = DateUtil.stringDateFormattimeStamp(startTime);
		}
		if (endTime != null && endTime != "") {
			endTimes =DateUtil.stringDateFormattimeStamp(endTime);
		}
		pd.put("buyer_name", buyer_name);
		pd.put("payment_name", payment_name);
		pd.put("order_sn", order_sn);
		pd.put("seller_name", seller_name);
		pd.put("status", status);
		pd.put("startPrices", startPrices);
		pd.put("endPrices", endPrices);
		pd.put("startTimes", startTimes);
		pd.put("endTimes", endTimes);
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("订单ID");
			titles.add("订单号");
			titles.add("店铺名称");
			titles.add("下单时间");
			titles.add("买家姓名");
			titles.add("商品总金额");
			titles.add("订单状态");
			titles.add("订单折扣价");
			titles.add("支付名");
			titles.add("支付时间");
			titles.add("收件人");
			titles.add("送货地址");
			titles.add("座机电话");
			titles.add("手机电话");
			titles.add("送货方式");
			titles.add("邮编");
			titles.add("地区名");
			titles.add("商品名");
			titles.add("商品单价");
			titles.add("数量");
			titles.add("是否有效");
			dataMap.put("titles", titles);
			List<PageData> varOList =  orderService.exportExcel(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				if(varOList.get(i).get("add_time")!=null&&varOList.get(i).get("add_time")!=""){
					String DateAddTime  = DateUtil.timeStampToDate(String.valueOf( varOList.get(i).get("add_time"))) ;
					varOList.get(i).put("add_time", DateAddTime);
					}
					if( varOList.get(i).get("pay_time")!=null&&varOList.get(i).get("pay_time")!=""){
						String DatePayTime  = DateUtil.timeStampToDate(String.valueOf( varOList.get(i).get("pay_time"))) ;
						varOList.get(i).put("pay_time", DatePayTime);
					}
				if(String.valueOf(varOList.get(i).get("status")).equals("40")){
					varOList.get(i).put("status", "已完成");
					}
				if(String.valueOf(varOList.get(i).get("status")).equals("0")){
					varOList.get(i).put("status", "已取消");
				}
				if(String.valueOf(varOList.get(i).get("status")).equals("30")){
					varOList.get(i).put("status", "已发货");
				}
				if(String.valueOf(varOList.get(i).get("status")).equals("20")){
					varOList.get(i).put("status", "待发货");
				}
				if(String.valueOf(varOList.get(i).get("status")).equals("11")){
					varOList.get(i).put("status", "待付款");
				}
				if(String.valueOf(varOList.get(i).get("status")).equals("1")){
					varOList.get(i).put("status", "已提交");
				}
				PageData vpd = new PageData();
				vpd.put("var1", String.valueOf(varOList.get(i).get("order_id")));	//1
				vpd.put("var2", String.valueOf(varOList.get(i).get("order_sn")));	//2
				vpd.put("var3", String.valueOf(varOList.get(i).get("seller_name")));	//3
				vpd.put("var4",String.valueOf( varOList.get(i).get("add_time")));	//4
				vpd.put("var5", String.valueOf(varOList.get(i).get("buyer_name")));	//5
				vpd.put("var6", String.valueOf(varOList.get(i).get("goods_amount")));	//6
				vpd.put("var7",String.valueOf( varOList.get(i).get("status")));	//7
				vpd.put("var8", String.valueOf(varOList.get(i).get("order_amount")));	//8
				vpd.put("var9", String.valueOf(varOList.get(i).get("payment_name")));	//9
				vpd.put("var10", String.valueOf(varOList.get(i).get("pay_time")));	//10
				vpd.put("var11", String.valueOf(varOList.get(i).get("consignee")));	//11
				vpd.put("var12",String.valueOf( varOList.get(i).get("address")));	//12
				vpd.put("var13",String.valueOf( varOList.get(i).get("phone_tel")));	//13
				vpd.put("var14",String.valueOf( varOList.get(i).get("phone_mob")));	//14
				vpd.put("var15",String.valueOf( varOList.get(i).get("shipping_name")));	//15
				vpd.put("var16",String.valueOf( varOList.get(i).get("zipcode")));	//16
				vpd.put("var17", String.valueOf(varOList.get(i).get("region_name")));	//17
				vpd.put("var18", String.valueOf(varOList.get(i).get("goods_name")));	//18
				vpd.put("var19", String.valueOf(varOList.get(i).get("price")));	//19
				vpd.put("var20", String.valueOf(varOList.get(i).get("quantity")));	//20
				vpd.put("var21", String.valueOf(varOList.get(i).get("is_valid")));	//21
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	

	/**
	 * 获取所有订单
	 * 
	 * @param response
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@RequestMapping(value = "/getOrderList")
	public String getOrderList(HttpServletResponse response,	@RequestParam (value = "page" ,required = false) String page,
			@RequestParam(value = "rows" ,required =false) String rows) throws UnsupportedEncodingException {
		//List<Map<String, Object>> orderList = new ArrayList<>(50);
		PageData pd = new PageData(); 
		pd.put("page",Integer.parseInt(page)) ;
		pd.put("rows", Integer.parseInt(rows)) ;
		List<Map<String, Object>> list = orderService.getOrderList(pd);
		for (int j = 0; j < list.size(); j++) {
			if(list.get(j).get("add_time")!=null&&list.get(j).get("add_time")!=""){
			String DateAddTime  = DateUtil.timeStampToDate(String.valueOf( list.get(j).get("add_time"))) ;
			list.get(j).put("add_time", DateAddTime);
			}
			if( list.get(j).get("pay_time")!=null&& list.get(j).get("pay_time")!=""){
				String DatePayTime  = DateUtil.timeStampToDate(String.valueOf( list.get(j).get("pay_time"))) ;
				list.get(j).put("pay_time", DatePayTime);
			}
		if(String.valueOf(list.get(j).get("status")).equals("40")){
				list.get(j).put("status", "已完成");
			}
		if(String.valueOf(list.get(j).get("status")).equals("0")){
			list.get(j).put("status", "已取消");
		}
		if(String.valueOf(list.get(j).get("status")).equals("30")){
			list.get(j).put("status", "已发货");
		}
		if(String.valueOf(list.get(j).get("status")).equals("20")){
			list.get(j).put("status", "待发货");
		}
		if(String.valueOf(list.get(j).get("status")).equals("11")){
			list.get(j).put("status", "待付款");
		}
		if(String.valueOf(list.get(j).get("status")).equals("1")){
			list.get(j).put("status", "已提交");
		}
		}
		JSONArray row = JSONArray.fromObject(list);
		JSONObject result = new JSONObject();
		result.put("rows", row);
		// result.put("total", total);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 获取用户订单信息
	 * 请求示例：http://localhost:8080/Ecshop_Server/appuser/getOrderListByUserIdAndStatus.do?user_id="user_id"&status="status"
	 *  参数可选择传递
	 * @param response
	 * @param status（40 已完成 ，0 已取消，30 已发货， 20 待发货，11 待付款，1 已提交）
	 * @param user_id
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/getOrderListByUserIdAndStatus")
	public String getOrderListByUserIdAndStatus(HttpServletResponse response ,	
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "user_id", required = false) String user_id
			) throws ParseException{
	
		PageData pd = new PageData();
		pd.put("status", status);
		pd.put("user_id", user_id);
		List<PageData> list = orderService.findOrderListByUserIdAndStatus(pd);
		for (int j = 0; j < list.size(); j++) {
			if(list.get(j).get("add_time")!=null&&list.get(j).get("add_time")!=""){
			String DateAddTime  = DateUtil.timeStampToDate(String.valueOf( list.get(j).get("add_time"))) ;
			list.get(j).put("add_time", DateAddTime);
			}
			if( list.get(j).get("pay_time")!=null&& list.get(j).get("pay_time")!=""){
				String DatePayTime  = DateUtil.timeStampToDate(String.valueOf( list.get(j).get("pay_time"))) ;
				list.get(j).put("pay_time", DatePayTime);
			}
		if(String.valueOf(list.get(j).get("status")).equals("40")){
				list.get(j).put("status", "已完成");
			}
		if(String.valueOf(list.get(j).get("status")).equals("0")){
			list.get(j).put("status", "已取消");
		}
		if(String.valueOf(list.get(j).get("status")).equals("30")){
			list.get(j).put("status", "已发货");
		}
		if(String.valueOf(list.get(j).get("status")).equals("20")){
			list.get(j).put("status", "待发货");
		}
		if(String.valueOf(list.get(j).get("status")).equals("11")){
			list.get(j).put("status", "待付款");
		}
		if(String.valueOf(list.get(j).get("status")).equals("1")){
			list.get(j).put("status", "已提交");
		}
		}
		JSONArray jsonarray = JSONArray.fromObject(list);
		JSONObject result = new JSONObject();
		result.put("list", jsonarray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 通过条件查询订单
	 * @param response
	 * @param buyer_name
	 * @param payment_name
	 * @param order_sn
	 * @param seller_name
	 * @param status
	 * @param startPrice
	 * @param endPrice
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param row
	 * @return
	 * @throws ParseException
	 */
	
	@RequestMapping(value="/getOrderListByTerm")
	public String getOrderListByTerm(HttpServletResponse response ,		@RequestParam(value = "buyer_name", required = false) String buyer_name,
			@RequestParam(value = "payment_name", required = false) String payment_name,
			@RequestParam(value = "order_sn", required = false) String order_sn,
			@RequestParam(value = "seller_name", required = false) String seller_name,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "startPrice", required = false) String startPrice,
			@RequestParam(value = "endPrice", required = false) String endPrice,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime ,
			@RequestParam (value = "page" ,required = false) String page,
			@RequestParam(value = "row" ,required =false) String row) throws ParseException{
		int startPrices = 0;
		int endPrices = 0;
		int startTimes = 0;
		int endTimes = 0;

		if (startPrice != null && startPrice != "") {
			startPrices = Integer.parseInt(startPrice);
		}
		if (endPrice != null && endPrice != "") {
			endPrices = Integer.parseInt(endPrice);

		}
	
		if (startTime != null && startTime != "") {
			startTimes = DateUtil.stringDateFormattimeStamp(startTime);
		}
		if (endTime != null && endTime != "") {
			endTimes =DateUtil.stringDateFormattimeStamp(endTime);
		}
		PageData pd = new PageData();
		pd.put("buyer_name", buyer_name);
		pd.put("payment_name", payment_name);
		pd.put("order_sn", order_sn);
		pd.put("seller_name", seller_name);
		pd.put("status", status);
		pd.put("startPrices", startPrices);
		pd.put("endPrices", endPrices);
		pd.put("startTimes", startTimes);
		pd.put("endTimes", endTimes);
		pd.put("page", page) ;
		pd.put("row", row) ;
		List<PageData> list = orderService.exportExcel(pd);
		for (int j = 0; j < list.size(); j++) {
			if(list.get(j).get("add_time")!=null&&list.get(j).get("add_time")!=""){
			String DateAddTime  = DateUtil.timeStampToDate(String.valueOf( list.get(j).get("add_time"))) ;
			list.get(j).put("add_time", DateAddTime);
			}
			if( list.get(j).get("pay_time")!=null&& list.get(j).get("pay_time")!=""){
				String DatePayTime  = DateUtil.timeStampToDate(String.valueOf( list.get(j).get("pay_time"))) ;
				list.get(j).put("pay_time", DatePayTime);
			}
		if(String.valueOf(list.get(j).get("status")).equals("40")){
				list.get(j).put("status", "已完成");
			}
		if(String.valueOf(list.get(j).get("status")).equals("0")){
			list.get(j).put("status", "已取消");
		}
		if(String.valueOf(list.get(j).get("status")).equals("30")){
			list.get(j).put("status", "已发货");
		}
		if(String.valueOf(list.get(j).get("status")).equals("20")){
			list.get(j).put("status", "待发货");
		}
		if(String.valueOf(list.get(j).get("status")).equals("11")){
			list.get(j).put("status", "待付款");
		}
		if(String.valueOf(list.get(j).get("status")).equals("1")){
			list.get(j).put("status", "已提交");
		}
		}
		JSONArray rows = JSONArray.fromObject(list);
		JSONObject result = new JSONObject();
		result.put("rows", rows);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
}
