<%@ page import="com.jfinal.plugin.activerecord.Record"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jfinal.plugin.activerecord.Db"%>
<%@ page import="com.emindsoft.zsj.system.model.Role"%>
<%@ page import="com.emindsoft.zsj.system.model.User"%>
<%@ page import="com.emindsoft.zsj.system.model.RoleUser"%>
<%@ page import="com.emindsoft.zsj.system.model.RolePower"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.jfinal.plugin.activerecord.DbKit"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
	//xx市管理员
	//局主要领导
	//局分管领导
	//科室领导
	//统计员
	//项目管理员
	//一般工作人员

	//每个地区1个管理员账号， 7个角色， 管理员账号有第一个角色

	Connection conn = DbKit.getConfig().getDataSource().getConnection();
	DbKit.getConfig().setThreadLocalConnection(conn);
	conn.setAutoCommit(false);//自动提交变成false

	String areaSql = "select * from s_area";
	List<Record> areaList = Db.find(areaSql);

	try {
		for (Record area : areaList) {
			String regionCode = area.getStr("regionCode");
			String regionName = area.getStr("region");

			String adminRoleId = initRoles(regionCode, regionName);
			initAccount(adminRoleId, regionCode, regionName);

			System.out.println(regionName);
		}

		conn.commit();
		System.out.println("finish");
	} catch (Exception e) {
		e.printStackTrace();
		conn.rollback();
	}
%>
<%!private void initAccount(String roleId, String regionCode, String regionName) {
	User user = new User();
	user.set("userName", regionCode + "_admin")
			.set("relaName", regionName + "管理员")
			.set("regionId", regionCode)
			.set("password", "07bd326091c30ece8b015d58b7c4e922")
			.saveOrUpdate();
	RoleUser roleUser = new RoleUser();
	roleUser.set("roleId", roleId).set("userId", user.getStr("keyId"))
			.saveOrUpdate();
}

	private String initRoles(String regionCode, String regionName) {
		Role role = new Role();
		role.set("name", regionName + "管理员").set("regionCode", regionCode)
				.set("orderId", 1).saveOrUpdate();
		initRolesPower("管理员", role.getStr("keyId"));
		new Role().set("name", "一般工作人员").set("regionCode", regionCode)
				.set("orderId", 2).saveOrUpdate();
		initRolesPower("other", role.getStr("keyId"));
		return role.getStr("keyId");
	}

	//初始化角色权限
	private void initRolesPower(String name, String roleId) {

		String admin_sql = "insert into s_role_power(KeyID,PowerID,LookPower,ADDPower,EditPower,DelPower,AppPower,CheckPower,objectID,permission) " +
				"select REPLACE(UUID(),'-',''),KeyID,1,1,1,1,1,1,?,? from s_power";

		String normal_sql = "insert into s_role_power(KeyID,PowerID,LookPower,objectID,permission) " +
				"select REPLACE(UUID(),'-',''),KeyID,1,?,? from s_power";

		if (name == "管理员") {
			String permission = "LookPower,AddPower,DelPower,EditPower,CheckPower,";
			Db.update(admin_sql, roleId, permission);
		} else {
			String permission = "LookPower,";
			Db.update(normal_sql, roleId, permission);
		}

	}%>