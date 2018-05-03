package 练习;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02 {

	/**
	 * 查询每个班的男女人数（班级名称，性别，人数）
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://localhost:3306/ningyou";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"ning","ning");
			//sql拼接
			String sql = "SELECT a.`name`,IF(b.`sex`=1,'男','女') sex,COUNT(*) c " +
					" FROM t_class a,t_student b " +
					" WHERE a.`id`=b.`class_id` GROUP BY a.`name`,b.`sex`";
			sm = conn.createStatement();
			//执行查询sql语句
			rs = sm.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("name")
						+ " -- " + rs.getString("sex")+ " -- " +rs.getInt("c"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
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
