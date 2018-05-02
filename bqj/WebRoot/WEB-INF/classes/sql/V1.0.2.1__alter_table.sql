CREATE TABLE `s_area_member` (
  `KeyId` varchar(255) NOT NULL,
  `regionCode` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `job2` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8