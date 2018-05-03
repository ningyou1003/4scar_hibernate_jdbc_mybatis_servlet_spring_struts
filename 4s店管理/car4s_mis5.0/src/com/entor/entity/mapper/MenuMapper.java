package com.entor.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.entor.entity.Menu;
/**
 *结果集与菜单实体的映射类
 * @author ZHQL
 */
public class MenuMapper implements RowMapper<Menu> {
	@Override
	public Menu mapRow(ResultSet rs, int index) throws SQLException {
		Menu u = new Menu();
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setParentId(rs.getLong("parent_id"));
		u.setUrl(rs.getString("url"));
		u.setMenuLevel(rs.getInt("menu_level"));
		u.setUseFlag(rs.getInt("use_flag"));
		u.setCreateDate(rs.getDate("create_date"));
		return u;
	}

}






