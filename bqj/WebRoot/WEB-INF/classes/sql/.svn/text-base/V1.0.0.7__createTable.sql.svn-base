CREATE TABLE `n_dynamic` (
  `KeyId` varchar(50) NOT NULL,
  `Title` varchar(255) DEFAULT NULL COMMENT '标题',
  `Time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `Type` varchar(50) DEFAULT NULL COMMENT '类型',
  `Creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `Content` text COMMENT '详细内容',
  `Url` varchar(255) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `n_events` (
  `KeyId` varchar(50) NOT NULL,
  `Title` varchar(255) DEFAULT NULL COMMENT '标题',
  `Time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `Month` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL COMMENT '类型',
  `Creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `Content` text COMMENT '详细内容',
  `Url` varchar(255) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;