package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.CarInorder;
/**
 * 整车进货单实体映射类
 * @author ZHQL
 */
public class CarInorderMapper implements RowMapper<CarInorder> {

	@Override
	public CarInorder mapRow(ResultSet rs, int index) throws SQLException {
		CarInorder carIn = new CarInorder();
		carIn.setId(rs.getInt("id"));
		carIn.setBrand(rs.getString("brand"));
		carIn.setSeries(rs.getString("series"));
		carIn.setCarId(rs.getLong("car_Id"));
		carIn.setSupplierId(rs.getLong("supplier_Id"));
		carIn.setSupName(rs.getString("sname"));
		carIn.setInPrice(rs.getFloat("in_Price"));
		carIn.setCount(rs.getLong("count"));
		carIn.setTotal(rs.getFloat("total"));
        carIn.setInDate(rs.getDate("in_Date"));
        carIn.setInState(rs.getInt("in_State"));
		carIn.setCreateDate(rs.getDate("create_date"));
		carIn.setRemark(rs.getString("remark"));
		return carIn;
	}

}
