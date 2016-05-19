package com.ecshop.utils;

	import java.security.MessageDigest;
/**
 * 
 * TODO MD5加密工具类
 * <p/> 用于为系统给定的字符转加密
 * 
 * @author Yang
 * @serial 1.0
 * @since 2016年4月13日 下午2:46:49
 */
	public class MD5 {
		/**
		 * 
		 * @param str 需要加密的字符串
		 * @return 加密后的字符串
		 */
		public static String md5(String str) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte b[] = md.digest();

				int i;

				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				str = buf.toString();
			} catch (Exception e) {
				e.printStackTrace();

			}
			return str;
		}
		
	}


