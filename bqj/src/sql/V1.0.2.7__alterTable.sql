--对应表添加审核状态字段status,修改创建人id字段UserId为createUserId

ALTER TABLE `p_bulletin`
CHANGE COLUMN `UserId` `CreateUserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id' AFTER `KeyID`,
ADD COLUMN `Status`  bit NULL COMMENT '审核状态' AFTER `ReleaseTime`;

ALTER TABLE `p_other`
CHANGE COLUMN `UserId` `CreateUserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id' AFTER `KeyID`,
ADD COLUMN `Status`  bit NULL COMMENT '审核状态' AFTER `ReleaseTime`;

ALTER TABLE `p_show`
CHANGE COLUMN `UserId` `CreateUserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id' AFTER `KeyID`,
ADD COLUMN `Status`  bit NULL COMMENT '审核状态' AFTER `ReleaseTime`;

ALTER TABLE `p_silhouette`
CHANGE COLUMN `UserId` `CreateUserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id' AFTER `KeyID`,
ADD COLUMN `Status`  bit NULL COMMENT '审核状态' AFTER `ReleaseTime`;

ALTER TABLE `p_video`
CHANGE COLUMN `UserId` `CreateUserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id' AFTER `KeyID`,
ADD COLUMN `Status`  bit NULL COMMENT '审核状态' AFTER `ReleaseTime`;

