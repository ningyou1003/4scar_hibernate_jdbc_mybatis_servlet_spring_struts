package com.emindsoft.zsj.news.ctrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.news.model.Dynamic;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.ApageVO;
import com.emindsoft.zsj.vo.DynamicSelectVO;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import cn.dreampie.routebind.ControllerKey;

@ControllerKey("dynamic")
public class DynamicCtrl extends AdminBaseController<Dynamic>{
	
	public DynamicCtrl(){
		this.modelClass=Dynamic.class;
	}
	
	@Before(Tx.class)
	public void add() throws Exception{
		Dynamic d;
		try {
			d=getModel();
			d.set("keyid", Dynamic.dao.getId());
			/*Date date = new Date();
			String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			d.set("time", nowdate);*/
			String creator = getCurrentUserId();
			User u = User.dao.loadUserDetail(creator);
			d.set("creator", u.getStr("relaname"));
			d.set("createuserid", creator);
			d.set("status", 4);
			String RegionId= u.getStr("RegionId");
			try {
				
			
				if (!"undefined".equals(getChooseRegionId()) && getChooseRegionId().length()>10) {
					if(Integer.parseInt(RegionId)<Integer.parseInt(getChooseRegionId()))
					RegionId=getChooseRegionId();
				}
			} catch (Exception e) {
				e.printStackTrace();
				//异常改变区域id
				RegionId=getCurrentUserRegionId();
			}
			d.set("regionId", RegionId);
			d.save();
			processAttachment(d.getStr("keyid"));
			success(d.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.error(801);
			throw e;
		}
	}
	@PowerBind(code = "1201_EditPower", funcName = "编辑最新动态")
	public void edit(){
		Dynamic d;
		try{
			d=getModel();
			d.update();
			success(d.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}
	
	
	@Before(Tx.class)
	@PowerBind(code = "1201_DelPower", funcName = "删除专最新动态")
	public void delete(){
		String[] keyids = getPara("keyids").split(",");
		Dynamic.dao.deleteDynamicByIds(keyids);
		success();
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {			
		String keyid = getPara("keyid");
		Dynamic d = Dynamic.dao.loadDynamicDetail(keyid);
		success(d);
	}
	@PowerBind(code = "1201_LookPower", funcName = "查看最新动态列表")
	public void listPage(){
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String uid = getCurrentUserId();
		String userRegionId = getCurrentUserRegionId();
		String ispublic = getPara("ispublic");
		Role r=Role.dao.findRolesByUser(uid);
		int orderid=0;
		if (r!=null) {
			orderid=r.getInt("orderid");
		}
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId)
				|| (parentregion.contains(userRegionId) && 5==orderid)){
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());;
		}	
		DynamicSelectVO dyVO = Tools.getSubVO(DynamicSelectVO.class, getRequest());
		Page<Dynamic> grPage=Dynamic.dao.page(getPageNo(), getPageSize(), dyVO,uid,chooseRegionId,userRegionId,ispublic);
		
		List<Dynamic> listDynamic = grPage.getList();
		
		for (Dynamic dynamic : listDynamic) {
			String s = dynamic.getStr("keyid");
			List<Attachment> list= Attachment.dao.findAtt("dynamic", s);
			if(list.size()>0){
				String dUrl=list.get(0).getStr("keyid");
				String url ="/upload/"+dUrl;
				dynamic.put("url", url);
			}
				
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", new ApageVO(grPage,rp));
		success(map);
	}
	
	public void listUrl(){//图片接口，且不同区域展示不同内容
		
		Integer number = getParaToInt("number");
//		System.out.println(new Date());
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId = getChooseRegionId();
		List<Dynamic> listDynamic= Dynamic.dao.findIndexPageNew(userRegionId, chooseRegionId, number);
		
		//List urls = new ArrayList();		
		String upload = PropertiesContent.get("attachment_rootDir");//获得配置文件的上传路径
		
//		List urlsIndex = new ArrayList();//返回的list 
//		int i=0;//条数,用于优化查询，我没改之前这个接口访问一次执行了200多条sql查询，客户那边访问首页页面很慢
		
		for (Dynamic dynamic : listDynamic) {//得到图片
//			String s = dynamic.getStr("keyid");
//			List<Attachment> list= Attachment.dao.findAtt("dynamic", s);		
//			if(list.size()>0){
				String dUrl=dynamic.getStr("Content");
				//if(dUrl.toLowerCase().indexOf(".jpg")>=0||dUrl.toLowerCase().indexOf(".jpeg")>=0||dUrl.toLowerCase().indexOf(".png")>=0 ||dUrl.toLowerCase().indexOf(".bmp")>=0){
					String url =upload+dUrl;
					dynamic.put("url", url);
//					urlsIndex.add(dynamic);
//					i++;
				//}
				
			}
//			if (i==5) {
//				break;
//			}
//		}
		
		//List urlsIndex = new ArrayList(); 移动到上方，优化运行速度
//		if(urls.size()>0){
//			if(number<=urls.size()){
//				for (int i = 0; i < number; i++) {
//					urlsIndex.add(urls.get(i));
//				}
//			}else{
//				for (int i = 0; i < urls.size(); i++) {
//					urlsIndex.add(urls.get(i));
//				}
//			}
//			
//		}
		
		success(listDynamic);
		//System.out.println(new Date());
		
		
	}
	
	public void listText(){//纯文字接口，且不同区域展示不同内容
		Integer number = getParaToInt("number");
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId = getChooseRegionId();
		List<Dynamic> urls= Dynamic.dao.findIndexPageText(userRegionId, chooseRegionId);
		
		/*List urls = new ArrayList();
		
		for (Dynamic dynamic : list) {
			String s = dynamic.getStr("keyid");
			List<Attachment> lists= Attachment.dao.findAtt("dynamic", s);
			if(lists.size()==0){
				urls.add(dynamic);
			}		    
		}*/
		
		List textIndex = new ArrayList();
		if(number<=urls.size()){
			for (int i = 0; i < number; i++) {
				textIndex.add(urls.get(i));
			}
		}else{
			for (int i = 0; i < urls.size(); i++) {
				textIndex.add(urls.get(i));
			}
		}
		success(textIndex);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listUrlWeb(){//网站图片接口
		Integer number = getParaToInt("number");
		String regioncode=getPara("regioncode");
		List<Dynamic> listDynamic= Dynamic.dao.findIndexPageWeb(regioncode);
		int k = 0;
		List urls = new ArrayList();		
		String upload = PropertiesContent.get("attachment_rootDir");//获得配置文件的上传路径
		for (Dynamic dynamic : listDynamic) {//得到图片
			String s = dynamic.getStr("keyid");
			k++;
			List<Attachment> list= Attachment.dao.findAtt("dynamic", s);		
			if(list.size()>0){
				String dUrl=list.get(0).getStr("path");
				if(dUrl.toLowerCase().indexOf(".jpg")>=0||dUrl.toLowerCase().indexOf(".jpeg")>=0||dUrl.toLowerCase().indexOf(".png")>=0 ||dUrl.toLowerCase().indexOf(".bmp")>=0){
					String url =upload+dUrl;
					dynamic.put("url", url);
					urls.add(dynamic);
				}
				
			}
			if(k>=number) break;
		}
		
		List urlsIndex = new ArrayList();
		if(urls.size()>0){
			if(number<=urls.size()){
				for (int i = 0; i < number; i++) {
					urlsIndex.add(urls.get(i));
				}
			}else{
				for (int i = 0; i < urls.size(); i++) {
					urlsIndex.add(urls.get(i));
				}
			}
			
		}
		
		success(urlsIndex);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listTextWeb(){//网站文字接口
		String regioncode=getPara("regioncode");
		Integer number = getParaToInt("number");
		List<Dynamic> urls= Dynamic.dao.findIndexPageWebText(regioncode,number);				
//		List textIndex = new ArrayList();
//		if(number<=urls.size()){
//			for (int i = 0; i < number; i++) {
//				textIndex.add(urls.get(i));
//			}
//		}else{
//			for (int i = 0; i < urls.size(); i++) {
//				textIndex.add(urls.get(i));
//			}
//		}
//		success(textIndex);
		success(urls);
	}

	@ClearInterceptor(ClearLayer.ALL)
	public void listPageApp(){//app接口，最新新闻列表,未登录公开数据
		String regionId = getRegionCodeForApp();
		int page = getParaToInt("page");
		Page<Dynamic> dmPage=Dynamic.dao.pageApp(page, getPageSize(),regionId);
		List<Dynamic> listDynamicApp = dmPage.getList();
		for (Dynamic dynamic : listDynamicApp) {
			String s = dynamic.getStr("keyid");
			List<Attachment> list= Attachment.dao.findAtt("dynamic", s);
			if(list.size()>0){
				String dUrl=list.get(0).getStr("keyid");
				String url ="/upload/"+dUrl;
				dynamic.put("url", url);
			}
				
		}	
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", dmPage);
		success(map);
		
	}
	/*
	 * 任意表返回列表接口
	 * http://192.168.1.116:8180/bqj/dynamic/listPageany?page=1&where=type='other'&tb=c_case
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void listPageany(){// 
		String regionId = getRegionCodeForApp();
		
		// 获取分页参数
				int pageNumber = getParaToInt("page",1); //page 
				String tb = getPara ("tb");//tablename 
				int pageSize = getParaToInt("pagesize", 15);

				// 获取条件
				List<String> parmList = new ArrayList<String>();
				//where = WidgetManager.getWhere(this, fields, parmList, object.getStr("filter"));
				String where =  getPara ("where","");//where: add =1
				//parmList.add(where);
				if (!where.equals("")) 
					where= " where " +where;
				
				String orderby =  getPara ("orderby","");//tablename
				if (!orderby.equals("")) orderby= " order by " +orderby;
				
				// 转换SQL参数为Obj[]
				Object[] parm = new Object[parmList.size()];
				parmList.toArray(parm);
				
				// 获取排序
				String sort =orderby;// WidgetManager.getSort(this, object);

				// 分页查询Grid数据
				String view = tb +" ";
				String sql = "from " + view + where + sort;
				// SQL优化
				if (sql.endsWith(" where 1=1 ")) {
					sql = sql.replace(" where 1=1 ", "");
				}
				
				Page<Record> dmPage = Db.paginate(pageNumber, pageSize, "select *", sql, parm);
				//Page<Record> dmPage = Db.use("").paginate(pageNumber, pageSize, "select *", sql, parm);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", dmPage);
		success(map);
		
		
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listUrlApp(){//app图片接口
		Integer number = getParaToInt("number");
		List<Dynamic> listDynamic= Dynamic.dao.findIndexPageApp();
		int j=0;
		List urls = new ArrayList();		
		String upload = PropertiesContent.get("attachment_rootDir");//获得配置文件的上传路径
		for (Dynamic dynamic : listDynamic) {//得到图片
			String s = dynamic.getStr("keyid");
			j++;
			List<Attachment> list= Attachment.dao.findAtt("dynamic", s);		
			if(list.size()>0){
				String dUrl=list.get(0).getStr("path");
				if(dUrl.toLowerCase().indexOf(".jpg")>=0||dUrl.toLowerCase().indexOf(".jpeg")>=0||dUrl.toLowerCase().indexOf(".png")>=0 ||dUrl.toLowerCase().indexOf(".bmp")>=0){
					String url =upload+dUrl;
					dynamic.put("url", url);
					urls.add(dynamic);
				}
				
			}	
			if(j>=number) break;
			
		}
		
		List urlsIndex = new ArrayList();
		if(urls.size()>0){
			if(number<=urls.size()){
				for (int i = 0; i < number; i++) {
					urlsIndex.add(urls.get(i));
				}
			}else{
				for (int i = 0; i < urls.size(); i++) {
					urlsIndex.add(urls.get(i));
				}
			}
			
		}
		
		success(urlsIndex);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listUrlAndroid(){
		String regionId = getRegionCodeForApp();
		String jpg = ".jpg";
		String jpeg = ".jpeg";
		String png = ".png";
		String bmp = ".bmp";
		String sql="SELECT n.KeyId,n.Title,b.Path FROM n_dynamic n,base_attachment b " +
				"WHERE n.KeyId=b.RelateId  AND n.regionId='" +regionId+"'" +
				"AND (b.Path like '%" +jpg+ "%' or b.Path like '%" +jpeg+ "%' or b.Path like '%" +png+ "%' or b.Path like '%" +bmp+ "%') " +
				"GROUP BY n.KeyId ORDER BY n.Time DESC LIMIT 5 ";
		List<Record> listData = Db.find(sql);
		String upload = PropertiesContent.get("attachment_rootDir");
		for (Record record : listData) {
			String dUrl = record.getStr("path");
			String url = upload+dUrl;
			record.set("url", url);
		}
		success(listData);
	}
}
