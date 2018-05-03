package com.zrcx;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.zrcx.dao.DictDao;

/**
 * spring整合jdbc测试
 * @author Administrator
 *
 */
public class Test01 {
	public static void main(String[] args) {
		AbstractApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext01.xml");
		DictDao dao = (DictDao) ac.getBean("dictDao");
		dao.list();
		dao.update();
		ac.close();
	}
}















