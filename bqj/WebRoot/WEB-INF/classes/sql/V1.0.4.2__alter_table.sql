ALTER TABLE `s_area`
MODIFY COLUMN `description`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介' AFTER `OperLevel`,
MODIFY COLUMN `workingSituation`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '工作情况' AFTER `mUResponsibility`,
MODIFY COLUMN `workingOrgans`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '工作机构' AFTER `workingSituation`;
