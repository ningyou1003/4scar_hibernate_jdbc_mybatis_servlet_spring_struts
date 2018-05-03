package com.zrcx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class User1Controller extends MultiActionController {

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------用户新增---------------");
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("msg","用户新增成功");
		return mv;
	}
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------用户更新---------------");
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("msg","用户更新成功");
		return mv;
	}

}
