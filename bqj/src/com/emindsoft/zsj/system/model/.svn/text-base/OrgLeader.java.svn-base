package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@TableBind(tableName = "s_org_user", pkName = "KeyID")
public class OrgLeader extends BaseModel<OrgLeader>{

	private static final long serialVersionUID = -1677313818726256856L;
	public static OrgLeader dao = new OrgLeader();
	public static String table = "s_org_user";
		
	/**
	 * load出领导信息
	 * @param keyid
	 * @return
	 */
	public List<OrgLeader> loadLeader(String keyid){//2015.6.24
		if(!StringUtils.isEmpty(keyid)){
			return find("SELECT u.KeyID,u.RelaName FROM s_user u ,s_org_user o WHERE u.KeyID=o.userid AND o.orgid='"+keyid+"' ");			
	}
		return null;
	}
	
	/**
	 * 检查有没有相同的用户
	 * @return
	 */
	public boolean checkUserid(String oid,String uid){		
		List<OrgLeader> leader= find("SELECT * FROM s_org_user o WHERE orgid='"+oid+"' AND userid='"+uid+"' ");
		return leader.size() > 0;
		/*boolean a = leader.size() > 0 ? true : false;
		return a;*/
	}
}