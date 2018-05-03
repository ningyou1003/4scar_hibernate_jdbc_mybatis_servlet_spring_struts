package com.zrcx.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * JDK实现的动态代理
 * @author ZHQL
 */
public class JDKProxy implements InvocationHandler {
	//要代理的目标对象
	private Object targetObj;
	//生成代理对象
	public Object createProxyInstance(Object targetObj){
		this.targetObj = targetObj;
		/*
		 * 参数一:类装载器
		 * 参数二:设置代理类的实现接口
		 * 参数三:设置回调对象
		 */
		Object proxyInstance = 	//jdk的高级应用
		Proxy.newProxyInstance(
				targetObj.getClass().getClassLoader(), 
				targetObj.getClass().getInterfaces(), 
				this);
		return proxyInstance;
	}

	@Override
	public Object invoke(Object proxy, 
			Method method, Object[] args)
			throws Throwable {
		System.out.println("调用目标对象方法:" + method.getName());
		System.out.println("参数信息:");
		if(args!=null){
			for (Object o : args) {
				System.out.println("参数类型:" 
			    + o.getClass().getName()+" - 参数值:" + o);
				
			}
		}
		Object result = null;//接收结果
		System.out.println("-------------前置通知--------------");
		try{
			//调用目标对象方法
			result = method.invoke(targetObj, args);
		}catch (Exception e) {
			System.out.println("---------例外通知-----------");
			e.printStackTrace();
		}	
		System.out.println("-------------后置通知--------------");
		return result;
	}

}






