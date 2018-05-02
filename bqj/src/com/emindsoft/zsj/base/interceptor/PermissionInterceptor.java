package com.emindsoft.zsj.base.interceptor;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.BaseController;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.util.Tools;
import com.jfinal.core.ActionInvocation;
import com.jfinal.log.Logger;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限验证拦截器,并写入日志
 * 
 * @author ym
 */
public class PermissionInterceptor extends BaseInterceptor {
	public Logger log = Logger.getLogger(getClass());

	/** 获取当前系统操作人 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doIntercept(ActionInvocation ai) {
		BaseController ctrl = (BaseController) ai.getController();
		ctrl.setAttr("port", ctrl.getRequest().getServerPort());
		Method m = ai.getMethod();
		PowerBind p = ai.getMethod().getAnnotation(PowerBind.class);
		log.debug("操作权限控制");
		if (p == null) {
			p = ai.getController().getClass().getAnnotation(PowerBind.class);
		}
		if (p == null) {
			ai.invoke();
		} else if (power_check) {
			boolean v = false;
			String[] code = null;
			v = p.v();
			code = p.code();
			boolean f = false;
			if (v == true) {
				// 菜单权限判断,顺便写入日志
				if (RolePower.dao.checkPower(code, ctrl.getCurrentUserId(),Tools.getRemoteHost(ctrl.getRequest()))) {
					ai.invoke();// 注意 一定要执行此方法
				} else {
					f = true;
				}
			}
			if (f) {
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("success", false);
				json.put("msg", "您未有此操作权限！<br>请联系管理员修改权限设置！");
				json.put("status", 401);
				ctrl.renderJson(json);// ("{\"success\":false,\"msg\":\"您未有此操作权限！<br>请联系管理员修改权限设置！\",\"status\":401}");
			}
		}else{
			ai.invoke();
		}
		
//		String uid=ctrl.getCurrentUserId();
//		if (uid==null || uid.equals("")) {
//			return;
//		}
//		String uip1=getIpAddr(ctrl.getRequest());
//		String uip3=ctrl.getRequest().getRemoteAddr();
//		ServletContext sc=ctrl.getSession().getServletContext();
//		Map<String, String> onlineUser=(Map<String, String>) sc.getAttribute("onlineUser");
//		if (onlineUser==null) {
//			onlineUser=new HashMap<String, String>();
//		}
//		if (!onlineUser.containsKey(uid)) {
//			String uip=getIpAddr(ctrl.getRequest());
//			onlineUser.put(uid, uip);
//			sc.setAttribute("onlineUser", onlineUser);
//		}
	
	}
	
	protected String getIpAddr(HttpServletRequest request) {
        // 通过请求头获取ip地址
        String ip = request.getHeader("x-forwarded-for");
        // 判断ip地址是否是代理地址
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        // 判断ip地址是否是代理地址
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        // 判断ip地址是否是代理地址
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

	private boolean check(String[] s, String[] code) {
		for (String c : code) {
			if (ArrayUtils.contains(s, c)) {
				return true;
			}
		}
		return false;
	}
}