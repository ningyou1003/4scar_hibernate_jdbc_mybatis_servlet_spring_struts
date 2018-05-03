package com.entor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IDictDao;
import com.entor.entity.Dict;
import com.entor.entity.DictMapper;
/**
 * 字典操作DAO
 * @author ZHQL
 */
@Repository
public class DictDao extends BaseDao implements IDictDao{
	/**
	 * 查询对象列表
	 * @return
	 */
	@Override
	public List<Dict> list(Map<String,Object> param){
		List<Dict> list = new ArrayList<Dict>();
		try {
			// sql动态拼接
			String sql = "select * from t_4s_dict where del_flag=1 ";
			sql+= this.apdStr(param,"dictName","",true);
			sql+= this.apdNum(param,"useFlag","0");
			sql += " order by dict_name,order_no";
			if(param.get("page")!=null){
				Page p = (Page)param.get("page");
				sql = this.getPageSql(sql,p);
			}
			System.out.println("sql:" + sql);
			list = jdbctmp.query(sql,new DictMapper());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据ID查询对象
	 * @param id
	 * @return
	 */
	@Override
	public Dict getObjById(long id){
		Dict u = null;
		try {
			// sql动态拼接
			String sql = "select * from t_4s_dict where id="+id;
			System.out.println("sql:" + sql);
		    u = jdbctmp.queryForObject(sql,new DictMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;		
	}
	/**
	 * 新增
	 * @param tc
	 * @return
	 */
	@Override
	public int add(Dict tc){
		int i = 0;
		String sql 
		 = "insert into t_4s_dict "
         +"(id, dict_name, key,value,use_flag,order_no,create_date)"
         +"values"
         +"(seq_t_4s_dict.nextval,'" +tc.getDictName()
         +"','"+tc.getKey()
         +"','" + tc.getValue() 
         + "',"+tc.getUseFlag()
         +"," + tc.getOrderNo()+",sysdate)";
		System.out.println("sql:" + sql);
	    i = jdbctmp.update(sql);
		return i;
	}
	/**
	 * 删除
	 * @param Id
	 * @return
	 */
	@Override
	public int delete(long id){
		int i = 0;
		String sql = "delete from t_4s_dict where id=" + id;
		System.out.println("sql:" + sql);
	    i = jdbctmp.update(sql);
		return i;
	}
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@Override
	public int update(Dict tc){
		int i = 0;
		if(tc==null){
			return i;
		}
		String sql = "update t_4s_dict set id=id";
		if(null!=tc.getDictName() && !"".equals(tc.getDictName())){
			sql +=",dict_name='" + tc.getDictName() +"'";
		}
		if(null!=tc.getKey() && !"".equals(tc.getKey())){
			sql +=",key='" + tc.getKey() +"'";
		}
		if(null!=tc.getValue() && !"".equals(tc.getValue())){
			sql +=",value='" + tc.getValue() +"'";
		}
		if(0!=tc.getUseFlag() && !"".equals(tc.getUseFlag())){
			sql +=",use_flag='" + tc.getUseFlag() +"'";
		}
		
		/*if(tc!=null && null!=tc.getCharger()
				    &&!"".equals(tc.getCharger())){
			sql +=",charger='" + tc.getCharger() +"'";
		}*/
		sql += " where id =" + tc.getId();
		System.out.println("sql:" + sql);
	    i = jdbctmp.update(sql);
		return i;
	}
}
