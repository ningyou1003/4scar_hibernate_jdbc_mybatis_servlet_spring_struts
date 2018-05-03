package com.zrcx;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import oracle.jdbc.OracleTypes;

/**
 * jdbc调用存储过程
 * @author zhql
 */
public class Test03 {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = DBUtil.getConn();
			//存入参数传出值
			cs = conn.prepareCall("{call pkg_test01.proc_test2(?,?)}");
			cs.setLong(1, 2);//设置参数
			//注册传出参数的类型
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(2);
			while(rs.next()){
				System.out.println("姓名："+rs.getString("name")+
						" 出生日期："+rs.getDate("birthday"));
			}
			System.out.println("执行完毕");
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
