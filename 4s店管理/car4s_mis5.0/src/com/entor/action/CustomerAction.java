package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.ICustomerDao;
import com.entor.entity.Customer;

/**
 * 客户操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Customer customer;
	@Autowired
	private ICustomerDao customerDao;

	public String list() {
		List<Customer> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && customer != null) {
			param.put("name", customer.getName());
			param.put("contactTel", customer.getContactTel());
			param.put("idNo", customer.getIdNo());
			param.put("sex", customer.getSex());
		}
		if (getPage() == null) {
			setPage(new Page());
		}
		//查询总记录数
		getPage().setTotalRows(customerDao.select(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = customerDao.select(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	public String select() {
		List<Customer> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && customer != null) {
			param.put("name", customer.getName());
			param.put("contactTel", customer.getContactTel());
			param.put("idNo", customer.getIdNo());
			param.put("sex", customer.getSex());
		}
		if (getPage() == null) {
			setPage(new Page());
		}
		// 查询总记录数
		getPage().setTotalRows(customerDao.select(param).size());
		// 把分页信息传到DAO
		param.put("page", getPage());
		list = customerDao.select(param);
		// 把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "select";
	}

	public String add() {
		int i = customerDao.add(customer);
		if (i > 0) {
			this.setMsg("新增成功!");
		} else {
			this.setMsg("新增失败!");
		}
		customer = null;
		return list();
	}

	public String update() {
		// 初始化页面
		if (!this.isCommit()) {
			customer = customerDao.getObjById(customer.getId());
			return "update";
		}
		int i = customerDao.update(customer);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}

	public String delete() {
		int i = customerDao.delete(customer.getId());
		if (i > 0) {
			this.setMsg("删除成功!");
		} else {
			this.setMsg("删除失败!");
		}
		return list();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
