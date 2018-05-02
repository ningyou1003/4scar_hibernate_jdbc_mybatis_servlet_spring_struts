package com.emindsoft.zsj.system.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.system.vo.UserSelectVO;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.util.safe.MD5;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by ym on 15-3-9.
 */
@TableBind(tableName = "s_user", pkName = "KeyId")
public class User extends BaseModel<User> {
	public static User dao = new User();
	public static String table = "s_user";

	public User qryLoginUser(String id) {
		User user = this.findFirst("select * from " + table + " where KeyID=?",
				id);
		return user;
	}

	/**
	 * 登录逻辑
	 * 
	 * @param userno
	 * @param pwd
	 * @return
	 */
	public User login(String userno, String pwd) {
		pwd = MD5.getMD5ofStr(PropertiesContent.get("md5code") + pwd
				+ PropertiesContent.get("md5code"));
		return this.findFirst("SELECT u.* FROM "+table+" u JOIN s_area a ON u.RegionId=a.RegionCode WHERE u.PassWord=? AND (u.UserName=? OR u.Mobile=? OR u.Email=?) ;", pwd, userno, userno, userno);
	}
	
	/**
	 * 登录判断
	 * 
	 * @param userno
	 * @param pwd
	 * @return
	 */
	public User loginCheck(String userno, String pwd) {
		pwd = MD5.getMD5ofStr(PropertiesContent.get("md5code") + pwd
				+ PropertiesContent.get("md5code"));
		return this.findFirst("select * from " + table
				+ " where laborstatus='在职在岗' and UserName = ? and PassWord = ?", userno, pwd);
	}
	
	/**
	 * 密码修改检查
	 * 
	 * @param keyid
	 * @param pwd
	 * @return
	 */
	public User check(String keyid, String pwd) {
		pwd = MD5.getMD5ofStr(PropertiesContent.get("md5code") + pwd
				+ PropertiesContent.get("md5code"));
		return this.findFirst("select * from " + table
				+ " where Keyid = ? and PassWord = ?", keyid, pwd);
	}

