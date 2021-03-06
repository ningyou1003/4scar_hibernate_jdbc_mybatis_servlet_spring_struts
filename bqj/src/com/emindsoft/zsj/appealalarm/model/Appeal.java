package com.emindsoft.zsj.appealalarm.model;

import org.apache.commons.lang3.StringUtils;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.vo.AppealSelectVO;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "a_appeal",pkName = "KeyId")
public class Appeal extends BaseModel<Appeal>{

	private static final long serialVersionUID = 4389246249517146502L;
	public static Appeal dao = new Appeal();
	public static String table = "a_appeal";
	
	public boolean deleteAppealByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

	public Appeal loadAppealDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from a_appeal a where a.keyid='"+keyid+"' ");
		} else {
			return null;
		}
	}

	public Page<Appeal> page(int pageNo, int pageSize, AppealSelectVO apVO,String fid,String chooseRegionId,String userRegionId) {
		if(!StringUtils.isEmpty(chooseRegionId)&& !"undefined".equals(chooseRegionId)&& !chooseRegionId.equals(userRegionId)){
			String sqlExceptSelect = " FROM a_appeal a where a.regionId='"+chooseRegionId+"' and a.status<>0 ";
			if (!StringUtils.isEmpty(apVO.getAppealname())) {
				sqlExceptSelect += " and a.Title like '%" + apVO.getAppealname()
						+ "%' ";
			}	
			sqlExceptSelect += " ORDER BY a.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select a.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=a.reportPersonId) as reporter",
							sqlExceptSelect);
		}else{
			String sqlExceptSelect = " FROM a_appeal a where a.regionId='"+userRegionId+"' and a.status<>0 ";
			if (!StringUtils.isEmpty(apVO.getAppealname())) {
				sqlExceptSelect += " and a.Title like '%" + apVO.getAppealname()
						+ "%' ";
			}	
			sqlExceptSelect += " ORDER BY a.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select a.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=a.reportPersonId) as reporter",
							sqlExceptSelect);
		}
	}
	
	public Page<Appeal> page(int pageNo, int pageSize){
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM a_appeal ORDER BY Time DESC");
	}
	
	public Page<Appeal> page(int pageNo, int pageSize,String regioncode){
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM a_appeal where regionid='"+regioncode+"' and status<>0 ORDER BY Time DESC");
	}
}
