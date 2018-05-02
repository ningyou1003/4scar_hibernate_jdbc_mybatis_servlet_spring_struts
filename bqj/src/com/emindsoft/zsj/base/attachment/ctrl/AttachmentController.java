package com.emindsoft.zsj.base.attachment.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.BaseController;
import com.emindsoft.zsj.util.PropertiesContent;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;

/**
 * Created by ym on 15-4-28.
 */
@ControllerKey("attachment")
public class AttachmentController extends BaseController {

    /**
     * 上传文件
     * @throws Exception
     */
    public void upload()throws Exception{
        //有文件上传的, 先调用这个方法获取文件, 才能取得表单域提交的数据. 多个文件上传可以调用getFiles方法
    	String basePath = PropertiesContent.get("upload_dir")+"upload";
        List<UploadFile> uploadFiles = getFiles(basePath);

        String relateId = getPara("relateId"); //有附件的业务数据的id
        String relateType = getPara("relateType"); //类型

        //调用附件服务保存文件
        if(uploadFiles!=null){
            //参数1为业务数据id, 参数2为附件类型(自定), 参数3为文件
//            AttachmentInfo attachmentInfo = AttachmentService.instance.upload("", "", uploadFile);
            //attachmentInfo包含已保存附件的信息
//            renderJson(attachmentInfo);
        	String suffix = "";
        	String id = "";
        	String path = "upload";
            for(UploadFile uf : uploadFiles){
            	suffix = getSuffix(uf.getFileName());
                id = UUID.randomUUID().toString().replace("-", "");
                if(suffix==null || suffix.length()==0){
                    path = path + System.getProperty("file.separator") + id;
                }else{
                	id = id + "." + suffix;
                    path = path + System.getProperty("file.separator") + id ;
                }
                //保存
                Attachment a = new Attachment();
                
                a.set("relateType", relateType)
                		.set("keyid", id)
                        .set("relateId", relateId)
                        .set("path",path)
                        .set("name", uf.getOriginalFileName())
                        .set("uploadDate", new Date())
                        .set("uploadUserId", getCurrentUserId())
                        .save();
                //改名
                uf.getFile().renameTo(new File(uf.getSaveDirectory() + File.separator + id));
            }
        }

        success();
    }

    /**
     * 文件列表
     * @throws Exception
     */
    public void list()throws Exception{
        String relateId = getPara("relateId"); //有附件的业务数据的id
        String relateType = getPara("relateType"); //类型

        //调用附件服务
        List<Attachment> list = Attachment.dao.find("select * from base_attachment where relateType=? and relateId=?", relateType, relateId);
        for (Attachment attachment : list) {
        	HttpServletRequest request = getRequest();
//        	String url = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();
        	String url = PropertiesContent.get("outer_net");
        	url += "/attachment/download?attachmentId="+attachment.getStr("keyid");
        	attachment.put("url", url);
		}
        success(list);
    }

    /**
     * 文件列表
     * @throws Exception
     */
    public void delete()throws Exception{
        String attachmentId = getPara("attachmentId"); //附件id
        Attachment attachment = Attachment.dao.findFirst("select * from " + Attachment.table + " where KeyId=?", attachmentId);

        //删除本地
        String rootDir = PropertiesContent.get("upload_dir");
        String path = attachment.getStr("path");
        File file = new File(rootDir + path);
        boolean result = !(file.exists()) || file.delete();
        if(result){
            //删除数据库
            Db.update("delete from " + Attachment.table + " where KeyId=?", attachmentId);
        }
        success(result);
    }

    public void download()throws Exception{
        String attachmentId = getPara("attachmentId");
        Attachment info = Attachment.dao.findById(attachmentId);
        if(info==null){
            renderNull();
            return;
        }
        String rootDir = PropertiesContent.get("upload_dir");
        String path = info.getStr("path");
        String fileDisplay = info.getStr("name");
        fileDisplay = URLEncoder.encode(fileDisplay, "UTF-8");
        getResponse().addHeader("Content-Disposition", "attachment;filename=" + fileDisplay);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(rootDir+path);
            IOUtils.copy(fis, getResponse().getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            fis.close();
        }

        renderNull();
    }
    
    /**
     * 根据文件名获取文件后缀
     * @param fileName
     * @return
     */
    private String getSuffix(String fileName){
        if(fileName==null || fileName.length()==0 || !fileName.contains(".")){
            return "";
        }

        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        return suffix;
    }
    
    /**
     * 批量复制并重命名文件
     */
    public void copyAttr(String relateId,String newRelateId) {
//    	String relateId = getPara("relateId");
		List<Attachment> attList = Attachment.dao.findAtt(relateId);
		Attachment attachment;
		String attId, rootDir, path, fName, suffix;
		if(attList.size()>0){//循环附件信息列表
			for (Attachment att : attList) {
				attId = att.getStr("keyid");
				/*复制附件文件*/
				rootDir = PropertiesContent.get("upload_dir");
			    path = att.getStr("path");
			    suffix = getSuffix(path);
			    File s = new File(rootDir+path);	
			    String newKeyid = Attachment.dao.getId();
			    path = path.replace(attId, newKeyid);
			    File t = new File(rootDir+path);
			    fileChannelCopy(s, t);
			    /*保存复制文件的信息到数据库,并修改复制的附件信息的关联id及路径*/
			    attachment = att;
			    attachment.set("keyid", newKeyid);
			    attachment.set("path", path);
			    attachment.set("RelateId", newRelateId);
			    attachment.save();
			}
		}
	}
    
    /**
     * 批量复制并重命名文件
     */
    public void copyAttr(String[] relateIds,String[] newRelateIds) {
    	for(int i = 0; i < relateIds.length; i++){
    		copyAttr(relateIds[i], newRelateIds[i]);
    	}
    }
    
    /**
     * 使用文件通道的方式复制文件
     * @param s 源文件
     * @param t 复制到的新文件
     */
    public void fileChannelCopy(File s, File t) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
