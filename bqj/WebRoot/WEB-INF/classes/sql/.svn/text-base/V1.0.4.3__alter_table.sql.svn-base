ALTER TABLE `c_record_map`
MODIFY COLUMN `Title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查点：用户输入该地点的详细检查点名称' AFTER `KeyId`,
MODIFY COLUMN `Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问题描述：当“检查是否发现问题”被勾选时，此项可编辑' AFTER `Title`,
MODIFY COLUMN `lng`  double NULL DEFAULT NULL COMMENT '点位的经度' AFTER `regionId`,
MODIFY COLUMN `lat`  double NULL DEFAULT NULL COMMENT '点位的维度' AFTER `lng`,
MODIFY COLUMN `address`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '地点：去用百度地图获取的点位地址，允许修改，并自动将点位调整至相应地图位置' AFTER `lat`,
ADD COLUMN `level`  int(1) NULL COMMENT '级别：填入“自治区/..市/..区或县”，默认按照填报人所在级别填入' AFTER `important`,
ADD COLUMN `outerNUmber`  int(10) NULL COMMENT '出动人数' AFTER `level`,
ADD COLUMN `time`  time NULL COMMENT '时间' AFTER `outerNUmber`,
ADD COLUMN `hasProblem`  int(1) NULL COMMENT '检查是否发现问题, 0表示没有发现问题，1 表示发现问题' AFTER `time`,
ADD COLUMN `isSolved`  int NULL COMMENT '问题是否解决，0表示未解决，1表示解决' AFTER `hasProblem`,
ADD COLUMN `checkPicture`  text NULL COMMENT '检查照片：允许上传本次检查照片，用于验证本次外出检查的真实性' AFTER `isSolved`;
