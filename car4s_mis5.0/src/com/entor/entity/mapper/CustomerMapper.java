package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.Customer;
/**
 * 客户实体映射类
 * @author ZHQL
 */
public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int index) throws SQLException {
		Customer cust = new Customer();
		cust.setId(rs.getInt("id"));
		cust.setName(rs.getString("name"));
		cust.setSex(rs.getInt("sex"));
		cust.setVocation(rs.getString("vocation"));
		cust.setWorkunit(rs.getString("workunit"));
		cust.setIdNo(rs.getString("id_no"));
		cust.setContactTel(rs.getString("contact_tel"));
		cust.setAddress(rs.getString("address"));
		cust.setRemark(rs.getString("remark"));
		cust.setCreateDate(rs.getDate("create_date"));
		return cust;
	}

}
