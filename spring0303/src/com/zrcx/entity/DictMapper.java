package com.zrcx.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;



public class DictMapper implements RowMapper<Dict> {

	@Override
	public Dict mapRow(ResultSet rs, int index) throws SQLException {
		Dict u = new Dict();
		u.setId(rs.getLong("id"));
		u.setCkey(rs.getString("ckey"));
		u.setCvalue(rs.getString("cvalue"));
		u.setDictName(rs.getString("dict_name"));
		u.setUseFlag(rs.getInt("use_flag"));
		u.setOrderNo(rs.getInt("order_no"));
		u.setCreateDate(rs.getDate("create_date"));
		return u;
	}



}
