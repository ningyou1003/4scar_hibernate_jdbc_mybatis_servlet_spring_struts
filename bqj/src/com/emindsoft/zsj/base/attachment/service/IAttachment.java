package com.emindsoft.zsj.base.attachment.service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by ym on 15-4-17.
 */
public interface IAttachment {

    /**
     * 上传文件
     * @param fileName 原始文件名
     * @param relateId 关联id, 此id来自业务, 一个id可对应有多个附件, 典型例子为一个表单可以有[多个]附件
     * @param relateType 关联类型, 自行确定, 如user_pic website_news之类的值, 典型例子为一个表单可以有[多种]附件
     * @param fileInputStream 文件输入流, 上传的文件的输入流
     * @return
     * @throws Exception
     */
    AttachmentInfo upload(String fileName, String relateId, String relateType, InputStream fileInputStream)throws Exception;
    AttachmentInfo upload(String fileName, String relateId, String relateType, InputStream fileInputStream, String msg)throws Exception;
    /**
     * 上传并覆盖文件
     * @param attachmentId 附件id
     * @param fileInputStream 文件输入流
     * @return
     * @throws Exception
     */
    AttachmentInfo overwrite(String attachmentId, InputStream fileInputStream)throws Exception;
    AttachmentInfo overwrite(String attachmentId, InputStream fileInputStream, String msg)throws Exception;

    /**
     * 更新已上传的文件
     * @param attachmentId 附件id
     * @param relateId 关联Id
     * @param relateType 关联类型
     * @return
     * @throws Exception
     */
    boolean update(String attachmentId, String relateId, String relateType)throws Exception;
    boolean update(String attachmentId, String msg)throws Exception;
    /**
     * 附件列表
     * @param relateId 关联id
     * @return
     * @throws Exception
     */
    List<AttachmentInfo> list1(String relateId)throws Exception;

    /**
     * 附件列表
     * @param relateId 关联id
     * @param relateType 关联类型
     * @return
     * @throws Exception
     */
    List<AttachmentInfo> list2(String relateId, String relateType)throws Exception;

    /**
     * 附件信息
     * @param attachmentId 附件id
     * @return
     * @throws Exception
     */
    AttachmentInfo info(String attachmentId)throws Exception;

    /**
     * 删除附件
     * @param attachmentId 附件id
     * @return
     * @throws Exception
     */
    boolean delete(String attachmentId)throws Exception;

}
