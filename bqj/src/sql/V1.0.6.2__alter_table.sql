--统一修改字段用户为用户ID
ALTER TABLE `b_build`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `ReleaseTime`;

ALTER TABLE `p_bulletin`
CHANGE COLUMN `CreateUser` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `KeyID`;

ALTER TABLE `p_other`
CHANGE COLUMN `CreateUser` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `KeyID`;

ALTER TABLE `p_show`
CHANGE COLUMN `CreateUser` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `KeyID`;

ALTER TABLE `l_judicial`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `ReleaseTime`;

ALTER TABLE `l_law`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `ReleaseTime`;

ALTER TABLE `l_notice`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `ReleaseTime`;

ALTER TABLE `l_regulations`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `ReleaseTime`;

ALTER TABLE `l_stipulate`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `ReleaseTime`;

ALTER TABLE `t_ledger`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `RegionId`;

ALTER TABLE `t_proposal`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `RegionId`;

ALTER TABLE `t_statistics`
CHANGE COLUMN `User` `UserId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户ID' AFTER `RegionId`;

--增加用户ＩＤ字段
ALTER TABLE `t_temple`
ADD COLUMN `UserId`  varchar(50) NULL COMMENT '录入用户ID' AFTER `RegionId`;


--更新用户ID字段数据为用户ID
UPDATE b_build SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE p_bulletin SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE p_show SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE p_other SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE l_law SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE l_notice SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE l_regulations SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE l_judicial SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE l_stipulate SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE t_statistics SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE t_ledger SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );

UPDATE t_proposal SET UserId = (SELECT KeyID FROM s_user  WHERE RelaName  = UserId LIMIT 1) WHERE  UserId in (SELECT RelaName FROM s_user );
