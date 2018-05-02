package com.emindsoft.zsj.culture.ctrl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.sanselan.ImageReadException;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.culture.model.Silhouette;
import com.emindsoft.zsj.culture.vo.SilhouetteDetailVO;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.util.CmykToRgb;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import cn.dreampie.routebind.ControllerKey;

@ControllerKey("silhouette")
public class SilhouetteCtrl extends AdminBaseController<Silhouette> {
	
	public SilhouetteCtrl() {
		this.modelClass = Silhouette.class;
	}
	
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		String regionCode = getPara("regionCode");
		Silhouette silhouette;
		try {
			silhouette = getModel();
			silhouette.set("keyid", Silhouette.dao.getId());
			silhouette.set("releaseTime",dateTimeFormat.format(new Date()));
			silhouette.set("createUserId",getCurrentUserId());
			silhouette.set("status", 4);
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
			silhouette.set("regionId",RegionId);
			silhouette.set("SourceID", UUID.randomUUID().toString().replace("-", ""));
			silhouette.save();
			processAttachment(silhouette.getStr("keyid"),silhouette.getStr("SourceID"));
			success(silhouette.get("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败！");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1613_DelPower", funcName = "墙报删除")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Silhouette.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1613_EditPower", funcName = "墙报更新")
	public void edit() {
		Silhouette silhouette;
		try {
			silhouette = getModel();
			silhouette.set("createUserId",getCurrentUserId());
			silhouette.set("releaseTime",dateTimeFormat.format(new Date()));
			silhouette.update();
			success(silhouette.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！", e);
			error(002, "保存失败！");
		}
	}
	
	/**
	 * 获取更新数据
	 */
	public void editData() {
		String keyid = getPara("keyId");
		Silhouette silhouette = Silhouette.dao.findById(keyid);				
		success(silhouette);
	}
	
	/**
	 * 详细页数据
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void detailData() {
		String keyid = getPara("keyId");
		Silhouette silhouette = Silhouette.dao.findById(keyid);
		
		String attachment_rootDir = PropertiesContent.get("attachment_rootDir");
		List<String> list = new ArrayList<String>();
		//通过附件keyid组成压缩图片路径
		List<Attachment> attachment = Attachment.dao.findAtt("silhouette", keyid);
		for(int i = 0; i < attachment.size(); i++) {
			String path = attachment.get(i).getStr("path");
			String url = attachment_rootDir + path;
			list.add(url);
		}
		success(new SilhouetteDetailVO(silhouette, list));
	}
	
/*	*//**
	 * 详细页数据
	 *//*
	@ClearInterceptor(ClearLayer.ALL)
	public void detailData() throws Exception {
		//通过数据ID获取墙报详细数据
		String keyid = getPara("keyId");
		Silhouette silhouette = Silhouette.dao.findById(keyid);
		//通过关联keyid找到压缩图片路径
		//返回路径list
		List<Attachment> attachment = Attachment.dao.find("SELECT keyid,path FROM base_attachment " +
				"WHERE RelateId = ? and RelateType = ? ",keyid,"silhouette");
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < attachment.size(); i++) {
			//如果不存在，调用generateImages()生成
			String upload_Dir = PropertiesContent.get("upload_dir");
			String zipPath = "zipJpg/" + attachment.get(i).getStr("keyid") + ".jpg";
			if (!new File(upload_Dir + zipPath).exists()) {
				generateImages(upload_Dir + attachment.get(i).getStr("path"), upload_Dir + zipPath);
			}
			//添加路径到list
			list.add(PropertiesContent.get("attachment_rootDir") + zipPath);
		}
		success(new SilhouetteDetailVO(silhouette, list));
	}*/
	
	
/*	*//**
	 * 根据用户所在或选择区域加载区域树形结构
	 *//*
	public void loadRegionTree() {
		//获取字节点依据
		String isLeaf = getPara("isLeaf");
		//获取年份
		String year = getPara("year");
		
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		
		//确定区域ID
		if(!StringUtils.isEmpty(chooseRegionId) && !"undefined".equals(chooseRegionId))	 {
			userRegionId = chooseRegionId;
		}
		
		//判断树形目录是否存在字节点，否则返回空list
		List<Area> list = new ArrayList<Area>();
		if("true".equals(isLeaf)) {
			String sql = "SELECT region,regionCode,(" + year + ")year FROM " +  Area.table
					+ " d WHERE d.isavailable=0 and (d.ParentCode =? or d.regionCode = ?) order by d.regioncode";
			list = Area.dao.find(sql,userRegionId,userRegionId);
		}
		this.renderJson(list);
	}
	
	
	
	*//**
	 * 获取区域数据集
	 *//*
	public void regionListToSilhouette() {
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		
		//确定查询区域ID
		if(!StringUtils.isEmpty(chooseRegionId) && !"undefined".equals(chooseRegionId)) {
			userRegionId = chooseRegionId;
		}
		
		List<Area> regionList = Area.dao.find("select region,regionCode,parentCode " +
				"FROM s_area WHERE  IsAvailable=0 AND (ParentCode = ? or RegionCode = ?) ORDER BY RegionCode",userRegionId,userRegionId);
		success(regionList);
	}*/
	
	/**
	 * 分页列表
	 * 
	 */
	public void List() {
		//权限对象
		RolePower rp = null;
		//年份
		String year = getPara("year");
		//区域代码
		String regionCode = getPara("regionCode");
		//区域代码的父代码
		String parentCode = getPara("parentCode");
		//当前系统操作人区域ID
		String userRegionId = getCurrentUserRegionId();
		//根据区域代码确定权限
		Role r=Role.dao.findRolesByUser(getCurrentUserId());
		int orderid=0;
		if (r!=null) {
			orderid=r.getInt("orderid");
		}
		String chooseRegionId =getChooseRegionId();
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		if(userRegionId.equals(regionCode) 
				|| userRegionId.equals("450000000000") 
				|| userRegionId.equals(parentCode) || (parentregion.contains(userRegionId) && 5==orderid)) 
		{
			rp = RolePower.dao.getOperPower("1613",getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("1613",getCurrentUserId());
		}
		cultureSelectVO sv = Tools.getSubVO(cultureSelectVO.class, getRequest());
		Page<Silhouette> pageList = Silhouette.dao.page(getPageNo(), getPageSize(),getCurrentUserId(),regionCode,year,sv);
		//不存在数据就不需要查关联图片
		if(pageList.getTotalRow() != 0) {
			//生成关联图片ID数组
			List<Silhouette> list = pageList.getList();
			String[] keyids = new String[list.size()];
			for(int i = 0; i < list.size(); i++) {
				keyids[i] = list.get(i).getStr("keyid");
			}
			//获取图片信息
			List<Attachment> attachment = Attachment.dao.findByRelateIds(keyids,"silhouette" );
			//图片匹配，添加图片url
			String attachment_rootDir = PropertiesContent.get("attachment_rootDir");
			for (Silhouette silhouette : list) {
				String keyString = silhouette.getStr("keyid");
				for(int i = 0; i < attachment.size(); i++) {
					String relateid = attachment.get(i).getStr("RelateId");
					if(keyString.equals(relateid)) {
						String url = attachment_rootDir + attachment.get(i).getStr("path");;
						silhouette.put("url", url);
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
	public void getSilhouetteList() {
		String year = getPara("year");//年份
		Page<Silhouette> pageList = Silhouette.dao.page(getPageNo(), getPageSize(),year);
		//不存在数据就不需要查关联图片
		if(pageList.getTotalRow() != 0) {
			//生成关联图片ID数组
			List<Silhouette> list = pageList.getList();
			String[] keyids = new String[list.size()];
			for(int i = 0; i < list.size(); i++) {
				keyids[i] = list.get(i).getStr("keyid");
			}
			//获取图片信息
			List<Attachment> attachment = Attachment.dao.findByRelateIds(keyids,"silhouette" );
			//图片匹配，添加图片url
			String attachment_rootDir = PropertiesContent.get("attachment_rootDir");
			for (Silhouette silhouette : list) {
				String keyString = silhouette.getStr("keyid");
				for(int i = 0; i < attachment.size(); i++) {
					String relateid = attachment.get(i).getStr("RelateId");
					if(keyString.equals(relateid)) {
						String url = attachment_rootDir + attachment.get(i).getStr("path");;
						silhouette.put("url", url);
						attachment.remove(i);
						break;
					}
				}
			}			
		}
		success(pageList);
	}
	
/*	*//**
	 * 分页列表
	 * @throws  
	 * 
	 *//*
	public void List() throws Exception {
		RolePower rp;//权限
		String year = getPara("year");//获取年份
		String regionCode = getPara("regionCode");//获取区域代码
		String parentCode = getPara("parentCode");//获取父区域代码
		String userRegionId = getCurrentUserRegionId();
		//确定查询区域代码与权限
		if(userRegionId.equals(regionCode) || userRegionId.equals("450000000000") || userRegionId.equals(parentCode)) {
			rp = RolePower.dao.getOperPower("1613",getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("1613",getCurrentUserId());
		}
		//获取墙报数据
		Page<Silhouette> pageList = Silhouette.dao.page(getPageNo(), getPageSize(),getCurrentUserId(),regionCode,year);
		//通关联keyid找到附件压缩图片路径
		List<Silhouette> list = pageList.getList();
		for(Silhouette silhouette : list) {
			String keyid = silhouette.getStr("keyid");
			List<Attachment> attachment = Attachment.dao.find("SELECT keyid,path FROM base_attachment " +
					"WHERE RelateId = ? and RelateType = ? LIMIT 1 ",keyid,"silhouette");
			if(attachment.size() > 0) {
				//如不存在压缩图，则调用generateImages()生成压缩图片
				String upload_Dir = PropertiesContent.get("upload_dir");
				String zipPath = "zipJpg/" + attachment.get(0).getStr("keyid") + ".jpg";//压缩图片路径
				if(!new File(upload_Dir + zipPath).exists()) {
					generateImages(upload_Dir + attachment.get(0).getStr("path"), upload_Dir + zipPath);
				}
				//添加图片路径
				silhouette.put("url", PropertiesContent.get("attachment_rootDir") + zipPath);
			} else {
				silhouette.put("url", "");
			}
		}
		//返回数据
		success(new PageVO(pageList,rp));
	}*/
	
	/**
	 * 生成压缩图片,
	 * CMYK TO RGB,
	 * 按一定比例压缩
	 * @param uploadPath
	 * @param zipPath
	 * @throws ImageReadException
	 * @throws IOException
	 */
	public void generateImages(String uploadPath, String zipPath) throws ImageReadException, IOException {
		File file = new File(uploadPath);
		//CMYK TO RGB
		BufferedImage image;
        try {
        	image = ImageIO.read(file);
        } catch (IOException e) {
          	// TODO Auto-generated catch block
          	// e.printStackTrace();
          	CmykToRgb rgb = new CmykToRgb();
          	image = rgb.readImage(file);
        }
		double rate;//压缩比例
		double rate1 = (double)image.getWidth(null);
		double rate2 = (double)image.getHeight(null);
		if(rate1 > rate2) {
			rate = 1700.00 / rate1;
		} else {
			rate = 950.00 / rate1;
		}
		//确定压缩图片尺寸
		int newWidth = (int)((double)image.getWidth(null) * rate);
		int newHeight = (int)((double)image.getHeight(null) * rate);
		//压缩参数设置
		BufferedImage tag = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH), 0, 0, null);
		//输出路径
		FileOutputStream out = new FileOutputStream(zipPath);
		//压缩图片
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag);
		out.close();
	}
	

}
