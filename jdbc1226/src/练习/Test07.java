package 练习;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Test07 {

	/**
	 *获取数据库元数据演示
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement sm = null;
		try {
			String url = "jdbc:mysql://192.168.1.23:3306/ningyou";
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,"ning","ning");
			//数据库元数据对象
			DatabaseMetaData dmd = conn.getMetaData();//数据库的整体综合信息。
			System.out.println(dmd.getDatabaseProductName()); // 获取此数据库产品的名称。
			System.out.println(dmd.getDatabaseMajorVersion());// 获取此数据库产品的版本号。
			System.out.println(dmd.getDriverName());//获取此 JDBC 驱动程序的次版本号。
			System.out.println(dmd.getURL());// 获取此 DBMS 的 URL。
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null){
					conn.close();
				}
					
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
