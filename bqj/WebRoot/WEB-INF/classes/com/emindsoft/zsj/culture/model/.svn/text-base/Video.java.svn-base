package com.emindsoft.zsj.culture.model;

import java.util.ArrayList;
import java.util.List;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.tablebind.TableBind;

@TableBind(tableName = "p_video", pkName = "KeyID")
public class Video extends BaseModel<Video> {
	public static Video dao = new Video();
	public static String table = "p_video";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Video> page(int pageNo,int pageSize,String createUserId,String selectRegionId,String typeid,cultureSelectVO sv) {
		String sql = "select *,(SELECT s.relaname FROM s_user s WHERE s.KeyID = b.createUserId) as user,"
				+"(SELECT s.relaname FROM s_user s WHERE s.keyid = b.reportPersonId) as reportPerson ";
		
		String sqlExceptSelect = "from " + table + " b where 1 = 1 and b.status<>0 " +
				"and (b.typeid = '"+typeid+"' and b.regionId = '" + selectRegionId + "' " +
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
		
		sqlExceptSelect += "order by b.releasetime desc";
		
		if(parasList.size() != 0) {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect, parasList.toArray());
		}else {
			return this.paginate(pageNo, pageSize, sql, sqlExceptSelect);
		}
	}
	
	//old
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param typeid
	 * @return
	 */
	public Page<Video> page(int pageNo,int pageSize,String typeid) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(SELECT s.relaname FROM s_user s WHERE s.KeyID = createUserId) as user ", 
				" from " + table + " where 1 = 1 and typeid = '"+typeid+"' order by releasetime desc");
	}
	//now
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param typeid
	 * @return
	 */
	public Page<Video> page(int pageNo,int pageSize,String typeid,String regioncode) {
		return this.paginate(pageNo, pageSize, "select *,"
				+"(SELECT s.relaname FROM s_user s WHERE s.KeyID = createUserId) as user ", 
				" from " + table + " where 1 = 1 and typeid = '"+typeid+"' and regionid='"+regioncode+"' and ispublic=1 order by releasetime desc");
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
