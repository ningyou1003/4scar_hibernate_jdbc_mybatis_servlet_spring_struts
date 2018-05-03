package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IFitInorderDao;
import com.entor.entity.FitInorder;
import com.entor.entity.mapper.FitInorderMapper;
@Transactional
@Repository
public class FitInorderDao extends BaseDao implements IFitInorderDao {
	
	@Override
	public List<FitInorder> select(Map<String,Object> param) {
		List<FitInorder> list = null;
		try {
			String sql = "select a.*,b.brand,b.name fname,b.type,c.name sname " +
					" from T_4S_FITTINGS_INORDER a,T_4S_FITTINGS b,T_4S_SUPPLIER c " +
					" where a.fittings_id=b.id(+) and a.supplier_id=c.id(+) " +
					" and a.del_flag=1 ";
		    if(isApd(param, "inState", "0")){
				sql += " and in_state =" + param.get("inState");   
		    }
		    if(isApd(param, "brand", "")){
			    sql += " and b.brand like '" + param.get("brand") +"%'";   
		    }
		    if(isApd(param, "fitName", "")){
		    	sql += " and b.name like '" + param.get("fitName") +"%'";   
		    }
		    if(isApd(param, "type", "")){
		    	sql += " and b.type like '" + param.get("type") +"%'";   
		    }
		    if(isApd(param, "supplierId", "0")){
		    	sql += " and a.supplier_id =" + param.get("supplierId");   
		    }
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new FitInorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public FitInorder getObjById(long id){
		FitInorder obj = null;
		try {
			String sql = "select a.*,b.brand,b.name fname,b.type,c.name sname " +
					" from T_4S_FITTINGS_INORDER a,T_4S_FITTINGS b,T_4S_SUPPLIER c " +
					" where a.fittings_id=b.id(+) and a.supplier_id=c.id(+) " +
					" and a.del_flag=1 and a.id=" + id;
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new FitInorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}
	
	@Override
	public int delete(long id){  
		int result = 0;
		try {
			String sql = "update T_4S_FITTINGS_INORDER set del_flag=0 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Override
	public int update(FitInorder obj){
		int result = 0;
		try {
			String sql = "update T_4S_FITTINGS_INORDER set id=id ";
			if(isApd(obj, "count", "0")){
				sql += ",count =" + obj.getCount();
			}
			if(isApd(obj, "inPrice", "0.0")){
				sql += ",in_price=" + obj.getInPrice();
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
	public int add(FitInorder obj){
		int result = 0;
		try {
			String sql = "insert into T_4S_FITTINGS_INORDER " +
              "(id,fittings_id,supplier_id,in_price,count,total,in_date,in_state,create_date,remark)" +
              "values (SEQ_T_4S_FITTINGS_INORDER.nextval," 
              + obj.getFittingsId()+","+obj.getSupplierId()+","+obj.getInPrice()
              +","+obj.getCount()+","+obj.getTotal()+",null,1,sysdate,'"
              +obj.getRemark()+"')"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int pickUp(long id){
		int result = 0;
		FitInorder obj = this.getObjById(id);
		try {
			String sql1 = "select count(*) from T_4S_FITTINGS_STOCK where fittings_id=" 
		     + obj.getFittingsId();
			System.out.println("sql:" + sql1);
			int count = jdbctmp.queryForInt(sql1);
			if(count==0){
				String sql2 = "insert into T_4S_FITTINGS_STOCK " +
			              "(id,fittings_id,count,remark)" +
			              "values (SEQ_T_4S_FITTINGS_STOCK.nextval," 
			              + obj.getFittingsId()+","+obj.getCount()+",'')"; 
				System.out.println("sql:" + sql2);
				jdbctmp.execute(sql2);
			}else{
				String sql3 = "update T_4S_FITTINGS_STOCK " +
						"set count= count+" + obj.getCount()
						+ " where fittings_id=" + obj.getFittingsId();
				System.out.println("sql:" + sql3);
				jdbctmp.execute(sql3);
			}
			String sql4 = "update T_4S_FITTINGS_INORDER " +
					"set in_date= sysdate,in_state=2 "
					+ " where id=" + id;
			System.out.println("sql:" + sql4);
			jdbctmp.execute(sql4);
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
