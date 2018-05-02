package com.emindsoft.zsj.build.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.build.vo.BuildSelectVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "b_build", pkName = "KeyID")
public class Build extends BaseModel<Build> {
	public static Build dao = new Build();
	public static String table = "b_build";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param bsVO
	 * @param userRegionId
	 * @param chooseRegionId
	 * @return
	 */
	public Page<Build> page(int pageNo,int pageSize,BuildSelectVO bsVO,String selectRegionId) {
		
		String sql = "select *,(select s.RelaName from s_user s where s.keyid = b.userId ) as user ";
		
		String sqlExceptSelect = "from " + table + " b where 1 = 1 and b.regionId = '" + selectRegionId + "' ";
		
		List<String> parasList = new ArrayList<String>();
		
		if(bsVO.getTitle() != null) {
			sqlExceptSelect += "and b.title like ? ";
			parasList.add("%" +  bsVO.getTitle() + "%");
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
	 * 按区域倒序返回数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Build> page(int pageNo,int pageSize) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(select s.RelaName from s_user s where s.keyid = userId ) as user ",
				"from " + table + " where 1=1 order by releasetime desc ");
	}
	
	/**
	 * 分页
	 * 按区域倒序返回数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Build> page(int pageNo,int pageSize,String regioncode) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(select s.RelaName from s_user s where s.keyid = userId ) as user ",
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
