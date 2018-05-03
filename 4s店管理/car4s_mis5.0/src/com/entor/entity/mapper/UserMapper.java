package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entor.entity.User;
/**
 *结果集与用户实体的映射类
 * @author ZHQL
 */
public class UserMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int index) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setDeptId(rs.getLong("dept_id"));
		u.setSex(rs.getInt("sex"));
		u.setBirthday(rs.getDate("birthday"));
		u.setEntryDate(rs.getDate("entry_date"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		u.setLoginFlag(rs.getInt("login_flag"));
		u.setApplyFlag(rs.getInt("apply_state"));
		u.setRoleId(rs.getLong("role_id"));
		u.setCreateDate(rs.getDate("create_date"));
		return u;
	}

}






