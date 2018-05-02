--为简报表增加简报年份字段
ALTER TABLE `p_bulletin`
ADD COLUMN `year`  varchar(4) NULL COMMENT '简报年份' AFTER `regionId`;

--c-record表增加num字段
ALTER TABLE `c_record`
ADD COLUMN `num`  varchar(50) NULL COMMENT '同一个站点第几次检查' AFTER `checkPicture`;