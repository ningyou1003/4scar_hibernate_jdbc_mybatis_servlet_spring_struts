package com.emindsoft.zsj.docNotice.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "d_experience", pkName = "KeyId")
public class Experience extends BaseModel<Experience> {
	
	public static Experience dao = new Experience();
	public static String table = "d_experience";
	
	public Page<Experience> page(int pageNo, int pageSize, String regionCode) {
		if (!StringUtils.isEmpty(regionCode)) {
			String sql = "SELECT d.keyid,d.experiencename,s.relaname uploader,d.uploaddate";
			String sqlExpand = "FROM " + table + " d JOIN s_user s ON d.uploader=s.KeyID WHERE d.regionId='"+regionCode+"' ORDER BY d.uploaddate";
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
	
	public List<Document> getReferList(String activityId) {
		String sql = "";
		return null;
	}
	
//	public Page<Experience> pageList(){
//		
//	}

}
