/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : ningyou

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2017-11-17 11:52:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a
-- ----------------------------
DROP TABLE IF EXISTS `a`;
create table T_4S_CAR
(
  id          NUMBER(10) not null,
  brand       VARCHAR2(32) not null,
  series      VARCHAR2(32) not null,
  type        NUMBER(2) not null,
  volume      VARCHAR2(12) not null,
  color       VARCHAR2(12) not null,
  pro_place   VARCHAR2(12) not null,
  price       NUMBER(10,2) not null,
  create_date DATE,
  del_flag    NUMBER(2) not null,
  remark      VARCHAR2(64),
  pic_path    VARCHAR2(64) not null
)