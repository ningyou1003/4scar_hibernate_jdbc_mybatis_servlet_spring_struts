-- 删除区域联防-文件表
DROP TABLE IF EXISTS `zd_file`;
-- 新建区域联防-文件表
CREATE TABLE `zd_file` (
  `keyid` varchar(50) NOT NULL,
  `filename` varchar(50) DEFAULT NULL COMMENT '文件名',
  `brief` varchar(200) DEFAULT NULL COMMENT '文件简介',
  `uploadTime` timestamp NULL DEFAULT NULL COMMENT '上传时间',
  `uploaderId` varchar(50) DEFAULT NULL COMMENT '上传者id',
  PRIMARY KEY (`keyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 删除区域联防-活动表
DROP TABLE IF EXISTS `zd_active`;
-- 新建区域联防-活动表
CREATE TABLE `zd_active` (
  `keyid` varchar(50) NOT NULL,
  `activeName` varchar(50) DEFAULT NULL COMMENT '活动标题',
  `startTime` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `endTime` timestamp NULL DEFAULT NULL COMMENT '活动结束时间',
  `holdOrganization` varchar(50) DEFAULT NULL COMMENT '活动举办单位',
  `joinOrganization` varchar(50) DEFAULT NULL COMMENT '参加活动单位',
  `content` varchar(200) DEFAULT NULL COMMENT '活动内容',
  PRIMARY KEY (`keyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;