package com.entor.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.dao.IDeptDao;
import com.entor.entity.Dept;
/**
 * 部门操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class DeptAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IDeptDao deptDao;
	private Dept dept;
	/**
	 * 列表查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && dept != null) {//点击查询时
			// 查询参数
			param.put("name", dept.getName());
			param.put("charger", dept.getCharger());
			param.put("contactTel", dept.getContactTel());
		}
		//查询总记录数
		getPage().setTotalRows(deptDao.select(param).size());
		//把分页对象传到Dao
		param.put("page",getPage());
		//查询列表并把数据集合放到request对象中，运到加页面显示
		request.setAttribute("list", deptDao.select(param));
		return "list";
	}
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		dept.setCreateDate(new Date());
		int i = deptDao.add(dept);
		if(i>0){
			this.setMsg("新增操作成功");
		}else{
			this.setMsg("新增操作失败");
		}
		dept = null;
		return this.list();
	}
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!this.isCommit()){//初始化页面
			dept = deptDao.getObjById(dept.getId());
			return "update";
		}
		int i = deptDao.update(dept);
		if(i>0){
			this.setMsg("更新操作成功");
		}else{
			this.setMsg("更新操作失败");
		}
		dept = null;
		return this.list();
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		int i = deptDao.delete(dept.getId());
		if(i>0){
			this.setMsg("删除操作成功");
		}else{
			this.setMsg("删除操作失败");
		}
		dept = null;
		return this.list();
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}	
}
