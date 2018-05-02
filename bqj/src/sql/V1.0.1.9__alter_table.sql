--修改简报表
ALTER TABLE `p_bulletin`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
--修改活动演出表
ALTER TABLE `p_show`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
--修改视频表
ALTER TABLE `p_video`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
--修改其他表
ALTER TABLE `p_other`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
--修改制度建设表
ALTER TABLE `b_build`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;
