package com.zrcx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/**
 * mysql分页管理
 * @author zhql
 */
public class Test11 {

	public static void main(String[] args) {
		try {
			int pageSize = 10;
			String url = "jdbc:mysql://localhost:3306/db1620";
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,"u1620","u1620");
			String sql =  "select * from t_user t where id > 15";
			//创建语句对象
			Statement sm = conn.createStatement();
			//执行sql,返回查询结果集
			ResultSet rs = sm.executeQuery("select count(*) from ("+sql+") a");
			rs.next();//游标下移
			int count = rs.getInt(1);
			System.out.println("总页数:" + ((count/pageSize) + ((count%pageSize!=0)?1:0)));
			Scanner s = new java.util.Scanner(System.in);
			
			while(true){
				System.out.println("请输入你要查询的页码(小于1就退出系统):");
				int page = s.nextInt();
				if(page <1){
					System.out.println("退出系统");
					break;
				}
				String temp = sql + " limit "+(pageSize*(page-1)) + "," + pageSize;
				System.out.println("sql: " + temp);
				rs = sm.executeQuery(temp);
				System.out.println("当前显示的是第" + page + "页:");
				while(rs.next()){
					System.out.println("id:" + rs.getInt("id") + "  姓名:" + rs.getString("name"));
				}
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
