package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 角色model
 * 
 * @author ym
 */

@TableBind(tableName = "s_role", pkName = "KeyId")
// @TableBind注解标签，本类对应的是数据库的表s_roles，pkName主键
public class Role extends BaseModel<Role> {
	public static Role dao = new Role(); // 定义查询对象dao
	public static String table = "s_role";// 把对应的表名赋予给变量table，方便调用

	/**
	 * 角色的分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Role> page(int pageNo, int pageSize) {
		return this.paginate(pageNo, pageSize, "select *", "FROM " + table
				+ " ORDER BY OrderID");
	}

	/**
	 * 根据选择的区域查询对应的角色
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param regioncode
	 * @return
	 */
	public Page<Role> page(int pageNo, int pageSize,String regionCode) {
		String s1 = "";
		StringBuffer sb = new StringBuffer();
		if(!"".equals(regionCode)&& regionCode!=null){
			s1 += ",(SELECT region FROM " + Area.table + "  WHERE RegionCode='" + regionCode + "') as region ";
			sb.append(" AND RegionCode='"+regionCode+"'");
		}
		return this.paginate(pageNo, pageSize, "select *" + s1, "FROM " + table +" where 1=1 "+sb.toString()+""
				+ " ORDER BY OrderID");
	}
	
	/**
	 * 删除方法
	 * 
	 * @param keyids
	 * @return 直接调用父类的方法
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

	/**
	 * 查询所有的角色信息
	 * 
	 * @return
	 */
	public List<Role> loadRole() {
		return findAll();
	}

	/**
	 * 查询某地区的角色信息
	 * @param regionCode
	 * @return
	 */
	public List<Role> loadRolePick(String regionCode) {
		String sql = "select * from " + table + " where regioncode = ? order by orderId";
		return this.find(sql, regionCode);
	}

	/**
	 * 查询用户的所属角色
	 * @param currentUserId
	 * @return 
	 */
	public List<Role> loadRolesForUser(String keyid) {
		if(!StringUtils.isEmpty(keyid)){
			return find("SELECT r.KeyId,r.Name FROM " + Role.table + " r, s_role_user ru WHERE r.KeyID=ru.roleid AND ru.userid=? LIMIT 1", keyid);
		}
		return null;
	}
	
	/**
	 * 查询用户的所属角色
	 * @param currentUserId
	 * @return 
	 */
	public Role findRolesByUser(String keyid) {
		if(!StringUtils.isEmpty(keyid)){
			return findFirst("SELECT r.* FROM " + Role.table + " r, s_role_user ru WHERE r.KeyID=ru.roleid AND ru.userid=? LIMIT 1", keyid);
		}
		return null;
	}
	
	public Role loadUserRole(String keyid){
		if(!StringUtils.isEmpty(keyid)){
			return findFirst("SELECT r.KeyId,r.Name, r.level FROM " + Role.table + " r, s_role_user ru WHERE r.KeyID=ru.roleid AND ru.userid=? LIMIT 1", keyid);
		}
		return null;
	}

	/**
	 * 根据区域和角色名称查询角色
	 * @param name
	 * @param regioncode
	 * @return
	 */
	public List<Role> findByNameAndRegioncode(String name, String regioncode) {
		List<Role> roleList = find("SELECT * FROM " + Role.table + " WHERE Name=? AND RegionCode=?", name, regioncode);
		return roleList;
	}
	/**
	 * editdata根据keyid进行查询
	 * @return
	 */
	public Role findByKeyid(String keyid) {
		String sql = "select *,'' as par from " + table + " s," + Area.table + " a where keyid = ?";
		return findFirst(sql,keyid);
	}
	/**
	 * 根据用户选择的工作单位(机构)信息，查询该单位(机构)下所在的区域的角色
	 * */
	public List<Role> loadRolesForUserRegion(String regionid) {
		String sql = "SELECT * FROM "+table+" WHERE RegionCode=?";
		return find(sql,regionid);
	}

	/**
	 * 根据等级和角色名称查询角色
	 * @param name
	 * @param level
	 * @return
	 */
	public List<Role> findByNameAndLevel(String name, int level) {
		List<Role> roleList = find("SELECT * FROM " + table + " WHERE Name=? AND level=?", name, level);
		return roleList;
	}

	public List<Role> loadRoleByLevel(String roleLevel) {
		if(!StringUtils.isEmpty(roleLevel)){
			String sql = "SELECT KeyID,Name, OrderID,level FROM "+table + " WHERE level="+roleLevel;
			return find(sql);
		}
		return null;
	}
	
}
