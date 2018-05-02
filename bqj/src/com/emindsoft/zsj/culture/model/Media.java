package com.emindsoft.zsj.culture.model;

import java.util.ArrayList;
import java.util.List;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "p_media", pkName = "KeyID")
public class Media extends BaseModel<Media>{
	public static Media dao = new Media();
	public static String table = "p_media";
	
	public Page<Media> page(int pageNo,int pageSize,String createUserId,cultureSelectVO sv,String selectRegionId) {
		String sql = "select *,(select s.relaname from s_user s where s.keyid = b.userId) as user,"
				 +"(SELECT s.relaname FROM s_user s WHERE s.keyid = b.reportPersonId) as reportPerson ";
		
		String sqlExceptSelect = " from " + table + " b where 1 = 1 and b.status<>0 " +
				"and (b.regionId = '" + selectRegionId + "' or b.regionId = (SELECT f.regionId FROM flow_todo f WHERE f.status = 1 and f.FlowId = b.keyID )) ";
		
		List<String> parasList = new ArrayList<String>();
		
		if(sv.getTitle() != null){
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
	 * 根据keyIds删除
	 * @param keyids
	 * @return
	 */
	public boolean deleteById(String[] keyids) { 
		return super.deleteByIds(keyids, table);
	}
	
	/**
	 * 公众网查询接口
	 * @param pageNo 第几页
	 * @param pageSize 每页有多少条记录
	 * @param regioncode 要查询的区域编码
	 * @return
	 */
	public Page<Media> getPageList(int pageNo,int pageSize,String regioncode){
		//sql语句
		String sql="SELECT * ";
		String sqlExceptSelect=" FROM p_media WHERE IsPublic=1 AND regionId='"+regioncode+"' AND `status`<>0 ORDER BY ReleaseTime DESC";
		return this.paginate(pageNo, pageSize, sql, sqlExceptSelect);
		
	}
	

}
