--修改年份不能为空
ALTER TABLE `p_silhouette`
MODIFY COLUMN `Year`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '2016' COMMENT '年份' AFTER `reportTime`;

ALTER TABLE `p_silhouetteyear`
MODIFY COLUMN `Region`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '2016' COMMENT '墙报年份' AFTER `keyid`;

