package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IFeedbackDao;
import com.entor.entity.Feedback;
import com.entor.entity.mapper.FeedbackMapper;

@Transactional
@Repository
public class FeedbackDao extends BaseDao implements IFeedbackDao {
	
	@Override
	public List<Feedback> list(Map<String,Object> param) {
		List<Feedback> list = null;
		try {
			String sql = "select a.*,b.name,b.contact_tel " +
					" from T_4S_FEEDBACK a,T_4S_CUSTOMER b " +
					" where a.del_flag=1 and a.customer_id=b.id ";
			sql += this.apdStr(param, "title", "", true);
			sql += this.apdStr(param, "info", "", true);
			sql += this.apdStr(param, "cname", "", true);
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new FeedbackMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Feedback getObjById(long id){
		Feedback obj = null;
		try {
			String sql = "select a.*,b.name,b.contact_tel from T_4S_FEEDBACK a,T_4S_CUSTOMER b " +
					" where a.customer_id=b.id and a.id=" + id;
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new FeedbackMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}
	
	@Override
	public int delete(long id){  
		int result = 0;
		try {
			String sql = "update T_4S_FEEDBACK set del_flag=0 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Override
	public int update(Feedback obj){
		int result = 0;
		try {
			String sql = "update T_4S_FEEDBACK set id=id ";
			if(isApd(obj, "title", "")){
				sql += ",title ='" + obj.getTitle() + "'";
			}
			if(isApd(obj, "info", "0")){
				sql += ",info=" + obj.getInfo();
			}
			sql += " where id= " + obj.getId(); 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int add(Feedback obj){

		int result = 0;
		try {
			String sql = "insert into T_4S_FEEDBACK " +
              "(id, title, info, customer_id, create_date,del_flag)" +
              "values (seq_t_4s_FEEDBACK.nextval, " +
              "'"+obj.getTitle()
              +"','"+obj.getInfo()
              +"',"+ obj.getCustomerId() + ",sysdate,1)"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
