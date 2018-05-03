package 练习;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test06 {

	/**
	 *PrepareStatement操作演示
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psm = null;
		try {
			String url = "jdbc:mysql://192.168.1.23:3306/ningyou";
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,"ning","ning");
			//插入语句
			String sql1 = "insert into t_user(name,sex,birthday,username," +
					" password,login_flag,create_date) " +
					" values (?,?,?,?,?,?,now())";
			psm = conn.prepareStatement(sql1);//预编译功能，相同操作批量数据效率较高
			//参数赋值
			psm.setString(1, "测试用户test6");
			psm.setInt(2, 1);
			psm.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			psm.setString(4, "a");
			psm.setString(5, "1");
			psm.setInt(6, 1);
			int i = psm.executeUpdate();
			
			System.out.println("影响行数:" + i);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(psm!=null){
					
				}
				if(conn!=null){
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
