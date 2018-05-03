package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entor.entity.Dict;

/**
 * 字典实体映射类
 * 
 * @author ZHQL
 */
public class DictMapper implements RowMapper<Dict> {

	@Override
	public Dict mapRow(ResultSet rs, int index) throws SQLException {
		Dict obj = new Dict();
		obj.setId(rs.getInt("id"));
		obj.setDictName(rs.getString("dict_name"));
		obj.setKey(rs.getString("key"));
		obj.setValue(rs.getString("value"));
		obj.setUseFlag(rs.getInt("use_flag"));
		obj.setOrderNo(rs.getInt("order_no"));
		obj.setCreateDate(rs.getDate("create_date"));
		return obj;
	}

}
