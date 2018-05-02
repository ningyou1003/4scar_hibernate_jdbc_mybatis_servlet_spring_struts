ALTER TABLE `a_alarm`
CHANGE COLUMN `Atitle` `Title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案件标题' AFTER `KeyId`,
CHANGE COLUMN `Afounder` `CreateUserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人' AFTER `Adescribe`,
ADD COLUMN `Status`  int(1) NULL AFTER `ObjectAdd`;

ALTER TABLE `a_appeal`
CHANGE COLUMN `Founder` `CreateUserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `Describe`,
ADD COLUMN `Status`  int(1) NULL AFTER `randomNum`;

ALTER TABLE `c_case`
ADD COLUMN `CreateUserId`  varchar(50) NULL AFTER `randomNum`,
ADD COLUMN `Status`  int(1) NULL AFTER `CreateUserId`;

ALTER TABLE `n_dynamic`
ADD COLUMN `CreateUserId`  varchar(50) NULL AFTER `randomNum`,
ADD COLUMN `Status`  int(1) NULL AFTER `CreateUserId`;

ALTER TABLE `n_events`
ADD COLUMN `CreateUserId`  varchar(50) NULL AFTER `randomNum`,
ADD COLUMN `Status`  int(1) NULL AFTER `CreateUserId`;

CREATE TABLE `c_record` (
  `KeyId` varchar(50) NOT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Content` text,
  `Type` varchar(50) DEFAULT NULL,
  `Time` datetime DEFAULT NULL,
  `randomNum` varchar(255) DEFAULT NULL,
  `Creator` varchar(50) DEFAULT NULL,
  `CreateUserId` varchar(50) DEFAULT NULL,
  `Status` int(1) DEFAULT NULL,
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;