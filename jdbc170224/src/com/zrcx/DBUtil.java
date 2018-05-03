package com.zrcx;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库操作工具类
 * @author Administrator
 *
 */
public class DBUtil {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static{
		try {
			FileInputStream fis = new FileInputStream("config/db.properties");
			//创建属性文件对象
			Properties pro = new Properties();
			pro.load(fis);
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取数据库连接的方法
	public static Connection getConn(){
		System.out.println("获取新的数据库连接....");
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//执行更新语句
	public static int update(String sql){
		Connection conn = null;
		Statement sm = null;
		int i = 0;
		try {
			conn = getConn();
			sm = conn.createStatement();
			System.out.println("update-sql:"+sql);
			i = sm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(sm!=null){
					sm.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	//执行查询语句
	public static ResultSet query(String sql){
		Connection conn = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			sm = conn.createStatement();
			System.out.println("query-sql:"+sql);
			rs = sm.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//执行查询语句后关闭连接
	public static void close(ResultSet rs){
		Connection conn = null;
		Statement sm = null;
		try {
			if(rs != null){
				sm = rs.getStatement();
				rs.close();
			}
			if(sm != null){
				conn = sm.getConnection();
				sm.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
