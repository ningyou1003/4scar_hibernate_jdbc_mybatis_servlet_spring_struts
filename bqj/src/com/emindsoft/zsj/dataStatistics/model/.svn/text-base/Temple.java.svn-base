package com.emindsoft.zsj.dataStatistics.model;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;
import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "t_temple", pkName = "KeyID")
public class Temple extends BaseModel<Temple> {
	
	public static Temple dao = new Temple(); 
	public static String table = "t_temple";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param selectRegionId
	 * @return
	 */
	public Page<Temple> page(int pageNo,int pageSize,String SelectTitle) {
		String sql = "select *,(select relaName from s_user where keyid = t.userId) as user ";
		String sqlExceptSelect = "from " + table + " t where 1 = 1 ";
		
		if(!"".equals(SelectTitle)){
			sqlExceptSelect += "and t.type like ? ";
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
