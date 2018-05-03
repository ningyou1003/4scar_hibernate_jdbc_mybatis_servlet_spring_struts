package com.entor.action;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.IUserDao;
import com.entor.entity.User;
/**
 * 用户操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserDao userDao;
	private User user;
	/**
	 * 列表查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && user != null) {//点击查询时
			// 查询参数
			param.put("name", user.getName());
			param.put("username", user.getUsername());
			param.put("deptId", user.getDeptId());
			param.put("loginFlag", user.getLoginFlag());
		}
		//
		if(getPage()==null){
			this.setPage(new Page());
		}
		//查询总记录数
		getPage().setTotalRows(userDao.list(param).size());
		//把分页对象传到Dao
		param.put("page",getPage());
		//查询列表并把数据集合放到request对象中，运到加页面显示
		request.setAttribute("list", userDao.list(param));
		return "list";
	}
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		user.setLoginFlag(1);//正常
		user.setPassword("123");//初始密码
		int i = userDao.add(user);
		if(i>0){
			this.setMsg("新增操作成功");
		}else{
			this.setMsg("新增操作失败");
		}
		user = null;
		return this.list();
	}
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!this.isCommit()){//初始化页面
			user = userDao.getObjById(user.getId());
			return "update";
		}
		
		int i = userDao.update(user);
		if(i>0){
			this.setMsg("更新操作成功");
		}else{
			this.setMsg("更新操作失败");
		}
		user = null;
		return this.list();
	}
	/**
	 * 密码更新
	 * @return
	 * @throws Exception
	 */
	public String pwdupdate() throws Exception {
		if(!this.isCommit()){//初始化页面
			User u = (User)request.getSession()
					  .getAttribute("user");
			user = userDao.getObjById(u.getId());
			return "pwdupdate";
		}
		if(user.getNewpassword().equals("") || !user.getNewpassword().equals(user.getPassword1())){
			this.setMsg("密码更新操作失败");
			return "result";
		}
		int i = userDao.update(user);
		if(i>0){
			this.setMsg("密码更新操作成功");
		}else{
			this.setMsg("密码更新操作失败");
		}
		return "result";
	}
	/**
	 * 用户个人信息
	 * @return
	 * @throws Exception
	 */
	public String userInfo() throws Exception {
		User u = (User)request.getSession().getAttribute("user");
		user = userDao.getObjById(u.getId());
		return "userInfo";
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		int i = userDao.delete(user.getId());
		if(i>0){
			this.setMsg("删除操作成功");
		}else{
			this.setMsg("删除操作失败");
		}
		return this.list();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
