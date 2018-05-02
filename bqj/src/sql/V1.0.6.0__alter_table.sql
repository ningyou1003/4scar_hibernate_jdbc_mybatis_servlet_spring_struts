ALTER TABLE `a_alarm`
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `regionId`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`,
ADD COLUMN `Opinion`  text NULL COMMENT '处理意见' AFTER `reportTime`,
ADD COLUMN `OpinionTime`  datetime NULL COMMENT '处理时间' AFTER `Opinion`,
ADD COLUMN `Creator`  varchar(50) NULL AFTER `OpinionTime`;

ALTER TABLE `a_appeal`
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `regionId`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`,
ADD COLUMN `Opinion`  text NULL COMMENT '处理意见' AFTER `reportTime`,
ADD COLUMN `OpinionTime`  datetime NULL COMMENT '处理时间' AFTER `Opinion`,
ADD COLUMN `Creator`  varchar(50) NULL AFTER `OpinionTime`;

ALTER TABLE `c_case`
ADD COLUMN `IsPublic`  int(1) NULL COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`;

ALTER TABLE `c_formula`
ADD COLUMN `IsPublic`  int(1) NULL COMMENT '是否公开' AFTER `sendcode`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`,
ADD COLUMN `ftype`  int(1) NULL COMMENT '案件类型' AFTER `reportTime`;

ALTER TABLE `n_dynamic`
ADD COLUMN `IsPublic`  int(1) NULL COMMENT '是否公开，0表示默认不公开，1表示公开' AFTER `Month`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`;

ALTER TABLE `n_events`
ADD COLUMN `IsPublic`  int(1) NULL COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`;

ALTER TABLE `n_history`
ADD COLUMN `IsPublic`  int(1) NULL COMMENT '是否公开' AFTER `regionId`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`,
ADD COLUMN `Creator`  varchar(50) NULL AFTER `reportTime`;

ALTER TABLE `n_leader`
ADD COLUMN `IsPublic`  int(1) NULL COMMENT '是否公开' AFTER `lyear`,
ADD COLUMN `reportPersonId`  varchar(50) NULL COMMENT '上报人' AFTER `IsPublic`,
ADD COLUMN `reportTime`  datetime NULL COMMENT '上报时间' AFTER `reportPersonId`,
ADD COLUMN `Creator`  varchar(50) NULL AFTER `reportTime`;