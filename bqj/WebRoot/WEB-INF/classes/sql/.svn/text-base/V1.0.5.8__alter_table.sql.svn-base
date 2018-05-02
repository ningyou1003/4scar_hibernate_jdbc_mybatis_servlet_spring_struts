--为以下表增加字段：是否公开（"0"代表否，"1"代表是）; 上报人ID； 上报时间
ALTER TABLE `p_bulletin`
ADD COLUMN `IsPublic`  int(1) NULL DEFAULT 0 COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;


ALTER TABLE `p_other`
ADD COLUMN `IsPublic`  int(1) NULL DEFAULT 0 COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `p_show`
ADD COLUMN `IsPublic`  int(1) NULL DEFAULT 0 COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `p_silhouette`
ADD COLUMN `IsPublic`  int(1) NULL DEFAULT 1 COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `p_video`
ADD COLUMN `IsPublic`  int(1) NULL DEFAULT 1 COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `a_activity`
ADD COLUMN `IsPublic`  int(1) NULL DEFAULT 0 COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `z_zonedefence`
ADD COLUMN `IsPublic`  int(1) NULL DEFAULT 1 COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `t_statistics`
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `Status`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `t_ledger`
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `Status`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;

ALTER TABLE `t_proposal`
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人id' AFTER `Status`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报人时间' AFTER `reportPersonId`;
