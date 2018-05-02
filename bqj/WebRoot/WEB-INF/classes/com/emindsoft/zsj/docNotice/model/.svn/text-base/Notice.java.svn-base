package com.emindsoft.zsj.docNotice.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "d_notice", pkName = "KeyId")
public class Notice extends BaseModel<Notice>{
	public static Notice dao = new Notice();
	public static String table = "d_notice";
	
	public Page<Notice> page(int pageNo, int pageSize, String regionCode ) {	
		if (!StringUtils.isEmpty(regionCode)) {
			String sql = " SELECT n.keyid, n.title, n.publishTime, u.relaname  publisher";
			String sqlExpand = "FROM " + table + " n JOIN s_user u ON n.publisherId=u.KeyID WHERE n.regionId='"+regionCode+"' ORDER BY n.publishTime desc";
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

	public List<Notice> getCountByTime(String regionid){
		String sql="SELECT  COUNT(*) as `status`,publishTime FROM d_notice WHERE regionId='"+regionid+"' GROUP BY publishTime ORDER BY publishTime ";
		
		return find(sql);
		
	}
}
