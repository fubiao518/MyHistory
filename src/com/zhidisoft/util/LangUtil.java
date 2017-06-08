package com.zhidisoft.util;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串到包装类对象之间的转换
 * @author 卢健良
 *
 */
public class LangUtil {

	public static Integer toInteger(String str){
		return StringUtils.isBlank(str) ? null : Integer.valueOf(str);
	}
	
	public static Long toLong(String str){
		return StringUtils.isBlank(str) ? null : Long.valueOf(str);
	}
	
	public static Short toShort(String str){
		return StringUtils.isBlank(str) ? null : Short.valueOf(str);
	}
	
	public static Byte toByte(String str){
		return StringUtils.isBlank(str) ? null : Byte.valueOf(str);
	}
	
	public static Double toDouble(String str){
		return StringUtils.isBlank(str) ? null : Double.valueOf(str);
	}
	
	public static Float toFloat(String str){
		return StringUtils.isBlank(str) ? null : Float.valueOf(str);
	}
}
