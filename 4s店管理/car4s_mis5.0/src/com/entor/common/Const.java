package com.entor.common;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 常量类
 * @author ZHQL
 */
public class Const {
	public static String FILE_SAVE_PATH;

	public static void load() {
		try {
			//在路径下查找文件的
			String path = Const.class.getResource("/system.properties").getPath();
			FileInputStream fis = new FileInputStream(path.substring(1));
			Properties pro = new Properties();
			// 加载数据
			pro.load(fis);
			FILE_SAVE_PATH = pro.getProperty("FILE_SAVE_PATH");
			System.out.println("加载系统属性文件...");
			fis.close();// 关闭
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
