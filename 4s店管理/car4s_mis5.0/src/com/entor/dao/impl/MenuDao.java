package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IMenuDao;
import com.entor.entity.Menu;
import com.entor.entity.mapper.MenuMapper;
@Repository
public class MenuDao extends BaseDao implements IMenuDao {
	@Override
	public List<Menu> getMenusByRoleId(long roleId){
		String sql = "select * from t_4s_menu a,t_4s_role2menu b " +
				" where a.id=b.menu_id " +
				" and a.use_flag=1 and a.del_flag!=0 and b.role_id="+roleId;
		System.out.println("sql:" + sql);
		List<Menu> list = jdbctmp.query(sql,new MenuMapper()); 
		return list;
	}
	public List<Menu> getAllMenus(){
		String sql = "select * from t_4s_menu where use_flag=1 and del_flag!=0";
		System.out.println("sql:" + sql);
		List<Menu> list = jdbctmp.query(sql,new MenuMapper()); 
		return list;
	}
	
	@Override
	public List<Menu> list(Map<String,Object> param) {
		List<Menu> list = null;
		try {
			String sql = "select * from T_4S_MENU where 1 = 1 ";
			sql+= this.apdStr(param,"name","",true);
			sql+= this.apdStr(param,"url","",false);
			sql+= this.apdNum(param,"useFlag","0");
			sql+= this.apdNum(param,"menuLevel","0");
			sql+= this.apdNum(param,"parentId","0");
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			sql += " order by id ";
            System.out.println("sql:" + sql);
            list = jdbctmp.query(sql,new MenuMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Menu getObjById(long id){
		Menu obj = null;
		try {
			String sql = "select * from T_4S_MENU where id=" + id; 
			System.out.println("sql:" + sql);
            obj = jdbctmp.queryForObject(sql,new MenuMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@Override
	public int delete(long id){  
		int i = 0;
		try {
			String sql = "delete from T_4S_MENU where id="+id;
			System.out.println("sql:" + sql);
			i = jdbctmp.update(sql);		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return i;		
	}
	
	@Override
	public int update(Menu obj){
		int i = 0;
		try {
			String sql = "update T_4S_MENU set id=id ";
			if(isApd(obj, "name", "")){
				sql += ",name ='" + obj.getName() + "'";
			}
			if(isApd(obj, "url", "")){
				sql += ",url='" + obj.getUrl() + "'";
			}
			if(isApd(obj, "parentId", "")){
				sql += ",parent_id=" + obj.getParentId();
			}
			if(isApd(obj, "menuLevel", "0")){
				sql += ",menu_level=" + obj.getMenuLevel();
			}
			sql += " where id = " + obj.getId(); 
			System.out.println("sql:" + sql);
			i = jdbctmp.update(sql);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return i;		
	}
	@Override
	public int add(Menu obj){
		int i = 0;
		try {
			String sql = "insert into T_4S_MENU " +
		              "(id, name, url, parent_id, " +
		              "use_flag, menu_level,create_date)" +
		              "values (seq_t_4s_menu.nextval, " +
		              "'"+obj.getName()+"','"+obj.getUrl()
		              +"',"+obj.getParentId()+","+obj.getUseFlag()+","
		              +obj.getMenuLevel()+",sysdate)"; 
					System.out.println("sql:" + sql);
					i = jdbctmp.update(sql);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return i;		
	}
	/**
	 * 新增角色与菜单关系
	 * @return
	 */
	public int addRole2Menu(long roleId,long menuId){
		
		
		int i = 0;
		try {
			String sql = "insert into T_4S_ROLE2MENU(role_id, menu_id)"
					+ "values (" + roleId + "," + menuId + ")";
			System.out.println("sql:" + sql);
			i = jdbctmp.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return i;		
	}
	/**
	 * 删除角色与菜单关系
	 * @return
	 */
	public int deleteRole2Menu(long roleId,long menuId){
		int i = 0;
		try {
			String sql = "delete from T_4S_ROLE2MENU  where "
					+ " role_id = "+ roleId +" and menu_id=" + menuId;
			System.out.println("sql:" + sql);
			i = jdbctmp.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return i;		
	}
}











