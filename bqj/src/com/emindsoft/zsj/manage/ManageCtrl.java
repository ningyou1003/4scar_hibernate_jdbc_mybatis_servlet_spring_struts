package com.emindsoft.zsj.manage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.ctrl.AreaCtrl;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.AreaMember;
import com.emindsoft.zsj.util.ExcelUtils;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;

/**
 * Created by ym on 15-3-9.
 */
@ControllerKey("manage")
public class ManageCtrl extends AdminBaseController<Area> {

	public ManageCtrl() {
		this.modelClass = Area.class;
	}

	/**
	 * 区域分页列表 2015-05-13
	 */
	public void desktop() {
		String regionId = getAttr("regionId");
		if (StringUtils.isEmpty(regionId)) {
			regionId = getCurrentUserRegionId();
		}
		List<Area> areaList = Area.dao.loadActArea(regionId);
		for (Area a : areaList) {
			String desc = a.getStr("description");
			if (StrKit.notBlank(desc) && desc.length() > 30) {
				desc = desc.substring(0, 30) + "...";
				a.put("description", desc);
			}
		}
		Area.resetRegion(areaList);
		success(areaList);
	}

	@PowerBind(code = "100_AddPower", funcName = "添加成员")
	public void addMember() throws Exception {
		AreaMember member = (AreaMember) getModel2(AreaMember.class);
		if (StrKit.isBlank(member.getStr("keyId"))) {
			member.set("createTime", new Date());
		}
		member.saveOrUpdate();
		success();
	}

	public void memberInfo() throws Exception {
		AreaMember member = AreaMember.dao.findById(getPara("id"));
		success(member);
	}

	public void loadMember() {
		String regionCode = getPara("regionCode");
		String type = getPara("type");
		List<AreaMember> members = AreaMember.dao
				.findByRegion(regionCode, type);
		success(members);
	}

	/**
	 * 根据区域获取领导小组、办公室、成员单位及其职责、文化综合执法队伍
	 */
	public void loadMemberForFront() {
		String regionId = getChooseRegionId();
		if (StringUtils.isEmpty(regionId) || "undefined".equals(regionId)) {
			regionId = getCurrentUserRegionId();
		}
		List<AreaMember> members0 = AreaMember.dao.findByRegion(regionId, "0");// 成员单位及其职责
		List<AreaMember> members1 = AreaMember.dao.findByRegion(regionId, "1");// 领导小组
		List<AreaMember> members2 = AreaMember.dao.findByRegion(regionId, "2");// 办公室
		List<AreaMember> members3 = AreaMember.dao.findByRegion(regionId, "3");// 文化综合执法队伍
		members1.addAll(members0);
		members1.addAll(members2);
		members1.addAll(members3);
		success(members1);
	}

	@Before(Tx.class)
	public void deleteMember() {
		try {
			String[] keyids = getPara("keyids").split(",");
			for (String id : keyids) {
				Db.deleteById(AreaMember.table, "keyId", id);
			}
			success();
		} catch (Exception e) {
			log.error("删除异常", e);
			error(803);
		}
	}

	/**
	 * 根据区域获取成员单位，成员单位职责，工作机构等信息
	 */
	public void loadMemberInfo() {
		String regionId = getChooseRegionId();
		if (StringUtils.isEmpty(regionId) || "undefined".equals(regionId)) {
			regionId = getCurrentUserRegionId();
		}
		Area area = Area.dao.findByRegion(regionId);
		success(area);
	}

