package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entor.entity.Dept;
/**
 * 部门实体映射类
 * @author ZHQL
 */
public class DeptMapper implements RowMapper<Dept> {

	@Override
	public Dept mapRow(ResultSet rs, int index) throws SQLException {
		Dept obj = new Dept();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setCharger(rs.getString("charger"));
		obj.setContactTel(rs.getString("contact_tel"));
		obj.setCreateDate(rs.getDate("create_date"));
		obj.setDelFlag(rs.getInt("del_flag"));
		return obj;
	}
}
