package com.emindsoft.zsj.matter;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "m_matter", pkName = "KeyId")
public class Matter extends BaseModel<Matter>{
	public static Matter dao = new Matter();
	public static String table = "m_matter";
	
	public Page<Matter> page(int pageNo, int pageSize, String userId,
			String status) {
		String sql = "SELECT m.*, u.UserName ";
		String sqlExpand = "FROM m_matter m, s_user u WHERE m.createUserId=u.keyid AND m.createUserId='" + userId + "' AND m.status='" + status + "'";
		return this.paginate(pageNo, pageSize, sql, sqlExpand);
	}
	
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}
}
