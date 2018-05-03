package com.entor.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 * 结果集与字典实体的映射类
 * @author ZHQL
 */
public class DictMapper implements RowMapper<Dict> {
	@Override
	public Dict mapRow(ResultSet rs, int index) 
			throws SQLException {
		Dict u = new Dict();
		u.setId(rs.getLong("id"));
		u.setDictName(rs.getString("dict_name"));
		u.setKey(rs.getString("key"));
		u.setValue(rs.getString("value"));
		u.setUseFlag(rs.getInt("use_flag"));
		u.setOrderNo(rs.getInt("order_no"));
		u.setCreateDate(rs.getDate("create_date"));
		return u;
	}

}