	/**
	 * 从excel表格导入数据
	 * 
	 * @throws IOException
	 */
	public void importExcel() {
		try {
			
		
		AreaMember am = null;
		List<AreaMember> members = null;
		String name = null; // 获取excel的name列单元，如果已存在name的数据，则不导入该条数据
		List<UploadFile> uploadFiles = getFiles();
		String regioncode = getPara("regionCode");
		String type = getPara("type");
		int typeOfInt = Integer.parseInt(type);

		if (null != uploadFiles) {
			for (UploadFile file : uploadFiles) {
				String[][] dataRows = ExcelUtils.ReadExcel(new FileInputStream(
						file.getFile()));
				if (dataRows != null && dataRows.length > 0) {
					for (int i = 0; i < dataRows.length; i++) {
						am = new AreaMember();
						boolean flag = false;
						// 2016-12-6 Excel导入 根据传参type值判断导入的excel表格 开始
						switch (typeOfInt) {
						case 0:// 成员单位及其职责
							
							name = dataRows[i][0];
							/**
							 * 防止重复提交数据，根据name,job,phone判断
							 * 考虑到同名同姓的人，职位又相同（比如都是副局长），则根据电话号码判断
							 * */
							if(name==null||"".equals(name)){
								;
							}else{
								members = AreaMember.dao.findByName(regioncode,
										type, name);
								Iterator it = members.iterator();
								
								while(it.hasNext()){
									flag = true;
									AreaMember member = (AreaMember) it.next();
									if(dataRows[i][1]!=null){
										if(dataRows[i][1].equals(member.getStr("job"))){
											flag = true;
										}else{
											flag = false;
										}
									}else{
										;
									}
								}
							}
							
					
							// 如果数据库已经存在某一个人的数据了，则不直接插入，可以选择修改
							if (!flag) {
								am.set("name", dataRows[i][0]);
								am.set("job", dataRows[i][1]);
								am.set("regioncode", regioncode);
								am.set("type", Integer.valueOf(type));
								am.set("createTime", new Date());
								am.set("millisecond", new Date().getTime());
								am.set("keyid", AreaMember.dao.getId());
								//System.out.println(dataRows[i][2]);
								if(dataRows[i][2]==null || dataRows[i][2].length()<1){
									continue;
								}
								if (dataRows[i][2].indexOf(".")>0) {
									dataRows[i][2]=dataRows[i][2].substring(0,dataRows[i][2].indexOf("."));
								}
								if (dataRows[i][2].length()>3) {
									am.set("orderid", dataRows[i][2].substring(0,3));
								}else {
									am.set("orderid", dataRows[i][2]);
								}
								
								
							} else {
								continue;
							}
							break;
						case 1:// 工作小组(工作站)成员名单
							name = dataRows[i][1];
							if(name==null||"".equals(name)){
								;
							}else{
								members = AreaMember.dao.findByName(regioncode,
										type, name);
								Iterator it = members.iterator();
								/**
								 * 防止重复提交数据，根据name,job,phone判断
								 * 考虑到同名同姓的人，职位又相同（比如都是副局长），则根据电话号码判断
								 * */
								while(it.hasNext()){
									flag = true;
									AreaMember member = (AreaMember) it.next();
									if(dataRows[i][2]!=null){
										if(dataRows[i][2].equals(member.getStr("job2"))){
											flag = true;
										}else{
											flag = false;
										}
										if(dataRows[i][3].equals(member.getStr("phone"))){
											flag = true;
										}else{
											flag = false;
										}
									}else{
										;
									}
								}
							}
//							members = AreaMember.dao.findByName(regioncode,
//									type, name);	
							 //如果数据库已经存在某一个人的数据了，则不直接插入，可以选择修改
							if (!flag) {
								am.set("type", Integer.valueOf(type));
								am.set("keyid", AreaMember.dao.getId());
								am.set("regioncode", regioncode);
								am.set("job", dataRows[i][0]);
								am.set("name", dataRows[i][1]);
								am.set("job2", dataRows[i][2]);
								am.set("phone", dataRows[i][3]);
								am.set("remark", dataRows[i][4]);
								am.set("createTime", new Date());
								am.set("millisecond", new Date().getTime());
								if(dataRows[i][5]==null || dataRows[i][5].length()<1){
									continue;
								}
								if (dataRows[i][5].indexOf(".")>0) {
									dataRows[i][5]=dataRows[i][5].substring(0,dataRows[i][5].indexOf("."));
								}
								if (dataRows[i][5].length()>3) {
									am.set("orderid", dataRows[i][5].substring(0,3));
								}else {
									am.set("orderid", dataRows[i][5]);
								}
							} else {
								continue;
							}
							break;
						case 2:// 工作小组办公室成员名单
							name = dataRows[i][1];
							if(name==null||"".equals(name)){
								;
							}else{
								members = AreaMember.dao.findByName(regioncode,
										type, name);
								Iterator it = members.iterator();
								/**
								 * 防止重复提交数据，根据name,job,phone判断
								 * 考虑到同名同姓的人，职位又相同（比如都是副局长），则根据电话号码判断
								 * */
								while(it.hasNext()){
									flag = true;
									AreaMember member = (AreaMember) it.next();
									if(dataRows[i][2]!=null){
										if(dataRows[i][2].equals(member.getStr("job2"))){
											flag = true;
										}else{
											flag = false;
										}
										if(dataRows[i][3].equals(member.getStr("phone"))){
											flag = true;
										}else{
											flag = false;
										}
									}else{
										;
									}
								}
							}
//							members = AreaMember.dao.findByName(regioncode,
//									type, name);
							// 如果数据库已经存在某一个人的数据了，则不直接插入，可以选择修改
							if (!flag) {
								am.set("type", Integer.valueOf(type));
								am.set("keyid", AreaMember.dao.getId());
								am.set("regioncode", regioncode);
								am.set("job", dataRows[i][0]);
								am.set("name", dataRows[i][1]);
								am.set("job2", dataRows[i][2]);
								am.set("phone", dataRows[i][3]);
								am.set("remark", dataRows[i][4]);
								am.set("millisecond", new Date().getTime());
								am.set("createTime", new Date());
								if(dataRows[i][5]==null || dataRows[i][5].length()<1){
									continue;
								}
								if (dataRows[i][5].indexOf(".")>0) {
									dataRows[i][5]=dataRows[i][5].substring(0,dataRows[i][5].indexOf("."));
								}
								if (dataRows[i][5].length()>3) {
									am.set("orderid", dataRows[i][5].substring(0,3));
								}else {
									am.set("orderid", dataRows[i][5]);
								}
							} else {
								continue;
							}
							break;
						case 3:// 文化市场综合执法支队表
							name = dataRows[i][1];
							if(name==null||"".equals(name)){
								;
							}else{
								members = AreaMember.dao.findByName(regioncode,
										type, name);
								Iterator it = members.iterator();
								/**
								 * 防止重复提交数据，根据name,job,phone判断
								 * 考虑到同名同姓的人，职位又相同（比如都是副局长），则根据电话号码判断
								 * */
								while(it.hasNext()){
									flag = true;
									AreaMember member = (AreaMember) it.next();
									if(dataRows[i][2]!=null){
										if(dataRows[i][2].equals(member.getStr("job2"))){
											flag = true;
										}else{
											flag = false;
										}
										if(dataRows[i][3].equals(member.getStr("phone"))){
											flag = true;
										}else{
											flag = false;
										}
									}else{
										;
									}
								}
							}
//							members = AreaMember.dao.findByName(regioncode,
//									type, name);
							// 如果数据库已经存在某一个人的数据了，则不直接插入，可以选择修改
							if (!flag) {
								am.set("type", Integer.valueOf(type));
								am.set("keyid", AreaMember.dao.getId());
								am.set("regioncode", regioncode);
								am.set("job", dataRows[i][0]);
								am.set("name", dataRows[i][1]);
								am.set("job2", dataRows[i][2]);
								am.set("phone", dataRows[i][3]);
								am.set("remark", dataRows[i][4]);
								am.set("createTime", new Date());
								am.set("millisecond", new Date().getTime());
								if(dataRows[i][5]==null || dataRows[i][5].length()<1){
									continue;
								}
								if (dataRows[i][5].indexOf(".")>0) {
									dataRows[i][5]=dataRows[i][5].substring(0,dataRows[i][5].indexOf("."));
								}
								if (dataRows[i][5].length()>3) {
									am.set("orderid", dataRows[i][5].substring(0,3));
								}else {
									am.set("orderid", dataRows[i][5]);
								}
 							} else {
								continue;
							}
							break;
						default:
							break;
						}
						// 2016-12-6 Excel导入 根据传参type值判断插入的excel表格 结束
						am.save();
					}
				} else {
					error(1, "文件为空或者格式不正确");
					return;
				}
			}

		}
		} catch (Exception e) {
			e.printStackTrace();
			error(1, "文件异常或者格式不正确");
			return;
		}
		success();
	}

