package com.zrcx.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//加/表示跟路径
@WebServlet(value="/LoginServlet")//告诉容器，如果请求的URL是“/LoginServlet”，则由LoginServlet的实例提供服务。
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override	 //service()方法实现用户名和密码的验证
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
//		设置请求参数编码     告知Tomcat浏览器采用的字符集，必须在request.getParameter()前调用
		request.setCharacterEncoding("UTF-8");
//		获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名；"+username);
		System.out.println("密码；"+password);
//		响应的内容类型
		response.setContentType("text/html;charset=UTF-8");
//		指定客户端的流
		PrintWriter out = response.getWriter();
//		动态生成一个页面响应客户
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>结果显示</TITLE></HEAD>");
		out.println("  <BODY>");
		
		if("abc".equals(username) && "123".equals(password)){
			out.print("<center><h2>用户登录成功</h2></center>");
		}else{
			out.print("<center><h2>用户登录失败</h2></center>");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	
		//往request对象放属性值
		request.setAttribute("name", "admin");
		//页面重定向
		if("abc".equals(username) && "123".equals(password)){
			HttpSession session = request.getSession();
//			往对象放属性值
			session.setAttribute("ticket", "公园门票");

			//往浏览器写Cookie数据
			Cookie c1 = new Cookie("username", username);
			c1.setMaxAge(400);//秒    有效期限
			Cookie c2 = new Cookie("password", password);
			c2.setMaxAge(600);
			response.addCookie(c1);
			response.addCookie(c2);
			response.sendRedirect("success.html");
		}else{
			response.sendRedirect("fail.html");
		}
	}
}
