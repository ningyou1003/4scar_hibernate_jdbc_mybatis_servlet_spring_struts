package com.entor.common;

import java.util.Arrays;

public class StringUtil {
	public static String charSort(String s){
		//转换成大写，再转成数组
		char[] c = s.toUpperCase().toCharArray();
		Arrays.sort(c);//排序		
		return new String(c);
	}

}
