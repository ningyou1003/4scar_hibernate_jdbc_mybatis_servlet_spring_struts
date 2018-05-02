package com.emindsoft.zsj.news.model;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.vo.LeaderSelectVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "n_leader",pkName = "KeyId")
public class Leader extends BaseModel<Leader>{
	private static final long serialVersionUID = -4198436947071664037L;
	public static Leader dao = new Leader();
	public static String table = "n_leader";
	
	public boolean deleteLeaderByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

	public Leader loadLeaderDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from n_leader l where l.keyid='"+keyid+"' ");
		} else {
			return null;
		}
	}
	
	public Page<Leader> page(int pageNo, int pageSize, LeaderSelectVO leaVO,String fid,String chooseRegionId,String userRegionId,String ispublic) {
		if(!StringUtils.isEmpty(chooseRegionId)&& !"undefined".equals(chooseRegionId)&& !chooseRegionId.equals(userRegionId)){
			String sqlExceptSelect = " FROM n_leader l where l.regionId='"+chooseRegionId+"' and l.status<>0 ";
			if (!StringUtils.isEmpty(leaVO.getName())) {
				sqlExceptSelect += " and l.Title like '%" + leaVO.getName()
						+ "%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExceptSelect += "and l.ispublic='" +ispublic+"'";
			}
			sqlExceptSelect += " ORDER BY l.title desc,l.time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select l.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=l.reportPersonId) as reporter",
							sqlExceptSelect);
		}else{
			String sqlExceptSelect = " FROM n_leader l where l.regionId='"+userRegionId+"' ";
			if (!StringUtils.isEmpty(leaVO.getName())) {
				sqlExceptSelect += " and l.Title like '%" + leaVO.getName()
						+ "%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExceptSelect += "and l.ispublic='" +ispublic+"'";
			}
			sqlExceptSelect += " ORDER BY l.title desc,l.time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select l.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=l.reportPersonId) as reporter",
							sqlExceptSelect);
		}
	}
}
