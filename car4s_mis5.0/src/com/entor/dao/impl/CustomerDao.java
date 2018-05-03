package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.ICustomerDao;
import com.entor.entity.Customer;
import com.entor.entity.mapper.CustomerMapper;

@Transactional
@Repository
public class CustomerDao extends BaseDao implements ICustomerDao {
	
	@Override
	public List<Customer> select(Map<String,Object> param) {
		List<Customer> list = null;
		try {
			String sql = "select * from T_4S_CUSTOMER where del_flag = 1 ";
			sql += this.apdStr(param, "name", "", true);
			sql += this.apdStr(param, "idNo", "", false);
			sql += this.apdStr(param, "contactTel", "", true);
			sql += this.apdNum(param, "sex", "0");			
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new CustomerMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Customer getObjById(long id){
		Customer obj = null;
		try {
			String sql = "select * from T_4S_CUSTOMER where id=" + id; 
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new CustomerMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}
	
	@Override
	public int delete(long id){  
		int result = 0;
		try {
			String sql = "update T_4S_CUSTOMER set del_flag=0 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Override
	public int update(Customer obj){
		int result = 0;
		try {
			String sql = "update T_4S_CUSTOMER set id=id ";
			if(isApd(obj, "name", "")){
				sql += ",name ='" + obj.getName() + "'";
			}
			if(isApd(obj, "sex", "0")){
				sql += ",sex=" + obj.getSex();
			}
			if(isApd(obj, "vocation", "")){
				sql += ",vocation=" + obj.getVocation();
			}
			if(isApd(obj, "workunit", "")){
				sql += ",workunit='" + obj.getWorkunit() + "'";
			}
			if(isApd(obj, "idNo", "")){
				sql += ",id_no='" + obj.getIdNo() + "'";
			}
			if(isApd(obj, "contactTel", "")){
				sql += ",contact_tel='" + obj.getContactTel() + "'";
			}
			if(isApd(obj, "address", "")){
				sql += ",address='" + obj.getAddress() + "'";
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
	@Override
	public int add(Customer obj){

		int result = 0;
		try {
			String sql = "insert into t_4s_Customer " +
              "(id,name,sex,vocation,workunit,id_no,address,contact_tel,remark,create_date,del_flag)" +
              "values (seq_t_4s_Customer.nextval," +
              "'"+obj.getName()
              +"',"+obj.getSex()
              +",'"+ obj.getVocation()
              +"','"+ obj.getWorkunit()
              +"','"+ obj.getIdNo()
              +"','"+ obj.getAddress()
              +"','"+ obj.getContactTel()
              +"','"+ obj.getRemark()+"',sysdate,1)"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}

}
