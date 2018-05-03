package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.Car;
/**
 * 整车实体映射类
 * @author ZHQL
 */
public class CarMapper implements RowMapper<Car> {

	@Override
	public Car mapRow(ResultSet rs, int index) throws SQLException {
		Car car = new Car();
		car.setId(rs.getInt("id"));
		car.setBrand(rs.getString("brand"));
		car.setSeries(rs.getString("series"));
		car.setType(rs.getInt("type"));
		car.setVolume(rs.getString("volume"));
		car.setColor(rs.getString("color"));
		car.setProPlace(rs.getString("pro_place"));
        car.setPicPath(rs.getString("pic_path"));
        car.setPrice(rs.getFloat("price"));
		car.setCreateDate(rs.getDate("create_date"));
		car.setRemark(rs.getString("remark"));
		car.setVender(rs.getString("vender"));
		return car;
	}

}
