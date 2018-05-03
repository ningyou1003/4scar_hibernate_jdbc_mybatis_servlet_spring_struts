package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IFittingsDao;
import com.entor.entity.Fittings;
import com.entor.entity.mapper.FittingsMapper;

@Repository
public class FittingsDao extends BaseDao implements IFittingsDao {
	
	@Override
	public List<Fittings> list(Map<String,Object> param) {
		List<Fittings> list = null;
		try {
			String sql = "select * from T_4S_FITTINGS where del_flag = 1 ";
			sql += this.apdStr(param, "name", "", true);
			sql += this.apdStr(param, "type", "", true);
			sql += this.apdStr(param, "brand", "", true);
			sql += this.apdNum(param, "unit", "0");
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new FittingsMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 新增
	 * @param fittings
	 * @return
	 */
	@Override
	public int add(Fittings obj){
		int result = 0;
		try {
			String sql = "insert into t_4s_fittings " +
              "(id, name, unit, price, brand, type, create_date, remark, del_flag)" +
              "values (seq_t_4s_fittings.nextval, " +
              "'"+obj.getName()
              +"','"+obj.getUnit()
              +"',"+ obj.getPrice()
              +",'"+ obj.getBrand()
              +"','"+ obj.getType()
              +"',sysdate,'"+ obj.getRemark()+"',1)"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	@Override
	public int delete(long id){
		int result = 0;
		try {
			String sql = "update t_4s_fittings set del_flag=0 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	@Override
	public int update(Fittings obj){
		int result = 0;
		try {
			String sql = "update t_4s_fittings set id=id ";
			if(isApd(obj, "name", "")){
				sql += ",name ='" + obj.getName() + "'";
			}
			if(isApd(obj, "unit", "")){
				sql += ",unit=" + obj.getUnit();
			}
			if(isApd(obj, "price", "0.0")){
				sql += ",price=" + obj.getPrice();
			}
			if(isApd(obj, "brand", "")){
				sql += ",brand='" + obj.getBrand() + "'";
			}
			if(isApd(obj, "type", "")){
				sql += ",type='" + obj.getType() + "'";
			}
			if(isApd(obj, "remark", "")){
				sql += ",remark='" + obj.getRemark() + "'";
			}
			sql += " where id= " + obj.getId(); 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据ID查询
	 * @param list
	 * @return
	 */
	@Override
	public Fittings getObjById(long id){
		Fittings obj = null;
		try {
			String sql = "select * from t_4s_fittings where id=" + id; 
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new FittingsMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}
}
