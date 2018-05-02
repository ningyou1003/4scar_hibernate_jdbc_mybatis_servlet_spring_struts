package com.emindsoft.zsj.base.interceptor;

import com.emindsoft.zsj.base.ctrl.BaseController;
import com.emindsoft.zsj.util.PropertiesContent;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 基础拦截器父类
 * 
 * @author ym
 */
public abstract class BaseInterceptor implements Interceptor {
	/** 权限检测 */
	protected static Boolean power_check;
	/** 登录状态验证排除url */
	protected static String[] power_url_exclude;

	@Override
	public void intercept(ActionInvocation ai) {
		if (power_check == null) {
			power_check = PropertiesContent.getToBool("power_check", false);
		}
		if (power_url_exclude == null)
			power_url_exclude = PropertiesContent.get("power_url_exclude", "")
					.split(",");

		BaseController ctrl = (BaseController) ai.getController();
		String uid = ctrl.getCurrentUserId();
		String uri = ctrl.getRequest().getServletPath();
		// 登录超时控制
		if (StringUtils.isEmpty(uid)
				&& (!ArrayUtils.contains(power_url_exclude, uri))) {
			ctrl.renderJson("{\"msg\":\"登录超时，请重新登录！\",\"status\":300}");
			return;
		}
		ctrl.keepPara();
		doIntercept(ai);
		ctrl.setAttr("now", new Date());
		ctrl.setAttr("PropertiesContent", PropertiesContent.me);
		// ai.invoke();//注意 不能执行此方法 否则拦截器失效 主要是验证拦截
	}

	public abstract void doIntercept(ActionInvocation ai);
}
