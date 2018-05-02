package com.emindsoft.zsj.cases.ctrl;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.cases.model.Formula;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.FormulaSelectVO;
import com.emindsoft.zsj.vo.FormulaVO;
import com.emindsoft.zsj.vo.PageVO;
import com.emindsoft.zsj.vo.formulaDetailVOs;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

@ControllerKey("formula")
public class FormulaCtrl extends AdminBaseController<Formula>{
	
	public FormulaCtrl(){
		this.modelClass = Formula.class;
	}
	
	/**
	 * 根据用户所在或选择区域加载区域树形结构
	 */
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
			String sql = "SELECT *,(" + year + ")year FROM " +  Area.table
					+ " d WHERE d.isavailable=0 and (d.ParentCode =? or d.regionCode = ?) and OperLevel between 1 and 4 order by d.regioncode";
			list = Area.dao.find(sql,userRegionId,userRegionId);
		}
		Area.resetRegion(list);
		this.renderJson(list);
	}
	
	/**
	 * 获取区域数据集
	 */
	public void regionList() {
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		
		//确定查询区域ID
		if(!StringUtils.isEmpty(chooseRegionId) && !"undefined".equals(chooseRegionId)) {
			userRegionId = chooseRegionId;
		}
		
		List<Area> regionList = Area.dao.find("select * FROM s_area WHERE  IsAvailable=0 AND OperLevel between 1 and 4 and (ParentCode = ? or RegionCode = ?) ORDER BY RegionCode",userRegionId,userRegionId);
		Area.resetRegion(regionList);
		success(regionList);
	}
	
	/**
	 * 公式分页列表
	 * @throws  
	 * 
	 */
	
	public void List() throws Exception {
		RolePower rp;
		String year = getPara("year");
		String regionCode = getPara("regionCode");
		String userRegionId = getCurrentUserRegionId();
		String ispublic = getPara("ispublic");
		String ftype = getPara("ftype");
		int  pageSize = getParaToInt("pageSize");
		if(userRegionId.equals(regionCode) ||"450000000000".equals(userRegionId)) {//自治区账户给操作权限
			rp = RolePower.dao.getOperPower("1613",getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("1613",getCurrentUserId());
		}
		
		FormulaSelectVO formulaVO = Tools.getSubVO(FormulaSelectVO.class, getRequest());
		Page<Formula> pageList = Formula.dao.newPage(getPageNo(), pageSize,formulaVO,getCurrentUserId(),regionCode,year,ispublic,ftype);
		
		List<Formula> list = pageList.getList();
		String upload_Dir = PropertiesContent.get("attachment_rootDir");//获取上传位置
		String sKeyid;
		for(Formula formula : list) {
			sKeyid = formula.getStr("keyid");
			List<Attachment> attachment = Attachment.dao.find("SELECT path FROM base_attachment WHERE RelateId = ? and RelateType = ? LIMIT 1 ",sKeyid,"formula");
			if(attachment.size() > 0) {
				String name = attachment.get(0).getStr("path");
				String url = upload_Dir + name;
				formula.put("url", url);
			}
		}
		success(new PageVO(pageList,rp));
	}
	@PowerBind(code = "1003_DelPower", funcName = "删除案件公示")
	public void delete() {
		/*String keyid = getPara("keyid");
		Formula.dao.deleteById(keyid);
		success();*/
		
		String[] keyids = getPara("keyids").split(",");
		Formula.dao.deleteFormulaByIds(keyids);
		success();
	}
	
	/**
	 * 获取更新数据
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {
		String keyid = getPara("keyId");
		Formula c = Formula.dao.findById(keyid);
		
		String imgUrl = PropertiesContent.get("attachment_rootDir");//获取上传位置
		List<Attachment> attachment = Attachment.dao.find("SELECT path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"formula");
		for(Attachment a : attachment) {
			String name = a.getStr("path");
			String url = imgUrl + name;
			a.put("url", url);
		}
				
		success(new FormulaVO(c,attachment));
	}

	@ClearInterceptor(ClearLayer.ALL)
	public void editDataAndroid() {
		String keyid = getPara("keyid");
		Formula c = Formula.dao.loadFormulaDetail(keyid);
		
		String imgUrl = PropertiesContent.get("attachment_rootDir");//获取上传位置
		List<Attachment> attachment = Attachment.dao.find("SELECT path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"formula");
		for(Attachment a : attachment) {
			String name = a.getStr("path");
			String url = imgUrl + name;
			a.put("url", url);
		}
				
		success(new FormulaVO(c,attachment));
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1003_EditPower", funcName = "编辑案件公示")
	public void edit() {
		Formula f;
		try {
			f = getModel();
			f.set("releaseTime",dateTimeFormat.format(new Date()));
			f.update();
			success(f.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！", e);
			success(002);
		}
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Formula f;
		try {
			f = getModel();
			String sendcode = f.get("sendcode");
			String currentUserRegionId = getCurrentUserRegionId();
			
			f.set("keyid", Formula.dao.getId());
			//f.set("releaseTime",dateTimeFormat.format(new Date()));
			f.set("createUserId",getCurrentUserId());
			f.set("status", 4);
			
			if("450000000000".equals(currentUserRegionId)){//自治区操作添加功能 区域码是选择的区域，非自治区
				f.set("regionId",sendcode);
			}else{
				f.set("regionId",getCurrentUserRegionId());
			}			
			f.save();
			processAttachment(f.getStr("keyid"));
			success(f.get("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			success(003);
		}
	}
	
	/**
	 * 获取详情页数据
	 * @throws IOException 
	 */
	public void detailDatas() throws IOException {
		String keyid = getPara("keyId");
		Formula f = Formula.dao.findById(keyid);
		//if(f.getStr("content") == null) {
			String uploadUrl = PropertiesContent.get("upload_dir");//获取上传位置
			String rootDir = PropertiesContent.get("attachment_rootDir");//返回上传位置
			String filder = uploadUrl + "pdfTojpg";
			File file = new File(filder);
			//判断是否存在主文件夹，否则创建
			if(!file.exists()) {
				file.mkdirs();
			}
			String url = filder + "/" + f.getStr("keyid");
			File urlFile = new File(url);
			//根据keyid创建对应文件夹
			if(urlFile.exists()) {
				String[] jpgNumber = urlFile.list();
				for(int i = 1; i <= jpgNumber.length; i++) {
					File jpgFile = new File(url + "/" + i + ".jpg");
					jpgFile.delete();
				}
			} else {
				urlFile.mkdirs();
			}
			
			List<Attachment> attachment = Attachment.dao.find("SELECT keyid,path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"formula");
			for(Attachment a : attachment) {
				String pdfName = a.getStr("path");
				String pdfUrl = uploadUrl + pdfName;
				//转换
				pdfToJpg(pdfUrl,url);
				}
			File[] totalFile = urlFile.listFiles();	//获取图片总数
			List<String> list = new ArrayList<String>();	
			for(int i=0,k=1; i < totalFile.length; i++,k++) {
				list.add(rootDir + "pdfTojpg/" + f.getStr("keyid") + "/" + k +".jpg");
			}
			success(new formulaDetailVOs(f,list));
				
		/*} else {
			success(new formulaDetailVOs(f,null));
		}*/
		
	}
	
	/**
	 * pdf文件转换为jpg图片
	 * @param filePath
	 * @param jpgPath
	 * @throws IOException
	 */
	public void pdfToJpg(String filePath,String jpgPath) throws IOException {
        File file = new File(filePath);  
        RandomAccessFile raf = new RandomAccessFile(file, "r");  
        FileChannel channel = raf.getChannel();  
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel  
                .size());  
        PDFFile pdffile = new PDFFile(buf);  
 
        System.out.println("页数： " + pdffile.getNumPages());  
        
        //默认转换5页
        int NumPages = pdffile.getNumPages() >= 5 ? 5 : pdffile.getNumPages();
        for (int i = 1; i <= NumPages; i++) {             
            PDFPage page = pdffile.getPage(i);  
            
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()  
                    .getWidth(), (int) page.getBBox().getHeight());  
            int n = 2;
            Image img = page.getImage(rect.width * n, rect.height * n, // width &  
                                                                // height  
                    rect, // clip rect  
                    null, // null for the ImageObserver  
                    true, // fill background with white  
                    true // block until drawing is done  
                    );  
 
            BufferedImage tag = new BufferedImage(rect.width * n, rect.height * n,  
                    BufferedImage.TYPE_INT_RGB);  
            tag.getGraphics().drawImage(img, 0, 0, rect.width * n, rect.height * n,  
                    null); 
          
            FileOutputStream out = new FileOutputStream(jpgPath + "/" + i + ".jpg"); // 输出到文件流
            System.out.println("成功保存图片到 ：  " +jpgPath + "/" + i + ".jpg");
           
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag); // JPEG编码  
 
            out.close();
            }
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listFormulaForWeb(){
		Page<Formula> list = Formula.dao.page(getPageNo(), getPageSize());
		success(list);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listFormulaForAndroid(){
		String year = getPara("year");
		String regioncode = getPara("regioncode");
		Page<Formula> list = Formula.dao.newPageForAndroid(getPageNo(), getPageSize(),regioncode,year);
		success(list);
	}
	
}
