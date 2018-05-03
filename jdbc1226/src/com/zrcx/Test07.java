package com.zrcx;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 获取数据库元数据演示
 * @author zhql
 */
public class Test07 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement sm = null;
		try {
			String url = "jdbc:mysql://localhost:3306/db1620";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"u1620", "u1620");
			//数据库元数据对象
			DatabaseMetaData dmd = conn.getMetaData();
			System.out.println(dmd.getDatabaseProductName());
			System.out.println(dmd.getDatabaseProductVersion());
			System.out.println(dmd.getDriverName());
			System.out.println(dmd.getURL());
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}






