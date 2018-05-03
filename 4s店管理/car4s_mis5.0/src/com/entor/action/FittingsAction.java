package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.dao.IFittingsDao;
import com.entor.entity.Fittings;
/**
 * 配件操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class FittingsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Fittings fittings;
	@Autowired
	private IFittingsDao fittingsDao;

	public String list(){
		List<Fittings> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && fittings !=null) {
			param.put("name", fittings.getName());
			param.put("type", fittings.getType());
			param.put("unit", fittings.getUnit());
		}
		//查询总记录数
		getPage().setTotalRows(fittingsDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = fittingsDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	public String select(){
		List<Fittings> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && fittings !=null) {
			param.put("name", fittings.getName());
			param.put("type", fittings.getType());
			param.put("unit", fittings.getUnit());
		}
		//查询总记录数
		getPage().setTotalRows(fittingsDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = fittingsDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "select";
	}
	
	public String add(){
		int i = fittingsDao.add(fittings);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		fittings = null;
		return list();
	}
	
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			fittings = fittingsDao.getObjById(fittings.getId());
			return "update";
		}
		int i = fittingsDao.update(fittings);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = fittingsDao.delete(fittings.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}

	public Fittings getFittings() {
		return fittings;
	}

	public void setFittings(Fittings fittings) {
		this.fittings = fittings;
	}
}
