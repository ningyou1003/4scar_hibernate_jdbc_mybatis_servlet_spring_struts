package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.system.vo.OrgSelectVO;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@TableBind(tableName = "s_org", pkName = "KeyID")
public class Org extends BaseModel<Org> {

	private static final long serialVersionUID = -2260776342494897802L;
	public static Org dao = new Org();
	public static String table = "s_org";
	
	/**
	 * 机构分页带条件查询(机构列表页面也可以调用)
	 * @param pageNo
	 * @param pageSize
	 * @param orgVO
	 * @return
	 */
	public Page<Org> page(int pageNo, int pageSize,OrgSelectVO orgVO,String currentUserRegion){
		
		String sqlExceptSelect =" FROM " + Org.table + " o where 1=1 and o.isvailable = '0' ";
		
		if(!StringUtils.isEmpty(orgVO.getOrgname())){
			sqlExceptSelect += " and o.org_fullname like '%" + orgVO.getOrgname()+ "%' ";
		}
		if (!StringUtils.isEmpty(orgVO.getOrgcode())) {
			sqlExceptSelect += " and o.regioncode='" + orgVO.getOrgcode()+ "' ";
		}else{
			sqlExceptSelect += " and o.regioncode='" +currentUserRegion+ "' ";
		}
		
		sqlExceptSelect += " ORDER BY o.regioncode ";
		return this.paginate(pageNo, pageSize, "SELECT o.KeyID,o.regioncode, o.org_fullname, o.workarea, " +
				"o.address, o.phone, o.status,o.supname,o.suprelation, " +
				"(SELECT Region FROM " + Area.table + "  WHERE RegionCode=o.regioncode) as regionname, " +
				"o.type ", sqlExceptSelect);
	}

	/**
	 * 删除机构(更新状态为不可用)
	 * 
	 * @param keyids
	 * @return 调用父类方法
	 */
	public void deleteOrgByIds(String[] keyids) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE ");
		sql.append(table);
		sql.append(" SET isvailable = 1");
		sql.append(" WHERE KeyId in ( ? ");
		for (String keyid : keyids) {
			sql.append(", ? ");
		}
		String sqlString = sql.substring(0, sql.length() - 4);
		sqlString = sqlString + ")";

		Db.update(sqlString, keyids);
	}
	
	/**
	 * 点击机构名称显示的详细信息页面的方法,需要一个参数keyid
	 * @param keyid
	 * @return
	 */
	public Org loadOrgDetail(String keyid){
		if(!StringUtils.isEmpty(keyid)){
			return findFirst("SELECT o.KeyID ,o.org_fullname, o.org_fullname_abroad, " +
					"o.org_shutcut, o.org_shutcut_abroad, o.status, " +
					"o.orgnum, o.type, o.address, o.workarea,o.zipcode, " +
					"o.phone,o.portraiture, o.website, o.email, o.webchat, o.twitter, o.regioncode," +
					"(SELECT Region FROM " + Area.table + "  WHERE RegionCode=o.regioncode) as regionname, o.isvailable," +
					"o.supname,o.supname_e,o.supshortname,o.supshortname_e,o.suprelation," +
					"o.subname,o.subname_e,o.subshortname,o.subshortname_e,o.subrelation " +
					"FROM " + Org.table + " o WHERE o.KeyID='"+keyid+"' ORDER BY org_fullname;");
		}else{
			return null;
		}
	}
/**
 * supname:"",
		supname_e:"",
		supshortname:"",
		supshortname_e:"",
		suprelation:"",
		subname:"",
		subname_e:"",
		subshortname:"",
		subshortname_e:"",
		subrelation:"",
 * */
	public List<Org> loadOrgTree(String regionCode) {
//		String region_seachcode = Area.dao.getSeachCode(currentUserId);
		String sql = "select * from " + table +" where regioncode like '"+regionCode+"%'";
		return find(sql);
	}
	
	public List<Org> findOrgRegionCode(){
		String sql = "SELECT regioncode FROM " + Org.table + " ORDER BY regioncode";
		return find(sql);
	}

	public Org findByOrgNum(String orgNum) {
		String sql = "SELECT * FROM " + Org.table + " where orgNum=?";
		return findFirst(sql, orgNum);
	}

	//所在区域的下级区域的机构
	public List<Org> findSubOrg(String regionCode){
		if(StrKit.isBlank(regionCode)){
			return new ArrayList<Org>();
		}

		regionCode = StringUtils.reverse(regionCode);
		regionCode = StringUtils.stripStart(regionCode, "0");
		regionCode = StringUtils.reverse(regionCode);

		String sql = "select keyId, regionCode, org_fullname from " + table + " where regionCode like ?";
		List<Org> list = find(sql, regionCode+"%");
		return list;
	}

}
