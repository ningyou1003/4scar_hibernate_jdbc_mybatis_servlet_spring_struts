ALTER TABLE `s_log`
MODIFY COLUMN `UserName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `S_DepartmentID`;
