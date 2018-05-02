package com.emindsoft.zsj.culture.model;

import java.util.ArrayList;
import java.util.List;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "p_bulletin", pkName = "KeyID")
public class Bulletin extends BaseModel<Bulletin> {
	public static Bulletin dao = new Bulletin();
	public static String table = "p_bulletin";
	
	/**
	 * 分页数据
	 * @param pageNo
	 * @param pageSize
	 * @param createUserId
	 * @param selectRegionId
	 * @return
	 */
	public Page<Bulletin> page(int pageNo,int pageSize,String createUserId,cultureSelectVO sv,String selectRegionId) {
		String sql = "select *,(select s.relaname from s_user s where s.keyid = b.userId) as user,"
				+"(SELECT s.relaname FROM s_user s WHERE s.keyid = b.reportPersonId) as reportPerson ";
		
		String sqlExceptSelect = " from " + table + " b where 1 = 1 and b.status<>0 and (b.regionId = '" + selectRegionId + "' " +
						"or b.regionId = (SELECT f.regionId FROM flow_todo f WHERE f.status = 1 and f.FlowId = b.keyID )) ";
		
		List<String> parasList = new ArrayList<String>();
		
		if(sv.getTitle() != null) {
			sqlExceptSelect += "and b.title like ? ";
			parasList.add("%"+sv.getTitle()+"%");
		}
		if(sv.getIsPublic() != null) {
			sqlExceptSelect += "and b.ispublic = ? ";
			parasList.add(sv.getIsPublic());
		}
		
		sqlExceptSelect += "order by CAST(b.year AS DECIMAL) desc, CAST(b.number AS DECIMAL) desc";
		
		if(parasList.size() != 0) {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect, parasList.toArray());
		}else {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect);
		}
	}
	
	/**
	 * 分页数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Bulletin> page(int pageNo,int pageSize) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(select s.relaname from s_user s where s.keyid = userId) as user ",
				" from " + table + " where 1=1 and ispublic = '1' " +
						"order by CAST(year AS DECIMAL) desc, CAST(number AS DECIMAL) desc ");
	}
	
	/**
	 * 根据keyids删除数据
	 * @param keyids
	 * @return
	 */
	public boolean deleteById(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}


}
