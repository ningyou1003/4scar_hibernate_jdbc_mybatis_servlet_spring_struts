package com.zrcx.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 权限拦截切面
 * @author Administrator
 *
 */
@Aspect
@Component
public class PrivAspect {
	//任意方法名，表示切入点
	@Pointcut("execution(* com.zrcx.dao.*.*(..))")
	public void pc1(){};
	@Pointcut("execution(* com.zrcx.service.*.*(..))")
	public void pc2(){};
	@Before(value="pc1()")//把通知织入到切点上
	public void beforeAdvice(){
		System.out.println("前置通知2");
	}
	@After(value="pc1()")
	public void afterAdvice(){
		System.out.println("后置通知2");
	}
	@AfterReturning(value="pc1()")
	public void returnAdvice(){
		System.out.println("最终通知2");
	}
	@AfterThrowing(value="pc1()")
	public void throwableAdvice(){
		System.out.println("例外通知2");
	}
	@Around(value="pc1()")//环绕通知
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("进入环绕通知2");
		//调用目标方法
		Object result = pjp.proceed();
		System.out.println("退出环绕通知2");
		return result;
	}
	
}
















