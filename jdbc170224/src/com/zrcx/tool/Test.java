package com.zrcx.tool;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zrcx.DBUtil;
/**
 * 把表结构转化实体类
 * @author ZHQL
 */
public class Test {

	public static void main(String[] args) {
		String tableName = "T_User";
		String className = "User";
		String pkg = "com.zrcx.entity";
		String path = "D:\\temp";
		ResultSet rs = null;
		try {
			String sql = "select * from " + tableName + " where 1=2";
			rs = DBUtil.query(sql);
		    //结果集元数据对象
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();//列数
			List<FieldDesc> list = new ArrayList<FieldDesc>();
			FieldDesc fd = null;
			for (int i = 1; i <=cols; i++) {//读所有字段的信息
				fd = new FieldDesc();
				fd.setCol(i);
				fd.setName(md.getColumnName(i));
				fd.setType(md.getColumnTypeName(i));
				fd.setPrecision(md.getPrecision(i));
				fd.setScale(md.getScale(i));
				list.add(fd);
			}
			//类描述对象
			ClassDesc cd = new ClassDesc();
			cd.setFields(list);
			cd.setName(className);
			cd.setPkg(pkg);
			cd.setPath(path);	
			sql = "select t.COMMENTS" +
					" from user_col_comments t " +
					" where t.TABLE_NAME='"
					+ tableName.toUpperCase() +"'";
			rs = DBUtil.query(sql);
			Map<Integer,String> map =
			new HashMap<Integer,String>();
			while(rs.next()){
				map.put(rs.getRow(),
						rs.getString("COMMENTS"));
			}
			cd.setComments(map);
			cd.print();//打印java代码
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
		}

	}

}
