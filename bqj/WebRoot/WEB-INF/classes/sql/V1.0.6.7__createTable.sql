CREATE TABLE `p_media` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `UserId` varchar(50) NOT NULL COMMENT '录入用户ID',
  `Title` varchar(255) NOT NULL COMMENT '标题',
  `Content` longtext COMMENT '内容',
  `ReleaseTime` datetime NOT NULL COMMENT '发布时间',
  `status` int(1) DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报',
  `regionId` varchar(50) DEFAULT NULL COMMENT '所属区域编码',
  `IsPublic` int(1) DEFAULT '0' COMMENT '是否公开',
  `reportPersonId` varchar(50) DEFAULT NULL COMMENT '上报人id',
  `reportTime` datetime DEFAULT NULL COMMENT '上报人时间',
  `DepartmentNumber` varchar(50) DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;