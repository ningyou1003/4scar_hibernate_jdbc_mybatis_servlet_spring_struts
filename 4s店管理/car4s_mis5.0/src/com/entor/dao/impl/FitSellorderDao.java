package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IFitSellorderDao;
import com.entor.entity.FitSellorder;
import com.entor.entity.mapper.FitSellorderMapper;
@Transactional
@Repository
public class FitSellorderDao extends BaseDao implements IFitSellorderDao {
	
	@Override
	public List<FitSellorder> select(Map<String,Object> param) {
		List<FitSellorder> list = null;
		try {
			String sql = "select a.*,b.brand,b.name fname,b.type,c.name cname " +
					" from T_4S_FITTINGS_SELLORDER a,T_4S_FITTINGS b,T_4S_CUSTOMER c " +
					" where a.fittings_id=b.id(+) and a.customer_id=c.id(+) " +
					" and a.del_flag=1 ";
		    if(isApd(param, "outState", "0")){
				sql += " and out_state =" + param.get("outState");   
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
		    if(isApd(param, "customerId", "0")){
		    	sql += " and a.customer_id =" + param.get("customerId");   
		    }
		    if(isApd(param, "salesman", "")){
		    	sql += " and a.salesman ='" + param.get("salesman") + "'";   
		    }
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new FitSellorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public FitSellorder getObjById(long id){
		FitSellorder obj = null;
		try {
			String sql = "select a.*,b.brand,b.name fname,b.type,c.name cname " +
					" from T_4S_FITTINGS_SELLORDER a,T_4S_FITTINGS b,T_4S_CUSTOMER c " +
					" where a.fittings_id=b.id(+) and a.customer_id=c.id(+) " +
					" and a.del_flag=1 and a.id=" + id;
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new FitSellorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}
	
	@Override
	public int delete(long id){  
		int result = 0;
		try {
			String sql = "update T_4S_FITTINGS_SELLORDER set del_flag=0 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Override
	public int update(FitSellorder obj){
		int result = 0;
		try {
			String sql = "update T_4S_FITTINGS_SELLORDER set id=id ";
			if(isApd(obj, "count", "0")){
				sql += ",count =" + obj.getCount();
			}
			if(isApd(obj, "sellPrice", "0.0")){
				sql += ",sell_price=" + obj.getSellPrice();
			}
			if(isApd(obj, "customerId", "0")){
				sql += ",customer_id=" + obj.getCustomerId();
			}
			if(isApd(obj, "salesman", "")){
				sql += ",salesman='" + obj.getSalesman()+"'";
			}
			if(isApd(obj, "remark", "")){
				sql += ",remark='" + obj.getRemark()+"'";
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
	public int add(FitSellorder obj){
		int result = 0;
		try {
			String sql = "insert into T_4S_FITTINGS_SELLORDER " +
              "(id,fittings_id,customer_id,sell_price,count,total,out_date,out_state,sell_date,remark)" +
              "values (SEQ_T_4S_FITTINGS_SELLORDER.nextval," 
              + obj.getFittingsId()+","+obj.getCustomerId()+","+obj.getSellPrice()
              +","+obj.getCount()+","+obj.getTotal()+",null,1,sysdate,'"
              +obj.getRemark()+"')"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
