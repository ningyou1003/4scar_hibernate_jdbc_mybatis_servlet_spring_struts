package 练习;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test05 {

	/**
	 * 结果集操作演示
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		String url = "jdbc:mysql://192.168.1.23:3306/ningyou";
		//加载驱动     //反射
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, "ning", "ning");
		String sql = "select * from t_user";
		//创建语句对象
		Statement sm = conn.createStatement();
		//执行sql,返回查询结果集	
		ResultSet rs = sm.executeQuery(sql);
		//循环结果集（rs.next()游标下移一行，如果还有数据返回true，否则返回false）
		while(rs.next()){
//			System.out.println(rs.getString("name"));
//			System.out.println(rs.getInt("sex"));
//			System.out.println(rs.getDate("birthday"));
//			根据列编号（从1开始）获取值
			System.out.println(rs.getString(2));
		}
		//关闭（后打开前关闭）
		rs.close();
		sm.close();
		conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
