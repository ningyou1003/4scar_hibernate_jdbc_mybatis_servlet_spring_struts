--为简报表增加简报期数字段
ALTER TABLE `p_bulletin`
ADD COLUMN `Number`  varchar(255) NULL COMMENT '简报期数' AFTER `Title`;

--为视频表增加视频类型id字段
ALTER TABLE `p_video`
ADD COLUMN `TypeId`  int(1) NULL COMMENT '视频类型id' AFTER `Title`;