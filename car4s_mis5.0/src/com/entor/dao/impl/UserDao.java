package com.entor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.entor.common.DateUtil;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IUserDao;
import com.entor.entity.User;
import com.entor.entity.mapper.UserMapper;
/**
 * 用户数据访问
 * @author ZHQL
 */
@Repository
public class UserDao extends BaseDao implements IUserDao{
	private static Logger log = Logger.getLogger(UserDao.class);
	
	@Override
	public List<User> list(Map<String,Object> param) {
		log.info("----------执行用户列表查询Dao-----------");
		log.debug("查询条件:" + param);
		List<User> list = new ArrayList<User>();
		try {
			//拼接sql
			String sql = " select * from t_4s_user t where 1=1 ";
			sql+= this.apdStr(param,"name","",true);
			sql+= this.apdStr(param,"username","",false);
			sql+= this.apdStr(param,"password","",false);
			sql+= this.apdNum(param,"applyState","0");
			sql+= this.apdNum(param,"sex","0");
			sql+= this.apdNum(param,"loginFlag","0");
			if(param.get("page")!=null){
				Page p = (Page)param.get("page");
				sql = this.getPageSql(sql,p);
			}
			log.debug("sql:" + sql);
			list = jdbctmp.query(sql,new UserMapper());
			log.debug("查询用户数:" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("用户列表查询异常", e);
		}
		return list;
	}
	//新增用户
	@Override
	public int add(User u){
		int i = 0;
		String sql =" insert into t_4s_user(id, name, sex, birthday, username, password, login_flag, create_date) values   " +
				"(seq_t_user.nextval,'"+ u.getName() 
				+"', "+ u.getSex() 
				+", date '"+ DateUtil.toStr(u.getBirthday()) 
				+"', '"+ u.getUsername()
				+"', '"+ u.getPassword() 
				+"', "+ u.getLoginFlag() +",sysdate)";
		System.out.println("sql:" + sql);
	    i = jdbctmp.update(sql);
		return i;		
	}
	//更新
	@Override
	public int update(User u){
		int i = 0;
		if(null == u){
			return i;
		}
		
		String sql = "update t_4s_user t set t.id=t.id ";
		if(u.getName()!=null && !u.getName().equals("")){
			sql+= ",t.name='" + u.getName() + "'";
		}
		if(u.getUsername()!=null && !u.getUsername().equals("")){
			sql+= ",t.username='" + u.getUsername() + "'";
		}
		if(u.getPassword()!=null && !u.getPassword().equals("")){
			sql+= ",t.password='" + u.getPassword() + "'";
		}
		if(u.getNewpassword()!=null && !u.getNewpassword().equals("")){
			sql+= ",t.password='" + u.getNewpassword() + "'";
		}
		if(u.getSex()!=0){
			sql+= ",t.sex=" + u.getSex();
		}
		if(u.getLoginFlag()!=0){
			sql+= ",t.login_flag=" + u.getLoginFlag();
		}
		if(u.getBirthday()!=null){
			sql+= ",t.birthday= date '" 
		        + DateUtil.toStr(u.getBirthday())+ "'";
		}
		sql += " where t.id=" + u.getId();
		System.out.println("sql:" + sql);
	    i = jdbctmp.update(sql);
		return i;		
	}
	//删除
	@Override
	public int delete(long id){
		int i = 0;
		String sql =" delete from t_4s_user where id=" + id;
		System.out.println("sql:" + sql);
	    i = jdbctmp.update(sql);
		return i;		
	}
	//根据ID查询
	@Override
	public User getObjById(long id) {
		User u = null;
		try {
			String sql = "select * from t_4s_user t where id=" + id;
			System.out.println("sql:" + sql);
		    u = jdbctmp.queryForObject(sql,new UserMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
}







