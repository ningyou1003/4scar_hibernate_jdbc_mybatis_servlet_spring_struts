-- 删除区域联防-制度表
DROP TABLE IF EXISTS `zd_system`;
-- 新建区域联防-制度表
CREATE TABLE `zd_system` (
`keyid`  varchar(50) NOT NULL COMMENT '制度id' ,
`title`  varchar(50) NULL COMMENT '制度标题' ,
`content`  varchar(1000) NULL COMMENT '制度内容' ,
`publishTime`  timestamp NULL COMMENT '制度发布时间' ,
`publisherId`  varchar(50) NULL COMMENT '制度发布者id' ,
PRIMARY KEY (`keyid`)
)
;

ALTER TABLE `a_activity`
ADD COLUMN `content`  varchar(200) NULL COMMENT '活动内容简介' AFTER `referenceId`,
ADD COLUMN `type`  tinyint(2) NULL COMMENT '活动类型，1：清源  2：净网  3：护苗  4：固边  5：秋风  6：其他' AFTER `content`;
