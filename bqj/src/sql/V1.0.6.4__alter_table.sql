--添加是否为全国扫黄打非检查点字段
ALTER TABLE `c_record_map`
ADD COLUMN `nationalCheckPoint`  int(1) NULL COMMENT '是否为全国扫黄打非检查点  0：不是    1：是' AFTER `checkPicture`;