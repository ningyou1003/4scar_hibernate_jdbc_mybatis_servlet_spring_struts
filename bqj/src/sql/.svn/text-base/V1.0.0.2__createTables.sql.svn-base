CREATE TABLE `a_alarm` (
  `KeyId` varchar(50) NOT NULL,
  `Atitle` varchar(255) DEFAULT NULL COMMENT '案件标题',
  `AfeedBacker` varchar(50) DEFAULT NULL COMMENT '报案人',
  `Agender` int(1) DEFAULT NULL,
  `Atime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `AreginCode` varchar(50) DEFAULT NULL COMMENT '所在区域',
  `Aphone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `Address` varchar(255) DEFAULT NULL COMMENT '案发地点',
  `Adescribe` text COMMENT '案件描述',
  `Astatus` int(1) DEFAULT NULL,
  `Aisvailable` int(1) DEFAULT NULL,
  `Afounder` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
