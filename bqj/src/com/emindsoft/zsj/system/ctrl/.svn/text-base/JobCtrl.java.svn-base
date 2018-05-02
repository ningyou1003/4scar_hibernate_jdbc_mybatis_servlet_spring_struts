package com.emindsoft.zsj.system.ctrl;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.Job;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.vo.JobPageVO;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import java.util.List;


@ControllerKey("job")
public class JobCtrl extends AdminBaseController<Job>{
	
	public JobCtrl() {
		this.modelClass = Job.class;
	}
	
	/**
	 * 职务管理列表
	 */
	public void jobList() {
		RolePower rp= RolePower.dao.getOperPower("819", getCurrentUserId());
		String regionCode = getAttr("regionCode");
		boolean b = StringUtils.isEmpty(regionCode);
		if(b==true||"undefined".equals(regionCode)){
			regionCode = getCurrentUserRegionId();
		}
		Page<Job> joblist=Job.dao.page(getPageNo(),getPageSize(),regionCode);
		JobPageVO jobvo=new JobPageVO(joblist, rp);
		success(jobvo);
	}

	public void jobListForUser() {
		String regioncode = getCurrentUserRegionId();
		List<Job> jobList = Job.dao.jobListForUser(regioncode);
		success(jobList);
	}
	
	/**
	 * 根据区域编码查询职务
	 */
	public void getJobListByRegionCode(){
		String regioncode = getAttr("regionCode");
		List<Job> jobList = Job.dao.jobListForUser(regioncode);
		this.renderJson(jobList);
//		success(jobList);
	}
	
	/**
	 * 获取用户所属职务信息
	 */
	public void jobUserDate() {
		String userId = getPara("userId");
		List<Job> rolelist = Job.dao.loadJobsForUser(userId);
		success(rolelist);
	}
	
	/**
	 * 删除选择的职务管理
	 */
	public void deletejob() {
		String[] keyids = getPara("keyids").split(",");
		Job.dao.deleteByIds(keyids);
		success();
	}
	
	/**
	 * 添加信息
	 * @throws Exception 
	 */
	public void add() throws Exception {
		Job job;
		try {
			job = getModel();
			job.set("keyid", Job.dao.getId());
			job.save();
			this.success(job.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.error(801);
			throw e;
		}
	}
	
	/**
	 * editData()查询要读取的信息
	 */
	public void editData() {
		String keyid = getPara("keyid");
		Job job = Job.dao.findByKeyid(keyid);
		this.success(job);
	}
	
	/**
	 * deit()把读取出来的信息进行修改
	 */
	public void edit() {					
		Job job;
		try {
			job = getModel();
			job.update();
			success(job.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新用户异常", e);
			error(802);
		}
	}
}
