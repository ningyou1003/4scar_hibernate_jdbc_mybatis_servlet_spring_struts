package com.entor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.ISupplierDao;
import com.entor.entity.Supplier;
import com.entor.entity.mapper.SupplierMapper;
/**
 * 供应商操作Dao
 * @author ZHQL
 */
@Repository
public class SupplierDao extends BaseDao implements ISupplierDao{
	
	//列表信息查询
	@Override
	public List<Supplier> list(Map<String,Object> param) {
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			String sql = "select * from T_4S_SUPPLIER where del_flag=1 ";
			sql += this.apdStr(param, "name", "", true);
			sql += this.apdStr(param, "bankName", "", false);
			sql += this.apdStr(param, "contacts", "", false);
			sql += this.apdStr(param, "contactTel", "", false);
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new SupplierMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	@Override
	public int add(Supplier obj){
		int result = 0;
		try {
			String sql = "insert into T_4S_SUPPLIER " +
              "(id, name, contacts, contact_tel, bank_name, bank_account, remark,create_date)" +
              "values (seq_t_4s_supplier.nextval, " +
              "'"+obj.getName()+"','"+obj.getContacts()
              +"','"+ obj.getContactTel()+"','"+ obj.getBankName()+"','"
              + obj.getBankAccount()+"','"+ obj.getRemark()+"',sysdate)"; 
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
	public int delete(int id){
		int result = 0;
		try {
			String sql = "update from T_4S_SUPPLIER set del_flag=1 where id="+id;
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
	public int update(Supplier obj){
		int result = 0;
		try {
			String sql = "update T_4S_SUPPLIER set id=id ";
			if(isApd(obj, "name", "")){
				sql += ",name ='" + obj.getName() + "'";
			}
			if(isApd(obj, "contacts", "")){
				sql += ",contacts='" + obj.getContacts() + "'";
			}
			if(isApd(obj, "contactTel", "")){
				sql += ",contact_tel='" + obj.getContactTel() + "'";
			}
			if(isApd(obj, "bankName", "")){
				sql += ",bank_name='" + obj.getBankName() + "'";
			}
			if(isApd(obj, "bankAccount", "")){
				sql += ",bank_account='" + obj.getBankAccount() + "'";
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
	public Supplier getObjById(int id){
		Supplier obj = null;
		try {
			String sql = "select * from T_4S_SUPPLIER where id=" + id; 
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new SupplierMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}

}
