package com.entor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entor.dao.BaseDao;
import com.entor.dao.IRoleDao;
import com.entor.entity.Role;
import com.entor.entity.mapper.RoleMapper;

/**
 * 角色操作Dao
 * @author ZHQL
 */
@Transactional
@Repository
public class RoleDao extends BaseDao implements IRoleDao {

	// 列表信息查询
	@Override
	public List<Role> list(Map<String, Object> param) {
		List<Role> list = new ArrayList<Role>();
		try {
			String sql = "select * from t_4s_role where del_flag=1 ";
			sql += this.apdStr(param, "name", "", true);
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql, new RoleMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 新增
	 * @param user
	 * @return
	 */
	@Override
	public int add(Role obj) {
		int result = 0;
		String sql = "insert into t_4s_role " + "(id, name, create_date)"
				+ "values (seq_t_4s_role.nextval,'" + obj.getName()
				+ "',sysdate)";
		System.out.println("sql:" + sql);
		result = jdbctmp.update(sql);
		return result;
	}

	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	@Override
	public int delete(int id) {
		int result = 0;
		String sql = "delete from t_4s_role where id=" + id;
		System.out.println("sql:" + sql);
		result = jdbctmp.update(sql);
		return result;
	}

	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	@Override
	public int update(Role obj) {
		int result = 0;
		String sql = "update t_4s_role set id=id ";
		if (isApd(obj, "name", "")) {
			sql += ",name ='" + obj.getName() + "'";
		}
		sql += " where  id = " + obj.getId();
		System.out.println("sql:" + sql);
		result = jdbctmp.update(sql);
		return result;
	}

	/**
	 * 根据ID查询
	 * @param list
	 * @return
	 */
	@Override
	public Role getObjById(int id) {
		Role obj = null;
		try {
			String sql = "select * from t_4s_role where id=" + id;
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql, new RoleMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
