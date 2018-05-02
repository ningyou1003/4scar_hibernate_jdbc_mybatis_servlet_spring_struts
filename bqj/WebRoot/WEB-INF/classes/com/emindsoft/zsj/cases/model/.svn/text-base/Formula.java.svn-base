package com.emindsoft.zsj.cases.model;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.culture.model.Silhouette;
import com.emindsoft.zsj.vo.FormulaSelectVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "c_formula",pkName = "KeyId")
public class Formula extends BaseModel<Formula>{

	private static final long serialVersionUID = 1412598879868614600L;
	public static Formula dao = new Formula();
	public static String table = "c_formula";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Formula> page(int pageNo,int pageSize,String createUserId,String selectRegionId,String year,String ispublic,String ftype) {
		return this.paginate(pageNo, pageSize,"select b.KeyID,b.title,b.status,b.ispublic,b.releasetime,b.reporttime,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=b.reportpersonid) as reporter ",
				" from " + table + " b where 1 = 1 and b.year = '"+ year +"' and b.regionId = '" + selectRegionId + "' or b.regionId = (SELECT f.regionId FROM flow_todo f WHERE f.status = 1 and f.FlowId = b.keyID ) order by b.releasetime desc");
	}
	
	public Page<Formula> newPage(int pageNo,int pageSize,FormulaSelectVO formulaVO,String createUserId,String selectRegionId,String year,String ispublic,String ftype) {
		//return this.paginate(pageNo, pageSize,"select b.KeyID,b.title,b.status,b.ispublic,b.releasetime,b.reporttime,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=b.reportpersonid) as reporter ",
		//		" from " + table + " b where 1 = 1 and b.year = '"+ year +"' and b.regionId = '" + selectRegionId + "' or b.regionId = (SELECT f.regionId FROM flow_todo f WHERE f.status = 1 and f.FlowId = b.keyID ) order by b.releasetime desc");
	
		String sqlExceptSelect = " FROM c_formula b where 1 = 1 and b.year = '"+ year +"' and ( b.regionId = '" + selectRegionId + "' or b.regionId = (SELECT f.regionId FROM flow_todo f WHERE f.status = 1 and f.FlowId = b.keyID ) )";
		
		if (!StringUtils.isEmpty(formulaVO.getName())) {
			sqlExceptSelect += " and b.Title like '%" + formulaVO.getName()
					+ "%' ";
		}
		if(StringUtils.isNotEmpty(ispublic)){
			sqlExceptSelect += "and b.ispublic='" +ispublic+"'";
		}
		if(StringUtils.isNotEmpty(ftype)){
			sqlExceptSelect += "and b.ftype='" +ftype+"'";
		}
		sqlExceptSelect += " ORDER BY b.releasetime DESC";
		return this
				.paginate(
						pageNo,
						pageSize,
						"select b.KeyID,b.title,b.status,b.ispublic,b.releasetime,b.reporttime,b.ftype,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=b.reportpersonid) as reporter ",
						sqlExceptSelect);
	}

	public boolean deleteFormulaByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
		
	}
	
	public Page<Formula> page(int pageNo,int pageSize){
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM c_formula WHERE IsPublic = 1 ORDER BY ReleaseTime DESC");
	}
	
	public Page<Formula> newPageForAndroid(int pageNo,int pageSize,String regioncode,String year){
		return this.paginate(pageNo, pageSize, "SELECT KeyId,Title,ReleaseTime"," FROM c_formula WHERE IsPublic = 1 AND Year = '"+year+"' AND regionId = '"+regioncode+"' ORDER BY ReleaseTime DESC");
	}

	public Formula loadFormulaDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from c_formula f where f.keyid='"+keyid+"' ");
		} else {
			return null;
		}
	}
}
