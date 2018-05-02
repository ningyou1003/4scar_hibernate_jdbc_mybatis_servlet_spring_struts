-- 为菜单中各个模块添加regionId字段，用于保存区域编码
-- 专项活动表
ALTER TABLE `a_activity`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `a_alarm`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `a_appeal`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `b_build`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `c_case`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `c_record`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

-- 文件通知--文件表
ALTER TABLE `d_document`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ,
MODIFY COLUMN `uploader`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件上传者id' ,
MODIFY COLUMN `checker`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件审核者' ;

-- 文件通知--通知表
ALTER TABLE `d_notice`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

-- 事项管理表
ALTER TABLE `flow_todo`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `l_law`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `l_notice`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `l_ppt`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `l_regulations`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `l_stipulate`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `n_dynamic`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `n_events`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `p_bulletin`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `p_other`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `p_show`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `p_silhouette`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `p_video`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

ALTER TABLE `s_msg`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;

-- 区域联防表
ALTER TABLE `z_zonedefence`
ADD COLUMN `regionId`  varchar(50) NULL COMMENT '所属区域编码' ;