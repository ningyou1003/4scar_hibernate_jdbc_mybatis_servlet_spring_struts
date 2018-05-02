package com.emindsoft.zsj.law.model;

import java.util.ArrayList;
import java.util.List;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.law.vo.NoticeSelectVO;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "l_notice", pkName = "keyID" )
public class Notice extends BaseModel<Notice> {
	public static Notice dao = new Notice();
	public static String table = "l_notice";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param noticeSelectVO
	 * @param selectRegionId
	 * @return
	 */
	public Page<Notice> page(int pageNo,int pageSize,NoticeSelectVO noticeSelectVO,String selectRegionId) {
		
		String sql = "select *,"
				+"(select relaName from s_user where keyid = b.userId) as user ";
		
		String sqlExceptSelect = "from " + table + " b where 1 = 1 and b.regionId = '" + selectRegionId + "' ";
		
		List<String> parasList = new ArrayList<String>();
		
		if(noticeSelectVO.getTitle() != null) {
			sqlExceptSelect += "and b.title like ? ";
			parasList.add("%" + noticeSelectVO.getTitle() + "%");
		}
		
		sqlExceptSelect += "order by b.releasetime desc";
		
		if(parasList.size() != 0)
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect, parasList.toArray());
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
	public Page<Notice> page(int pageNo,int pageSize) {
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
	public Page<Notice> page(int pageNo,int pageSize,String regioncode) {
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
