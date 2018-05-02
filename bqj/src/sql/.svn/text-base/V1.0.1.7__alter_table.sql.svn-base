ALTER TABLE `a_alarm`
MODIFY COLUMN `Atime`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间' AFTER `AfeedBacker`,
MODIFY COLUMN `Aphone`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举报人联系电话' AFTER `Atime`,
MODIFY COLUMN `Adescribe`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '事实与线索' AFTER `Aphone`,
MODIFY COLUMN `Afounder`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人' AFTER `Adescribe`,
ADD COLUMN `RealName`  varchar(50) NULL COMMENT '真实姓名' AFTER `randomNum`,
ADD COLUMN `Gender`  int(1) NULL COMMENT '性别' AFTER `RealName`,
ADD COLUMN `Aliases`  varchar(50) NULL COMMENT '化名' AFTER `Gender`,
ADD COLUMN `Email`  varchar(50) NULL COMMENT '电子邮件' AFTER `Aliases`,
ADD COLUMN `PostCode`  varchar(50) NULL COMMENT '邮政编码' AFTER `Email`,
ADD COLUMN `Address`  varchar(255) NULL COMMENT '通讯地址' AFTER `PostCode`,
ADD COLUMN `Object`  varchar(255) NULL COMMENT '举报对象个人或单位' AFTER `Address`,
ADD COLUMN `ObjectTel`  varchar(50) NULL COMMENT '举报对象联系电话' AFTER `Object`,
ADD COLUMN `ObjectAdd`  varchar(255) NULL COMMENT '举报对象地址' AFTER `ObjectTel`;