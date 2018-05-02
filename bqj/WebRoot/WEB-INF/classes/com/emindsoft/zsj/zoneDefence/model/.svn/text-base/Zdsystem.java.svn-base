package com.emindsoft.zsj.zoneDefence.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

/**
 * 区域联防-制度类
 * @author root
 *
 */
@TableBind(tableName = "zd_system", pkName = "KeyId")
public class Zdsystem extends BaseModel<Zdsystem>{
	public static Zdsystem dao = new Zdsystem();
	public static String table = "zd_system";
	
	public Page<Zdsystem> page(int pageNo, int pageSize ) {
		return this.paginate(pageNo, pageSize, "select keyid,title,publishTime ", " FROM " + table
				+ " ORDER BY publishTime desc");

	}
	
	/**
	 * 根据参数keyid删除本表数据
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

}
