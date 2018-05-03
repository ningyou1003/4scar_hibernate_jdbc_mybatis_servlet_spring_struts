package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
/**
 * 获取数据类型
 * @author zhql
 */
public class DataBaseType {
    
	public static String getPojoType(String columnType,int precision,int scale){
		if(columnType.equalsIgnoreCase("DATE")){
			return "Date";
		}else if(columnType.substring(0, 6).equalsIgnoreCase("NUMBER")){
			if(scale==0){//整数
				if(precision<6){
					return "Long";
				}else{
					return "Integer";
				}
			}else{
				if(precision<8){
					return "Float";
				}else{
					return "Double";
				}
			}
		}else if(columnType.substring(0, 7).equalsIgnoreCase("VARCHAR")){
			return "String";
		}
		return "";
	}
	//获取字段注释
	public static Map<String,String> getColumnComment(String tableName,Connection connection){
		
		 Map<String,String> map = new HashMap<String, String>();
		 try {
			String sql = " SELECT COLUMN_NAME,COMMENTS FROM USER_COL_COMMENTS WHERE TABLE_NAME= '" + tableName + "'";
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
			  map.put(rs.getString("COLUMN_NAME"), rs.getString("COMMENTS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
