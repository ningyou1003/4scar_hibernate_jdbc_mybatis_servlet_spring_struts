package com.zrcx.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * hibernate工具类
 * @author Administrator
 *
 */
public class HibernateUtil {
	//会话工厂对象
	public static SessionFactory sessionFactory;
	//服务注册对象
	private static ServiceRegistry serviceRegistry; 
	//静态代码块，在类加载执行（仅执行一次）
	static{
		try {
			Configuration c = new Configuration();
			//加载配置文件,文件默认名hibernate.cfg.xml
			c.configure("hibernate.cfg.xml");
			//创建会话工厂
			//4.X加了新的对象
			//hibernate的任何配置和服务都必须在该对象注册后才能生效
			serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(c.getProperties()).buildServiceRegistry();
			//创建工厂
			sessionFactory = c.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Session getSession(){//打开会话
		return sessionFactory.openSession();
		
		
	}
	public static void close(){//关闭工厂
		sessionFactory.close();
	}
}
