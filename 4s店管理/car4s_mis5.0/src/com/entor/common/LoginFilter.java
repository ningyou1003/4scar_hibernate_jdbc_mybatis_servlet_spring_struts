package com.entor.common;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entor.entity.User;
/**
 * 是否登录检查过滤器
 * @author ZHQL
 */
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig fc) 
			throws ServletException {
		//System.out.println("过滤器初始化...");
	}
	@Override
	public void destroy() {
		//System.out.println("过滤器销毁...");
	}
	@Override
	public void doFilter(ServletRequest req, 
			ServletResponse resp,
			FilterChain fc) 
			throws IOException, ServletException {
	   //需求强制转换才能得到请求对象
	   HttpServletRequest request = (HttpServletRequest) req;
	   HttpServletResponse response = (HttpServletResponse) resp;
	   String uri = request.getRequestURI();
	   //会话对象
	   HttpSession session = request.getSession();
	   User user = (User)session.getAttribute("user");
	   //已登录过或访问非限制资源直接放行
	   if(null != user
			   ||uri.matches(".*(login.jsp|register.jsp)")
			   ||uri.equals(request.getContextPath()+"/")){
		  //把请求放行，进入下一个过滤器或servlet
		  fc.doFilter(req, resp);  
	   }else{
		  System.out.println("被拦截URI:" + uri);
		  //跳转到登录页面
		  response.sendRedirect(request.getContextPath()+"/common/login.jsp?msg=" 
		  + URLEncoder.encode("你未登录，无操作权限","UTF-8"));
	   }	  
	}
}









