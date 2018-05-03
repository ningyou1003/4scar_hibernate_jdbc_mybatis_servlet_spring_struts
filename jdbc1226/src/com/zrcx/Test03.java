package com.zrcx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc实现增删改操作
 * @author zhql
 */
public class Test03 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement sm = null;
		try {
			String url = "jdbc:mysql://localhost:3306/db1620";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"u1620", "u1620");
			//插入语句
			String sql1 = "insert into t_user(name,sex,birthday,username," +
					" password,login_flag,create_date) " +
					" values ('测试用户',1,'1994-02-01','abc','123',2,now())";
			//更新语句
			String sql2 = "update t_user t " +
					" set t.name='李向阳', t.birthday = date_add(t.birthday,interval 1 day)" +
					" where id=12";
			//删除语句(mysql删除语句不能用别名)
			String sql3 = "delete from t_user where id=14";
			//创建表
			String sql4 = "create table t_test(id int(10))";
			//删除表
			String sql5 = "drop table t_test";
			sm  = conn.createStatement();
			System.out.println("sql:" + sql5);
			int i = sm.executeUpdate(sql5);
			System.out.println("影响行数:" + i);
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






