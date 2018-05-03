package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 数据库连接工具类
 * @author zhql
 */
public class DbconnUtil {
    public static final int SQLSERVER = 1;
    public static final int MYSQL = 2;
    public static final int ORACLE = 3;
    /**
     * 通过jdbc获取相应的数据库链接connection
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public static Connection getConnection(String ipport,String dbName,String username,String password,int type) 
			throws ClassNotFoundException, SQLException {

		String jdbcString = null;
		if (type == SQLSERVER) {
			jdbcString = "jdbc:jtds:sqlserver://" + ipport + ";databaseName=" + dbName;
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} else if (type == MYSQL) {
			jdbcString = "jdbc:mysql://" + ipport + "/" + dbName;
			Class.forName("org.gjt.mm.mysql.Driver");
		} else if (type == ORACLE) {
			jdbcString = "jdbc:oracle:thin:@" + ipport + ":" + dbName;
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
//		System.out.println("url: " + jdbcString);
//		System.out.println("username: " + username);
//		System.out.println("password: " + password);
		Connection connection = null;
		connection = DriverManager.getConnection(jdbcString, username, password);
		return connection;
	}
}
