--墙报表增加年份字段
ALTER TABLE `p_silhouette`
ADD COLUMN `Year`  varchar(50) NULL DEFAULT '2016' COMMENT '年份' AFTER `regionId`;

--修改信息统计模板表字段time为releaseTime
ALTER TABLE `t_temple`
CHANGE COLUMN `Time` `ReleaseTime`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `Type`;