-- 删除文件表
DROP TABLE IF EXISTS `d_document`;
-- 新建文件表
CREATE TABLE `d_document` (
`keyid`  varchar(50) NOT NULL ,
`docname`  varchar(50) NULL COMMENT '文件标题' ,
`brief`  varchar(200) NULL COMMENT '文件简介' ,
`uploader`  varchar(20) NULL COMMENT '文件上传者' ,
`checker`  varchar(20) NULL COMMENT '文件审核者' ,
`uploaddate`  timestamp NOT NULL COMMENT '文件上传时间' ,
`checkdate`  timestamp NULL COMMENT '文件审核时间' ,
`status`  tinyint(1) NULL COMMENT '审核状态，0未审核，1审核通过，2审核未通过',
`res`  varchar(50) NULL COMMENT '文件来源' ,
PRIMARY KEY (`keyid`)
)
;

-- 删除活动表
DROP TABLE IF EXISTS `a_activity`;
-- 新建活动表
CREATE TABLE `a_activity` (
`keyid`  varchar(50) NOT NULL COMMENT '活动id' ,
`activityName`  varchar(50) NULL COMMENT '活动名称' ,
`startTime`  timestamp NULL COMMENT '活动开始时间' ,
`endTime`  timestamp NULL COMMENT '活动结束时间' ,
`holdOrganization`  varchar(50) NULL COMMENT '活动举办单位' ,
`joinOrganization`  varchar(50) NULL COMMENT '参与单位' ,
`referenceId`  varchar(50) NULL COMMENT '活动参考文件id' ,
PRIMARY KEY (`keyid`)
)
;