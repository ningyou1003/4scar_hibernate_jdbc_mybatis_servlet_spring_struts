package com.zrcx.common;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * mybatis工具类
 * @author zhql
 */
public class MybatisUtil {
	private static SqlSessionFactory factory;
	static{
		try {
			//配置文件的路径（在src下）
			String resource = "mybatis-config.xml";
			//读取配置文件
			Reader reader = Resources.getResourceAsReader(resource);
			//创建SqlSessionFactoryBuilder对象
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			//解析配置，构建SqlSessionFactory对象
			factory = builder.build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static SqlSessionFactory getFactory(){
		return factory;
	}

}
