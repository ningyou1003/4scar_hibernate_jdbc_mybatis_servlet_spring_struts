package 练习;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test10 {

	/**
	 *批量操作演示
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
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
			for (int i = 1; i <= 1000; i++) {
				//参数赋值
				psm.setString(1, "测试用户"+i);
				psm.setInt(2, 1);
				psm.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				psm.setString(4, "a");
				psm.setString(5, "1");
				psm.setInt(6, 1);
				psm.addBatch();//加到批量      将一组参数添加到此 PreparedStatement 对象的批处理命令中
				if(i%100==0){//一百条作为一批提交
					psm.executeBatch();//批量执行
					psm.clearBatch();//清空      立即清除当前参数值。
				}
			}
			System.out.println("批量入库完成");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(psm != null){
					psm.close();
				}
				if(conn!=null){
					conn.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("用时:" + (System.currentTimeMillis()-start)/1000 +"秒");
	}

}






