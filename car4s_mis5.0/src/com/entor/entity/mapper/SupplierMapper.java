package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entor.entity.Supplier;
/**
 * 供应商实体映射类
 * @author ZHQL
 */
public class SupplierMapper implements RowMapper<Supplier> {

	@Override
	public Supplier mapRow(ResultSet rs, int index) throws SQLException {
		Supplier obj = new Supplier();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setContacts(rs.getString("contacts"));
		obj.setContactTel(rs.getString("contact_tel"));
		obj.setBankName(rs.getString("bank_name"));
		obj.setBankAccount(rs.getString("bank_account"));
		obj.setRemark(rs.getString("remark"));
		obj.setCreateDate(rs.getDate("create_date"));
		obj.setDelFlag(rs.getInt("del_flag"));
		return obj;
	}

}
