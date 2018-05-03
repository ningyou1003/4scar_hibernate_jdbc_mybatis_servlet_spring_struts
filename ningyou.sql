/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.15 : Database - ningyou
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ningyou` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ningyou`;

/*Table structure for table `a0` */

DROP TABLE IF EXISTS `a0`;

CREATE TABLE `a0` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `sex` int(2) NOT NULL DEFAULT '0' COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `a0` */

insert  into `a0`(`id`,`name`,`sex`,`birthday`) values (2,'a',2,'1585-08-08');
insert  into `a0`(`id`,`name`,`sex`,`birthday`) values (3,'1',2,'1111-01-01');

/*Table structure for table `a9` */

DROP TABLE IF EXISTS `a9`;

CREATE TABLE `a9` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '',
  `sex` int(2) NOT NULL DEFAULT '0',
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `a9` */

/*Table structure for table `t_1` */

DROP TABLE IF EXISTS `t_1`;

CREATE TABLE `t_1` (
  `num` int(11) NOT NULL AUTO_INCREMENT COMMENT 'xuehao',
  `sex` int(2) NOT NULL DEFAULT '2' COMMENT 'xingbei',
  `name` varchar(8) NOT NULL DEFAULT '' COMMENT 'xingming',
  `birthday` date DEFAULT NULL COMMENT 'shenri',
  `qq` int(16) DEFAULT '0' COMMENT 'qq',
  `zhuanye` varchar(16) NOT NULL DEFAULT '' COMMENT 'zy',
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_1` */

/*Table structure for table `t_class` */

DROP TABLE IF EXISTS `t_class`;

CREATE TABLE `t_class` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `name` varchar(32) NOT NULL COMMENT '班级名称',
  `charger` varchar(32) DEFAULT NULL COMMENT '班主任',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_class` */

insert  into `t_class`(`id`,`name`,`charger`,`create_date`) values (1,'软件工程一班','李小珍','2016-12-23');
insert  into `t_class`(`id`,`name`,`charger`,`create_date`) values (2,'软件工程二班','彭娟','2016-12-23');
insert  into `t_class`(`id`,`name`,`charger`,`create_date`) values (3,'软件工程三班','王小利','2016-12-23');
insert  into `t_class`(`id`,`name`,`charger`,`create_date`) values (4,'软件工程四班','李小彤','2016-12-23');
insert  into `t_class`(`id`,`name`,`charger`,`create_date`) values (5,'软件工程五班','潘霞','2016-12-23');
insert  into `t_class`(`id`,`name`,`charger`,`create_date`) values (8,'a','a','2017-01-04');

/*Table structure for table `t_course` */

DROP TABLE IF EXISTS `t_course`;

CREATE TABLE `t_course` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID（PK）',
  `name` varchar(32) NOT NULL COMMENT '课程名称',
  `credit` int(8) NOT NULL COMMENT '学分',
  `create_date` date NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_course` */

/*Table structure for table `t_course_on` */

DROP TABLE IF EXISTS `t_course_on`;

CREATE TABLE `t_course_on` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `teacher_id` int(10) NOT NULL COMMENT '授课老师id',
  `course_id` int(10) NOT NULL COMMENT '课程id',
  `year` int(6) NOT NULL COMMENT '年度（2014,2015,2016）',
  `school_term` int(2) NOT NULL COMMENT '学期（1-春节学期，2-秋季学期）',
  `state` int(2) NOT NULL COMMENT '状态（1-正常，2-无效）',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_course_on` */

/*Table structure for table `t_dict` */

DROP TABLE IF EXISTS `t_dict`;

CREATE TABLE `t_dict` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `dict_name` varchar(32) NOT NULL COMMENT '字典名称(英文大写，多个单词用下画线分隔)',
  `ckey` varchar(32) NOT NULL COMMENT '字典KEY',
  `cvalue` varchar(32) NOT NULL COMMENT '字典VALUE',
  `use_flag` int(2) NOT NULL COMMENT '状态(1-正常,2-无效)',
  `order_no` int(2) NOT NULL COMMENT '顺序号',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_dict` */

insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (1,'SYS_SEX','1','男',1,1,'2016-12-29');
insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (2,'SYS_SEX','2','女',1,4,'2016-12-29');
insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (3,'SYS_STATE','1','正常',1,2,'2016-12-29');
insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (4,'SYS_STATE','2','无效',1,4,'2016-12-29');
insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (5,'MENU_LEVEL','1','一级',1,4,'2016-12-30');
insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (6,'MENU_LEVEL','2','二级',1,6,'2017-01-06');
insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (7,'MENU_LEVEL','3','三级',1,8,'2017-01-06');
insert  into `t_dict`(`id`,`dict_name`,`ckey`,`cvalue`,`use_flag`,`order_no`,`create_date`) values (8,'123','1','1',2,1,'2017-01-14');

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单ID（pk）',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `url` varchar(64) NOT NULL COMMENT '链接路径',
  `parent_id` int(10) DEFAULT NULL COMMENT '父菜单',
  `menu_level` int(2) NOT NULL COMMENT '级别(1-一级，2-二级)',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (1,'系统管理','url',0,1,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (2,'基础数据','url',0,1,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (3,'字典管理','dict_list.do',1,2,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (4,'菜单管理','menu_list.do',1,2,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (5,'角色管理','role_list.do',1,2,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (6,'用户管理','user_list.do',1,2,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (7,'教学管理','url',0,1,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (8,'班级管理','tclass_list.do',2,2,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (9,'学生管理','student_list.do',2,2,'2017-01-06');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (10,'嘎嘎','student_list.do',7,2,'2017-01-07');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (11,'汪汪','student_list.do',7,2,'2017-01-07');
insert  into `t_menu`(`id`,`name`,`url`,`parent_id`,`menu_level`,`create_date`) values (12,'教师管理','teacher_list.do',2,2,'2017-01-09');

/*Table structure for table `t_menu0` */

DROP TABLE IF EXISTS `t_menu0`;

CREATE TABLE `t_menu0` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(64) DEFAULT NULL COMMENT '链接路径',
  `parent_id` int(10) NOT NULL COMMENT '父菜单',
  `menu_leve` int(2) DEFAULT NULL COMMENT '级别(1-一级，2—二级)',
  `create_date` date NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu0` */

insert  into `t_menu0`(`id`,`name`,`url`,`parent_id`,`menu_leve`,`create_date`) values (1,'系统管理','url',0,1,'2016-12-23');
insert  into `t_menu0`(`id`,`name`,`url`,`parent_id`,`menu_leve`,`create_date`) values (2,'基础数据','url',0,1,'2016-12-23');
insert  into `t_menu0`(`id`,`name`,`url`,`parent_id`,`menu_leve`,`create_date`) values (3,'字典管理','dict_list.do',1,2,'2016-12-23');
insert  into `t_menu0`(`id`,`name`,`url`,`parent_id`,`menu_leve`,`create_date`) values (4,'菜单管理','menu_list.do',1,2,'2016-12-23');
insert  into `t_menu0`(`id`,`name`,`url`,`parent_id`,`menu_leve`,`create_date`) values (5,'角色管理','role_list.do',1,2,'2016-12-23');
insert  into `t_menu0`(`id`,`name`,`url`,`parent_id`,`menu_leve`,`create_date`) values (6,'用户管理','user_list.do',1,2,'2016-12-23');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`create_date`) values (1,'管理员','2017-01-06');
insert  into `t_role`(`id`,`name`,`create_date`) values (2,'教师角色','2017-01-06');
insert  into `t_role`(`id`,`name`,`create_date`) values (3,'学生角色','2017-01-06');
insert  into `t_role`(`id`,`name`,`create_date`) values (5,'asd','2017-01-17');

