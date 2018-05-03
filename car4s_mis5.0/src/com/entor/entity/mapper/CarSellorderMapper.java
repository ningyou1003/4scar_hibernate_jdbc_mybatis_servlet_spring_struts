package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.CarSellorder;
/**
 * 整车销售单实体映射类
 * @author ZHQL
 */
public class CarSellorderMapper implements RowMapper<CarSellorder> {

	@Override
	public CarSellorder mapRow(ResultSet rs, int index) throws SQLException {
		CarSellorder carSell = new CarSellorder();
		carSell.setId(rs.getInt("id"));
		carSell.setBrand(rs.getString("brand"));
		carSell.setSeries(rs.getString("series"));
		carSell.setCarId(rs.getLong("CAR_ID"));
		carSell.setCustomerId(rs.getLong("CUSTOMER_ID"));
		carSell.setCustName(rs.getString("custname"));
		carSell.setCount(rs.getLong("count"));
		carSell.setTotal(rs.getFloat("total"));
		carSell.setSellPrice(rs.getFloat("sell_price"));
        carSell.setOutDate(rs.getDate("out_date"));
        carSell.setOutState(rs.getInt("out_state"));
		carSell.setSellDate(rs.getDate("sell_date"));
		carSell.setRemark(rs.getString("remark"));
		carSell.setSalesman(rs.getString("salesman"));
		return carSell;
	}

}
