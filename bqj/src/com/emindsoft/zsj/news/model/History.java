package com.emindsoft.zsj.news.model;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.vo.EventsSelectVO;
import com.emindsoft.zsj.vo.HistorySelectVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "n_history",pkName = "KeyId")
public class History extends BaseModel<History>{
	private static final long serialVersionUID = -2011832282813038970L;
	public static History dao = new History();
	public static String table = "n_history";
	
	public boolean deleteHistoryByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

	public History loadHistoryDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from n_history h where h.keyid='"+keyid+"' ORDER BY h.month DESC, h.Time DESC");
		} else {
			return null;
		}
	}

	public Page<History> page(int pageNo, int pageSize, HistorySelectVO hisVO,String fid,String chooseRegionId,String userRegionId,String ispublic) {
		if(!StringUtils.isEmpty(chooseRegionId)&& !"undefined".equals(chooseRegionId)&& !chooseRegionId.equals(userRegionId)){
			String sqlExceptSelect = " FROM n_history h where h.regionId='"+chooseRegionId+"' and h.status<>0 ";
			if (!StringUtils.isEmpty(hisVO.getName())) {
				sqlExceptSelect += " and h.Title like '%" + hisVO.getName()
						+ "%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExceptSelect += "and h.ispublic='" +ispublic+"'";
			}
			sqlExceptSelect += " ORDER BY h.month DESC, h.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select h.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=h.reportPersonId) as reporter",
							sqlExceptSelect);
		}else{
			String sqlExceptSelect = " FROM n_history h where h.regionId='"+userRegionId+"' ";
			if (!StringUtils.isEmpty(hisVO.getName())) {
				sqlExceptSelect += " and h.Title like '%" + hisVO.getName()
						+ "%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExceptSelect += "and h.ispublic='" +ispublic+"'";
			}
			sqlExceptSelect += " ORDER BY h.month DESC, h.Time DESC";
			return this
					.paginate(
							pageNo,
							pageSize,
							"select h.*,(SELECT u.RelaName FROM s_user u WHERE u.KeyID=h.reportPersonId) as reporter",
							sqlExceptSelect);
		}
	}
	
	/**
	 * 分页
	 * 按区域返回数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<History> page(int pageNo,int pageSize,String regioncode) {
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM n_history WHERE regionid='"+regioncode+"' and ispublic=1 ORDER BY month DESC, Time DESC");
	}
	
}
