package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import org.apache.commons.lang3.StringUtils;

@TableBind(tableName = "s_org_temp", pkName = "KeyID")
public class OrgTemp extends BaseModel<OrgTemp> {

	private static final long serialVersionUID = -2260776342494897803L;
	public static OrgTemp dao = new OrgTemp();
	public static String table = "s_org_temp";


	/**
	 * 点击机构名称显示的详细信息页面的方法,需要一个参数keyid
	 * @param keyid
	 * @return
	 */
	public OrgTemp loadOrgDetail(String keyid){
		if(!StringUtils.isEmpty(keyid)){
			return findFirst("SELECT o.KeyID ,o.org_fullname, o.org_fullname_abroad, " +
					"o.org_shutcut, o.org_shutcut_abroad, o.status, " +
					"o.orgnum, o.type, o.address, o.workarea,o.zipcode, " +
					"o.phone,o.portraiture, o.website, o.email, o.webchat, o.twitter, o.regioncode," +
					"(SELECT Region FROM " + Area.table + "  WHERE RegionCode=o.regioncode) as regionname, o.isvailable, " +
					"o.supname,o.supname_e,o.supshortname,o.supshortname_e,o.suprelation," +
					"o.subname,o.subname_e,o.subshortname,o.subshortname_e,o.subrelation " +
					"FROM "+table+" o WHERE o.KeyID='"+keyid+"' ORDER BY org_fullname;");
		}else{
			return null;
		}
	}

}
