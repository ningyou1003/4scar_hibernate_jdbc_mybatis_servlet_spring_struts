package com.zrcx;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 批量操作演示
 * @author zhql
 */
public class Test10 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
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
			for (int i = 1; i <= 1000; i++) {
				//参数赋值
				psm.setString(1, "测试用户"+i);
				psm.setInt(2, 1);
				psm.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				psm.setString(4, "a");
				psm.setString(5, "1");
				psm.setInt(6, 1);
				psm.addBatch();//加到批量
				if(i%100==0){//一百条作为一批提交
					psm.executeBatch();//批量执行
					psm.clearBatch();//清空
				}
			}
			System.out.println("批量入库完成");
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
		System.out.println("用时:" + (System.currentTimeMillis()-start)/1000 +"秒");
	}

}






