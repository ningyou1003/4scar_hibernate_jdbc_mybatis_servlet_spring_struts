package com.zrcx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc事务操作演示
 * @author zhql
 */
public class Test09 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement sm = null;
		try {
			String url = "jdbc:mysql://localhost:3306/db1620";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"u1620", "u1620");
			//把事务提交设为手动
			conn.setAutoCommit(false);
			//插入语句
			String sql1 = "insert into t_user(name,sex,birthday,username," +
					" password,login_flag,create_date) " +
					" values ('测试用户2',1,'1994-02-01','abc','123',2,now())";
			//更新语句
			String sql2 = "update t_user t " +
					" set t.birthday = date_add(t.birthday,interval 1 day)" +
					" where id=12";
			sm  = conn.createStatement();
			System.out.println("sql:" + sql1);
			sm.executeUpdate(sql1);
			System.out.println("sql:" + sql2);
			sm.executeUpdate(sql2);
			conn.commit();//提交事务
		}catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//回滚事务
			e.printStackTrace();
		}finally{
			try {
				if(sm != null){
					sm.close();
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






