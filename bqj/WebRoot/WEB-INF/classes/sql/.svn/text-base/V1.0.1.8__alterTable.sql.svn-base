-- 修改字段对应表中content字段及brief字段类型为longtext

ALTER TABLE `a_activity`
MODIFY COLUMN `content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动内容简介' ;

ALTER TABLE `d_document`
MODIFY COLUMN `brief`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文件简介';

ALTER TABLE `d_notice`
MODIFY COLUMN `content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '通知内容';

ALTER TABLE `zd_active`
MODIFY COLUMN `content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动内容';

ALTER TABLE `zd_file`
MODIFY COLUMN `brief`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文件简介';

ALTER TABLE `zd_system`
MODIFY COLUMN `content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '制度内容';