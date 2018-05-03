package 练习;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Test08 {

	/**
	 *结果集元数据类演示
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://192.168.1.23:3306/ningyou";
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url,"ning","ning");
			String sql = "select * from t_score";
			//创建语句对象
			Statement sm = conn.createStatement();
			//执行sql，返回查询结果集
			ResultSet rs = sm.executeQuery(sql);
			//结果集元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("列数:" + rsmd.getColumnCount());
			System.out.println("-------");
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(i+"***");
				System.out.println("列名:" + rsmd.getColumnName(i) 
			            + " 类型:" + rsmd.getColumnTypeName(i)
			            + " 长度:" + rsmd.getPrecision(i)
			            + " 小数点位数:" + rsmd.getScale(i)
			          );
			}			
			//关闭(后打开前关闭)
			rs.close();
			sm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
