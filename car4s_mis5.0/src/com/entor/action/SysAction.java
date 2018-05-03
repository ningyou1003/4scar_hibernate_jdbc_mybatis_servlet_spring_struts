package com.entor.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.dao.IMenuDao;
import com.entor.dao.IUserDao;
import com.entor.entity.Menu;
import com.entor.entity.User;
/**
 * 系统管理
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class SysAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private User user;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IMenuDao menuDao;
	
	//登录验证
	public String login() throws Exception{
		if(!this.isCommit()){//对直接访问sys_login.do拦截
			return "login";
		}else if(user==null 
			|| "".equals(user.getUsername().trim())
			|| "".equals(user.getPassword().trim())
			){
			return "login";
		}		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("username", user.getUsername());
		param.put("password", user.getPassword());
		//申请末通过当作不存在处理
		param.put("applyState", 2);
    	List<User> list = userDao.list(param);
    	User u = null;
        if(list.size() > 0){
        	u = list.get(0);
			if (u.getLoginFlag() == 1) {// 没冻结
				HttpSession session = request.getSession();
				// 把用户对象放到会话中，用作授权判断的标记
				session.setAttribute("user", u);
				List<Menu> mlist = menuDao.getMenusByRoleId(u.getRoleId());
				//key=父菜单对象，value=子菜单集合
				Map<Menu,List<Menu>> menu =  new TreeMap<Menu,List<Menu>>();
				//临时集合
				Map<Long,Menu> pmap = new HashMap<Long, Menu>();
				//权限集合
				List<String> priv = new ArrayList<String>();
				//循环当前用户所有菜单和权限
				for (Menu m : mlist) {
					if(m.getMenuLevel()==1){//一级菜单
						//判断有没有一级菜单容器
						if(!pmap.keySet().contains(m.getId())){
							menu.put(m, new LinkedList<Menu>());
							pmap.put(m.getId(), m);//为方便后而的判断
						}					
					}else{
						//记录二级菜单和权限的url,为做权限拦截
						priv.add(m.getUrl());					
					}
				} 
				//把二级菜单和权限的url集合放到会话中
				session.setAttribute("priv",priv);
				//循环当前用户所有菜单和权限
				for (Menu m : mlist) {
					if(m.getMenuLevel()==2){//二级菜单
						Menu pmenu = pmap.get(m.getParentId());
						menu.get(pmenu).add(m);
					}				
				}			
				session.setAttribute("menu", menu);
				return "index";
			}else{
				this.setMsg("用户已冻结");
			}
        }else{
        	this.setMsg("用户名密码不正确");
        }
		return "login";
	}
    //退出系统
	public String logout() throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();//把会话设置为无效
		this.setMsg("正常退出系统");
		return "login";
	}
	//用户注册
	public String register() throws Exception{
		int i = userDao.add(user);
		this.setMsg(i>0?"注册成功":"注册失败");
		user = null;
		return "login";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
