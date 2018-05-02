package com.emindsoft.zsj.base.attachment.service;

import com.emindsoft.zsj.util.PropertiesContent;
import com.jfinal.upload.UploadFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 15-4-22.
 */
public class AttachmentService {

    public static final AttachmentService instance = new AttachmentService();

    private IAttachment iAttachment;

    private AttachmentService(){

    }

    /**
     * 上传文件
     * @param relateId 关联id, 此id来自业务, 一个id可对应有多个附件, 典型例子为一个表单可以有多个附件
     * @param relateType 关联类型, 自行确定, 如user_pic website_news之类的值, 典型例子为一个表单可以有[多种]附件
     * @param uploadFile 上传的文件
     * @return
     * @throws Exception
     */
    public AttachmentInfo upload(String relateId, String relateType, UploadFile uploadFile)throws Exception{
        FileInputStream fio = new FileInputStream(uploadFile.getFile());
        AttachmentInfo attachmentInfo = iAttachment.upload(uploadFile.getOriginalFileName(), relateId, relateType, fio);
        if(attachmentInfo!=null){
            String attachmentServerUrl = PropertiesContent.get("attachment_server");
            attachmentInfo.setUrl(attachmentServerUrl + "/download?attachmentId=" + attachmentInfo.getId());
            fio.close();
        }
        uploadFile.getFile().delete();
        return attachmentInfo;
    }
    public AttachmentInfo upload(String relateId, String relateType, UploadFile uploadFile, String msg)throws Exception{
        FileInputStream fio = new FileInputStream(uploadFile.getFile());
        AttachmentInfo attachmentInfo = iAttachment.upload(uploadFile.getOriginalFileName(), relateId, relateType, fio, msg);
        if(attachmentInfo!=null){
            String attachmentServerUrl = PropertiesContent.get("attachment_server");
            attachmentInfo.setUrl(attachmentServerUrl + "/download?attachmentId=" + attachmentInfo.getId());
            fio.close();
        }
        uploadFile.getFile().delete();
        return attachmentInfo;
    }

    /**
     * 上传并覆盖文件
     * @param attachmentId 附件id
     * @param fileInputStream 上传的文件
     * @return
     * @throws Exception
     */
    public void upload(String attachmentId, InputStream fileInputStream)throws Exception{
        iAttachment.overwrite(attachmentId, fileInputStream);
    }
    public void upload(String attachmentId, InputStream fileInputStream, String msg)throws Exception{
        iAttachment.overwrite(attachmentId, fileInputStream, msg);
    }

    /**
     * 更新已上传的文件
     * @param attachmentId 附件id
     * @param relateId 关联Id
     * @param relateType 关联类型
     * @return
     * @throws Exception
     */
    public boolean update(String attachmentId, String relateId, String relateType)throws Exception{
        return iAttachment.update(attachmentId, relateId, relateType);
    }
    public boolean update(String attachmentId, String msg)throws Exception{
        return iAttachment.update(attachmentId,  msg);
    }
    
    /**
     * 附件列表
     * @param relateId 关联id
     * @return
     * @throws Exception
     */
    public List<AttachmentInfo> list(String relateId)throws Exception{
        List<AttachmentInfo> list = iAttachment.list1(relateId);
        if(list==null){
            list = new ArrayList<AttachmentInfo>();
        }
        String attachmentServerUrl = PropertiesContent.get("attachment_server");
        for(AttachmentInfo att : list){
            att.setUrl(attachmentServerUrl + "/download?attachmentId=" + att.getId());
        }
        return list;
    }

    /**
     * 附件列表
     * @param relateId 关联id
     * @param relateType 关联类型
     * @return
     * @throws Exception
     */
    public List<AttachmentInfo> list(String relateId, String relateType)throws Exception{
        List<AttachmentInfo> list = iAttachment.list2(relateId, relateType);
        if(list==null){
            list = new ArrayList<AttachmentInfo>();
        }
        String attachmentServerUrl = PropertiesContent.get("attachment_server");
        for(AttachmentInfo att : list){
            att.setUrl(attachmentServerUrl + "/download?attachmentId=" + att.getId());
        }
        return list;
    }

    /**
     * 附件信息
     * @param attachmentId 附件id
     * @return
     * @throws Exception
     */
    public AttachmentInfo info(String attachmentId)throws Exception{
        return iAttachment.info(attachmentId);
    }

    /**
     * 删除附件
     * @param attachmentId 附件id
     * @return
     * @throws Exception
     */
    public boolean delete(String attachmentId)throws Exception{
        return iAttachment.delete(attachmentId);
    }

    /**
     * 删除附件
     * @param relateId 关联id
     * @return
     * @throws Exception
     */
    public boolean deleteByRelate(String relateId)throws Exception{
        List<AttachmentInfo> list = list(relateId);
        for(AttachmentInfo att : list){
            delete(att.getId());
        }
        return true;
    }

    /**
     * 删除附件
     * @param relateId 附件id
     * @param relateType 附件id
     * @return
     * @throws Exception
     */
    public boolean deleteByRelate(String relateId, String relateType)throws Exception{
        List<AttachmentInfo> list = list(relateId, relateType);
        for(AttachmentInfo att : list){
            delete(att.getId());
        }
        return true;
    }

}
