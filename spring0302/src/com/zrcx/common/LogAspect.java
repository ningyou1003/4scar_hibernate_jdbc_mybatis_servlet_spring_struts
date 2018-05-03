package com.zrcx.common;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 日志记录切面
 * @author Administrator
 *
 */
public class LogAspect {
	public void beforeAdvice(){
		System.out.println("前置通知");
	}
	public void afterAdvice(){
		System.out.println("后置通知");
	}
	public void returnAdvice(){
		System.out.println("最终通知");
	}
	public void throwableAdvice(){
		System.out.println("例外通知");
	}
	//环绕通知
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("进入环绕通知");
		//调用目标方法
		Object result = pjp.proceed();
		System.out.println("退出环绕通知");
		return result;
	}
	
}
















