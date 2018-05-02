--修改对应表中Content字段类型为longtext
ALTER TABLE `b_build`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`;

ALTER TABLE `p_bulletin`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`;

ALTER TABLE `p_show`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`;

ALTER TABLE `p_other`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`;

ALTER TABLE `p_video`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`;

ALTER TABLE `l_law`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`;

--修改案例表
ALTER TABLE `l_regulations`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
--修改规定表
ALTER TABLE `l_stipulate`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
--修改通知表
ALTER TABLE `l_notice`
DROP COLUMN `randomNum`,
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
--修改剪影表
ALTER TABLE `p_silhouette`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;