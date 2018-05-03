package com.entor.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 * 在WEB启动时执行，做系统的初始化工作
 * @author ZHQL
 *
 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("系统初始化...");
		//对应jsp里的application
		ServletContext sc = config.getServletContext();
		//获得应用的根路径
		String rootPath = sc.getContextPath();
		System.out.println("rootPath:" + rootPath);
		//把路径放到应用上下文属性
		sc.setAttribute("_cxt", rootPath);
		sc.setAttribute("_ui", rootPath+"/ui");
		sc.setAttribute("_js", rootPath+"/ui/js");
		sc.setAttribute("_css", rootPath+"/ui/css");
		sc.setAttribute("_images", rootPath+"/ui/images");
		sc.setAttribute("_plugins", rootPath+"/ui/plugins");
		//应用主题
		sc.setAttribute("_title", "南宁中软创新汽车4S店管理系统V5.0");
		//系统配置常量
		Const.load();
		//系统字典
		DictUtil.refreshDict(sc);
		//部门
		DictUtil.refreshDept(sc);
		//角色
		DictUtil.refreshRole(sc);
		//一二级菜单
		DictUtil.refreshMenu(sc);
		//所有菜单权限
		DictUtil.refreshPriv(sc);
		DictUtil.refreshSupplier(sc);
	}
}
