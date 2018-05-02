--修改用户id(UserId)为用户(User)
ALTER TABLE `t_ledger`
CHANGE COLUMN `UserId` `User`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户' AFTER `RegionId`;

--修改用户id(UserId)为用户(User)
ALTER TABLE `t_proposal`
CHANGE COLUMN `UserId` `User`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户' AFTER `RegionId`;

--修改用户id(UserId)为用户(User)
ALTER TABLE `t_statistics`
CHANGE COLUMN `UserId` `User`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入用户' AFTER `RegionId`;