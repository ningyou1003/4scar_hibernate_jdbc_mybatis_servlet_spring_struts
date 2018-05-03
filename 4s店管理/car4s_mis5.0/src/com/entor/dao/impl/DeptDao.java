package com.entor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IDeptDao;
import com.entor.entity.Dept;
import com.entor.entity.mapper.DeptMapper;

/**
 * 部门操作Dao
 * @author ZHQL
 */
@Repository
public class DeptDao extends BaseDao implements IDeptDao {
	// 列表信息查询
	@Override
	public List<Dept> select(Map<String, Object> param) {
		List<Dept> list = new ArrayList<Dept>();
		try {
			String sql = "select * from t_4s_dept where del_flag=1 ";
			sql += this.apdStr(param, "name", "", true);
			sql += this.apdStr(param, "charger", "", true);
			sql += this.apdStr(param, "contactTel", "", true);
			// 分页对象
			if (null != param && null != param.get("page")) {
				sql = this.getPageSql(sql, (Page) param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql, new DeptMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 新增
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public int add(Dept obj) {
		int result = 0;
		String sql = "insert into t_4s_dept "
				+ "(id, name, charger, contact_tel, create_date)"
				+ "values (seq_t_4s_dept.nextval, " + "'" + obj.getName()
				+ "','" + obj.getCharger() + "','" + obj.getContactTel()
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
		String sql = "update t_4s_dept set del_flag=0 where id=" + id;
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
	public int update(Dept obj) {
		int result = 0;
		try {
			String sql = "update t_4s_dept set id=id ";
			if (isApd(obj, "name", "")) {
				sql += ",name ='" + obj.getName() + "'";
			}
			if (isApd(obj, "charger", "")) {
				sql += ",charger='" + obj.getCharger() + "'";
			}
			if (isApd(obj, "contactTel", "")) {
				sql += ",contact_tel='" + obj.getContactTel() + "'";
			}
			sql += " where id= " + obj.getId();
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据ID查询
	 * @param list
	 * @return
	 */
	@Override
	public Dept getObjById(int id) {
		Dept obj = null;
		try {
			String sql = "select * from t_4s_dept where id=" + id;
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql, new DeptMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
