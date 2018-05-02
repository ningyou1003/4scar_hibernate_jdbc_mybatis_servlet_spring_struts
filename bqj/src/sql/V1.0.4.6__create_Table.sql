CREATE TABLE `n_history` (
  `KeyId` varchar(50) NOT NULL,
  `Title` varchar(255) DEFAULT NULL COMMENT '标题',
  `Time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `Month` varchar(50) DEFAULT NULL,
  `Content` text COMMENT '详细内容',
  `randomNum` varchar(255) DEFAULT NULL COMMENT '附件',
  `CreateUserId` varchar(50) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL,
  `regionId` varchar(50) DEFAULT NULL COMMENT '所属区域编码',
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `n_leader` (
  `KeyId` varchar(50) NOT NULL,
  `Title` varchar(255) DEFAULT NULL COMMENT '标题',
  `Time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `Month` varchar(50) DEFAULT NULL,
  `Content` text COMMENT '详细内容',
  `randomNum` varchar(255) DEFAULT NULL COMMENT '附件',
  `CreateUserId` varchar(50) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL,
  `regionId` varchar(50) DEFAULT NULL COMMENT '所属区域编码',
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;