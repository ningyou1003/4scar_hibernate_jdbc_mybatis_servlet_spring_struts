package com.zrcx;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * PrepareStatement操作演示
 * @author zhql
 */
public class Test06 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psm = null;
		try {
			String url = "jdbc:mysql://localhost:3306/db1620";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"u1620", "u1620");
			//插入语句
			String sql1 = "insert into t_user(name,sex,birthday,username," +
					" password,login_flag,create_date) " +
					" values (?,?,?,?,?,?,now())";
			psm  = conn.prepareStatement(sql1);
			//参数赋值
			psm.setString(1, "测试用户");
			psm.setInt(2, 1);
			psm.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			psm.setString(4, "a");
			psm.setString(5, "1");
			psm.setInt(6, 1);
			int i = psm.executeUpdate();
			
			System.out.println("影响行数:" + i);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(psm != null){
					psm.close();
				}
				if(conn!=null){
					conn.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}






