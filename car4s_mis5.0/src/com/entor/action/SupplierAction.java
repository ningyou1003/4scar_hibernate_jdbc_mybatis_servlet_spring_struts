package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.ISupplierDao;
import com.entor.entity.Supplier;
/**
 * 供应商操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class SupplierAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Supplier supplier;
	@Autowired
	private ISupplierDao supplierDao;

	public String list(){
		List<Supplier> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && supplier !=null) {
			param.put("name", supplier.getName());
			param.put("contacts", supplier.getContacts());
			param.put("contactTel", supplier.getContactTel());
			param.put("bankName",supplier.getBankName());
			param.put("bankAccount",supplier.getBankAccount());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(supplierDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = supplierDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	
	public String select(){
		List<Supplier> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && supplier !=null) {
			param.put("name", supplier.getName());
			param.put("contacts", supplier.getContacts());
			param.put("contactTel", supplier.getContactTel());
			param.put("bankName",supplier.getBankName());
			param.put("bankAccount",supplier.getBankAccount());
		}
		if(getPage()==null){
			setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(supplierDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = supplierDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "select";
	}
	
	public String add(){
		int i = supplierDao.add(supplier);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		supplier = null;
		return list();
	}
	
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			supplier = supplierDao.getObjById(supplier.getId());
			return "update";
		}
		int i = supplierDao.update(supplier);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = supplierDao.delete(supplier.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ISupplierDao getSupplierDao() {
		return supplierDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
}
