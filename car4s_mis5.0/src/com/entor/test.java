package com.entor;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(test01("1234567"));

	}
//二、（1）
	public static String test01(String s){
		try{
			int i = Integer.parseInt(s);
			if (i - (int)i == 0  && i>0) {
				String st = i+"";
				return st.substring(4, 7);
			}else {
				return "不是正整数";
			}
		}catch (Exception e) {
			return "不是数字";
		}
	}

}