/*Table structure for table `t_role2menu` */

DROP TABLE IF EXISTS `t_role2menu`;

CREATE TABLE `t_role2menu` (
  `role_id` int(10) NOT NULL COMMENT '角色ID',
  `menu_id` int(10) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role2menu` */

insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,1);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,3);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,4);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,5);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,6);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (2,1);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (2,3);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (2,4);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,2);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,8);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,9);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,12);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,7);
insert  into `t_role2menu`(`role_id`,`menu_id`) values (1,10);

/*Table structure for table `t_score` */

DROP TABLE IF EXISTS `t_score`;

CREATE TABLE `t_score` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `student_id` int(10) DEFAULT NULL COMMENT '学生ID(FK)',
  `study_year` varchar(32) DEFAULT NULL COMMENT '学年(2010-春季)',
  `maths` decimal(8,2) DEFAULT NULL COMMENT '数学',
  `english` decimal(8,2) DEFAULT NULL COMMENT '英语',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

/*Data for the table `t_score` */

insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (1,1,'2014-春季',100.00,57.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (2,1,'2015-春季',99.00,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (3,2,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (4,3,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (5,4,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (6,5,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (7,6,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (8,7,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (9,8,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (10,9,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (11,10,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (12,11,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (13,12,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (14,13,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (15,14,'2014-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (16,1,'2014-秋季',89.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (17,2,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (18,3,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (19,4,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (20,5,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (21,6,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (22,7,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (23,8,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (24,9,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (25,10,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (26,11,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (27,12,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (28,13,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (29,14,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (30,1,'2015-春季',89.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (31,2,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (32,3,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (33,4,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (34,5,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (35,6,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (36,7,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (37,8,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (38,9,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (39,10,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (40,11,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (41,12,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (42,13,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (43,14,'2015-春季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (44,1,'2014-秋季',89.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (45,2,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (46,3,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (47,4,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (48,5,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (49,6,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (50,7,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (51,8,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (52,9,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (53,10,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (54,11,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (55,12,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (56,13,'2014-秋季',104.50,67.50);
insert  into `t_score`(`id`,`student_id`,`study_year`,`maths`,`english`) values (57,14,'2014-秋季',104.50,67.50);

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别(1-男,2-女)',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `native` varchar(32) DEFAULT NULL COMMENT '籍贯',
  `class_id` int(10) DEFAULT NULL COMMENT '班级ID(FK)',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (1,'谢美玲',2,'2016-12-07','广西百色',NULL,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (2,'谢芳',2,'1993-05-07','广西百色',3,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (3,'何小玲',2,'1994-12-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (4,'何金健',1,'1994-02-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (5,'何康',1,'1990-12-01','广西桂林',2,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (6,'卢欢',2,'1992-03-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (7,'韦小芳',2,'1993-05-07','广西百色',3,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (8,'王小利',2,'1995-02-05','广西钦州',4,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (9,'谢婷',2,'1995-11-27','广西南宁',5,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (10,'何玲',2,'1994-12-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (11,'王鑫',1,'1994-02-07','广西百色',5,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (12,'何霖',1,'1990-12-01','广西桂林',2,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (13,'卢铮',1,'1992-03-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (14,'王芳',2,'1993-05-07','广西百色',3,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (15,'周比利',1,'1995-02-05','广西钦州',4,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (16,'谢胜',1,'1995-11-27','广西南宁',5,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (17,'周小玲',2,'1994-12-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (18,'周金健',1,'1994-02-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (19,'黄康',1,'1990-12-01','广西桂林',2,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (20,'李欢',2,'1992-03-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (21,'杨小芳',2,'1993-05-07','广西百色',3,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (22,'杨小利',2,'1995-02-05','广西钦州',4,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (23,'李婷',2,'1995-11-27','广西南宁',5,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (24,'王玲',2,'1994-12-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (25,'周鑫',1,'1994-02-07','广西百色',5,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (26,'卢霖',1,'1990-12-01','广西桂林',2,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (27,'王铮',1,'1992-03-07','广西百色',1,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (28,'谢芳',2,'1993-05-07','广西百色',3,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (29,'王大利',1,'1995-02-05','广西钦州',4,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (30,'谢庐',1,'1995-11-27','广西南宁',5,'2016-12-23');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (32,'ning',1,'1111-11-01','aa',1,'1111-11-01');
insert  into `t_student`(`id`,`name`,`sex`,`birthday`,`native`,`class_id`,`create_date`) values (33,'aa',1,'1111-11-01','bb',2,'1111-11-01');

/*Table structure for table `t_sys_menu_level` */

DROP TABLE IF EXISTS `t_sys_menu_level`;

CREATE TABLE `t_sys_menu_level` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `create_date` date NOT NULL COMMENT '创建日期',
  KEY `ID` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_menu_level` */

insert  into `t_sys_menu_level`(`id`,`name`,`create_date`) values (1,'ning','2016-12-30');
insert  into `t_sys_menu_level`(`id`,`name`,`create_date`) values (2,'ni','2016-12-30');
insert  into `t_sys_menu_level`(`id`,`name`,`create_date`) values (3,'nin','2016-12-30');
insert  into `t_sys_menu_level`(`id`,`name`,`create_date`) values (4,NULL,'2016-12-30');
insert  into `t_sys_menu_level`(`id`,`name`,`create_date`) values (5,'nn','2016-12-30');

/*Table structure for table `t_teacher` */

DROP TABLE IF EXISTS `t_teacher`;

CREATE TABLE `t_teacher` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `sex` int(1) NOT NULL COMMENT '性别（1-男，2-女）',
  `birthday` date NOT NULL COMMENT '出生日期',
  `work_date` date NOT NULL COMMENT '开始工作日期',
  `speclalty` varchar(32) NOT NULL COMMENT '专业方向',
  `level` int(2) NOT NULL COMMENT '职称等级（1-初级，2-中级，3-高级）',
  `create_date` date NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_teacher` */

insert  into `t_teacher`(`id`,`name`,`sex`,`birthday`,`work_date`,`speclalty`,`level`,`create_date`) values (1,'老王',1,'2017-01-09','2017-01-09','隔壁的',1,'2017-01-09');
insert  into `t_teacher`(`id`,`name`,`sex`,`birthday`,`work_date`,`speclalty`,`level`,`create_date`) values (2,'a',1,'2017-01-10','2017-01-10','a',1,'2017-01-10');
insert  into `t_teacher`(`id`,`name`,`sex`,`birthday`,`work_date`,`speclalty`,`level`,`create_date`) values (3,'b',1,'2017-01-10','2017-01-10','b',1,'2017-01-10');
insert  into `t_teacher`(`id`,`name`,`sex`,`birthday`,`work_date`,`speclalty`,`level`,`create_date`) values (4,'c',1,'2017-01-10','2017-01-10','c',1,'2017-01-10');
insert  into `t_teacher`(`id`,`name`,`sex`,`birthday`,`work_date`,`speclalty`,`level`,`create_date`) values (6,'f',2,'2017-01-10','2017-02-03','f',1,'2017-01-10');

/*Table structure for table `t_temp` */

DROP TABLE IF EXISTS `t_temp`;

CREATE TABLE `t_temp` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_temp` */

insert  into `t_temp`(`id`,`name`) values (1,'');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `sex` int(2) NOT NULL COMMENT '性别(1-男,2-女)',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `login_flag` int(2) DEFAULT NULL COMMENT '登录状态(1-正常,2-无效)',
  `role_id` int(10) NOT NULL DEFAULT '1' COMMENT '角色ID',
  `file_path` varchar(64) DEFAULT NULL COMMENT '文件相对路径',
  `create_date` date NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2048 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (3,'gg',2,'1900-11-28','yy','333',1,1,NULL,'2016-12-10');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (4,'jj',1,'2000-11-27','xx','4',1,1,NULL,'2016-12-31');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (5,'kk',1,'1800-12-31','gg','1',2,1,NULL,'2016-12-03');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (6,'hh',2,NULL,'ff','1',NULL,1,NULL,'2000-01-01');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (7,'ff',1,'1994-02-01','abc','1',2,1,NULL,'2016-12-26');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (8,'测试用户',1,'1994-02-01','abc','1',2,1,NULL,'2016-12-27');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (9,'测试用户',1,'1994-02-01','abc','1',2,1,NULL,'2016-12-27');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (10,'测试用户1',1,'1994-02-01','abc','1',2,1,NULL,'2016-12-27');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (11,'b',1,'1994-02-02','abc','1',2,1,NULL,'2016-12-27');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (15,'测试用户1',1,'1994-02-01','abc','1',2,1,NULL,'2016-12-27');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (16,'测试用户test6',1,'2016-12-27','xx','222',1,1,NULL,'2016-12-27');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2019,'123',1,'2015-11-11','abc','123',0,1,NULL,'2016-12-28');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2020,'a',1,'1111-11-11','a','a',1,1,NULL,'2016-12-28');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2021,'a',1,'1111-11-11','a','123',0,1,NULL,'2016-12-28');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2022,'a',1,'1111-11-11','a','123',0,1,NULL,'2016-12-28');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2023,'宁',1,'1111-11-11','ning','123',1,1,NULL,'2016-12-29');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2024,' 啊',1,'2017-01-03','啊','123',1,1,NULL,'2017-01-07');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2025,'啊',1,'2017-01-25','啊','123',1,1,NULL,'2017-01-07');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2026,'b',1,'2017-01-07','b','b',1,2,NULL,'2017-01-07');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2028,'cc',1,'2017-01-09','c','123',1,1,'null','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2029,'qq',1,'2017-01-09','qq','123',1,1,'null','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2031,'123',1,'2017-01-09','123','123',1,1,'null','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2032,'1',1,'2017-01-09','11','123',1,1,'null','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2033,'222',1,'2017-01-09','2','123',1,1,'null','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2036,'1',1,'2017-01-09','1','123',1,1,'1483944796939.jpg','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2037,'1',1,'2017-01-09','1','123',1,1,'1483944906475.jpg','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2039,'123123132',1,'2017-01-09','132123','123',1,1,'','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2040,'111',1,'2017-01-09','1','123',1,3,'1483949958077.jpg','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2041,'aaa',1,'2017-01-09','a','123',1,3,'','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2042,'aaaaaaa',1,'2017-01-09','fff','123',1,3,'1483950042775.jpg','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2043,'q',1,'2017-01-09','q','123',1,3,'','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2044,'qqqqq',1,'2017-01-09','q','123',1,3,'1483950223182.jpg','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2045,'12we',1,'2017-01-09','2','123',1,3,'','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2046,'231e',1,'2017-01-09','2','123',1,3,'1483950308457.jpg','2017-01-09');
insert  into `t_user`(`id`,`name`,`sex`,`birthday`,`username`,`password`,`login_flag`,`role_id`,`file_path`,`create_date`) values (2047,'a',1,'2017-01-09','dddd','123',1,3,'1483950557488.jpg','2017-01-09');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