	/**
	 * 导出数据到excel表格
	 * 
	 * @throws Exception
	 */
	public void exportExcel() throws Exception {
		String regioncode = getPara("regionCode");
		String type = getPara("type");
		LinkedHashMap<String, String> ttop = new LinkedHashMap<String, String>();
		String fileName = "";
		if ("1".equals(type)) {
			fileName = "工作小组（工作站）成员名单";
			ttop.put("job", "小组职务");
			ttop.put("name", "姓名");
			ttop.put("job2", "职务");
			ttop.put("phone", "联系电话");
			ttop.put("remark", "备注");
		}
		if ("2".equals(type)) {
			fileName = "工作小组办公室成员名单";
			ttop.put("job", "办公室职务");
			ttop.put("name", "姓名");
			ttop.put("job2", "职务");
			ttop.put("phone", "联系电话");
			ttop.put("remark", "备注");
		}
		if ("3".equals(type)) {
			fileName = "文化市场综合执法支队";
			ttop.put("job", "支队职务");
			ttop.put("name", "姓名");
			ttop.put("job2", "职务");
			ttop.put("phone", "联系电话");
			ttop.put("remark", "备注");
		}
		if ("0".equals(type)) {
			fileName = "成员单位及其职责";
			ttop.put("name", "成员单位");
			ttop.put("job", "成员单位职责");
		}

		List<AreaMember> list = AreaMember.dao.findByRegion(regioncode, type);
		getResponse().setContentType("application/x-download;charset=UTF-8");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = fileName + "_" + df.format(new Date()) + ".xls";
		getResponse().addHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		ExcelUtils.generateExcel(getResponse().getOutputStream(), ttop, list);
		renderNull();
	}

