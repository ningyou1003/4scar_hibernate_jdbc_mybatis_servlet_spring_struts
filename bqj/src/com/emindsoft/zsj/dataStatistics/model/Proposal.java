package com.emindsoft.zsj.dataStatistics.model;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "t_proposal", pkName = "KeyID")
public class Proposal extends BaseModel<Proposal> {
	
	public static Proposal dao = new Proposal();
	public static String table = "t_proposal";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param selectRegionId
	 * @return
	 */
	public Page<Proposal> page(int pageNo,int pageSize,String SelectTitle,String selectRegionId) {
		String sql = "select *,(select relaName from s_user where keyid = t.userId) as user," +
				"(SELECT s.relaname FROM s_user s WHERE s.keyid = t.reportPersonId) as reportPerson ";
		
		String sqlExceptSelect = "from " + table + " t where 1 = 1 and t.status<>0 and (t.regionId = '" + selectRegionId + "' "+
				"or t.regionId = (SELECT f.regionId FROM flow_todo f WHERE f.status = 1 and f.FlowId = t.keyID )) ";
		
		if(!"".equals(SelectTitle)){
			sqlExceptSelect += "and t.title like ? ";
		}
		
		sqlExceptSelect += "order by t.releasetime desc";
		
		if(!"".equals(SelectTitle)) {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect, "%"+SelectTitle+"%");
		}else {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect);
		}
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
