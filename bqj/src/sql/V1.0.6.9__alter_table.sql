--c_record表增加解决情况字段
ALTER TABLE `c_record`
ADD COLUMN `settle`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '解决情况' AFTER `checkName`;