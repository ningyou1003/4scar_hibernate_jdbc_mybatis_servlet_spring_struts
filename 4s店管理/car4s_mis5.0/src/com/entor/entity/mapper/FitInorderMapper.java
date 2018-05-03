package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.FitInorder;
/**
 * 配件进货单实体映射类
 * @author ZHQL
 */
public class FitInorderMapper implements RowMapper<FitInorder> {

	@Override
	public FitInorder mapRow(ResultSet rs, int index) throws SQLException {
		FitInorder fitIn = new FitInorder();
		fitIn.setId(rs.getInt("id"));
		fitIn.setBrand(rs.getString("brand"));
		fitIn.setFitName(rs.getString("fname"));
		fitIn.setFittingsId(rs.getInt("fittings_Id"));
		fitIn.setSupplierId(rs.getInt("supplier_Id"));
		fitIn.setSupName(rs.getString("sname"));
		fitIn.setInPrice(rs.getFloat("in_Price"));
		fitIn.setCount(rs.getInt("count"));
		fitIn.setTotal(rs.getFloat("total"));
        fitIn.setInDate(rs.getDate("in_Date"));
        fitIn.setInState(rs.getInt("in_State"));
		fitIn.setCreateDate(rs.getDate("create_date"));
		fitIn.setRemark(rs.getString("remark"));
		fitIn.setType(rs.getString("type"));
		return fitIn;
	}
}
