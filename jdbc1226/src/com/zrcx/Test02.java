package com.zrcx;

import java.sql.ResultSet;

/**
 * 查询每个班的男女人数(班级名称，性别，人数)
 * @author zhql
 */
public class Test02 {

	public static void main(String[] args) {
		ResultSet rs = null;
		try {
			//sql拼接
			String sql = "SELECT a.name,IF(b.sex=1,'男','女') sex,COUNT(*) c" +
					" FROM t_class a,t_student b WHERE a.id=b.class_id " +
					" GROUP BY a.name,b.sex";
			//执行查询sql语句
			rs = DBUtil.query(sql);
			while(rs.next()){
				System.out.println(rs.getString("name")
						+ " -- " + rs.getString("sex")+ " -- " + rs.getInt("c"));
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
				DBUtil.close(rs);
		}
	}

}
