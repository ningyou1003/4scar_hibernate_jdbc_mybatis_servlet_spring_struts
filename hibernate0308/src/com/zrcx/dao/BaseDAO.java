package com.zrcx.dao;

import com.zrcx.common.HibernateUtil;
import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseDAO implements IBaseDAO {
	
	public Session getSession() {
		return HibernateUtil.getSession();
	}
	
}