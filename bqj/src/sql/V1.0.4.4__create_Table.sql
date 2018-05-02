--删除c_record表
DROP TABLE IF EXISTS `c_record`;

--新建c_record表
CREATE TABLE `c_record` (
`keyId`  varchar(50) NOT NULL ,
`mapRecordId`  varchar(50) NOT NULL COMMENT '关联的检查记录点位ID，即对应的c_record_map 表的keyid值，一个点位id对应多个检查记录' ,
`time`  timestamp NULL COMMENT '时间' ,
`outerNumber`  int(10) NULL COMMENT '出动人次' ,
`hasProblem`  int(1) NULL COMMENT '检查是否发现问题, 0:没有发现问题，1：发现问题' ,
`description`  text NULL COMMENT '问题描述：当“检查是否发现问题”被勾选时，此项可编辑' ,
`isSolved`  int(1) NULL COMMENT '问题是否解决，0：未解决，  1：已解决' ,
`checkPicture`  text NULL COMMENT '检查照片：允许上传本次检查照片，用于验证本次外出检查的真实性' ,
PRIMARY KEY (`keyId`)
)
;
