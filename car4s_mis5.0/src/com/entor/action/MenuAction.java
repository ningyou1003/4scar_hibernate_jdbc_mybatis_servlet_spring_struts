package com.entor.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.DictUtil;
import com.entor.common.Page;
import com.entor.dao.IMenuDao;
import com.entor.entity.Menu;
/**
 * 菜单操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IMenuDao menuDao;
	private Menu menu;
	//角色ID
	private int roleId;
	//菜单ID串
	private String menuIds;
	/**
	 * 列表查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && menu != null) {//点击查询时
			// 查询参数
			param.put("name", menu.getName());
			param.put("parentId", menu.getParentId());
			param.put("menuLevel", menu.getMenuLevel());
			param.put("useFlag", menu.getUseFlag());
		}
		//
		if(getPage()==null){
			this.setPage(new Page());
		}
		//查询总记录数
		getPage().setTotalRows(menuDao.list(param).size());
		//把分页对象传到Dao
		param.put("page",getPage());
		//查询列表并把数据集合放到request对象中，运到加页面显示
		request.setAttribute("list", menuDao.list(param));
		return "list";
	}
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		menu.setCreateDate(new Date());
		int i = menuDao.add(menu);
		if(i>0){
			this.setMsg("新增操作成功");
		}else{
			this.setMsg("新增操作失败");
		}
		menu = null;
		return this.list();
	}
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!this.isCommit()){//初始化页面
			menu = menuDao.getObjById(menu.getId());
			return "update";
		}
		int i = menuDao.update(menu);
		if(i>0){
			this.setMsg("更新操作成功");
		}else{
			this.setMsg("更新操作失败");
		}
		menu = null;
		return this.list();
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		int i = menuDao.delete(menu.getId());
		if(i>0){
			this.setMsg("删除操作成功");
		}else{
			this.setMsg("删除操作失败");
		}
		return this.list();
	}
	
	//弹出窗口为角色分配菜单
	public String menuTree(){
		//查询所有菜单
		Map<String, Object> param = new HashMap<String, Object>();
		List<Menu> list1 = menuDao.list(param);
		//角色当前菜单权限
		List<Menu> list2 = menuDao.getMenusByRoleId(roleId);
		
		//用户菜单Key=url,value=name
    	Map<String,String> menusMap = 
    	DictUtil.listToMap(list2,Menu.class,"getId","getName");
    	//在角色拥有的菜单权限上做标记
    	for (Menu o : list1) {
    		if(menusMap.containsKey(o.getId()+"")){
    			o.setChecked(true);
    		}else{
    			o.setChecked(false);
    		}
    	}
    	JSONArray jo2 = JSONArray.fromObject(list1);
		//把对象集合转化成json格式字符串
		String menuJson= jo2.toString();
		System.out.println("menuJson=" + menuJson);
/*		String json="["+
			"{ id:1, pId:0, name:\"系统管理\",open:true},"+
			"{ id:11, pId:1, name:\"部门管理\"},"+
			"{ id:112, pId:11, name:\"修改\"},"+
			"{ id:14, pId:1, name:\"角色管理\"},"+
			"{ id:2, pId:0, name:\"基础数据\",open:true},"+
			"{ id:21, pId:2, name:\"学员管理\"},"+
			"{ id:23, pId:2, name:\"课程管理\"}]";*/
        //把集合放到request对象属性中，传送到页面显示
      	request.setAttribute("roleId", roleId);
      	request.setAttribute("json", menuJson);
		return "menuTree";
	}
	/**
	 * 更新角色和菜单关系信息（新增和删除）
	 * @return
	 */
	public String updateRole2Menu(){
		//先根据角色ID去数据库查询它拥有的菜单
		List<Menu> list = menuDao.getMenusByRoleId(roleId);
		//用户菜单Key=url,value=name
    	Map<String,String> menusMap = 
    	  DictUtil.listToMap(list,Menu.class,"getId","getName");
    	int i = 0;
    	try {
			//新增的菜单选项
			for (String menuId : menuIds.split(",")) {
				if (!"".equals(menuId) && !menusMap.containsKey(menuId)) {//
					menuDao.addRole2Menu(roleId,Integer.parseInt(menuId));
				}
			}
			//删除的菜单选项
			List<String> menuList = Arrays.asList(menuIds.split(","));
			for (String menuId : menusMap.keySet()) {
				if (!menuList.contains(menuId)) {//
					menuDao.deleteRole2Menu(roleId,Integer.parseInt(menuId));
				}
			}
			i = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (i > 0) {
			this.setJsonText("{'result':'权限分配成功'}");
		} else {
			this.setJsonText("{'result':'权限分配失败'}");
		}
		return "json";
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
}
