package com.emindsoft.zsj.docNotice.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "d_document", pkName = "KeyId")
public class Document extends BaseModel<Document>{
	public static Document dao = new Document();
	public static String table = "d_document";
	
	public Page<Document> page(int pageNo, int pageSize, String regionCode) {
		if (!StringUtils.isEmpty(regionCode)) {
			String sql = "SELECT d.keyid,d.docname,s.relaname uploader,d.uploaddate";
			String sqlExpand = "FROM " + table + " d JOIN s_user s ON d.uploader=s.KeyID WHERE d.regionId='"+regionCode+"' ORDER BY d.uploaddate desc ";
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
	
	public List<Document> getCountByTime(String regionid){
		String sql="SELECT  COUNT(*) as `status`,uploaddate FROM d_document WHERE regionId='"+regionid+"' GROUP BY uploaddate ORDER BY uploaddate ";
		
		return find(sql);
		
	}
	

}
