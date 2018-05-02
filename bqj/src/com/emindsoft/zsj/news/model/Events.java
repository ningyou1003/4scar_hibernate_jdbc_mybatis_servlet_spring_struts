package com.emindsoft.zsj.news.model;

import org.apache.commons.lang3.StringUtils;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.vo.EventsSelectVO;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "n_events",pkName = "KeyId")
public class Events extends BaseModel<Events>{

	private static final long serialVersionUID = 6787281254072033821L;
	public static Events dao = new Events();
	public static String table = "n_events";
	public boolean deleteEventsByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
		
	}
	public Events loadEventsDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from n_events e where e.keyid='"+keyid+"'  ORDER BY e.month DESC,e.Time DESC");
		} else {
			return null;
		}
	}
	public Page<Events> page(int pageNo, int pageSize, EventsSelectVO evVO,String fid,String chooseRegionId,String userRegionId,String ispublic) {
		if(!StringUtils.isEmpty(chooseRegionId)&& !"undefined".equals(chooseRegionId)&& !chooseRegionId.equals(userRegionId)){
			String sqlExceptSelect = " FROM n_events e where e.regionId='"+chooseRegionId+"' and e.status<>0 ";
			if (!StringUtils.isEmpty(evVO.getName())) {
				sqlExceptSelect += " and e.Title like '%" + evVO.getName()
						+ "%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExceptSelect += "and e.ispublic='" +ispublic+"'";
			}
			sqlExceptSelect += " ORDER BY e.month DESC,e.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select e.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=e.reportPersonId) as reporter",
							sqlExceptSelect);
		}else{
			String sqlExceptSelect = " FROM n_events e where e.regionId='"+userRegionId+"' ";
			if (!StringUtils.isEmpty(evVO.getName())) {
				sqlExceptSelect += " and e.Title like '%" + evVO.getName()
						+ "%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExceptSelect += "and e.ispublic='" +ispublic+"'";
			}
			sqlExceptSelect += " ORDER BY e.month DESC,e.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select e.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=e.reportPersonId) as reporter",
							sqlExceptSelect);
		}
	}
	
	public Page<Events> pageEvents(int pageNo, int pageSize, EventsSelectVO evVO,String fid,String chooseRegionId,String userRegionId) {
		if(!StringUtils.isEmpty(chooseRegionId)&& !"undefined".equals(chooseRegionId)&& !chooseRegionId.equals(userRegionId)){
			String sqlExceptSelect = " FROM n_events e where e.regionId='"+chooseRegionId+"' ";
			sqlExceptSelect += " ORDER BY e.month DESC,e.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select *",
							sqlExceptSelect);
		}else{
			String sqlExceptSelect = " FROM n_events e where e.regionId='"+userRegionId+"' ";
			sqlExceptSelect += " ORDER BY e.month DESC,e.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select *",
							sqlExceptSelect)
							;
		}
	}
	public Page<Events> pageEventsWeb(int pageNo, int pageSize) {
		String sqlExceptSelect = " FROM n_events e where e.IsPublic = 1 ";
		sqlExceptSelect += " ORDER BY e.Time DESC,e.month DESC";
		return this.paginate(pageNo,pageSize,"select *",sqlExceptSelect);
	}
	public Page<Events> pageApp(int page, int pageSize,String regionId) {
		String sqlExceptSelect = " FROM n_events e where e.IsPublic = 1 and e.regionId='" +regionId+"' ";
		sqlExceptSelect += " ORDER BY e.month DESC,e.Time DESC";
		return this.paginate(page,pageSize,"select *",sqlExceptSelect);
	}
	
	/**
	 * 分页
	 * 按区域返回数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Events> page(int pageNo,int pageSize,String regioncode) {
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM n_events WHERE regionid='"+regioncode+"' and ispublic=1 ORDER BY month DESC,Time DESC");
	}
}
