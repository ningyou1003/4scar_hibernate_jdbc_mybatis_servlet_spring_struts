package com.entor.dao;

import java.lang.reflect.Method;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.entor.common.Page;
/**
 * DAO基类
 * @author ZHQL
 */
public class BaseDao {
	public JdbcTemplate jdbctmp;
	//注入数据源，并创建jdbc模板
	@Autowired
	public void setDataSource(DataSource ds){
		jdbctmp = new JdbcTemplate(ds);
	}
	/**
	 * 把查询sql包装成分页查询
	 * @param sql 原sql
	 * @param page 分页对象
	 * @return
	 */
	public String getPageSql(String sql,Page page){
		sql = 
		"select *  from (select a.*, rownum rnum from " +
		"("+ sql +") a) b"
		+" where b.rnum > " + page.getStartRow()
		+" and b.rnum <= "+(page.getStartRow()+page.getRowsPerPage());
		return sql;
	}
	
	public String apdStr(Map<String,Object> param,
			      String key,
			      String value,boolean isLike){
		StringBuilder s = new StringBuilder();
		if(param==null){
			return "";
		}
		String v = (String)param.get(key);
		if(v != null && !v.equals(value)){
			if(isLike){
				s.append(" and ").append(this.toDBFiled(key))
				.append(" like '%").append(v).append("%'");
			}else{
				s.append(" and ").append(this.toDBFiled(key))
				.append(" ='").append(v).append("'");
			}			
		}		
		return s.toString();	
	}
	public String apdNum(Map<String,Object> param,
		      String key,String value){
	StringBuilder s = new StringBuilder();
	if(param==null){
		return "";
	}
	String v = ""+param.get(key);
	if (param.get(key)!=null && !v.equals(value)) {
			s.append(" and ").append(this.toDBFiled(key))
			.append(" =").append(v).append(" ");
	}	
	return s.toString();	
}
	/**
	 * 把java字段名转换成DB的字段名（在大写字母前加下划线）
	 * @param javaFiled
	 * @return
	 */
	public String toDBFiled(String javaFiled) {
		String temp =javaFiled.replaceAll("[^A-Z]*", "");
		for (int i = 0; i < temp.length(); i++) {
			javaFiled=javaFiled
			.replaceFirst(""+temp.charAt(i),"_"+temp.charAt(i));
		}
		return javaFiled.toUpperCase();
	}
	
	/**
	 * 判断某属性是否要拼接到sql中
	 * @param obj
	 * @param field
	 * @param value 当前fieldValue等于value时返回false
	 * @return
	 */
	public boolean isApd(Object obj,String field,String value) {
		boolean is = true;
		try {
			if (null != obj && null != field 
				  && !field.trim().equals("")) {
				//获取类的描述类对象
				Class<?> c = obj.getClass();
				//把属性名转换成方法名
				String methodName = 
				"get" + (field.charAt(0)+"").toUpperCase() 
				+ field.substring(1);
				//获取方法
				Method m = c.getMethod(methodName);
				//调用方法
				Object fieldValue = m.invoke(obj);
				if(null==fieldValue || (fieldValue+"").trim().equals(value)){
				   is = false;
				}
			} else if(null == obj) {
				is = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}
}













