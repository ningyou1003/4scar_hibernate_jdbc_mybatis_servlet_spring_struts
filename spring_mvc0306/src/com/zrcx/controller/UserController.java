package com.zrcx.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zrcx.entity.User;


@Controller
@RequestMapping("user")
public class UserController{
	//类型转换器绑定
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
	
	@RequestMapping("add1")
	public ModelAndView add(String name,int age,Date birthday) throws Exception {
		System.out.println("---------------执行add()方法----------------");
		System.out.println("用户名:" + name);
		System.out.println("年    龄:" + age);
		System.out.println("生    日:" + birthday);
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("msg", "用户注册成功");
		return mv;
	}
	@RequestMapping("add2")
	public ModelAndView update(User user) throws Exception {
		System.out.println("---------------执行update()方法----------------");
		System.out.println("用户名:" + user.toString());
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("msg", "用户更新成功");
		return mv;
	}

	@RequestMapping("add")
	public String info(User user,Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@CookieValue("JSESSIONID") String sessionId,
			@RequestHeader("accept") String accept) throws Exception {
		System.out.println("---------------执行update()方法----------------");
		System.out.println("用户信息:" + user.toString());
		System.out.println("请求对象:" + request);
		System.out.println("响应对象:" + response);
		System.out.println("会话ID:" + sessionId);
		System.out.println("信息类型:" + accept);
		model.addAttribute("user", user);
		return "index";
	}
	
	
}















