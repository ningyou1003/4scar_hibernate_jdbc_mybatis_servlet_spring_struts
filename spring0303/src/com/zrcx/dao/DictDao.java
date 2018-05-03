package com.zrcx.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zrcx.entity.Dict;
import com.zrcx.entity.DictMapper;
@Transactional
@Repository
public class DictDao {
	//jsbc模板，它是对jdbc底层操作进行了封装
	private JdbcTemplate jtp;
	@Autowired
	public void setDataSource(DataSource dataSource){
		//根据数据源创建jdbc模板
		jtp = new JdbcTemplate(dataSource);
	}
	//不需要事务处理
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void list(){
		String sql = "select * from t_dict";
		List<Dict> list = jtp.query(sql,new DictMapper());
		for (Dict dict : list) {
			System.out.println(dict);
		}
	}
	public void update(){
//		this.delete();
		String sql1 = "update t_dict set create_date = create_date + 1where id = 121";
		int i = jtp.update(sql1);
		System.out.println("更新记录数：" + i);
//		i = 1/0;
		String sql2 = "update t_dict set create_date = create_date + 1where id = 122";
		int j = jtp.update(sql2);
		System.out.println("更新记录数：" + j);
		
	}
	
	private void delete() {
		
	}
}











