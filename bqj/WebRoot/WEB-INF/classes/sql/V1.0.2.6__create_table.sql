DROP TABLE IF EXISTS flow_todo;
CREATE TABLE `flow_todo` (
  `KeyId` varchar(255) NOT NULL,
  `FlowType` varchar(255) DEFAULT NULL COMMENT '流程类型',
  `FlowId` varchar(255) DEFAULT NULL COMMENT '流程id',
  `UserId` varchar(255) DEFAULT NULL COMMENT '待办人id',
  `TodoCommon` text COMMENT '审批意见',
  `Status` varchar(255) DEFAULT NULL COMMENT '状态',
  `StartTime` datetime DEFAULT NULL COMMENT '开始时间',
  `FinishTime` datetime DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`KeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8