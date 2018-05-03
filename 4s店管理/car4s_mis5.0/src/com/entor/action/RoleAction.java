package com.entor.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.IRoleDao;
import com.entor.entity.Role;
/**
 * 角色操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IRoleDao roleDao;
	private Role role;
	/**
	 * 列表查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && role != null) {//点击查询时
			// 查询参数
			param.put("name", role.getName());
		}
		//
		if(getPage()==null){
			this.setPage(new Page());
		}
		//查询总记录数
		getPage().setTotalRows(roleDao.list(param).size());
		//把分页对象传到Dao
		param.put("page",getPage());
		//查询列表并把数据集合放到request对象中，运到加页面显示
		request.setAttribute("list", roleDao.list(param));
		return "list";
	}
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		role.setCreateDate(new Date());
		int i = roleDao.add(role);
		if(i>0){
			this.setMsg("新增操作成功");
		}else{
			this.setMsg("新增操作失败");
		}
		role = null;
		return this.list();
	}
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!this.isCommit()){//初始化页面
			role = roleDao.getObjById(role.getId());
			return "update";
		}
		int i = roleDao.update(role);
		if(i>0){
			this.setMsg("更新操作成功");
		}else{
			this.setMsg("更新操作失败");
		}
		role = null;
		return this.list();
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		int i = roleDao.delete(role.getId());
		if(i>0){
			this.setMsg("删除操作成功");
		}else{
			this.setMsg("删除操作失败");
		}
		return this.list();
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
