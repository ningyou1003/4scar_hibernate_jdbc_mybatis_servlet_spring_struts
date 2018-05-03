package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.FitStock;
/**
 * 配件库存实体映射类
 * @author ZHQL
 */
public class FitStockMapper implements RowMapper<FitStock> {

	@Override
	public FitStock mapRow(ResultSet rs, int index) throws SQLException {
		FitStock fitstock = new FitStock();
		fitstock.setId(rs.getInt("id"));
		fitstock.setBrand(rs.getString("brand"));
		fitstock.setType(rs.getString("type"));
		fitstock.setFitName(rs.getString("fname"));
		fitstock.setCount(rs.getLong("count"));
		fitstock.setRemark(rs.getString("remark"));
		return fitstock;
	}

}
