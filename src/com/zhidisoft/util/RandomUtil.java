package com.zhidisoft.util;

import java.util.UUID;

/**
 * 随机工具类
 * @author 卢健良
 *
 */
public class RandomUtil {
	/**
	 * 生成UUID
	 * @return -返回去除"-"后的32位字符串的大写UUID值
	 */
	public static String randomUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 生成某一个范围内的随机数
	 * @param min 
	 * @param max
	 * @return
	 */
	public static int randomInt(int min,int max){
		return min + (int)(Math.random()*max);
	}
	
	public static int randomInt(int max){
		return randomInt(0,max);
	}

}
