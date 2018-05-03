package com.entor.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 日期工具类
 * @author ZHQL
 */
public class DateUtil {
	public static String toStr(Date date){
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	public static String toStr2(Date date){
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	/**
	 * 获得日期字符串
	 * @return
	 */
	public static String datestr(){
		SimpleDateFormat sdf = 
		new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Random r = new Random();
		return sdf.format(new Date())+r.nextInt(10);		
	}
	
	public static Date toDate(String s){
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

}
