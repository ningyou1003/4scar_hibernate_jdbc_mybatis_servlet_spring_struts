--为 s_role 表新增一个字段level，记录角色等级
ALTER TABLE `s_role`
ADD COLUMN `level`  int(3) NULL DEFAULT 0 COMMENT '角色等级:默认值为0，即默认为自治区管理员\r\n0：省/自治区/直辖市\r\n1：市\r\n2：县/区\r\n3：乡镇\r\n4：基层' AFTER `RegionCode`;