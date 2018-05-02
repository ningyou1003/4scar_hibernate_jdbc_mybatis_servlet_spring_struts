package com.emindsoft.zsj.zoneDefence.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

/**
 * 区域联防-制度类
 * @author root
 *
 */
@TableBind(tableName = "zd_file", pkName = "KeyId")
public class ZdFile extends BaseModel<ZdFile>{
	public static ZdFile dao = new ZdFile();
	public static String table = "zd_file";
	
	public Page<ZdFile> page(int pageNo, int pageSize, String userId ) {
		return this.paginate(pageNo, pageSize, "select keyid,filename,brief,uploadTime,(SELECT relaname FROM s_user WHERE keyid='"+userId+"') uploader ", " FROM " + table
				+ " ORDER BY uploadTime desc");

	}
	
	/**
	 * 根据参数keyid删除本表数据
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

}
