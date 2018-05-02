/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : bqj_db

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2016-05-03 16:12:32
*/

SET FOREIGN_KEY_CHECKS=0;

DELETE FROM a_alarm;
DELETE FROM a_appeal;
DELETE FROM base_attachment;
DELETE FROM s_area;
DELETE FROM s_dictionary;
DELETE FROM s_job;
DELETE FROM s_job_user;
DELETE FROM s_job_user_parttime;
DELETE FROM s_log;
DELETE FROM s_msg;
DELETE FROM s_org;
DELETE FROM s_org_dep;
DELETE FROM s_org_temp;
DELETE FROM s_org_user;
DELETE FROM s_power;
DELETE FROM s_power_permission;
DELETE FROM s_role;
DELETE FROM s_role_power;
DELETE FROM s_role_user;
DELETE FROM s_user;

-- ----------------------------
-- Records of a_alarm
-- ----------------------------
INSERT INTO `a_alarm` VALUES ('2d839807912d43589a2a04895ec28616', '报案1', '张三', '0', '2016-05-03 15:26:20', null, '13888888888', '南宁市', '案件描述', '1', '0', '4185ed984f3f4818ad51c72f6fa6cbfa');

-- ----------------------------
-- Records of a_appeal
-- ----------------------------
INSERT INTO `a_appeal` VALUES ('277d6f3bfc374dd98ecc9ebe6de6a38e', '关于XXX的问题', '张三', '0', '2016-05-03 15:27:25', null, '13888888888', '关于XXX的问题', '1', '0', '4185ed984f3f4818ad51c72f6fa6cbfa');

-- ----------------------------
-- Records of base_attachment
-- ----------------------------

