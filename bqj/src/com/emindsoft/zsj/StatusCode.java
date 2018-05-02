package com.emindsoft.zsj;

import java.util.HashMap;
import java.util.Map;

public class StatusCode {
	private static Map<String, String> codeMap = new HashMap<String, String>();
	// 静态的状态码信息
	static {
		codeMap = new HashMap<String, String>();
		// ********************公共的响应码
		codeMap.put("001", "操作成功！");
		codeMap.put("002", "更新数据异常！");
		codeMap.put("003", "操作失败！");
		// ********************系统设置模块状态码 8 开头
		// 用户管理
		codeMap.put("801", "添加用户信息失败！");
		codeMap.put("802", "修改用户信息失败！");
		codeMap.put("803", "两次输入密码不一致！");
		codeMap.put("804", "旧密码输入错误！");
		codeMap.put("805", "删除用户信息失败！");
		// 角色管理
		codeMap.put("806", "该角色已经存在，请重新输入！");
		codeMap.put("807", "修改角色信息失败！");
		codeMap.put("808", "删除角色信息失败！");
		// 字典管理
		codeMap.put("809", "添加字典信息失败！");
		codeMap.put("811", "编辑字典信息失败！");
		// 机构管理
		codeMap.put("810", "机构下还有下级部门，无法删除！");
		codeMap.put("820", "部门下还有用户，无法删除！");
		codeMap.put("830", "部门下还有其他的子部门，无法删除！");
		// 区域管理
		codeMap.put("812", "该区域编码已存在，请重新输入！");
		codeMap.put("813", "编码格式不正确,请重新输入！");
		codeMap.put("814", "该区域包含下级区域，不允许删除！");
		codeMap.put("815", "省级区域编码为2位数字！");
		codeMap.put("816", "市级区域编码为4位数字！");
		codeMap.put("817", "县级区域编码为7位数字！");
		codeMap.put("818", "区域编码格式不正确，请重新输入！");
		//政务办公
		codeMap.put("901", " 撤回失败");
		codeMap.put("902", " 结束流程失败");
		//待办事项
		codeMap.put("1301", "请不要重复上报！");
	}

	/**
	 * 获取返回前端的状态码描述
	 * 
	 * @param code
	 * @return
	 */
	public static String get(String code) {// value代表用户输入的字符串，pattern代表验证的类型
		return codeMap.get(code);
	}
}