	public Page<User> page(int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT u.KeyID,u.UserName,(SELECT DepartmentName from s_department where KeyID = u.DepartmentID)  as DepartmentID,"
				+ "(SELECT Name from " + Role.table + " where KeyID = u.RolesID)  as RolesID,"
				+ "(SELECT DictionaryName from s_dictionary where KeyID = u.JobsID)  as JobsID,"
				+ "u.Phone,u.RelaName,u.Address,u.Email,u.NetWorkVisit");
		return this.paginate(pageNo, pageSize, sql.toString(),
				"FROM s_user u where 1=1 ORDER BY UserName");
	}

	/**
	 * 根据id删除用户
	 * 
	 * @param keyids
	 *            用户id
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		RoleUser.dao.deleteByUserId(keyids);
		return super.deleteByIds(keyids, table);
	}
	
	/**
	 * 用户管理模块删除用户操作，并未将数据删除，而是将用户的帐号信息设为未生成，且不可登录系统
	 * @param keyIds
	 * @return
	 */
	public boolean setUnvailable(String[] keyIds){
		User user;
		try {
			for(String keyid:keyIds){
				user = findFirst("select * from "+table+" where keyid=?", keyid);
				user.set("username", null);
				user.set("LaborStatus", null);
				user.set("userNum", null);
				user.update();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 分页(根据用户所在区域编码查询分页)
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页显示的记录条数
	 * @param userVO
	 *            查询条件
	 * @return
	 */
	public Page<User> page(int pageNo, int pageSize, UserSelectVO userVO, String regionId) {
		String sqlExceptSelect = " FROM " + table
				+ " WHERE UserName <> '' and isAvailable=0 ";
		if (!StringUtils.isEmpty(userVO.getName())) {
			sqlExceptSelect += " AND relaname like '%" + userVO.getName()
					+ "%'";
		}
		if (!StringUtils.isEmpty(userVO.getDepartment()) ) {
			sqlExceptSelect += " AND orgdepid in (SELECT KeyID FROM " + OrgDepartment.table + " WHERE DepartmentName like '%"
					+ userVO.getDepartment() + "%')";
		}
		if (!StringUtils.isEmpty(userVO.getRole()) ) {
			sqlExceptSelect += " AND KeyID in (SELECT userid FROM s_role_user WHERE roleid = '"
					+ userVO.getRole() + "')";
		}
		if (!StringUtils.isEmpty(userVO.getRegionid())) {
			sqlExceptSelect += " AND regionid='" + userVO.getRegionid() + "'";
		} else {
			sqlExceptSelect += " AND regionid='" + regionId + "'";
		}
		sqlExceptSelect += " ORDER BY RegistrationTime DESC";
		return this
				.paginate(
						pageNo,
						pageSize,
						"SELECT KeyID,UserName,(SELECT DepartmentName from " + OrgDepartment.table + " where KeyID = orgdepid)  as DepartmentID,"
								+ "(SELECT CASE demonstration WHEN 1 THEN CONCAT(Region,'(示范)') ELSE Region END AS Region from " + Area.table + " where RegionCode = RegionID)  as RegionID,"
								+ "(SELECT Name from s_job where KeyID = JobsID)  as JobsID,"
								+ "mobile,Phone,RelaName,Address,Email,NetWorkVisit ",
						sqlExceptSelect);
	}

	/**
	 * 加载用户详细信息
	 * @param keyid
	 * @return
	 */
	public User loadUserDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("SELECT u.KeyID keyid,u.UserName,u.RegionId," +
					"(SELECT CASE demonstration WHEN 1 THEN CONCAT(Region,'(示范)') ELSE Region END AS Region from " + Area.table + " where RegionCode = u.RegionId)  as RegionName," +
					"u.Mobile,u.RelaName,u.Email FROM s_user u " +
					"WHERE u.keyid='"+keyid+"' LIMIT 1");
		} else {
			return null;
		}
	}

	public boolean findByDepNo(String[] keyids) {
		String sql = "SELECT KeyID from s_user WHERE DepartmentID =?";
		for(String dep : keyids){
			if(findFirst(sql,dep)==null){
				return false;
			}
		}
		return true;
	}

	public User findByUserNum(String userNum) {
		String sql = "SELECT * from " + table + " WHERE UserNum =?";
		return findFirst(sql, userNum);
	}

	public List<User> delOrgDepCheckUserExist(String orgDepId) {
		String sql = "SELECT * from " + table + " WHERE orgdepid =?";
		return find(sql, orgDepId);
	}

	public User findByUserName(String username) {
		String sql = "SELECT * from " + table + " WHERE UserName =?";
		return findFirst(sql, username);
	}

	public List<User> findOrgUsers(String orgid) {
		String sql = "SELECT u.keyid as id,u.orgid as ParentCode,u.keyid as RegionCode,u.RelaName as Region,'' as orgNum,'isuser' as type FROM s_user u WHERE u.orgid =? ";
		return find(sql,orgid);
	}

	/**
	 * 活动模块调用的部门下人员方法 2016.1.19 wzf
	 * @param depid
	 * @return
	 */
	public List<User> findDepUsers(String depid) {
		String sql="SELECT u.keyid as ID,u.orgdepid as ParentCode,u.keyid as RegionCode,u.RelaName as Region,'' as orgNum,'isuser' as type FROM s_user u WHERE u.orgdepid =?";
		return find(sql,depid);
	}

	public List<User> findDepRoleUsers(String orgId, String depId, String roleId){
		List param = new ArrayList();

		String sql = "SELECT u.KeyID, u.RelaName, u.orgdepid FROM s_user u WHERE u.orgid=? and u.userName not like '%admin%' order by userorder";
		param.add(orgId);

		if(StrKit.notBlank(depId) && StrKit.isBlank(roleId)){
			sql = "select u.KeyID, u.RelaName, u.orgdepid from s_user u where u.orgid=? and (orgdepid=? or orgdepid2=?) and u.userName not like '%admin%' order by userorder";
			param.add(depId);
			param.add(depId);
		}

		if(StrKit.isBlank(depId) && StrKit.notBlank(roleId)){
			sql = "select u.KeyID, u.RelaName, u.orgdepid from s_user u, s_role_user ru where u.KeyID=ru.userid and u.orgid=? and ru.roleid=?  and u.userName not like '%admin%' order by userorder";
			param.add(roleId);
		}

		if(StrKit.notBlank(depId) && StrKit.notBlank(roleId)){
			sql = "select distinct result.* from (" +
					"select u.KeyID, u.RelaName, u.orgdepid from s_user u, s_role_user ru where u.KeyID=ru.userid and u.orgid=? and ru.roleid=? and u.userName not like '%admin%'\n" +
					"union \n" +
					"select u.KeyID, u.RelaName, u.orgdepid from s_user u where u.orgid=? and (orgdepid=? or orgdepid2=?) and u.userName not like '%admin%'\n" +
					") as result order by userorder";
			param.add(roleId);
			param.add(orgId);
			param.add(depId);
			param.add(depId);
		}

		List<User> list = find(sql, param.toArray());
		return list;
	}

	public Page<User> linkmanListPage(int pageNo, int pageSize,String inputname, String orgid, String regioncode,
			String phone,String mobile) {
		StringBuffer sb = new StringBuffer();
		if (!StringUtils.isEmpty(inputname)) {
			sb.append(" AND i.RELANAME LIKE '%"+inputname+"%' ");
		}
		if (!StringUtils.isEmpty(orgid)) {
			sb.append(" AND i.orgid LIKE '%"+orgid+"%' ");
		}
		if (!StringUtils.isEmpty(phone)) {
			sb.append(" AND i.phone LIKE '%"+phone+"%' ");
		}
		if (!StringUtils.isEmpty(mobile)) {
			sb.append(" AND i.mobile LIKE '%"+mobile+"%' ");
		}
		if (!StringUtils.isEmpty(regioncode)) {
			sb.append(" AND i.REGIONID LIKE '%"+regioncode+"%' ");
		}
		String select = "SELECT i.*,(SELECT o.org_fullname FROM " + Org.table + " o WHERE o.KeyID=i.orgid) as org," +
				"(SELECT j.`Name` FROM s_job j WHERE j.KeyID=i.JobsID) as job ";
		return this.paginate(pageNo, pageSize, select, " FROM " +table+" i WHERE 1=1 "+sb.toString());
	}
	
	public List<User> findUserByRelanameLike(String relaname){
		String sql = "select * from "+table+" where RELANAME like '%"+relaname+"%' ";
		return find(sql);
	}
	
	public List<User> findByRegionId(String regionId){
		String sql = "SELECT KeyID,RelaName FROM s_user WHERE RegionId='" + regionId + "'";
		return find(sql);
	}
	
	public List<User> findByParentRegionId(String regionId){
		System.out.println(regionId);
		String sql = "SELECT u.KeyID,u.RelaName FROM s_user u,s_area a WHERE u.RegionId=a.regioncode and a.parentCode='" + regionId + "'";
		System.out.println(sql);
		return find(sql);
	}
	
	public User findById(String keyid){
		String sql = "SELECT u.KeyID, u.RegionId, u.UserName, u.RelaName, a.Region as level, u.PassWord FROM "+table+" u JOIN s_area a ON u.RegionId=a.RegionCode WHERE u.KeyID='"+keyid+"'";
		return findFirst(sql);
	}

}
