package com.emindsoft.zsj.record.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.vo.PointsSelectVO;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "c_record",pkName = "KeyId")
public class Record extends BaseModel<Record>{

	private static final long serialVersionUID = 3194539344276842418L;
	public static Record dao = new Record();
	public static String table = "c_record";
	
	public boolean deletePointsByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
		
	}
	
	public Record loadPointsDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from c_record c where c.keyid='"+keyid+"' ");
		} else {
			return null;
		}
	}
	
	public Page<Record> page(int pageNo, int pageSize, PointsSelectVO pointsVO,String fid, String type) {
		String sqlExceptSelect = " FROM c_record c where (c.status=1 or c.CreateUserId='"+fid+"') and c.Type='"+type+"' ";
		if (!StringUtils.isEmpty(pointsVO.getName())) {
			sqlExceptSelect += " and c.Title like '%" + pointsVO.getName()
					+ "%' ";
		}
		/*if(!StringUtils.isEmpty(type)){
			sqlExceptSelect += "and c.Type='"+type+"' ";
		}*/
		/*if(!StringUtils.isEmpty(fid)){
			sqlExceptSelect += "or c.CreateUserId='"+fid+"' ";
		}*/
		
		sqlExceptSelect += " ORDER BY c.Time DESC";
		return this
				.paginate(
						pageNo,
						pageSize,
						"select *",
						sqlExceptSelect);
	}
	
	public List<Record> findByMapRecordId(String mapId){
		String sql = "SELECT * FROM " + table +" WHERE mapRecordId='" + mapId + "' ORDER BY num";
		return find(sql);
	}

}
