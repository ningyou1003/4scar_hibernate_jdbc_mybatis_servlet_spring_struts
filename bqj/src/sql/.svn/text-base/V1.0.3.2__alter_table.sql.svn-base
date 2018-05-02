--删除课件表
DROP TABLE IF EXISTS `l_ppt`;

--新建司法解析表
CREATE TABLE `l_judicial` (
`KeyID`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`Title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题' ,
`Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' ,
`ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' ,
`UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id' ,
`regionId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域编码' ,
PRIMARY KEY (`KeyID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;