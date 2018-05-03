package com.entor.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.entor.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 用户登录验证拦截器
 * @author ZHQL
 */
public class LoginIntercepter implements Interceptor {
	private static final long serialVersionUID = 1L;
	@Override
	public void destroy() {
	}
	@Override
	public void init() {
	}
	@SuppressWarnings("unchecked") //告诉编译器忽略指定的警告，不用在编译完成后出现警告信息。
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		//获取Action上下文对象
		ActionContext ac = ai.getInvocationContext();
		//获取request对象
		HttpServletRequest request
  =(HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);
		//获取会话
		HttpSession session = request.getSession();
		if(null != session.getAttribute("user")){
			String uri = request.getRequestURI();
			System.out.println("uri:" +uri);
			Map<String,String> allPrivMap = 
			  (Map<String,String>)
			  session.getServletContext().getAttribute("allPrivMap");
			List<String> priv = 
			  (List<String>)session.getAttribute("priv");
			String url = uri.split("/")[uri.split("/").length-1];
			System.out.println("url:" +url);
			//判断用户有没有访问某个功能的权限
			if(allPrivMap.containsValue(url) 
			   && !priv.contains(url)){
			   System.out.println("你没有这个功能的访问权限！-" + url);
			   return "index";
			}
			ai.invoke();//请求放行
		}else{
			//获取被拦截的action
			BaseAction action = (BaseAction)ai.getAction();
			action.setMsg("你未登录，没有操作权限");
			System.out.println("被拦截路径:" + request.getContextPath());
			return "login";
		}
		return null;
	}

}









