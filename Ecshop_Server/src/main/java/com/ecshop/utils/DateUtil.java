package com.ecshop.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * TODO 类或接口说明
 * <p/> 自定义日期转换工具
 * @author <a href="1048688506@qq.com">Yang</a>
 * @version  Date: 2016年4月16日 下午4:13:20
 * @serial 1.0
 * @since 2016年4月16日 下午4:13:20
 */
public class DateUtil {
	
	private final static SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat formatDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat formatDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat formatTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	
	
	
	/**
	 * 获取YYYY格式时间
	 * 
	 * 返回当前时间，精确到年（例2016）
	 * @return
	 */
	public static String getYear() {
		return formatYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * 返回当前时间，精确到日
	 * 例2016-06-09
	 * @return
	 */
	public static String getDay() {
		return formatDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * 返回当前时间，精确到日
	 * 例：20160609 
	 * @return
	 */
	public static String getDays(){
		return formatDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 返回当前时间精确到秒
	 * 例：2016-06-09 15：22：49
	 * @return 
	 */
	public static String getTime() {
		return formatTime.format(new Date());
	}

	/**
	* @Title  日期比较
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param  firstTime
	* @param  secondTime
	* @return 比较的结果  
	* @throws
	* @author Yang
	*/
	public static boolean compareDate(String firTime, String secTime) {
		if(fomatDate(firTime)==null||fomatDate(secTime)==null){
			return false;
		}
		return fomatDate(firTime).getTime() >=fomatDate(secTime).getTime();
	}

	/**
	 * 对当前的日期格式化
	 * @param date (日期字符串格式)
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @param date (日期字符串格式)
	 * @Exception  抛出异常则说明传入的日期格式与要求日期格式不匹配
	 * @return
	 */
	public static boolean isValidDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * <li>功能描述：时间相减得到年份
	 * @param startTime
	 * @param endTime
	 * 传入的参数格式应该是工具可以接受的如 ：beginDateStr=2016-06-09，endDateStr=2017-06-09
	 * @exception      throw java.text.ParseException或者NullPointerException，就说明格式不对
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((format.parse(endTime).getTime()-format.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
					e.printStackTrace();
					return 0 ;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr  起始时间
     * @param endDateStr     终止时间
     *    传入的参数格式应该是工具可以接受的如 ：beginDateStr=2016-06-09，endDateStr=2017-06-09
     * @return long                 时间间隔("相隔的天数="+day)
     * @author Yang       
     * @exception      throw java.text.ParseException或者NullPointerException，就说明格式不对
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * <li> days>0 N天后的日期 
     * <li> days>0 N天前的日期
     * @param days 时间间隔（N天）
     * @return 返回N天后的详细时间
     * 例 ：2016-04-23 16:41:42
     */
    public static String getAfterDayDate(String days) {
    
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包（日历实例）
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义输出格式
        String dateString = dateFormat.format(date);
        return dateString;
    }
    
    /**
     * 得到n天之后是日期
     * @param days 时间间隔（N天）
     *  <li>days>0 N天后的日期 
     * <li> days>0 N天前的日期
     * @return 返回N天后为周几
     * 例：星期六
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E");//定义输出格式
        String dateString = dateFormat.format(date);
        return dateString;
    }
    
    /**
     * 将UNIX时间格式转换为北京时间
     * @param timestampString
     * @param formats 字符串类型时间
     * @return
     */
    public static String timeStampToDate(String timestampString){    
    	  Long timestamp = Long.parseLong(timestampString)*1000;    
    	  String formats = "yyyy-MM-dd HH:mm:ss" ;
    	  String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));    
    	  return date;    
    } 
    /**
     * 将date（yyyy-MM-dd HH:mm:ss）格式的串转换为int类型
     * @param dateTime
     * @return
     * @throws ParseException
     */
public static int stringDateFormattimeStamp(String dateTime) throws ParseException{
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	String Date  = simpleDateFormat.format(simpleDateFormat.parse(dateTime)) ;
	
	long datetime = simpleDateFormat.parse(Date).getTime()/1000 ;
	
	int intTime = new Long(datetime).intValue();
	
	return intTime ;
	
}



    
    public static void main(String[] args) throws ParseException {
    	
    	System.out.println(stringDateFormattimeStamp("2016-03-25"));
    }

}
