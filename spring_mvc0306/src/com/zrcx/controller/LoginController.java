package com.zrcx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
@org.springframework.stereotype.Controller
public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名:" + username);
		System.out.println("密码:" + password);
		ModelAndView mv = new ModelAndView("index");
		if("abc".equals(username)&&"123".equals(password)){
			mv.addObject("msg", "系统登录成功");
		}else{
			mv.addObject("msg", "系统登录失败");
		}
		return mv;
	}

}
