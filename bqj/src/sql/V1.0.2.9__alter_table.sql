-- 将对应表中status字段类型改为int
ALTER TABLE `a_activity`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `d_document`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `flow_todo`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `p_bulletin`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `p_other`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `p_show`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `p_silhouette`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `p_video`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';

ALTER TABLE `z_zonedefence`
MODIFY COLUMN `status`  int(1) NULL DEFAULT NULL COMMENT '审批状态，0：未审核，1：已审核，3：已上报';
