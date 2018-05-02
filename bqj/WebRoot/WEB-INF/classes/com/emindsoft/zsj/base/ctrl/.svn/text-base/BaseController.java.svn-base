package com.emindsoft.zsj.base.ctrl;

import com.alibaba.fastjson.JSON;
import com.emindsoft.zsj.StatusCode;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.util.safe.CipherUtil;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.render.JsonRender;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基础Controller
 * @author ym
 *
 */
public abstract class BaseController<M extends Model<M>> extends Controller {

	public Logger log=Logger.getLogger(getClass());

	protected String id;
    protected Class<M> modelClass;

	/**yyyy-MM-dd HH:mm:ss 日期时间*/
	public static final SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**yyyy-MM-dd 日期*/
	public static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	/**HH:mm:ss 时间*/
	public static final SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");

    /** 获取当前系统操作人 */
    protected User getCurrentUser() {
        return User.dao.qryLoginUser(getCurrentUserId());
    }
    /** 获取当前系统操作人ID */
    public String getCurrentUserId(){
        return getUserMap().get("uid");
    }
    /** 获取当前系统操作人ID */
    public String getCurrentPageSize(){
        return this.getCookie("pagesize");
    }
    /** 获取当前系统操作人区域ID */
    public String getCurrentUserRegionId(){
        return getCurrentUser().getStr("regionId");
    }
    /** 获取选择区域ID */
    public String getChooseRegionId(){
        return this.getCookie("chooseRegionId");
    }
	/** 获取当前系统操作人区域ID */
	public String getCurrentUserOrgId(){
		return getCurrentUser().getStr("orgId");
	}
    /** 获取当前系统操作人岗位ID */
    public String getPositionId(){
        return getUserMap().get("position_id");
    }
    /** 获取当前系统操作人部门ID */
    public String getDepartmentId(){
        return getUserMap().get("department_id");
    }

	@SuppressWarnings("unchecked")
	public Map<String, String> getUserMap() {
		Map<String, String> userMap =new HashMap<String,String>();
		String cookieVal = this.getCookie(PropertiesContent.get("cookie_field_key"));
		if (StringUtils.isNotEmpty(cookieVal)){
			cookieVal = CipherUtil.decryptData(cookieVal);
			userMap = (Map<String, String>) JSON.parse(cookieVal);
		}
		return userMap;
	}

	/***
	 *
	 * @param success
	 * @param statusCode 状态码默认为401
	 * @param msg
	 */
	protected void rendJson(boolean success,Integer statusCode,String msg,Object... data){
		Map<String,Object>json=new HashMap<String,Object>();
		json.put("success",success);
		json.put("status",success?200:(statusCode==null?401:statusCode));
		json.put("msg",msg);
		if(data!=null&&data.length>0){
			json.put("data",data[0]);
			if(data.length>1){
				json.put("tokenid",data[1]);
			}
		}
		rendJson(json);
	}

	/***
	 * 操作成功简便方法, 默认返回001状态码和成功提示
	 */
	protected void success(){
		success("");
	}

	/***
	 * 操作成功简便方法, 默认返回001状态码和成功提示
	 * @param data 业务数据
	 */
	protected void success(Object data){
		Map<String,Object>json=new HashMap<String,Object>();
		json.put("success", true);
		json.put("status", "001");
		json.put("msg", StatusCode.get("001"));
		json.put("data",data==null?new Object():data);
		rendJson(json);
	}
	
	public void success1(Object obj){
		rendJson(obj);
	}
	
	public void success2(Object... data){
		if(data.length%2!=0){
			throw new RuntimeException("data 参数不对");
		}

		Map dataMap = new LinkedHashMap();
		for(int i=0; i<data.length; i=i+2){
			dataMap.put(data[i], data[i + 1]);
		}

		Map<String,Object>json=new HashMap<String,Object>();
		json.put("success", true);
		json.put("status", "001");
		json.put("msg", StatusCode.get("001"));
		json.put("data",dataMap);
		rendJson(json);
	}

	/***
	 * 操作失败简便方法
	 * @param statusCode 状态码
	 */
	protected void error(int statusCode){
		Map<String,Object>json=new HashMap<String,Object>();
		json.put("success", false);
		json.put("status", String.valueOf(statusCode));
		json.put("msg", StatusCode.get(String.valueOf(statusCode)));
		rendJson(json);
	}

	protected void error(int statusCode, String msg){
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("success", false);
		json.put("status", statusCode);
		json.put("msg", msg);
		rendJson(json);
	}

	protected void rendJson(Object json){
		this.render(new JsonRender(json));
//		String agent = getRequest().getHeader("User-Agent");
//		if(agent.contains("MSIE"))
//			this.render(new JsonRender(json).forIE());
//		else{
//			this.render(new JsonRender(json));
//		}
	}

	protected String getId(){
		id = this.getPara(0);
		if (StringUtils.isEmpty(id)) {
			id = this.getPara("id");
		}
		return id;
	}

}
