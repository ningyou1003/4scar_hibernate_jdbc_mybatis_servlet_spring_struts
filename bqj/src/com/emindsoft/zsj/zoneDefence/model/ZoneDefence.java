package com.emindsoft.zsj.zoneDefence.model;

import org.apache.commons.lang3.StringUtils;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "z_zonedefence", pkName = "KeyId")
public class ZoneDefence extends BaseModel<ZoneDefence>{
	public static ZoneDefence dao = new ZoneDefence();
	public static String table = "z_zonedefence";
	
	public Page<ZoneDefence> page(int pageNo, int pageSize , String regionCode) {
		 if(!StringUtils.isEmpty(regionCode)) {
			String sql = "select keyid,title,createTime,status";
			String sqlExpand = "FROM " + table + " WHERE regionId='" + regionCode + "' ORDER BY createTime desc";
			return this.paginate(pageNo, pageSize, sql, sqlExpand);
		} 
		return null;
	}
	
	/**
	 * 根据参数keyid删除本表数据
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

	public Page<ZoneDefence> page(int pageNo, int pageSize, String regionCode,
			String title, String ispublic) {
		if(!StringUtils.isEmpty(regionCode)) {
			String sql = "SELECT zdf.keyid, zdf.title, zdf.ispublic, zdf.createtime, input.RelaName inputperson, report.RelaName reportperson";
			String sqlExpand = "FROM " + table + " zdf LEFT JOIN s_user input ON zdf.createuserid=input.keyid LEFT JOIN s_user report on zdf.reportpersonid=report.keyid WHERE zdf.regionId='" + regionCode +"' and zdf.status<>0";
			if(StringUtils.isNotEmpty(title)){
				sqlExpand += "AND zdf.title like'%" +title+"%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExpand += "AND zdf.ispublic='" +ispublic+"'";
			}
			sqlExpand +=" ORDER BY zdf.createTime desc";
			return this.paginate(pageNo, pageSize, sql, sqlExpand);
		} 
		return null;
	}
}
