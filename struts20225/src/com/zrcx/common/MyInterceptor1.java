package com.zrcx.common;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterceptor1 implements Interceptor {

	@Override
	public void destroy() {
		System.out.println("拦截器一销毁");
	}

	@Override
	public void init() {
		System.out.println("拦截器一创建");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("有请求经过拦截器一.........");
		return null;
	}
	
	
}