-- ----------------------------
-- Records of s_area
-- ----------------------------
INSERT INTO `s_area` VALUES ('0', null, null, '0', '中国', '0', null, null, null, null, null, null, null, null, null), ('1', '45', '0', '45000000', '广西壮族自治区', '1', null, null, null, null, null, null, null, null, '0'), ('19', '4501', '45000000', '45010000', '南宁市', '2', null, null, null, null, null, null, null, null, '0'), ('41', '4502', '45000000', '45020000', '柳州市', '2', null, null, null, null, null, null, null, null, '0'), ('57', '4503', '45000000', '45030000', '桂林市', '2', null, null, null, null, null, null, null, null, '0'), ('79', '4504', '45000000', '45040000', '梧州市', '2', null, null, null, null, null, null, null, null, '0'), ('95', '4505', '45000000', '45050000', '北海市', '2', null, null, null, null, null, null, null, null, '0'), ('112', '4506', '45000000', '45060000', '防城港市', '2', null, null, null, null, null, null, null, null, '0'), ('124', '4507', '45000000', '45070000', '钦州市', '2', null, null, null, null, null, null, null, null, '0'), ('136', '4508', '45000000', '45080000', '贵港市', '2', null, null, null, null, null, null, null, null, '0'), ('145', '4509', '45000000', '45090000', '玉林市', '2', null, null, null, null, null, null, null, null, '0'), ('161', '4510', '45000000', '45100000', '百色市', '2', null, null, null, null, null, null, null, null, '0'), ('179', '4511', '45000000', '45110000', '贺州市', '2', null, null, null, null, null, null, null, null, '0'), ('187', '4512', '45000000', '45120000', '河池市', '2', null, null, null, null, null, null, null, null, '0'), ('206', '4513', '45000000', '45130000', '来宾市', '2', null, null, null, null, null, null, null, null, '0'), ('217', '4514', '45000000', '45140000', '崇左市', '2', null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Records of s_dictionary
-- ----------------------------

-- ----------------------------
-- Records of s_job
-- ----------------------------
INSERT INTO `s_job` VALUES ('3e5b895919b146fbba7e8470861c06f2', '科员', null, '45000001', '231'), ('bc89fc34329448a5ba24f2ab2c0e8152', '工作人员', null, '45000001', '233'), ('bf40d0ca61be46a09380c3e3e337cbbf', '干事', null, '45000001', '232');

-- ----------------------------
-- Records of s_job_user
-- ----------------------------
INSERT INTO `s_job_user` VALUES ('4117ef3f083c4626bbdf4a02c10f9bf5', '4185ed984f3f4818ad51c72f6fa6cbfa', 'bc89fc34329448a5ba24f2ab2c0e8152'), ('91f53cc839984d668abc7f4115238e41', '4185ed984f3f4818ad51c72f6fa6cbfa', '3e5b895919b146fbba7e8470861c06f2'), ('c493da8ea2de473c9fc5ca1446ad7a56', '4185ed984f3f4818ad51c72f6fa6cbfa', 'bf40d0ca61be46a09380c3e3e337cbbf'), ('df216e39bb1b4fc6875e0a84ce5712d7', '63bf492da4c5461998a6960be59daacf', ''), ('ef36a542c26f4bbfa72f36ed348853d0', '801b75b3f49f48c9bf9ac8890753642b', '');

-- ----------------------------
-- Records of s_job_user_parttime
-- ----------------------------

-- ----------------------------
-- Records of s_log
-- ----------------------------
INSERT INTO `s_log` VALUES ('0820d3c29b7049de848f240212d5cbf4', '用户管理', '2016-04-14 09:31:54', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('222ab7ac71084910a225320d37b585e5', '角色管理', '2016-04-13 17:16:21', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对角色管理模块进行了修改操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('2d09bf10525a4842b1b7326b6af160c3', '用户管理', '2016-04-14 09:30:26', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('3cf35d144bc543e3b252a0a2c20f6b95', '用户管理', '2016-04-14 09:30:13', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('676d76b423c046f9bce3e6d2fd5dfe43', '用户管理', '2016-04-14 10:01:20', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('6ddb98d96de747c5b13712bd60f52046', '用户管理', '2016-04-14 09:31:56', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('9b0c83a693354e7bab2ca5ba4730c791', '角色管理', '2016-04-13 17:16:15', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对角色管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('d9111e46c1274acca435b24f2f0727dc', '角色管理', '2016-04-13 17:16:21', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对角色管理模块进行了修改操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8'), ('f50293ce7c84473d8f4f6f216d08221e', '用户管理', '2016-04-14 09:31:40', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');

-- ----------------------------
-- Records of s_msg
-- ----------------------------

-- ----------------------------
-- Records of s_org
-- ----------------------------
INSERT INTO `s_org` VALUES ('dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '45000001001', null, null, '', null, '广西壮族自治区版权局', 'GXIPA', '事业单位', '广西南宁市', null, '', '', '', '', '', '', '', '45000001', null, '0', '0', '广西壮族自治区人民政府', '', '', '', '主管', '', '', '', '', '');

-- ----------------------------
-- Records of s_org_dep
-- ----------------------------
INSERT INTO `s_org_dep` VALUES ('3b3e8c896810491391fdd0213efc289b', null, null, '招商政策调研处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责招商引资政策投资课题研究，分析评估招商引资发展趋势，探索创新招商引资模式，推广招商引资成果经验；\n2.负责全区招商引资有关数据统计和投资促进运行分析工作；\n3.拟订全区招商引资年度工作目标计划；\n4.组织实施对引进国（境）外和国内投资项目的奖励工作；\n5.牵头组织本局重要招商政策调研材料的撰写及相关文稿的起草工作。', '1', '', null), ('60be6a807dff4ae4a09639d09ae35847', null, null, '项目协调督办处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责全区重大招商引资项目的协调督办工作；\n2.调查处理投资投诉案件，提出诉案处理意见；\n3.办理本局在自治区政务服务中心的窗口业务；\n4.承担自治区招商引资项目大兑现工作领导小组的日常工作。', '唐燕', '', null), ('61550764b968466fbb840b4461e1de3d', null, null, '机关工会', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '', '蔡莉宁', '', null), ('7a626fbe695747e2b525c59b593623f4', null, null, '人事处（离退休人员工作处）', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责机关的人事管理、机构编制、教育培训、工资福利、出国（境）人员政审等工作；\n2.负责局机关在职人员的录用奖惩和专业技术职务的评聘工作；\n3.负责机关离退休人员工作。', '邓宏业', '', null), ('9b759a35f7a14308bc28c32cce083f31', null, null, '机关党委', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '', '陈行亮', '', null), ('9fe559b0d30e4d78af9e2efd337953ad', null, null, '县域经济投资促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责研究提出全区县域投资促进工作政策措施和承接产业转移招商引资工作措施；\n2.负责自治区投资促进活动的对外宣传工作；\n3.负责广西投资促进网络信息管理系统及官方网站的管理。', '黎明', '', null), ('a472c8705fe24244a4896a0040cc6bd9', null, null, '办公室', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责机关文电处理、机要保密、档案文印、行政后勤、计划生育等方面工作；\n2.负责机关财务、固定资产管理工作；\n3.负责有关会务、接待工作；\n4.负责机关内部管理制度建设；\n5.编印投资、招商信息简报；\n6.牵头组织本局重要文件文稿的起草工作。', '苏海光', '', null), ('ac1f38ac23a04646b1885dca07ec2134', null, null, '国内投资促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责与国内投资企业的联系和沟通工作，组织开展国内重大投资促进活动；\n2.统筹推进北部湾经济区投资促进工作；\n3.统筹推进全区各级各类园区的招商引资工作；\n4.承担自治区“百企入桂”工作领导小组的日常工作。', '梁艺', '', null), ('b66c73e3a538452db264bcef68678ea5', null, null, '外国投资促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责全区国（境）外投资促进活动的统筹和协调工作；\n2.统计分析全区外资促进工作；\n3.负责直接利用外资项目的跟踪、协调、服务工作；\n4.参与制定全区投资促进政策；\n5.负责策划、组织实施国（境）外投资促进活动；\n6.参与中国—东盟博览会投资促进活动；\n7.联络相关国际组织或境外投资促进机构开展业务合作；\n8.分析研究国际投资合作趋势及全区利用外资情况，提出工作建议和措施。', '陶德文', '', null), ('cac28ded4d6d4ecd93683bd78ed1d753', null, null, '区域合作处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责全区区域经济合作相关工作事项；\n2.会同有关部门推进我区与各类区域经济合作组织以及省（区、市）双边或多边经济合作事项的开展；\n3.牵头组织或配合自治区有关部门组团参加在国内举办的重要投资促进活动；\n4.承担对口支援方面的具体工作事项；\n5.办理外省（区、市）政府和经济组织驻桂办事机构备案登记；\n6.联系、沟通外省（区、市）广西商会；\n7.提出服务新社会阶层组织的工作建议和措施，开展维护商协会组织合法权益保障工作。', '唐非', '', null), ('fb640a3134e54331af9222467abc9d83', null, null, '投资项目促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区版权局', '0', null, '1.负责自治区重大招商引资项目的策划、包装、推介、储备、发布等工作；\n2.汇总上报重大招商项目列入自治区人民政府当年统筹推进重大项目；\n3.负责全区重大招商引资项目的协调推进工作；\n4.建设和动态管理全区招商引资项目库；\n5.服务自治区重大投资促进活动的项目签约工作；\n6.推介投资环境和重点项目，编印投资、招商项目宣传资料；\n7.参与组织招商引资项目有关业务培训工作；\n8.协调管理招商项目专项经费；\n9.统筹推进“西江经济带”和资源富集区的投资促进工作。', '曾伟', '', null);

-- ----------------------------
-- Records of s_org_temp
-- ----------------------------

-- ----------------------------
-- Records of s_org_user
-- ----------------------------

-- ----------------------------
-- Records of s_power
-- ----------------------------
INSERT INTO `s_power` VALUES ('0', '菜单导航', null, null, '../images/leftico01.png', '0', '0'), ('10', '报案诉求', '0', null, null, '1', '0'), ('1001', '报案管理', '10', 'appealalarm/alarmManage/alarmPage.html', null, '1', '0'), ('1002', '问题诉求', '10', 'appealalarm/appealManage/appealPage.html', null, '2', '0'), ('7', '个人中心', '0', null, null, '1', '0'), ('700', '待办事宜', '7', 'documentflow/document/MyPostilList.html', null, '0', '0'), ('703', '修改个人资料', '7', 'hr/user/pending/info.html?id', null, '3', '0'), ('704', '修改密码', '7', 'i/password.html', null, '4', '0'), ('705', '个人群组', '7', 'i/groupsList.html', null, '5', '0'), ('8', '系统管理', '0', null, '', '8', '0'), ('811', '用户管理', '8', 'system/userManage/userPage.html', null, '3', '0'), ('812', '角色管理', '8', 'system/roleManage/rolePage.html', null, '4', '0'), ('813', '数据字典管理', '8', 'system/dicManage/dicList.html', null, '6', '0'), ('814', '系统日记', '8', 'system/logManage/logList.html', null, '7', '0'), ('819', '职务管理', '8', 'system/jobManage/jobPage.html', null, '5', '0'), ('820', '区域管理', '8', 'system/areaManage/areaPage.html', null, '8', '0'), ('9', '帮助', '0', '', null, '9', '1'), ('910', '版本说明', '9', '', '../images/leftico01.png', '1', '0'), ('911', '操作手册', '9', null, null, '2', '0'), ('912', '使用帮助', '9', null, null, '3', '0');

-- ----------------------------
-- Records of s_power_permission
-- ----------------------------
INSERT INTO `s_power_permission` VALUES ('27', '811', 'EditPower', '修改'), ('28', '811', 'DelPower', '删除'), ('29', '812', 'AddPower', '增加'), ('30', '812', 'EditPower', '修改'), ('31', '812', 'DelPower', '删除'), ('32', '813', 'AddPower', '增加'), ('33', '813', 'EditPower', '修改'), ('34', '813', 'DelPower', '删除'), ('35', '814', 'EditPower', '修改'), ('36', '814', 'DelPower', '删除'), ('37', '819', 'AddPower', '增加'), ('38', '819', 'EditPower', '修改'), ('39', '819', 'DelPower', '删除'), ('40', '815', 'AddPower', '增加'), ('41', '815', 'EditPower', '修改');

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('663912d4cf334659810d38f52b6f34f3', '管理员', '0', '45000000'), ('7684ce46adfe40c0ab2291d8c4b3be78', '一般工作人员', '1', '45000000');

-- ----------------------------
-- Records of s_role_power
-- ----------------------------
INSERT INTO `s_role_power` VALUES ('0330e49765da40b883e90bc761849948', '814', null, '1', null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,EditPower,'), ('073bb1ca81b94b37b4ccb3884d8bbc40', '0', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('13a2798968c84c78a9eea473ffae9ef0', '1002', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('21740b61fb2343a58eb0a6194407e648', '912', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('38e9ac2059824e73823ab75b00a9b97b', '811', null, '1', null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,EditPower,'), ('3f6d614ec47e4355bce65ebb150491b6', '10', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('5fa2d999876346489bb6a9848a166e28', '820', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('61a1199b248e4aa98f0aa378ef0948aa', '8', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('743ee1f1f99f46efaa1f9973f6019dc0', '910', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('834dcaefe49740e08dff5013653e4c8d', '813', '1', '1', null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,AddPower,EditPower,'), ('86fa11b17993411a9b60bdb185f4e939', '911', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('90ea6c78c25d417b968a81bf2d2e2844', '819', '1', '1', null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,AddPower,EditPower,'), ('94e53df085354b04b789d6cbe9da2112', '1001', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,'), ('e940bc07c3fb42e097fee1027c3f5ec6', '812', '1', '1', null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,AddPower,EditPower,');

-- ----------------------------
-- Records of s_role_user
-- ----------------------------
INSERT INTO `s_role_user` VALUES ('97fd58fec5fb4916a8855730252c5ed7', '4185ed984f3f4818ad51c72f6fa6cbfa', '663912d4cf334659810d38f52b6f34f3'), ('c159459f4322483ba0795a4bec360b64', '63bf492da4c5461998a6960be59daacf', '663912d4cf334659810d38f52b6f34f3'), ('e3068f98539d42b6af58f8c604ac755a', '801b75b3f49f48c9bf9ac8890753642b', '663912d4cf334659810d38f52b6f34f3');

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('4185ed984f3f4818ad51c72f6fa6cbfa', null, null, '45000000', null, null, '450000010010008', 'admin', '超级管理员', '07bd326091c30ece8b015d58b7c4e922', 'admin', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '在职在岗', null, null, null, null, null, null, null, null, '2016-05-03 14:32:06', null, null, null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '60be6a807dff4ae4a09639d09ae35847', '项目协调督办处', null, null, '45000001', '45000001001', '0'), ('63bf492da4c5461998a6960be59daacf', null, null, '45000000', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-05-03 15:23:44', null, null, null, null, null, null, null, null, null, null, null, '0'), ('801b75b3f49f48c9bf9ac8890753642b', null, null, '45000000', null, null, null, null, 'adsf', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-05-03 15:23:44', null, null, null, null, null, null, null, null, null, null, null, '0');