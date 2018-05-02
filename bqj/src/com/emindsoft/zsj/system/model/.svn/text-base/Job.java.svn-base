package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@TableBind(tableName = "s_job", pkName = "keyid")
public class Job extends BaseModel<Job> {
	public static Job dao = new Job();
	public static String table = "s_job";

	/**
	 * 分页列表查询,进行条件模糊查询
	 * 
	 * @return
	 */
	public Page<Job> page(int pageNo, int pageSize,String regionCode) {
		StringBuffer sb = new StringBuffer();
		if(!"".equals(regionCode)&& regionCode!=null){
			sb.append(" AND j.RegionCode='"+regionCode+"'");
		}
		return this.paginate(pageNo, pageSize, "select *", "FROM " + table +" j," + Area.table + " a where j.regioncode=a.regioncode "+sb.toString()+""
				+ " ORDER BY j.RegionCode, j.order");
	}
	
	/**
	 * 根据id进行删除
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}
	
	/**
	 * editdata根据id进行查询
	 * @return
	 */
	public Job findByKeyid(String keyid) {
		String sql = "select * from " + table + " s," + Area.table + " a where s.RegionCode=a.RegionCode and keyid = ?";
		return findFirst(sql,keyid);
	}
	
	/**
	 * 根据区域编码查询职务列表
	 * @param regioncode
	 * @return
	 */
	public List<Job> jobListForUser(String regioncode) {
		String sql = "select * from "+table +" where regioncode = '"+regioncode+"' ORDER BY `order`";
		return find(sql);
	}

	/**
	 * 获取用户所属职务信息
	 * @param userId
	 * @return
	 */
	public List<Job> loadJobsForUser(String userId) {
		if(!StringUtils.isEmpty(userId)){
			return find("SELECT j.KeyId,j.Name FROM s_job j, s_job_user ju WHERE j.KeyID=ju.jobid AND ju.userid=?", userId);
		}
		return null;
	}
	
	/**
	 * 获取用户所属兼职职务信息
	 * @param userId
	 * @return
	 */
	public List<Job> loadParttimeJobsForUser(String userId) {
		if(!StringUtils.isEmpty(userId)){
			return find("SELECT j.KeyId,j.Name FROM s_job j, s_job_user_parttime ju WHERE j.KeyID=ju.jobid AND ju.userid=?", userId);
		}
		return null;
	}
}
