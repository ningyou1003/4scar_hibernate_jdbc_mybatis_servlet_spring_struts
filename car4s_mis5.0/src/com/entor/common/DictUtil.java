package com.entor.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.entor.dao.IDeptDao;
import com.entor.dao.IDictDao;
import com.entor.dao.IMenuDao;
import com.entor.dao.IRoleDao;
import com.entor.dao.ISupplierDao;
import com.entor.entity.Dept;
import com.entor.entity.Dict;
import com.entor.entity.Menu;
import com.entor.entity.Role;
import com.entor.entity.Supplier;
/**
 * 字典处理类
 * @author ZHQL
 */
public class DictUtil {
	/**
	 * 把list转化成Map
	 * @param list
	 * @param c
	 * @param idGet key的get方法名
	 * @param valueGet value的get方法名
	 * @return
	 */
	public static Map<String,String> listToMap(List<? extends Object> list,
		Class<?> c,String idGet,String valueGet){
		//定义一个空的Map
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			for (Object o : list) {
				Method m1 = c.getMethod(idGet);
				Method m2 = c.getMethod(valueGet);
			    //通过反射执行两个属性的get方法，最后把返回值放到map中
				map.put(m1.invoke(o)+"", m2.invoke(o)+"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 刷新系统字典并放到WEB应用上下文
	 * @param sc WEB应用上下文对象
	 */
	public static void refreshDict(ServletContext sc){
		System.out.println("刷新系统字典并放到WEB应用上下文...");
		//获取WEB应用中的sping容器
		ApplicationContext cxt =
		WebApplicationContextUtils.getWebApplicationContext(sc);
		IDictDao dictDao = (IDictDao)cxt.getBean("dictDao");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("flag",1);
		List<Dict> list = dictDao.list(param);
		//<key-字典名称,<key-字典key,value-字典value>>
		Map<String,Map<String,String>> dicts =
		new HashMap<String,Map<String,String>>();
		//循环所有字典进行分数存放
		for (Dict d : list) {
			String name = d.getDictName();
			Map<String,String> map = dicts.get(name);
			if(null == map){
				//创建一个新容器(有顺序)
				map = new LinkedHashMap<String, String>();
				//放入字典组的第一个字典
				map.put(d.getKey(),d.getValue());
				//放到大容器
				dicts.put(name, map);
			}else{
				//放入字典组的其它字典
				map.put(d.getKey(),d.getValue());
			}
		}
		//把分组字典放到WEB应用上下文，供系统使用
		for (String k : dicts.keySet()) {
			System.out.println("字典名称:" + k + " " + dicts.get(k));
			//以字典名称为Key放到application的属性
			sc.setAttribute(k, dicts.get(k));
		}
	}
	/**
	 * 刷新部门数据并放到应用上下文
	 * @param sc
	 */
	public static void refreshDept(ServletContext sc){
		//获取web应用中的spring容器
		ApplicationContext ac = 
		WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println("刷新部门数据到应用上下文...");
		IDeptDao dao = (IDeptDao)ac.getBean("deptDao");
		Map<String,Object> param = new HashMap<String, Object>();
		List<Dept> list = dao.select(param);
		Map<String,String> map 
		= DictUtil.listToMap(list,Dept.class,"getId","getName");
		System.out.println("deptMap:" + map);
		//放到上下文
		sc.setAttribute("deptMap", map);
	}
	
	/**
	 * 刷新角色数据并放到应用上下文
	 * @param sc
	 */
	public static void refreshRole(ServletContext sc){
		//获取web应用中的spring容器
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println("刷新角色数据到应用上下文...");
		IRoleDao dao = (IRoleDao)ac.getBean("roleDao");
		Map<String,Object> param = new HashMap<String, Object>();
		List<Role> list = dao.list(param);
		Map<String,String> map 
		= DictUtil.listToMap(list,Role.class,"getId","getName");
		System.out.println("roleMap:" + map);
		//放到上下文
		sc.setAttribute("roleMap", map);
	}
	/**
	 * 刷新菜单数据并放到应用上下文
	 * @param sc
	 */
	public static void refreshMenu(ServletContext sc){
		//获取web应用中的spring容器
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println("刷新菜单数据到应用上下文...");
		IMenuDao dao = (IMenuDao)ac.getBean("menuDao");
		//查出一级二级菜单
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("menuLevel", 1);
		List<Menu> list = dao.list(param);
		param.put("menuLevel", 2);
		list.addAll(dao.list(param));		
		Map<String,String> map 
		= DictUtil.listToMap(list,Menu.class,"getId","getName");
		System.out.println("menuMap:" + map);
		//放到上下文
		sc.setAttribute("menuMap", map);
	}
	/**
	 * 刷新所有菜单和权限数据并放到应用上下文
	 * @param sc
	 */
	public static void refreshPriv(ServletContext sc){
		//获取web应用中的spring容器
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println("刷新所有菜单和权限数据到应用上下文...");
		IMenuDao dao = (IMenuDao)ac.getBean("menuDao");
		//查出二级三级菜单
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("menuLevel", 2);
		List<Menu> list = dao.list(param);
		param.put("menuLevel", 3);
		list.addAll(dao.list(param));		
		Map<String,String> map 
		= DictUtil.listToMap(list,Menu.class,"getId","getUrl");
		System.out.println("allPrivMap:" + map);
		//放到上下文
		sc.setAttribute("allPrivMap", map);
	}
	/**
	 * 刷新供应商数据并放到应用上下文
	 * @param sc
	 */
	public static void refreshSupplier(ServletContext sc){
		//获取web应用中的spring容器
		ApplicationContext ac = 
				WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println("刷新供应商数据到应用上下文...");
		ISupplierDao dao = (ISupplierDao)ac.getBean("supplierDao");
		Map<String,Object> param = new HashMap<String, Object>();
		List<Supplier> list = dao.list(param);
		Map<String,String> map 
		= DictUtil.listToMap(list,Supplier.class,"getId","getName");
		//放到上下文
		sc.setAttribute("supplierMap", map);
	}
}





