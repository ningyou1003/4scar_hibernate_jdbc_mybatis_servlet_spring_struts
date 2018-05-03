package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.FitSellorder;
/**
 * 配件销售单实体映射类
 * @author ZHQL
 */
public class FitSellorderMapper implements RowMapper<FitSellorder> {

	@Override
	public FitSellorder mapRow(ResultSet rs, int index) throws SQLException {
		FitSellorder fitSell = new FitSellorder();
		fitSell.setId(rs.getInt("id"));
		fitSell.setBrand(rs.getString("brand"));
		fitSell.setFitName(rs.getString("fname"));
		fitSell.setCustName(rs.getString("cname"));
		fitSell.setFittingsId(rs.getInt("FITTINGS_ID"));
		fitSell.setCustomerId(rs.getLong("CUSTOMER_ID"));
		fitSell.setCount(rs.getInt("count"));
		fitSell.setTotal(rs.getFloat("total"));
		fitSell.setOutDate(rs.getDate("out_Date"));
		fitSell.setOutState(rs.getInt("out_State"));
		fitSell.setSellDate(rs.getDate("sell_date"));
		fitSell.setRemark(rs.getString("remark"));
		fitSell.setType(rs.getString("type"));
		fitSell.setSalesman(rs.getString("salesman"));
		fitSell.setSellPrice(rs.getFloat("sell_price"));
		return fitSell;
	}
}
