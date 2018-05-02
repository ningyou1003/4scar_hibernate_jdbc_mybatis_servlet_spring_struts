-- 删除区域联防表
DROP TABLE IF EXISTS `z_zonedefence`;
-- 新建区域联防表
CREATE TABLE `z_zonedefence` (
  `keyid` varchar(50) NOT NULL COMMENT '区域联防id',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `type` varchar(20) DEFAULT NULL COMMENT '区域联防分类，file：文件 , active：活动 , system ： 制度',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
  `attachmentId` varchar(20) DEFAULT NULL COMMENT '附件id',
  PRIMARY KEY (`keyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 删除通知表
DROP TABLE IF EXISTS `d_notice`;
-- 新建通知表
CREATE TABLE `d_notice` (
`keyid`  varchar(50) NOT NULL COMMENT '通知id' ,
`title`  varchar(50) NULL COMMENT '通知标题' ,
`content`  varchar(200) NULL COMMENT '通知内容' ,
`publishTime`  timestamp NULL COMMENT '通知发布时间' ,
`publisherId`  varchar(50) NULL COMMENT '通知发布者id' ,
PRIMARY KEY (`keyid`)
)
;
