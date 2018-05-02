--修改区域联防表
ALTER TABLE `z_zonedefence`
MODIFY COLUMN `content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' ,
ADD COLUMN `createUserId`  varchar(50) NULL COMMENT '创建人Id' ,
ADD COLUMN `createTime`  timestamp NULL  COMMENT '发布时间',
ADD COLUMN `approverId`  varchar(50) NULL COMMENT '审批人id' ,
ADD COLUMN `approverTime`  timestamp NULL COMMENT '审批时间' ,
ADD COLUMN `status`  tinyint(1) NULL COMMENT '审批状态，0：未审核，1：已审核' 
;

-- 删除活动表
DROP TABLE IF EXISTS `a_activity`;
-- 新建活动表
CREATE TABLE `a_activity` (
`keyid`  varchar(50) NOT NULL COMMENT '活动id' ,
`title`  varchar(50) NULL COMMENT '活动标题' ,
`createUserId`  varchar(50) NULL COMMENT '创建人id' ,
`createTime`  timestamp NULL COMMENT '活动创建时间' ,
 `content` longtext COMMENT '活动内容简介',
  `type` tinyint(2) DEFAULT NULL COMMENT '活动类型，1：清源  2：净网(云盘专项整治行动)  3：护苗  4：固边  5：秋风  6：其他(高校及周边打印点专项整治行动)',
  `status` tinyint(1) DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核',
PRIMARY KEY (`keyid`)
)
;

--修改区域表--增加字段
ALTER TABLE `s_area`
ADD COLUMN `memberUnits`  varchar(100) NULL COMMENT '成员单位' AFTER `IsAvailable`,
ADD COLUMN `mUResponsibility`  varchar(200) NULL COMMENT '成员单位职责  memberUnitsResponsibility' AFTER `memberUnits`,
ADD COLUMN `workingSituation`  varchar(200) NULL COMMENT '工作情况' AFTER `mUResponsibility`,
ADD COLUMN `workingOrgans`  varchar(100) NULL COMMENT '工作机构' AFTER `workingSituation`;