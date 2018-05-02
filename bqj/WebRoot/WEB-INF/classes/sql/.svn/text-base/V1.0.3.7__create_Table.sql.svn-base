--删除举报线索管理台账表
DROP TABLE IF EXISTS `t_account`;

--删除出版物市场检查管理台账表
DROP TABLE IF EXISTS `t_market`;

--删除案件统计月报表
DROP TABLE IF EXISTS `t_monthly`;

--删除印刷复制企业、出版物物流仓储企业、出版物集中销售场所管理台账表
DROP TABLE IF EXISTS `t_printing`;

--删除游商摊点主要分布区域管理台账表
DROP TABLE IF EXISTS `t_stalls`;

--删除督办案件管理台账表
DROP TABLE IF EXISTS `t_supervise`;



--新建模板表
CREATE TABLE `t_temple` (
`KeyID`  varchar(50) NOT NULL ,
`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
`Type`  varchar(100) NULL COMMENT '模板类型' ,
`Time`  datetime NULL COMMENT '输入时间' ,
PRIMARY KEY (`KeyID`)
);


--新建意见与建议表
CREATE TABLE `t_proposal` (
	`KeyID` varchar(50) NOT NULL ,
	`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
	`UserId` varchar(50) NULL COMMENT '用户id' ,
	`Title` varchar(255) NULL COMMENT '标题',
	`Content` varchar(255) NULL COMMENT '内容',
	`ReleaseTime` datetime NULL COMMENT '输入时间' ,
	`Status`  int(1) NULL COMMENT '审核状态' ,
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--新建台账表
CREATE TABLE `t_ledger` (
	`KeyID` varchar(50) NOT NULL ,
	`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
	`UserId` varchar(50) NULL COMMENT '用户id' ,
	`Title` varchar(255) NULL COMMENT '标题',
	`ReleaseTime` datetime NULL COMMENT '输入时间' ,
	`Status`  int(1) NULL COMMENT '审核状态' ,
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--新建统计表
CREATE TABLE `t_statistics` (
	`KeyID` varchar(50) NOT NULL ,
	`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
	`UserId` varchar(50) NULL COMMENT '用户id' ,
	`Title` varchar(255) NULL COMMENT '标题',
	`ReleaseTime` datetime NULL COMMENT '输入时间' ,
	`Status`  int(1) NULL COMMENT '审核状态' ,
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;