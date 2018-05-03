package com.zrcx;


import java.sql.ResultSet;

/**
 * 查询所有学生(jdbc连接Oracle)
 * @author zhql
 */
public class Test01 {

	public static void main(String[] args) {
		ResultSet rs = null;
		try {
			//sql拼接
			String sql = "SELECT *from t_student ";
			//执行查询sql语句
			rs = DBUtil.query(sql);
			while(rs.next()){
				System.out.println(rs.getString("name")
						+ " -- " + rs.getString("sex")+ " -- " + rs.getDate("birthday"));
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
				DBUtil.close(rs);
		}
	}

}
