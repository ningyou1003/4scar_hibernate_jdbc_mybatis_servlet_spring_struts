package com.emindsoft.zsj.culture.ctrl;

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
import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.culture.model.Other;
import com.emindsoft.zsj.culture.vo.OtherDetailVO;
import com.emindsoft.zsj.culture.vo.OtherDetailVOs;
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
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import cn.dreampie.routebind.ControllerKey;

@ControllerKey("other")
public class OtherCtrl extends AdminBaseController<Other> {
	
	public OtherCtrl() {
		this.modelClass = Other.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Other other;
		try {
			other = getModel();
			other.set("keyid", Other.dao.getId());
//			other.set("releaseTime",dateTimeFormat.format(new Date()));
			other.set("UserId",getCurrentUserId());
			other.set("status", 4);
			//修改为保存时的区域是当前选择的区域
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
			other.set("regionId",RegionId);
			other.save();
			processAttachment(other.getStr("keyid"));
			success(other.get("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1615_DelPower", funcName = "文化宣传-其他删除")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Other.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1615_EditPower", funcName = "文化宣传-其他更新")
	public void edit() {
		Other other;
		try {
			other = getModel();
			other.set("UserId",getCurrentUserId());
//			other.set("releaseTime",dateTimeFormat.format(new Date()));
			other.update();
			success(other.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！", e);
			error(002, "保存失败");
		}
	}
	
	/**
	 * 获取更新数据
	 */
	public void editData() {
		String keyid = getPara("keyId");
		Other other = Other.dao.findById(keyid);
		success(other);
	}
	
	/**
	 * 分页列表
	 * 
	 */
	public void List() {
		String selectRegionId;
		RolePower rp;
		String SelectTitle = getPara("SelectTitle");//按标题查询
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		Role r=Role.dao.findRolesByUser(getCurrentUserId());
		int orderid=0;
		if (r!=null) {
			orderid=r.getInt("orderid");
		}
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId) 
				|| (parentregion.contains(userRegionId) && 5==orderid)) {
			rp = RolePower.dao.getOperPower("1615", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("1615",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		if (!"undefined".equals(chooseRegionId)) {
			selectRegionId=chooseRegionId;
		}
		cultureSelectVO sv = Tools.getSubVO(cultureSelectVO.class, getRequest());
		Page<Other> pageList = Other.dao.page(getPageNo(), getPageSize(),getCurrentUserId(),sv,selectRegionId);
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getOtherList() {  
		Page<Other> pageList = Other.dao.page(getPageNo(), getPageSize());
		success(pageList);
	}
	
	/**
	 *  详情页数据
	 */
	public void detailData() {
		String keyid = getPara("keyId");
		Other other = Other.dao.findById(keyid);
		List<Attachment> attachment = null;
		if(other.getStr("content") == null) {
			String imgUrl = PropertiesContent.get("attachment_rootDir");//获取上传位置
			attachment = Attachment.dao.find("SELECT path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"other");
			for(Attachment a : attachment) {
				String name = a.getStr("path");
				String url = imgUrl + name;
				a.put("url", url);
			}
			success(new OtherDetailVO(other,attachment));
		} else {
			success(new OtherDetailVO(other,attachment));
		}
		
	}
	
	/**
	 * 获取详情页数据
	 * @throws IOException 
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void detailDatas() throws IOException {
		String keyid = getPara("keyId");
		Other other = Other.dao.findById(keyid);
		if(other.getStr("content") == null) {
			String uploadUrl = PropertiesContent.get("upload_dir");//获取上传位置
			String rootDir = PropertiesContent.get("attachment_rootDir");//返回上传位置
			String filder = uploadUrl + "pdfTojpg";
			File file = new File(filder);
			//判断是否存在主文件夹，否则创建
			if(!file.exists()) {
				file.mkdirs();
			}
			String url = filder + "/" + other.getStr("keyid");
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
			
			List<Attachment> attachment = Attachment.dao.find("SELECT keyid,path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"other");
			for(Attachment a : attachment) {
				String pdfName = a.getStr("path");
				String pdfUrl = uploadUrl + pdfName;
				//转换
				pdfToJpg(pdfUrl,url);
				}
			File[] totalFile = urlFile.listFiles();	//获取图片总数
			List<String> list = new ArrayList<String>();	
			for(int i=0,k=1; i < totalFile.length; i++,k++) {
				list.add(rootDir + "pdfTojpg/" + other.getStr("keyid") + "/" + k +".jpg");
			}
			success(new OtherDetailVOs(other,list));
				
		} else {
			success(new OtherDetailVOs(other,null));
		}
		
	}
	
	/**
	 * pdf文件转换为jpg图片
	 * @param filePath
	 * @param jpgPath
	 * @throws IOException
	 */
	public void pdfToJpg(String filePath,String jpgPath) throws IOException {
		// load a pdf from a byte buffer  
        File file = new File(filePath);  
        RandomAccessFile raf = new RandomAccessFile(file, "r");  
        FileChannel channel = raf.getChannel();  
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel  
                .size());  
        PDFFile pdffile = new PDFFile(buf);  
 
        System.out.println("页数： " + pdffile.getNumPages());  
 
//        String getPdfFilePath = System.getProperty("user.dir")+"\\pdfPicFile";
       
//        System.out.println("getPdfFilePath is  :"+jpgPath);
        
        //默认转换10页
        int NumPages = pdffile.getNumPages() >= 10 ? 10 : pdffile.getNumPages();
        for (int i = 1; i <= NumPages; i++) {  
            // draw the first page to an image  
            PDFPage page = pdffile.getPage(i);  
 
            // get the width and height for the doc at the default zoom  
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()  
                    .getWidth(), (int) page.getBBox().getHeight());  
 
            int n = 2;
            // generate the image  
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

}
