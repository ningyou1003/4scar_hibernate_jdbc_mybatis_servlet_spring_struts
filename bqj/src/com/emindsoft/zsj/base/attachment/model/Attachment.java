package com.emindsoft.zsj.base.attachment.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by ym on 15-3-10.
 */
@TableBind(tableName = "base_attachment", pkName = "KeyId")
public class Attachment extends BaseModel<Attachment> {

	public static Attachment dao = new Attachment();
	public static String table = "base_attachment";

	public Attachment findById(String attachmentId){
		Attachment info = Attachment.dao.findFirst("select * from " + Attachment.table + " where KeyId=?", attachmentId);
		return info;
	}

	public List<Attachment> findAtt(String relateType, String relateId){
		return find("select * from " + table + " where relateType=? and relateId=?", relateType, relateId);
	}
	
	public Attachment findFirstAtt(String relateType, String relateId){
		return findFirst("select * from " + table + " where relateType=? and relateId=?", relateType, relateId);
	}

	public List<Attachment> findAtt(String relateId){
		return find("select * from " + table + " where relateId=?", relateId);
	}
	
	/**
	 * 根据类型、关联Id数组查询
	 * @param RelateIds
	 * @return
	 */
	public List<Attachment> findByRelateIds(String[] RelateIds, String RelateType) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select path,RelateId from ");
		sqlBuffer.append(table);
		sqlBuffer.append(" WHERE RelateType = ");
		sqlBuffer.append("'"+RelateType+"'");
		sqlBuffer.append(" and RelateId in ( ? ");
		for(String relateId: RelateIds) {
			sqlBuffer.append(", ? ");
		}
		String sqlString = sqlBuffer.substring(0, sqlBuffer.length() - 4 );
		sqlString += ") ";
		return find(sqlString, RelateIds);
	}
}
