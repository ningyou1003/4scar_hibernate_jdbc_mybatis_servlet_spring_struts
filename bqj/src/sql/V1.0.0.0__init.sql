/* 初始脚本 */

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : bqj_db

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2016-04-14 14:21:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `s_area`
-- ----------------------------
DROP TABLE IF EXISTS `s_area`;
CREATE TABLE `s_area` (
  `ID` int(50) NOT NULL,
  `OldRegionCode` varchar(50) DEFAULT NULL,
  `ParentCode` varchar(50) DEFAULT NULL,
  `RegionCode` varchar(50) NOT NULL DEFAULT '',
  `Region` varchar(50) DEFAULT NULL,
  `OperLevel` int(11) DEFAULT NULL,
  `ZJM` varchar(50) DEFAULT NULL,
  `OpenZoneFlag` tinyint(4) DEFAULT NULL,
  `Economic_Zone_Num` int(11) DEFAULT NULL,
  `open_area` int(11) DEFAULT NULL,
  `develop_zone` int(11) DEFAULT NULL,
  `pk_area` int(11) DEFAULT NULL,
  `kfq_type` int(11) DEFAULT NULL,
  `BBW_Flag` tinyint(4) DEFAULT NULL,
  `IsAvailable` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_area
-- ----------------------------
INSERT INTO `s_area` VALUES ('0', null, null, '0', '中国', '0', null, null, null, null, null, null, null, null, null);
INSERT INTO `s_area` VALUES ('1', '45', '0', '45000000', '广西壮族自治区', '1', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('2', '4500', '45000000', '45000001', '自治区直属部门', '1', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('3', '4500001', '45000000', '45000002', '广西壮族自治区发展与改革委员会', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('4', '4500002', '45000000', '45000003', '广西壮族自治区工业和信息化委员会', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('5', '4500003', '45000000', '45000004', '广西壮族自治区国有资产监督管理委员会', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('6', '4500004', '45000000', '45000007', '广西壮族自治区交通运输厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('7', '4500005', '45000000', '45000008', '广西壮族自治区科学技术厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('8', '4500006', '45000000', '45000009', '广西壮族自治区水利厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('9', '4500007', '45000000', '45000010', '广西壮族自治区住房与城乡建设厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('10', '4500008', '45000000', '45000011', '广西壮族自治区农业厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('11', '4500009', '45000000', '45000012', '广西壮族自治区文化厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('12', '4500010', '45000000', '45000013', '广西壮族自治区教育厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('13', '4500011', '45000000', '45000005', '广西壮族自治区卫生和计划生育委员会', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('14', '4500012', '45000000', '45000006', '广西壮族自治区旅游发展委员会', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('15', '4500013', '45000000', '45000014', '广西壮族自治区林业厅', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('16', '4500014', '45000000', '45000015', '广西壮族自治区水产畜牧兽医局', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('17', '4500015', '45000000', '45000016', '广西壮族自治区农垦局', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('18', '4500016', '45000016', '45000051', '广西明阳工业区', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('19', '4501', '45000000', '45010000', '南宁市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('20', '4501001', '45010000', '45010001', '南宁市直属部门', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('21', '4501002', '45010000', '45010200', '南宁市兴宁区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('22', '4501003', '45010000', '45010300', '南宁市青秀区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('23', '4501004', '45010000', '45010500', '南宁市江南区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('24', '4501005', '45010000', '45010700', '南宁市西乡塘区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('25', '4501006', '45010000', '45010800', '南宁市良庆区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('26', '4501007', '45010000', '45010900', '南宁市邕宁区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('27', '4501008', '45010000', '45012200', '南宁市武鸣县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('28', '4501009', '45010000', '45012700', '南宁市横县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('29', '4501010', '45010000', '45012600', '南宁市宾阳县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('30', '4501011', '45010000', '45012500', '南宁市上林县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('31', '4501012', '45010000', '45012300', '南宁市隆安县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('32', '4501013', '45010000', '45012400', '南宁市马山县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('33', '4501014', '45010000', '45010052', '南宁经济技术开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('34', '4501015', '45010000', '45010051', '南宁高新技术产业开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('35', '4501016', '45010000', '45010053', '广西—东盟经济技术开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('36', '4501017', '45010800', '45010851', '广西良庆经济开发区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('37', '4501018', '45012700', '45012751', '南宁六景工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('38', '4501019', '45010500', '45010551', '南宁江南工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('39', '4501020', '45010900', '45010951', '南宁市仙葫经济开发区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('40', '4501021', '45010000', '45010054', '南宁保税物流中心', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('41', '4502', '45000000', '45020000', '柳州市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('42', '4502001', '45020000', '45020001', '柳州市直属部门', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('43', '4502002', '45020000', '45020200', '柳州市城中区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('44', '4502003', '45020000', '45020300', '柳州市鱼峰区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('45', '4502004', '45020000', '45020400', '柳州市柳南区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('46', '4502005', '45020000', '45020500', '柳州市柳北区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('47', '4502006', '45020000', '45022100', '柳州市柳江县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('48', '4502007', '45020000', '45022200', '柳州市柳城县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('49', '4502008', '45020000', '45022300', '柳州市鹿寨县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('50', '4502009', '45020000', '45022400', '柳州市融安县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('51', '4502010', '45020000', '45022600', '柳州市三江侗族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('52', '4502011', '45020000', '45022500', '柳州市融水苗族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('53', '4502012', '45020000', '45020051', '柳州市阳和开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('54', '4502013', '45020000', '45020052', '柳州市柳东新区／柳州高新技术产业开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('55', '', '45022100', '45022151', '柳江县经济开发区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('56', '', '45022600', '45022651', '三江侗族自治县工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('57', '4503', '45000000', '45030000', '桂林市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('58', '4503001', '45030000', '45030001', '桂林市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('59', '4503002', '45030000', '45030200', '桂林市秀峰区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('60', '4503003', '45030000', '45030300', '桂林市叠彩区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('61', '4503004', '45030000', '45030400', '桂林市象山区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('62', '4503005', '45030000', '45030500', '桂林市七星区／桂林高新技术产业开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('63', '4503006', '45030000', '45031100', '桂林市雁山区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('64', '4503007', '45030000', '45032100', '桂林市阳朔县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('65', '4503008', '45030000', '45031200', '桂林市临桂区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('66', '4503009', '45030000', '45032300', '桂林市灵川县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('67', '4503010', '45030000', '45032400', '桂林市全州县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('68', '4503011', '45030000', '45032500', '桂林市兴安县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('69', '4503012', '45030000', '45032600', '桂林市永福县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('70', '4503013', '45030000', '45032700', '桂林市灌阳县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('71', '4503014', '45030000', '45032800', '桂林市龙胜各族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('72', '4503015', '45030000', '45032900', '桂林市资源县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('73', '4503016', '45030000', '45033000', '桂林市平乐县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('74', '4503017', '45030000', '45033100', '桂林市荔浦县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('75', '4503018', '45030000', '45033200', '桂林市恭城瑶族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('76', '4503025', '45030000', '45030051', '苏桥工业园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('77', '', '45032400', '45032451', '全州县工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('78', '', '45033100', '45033151', '荔浦县工业集中区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('79', '4504', '45000000', '45040000', '梧州市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('80', '4504001', '45040000', '45040001', '梧州市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('81', '4504002', '45040000', '45040300', '梧州市万秀区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('82', '4504004', '45040000', '45040500', '梧州市长洲区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('83', '4504005', '45040000', '45048100', '梧州市岑溪市', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('84', '4504006', '45040000', '45042100', '梧州市苍梧县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('85', '4504007', '45040000', '45042200', '梧州市藤县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('86', '4504008', '45040000', '45042300', '梧州市蒙山县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('87', '4504009', '45040500', '45040551', '广西梧州长洲工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('88', '4504010', '45040000', '45040051', '广西梧州工业园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('89', '4504011', '45040000', '45040052', '梧州进口再生资源加工园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('90', '4504012', '45040000', '45040601', '梧州市龙圩区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('91', '', '45040000', '45040053', '粤桂合作特别试验区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('92', '', '45040000', '45040054', '梧州市不锈钢制品产业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('93', '', '45040000', '45040055', '梧州市商贸物流园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('94', '', '45040000', '45040602', '梧州市苍海新区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('95', '4505', '45000000', '45050000', '北海市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('96', '4505001', '45050000', '45050001', '北海市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('97', '4505002', '45050000', '45050200', '北海市海城区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('98', '4505003', '45050000', '45050300', '北海市银海区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('99', '4505004', '45050000', '45051200', '北海市铁山港区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('100', '4505005', '45050000', '45052100', '北海市合浦县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('101', '4505006', '45050000', '45050054', '广西北海出口加工区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('102', '4505007', '45050000', '45050055', '北海银滩国家旅游度假区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('103', '4505008', '45050000', '45050052', '北海市工业园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('104', '4505009', '45050000', '45050053', '北海高新技术产业园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('105', '4505010', '45052100', '45052152', '合浦南国星岛湖旅游度假区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('106', '4505011', '45050000', '45050056', '北海涠洲岛旅游度假区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('107', '4505012', '45052100', '45052151', '广西合浦工业区管委会', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('108', '4505013', '45050000', '45050057', '北海华侨投资开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('109', '4505014', '45050000', '45050058', '北海大学园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('110', '4505015', '45050000', '45050059', '北海海洋产业科技园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('111', '', '45051200', '45051251', '北海市铁山港（临海）工业区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('112', '4506', '45000000', '45060000', '防城港市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('113', '4506001', '45060000', '45060001', '防城港市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('114', '4506002', '45060000', '45060200', '防城港市港口区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('115', '4506003', '45060000', '45060300', '防城港市防城区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('116', '4506004', '45060000', '45062100', '防城港市上思县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('117', '4506005', '45060000', '45068100', '防城港市东兴市', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('118', '4506006', '45068100', '45068151', '东兴江平工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('119', '4506007', '45060000', '45060051', '广西东兴国家重点开发开放试验区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('120', '', '45060000', '45060052', '防城港市高新技术产业开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('121', '', '45060000', '45060053', ' 防城港市大西南临港工业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('122', '', '45060000', '45060054', '防城港市企沙工业区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('123', '', '45060000', '45060055', '防城港市江山半岛旅游度假区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('124', '4507', '45000000', '45070000', '钦州市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('125', '4507001', '45070000', '45070001', '钦州市直', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('126', '4507002', '45070000', '45070200', '钦州市钦南区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('127', '4507003', '45070000', '45070300', '钦州市钦北区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('128', '4507004', '45070000', '45072100', '钦州市灵山县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('129', '4507005', '45070000', '45072200', '钦州市浦北县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('130', '4507006', '45070000', '45070052', '钦州港经济技术开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('131', '4507007', '45070000', '45070053', '钦州市三娘湾旅游管理区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('132', '4507008', '45070000', '45070054', '广西钦州高新技术开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('133', '4507009', '45070000', '45070055', '钦州市滨海新城', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('134', '', '45070000', '45070051', '中国－马来西亚钦州产业园区', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('135', '', '45070000', '45070056', '钦州市北部湾华侨投资区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('136', '4508', '45000000', '45080000', '贵港市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('137', '4508001', '45080000', '45080001', '贵港市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('138', '4508002', '45080000', '45080200', '贵港市港北区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('139', '4508003', '45080000', '45080300', '贵港市港南区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('140', '4508004', '45080000', '45080400', '贵港市覃塘区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('141', '4508005', '45080000', '45082100', '贵港市平南县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('142', '4508006', '45080000', '45088100', '贵港市桂平市', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('143', '4508007', '45088100', '45088151', '桂平西山旅游度假区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('144', '4508008', '45080000', '45080052', '贵港市工业园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('145', '4509', '45000000', '45090000', '玉林市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('146', '4509001', '45090000', '45090001', '玉林市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('147', '4509002', '45090000', '45090200', '玉林市玉州区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('148', '4509003', '45090000', '45090300', '玉林市福绵区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('149', '4509004', '45090000', '45092100', '玉林市容县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('150', '4509005', '45090000', '45092200', '玉林市陆川县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('151', '4509006', '45090000', '45092300', '玉林市博白县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('152', '4509007', '45090000', '45092400', '玉林市兴业县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('153', '4509008', '45090000', '45098100', '玉林市北流市', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('154', '4509009', '45090000', '45090051', '玉林中医药健康产业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('155', '4509010', '45092100', '45092151', '容县经济开发区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('156', '4509011', '45090000', '45090052', '广西玉柴工业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('157', '4509012', '45098100', '45098151', '北流日用陶瓷出口生产园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('158', '4509013', '45090000', '45090053', '玉林市海峡两岸农业合作试验区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('159', '4509014', '45090000', '45090054', '广西北部湾经济区玉林龙潭产业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('160', '4509015', '45090000', '45090055', '玉林市玉东新区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('161', '4510', '45000000', '45100000', '百色市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('162', '4510001', '45100000', '45100001', '百色市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('163', '4510002', '45100000', '45100200', '百色市右江区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('164', '4510003', '45100000', '45102100', '百色市田阳县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('165', '4510004', '45100000', '45102200', '百色市田东县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('166', '4510005', '45100000', '45102300', '百色市平果县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('167', '4510006', '45100000', '45102400', '百色市德保县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('168', '4510007', '45100000', '45102500', '百色市靖西县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('169', '4510008', '45100000', '45102600', '百色市那坡县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('170', '4510009', '45100000', '45102700', '百色市凌云县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('171', '4510010', '45100000', '45102800', '百色市乐业县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('172', '4510011', '45100000', '45102900', '百色市田林县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('173', '4510012', '45100000', '45103000', '百色市西林县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('174', '4510013', '45100000', '45103100', '百色市隆林各族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('175', '4510014', '45100000', '45100051', '百色市工业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('176', ' ', '45100000', '45100052', '百色市百东新区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('177', '', '45102300', '45102351', '平果工业区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('178', '', '45102900', '45102951', '田林县工业集中区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('179', '4511', '45000000', '45110000', '贺州市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('180', '4511001', '45110000', '45110001', '贺州市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('181', '4511002', '45110000', '45110200', '贺州市八步区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('182', '4511003', '45110000', '45112100', '贺州市昭平县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('183', '4511004', '45110000', '45112200', '贺州市钟山县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('184', '4511005', '45110000', '45112300', '贺州市富川瑶族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('185', '4511007', '45110000', '451100051', '贺州市平桂管理区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('186', '4511006', '45110000', '451100052', '贺州旺高工业区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('187', '4512', '45000000', '45120000', '河池市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('188', '4512001', '45120000', '45120001', '河池市直', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('189', '4512002', '45120000', '45120200', '河池市金城江区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('190', '4512003', '45120000', '45128100', '河池市宜州市', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('191', '4512004', '45120000', '45122100', '河池市南丹县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('192', '4512005', '45120000', '45122200', '河池市天峨县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('193', '4512006', '45120000', '45122500', '河池市罗城仫佬族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('194', '4512007', '45120000', '45122300', '河池市凤山县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('195', '4512008', '45120000', '45122400', '河池市东兰县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('196', '4512009', '45120000', '45122700', '河池市巴马瑶族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('197', '4512010', '45120000', '45122800', '河池市都安瑶族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('198', '4512011', '45120000', '45122900', '河池市大化瑶族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('199', '4512012', '45120000', '45122600', '河池市环江毛南族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('200', '4512013', '45128100', '45128151', '广西宜州经济开发区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('201', '4512014', '45120000', '45120051', '河池市工业园区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('202', '4512015', '45122600', '45122651', '河池·环江工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('203', '4512016', '45122100', '45122151', '河池·南丹工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('204', '', '45122200', '45122251', '天峨县工业集中区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('205', '', '45122800', '45122851', '河池·都安临港工业区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('206', '4513', '45000000', '45130000', '来宾市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('207', '4513001', '45130000', '45130001', '来宾市直属部门', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('208', '4513002', '45130000', '45130200', '来宾市兴宾区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('209', '4513003', '45130000', '45132200', '来宾市象州县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('210', '4513004', '45130000', '45132300', '来宾市武宣县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('211', '4513005', '45130000', '45132400', '来宾市金秀瑶族自治县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('212', '4513006', '45130000', '45132100', '来宾市忻城县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('213', '4513007', '45130000', '45138100', '来宾合山市', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('214', '4513008', '45130000', '45130051', '来宾华侨投资区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('215', '4513009', '45130000', '45130052', '来宾市工业区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('216', '', '45130000', '45130053', '广西来宾高新技术产业开发区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('217', '4514', '45000000', '45140000', '崇左市', '2', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('218', '4514001', '45000000', '45140001', '崇左市直属部门', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('219', '4514002', '45140000', '45140200', '崇左市江州区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('220', '4514003', '45140000', '45142100', '崇左市扶绥县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('221', '4514004', '45140000', '45142400', '崇左市大新县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('222', '4514005', '45140000', '45142500', '崇左市天等县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('223', '4514006', '45140000', '45142200', '崇左市宁明县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('224', '4514007', '45140000', '45142300', '崇左市龙州县', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('225', '4514008', '45140000', '45148100', '崇左市凭祥市', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('226', '4514009', '45140000', '45140052', '崇左市凭祥边境经济合作区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('227', '', '45140000', '45140051', '广西凭祥综合保税区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('228', '', '45140000', '45140053', '崇左市城市工业区', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('229', '', '45140000', '45140054', '崇左市广西中国-东盟青年产业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('230', '', '45140000', '45140055', '广西剑麻-林产循环科技产业园', '3', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('231', '', '45140200', '45140251', '崇左市江州区工业园区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('232', '', '45142100', '45142151', '中国-东盟南宁空港扶绥经济区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('233', '', '45142300', '45142351', '龙州水口口岸经济区', '4', null, null, null, null, null, null, null, null, '0');
INSERT INTO `s_area` VALUES ('234', '', '45142500', '45142551', '天等县工业园区管委会', '4', null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for `s_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `s_dictionary`;
CREATE TABLE `s_dictionary` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `DictionaryName` varchar(20) DEFAULT NULL,
  `DictionaryType` varchar(20) DEFAULT NULL,
  `OrderID` int(11) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for `s_job`
-- ----------------------------
DROP TABLE IF EXISTS `s_job`;
CREATE TABLE `s_job` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `Name` varchar(100) DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `RegionCode` varchar(12) DEFAULT NULL,
  `order` int(10) DEFAULT NULL COMMENT '职务大小排序',
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_job
-- ----------------------------
INSERT INTO `s_job` VALUES ('3e5b895919b146fbba7e8470861c06f2', '科员', null, '45000001', '231');
INSERT INTO `s_job` VALUES ('bc89fc34329448a5ba24f2ab2c0e8152', '工作人员', null, '45000001', '233');
INSERT INTO `s_job` VALUES ('bf40d0ca61be46a09380c3e3e337cbbf', '干事', null, '45000001', '232');

-- ----------------------------
-- Table structure for `s_job_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_job_user`;
CREATE TABLE `s_job_user` (
  `KeyID` varchar(50) NOT NULL,
  `userid` varchar(50) DEFAULT NULL COMMENT '用户id',
  `jobid` varchar(50) DEFAULT NULL COMMENT '职务id',
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of s_job_user
-- ----------------------------
INSERT INTO `s_job_user` VALUES ('4117ef3f083c4626bbdf4a02c10f9bf5', '4185ed984f3f4818ad51c72f6fa6cbfa', 'bc89fc34329448a5ba24f2ab2c0e8152');
INSERT INTO `s_job_user` VALUES ('91f53cc839984d668abc7f4115238e41', '4185ed984f3f4818ad51c72f6fa6cbfa', '3e5b895919b146fbba7e8470861c06f2');
INSERT INTO `s_job_user` VALUES ('c493da8ea2de473c9fc5ca1446ad7a56', '4185ed984f3f4818ad51c72f6fa6cbfa', 'bf40d0ca61be46a09380c3e3e337cbbf');

-- ----------------------------
-- Table structure for `s_job_user_parttime`
-- ----------------------------
DROP TABLE IF EXISTS `s_job_user_parttime`;
CREATE TABLE `s_job_user_parttime` (
  `KeyID` varchar(50) NOT NULL,
  `userid` varchar(50) DEFAULT NULL COMMENT '用户id',
  `jobid` varchar(50) DEFAULT NULL COMMENT '兼职职务id',
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of s_job_user_parttime
-- ----------------------------

-- ----------------------------
-- Table structure for `s_log`
-- ----------------------------
DROP TABLE IF EXISTS `s_log`;
CREATE TABLE `s_log` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `Module` varchar(20) DEFAULT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UserID` varchar(50) DEFAULT NULL,
  `S_DepartmentID` varchar(50) DEFAULT NULL,
  `UserName` varchar(20) DEFAULT NULL,
  `UIP` varchar(20) DEFAULT NULL,
  `OContent` longtext,
  `RegionCode` varchar(50) DEFAULT NULL,
  `OrgId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_log
-- ----------------------------
INSERT INTO `s_log` VALUES ('0820d3c29b7049de848f240212d5cbf4', '用户管理', '2016-04-14 09:31:54', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('222ab7ac71084910a225320d37b585e5', '角色管理', '2016-04-13 17:16:21', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对角色管理模块进行了修改操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('2d09bf10525a4842b1b7326b6af160c3', '用户管理', '2016-04-14 09:30:26', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('3cf35d144bc543e3b252a0a2c20f6b95', '用户管理', '2016-04-14 09:30:13', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('676d76b423c046f9bce3e6d2fd5dfe43', '用户管理', '2016-04-14 10:01:20', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('6ddb98d96de747c5b13712bd60f52046', '用户管理', '2016-04-14 09:31:56', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('9b0c83a693354e7bab2ca5ba4730c791', '角色管理', '2016-04-13 17:16:15', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对角色管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('d9111e46c1274acca435b24f2f0727dc', '角色管理', '2016-04-13 17:16:21', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对角色管理模块进行了修改操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');
INSERT INTO `s_log` VALUES ('f50293ce7c84473d8f4f6f216d08221e', '用户管理', '2016-04-14 09:31:40', '4185ed984f3f4818ad51c72f6fa6cbfa', '60be6a807dff4ae4a09639d09ae35847', 'zhangsan', '192.168.99.1', '对用户管理模块进行了查询操作 。', '45000001', 'dd56a1a13fb34a21afbc3b69a174f2d8');

-- ----------------------------
-- Table structure for `s_msg`
-- ----------------------------
DROP TABLE IF EXISTS `s_msg`;
CREATE TABLE `s_msg` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `Title` varchar(255) DEFAULT NULL,
  `MContent` varchar(255) DEFAULT NULL,
  `ToUser` varchar(50) DEFAULT NULL,
  `CreateUser` varchar(50) DEFAULT NULL,
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `State` int(11) DEFAULT NULL,
  `ForeignkeyID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_msg
-- ----------------------------

-- ----------------------------
-- Table structure for `s_org`
-- ----------------------------
DROP TABLE IF EXISTS `s_org`;
CREATE TABLE `s_org` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `org_fullname` varchar(50) DEFAULT NULL COMMENT '机构名称(中文全称)',
  `orgNum` varchar(50) DEFAULT NULL COMMENT '部门编号',
  `parent_orgid` varchar(50) DEFAULT NULL COMMENT '上级单位',
  `under_orgid` varchar(50) DEFAULT NULL COMMENT '下级单位',
  `org_fullname_abroad` varchar(255) DEFAULT NULL COMMENT '机构名称(英文全称)',
  `relation` varchar(20) DEFAULT NULL,
  `org_shutcut` varchar(30) DEFAULT NULL COMMENT '机构名称(官方简称)',
  `org_shutcut_abroad` varchar(255) DEFAULT NULL COMMENT '机构名称(英文简称)',
  `type` varchar(50) DEFAULT NULL COMMENT '单位性质',
  `address` varchar(100) DEFAULT NULL,
  `workarea` text COMMENT '工作职能（经营范围）',
  `zipcode` varchar(10) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `portraiture` varchar(255) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL COMMENT '官方网站',
  `email` varchar(100) DEFAULT NULL COMMENT 'E_mail',
  `webchat` varchar(50) DEFAULT NULL,
  `twitter` varchar(50) DEFAULT NULL,
  `regioncode` varchar(50) DEFAULT NULL,
  `regionname` varchar(50) DEFAULT NULL,
  `isvailable` int(1) DEFAULT '0',
  `status` int(1) DEFAULT '0',
  `supname` varchar(255) DEFAULT NULL,
  `supname_e` varchar(255) DEFAULT NULL,
  `supshortname` varchar(255) DEFAULT NULL,
  `supshortname_e` varchar(255) DEFAULT NULL,
  `suprelation` varchar(255) DEFAULT NULL,
  `subname` varchar(255) DEFAULT NULL,
  `subname_e` varchar(255) DEFAULT NULL,
  `subshortname` varchar(255) DEFAULT NULL,
  `subshortname_e` varchar(255) DEFAULT NULL,
  `subrelation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_org
-- ----------------------------
INSERT INTO `s_org` VALUES ('dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '45000001001', null, null, 'Guangxi Investment Promotion Agency', null, '广西投资促进局', 'GXIPA', '事业单位', '广西南宁市青秀区民族大道91号兴桂大厦6层', '1. 承办自治区促进民间投资、开展招商引资和区域经济合作的有关具体事宜；组织全区性重大投资促进活动；负责全区重大招商引资项目的协调推进工作，调查、研究全区招商引资、投资软环境建设工作情况，并向自治区人民政府提出相关政策建议和工作措施，配合有关部门协调做好投资软环境建设的有关工作。\n2. 负责策划、组织实施全区赴国（境）外开展投资促进活动，参与中国—东盟博览会有关项目的投资促进等工作；负责全区利用国（境）外资有关数据统计工作；与相关国际组织或境外投资促进机构开展业务合作。\n3. 负责对国内外重要投资考察团（组）来访的接洽工作；负责自治区组织的国内外投资合作项目的洽谈、签约和跟踪、协调、服务工作；负责全区“央企、民企入桂”招商引资工作。\n4. 负责全区工业园区的重大招商工作，会同有关部门开展工业园区重大项目的招商推介活动；负责工业园区重点招商项目库、重点客商名录库的建立工作；研究提出吸引外来投资参与园区开发建设的优惠政策。\n5. 负责县域投资促进工作；组织开展县域投资促进活动；负责研究提出全区县域投资促进工作政策措施和承接产业转移招商引资工作措施。\n6. 负责国内区域和省际间经济技术合作、对口支援工作；负责全区承接国内外产业转移的招商引资工作；根据自治区人民政府授权，负责自治区级年度招商引资奖励工作的组织和实施。\n7. 负责受理、协调和处理投资者非涉及党政机关公务人员的投诉事项。\n8. 根据自治区经济发展规划，建立和管理全区招商项目数据库，组织开展招商引资项目的规划、包装推介等工作，协调做好招商项目储备工作；负责建设和管理广西投资促进网；负责全区投资环境的宣传推介工作。\n9. 负责与国家主管投资促进工作部门的业务联系；负责与外国驻华机构和我国驻外机构在投资促进方面的业务联系；负责与外省（区、市）驻桂办事机构、我区驻外省（区、市）及境外办事机构开展有关投资促进业务的联络工作。\n10. 负责外省（区、市）设立驻桂商协会的备案登记；负责驻桂异地商协会、非公有制经济组织或团体等新社会组织的合法权益保障工作。负责外省（区、市）“广西商会”的联系。\n11. 承办自治区人民政府交办的其他事项。', '530022', '0771-5853854', '0771-5861612', 'www.gxipn.gov.cn', 'gxipa@gxipn.gov.cn', '', '', '45000001', null, '0', '0', '广西壮族自治区人民政府', '', '', '', '主管', '', '', '', '', '');

-- ----------------------------
-- Table structure for `s_org_dep`
-- ----------------------------
DROP TABLE IF EXISTS `s_org_dep`;
CREATE TABLE `s_org_dep` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `ParentID` varchar(50) DEFAULT NULL,
  `ParentName` varchar(50) DEFAULT NULL,
  `DepartmentName` varchar(20) DEFAULT NULL,
  `DepartmentOn` varchar(20) DEFAULT NULL,
  `orgkeyid` varchar(50) DEFAULT NULL,
  `orgName` varchar(255) DEFAULT NULL,
  `isAvailable` int(1) DEFAULT '0',
  `relativeType` varchar(10) DEFAULT NULL,
  `DepartmentWorkarea` text,
  `DepartmentLeader` varchar(20) DEFAULT NULL,
  `DepartmentPhone` varchar(30) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_org_dep
-- ----------------------------
INSERT INTO `s_org_dep` VALUES ('3b3e8c896810491391fdd0213efc289b', null, null, '招商政策调研处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责招商引资政策投资课题研究，分析评估招商引资发展趋势，探索创新招商引资模式，推广招商引资成果经验；\n2.负责全区招商引资有关数据统计和投资促进运行分析工作；\n3.拟订全区招商引资年度工作目标计划；\n4.组织实施对引进国（境）外和国内投资项目的奖励工作；\n5.牵头组织本局重要招商政策调研材料的撰写及相关文稿的起草工作。', '张庆岩', '0771-5887061', null);
INSERT INTO `s_org_dep` VALUES ('60be6a807dff4ae4a09639d09ae35847', null, null, '项目协调督办处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责全区重大招商引资项目的协调督办工作；\n2.调查处理投资投诉案件，提出诉案处理意见；\n3.办理本局在自治区政务服务中心的窗口业务；\n4.承担自治区招商引资项目大兑现工作领导小组的日常工作。', '唐燕', '0771-5893219', null);
INSERT INTO `s_org_dep` VALUES ('61550764b968466fbb840b4461e1de3d', null, null, '机关工会', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '', '蔡莉宁', '0771-5853006', null);
INSERT INTO `s_org_dep` VALUES ('7a626fbe695747e2b525c59b593623f4', null, null, '人事处（离退休人员工作处）', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责机关的人事管理、机构编制、教育培训、工资福利、出国（境）人员政审等工作；\n2.负责局机关在职人员的录用奖惩和专业技术职务的评聘工作；\n3.负责机关离退休人员工作。', '邓宏业', '0771-5852217', null);
INSERT INTO `s_org_dep` VALUES ('9b759a35f7a14308bc28c32cce083f31', null, null, '机关党委', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '', '陈行亮', '0771-5872371', null);
INSERT INTO `s_org_dep` VALUES ('9fe559b0d30e4d78af9e2efd337953ad', null, null, '县域经济投资促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责研究提出全区县域投资促进工作政策措施和承接产业转移招商引资工作措施；\n2.负责自治区投资促进活动的对外宣传工作；\n3.负责广西投资促进网络信息管理系统及官方网站的管理。', '黎明', '0771-5857979', null);
INSERT INTO `s_org_dep` VALUES ('a472c8705fe24244a4896a0040cc6bd9', null, null, '办公室', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责机关文电处理、机要保密、档案文印、行政后勤、计划生育等方面工作；\n2.负责机关财务、固定资产管理工作；\n3.负责有关会务、接待工作；\n4.负责机关内部管理制度建设；\n5.编印投资、招商信息简报；\n6.牵头组织本局重要文件文稿的起草工作。', '苏海光', '0771-5856880', null);
INSERT INTO `s_org_dep` VALUES ('ac1f38ac23a04646b1885dca07ec2134', null, null, '国内投资促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责与国内投资企业的联系和沟通工作，组织开展国内重大投资促进活动；\n2.统筹推进北部湾经济区投资促进工作；\n3.统筹推进全区各级各类园区的招商引资工作；\n4.承担自治区“百企入桂”工作领导小组的日常工作。', '梁艺', '0771-5865469', null);
INSERT INTO `s_org_dep` VALUES ('b66c73e3a538452db264bcef68678ea5', null, null, '外国投资促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责全区国（境）外投资促进活动的统筹和协调工作；\n2.统计分析全区外资促进工作；\n3.负责直接利用外资项目的跟踪、协调、服务工作；\n4.参与制定全区投资促进政策；\n5.负责策划、组织实施国（境）外投资促进活动；\n6.参与中国—东盟博览会投资促进活动；\n7.联络相关国际组织或境外投资促进机构开展业务合作；\n8.分析研究国际投资合作趋势及全区利用外资情况，提出工作建议和措施。', '陶德文', '0771-5856055', null);
INSERT INTO `s_org_dep` VALUES ('cac28ded4d6d4ecd93683bd78ed1d753', null, null, '区域合作处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责全区区域经济合作相关工作事项；\n2.会同有关部门推进我区与各类区域经济合作组织以及省（区、市）双边或多边经济合作事项的开展；\n3.牵头组织或配合自治区有关部门组团参加在国内举办的重要投资促进活动；\n4.承担对口支援方面的具体工作事项；\n5.办理外省（区、市）政府和经济组织驻桂办事机构备案登记；\n6.联系、沟通外省（区、市）广西商会；\n7.提出服务新社会阶层组织的工作建议和措施，开展维护商协会组织合法权益保障工作。', '唐非', '0771-5885239', null);
INSERT INTO `s_org_dep` VALUES ('fb640a3134e54331af9222467abc9d83', null, null, '投资项目促进处', null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '0', null, '1.负责自治区重大招商引资项目的策划、包装、推介、储备、发布等工作；\n2.汇总上报重大招商项目列入自治区人民政府当年统筹推进重大项目；\n3.负责全区重大招商引资项目的协调推进工作；\n4.建设和动态管理全区招商引资项目库；\n5.服务自治区重大投资促进活动的项目签约工作；\n6.推介投资环境和重点项目，编印投资、招商项目宣传资料；\n7.参与组织招商引资项目有关业务培训工作；\n8.协调管理招商项目专项经费；\n9.统筹推进“西江经济带”和资源富集区的投资促进工作。', '曾伟', '0771-5851255', null);

-- ----------------------------
-- Table structure for `s_org_temp`
-- ----------------------------
DROP TABLE IF EXISTS `s_org_temp`;
CREATE TABLE `s_org_temp` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `org_fullname` varchar(50) DEFAULT NULL,
  `orgNum` varchar(50) DEFAULT NULL,
  `parent_orgid` varchar(50) DEFAULT NULL,
  `under_orgid` varchar(50) DEFAULT NULL,
  `org_fullname_abroad` varchar(50) DEFAULT NULL,
  `relation` varchar(20) DEFAULT NULL,
  `org_shutcut` varchar(30) DEFAULT NULL,
  `org_shutcut_abroad` varchar(30) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `workarea` text,
  `zipcode` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `portraiture` varchar(20) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `webchat` varchar(50) DEFAULT NULL,
  `twitter` varchar(50) DEFAULT NULL,
  `regioncode` varchar(50) DEFAULT NULL,
  `regionname` varchar(50) DEFAULT NULL,
  `isvailable` int(1) DEFAULT '0',
  `status` int(1) DEFAULT '0',
  `supname` varchar(255) DEFAULT NULL,
  `supname_e` varchar(255) DEFAULT NULL,
  `supshortname` varchar(255) DEFAULT NULL,
  `supshortname_e` varchar(255) DEFAULT NULL,
  `suprelation` varchar(255) DEFAULT NULL,
  `subname` varchar(255) DEFAULT NULL,
  `subname_e` varchar(255) DEFAULT NULL,
  `subshortname` varchar(255) DEFAULT NULL,
  `subshortname_e` varchar(255) DEFAULT NULL,
  `subrelation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_org_temp
-- ----------------------------

-- ----------------------------
-- Table structure for `s_org_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_org_user`;
CREATE TABLE `s_org_user` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `userid` varchar(50) DEFAULT NULL,
  `orgid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_org_user
-- ----------------------------

-- ----------------------------
-- Table structure for `s_power`
-- ----------------------------
DROP TABLE IF EXISTS `s_power`;
CREATE TABLE `s_power` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `Name` varchar(100) DEFAULT NULL,
  `ParentID` varchar(100) DEFAULT NULL,
  `Url` varchar(200) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `ISShow` int(11) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_power
-- ----------------------------
INSERT INTO `s_power` VALUES ('0', '菜单导航', null, null, '../images/leftico01.png', '0', '0');
INSERT INTO `s_power` VALUES ('7', '个人中心', '0', null, null, '1', '0');
INSERT INTO `s_power` VALUES ('700', '待办事宜', '7', 'documentflow/document/MyPostilList.html', null, '0', '0');
INSERT INTO `s_power` VALUES ('703', '修改个人资料', '7', 'hr/user/pending/info.html?id', null, '3', '0');
INSERT INTO `s_power` VALUES ('704', '修改密码', '7', 'i/password.html', null, '4', '0');
INSERT INTO `s_power` VALUES ('705', '个人群组', '7', 'i/groupsList.html', null, '5', '0');
INSERT INTO `s_power` VALUES ('8', '系统管理', '0', null, '', '8', '0');
INSERT INTO `s_power` VALUES ('811', '用户管理', '8', 'system/userManage/userPage.html', null, '3', '0');
INSERT INTO `s_power` VALUES ('812', '角色管理', '8', 'system/roleManage/rolePage.html', null, '4', '0');
INSERT INTO `s_power` VALUES ('813', '数据字典管理', '8', 'system/dicManage/dicList.html', null, '6', '0');
INSERT INTO `s_power` VALUES ('814', '系统日记', '8', 'system/logManage/logList.html', null, '7', '0');
INSERT INTO `s_power` VALUES ('819', '职务管理', '8', 'system/jobManage/jobPage.html', null, '5', '0');
INSERT INTO `s_power` VALUES ('820', '区域管理', '8', 'system/areaManage/areaPage.html', null, '8', '0');
INSERT INTO `s_power` VALUES ('9', '帮助', '0', '', null, '9', '1');
INSERT INTO `s_power` VALUES ('910', '版本说明', '9', '', '../images/leftico01.png', '1', '0');
INSERT INTO `s_power` VALUES ('911', '操作手册', '9', null, null, '2', '0');
INSERT INTO `s_power` VALUES ('912', '使用帮助', '9', null, null, '3', '0');

-- ----------------------------
-- Table structure for `s_power_permission`
-- ----------------------------
DROP TABLE IF EXISTS `s_power_permission`;
CREATE TABLE `s_power_permission` (
  `KeyID` int(10) NOT NULL AUTO_INCREMENT,
  `powerId` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_power_permission
-- ----------------------------
INSERT INTO `s_power_permission` VALUES ('27', '811', 'EditPower', '修改');
INSERT INTO `s_power_permission` VALUES ('28', '811', 'DelPower', '删除');
INSERT INTO `s_power_permission` VALUES ('29', '812', 'AddPower', '增加');
INSERT INTO `s_power_permission` VALUES ('30', '812', 'EditPower', '修改');
INSERT INTO `s_power_permission` VALUES ('31', '812', 'DelPower', '删除');
INSERT INTO `s_power_permission` VALUES ('32', '813', 'AddPower', '增加');
INSERT INTO `s_power_permission` VALUES ('33', '813', 'EditPower', '修改');
INSERT INTO `s_power_permission` VALUES ('34', '813', 'DelPower', '删除');
INSERT INTO `s_power_permission` VALUES ('35', '814', 'EditPower', '修改');
INSERT INTO `s_power_permission` VALUES ('36', '814', 'DelPower', '删除');
INSERT INTO `s_power_permission` VALUES ('37', '819', 'AddPower', '增加');
INSERT INTO `s_power_permission` VALUES ('38', '819', 'EditPower', '修改');
INSERT INTO `s_power_permission` VALUES ('39', '819', 'DelPower', '删除');
INSERT INTO `s_power_permission` VALUES ('40', '815', 'AddPower', '增加');
INSERT INTO `s_power_permission` VALUES ('41', '815', 'EditPower', '修改');

-- ----------------------------
-- Table structure for `s_role`
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `Name` varchar(100) DEFAULT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `RegionCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('663912d4cf334659810d38f52b6f34f3', '一般工作人员', '6', '45000001');

-- ----------------------------
-- Table structure for `s_role_power`
-- ----------------------------
DROP TABLE IF EXISTS `s_role_power`;
CREATE TABLE `s_role_power` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `PowerID` varchar(50) DEFAULT NULL,
  `AddPower` int(11) DEFAULT NULL,
  `EditPower` int(11) DEFAULT NULL,
  `DelPower` int(11) DEFAULT NULL,
  `AppPower` int(11) DEFAULT NULL,
  `CheckPower` int(11) DEFAULT NULL COMMENT '审核权限',
  `objectID` varchar(50) DEFAULT NULL,
  `objectTyp` int(11) DEFAULT NULL,
  `LookPower` int(11) DEFAULT NULL,
  `permission` text,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_power
-- ----------------------------
INSERT INTO `s_role_power` VALUES ('2c7e5f65c40211e58b666cf049066867', '0', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e8199c40211e58b666cf049066867', '7', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e81edc40211e58b666cf049066867', '700', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e8244c40211e58b666cf049066867', '701', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e829cc40211e58b666cf049066867', '702', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e82f0c40211e58b666cf049066867', '703', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e8344c40211e58b666cf049066867', '704', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e839ac40211e58b666cf049066867', '8', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e83eec40211e58b666cf049066867', '800', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e85abc40211e58b666cf049066867', '810', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e8609c40211e58b666cf049066867', '811', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,EditPower,DelPower');
INSERT INTO `s_role_power` VALUES ('2c7e865fc40211e58b666cf049066867', '812', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,AddPower,EditPower,DelPower');
INSERT INTO `s_role_power` VALUES ('2c7e86b7c40211e58b666cf049066867', '813', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,AddPower,EditPower,DelPower');
INSERT INTO `s_role_power` VALUES ('2c7e8712c40211e58b666cf049066867', '814', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,EditPower,DelPower');
INSERT INTO `s_role_power` VALUES ('2c7e8768c40211e58b666cf049066867', '815', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,AddPower,EditPower,DelPower');
INSERT INTO `s_role_power` VALUES ('2c7e87bfc40211e58b666cf049066867', '816', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e8815c40211e58b666cf049066867', '817', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e886cc40211e58b666cf049066867', '819', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,AddPower,EditPower,DelPower');
INSERT INTO `s_role_power` VALUES ('2c7e88c1c40211e58b666cf049066867', '820', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e891ac40211e58b666cf049066867', '9', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e896fc40211e58b666cf049066867', '910', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e89c5c40211e58b666cf049066867', '911', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');
INSERT INTO `s_role_power` VALUES ('2c7e8a1bc40211e58b666cf049066867', '912', null, null, null, null, null, '663912d4cf334659810d38f52b6f34f3', null, '1', 'LookPower,');

-- ----------------------------
-- Table structure for `s_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_role_user`;
CREATE TABLE `s_role_user` (
  `KeyID` varchar(50) NOT NULL DEFAULT '',
  `userId` varchar(50) DEFAULT NULL,
  `roleId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_user
-- ----------------------------
INSERT INTO `s_role_user` VALUES ('97fd58fec5fb4916a8855730252c5ed7', '4185ed984f3f4818ad51c72f6fa6cbfa', '663912d4cf334659810d38f52b6f34f3');

-- ----------------------------
-- Table structure for `s_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `KeyID` varchar(50) NOT NULL DEFAULT '' COMMENT '用户id',
  `DepartmentID` varchar(50) DEFAULT NULL COMMENT '工作部门id',
  `PartTimeDepID` varchar(50) DEFAULT NULL COMMENT '兼职部门id',
  `RegionId` varchar(20) DEFAULT NULL COMMENT '用户所在区域编码',
  `JobsID` varchar(50) DEFAULT NULL COMMENT '职务id',
  `jobsid_b` varchar(50) DEFAULT NULL COMMENT '兼职职务id',
  `UserNum` varchar(255) DEFAULT '' COMMENT '人员编号',
  `UserName` varchar(255) DEFAULT NULL COMMENT '用户名/帐号',
  `RelaName` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `PassWord` varchar(255) DEFAULT NULL COMMENT '密码',
  `NameLetters` varchar(255) DEFAULT NULL COMMENT '姓名拼音',
  `Gender` int(11) DEFAULT NULL COMMENT '性别',
  `NativePlace` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `Nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `PoliticalStatus1` varchar(255) DEFAULT NULL COMMENT '政治面貌',
  `PoliticalStatus2` varchar(255) DEFAULT NULL,
  `PoliticalStatus3` varchar(255) DEFAULT NULL,
  `Birthday` date DEFAULT NULL COMMENT '出生日期',
  `IdNum` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `Address` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `Zipcode` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `Mobile` varchar(255) DEFAULT NULL COMMENT '移动电话',
  `Phone` varchar(255) DEFAULT NULL COMMENT '办公电话',
  `Fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `Email` varchar(255) DEFAULT NULL,
  `EmailPassword` varchar(255) DEFAULT NULL,
  `Weixin` varchar(255) DEFAULT NULL COMMENT '微信号',
  `QQ` varchar(255) DEFAULT NULL COMMENT 'QQ号',
  `Weibo` varchar(255) DEFAULT NULL COMMENT '微博',
  `LaborStatus` varchar(11) DEFAULT NULL COMMENT '人事关系现状',
  `Specialty` varchar(255) DEFAULT NULL,
  `Hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `JobTitle` varchar(255) DEFAULT NULL,
  `QualificationCertificate` varchar(255) DEFAULT NULL,
  `ChineseDialect` varchar(255) DEFAULT NULL,
  `ChineseDialectLevel` varchar(255) DEFAULT NULL,
  `ForeignLanguage` varchar(255) DEFAULT NULL,
  `ForeignLanguageLevel` varchar(255) DEFAULT NULL,
  `RegistrationTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPageSize` int(11) DEFAULT NULL,
  `NetWorkVisit` int(11) DEFAULT NULL,
  `typeid` varchar(50) DEFAULT NULL,
  `orgid` varchar(255) DEFAULT NULL COMMENT '所在机构id',
  `orgname` varchar(255) DEFAULT NULL COMMENT '所在机构名称',
  `orgdepid` varchar(255) DEFAULT NULL COMMENT '所在机构名称',
  `orgdepname` varchar(255) DEFAULT NULL COMMENT '所在机构名称',
  `orgdepid2` varchar(255) DEFAULT NULL COMMENT '所在机构名称',
  `orgdepname2` varchar(255) DEFAULT NULL COMMENT '所在机构名称',
  `orgregionid` varchar(255) DEFAULT NULL COMMENT '用户所在机构所属的区域编码',
  `orgNum` varchar(50) DEFAULT NULL COMMENT '用户所在机构编码',
  `isAvailable` int(1) DEFAULT '0' COMMENT '是否可用，0为可用，1为不可用，系统管理-用户管理中删除时将状态改为1，人力资源个人信息管理删除时执行彻底删除',
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('4185ed984f3f4818ad51c72f6fa6cbfa', null, null, '45000001', null, null, '450000010010008', 'zhangsan', '超级管理员', '07bd326091c30ece8b015d58b7c4e922', 'zhangsan', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '在职在岗', null, null, null, null, null, null, null, null, '2016-04-14 14:20:20', null, null, null, 'dd56a1a13fb34a21afbc3b69a174f2d8', '广西壮族自治区投资促进局', '60be6a807dff4ae4a09639d09ae35847', '项目协调督办处', null, null, '45000001', '45000001001', '0');
