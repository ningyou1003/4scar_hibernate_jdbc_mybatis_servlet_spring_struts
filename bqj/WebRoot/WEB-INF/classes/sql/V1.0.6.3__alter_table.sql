ALTER TABLE `n_dynamic`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细内容' AFTER `Creator`;

ALTER TABLE `n_events`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细内容' AFTER `Creator`;

ALTER TABLE `n_history`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细内容' AFTER `Month`;

ALTER TABLE `n_leader`
MODIFY COLUMN `Content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细内容' AFTER `Time`;