package com.entor.dao;

import java.util.List;
import java.util.Map;

import com.entor.entity.Menu;

public interface IMenuDao {

	public List<Menu> getMenusByRoleId(long roleId);
	public List<Menu> getAllMenus();
	
	//------------------------------------
	public List<Menu> list(Map<String, Object> param);

	public Menu getObjById(long id);

	public int delete(long id);

	public int update(Menu obj);

	public int add(Menu obj);
	
	public int deleteRole2Menu(long roleId,long menuId);
		
    public int addRole2Menu(long roleId,long menuId);

}