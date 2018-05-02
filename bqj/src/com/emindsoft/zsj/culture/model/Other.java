package com.emindsoft.zsj.culture.model;

import java.util.ArrayList;
import java.util.List;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "p_other", pkName = "KeyID")
public class Other extends BaseModel<Other> {
	public static Other dao = new Other();
	public static String table = "p_other";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Other> page(int pageNo,int pageSize,String createUserId,cultureSelectVO sv,String selectRegionId) {
		String sql = "select *,(select s.relaname from s_user s where s.keyid = b.userId) as user,"
				+"(SELECT s.relaname FROM s_user s WHERE s.keyid = b.reportPersonId) as reportPerson ";
		
		String sqlExceptSelect = " from " + table + " b where 1 = 1 and b.status<>0 " +
				"and (b.regionId = '" + selectRegionId + "' or b.regionId = (SELECT f.regionId FROM flow_todo f WHERE f.status = 1 and f.FlowId = b.keyID ))";
		
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
	 * 分页数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Other> page(int pageNo,int pageSize) {
		return this.paginate(pageNo, pageSize, "select * "
				+"(select s.relaname from s_user s where s.keyid = userId) as user ", 
				" from " + table + " where 1=1 and ispublic = '1' " +
						"order by releasetime desc ");
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
