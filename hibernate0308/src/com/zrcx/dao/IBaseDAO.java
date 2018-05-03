package com.zrcx.dao;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseDAO {
	public Session getSession();
}