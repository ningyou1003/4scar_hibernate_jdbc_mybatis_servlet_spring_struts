package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.CarStock;
/**
 * 整车库存实体映射类
 * @author ZHQL
 */
public class CarStockMapper implements RowMapper<CarStock> {

	@Override
	public CarStock mapRow(ResultSet rs, int index) throws SQLException {
		CarStock carStock = new CarStock();
		carStock.setId(rs.getInt("id"));
		carStock.setBrand(rs.getString("brand"));
		carStock.setSeries(rs.getString("series"));
		carStock.setType(rs.getString("type"));
		carStock.setColor(rs.getString("color"));
		carStock.setVolume(rs.getString("volume"));
		carStock.setCarId(rs.getLong("car_Id"));
		carStock.setCount(rs.getLong("count"));
		carStock.setRemark(rs.getString("remark"));
		return carStock;
	}

}
