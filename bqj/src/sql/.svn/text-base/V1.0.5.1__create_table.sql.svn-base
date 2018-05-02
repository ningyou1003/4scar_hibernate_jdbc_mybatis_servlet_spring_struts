CREATE TABLE `c_formula` (
  `KeyId` varchar(50) NOT NULL DEFAULT '',
  `CreateUserId` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `Title` varchar(255) DEFAULT NULL COMMENT '标题',
  `Content` longtext COMMENT '内容',
  `ReleaseTime` datetime DEFAULT NULL COMMENT '发布时间',
  `status` int(1) DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报',
  `Name` varchar(255) DEFAULT NULL COMMENT '图片文件名称',
  `regionId` varchar(50) DEFAULT NULL COMMENT '所属区域编码',
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `c_formulayear` (
  `KeyId` varchar(50) NOT NULL,
  `Region` varchar(50) DEFAULT NULL COMMENT '案件公示年份',
  PRIMARY KEY (`keyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
