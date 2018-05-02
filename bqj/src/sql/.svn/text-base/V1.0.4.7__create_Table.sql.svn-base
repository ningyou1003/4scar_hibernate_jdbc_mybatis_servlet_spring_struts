--新建经验介绍表
CREATE TABLE `d_experience` (
`keyid`  varchar(50) NOT NULL ,
`experiencename`  varchar(50) NULL COMMENT '标题' ,
`brief`  longtext NULL COMMENT '简介'  ,
`uploader`  varchar(50) NULL COMMENT '上传者id' ,
`checker`  varchar(50) NULL COMMENT '审核者' ,
`uploaddate`  timestamp NOT NULL COMMENT '文件上传时间' ,
`checkdate`  timestamp NULL COMMENT '文件审核时间' ,
`status`  int(1) NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报' ,
`res`  varchar(50) NULL COMMENT '文件来源' ,
`regionId`  varchar(50) NULL COMMENT '所属区域编码' ,
PRIMARY KEY (`keyid`)
)
;