	/**
	 * 为官网提供成员单位，成员单位职责，工作机构等信息
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getMemberForWeb() {
		Area area = Area.dao
				.findFirst("SELECT regioncode FROM s_area WHERE region='广西壮族自治区'");
		String regionId = area.getStr("regioncode");
		List<AreaMember> members0 = AreaMember.dao.findByRegion(regionId, "0");// 成员单位及其职责
		List<AreaMember> members1 = AreaMember.dao.findByRegion(regionId, "1");// 领导小组
		List<AreaMember> members2 = AreaMember.dao.findByRegion(regionId, "2");// 办公室
		List<AreaMember> members3 = AreaMember.dao.findByRegion(regionId, "3");// 文化综合执法队伍
		members1.addAll(members0);
		members1.addAll(members2);
		members1.addAll(members3);
		success(members1);
	}

	/**
	 * 为手机端提供成员单位、领导小组及办公室相关信息
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getMemberForAndroid() {
		String regionId = getRegionCodeForApp();
		List<AreaMember> members = AreaMember.dao.findByRegion(regionId, "0");// 成员单位及其职责
		List<AreaMember> leader = AreaMember.dao.findByRegion(regionId, "1");// 领导小组
		List<AreaMember> office = AreaMember.dao.findByRegion(regionId, "2");// 办公室
		MembersVO mVo = new MembersVO(leader, office, members);
		success(mVo);
	}

	// 2017-1-9 提供本地excel模板下载 开始

	public void exportExcelModel() throws Exception {

		String fileName = null;
		String filePath = null;
		// String regioncode = getPara("regionCode");
		String type = getPara("type");
		try {
			InputStream is = null;

			if ("0".equals(type)) {
				filePath = getRequest().getSession().getServletContext()
						.getRealPath("/temp/" + "成员单位及其职责.xls");
				int index = filePath.lastIndexOf("/") + 1;
				fileName = filePath.substring(index);
			} else if ("1".equals(type)) {
				filePath = getRequest().getSession().getServletContext()
						.getRealPath("/temp/" + "工作小组工作站成员名单.xls");
				int index = filePath.lastIndexOf("/") + 1;
				fileName = filePath.substring(index);
			} else if ("2".equals(type)) {
				filePath = getRequest().getSession().getServletContext()
						.getRealPath("/temp/" + "工作小组办公室成员名单.xls");
				int index = filePath.lastIndexOf("/") + 1;
				fileName = filePath.substring(index);
			} else if ("3".equals(type)) {
				filePath = getRequest().getSession().getServletContext()
						.getRealPath("/temp/" + "文化市场综合执法支队.xls");
				int index = filePath.lastIndexOf("/") + 1;
				fileName = filePath.substring(index);
			} else {
				;
			}
			is = new FileInputStream(filePath);
			// getResponse().getOutputStream().close();
			getResponse().addHeader(
					"Content-Disposition",
					"attachment;filename="
							+ URLEncoder.encode(fileName, "UTF-8"));

			OutputStream out = getResponse().getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = is.read(b)) > 0) {
				// getResponse().getOutputStream().write(b,0,len);
				out.write(b, 0, len);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 程序默认使用render方法，以向客户端再次发出响应，引起报错 这里调用renderNull()方法，来解决异常
		 * */
		renderNull();
	}

	// 2017-1-9 提供本地excel模板下载 结束
	
	
	/**
	 * 按照公众网提供的区域编码查找
	 * 为官网提供成员单位，成员单位职责，工作机构等信息
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getMemberForWebByregioncode() {
		/*Area area = Area.dao
				.findFirst("SELECT regioncode FROM s_area WHERE region='广西壮族自治区'");
		String regionId = area.getStr("regioncode");*/
		String regionId = getPara("regioncode");
		List<AreaMember> members0 = AreaMember.dao.findByRegion(regionId, "0");// 成员单位及其职责
		List<AreaMember> members1 = AreaMember.dao.findByRegion(regionId, "1");// 领导小组
		List<AreaMember> members2 = AreaMember.dao.findByRegion(regionId, "2");// 办公室
		List<AreaMember> members3 = AreaMember.dao.findByRegion(regionId, "3");// 文化综合执法队伍
		members1.addAll(members0);
		members1.addAll(members2);
		members1.addAll(members3);
		success(members1);
	}
}