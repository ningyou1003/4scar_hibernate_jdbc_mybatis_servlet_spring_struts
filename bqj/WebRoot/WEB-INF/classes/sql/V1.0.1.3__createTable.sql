--新建通知表
CREATE TABLE `l_notice` (
	`KeyID` varchar(50) NOT NULL DEFAULT '',
	`Title` varchar(255) NULL COMMENT '标题',
	`Content` varchar(255) NULL COMMENT '内容',
	`Attachment` varchar(255) NULL COMMENT '附件',
	`randomNum` varchar(255) NULL COMMENT '附件标记',
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
--新建案例表
CREATE TABLE `l_regulations` (
	`KeyID` varchar(50) NOT NULL DEFAULT '',
	`Title` varchar(255) NULL COMMENT '标题',
	`Content` varchar(255) NULL COMMENT '内容',
	`Attachment` varchar(255) NULL COMMENT '附件',
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
--新建规定表
CREATE TABLE `l_stipulate` (
	`KeyID` varchar(50) NOT NULL DEFAULT '',
	`Title` varchar(255) NULL COMMENT '标题',
	`Content` varchar(255) NULL COMMENT '内容',
	`Attachment` varchar(255) NULL COMMENT '附件',
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
--新建规定表
CREATE TABLE `b_build` (
	`KeyID` varchar(50) NOT NULL DEFAULT '',
	`Title` varchar(255) NULL COMMENT '标题',
	`Content` varchar(255) NULL COMMENT '内容',
	`Attachment` varchar(255) NULL COMMENT '附件',
	PRIMARY KEY (`KeyID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;