package com.zrcx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * execute方法演示(了解)
 * @author zhql
 */
public class Test04 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement sm = null;
		try {
			String url = "jdbc:mysql://localhost:3306/db1620";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"u1620", "u1620");
			//查询语句
			String sql1 = "select * from t_user ";
			//更新语句
			String sql2 = "update t_user t " +
					" set t.birthday = date_add(t.birthday,interval 1 day)" +
					" where id=12";
			
			System.out.println("sql:" + sql2);
			sm = conn.createStatement();
			boolean is  = sm.execute(sql2);
			if(is){//查询语句
				ResultSet rs = sm.getResultSet();
				while(rs.next()){
					System.out.println(rs.getString("name"));
				}
			}else{//更新语句
				int i = sm.getUpdateCount();
				System.out.println("影响行数:" + i);
			}			
		}catch (Exception e) {
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






