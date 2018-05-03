package com.zrcx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zrcx.entity.User;
@Repository
public class UserDao extends BaseDao implements IUserDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<User> list(String username,String password){
		String hql = " from User where " +
				" username='"+username+"' and password='"+password+"'";
		List<User> list = this.getsSession().createQuery(hql).list();
		return list;
	}
}






















