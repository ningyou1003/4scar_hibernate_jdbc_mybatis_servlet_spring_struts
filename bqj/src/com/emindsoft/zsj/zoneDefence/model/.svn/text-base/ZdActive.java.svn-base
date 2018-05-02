package com.emindsoft.zsj.zoneDefence.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

/**
 * 区域联防-活动类
 * @author root
 *
 */
@TableBind(tableName = "zd_active", pkName = "KeyId")
public class ZdActive extends BaseModel<ZdActive>{
	public static ZdActive dao = new ZdActive();
	public static String table = "zd_active";
	
	public Page<ZdActive> page(int pageNo, int pageSize ) {
		return this.paginate(pageNo, pageSize, "select keyid,activeName,startTime, endTime,holdOrganization,joinOrganization,content ", " FROM " + table
				+ " ORDER BY startTime desc");

	}
	
	/**
	 * 根据参数keyid删除本表数据
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

}
