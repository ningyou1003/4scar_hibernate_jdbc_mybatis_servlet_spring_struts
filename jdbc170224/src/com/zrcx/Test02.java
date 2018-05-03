package com.zrcx;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * jdbc调用存储过程
 * @author zhql
 */
public class Test02 {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = DBUtil.getConn();
			//存入参数传出值
			cs = conn.prepareCall("{call proc_test03(?,?)}");
			cs.setLong(1, 2);//设置参数，1是位置，2是参数
			//注册传出参数的类型
			cs.registerOutParameter(2, Types.INTEGER);
			boolean is = cs.execute();
			int total = cs.getInt(2);
			System.out.println("返回的学生人数："+total);
			System.out.println("执行完毕...");
		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				conn.close();
				cs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
