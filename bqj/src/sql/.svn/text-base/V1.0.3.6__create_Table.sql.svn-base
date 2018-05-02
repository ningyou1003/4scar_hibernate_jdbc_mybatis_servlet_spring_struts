--新建出版物市场检查管理台账表
CREATE TABLE `t_market` (
`KeyID`  varchar(50) NOT NULL ,
`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
`Number`  varchar(50) NULL COMMENT '编号' ,
`Date`  datetime NULL COMMENT '检查日期' ,
`Name`  varchar(255) NULL COMMENT '企业名称',
`Address`  varchar(255) NULL COMMENT '检查地点' ,
`Way`  varchar(255) NULL COMMENT '检查方式' ,
`Corporation`  varchar(255) NULL COMMENT '法人代表' ,
`Charge`  varchar(255) NULL COMMENT '主要负责人' ,
`Telephone`  varchar(255) NULL COMMENT '联系方式' ,
`Content`  varchar(255) NULL COMMENT '检查内容' ,
`Department`  varchar(255) NULL COMMENT '参加部门' ,
`Participant`  varchar(255) NULL COMMENT '参加人员' ,
`Illegal`  varchar(255) NULL COMMENT '违法违规情况' ,
`sampling`  varchar(255) NULL COMMENT ' 取样情况' ,
`Punish`  varchar(255) NULL COMMENT '处理结果' ,
`Status`  int(1) NULL COMMENT '审核状态' ,
PRIMARY KEY (`KeyID`)
)
;
--新建督办案件管理台账表
CREATE TABLE `t_supervise` (
`KeyID`  varchar(50) NOT NULL ,
`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
`Number`  varchar(50) NULL COMMENT '编号' ,
`Name`  varchar(255) NULL COMMENT '案件名称',
`Code`  varchar(255) NULL COMMENT '案件代号' ,
`Date`  datetime NULL COMMENT '案件日期' ,
`Services`  varchar(255) NULL COMMENT '督办单位' ,
`Cases`  varchar(255) NULL COMMENT '简要案情' ,
`Progress`  varchar(255) NULL COMMENT '案件进展' ,
`Opinion`  varchar(255) NULL COMMENT '指导意见' ,
`Content`  varchar(255) NULL COMMENT '检查内容' ,
`Punish`  varchar(255) NULL COMMENT '办理结果' ,
`Status`  int(1) NULL COMMENT '审核状态' ,
PRIMARY KEY (`KeyID`)
)
;


--新建举报线索管理台账
CREATE TABLE `t_account` (
`KeyID`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`regionId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域编码' ,
`Number`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号' ,
`ProcessingTime`  datetime NULL DEFAULT NULL COMMENT '受理时间' ,
`TransferDate`  datetime NULL DEFAULT NULL COMMENT '转办时间' ,
`ReportContent`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举报内容' ,
`Clues`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线索来源' ,
`Mechanism`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核查处理机构' ,
`Situation`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核查处理情况' ,
`Status`  int(1) NULL COMMENT '审核状态' ,
PRIMARY KEY (`KeyID`)
)
;


--新建案件统计月报表
CREATE TABLE `t_monthly` (
`KeyID`  varchar(50) NOT NULL ,
`regionId`  varchar(50) NULL COMMENT '所属区域编码' ,
`Number`  varchar(50) NULL COMMENT '序号' ,
`Type`  varchar(255) NULL COMMENT '案件类型' ,
`Name`  varchar(255) NULL COMMENT '案件名称' ,
`Time`  datetime NULL COMMENT '立案时间' ,
`Address`  varchar(255) NULL COMMENT '查处地点' ,
`Varieties`  varchar(255) NULL COMMENT '查缴品种、数量' ,
`Destroy`  varchar(255) NULL COMMENT '查缴物品确需销毁情况' ,
`Remarks`  varchar(255) NULL ,
`Status`  int(1) NULL COMMENT '审核状态' ,
PRIMARY KEY (`KeyID`)
)
;
--新建游商摊点主要分布区域管理台账表
CREATE TABLE `t_stalls` (
`KeyID`  varchar(50) NOT NULL ,
`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
`Number`  varchar(50) NULL COMMENT '编号' ,
`Address`  varchar(255) NULL COMMENT '地点' ,
`Services`  varchar(255) NULL COMMENT '责任单位' ,
`Problem`  varchar(255) NULL COMMENT '存在问题' ,
`Punish`  varchar(255) NULL COMMENT '整治情况' ,
`Status`  int(1) NULL COMMENT '审核状态' ,
PRIMARY KEY (`KeyID`)
)
;
--新建印刷复制企业、出版物物流仓储企业、出版物集中销售场所管理台账表
CREATE TABLE `t_printing` (
`KeyID`  varchar(50) NOT NULL ,
`RegionId`  varchar(50) NULL COMMENT '所属区域编码' ,
`Number`  varchar(50) NULL COMMENT '编号' ,
`Name`  varchar(255) NULL COMMENT '名称' ,
`Address`  varchar(255) NULL COMMENT '地址' ,
`RegisteredCapital`  varchar(255) NULL COMMENT '注册资金' ,
`Corporation`  varchar(255) NULL COMMENT '法人代表' ,
`Charge`  varchar(255) NULL COMMENT '负责人' ,
`Telephone`  varchar(255) NULL COMMENT '联系方式' ,
`Staff`  varchar(255) NULL COMMENT '员工人数' ,
`OperatingConditions`  varchar(255) NULL COMMENT '经营状况' ,
`Result`  varchar(255) NULL COMMENT '违法违规情况及处理结果' ,
`Status`  int(1) NULL COMMENT '审核状态' ,
`Type`  tinyint(1) NULL COMMENT '台账类型' ,
PRIMARY KEY (`KeyID`)
)
;
