package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entor.entity.Role;
/**
 * 角色实体映射类
 * @author ZHQL
 */
public class RoleMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int index) throws SQLException {
		Role obj = new Role();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setCreateDate(rs.getDate("create_date"));
		obj.setDelFlag(rs.getInt("del_flag"));
		return obj;
	}

}
