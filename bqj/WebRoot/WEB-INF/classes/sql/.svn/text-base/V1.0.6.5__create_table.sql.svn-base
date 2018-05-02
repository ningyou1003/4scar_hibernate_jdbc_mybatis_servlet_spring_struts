--新建事项管理表
CREATE TABLE `m_matter` (
  `keyid` varchar(255) NOT NULL COMMENT '事项ID',
  `title` varchar(255) DEFAULT NULL COMMENT '事项名称/标题',
  `content` text COMMENT '事项内容',
  `createUserId` varchar(255) DEFAULT NULL COMMENT '录入人ID',
  `publishTime` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `status` int(1) DEFAULT NULL COMMENT '待办状态： 0：待办  1：已办',
  `handleTime` timestamp NULL DEFAULT NULL COMMENT '办理时间',
  PRIMARY KEY (`keyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;