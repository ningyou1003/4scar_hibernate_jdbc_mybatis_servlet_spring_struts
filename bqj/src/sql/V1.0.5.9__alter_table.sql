--c_record表增加检查人员字段
ALTER TABLE `c_record`
ADD COLUMN `checkName`  varchar(50) NULL AFTER `num`;