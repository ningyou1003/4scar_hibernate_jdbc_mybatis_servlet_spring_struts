package com.emindsoft.zsj.appealalarm.model;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.vo.AlarmSelectVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "a_alarm",pkName = "KeyId")
public class Alarm extends BaseModel<Alarm>{

	private static final long serialVersionUID = 5225492541870213417L;
	public static Alarm dao = new Alarm();
	public static String table = "a_alarm";
	
	public boolean deleteAlarmByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

	public Alarm loadAlarmDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from a_alarm a where a.keyid='"+keyid+"' ");
		} else {
			return null;
		}
	}

	public Page<Alarm> page(int pageNo, int pageSize, AlarmSelectVO apVO,String fid,String chooseRegionId,String userRegionId) {
		if(!StringUtils.isEmpty(chooseRegionId)&& !"undefined".equals(chooseRegionId)&& !chooseRegionId.equals(userRegionId)){
			String sqlExceptSelect = " FROM a_alarm a where a.regionId='"+chooseRegionId+"' and a.status<>0 ";
			if (!StringUtils.isEmpty(apVO.getAlarmname())) {
				sqlExceptSelect += " and a.Title like '%" + apVO.getAlarmname()
						+ "%' ";
			}	
			sqlExceptSelect += " ORDER BY a.aTime DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select a.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=a.reportPersonId) as reporter",
							sqlExceptSelect);
		}else{
			String sqlExceptSelect = " FROM a_alarm a where a.regionId='"+userRegionId+"' and a.status<>0 ";
			if (!StringUtils.isEmpty(apVO.getAlarmname())) {
				sqlExceptSelect += " and a.Title like '%" + apVO.getAlarmname()
						+ "%' ";
			}	
			sqlExceptSelect += " ORDER BY a.aTime DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select a.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=a.reportPersonId) as reporter",
							sqlExceptSelect);
		}
		
	}
	
	public Page<Alarm> page(int pageNo, int pageSize){
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM a_alarm ORDER BY Atime DESC");
	}
	
	public Page<Alarm> page(int pageNo, int pageSize,String regioncode){
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM a_alarm where regionid='"+regioncode+"' and status<>0 ORDER BY Atime DESC");
	}
}
