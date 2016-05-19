package com.ecshop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 
 * TODO 订单号生成工具
 * <p/>
 * 
 * @author <a href="1048688506@qq.com">Yang</a>
 * @version  Date: 2016年5月3日 上午10:23:39
 * @serial 1.0
 * @since 2016年5月3日 上午10:23:39
 */
public class OrderSnTool {

	public static String BuildOrderSn() throws ParseException{
		StringBuffer stringBuffer = new StringBuffer("16");
		stringBuffer.append(BuildRandomSn()).append(BuildDateSn()) ;
		String orderSn = stringBuffer.toString();
		return orderSn ;
	}
	
	public static String BuildRandomSn(){
			int max=999;
	        int min=100;
	        Random random = new Random();
	        int s = random.nextInt(max)%(max-min+1) + min;
		return String.valueOf(s) ;
	}
	
	public static  String BuildDateSn() throws ParseException{
		String dateSn = unixDateString().substring(unixDateString().length()-5, unixDateString().length());
		return dateSn ;
	}
	
	public static String unixDateString() throws ParseException{
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String Date  = simpleDateFormat.format(simpleDateFormat.parse(DateUtil.getTime())) ;
		
		long datetime = simpleDateFormat.parse(Date).getTime()/1000 ;
		
		int intTime = new Long(datetime).intValue();
		
		return String.valueOf(intTime);
	}
	
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(BuildOrderSn());
	}
}
