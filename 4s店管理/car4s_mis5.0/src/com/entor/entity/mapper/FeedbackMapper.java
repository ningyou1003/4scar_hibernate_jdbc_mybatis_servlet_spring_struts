package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.entor.entity.Feedback;
/**
 * 客户返馈实体映射类
 * @author ZHQL
 */
public class FeedbackMapper implements RowMapper<Feedback> {

	@Override
	public Feedback mapRow(ResultSet rs, int index) throws SQLException {
		Feedback fb = new Feedback();
		fb.setId(rs.getInt("id"));
		fb.setName(rs.getString("name"));
		fb.setTitle(rs.getString("title"));
		fb.setInfo(rs.getString("info"));
		fb.setCustomerId(rs.getLong("CUSTOMER_ID"));
		fb.setContactTel(rs.getString("CONTACT_TEL"));
		fb.setCreateDate(rs.getTimestamp("create_date"));
		return fb;
	}
}
