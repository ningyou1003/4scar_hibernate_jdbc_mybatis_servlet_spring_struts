package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.system.vo.OrgDepTreeVO;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@TableBind(tableName = "s_org_dep", pkName = "KeyId")
public class OrgDepartment extends BaseModel<OrgDepartment> {
	public static OrgDepartment dao = new OrgDepartment();
	public static String table = "s_org_dep";

	public List<OrgDepartment> loadDepartment(String regioncode) {
		String region_seachcode = Area.getSeachCode(regioncode);
		String sql = "select * from " + table +" where regioncode like '"+region_seachcode+"%'";
		return find(sql);
	}
	
	public List<OrgDepartment> loadDepartment(String regioncode, String orgId) {
		String region_seachcode = Area.getSeachCode(regioncode);
		String sql = "select * from " + table +" where regioncode like '"+region_seachcode+"%' and orgcode='"+orgId+"'";
		return find(sql);
	}
	
	
	/**
	 * 根据keyid删除部门信息
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}
	
	/**
	 * 根据keyid删除部门信息
	 * @return
	 */
	public void deleteDepByIds(String[] keyids) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE ");
		sql.append(table);
		sql.append(" SET IsAvailable = 1");
		sql.append(" WHERE KeyId in ( ? ");
		for (String keyid : keyids) {
			sql.append(", ? ");
		}
		String sqlString = sql.substring(0, sql.length() - 4);
		sqlString = sqlString + ")";
		System.out.println(sqlString);
		Db.update(sqlString, keyids);
	}
	
	/**
	 * 点击树形视图查询结果(首)部分
	 * @return
	 */
	public List<OrgDepartment> findChildById(String id) {
		List<OrgDepartment> tempDepList = null;
		
		OrgDepartment dep = findFirst("select keyid,departmentname,departmenton,(select departmentname from s_department where s.parent=keyid) parent from " + table
				+ " s where keyid = ?", id);
		if (dep != null) {
			tempDepList = new ArrayList<OrgDepartment>();
			tempDepList.add(dep);
			findChild(id, tempDepList);
		}
		return tempDepList;
	}
	
	/**
	 * 点击树形视图查询结果(尾)部分
	 * @return
	 */
	private void findChild(String id, List<OrgDepartment> tempDepList) {
		String sql = "select keyid,departmentname,departmenton,(select departmentname from s_department where s.parent=keyid) parent from " + table + " s where parent = ? ";
		List<OrgDepartment> mList = find(sql, id);
		for (OrgDepartment p : mList) {
			tempDepList.add(p);
			findChild(p.getStr("KeyID"), tempDepList);
		}
	}
	
	/**
	 * 查询部门列表信息
	 * @return
	 */
	public Page<OrgDepartment> page(int pageNo, int pageSize) {
	//return this.paginate(pageNo, pageSize, "select *", "FROM " + table + " ORDER BY Departmenton");
	//select DepartmentName,DepartmentOn,(select DepartmentName from s_department where KeyID = s.parent) as parent FROM  s_department s ORDER BY Departmenton
		return this.paginate(pageNo, pageSize, "select keyid, DepartmentName,DepartmentOn,(select DepartmentName from s_department where KeyID = s.parent) as parent",
		"FROM " + table+ " s ORDER BY Departmenton");
	}
	
	/**
	 * 加载列表
	 * @return
	 */
	public List<OrgDepartment> loadSelect() {
		String sql = "select * from " + table + " group by departmentname";
		return find(sql);
	}

	public Page<OrgDepartment> page(int pageNo, int pageSize, String parent) {
		if (StringUtils.isEmpty(parent)) {
			return this.paginate(pageNo, pageSize, "select *", "FROM " + table
					+ " ORDER BY Departmenton");
		} else {
			return this.paginate(pageNo, pageSize, "select *", "FROM " + table
					+ " WHERE parent='" + parent
					+ "' ORDER BY Departmenton");
		}

	}
	
	public boolean checkDep(String[] keyids) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT * FROM ");
		sql.append(table);
		sql.append(" WHERE orgkeyid in ( ? ");
		for (String keyid : keyids) {
			sql.append(", ? ");
		}
		String sqlString = sql.substring(0, sql.length() - 4);
		sqlString = sqlString + ")";
		
		List<OrgDepartment> dep = find(sqlString, keyids);
		
		boolean a = dep.size() > 0 ? true : false;
		return a;
	}
	
	/**
	 * 检查部门有没有子部门。
	 * @return
	 */
	public boolean checkParentDep(String[] keyids) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT * FROM ");
		sql.append(table);
		sql.append(" WHERE parentid in ( ? ");
		for (String keyid : keyids) {
			sql.append(", ? ");
		}
		String sqlString = sql.substring(0, sql.length() - 4);
		sqlString = sqlString + ")";
		
		List<OrgDepartment> dep = find(sqlString, keyids);
		
		boolean a = dep.size() > 0 ? true : false;
		return a;
	}
	
	//2015.6.17添加
	/**
	 * 显示机构部门人员信息弹出框
	 * @param keyid
	 * @return
	 */
	public List<OrgDepartment> findDepTreePick(String keyid) {
		String sql="SELECT u.KeyID as id, u.DepartmentID as pid,u.RelaName as name FROM s_user u  WHERE DepartmentID <> '' UNION select  d.KeyID as id,case WHEN d.Parent='0' then d.orgCode ELSE  d.Parent END as pid,d.DepartmentName as name FROM s_department d where d.orgCode ='45e006c7ee804e36b331f2518c984b9c' and d.isAvailable = '0'";
		List<OrgDepartment> dl=find(sql);		
		return dl;
	}
	
	/**
	 * 根据keyid获得部门信息用于编辑
	 * @param keyid
	 * @return
	 */
	public OrgDepartment loadDepartmentDetail(String keyid){
		OrgDepartment department = new OrgDepartment();
		if (!StringUtils.isEmpty(keyid)){
			return findFirst("SELECT d.KeyID,d.Parent,(SELECT DepartmentName FROM s_department WHERE KeyID=d.Parent) as parentdepartmentname, d.DepartmentName, d.DepartmentOn, d.DepartmentWorkarea, d.DepartmentLeader, d.DepartmentPhone FROM s_department d WHERE d.KeyID='"+keyid+"';");
		}else{
			return department;
		}
	}

	public List<Record> loadOrgDep(String orgId) {
		String sql = "SELECT * FROM "+table+" WHERE orgkeyid = ? AND isAvailable='0' ORDER BY deporder ";
		return Db.find(sql,orgId);
	}

	public List<Record> loadOrgDepTree(String orgId) {
		String sql = "SELECT * FROM "+table+" WHERE orgkeyid = ? AND isAvailable='0' ORDER BY DepartmentOn ";
		return Db.find(sql,orgId);
	}

	public List<OrgDepTreeVO> loadOrgDepTree2(String orgId) {
		String sqld = "SELECT i.KeyID as keyid,i.deporder,i.ParentID as parentid,(select n.DepartmentName FROM " + OrgDepartment.table + " n where n.keyid=i.ParentID) as parentname, i.DepartmentName as departmentname," +
				"i.DepartmentOn as departmenton,i.orgkeyid as orgkeyid,i.orgName as orgname,i.DepartmentWorkarea as departmentworkarea," +
				"i.DepartmentLeader as departmentleader, i.DepartmentPhone as departmentphone,'isorgdep' as type FROM " + OrgDepartment.table + " i WHERE " +
				"i.orgkeyid =? AND i.isAvailable='0' order by deporder ";
			/*	"UNION " +
				"SELECT s.KeyID as keyid,'' as deporder, s.orgdepid as parentid, s.orgdepname as parentname,s.RelaName as departmentname,'' as  departmenton," +
				"s.orgid as orgkeyid,s.orgname  as orgname,'' as departmentworkarea,'' as departmentleader,'' as departmentphone,'isuser' as type " +
				"FROM s_user s WHERE s.orgid =? ORDER BY deporder ";*/
		//String sql = "SELECT * FROM "+table+" WHERE orgkeyid = ? AND isAvailable='0' ORDER BY DepartmentOn ";
		List<OrgDepartment> orgdepList = find(sqld,orgId);
		List<OrgDepTreeVO> voList = new ArrayList<OrgDepTreeVO>();
		for(OrgDepartment orgdep : orgdepList){
			String parentId = orgdep.getStr("parentid");
			if(parentId==null||"".equals(parentId)){
				parentId="";
			}
			if(orgId.equals(orgdep.getStr("orgkeyid"))&& "".equals(parentId)){
				voList.add(OrgDepTreeVO.markTree(orgdep, orgdepList));
			}
		}
		
		return voList;
	}
	public List<OrgDepartment> loadPickLeader(String orgId) {
		String sqld = "SELECT i.KeyID as keyid,i.deporder,null as userorder,i.ParentID as parentid,(select n.DepartmentName FROM " + OrgDepartment.table + " n where n.keyid=i.ParentID) as parentname, i.DepartmentName as departmentname," +
				"i.DepartmentOn as departmenton,i.orgkeyid as orgkeyid,i.orgName as orgname,'isorgdep' as type FROM " + OrgDepartment.table + " i WHERE " +
				"i.orgkeyid =? AND i.isAvailable='0' " +
				"UNION " +
				"SELECT s.KeyID as keyid,null as deporder,s.userorder,s.orgdepid as parentid, s.orgdepname as parentname,s.RelaName as departmentname,'' as  departmenton," +
				"s.orgid as orgkeyid,s.orgname  as orgname,'isuser' as type " +
				"FROM s_user s WHERE s.orgid =? AND s.LaborStatus in('在职在岗') AND  !INSTR(s.RelaName,'admin')>0 AND !INSTR(s.RelaName,'管理员')>0 ORDER BY deporder,userorder ";
		//String sql = "SELECT * FROM "+table+" WHERE orgkeyid = ? AND isAvailable='0' ORDER BY DepartmentOn ";
		List<OrgDepartment> orgdepList = find(sqld,orgId,orgId);
		return orgdepList;
	}
	
	/**
	 * 活动管理模块调用的添加人员方法　2016.1.18 wzf
	 * @param selectType
	 * @param vStr
	 * @return
	 */
	public List<OrgDepartment> loadPickPerson(String orgId) {
		String sqld = "SELECT i.KeyID as ID,i.ParentID as ParentCode,i.KeyID as RegionCode,i.DepartmentName as Region,'' as orgNum,'isorgdep' as type " +
				"FROM " + OrgDepartment.table + " i WHERE i.orgkeyid =? AND i.isAvailable='0' " +
				"UNION SELECT s.KeyID as ID,s.orgdepid as ParentCode,s.KeyID as RegionCode,s.RelaName as Region,'' as  orgNum,'isuser' as type " +
				"FROM s_user s WHERE s.orgid =?";
		List<OrgDepartment> orgdepList = find(sqld,orgId,orgId);
		return orgdepList;
		
	}
	
	public List<OrgDepartment> findByParentId(String parentid){
		String sql = "SELECT * FROM "+table+" WHERE parentid=? ";
		return find(sql,parentid);
	}
	public OrgDepartment findOrgDepDataByKeyid(String keyid){
		String sql = "SELECT i.keyid,i.parentid,i.deporder,(select n.departmentName FROM " + OrgDepartment.table + " n where n.keyid=i.parentid) as parentname," +
				"i.departmentName,i.departmenton,i.orgkeyid,(select o.org_fullname FROM " + Org.table + " o where o.keyid=i.orgkeyid) as orgname," +
				"i.isAvailable,i.relativeType,i.DepartmentWorkarea,i.DepartmentLeader,i.DepartmentPhone,i.type FROM " + OrgDepartment.table + " i WHERE KeyID=? ";
		return findFirst(sql, keyid);
	}

}
