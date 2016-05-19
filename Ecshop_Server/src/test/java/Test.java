import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * TODO 类或接口说明
 * <p/> Test
 * 
 * @author <a href="mailto:Hoindbq@163.com">Holin Ding</a>
 * @version  Date: 2016年4月16日 上午11:03:39
 * @serial 1.0
 * @since 2016年4月16日 上午11:03:39
 */
public class Test {

	private static String string = "1" ;
	
	
	public static String formatDate(Date date){
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sf.format(date);
		
		
		return dateString ;
	}
	
	public static Date formatString(String string) throws ParseException{
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = sf.parse(string);
		
		return date ;
		
	}
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
	public static void dateFormatUniox(){
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		Long timestamp = Long.parseLong("1410318106")*1000;

		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));   

		System.out.println(date);
	}
	public static void getUnixDate() throws ParseException{
		
		//(simpleDateFormat.parse(simpleDateFormat.format(date))).getTime()/1000
		Date date =new Date();

		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String Date  = simpleDateFormat.format(date) ;
		
		long datetime = simpleDateFormat.parse(Date).getTime()/1000 ;
		int intTime = new Long(datetime).intValue();
		System.out.println(intTime);
		
	}
	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
			
	/*	byte[] b = new byte[1024*1024] ;
		b = string.getBytes("ISO-8859-1");
		
		byte[] b = new byte[20] ;
			System.out.println(binary(b, 10));
	
	}
	
	public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }*/ 
/*		
		Date date = new Date() ;
		String dateString = formatDate(date) ;
		System.out.println(dateString);
		//String string = "2016041613" ;
		Date time = formatString(dateString) ;
		//System.out.println(dateString);
		//int dateInt = Integer.parseInt(dateString);
		System.out.println(time);*/
	/*	Date date = new Date() ;
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd") ;
		String timeString  = formatTime.format(date);
		System.out.println(timeString);
		int a = getDiffYear("2016-04-16","2016-04-16") ;
		 System.out.println(a );*/
		getUnixDate();
	}	
		
}
