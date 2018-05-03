package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entor.entity.Fittings;
/**
 * 配件实体映射类
 * @author ZHQL
 */
public class FittingsMapper implements RowMapper<Fittings> {

	@Override
	public Fittings mapRow(ResultSet rs, int index) throws SQLException {
		Fittings obj = new Fittings();
		obj.setId(rs.getLong("id"));
		obj.setName(rs.getString("name"));
		obj.setUnit(rs.getInt("unit"));
		obj.setPrice(rs.getFloat("price"));
		obj.setBrand(rs.getString("brand"));
		obj.setType(rs.getString("type"));
		obj.setCreateDate(rs.getDate("create_date"));
		obj.setRemark(rs.getString("remark"));
		obj.setDelFlag(rs.getInt("del_flag"));
		return obj;
	}
}
