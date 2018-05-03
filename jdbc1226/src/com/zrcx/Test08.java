package com.zrcx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
/**
 * 结果集元数据类演示
 * @author zhql
 */
public class Test08 {

	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/db1620";
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,"u1620","u1620");
			String sql =  "select * from t_score";
			//创建语句对象
			Statement sm = conn.createStatement();
			//执行sql,返回查询结果集
			ResultSet rs = sm.executeQuery(sql);
			//结果集元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("列数:" + rsmd.getColumnCount());
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			   System.out.println("列名:" + rsmd.getColumnName(i) 
					            + " 类型:" + rsmd.getColumnTypeName(i)
					            + " 长度:" + rsmd.getPrecision(i)
					            + " 小数点位数:" + rsmd.getScale(i)
					          );
			}			
			//关闭(后打开前关闭)
			rs.close();
			sm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
