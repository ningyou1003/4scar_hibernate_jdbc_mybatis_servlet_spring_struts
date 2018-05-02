package com.emindsoft.zsj.culture.model;

import java.util.ArrayList;
import java.util.List;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "p_silhouette", pkName = "KeyID")
public class Silhouette extends BaseModel<Silhouette> {
	public static Silhouette dao = new Silhouette();
	public static String table = "p_silhouette";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Silhouette> page(int pageNo,int pageSize,String createUserId,String selectRegionId,String year,cultureSelectVO sv) {
		String sql = "select *,(SELECT s.relaname FROM s_user s WHERE s.KeyID = b.createUserId) as user,"
				+"(SELECT s.relaname FROM s_user s WHERE s.keyid = b.reportPersonId) as reportPerson ";
		
		String sqlExceptSelect = "from " + table + " b where 1 = 1 and b.status<>0 and b.year = '"+ year +"' " +
				"and b.regionId = '" + selectRegionId + "' ";
		
		List<String> parasList = new ArrayList<String>();
		
		if(sv.getTitle() != null) {
			sqlExceptSelect += "and b.title like ? ";
			parasList.add("%"+sv.getTitle()+"%");
		}
		if(sv.getIsPublic() != null) {
			sqlExceptSelect += "and b.ispublic = ? ";
			parasList.add(sv.getIsPublic());
		}
		
		sqlExceptSelect += "order by b.releasetime desc";
		
		if(parasList.size() != 0) {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect, parasList.toArray());
		}else {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect);
		}
	}
	
	/**
	 * 分页
	 * 按年份返回数据
	 * @param pageNo
	 * @param pageSize
	 * @param year
	 * @return
	 */
	public Page<Silhouette> page(int pageNo,int pageSize,String year) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(SELECT s.relaname FROM s_user s WHERE s.KeyID = createUserId) as user ", 
				" from " + table + " where 1 = 1 and ispublic = '1' and year = '"+year+"' order by releasetime desc ");
	}
	
	/**
	 * 根据keyids删除数据
	 * @param keyids
	 * @return
	 */
	public boolean deleteById(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}
	
	/**
	 * 根据year删除数据
	 * @param year
	 * @return
	 */
	public boolean deleteByYear(String year) {
		int i = Db.update("DELETE FROM " + table + " WHERE Year = ? ", year);
		return i > 0;
	}

}
