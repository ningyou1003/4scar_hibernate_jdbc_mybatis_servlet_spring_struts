package com.emindsoft.zsj.culture.ctrl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.culture.model.Silhouette;
import com.emindsoft.zsj.culture.model.Video;
import com.emindsoft.zsj.culture.vo.VideoVO;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("video")
public class VideoCtrl extends AdminBaseController<Video> {
	
	public VideoCtrl() {
		this.modelClass = Video.class;
	}
	
	/**
	 * 添加视频
	 * 
	 */
	public void add() {
     
		Video video;
		try {
			video = getModel();
			video.set("keyid", Video.dao.getId());
			video.set("releaseTime",dateTimeFormat.format(new Date()));
			video.set("createUserId",getCurrentUserId());
			video.set("status", 4);
			String RegionId=getCurrentUserRegionId();
			try {
				
				
				if (!"undefined".equals(getChooseRegionId()) && getChooseRegionId().length()>10) {
					RegionId=getChooseRegionId();
				}
			} catch (Exception e) {
				e.printStackTrace();
				//异常改变区域id
				RegionId=getCurrentUserRegionId();
			}
			video.set("regionId",RegionId);
			video.set("videoImgId",UUID.randomUUID().toString().replace("-", "") );
			video.set("VideoSourceID", UUID.randomUUID().toString().replace("-", ""));
			video.save();
			processAttachment(video.getStr("videoImgId"), video.getStr("keyid"), video.getStr("VideoSourceID"));
			success(video.getStr("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败");
		}
	}
	

	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1614_DelPower", funcName = "视频删除")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Video.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1614_EditPower", funcName = "视频更新")
	public void edit() {
		Video video;
		try {
			video = getModel();
			video.set("createUserId",getCurrentUserId());
			video.set("releaseTime",dateTimeFormat.format(new Date()));
			video.update();
			success(video.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！", e);
			error(002, "保存失败");
		}
	}
	
	/**
	 * 获取更新数据
	 */
	public void editData(){
		String keyid = getPara("keyId");
		Video video = Video.dao.findById(keyid);
		success(video);
	}
	
	/**
	 * 获取视频数据
	 * @throws IOException 
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void detailData() throws IOException {
		String keyid = getPara("keyId");
		Video video = Video.dao.findById(keyid);
		String attachment_rootDir = PropertiesContent.get("attachment_rootDir");
		//获取video地址
		List<Attachment> attachment = Attachment.dao.findAtt("video", keyid);
		for(Attachment a : attachment) {
			String path = a.getStr("path");
			String suffix =  path.substring(path.indexOf(".")+1);
			String url = attachment_rootDir + path;
/*			//如果是mpg,mov格式就获取转换后的视频路径
			if ("mpg".equals(suffix) || "mov".equals(suffix)) {
				String fileName = a.getStr("keyid");
				url = attachment_rootDir + "video/" + fileName + ".flv";
			}*/
			a.put("url", url);
		}
		
		success(new VideoVO(video,attachment));
	}
	
	/**
	 * 分页列表
	 * 
	 */
	public void List() throws Exception {
		//查询区域代码
		String selectRegionId;
		RolePower rp;
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		//视频类型
		String typeid = getPara("typeid");
		//根据区域代码确定权限
		Role r=Role.dao.findRolesByUser(getCurrentUserId());
		int orderid=0;
		if (r!=null) {
			orderid=r.getInt("orderid");
		}
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		
		if(StringUtils.isEmpty(chooseRegionId) 
				|| "undefined".equals(chooseRegionId) 
				|| userRegionId.equals(chooseRegionId)
				|| (parentregion.contains(userRegionId) && 5==orderid)) 
		{
			rp = RolePower.dao.getOperPower("1614", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("1614",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		if (!"undefined".equals(chooseRegionId)) {
			selectRegionId=chooseRegionId;
		}
		cultureSelectVO sv = Tools.getSubVO(cultureSelectVO.class, getRequest());
		Page<Video> pageList = Video.dao.page(getPageNo(), getPageSize(),getCurrentUserId(),selectRegionId,typeid,sv);
		//不存在数据就不需要查关联图片
		if(pageList.getTotalRow() != 0) {
			//生成关联图片ID数组
			List<Video> list = pageList.getList();
			String[] videoImgIds = new String[list.size()];
			for(int i = 0; i < list.size(); i++) {
				videoImgIds[i] = list.get(i).getStr("videoImgId");
			}
			//获取图片信息
			List<Attachment> attachment = Attachment.dao.findByRelateIds(videoImgIds,"video" );
			//图片匹配，添加图片url
			String attachment_rootDir = PropertiesContent.get("attachment_rootDir");
			for (Video video : list) {
				String keyString = video.getStr("videoImgId");
				for(int i = 0; i < attachment.size(); i++) {
					String relateid = attachment.get(i).getStr("RelateId");
					if(keyString.equals(relateid)) {
						String url = attachment_rootDir + attachment.get(i).getStr("path");;
						video.put("url", url);
						attachment.remove(i);
						break;
					}
				}
			}
		}
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getVideoList() {
		String typeId = getPara("typeId");
		String regioncode=getPara("regioncode");
		Page<Video> pageList = Video.dao.page(getPageNo(), getPageSize(),typeId,regioncode);
		//不存在数据就不需要查关联图片
		if(pageList.getTotalRow() != 0) {
			//生成关联图片ID数组
			List<Video> list = pageList.getList();
			String[] videoImgIds = new String[list.size()];
			for(int i = 0; i < list.size(); i++) {
				videoImgIds[i] = list.get(i).getStr("videoImgId");
			}
			//获取图片信息
			List<Attachment> attachment = Attachment.dao.findByRelateIds(videoImgIds,"video" );
			//图片匹配，添加图片url
			String attachment_rootDir = PropertiesContent.get("attachment_rootDir");
			for (Video video : list) {
				String keyString = video.getStr("videoImgId");
				for(int i = 0; i < attachment.size(); i++) {
					String relateid = attachment.get(i).getStr("RelateId");
					if(keyString.equals(relateid)) {
						String url = attachment_rootDir + attachment.get(i).getStr("path");;
						video.put("url", url);
						attachment.remove(i);
						break;
					}
				}
			}
		}
		success(pageList);
	}

}
