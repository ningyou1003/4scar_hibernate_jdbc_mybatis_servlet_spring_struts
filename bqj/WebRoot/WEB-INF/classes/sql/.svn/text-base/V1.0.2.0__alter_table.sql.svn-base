--修改法律法规表
ALTER TABLE `l_law`
DROP COLUMN `Attachment`,
MODIFY COLUMN `Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `Title`,
ADD COLUMN `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Content`,
ADD COLUMN `UserId`  varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户id' AFTER `ReleaseTime`;