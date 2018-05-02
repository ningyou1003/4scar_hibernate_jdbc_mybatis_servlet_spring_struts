package com.emindsoft.zsj.base.anatation;

import java.lang.annotation.*;

/**
 * 权限绑定标志<br>
 * 如果此标记未注解的方法则默认为需要验证。
 * <br>默认为不验证
 * 在controller上使用
 * @author ym
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface PowerBind {
	/**对应菜单权限代码 菜单中唯一索引 可以多个*/
	String[] code();
	
	/**功能名称*/
	String funcName();
	
	/**操作类型 <br>1:添加、2:修改 3:删除*/
	int operType() default 0;
	
	/**菜单ID*/
	long menuId() default 0;
	
	/**验证标记  true:需要验证*/
	boolean v() default true;
}
