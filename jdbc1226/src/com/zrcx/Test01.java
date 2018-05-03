package com.zrcx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 第一个jdbc程序
 * @author zhql
 */
public class Test01 {

	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/db1620";
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,"u1620","u1620");
			String sql =  "select * from t_user ";
			//创建语句对象
			Statement sm = conn.createStatement();
			//执行sql,返回查询结果集
			ResultSet rs = sm.executeQuery(sql);
			//循环结果集
			while(rs.next()){
				System.out.println(rs.getString("name") + " - " + rs.getDate("birthday"));
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
