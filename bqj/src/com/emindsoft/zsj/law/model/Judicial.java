package com.emindsoft.zsj.law.model;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;
import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "l_judicial",pkName = "KeyID")
public class Judicial extends BaseModel<Judicial> {
	
	public static Judicial dao = new Judicial();
	public static String table = "l_judicial";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param selectRegionId
	 * @return
	 */
	public Page<Judicial> page(int pageNo,int pageSize,String SelecTitle,String selectRegionId) {
		String sql = "select *,(select relaName from s_user where keyid = b.userId) as user ";
		
		String sqlExceptSelect = "from " + table + " b where 1 = 1 and b.regionId = '" + selectRegionId + "' ";
		
		if (!"".equals(SelecTitle)) {
			sqlExceptSelect += "and b.title like ? ";
		}
		
		sqlExceptSelect += "order by b.releasetime desc";
		
		if(!"".equals(SelecTitle))
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect, "%"+SelecTitle+"%");
		else 
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect);
	}
	
	/**
	 * 分页
	 * 按区域返回数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Judicial> page(int pageNo,int pageSize) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(select relaName from s_user where keyid = userId) as user ",
				"from " + table + " where 1=1 order by releasetime desc ");
	}
	
	/**
	 * 分页
	 * 按区域返回数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Judicial> page(int pageNo,int pageSize,String regioncode) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(select relaName from s_user where keyid = userId) as user ",
				"from " + table + " where 1=1 and regionid='"+regioncode+"' order by releasetime desc ");
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
