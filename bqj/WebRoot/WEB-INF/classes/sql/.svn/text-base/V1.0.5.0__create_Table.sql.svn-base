-- 删除发起互动表
DROP TABLE IF EXISTS `i_post`;
-- 新建发起互动表
CREATE TABLE `i_post` (
  `id` varchar(50) NOT NULL COMMENT '互动唯一id',
  `userID` varchar(50) NOT NULL DEFAULT '0' COMMENT '用户id,关联用户表\r\n',
  `content` text NOT NULL COMMENT '互动内容',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `hasReply` int(11) NOT NULL DEFAULT '0' COMMENT '评论次数',
  `hasLike` int(11) NOT NULL DEFAULT '0' COMMENT '点赞次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 删除评论表
DROP TABLE IF EXISTS `i_reply`;
-- 新建评论表
CREATE TABLE `i_reply` (
  `id` varchar(50) NOT NULL COMMENT '评论唯一id',
  `postID` varchar(50) NOT NULL COMMENT '互动唯一id关联i_post表',
  `userID` varchar(50) NOT NULL DEFAULT '0' COMMENT '用户id关联用户表s_user',
  `content` varchar(300) NOT NULL COMMENT '评论内容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 删除点赞表
DROP TABLE IF EXISTS `i_clicklike`;
-- 新建点赞表
CREATE TABLE `i_clicklike` (
  `id` varchar(50) NOT NULL COMMENT '评论唯一id',
  `postID` varchar(50) NOT NULL COMMENT '互动唯一id关联i_post表',
  `userID` varchar(50) NOT NULL COMMENT '用户id关联用户表s_user',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;