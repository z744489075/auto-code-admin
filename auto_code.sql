/*
SQLyog 
MySQL - 5.7.17-log : Database - auto_code
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`auto_code` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `auto_code`;

/*Table structure for table `file_images` */

DROP TABLE IF EXISTS `file_images`;

CREATE TABLE `file_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片管理',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `image_url` varchar(200) DEFAULT NULL COMMENT '图片url',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片管理';

/*Data for the table `file_images` */

/*Table structure for table `sys_auth` */

DROP TABLE IF EXISTS `sys_auth`;

CREATE TABLE `sys_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台权限',
  `parent_auth_id` int(11) DEFAULT NULL COMMENT '父id',
  `parent_auth_ids` varchar(2000) DEFAULT NULL COMMENT '所有父id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `shows` int(2) DEFAULT '0' COMMENT '{"name":"是否显示","0":"显示","1":"不显示"}',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=368 DEFAULT CHARSET=utf8 COMMENT='后台权限';

/*Data for the table `sys_auth` */

insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (1,0,NULL,'系统设置',1,'','layui-icon layui-icon layui-icon-set',0,1,'2019-01-17 15:46:30',1,'2019-02-19 15:09:02');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (2,0,NULL,'日志管理',2,'','layui-icon layui-icon-dialogue',0,1,'2019-01-23 15:01:12',1,'2019-01-23 18:18:46');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (3,0,NULL,'代码生成',3,'','layui-icon layui-icon-fonts-code',0,1,'2019-01-23 17:52:37',1,'2019-01-23 18:18:47');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (4,0,NULL,'报表管理',4,'','layui-icon layui-icon-chart',0,1,'2019-01-17 17:32:27',1,'2019-01-23 17:54:11');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (7,4,NULL,'折线图',1,'sysUserCharts/gotoLine','layui-icon layui-icon layui-icon layui-icon layui-icon-chart-screen',0,1,'2019-01-17 17:32:45',1,'2019-01-24 09:42:44');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (9,11,NULL,'权限设置',1,'sysAuth/gotoList','layui-icon layui-icon layui-icon layui-icon-auz',0,1,'2019-01-17 17:30:48',1,'2019-02-19 15:09:15');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (11,1,NULL,'管理员设置',1,'','layui-icon layui-icon layui-icon layui-icon-username',0,1,'2019-01-17 15:46:59',1,'2019-01-23 17:53:51');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (15,11,NULL,'角色管理',2,'sysRole/gotoList','layui-icon layui-icon layui-icon-username',0,1,'2019-01-21 14:37:33',1,'2019-01-24 09:34:17');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (16,15,NULL,'编辑',1,'sysRole/save','layui-icon layui-icon-username',1,NULL,'2019-01-21 14:38:44',NULL,'2019-01-23 13:57:37');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (17,15,NULL,'导出',2,'sysRole/export','layui-icon layui-icon-next',1,NULL,'2019-01-21 14:39:45',NULL,'2019-01-23 13:57:40');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (18,11,NULL,'用户设置',3,'sysUser/gotoList','layui-icon layui-icon layui-icon-user',0,1,'2019-01-21 14:45:17',1,'2019-01-24 09:34:29');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (19,18,NULL,'查看',1,'sysUser/selectAll','layui-icon layui-icon-search',1,NULL,'2019-01-21 14:46:35',NULL,'2019-01-23 13:57:41');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (20,18,NULL,'导出',2,'sysUser/export','layui-icon layui-icon-next',1,NULL,'2019-01-21 14:48:40',NULL,'2019-01-23 13:57:43');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (21,18,NULL,'编辑',3,'sysUser/save','layui-icon layui-icon-edit',1,NULL,'2019-01-21 14:49:21',NULL,'2019-01-23 13:57:45');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (22,15,NULL,'查询',1,'sysRole/selectAll','layui-icon layui-icon-search',1,NULL,'2019-01-21 14:52:20',NULL,'2019-01-23 13:57:47');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (32,1,NULL,'文件管理',2,'','layui-icon layui-icon-file-b',0,1,'2019-01-22 15:37:45',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (33,32,NULL,'图片管理',1,'fileImages/gotoList','layui-icon layui-icon-file',0,1,'2019-01-22 15:38:45',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (34,33,NULL,'查看',1,'fileImages/selectAll','layui-icon layui-icon-search',1,1,'2019-01-22 15:39:24',1,'2019-01-23 13:57:52');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (35,33,NULL,'删除',2,'fileImages/deleteByPrimaryKey','layui-icon layui-icon layui-icon-delete',1,1,'2019-01-22 15:39:54',1,'2019-01-23 13:57:54');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (36,33,NULL,'导出',3,'fileImages/export','layui-icon layui-icon-next',1,1,'2019-01-22 15:40:58',1,'2019-01-23 13:57:55');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (37,33,NULL,'修改',4,'fileImages/save','layui-icon layui-icon-edit',1,1,'2019-01-22 15:41:40',1,'2019-01-23 13:57:58');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (40,2,NULL,'操作日志',1,'sysOperationLog/gotoList','layui-icon layui-icon layui-icon layui-icon-top',0,1,'2019-01-23 15:02:17',1,'2019-01-24 09:42:07');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (41,40,NULL,'查询',1,'sysOperationLog/selectAll','layui-icon layui-icon-search',1,1,'2019-01-23 15:02:49',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (42,40,NULL,'导出',2,'sysOperationLog/export','layui-icon layui-icon-next',1,1,'2019-01-23 15:03:17',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (43,2,NULL,'登录日志',2,'sysLoginLog/gotoList','layui-icon layui-icon layui-icon-login-weibo',0,1,'2019-01-23 15:20:00',1,'2019-01-24 09:42:28');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (44,43,NULL,'查询',1,'sysLoginLog/selectAll','layui-icon layui-icon-search',1,1,'2019-01-23 15:20:26',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (45,43,NULL,'导出',2,'sysLoginLog/export','layui-icon layui-icon-next',1,1,'2019-01-23 15:21:14',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (46,4,NULL,'柱状图',2,'sysUserCharts/gotoBar','layui-icon layui-icon layui-icon-chart-screen',0,1,'2019-01-23 17:10:02',1,'2019-01-24 09:42:52');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (47,4,NULL,'饼图',3,'sysUserCharts/gotoPie','layui-icon layui-icon layui-icon layui-icon-picture',0,1,'2019-01-23 17:10:33',1,'2019-01-24 09:43:00');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (48,1,NULL,'数据源管理',3,'druid/index.html','layui-icon layui-icon-console',0,1,'2019-01-23 17:32:22',1,'2019-01-23 17:32:58');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (65,9,NULL,'查询',1,'sysAuth/selectAll','layui-icon layui-icon-search',1,1,'2019-01-24 09:39:22',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (66,9,NULL,'删除',2,'sysAuth/selectByPrimaryKey','layui-icon layui-icon-fonts-del',1,1,'2019-01-24 09:39:48',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (67,9,NULL,'编辑',3,'sysAuth/save','layui-icon layui-icon-edit',1,1,'2019-01-24 09:40:27',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (68,9,NULL,'导出',4,'sysAuth/export','layui-icon layui-icon-next',1,1,'2019-01-24 09:41:00',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (89,3,NULL,'单表生成',1,'','layui-icon layui-icon-fonts-code',0,1,'2019-03-06 10:14:39',1,'2019-03-06 10:18:21');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (90,3,NULL,'一对一生成',2,'','layui-icon layui-icon-fonts-code',0,1,'2019-03-06 10:15:52',1,'2019-03-06 10:17:22');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (91,3,NULL,'一对多生成',3,'','layui-icon layui-icon-fonts-code',0,1,'2019-03-06 10:16:01',1,'2019-03-06 10:17:58');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (92,3,NULL,'多对多生成',4,'','layui-icon layui-icon-fonts-code',0,1,'2019-03-06 10:16:11',1,'2019-03-06 10:17:42');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (273,89,NULL,'单表代码生成',1,'simpleCode/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-03-12 15:37:21',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (274,273,NULL,'查询',1,'simpleCode/selectAllByPaging','layui-icon layui-icon-search',1,NULL,'2019-03-12 15:37:21',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (275,273,NULL,'导出',2,'simpleCode/export','layui-icon layui-icon-next',1,NULL,'2019-03-12 15:37:21',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (276,273,NULL,'编辑',3,'simpleCode/save','layui-icon layui-icon-edit',1,NULL,'2019-03-12 15:37:21',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (277,273,NULL,'删除',4,'simpleCode/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-03-12 15:37:21',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (308,90,NULL,'一对一用户',1,'oneToOneUser/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-03-12 16:08:50',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (309,308,NULL,'查询',1,'oneToOneUser/selectAllByPaging','layui-icon layui-icon-search',1,NULL,'2019-03-12 16:08:50',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (310,308,NULL,'导出',2,'oneToOneUser/export','layui-icon layui-icon-next',1,NULL,'2019-03-12 16:08:50',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (311,308,NULL,'编辑',3,'oneToOneUser/save','layui-icon layui-icon-edit',1,NULL,'2019-03-12 16:08:50',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (312,308,NULL,'删除',4,'oneToOneUser/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-03-12 16:08:50',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (313,90,NULL,'一对一班级',1,'oneToOneClass/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-03-12 16:08:51',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (314,313,NULL,'查询',1,'oneToOneClass/selectAllByPaging','layui-icon layui-icon-search',1,NULL,'2019-03-12 16:08:51',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (315,313,NULL,'导出',2,'oneToOneClass/export','layui-icon layui-icon-next',1,NULL,'2019-03-12 16:08:51',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (316,313,NULL,'编辑',3,'oneToOneClass/save','layui-icon layui-icon-edit',1,NULL,'2019-03-12 16:08:51',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (317,313,NULL,'删除',4,'oneToOneClass/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-03-12 16:08:51',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (318,91,NULL,'一对多用户',1,'oneToManyUser/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-03-12 16:09:03',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (319,318,NULL,'查询',1,'oneToManyUser/selectAllByPaging','layui-icon layui-icon-search',1,NULL,'2019-03-12 16:09:03',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (320,318,NULL,'导出',2,'oneToManyUser/export','layui-icon layui-icon-next',1,NULL,'2019-03-12 16:09:03',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (321,318,NULL,'编辑',3,'oneToManyUser/save','layui-icon layui-icon-edit',1,NULL,'2019-03-12 16:09:03',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (322,318,NULL,'删除',4,'oneToManyUser/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-03-12 16:09:03',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (328,92,NULL,'多对多用户',1,'manyToManyUser/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (329,328,NULL,'查询',1,'manyToManyUser/selectAllByPaging','layui-icon layui-icon-search',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (330,328,NULL,'导出',2,'manyToManyUser/export','layui-icon layui-icon-next',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (331,328,NULL,'编辑',3,'manyToManyUser/save','layui-icon layui-icon-edit',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (332,328,NULL,'删除',4,'manyToManyUser/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (333,92,NULL,'多对多角色',1,'manyToManyRole/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (334,333,NULL,'查询',1,'manyToManyRole/selectAllByPaging','layui-icon layui-icon-search',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (335,333,NULL,'导出',2,'manyToManyRole/export','layui-icon layui-icon-next',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (336,333,NULL,'编辑',3,'manyToManyRole/save','layui-icon layui-icon-edit',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (337,333,NULL,'删除',4,'manyToManyRole/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-03-12 16:09:09',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (363,91,NULL,'一对多收货地址',1,'oneToManyAddr/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-03-13 09:30:33',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (364,363,NULL,'查询',1,'oneToManyAddr/selectAllByPaging','layui-icon layui-icon-search',1,NULL,'2019-03-13 09:30:33',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (365,363,NULL,'导出',2,'oneToManyAddr/export','layui-icon layui-icon-next',1,NULL,'2019-03-13 09:30:33',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (366,363,NULL,'编辑',3,'oneToManyAddr/save','layui-icon layui-icon-edit',1,NULL,'2019-03-13 09:30:33',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (367,363,NULL,'删除',4,'oneToManyAddr/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-03-13 09:30:33',NULL,NULL);
INSERT INTO `sys_auth` (`id`, `parent_auth_id`, `parent_auth_ids`, `name`, `sort`, `href`, `icon`, `shows`, `create_user_id`, `create_time`, `update_user_id`, `update_time`) VALUES('368','0',NULL,'swagger-api','5','swagger-ui.html#/','layui-icon layui-icon-app','0','1','2019-03-18 16:24:14','1',NULL);
/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(也是登录时间)',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `browser` varchar(200) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='登录日志';

/*Data for the table `sys_login_log` */

/*Table structure for table `sys_operation_log` */

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志',
  `sys_user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `sys_auth_id` int(11) DEFAULT NULL COMMENT '权限id',
  `auth_name` varchar(200) DEFAULT NULL COMMENT '权限名',
  `auth_href` varchar(200) DEFAULT NULL COMMENT '权限链接',
  `request_param` text COMMENT '请求参数',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `exceptions` text COMMENT '异常',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `sys_user_id` (`sys_user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='操作日志';

/*Data for the table `sys_operation_log` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台角色',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `status` int(2) DEFAULT '0' COMMENT '{"name":"状态","0":"启用","1":"禁用"}',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `dels` int(2) DEFAULT '0' COMMENT '{"name":"是否删除","0":"正常","1":"删除"}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='后台角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (10,'超级管理员',0,NULL,'2019-01-21 17:44:50',1,'2019-02-19 15:11:26',NULL);
insert  into `sys_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (11,'测试普通角色',0,NULL,'2019-01-21 17:45:49',1,'2019-01-23 14:18:26',NULL);
insert  into `sys_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (12,'测试2',0,1,'2019-01-22 13:49:26',1,'2019-01-22 13:49:45',NULL);

/*Table structure for table `sys_role_auth` */

DROP TABLE IF EXISTS `sys_role_auth`;

CREATE TABLE `sys_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限',
  `sys_auth_id` int(11) DEFAULT NULL COMMENT '权限id',
  `sys_role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `sys_auth_id` (`sys_auth_id`),
  KEY `sys_role_id` (`sys_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=442 DEFAULT CHARSET=utf8 COMMENT='角色权限';

/*Data for the table `sys_role_auth` */

insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (200,6,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (201,7,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (202,28,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (203,29,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (350,1,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (351,11,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (352,15,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (353,16,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (354,22,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (355,17,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (356,18,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (357,19,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (358,20,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (359,21,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (360,32,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (361,33,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (362,34,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (363,35,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (364,36,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (365,37,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (366,2,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (367,3,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (368,89,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (369,273,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (370,274,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (371,275,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (372,276,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (373,277,11,'2019-03-12 16:33:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (374,1,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (375,11,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (376,9,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (377,65,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (378,66,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (379,67,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (380,68,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (381,15,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (382,16,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (383,22,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (384,17,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (385,18,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (386,19,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (387,20,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (388,21,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (389,32,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (390,33,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (391,34,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (392,35,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (393,36,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (394,37,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (395,48,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (396,2,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (397,40,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (398,41,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (399,42,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (400,43,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (401,44,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (402,45,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (403,3,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (404,89,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (405,273,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (406,274,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (407,275,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (408,276,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (409,277,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (410,90,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (411,308,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (412,309,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (413,310,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (414,311,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (415,312,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (416,313,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (417,314,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (418,315,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (419,316,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (420,317,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (421,91,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (422,318,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (423,319,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (424,320,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (425,321,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (426,322,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (427,92,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (428,328,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (429,329,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (430,330,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (431,331,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (432,332,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (433,333,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (434,334,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (435,335,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (436,336,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (437,337,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (438,4,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (439,7,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (440,46,10,'2019-03-12 16:34:48');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (441,47,10,'2019-03-12 16:34:48');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台管理员',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `icon` varchar(200) DEFAULT NULL COMMENT '头像',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `status` int(2) DEFAULT '0' COMMENT '{"name":"状态","0":"启用","1":"禁用"}',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` text COMMENT '备注信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后台管理员';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_name`,`icon`,`password`,`no`,`name`,`email`,`mobile`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remarks`) values (1,'ztp','2019/01/22/20190122165404test.png','96e79218965eb72c92a549dd5a330112','123','123','3213','21123',0,NULL,'2019-01-18 16:37:59',1,'2019-03-06 12:17:14','范菲菲21321322');
insert  into `sys_user`(`id`,`login_name`,`icon`,`password`,`no`,`name`,`email`,`mobile`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remarks`) values (2,'test1','2019/01/22/20190122165220QQ截图20190122151326.png','96e79218965eb72c92a549dd5a330112','test1','测试一','12323@qq.com','18502075220',0,NULL,'2019-01-21 17:46:46',1,'2019-02-19 15:13:33','测试用户一');
insert  into `sys_user`(`id`,`login_name`,`icon`,`password`,`no`,`name`,`email`,`mobile`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remarks`) values (3,'test2',NULL,'96e79218965eb72c92a549dd5a330112','test2','测试2','2312@qq.com','18502075222',0,1,'2019-01-22 13:50:41',1,'2019-03-12 16:34:56','1');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色',
  `sys_user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `sys_role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `sys_user_id` (`sys_user_id`),
  KEY `sys_role_id` (`sys_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (31,2,11,'2019-01-23 14:30:24');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (49,1,10,'2019-03-06 12:17:44');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (50,1,11,'2019-03-06 12:17:44');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (51,1,12,'2019-03-06 12:17:44');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (52,3,10,'2019-03-12 16:34:56');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (53,3,11,'2019-03-12 16:34:56');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (54,3,12,'2019-03-12 16:34:56');

/*Table structure for table `test_many_to_many_role` */

DROP TABLE IF EXISTS `test_many_to_many_role`;

CREATE TABLE `test_many_to_many_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `status` int(2) DEFAULT '0' COMMENT '{"name":"状态","0":"启用","1":"禁用"}',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `dels` int(2) DEFAULT '0' COMMENT '{"name":"是否删除","0":"正常","1":"删除"}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='多对多角色';

/*Data for the table `test_many_to_many_role` */

insert  into `test_many_to_many_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (11,'多对多角色1',0,213,'2019-03-12 16:14:19',123,'2019-03-22 00:00:00',0);
insert  into `test_many_to_many_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (12,'多对多角色2',0,213,'2019-03-12 16:14:19',123,'2019-03-22 00:00:00',0);
insert  into `test_many_to_many_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (13,'多对多角色3',0,213,'2019-03-12 16:14:19',123,'2019-03-22 00:00:00',0);

/*Table structure for table `test_many_to_many_user` */

DROP TABLE IF EXISTS `test_many_to_many_user`;

CREATE TABLE `test_many_to_many_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(2) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","0":"禁用"}',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remarks` text COMMENT '备注',
  `mun` decimal(20,2) DEFAULT NULL COMMENT '数字',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='多对多用户';

/*Data for the table `test_many_to_many_user` */

insert  into `test_many_to_many_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (40,'多对多用户1',2,1,'2019-03-12','213',213.00,'2019-03-12 16:13:59','2019-03-28 00:00:00');
insert  into `test_many_to_many_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (41,'多对多用户2',2,1,'2019-03-12','213',213.00,'2019-03-12 16:13:59','2019-03-28 00:00:00');
insert  into `test_many_to_many_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (42,'多对多用户3',2,1,'2019-03-12','213',213.00,'2019-03-12 16:13:59','2019-03-28 00:00:00');

/*Table structure for table `test_many_to_many_user_role` */

DROP TABLE IF EXISTS `test_many_to_many_user_role`;

CREATE TABLE `test_many_to_many_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '测试用户角色',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='多对多用户角色';

/*Data for the table `test_many_to_many_user_role` */

insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (61,36,5,'2019-03-11 17:22:08');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (109,36,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (110,26,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (111,25,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (112,24,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (113,23,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (114,22,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (115,21,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (116,20,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (117,19,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (118,18,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (119,17,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (120,16,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (121,15,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (122,14,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (123,13,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (124,12,10,'2019-03-12 14:42:29');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (125,39,10,'2019-03-12 14:43:52');
insert  into `test_many_to_many_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (126,39,5,'2019-03-12 14:43:52');

/*Table structure for table `test_one_to_many_addr` */

DROP TABLE IF EXISTS `test_one_to_many_addr`;

CREATE TABLE `test_one_to_many_addr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户收货地址id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `addr_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `addr` varchar(30) DEFAULT NULL COMMENT '收货地址',
  `status` int(11) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","2":"删除"}',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='一对多收货地址';

/*Data for the table `test_one_to_many_addr` */

insert  into `test_one_to_many_addr`(`id`,`user_id`,`addr_name`,`phone`,`addr`,`status`,`create_time`,`update_time`) values (6,43,'收货地址3','1111','违法2',1,'2019-03-12 00:00:00','2019-03-29 00:00:00');
insert  into `test_one_to_many_addr`(`id`,`user_id`,`addr_name`,`phone`,`addr`,`status`,`create_time`,`update_time`) values (7,43,'收货地址4','1111','违法2',1,'2019-03-12 00:00:00','2019-03-29 00:00:00');

/*Table structure for table `test_one_to_many_user` */

DROP TABLE IF EXISTS `test_one_to_many_user`;

CREATE TABLE `test_one_to_many_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(2) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","0":"禁用"}',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remarks` text COMMENT '备注',
  `mun` decimal(20,2) DEFAULT NULL COMMENT '数字',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='一对多用户';

/*Data for the table `test_one_to_many_user` */

insert  into `test_one_to_many_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (41,'一对多用户2',1,1,'2019-03-12','收发文',23.00,'2019-03-12 16:10:52','2019-03-29 00:00:00');
insert  into `test_one_to_many_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (43,'一对多用户4',1,1,'2019-03-12','收发文',23.00,'2019-03-12 16:10:52','2019-03-29 00:00:00');

/*Table structure for table `test_one_to_one_class` */

DROP TABLE IF EXISTS `test_one_to_one_class`;

CREATE TABLE `test_one_to_one_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `class_name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `quantity` int(11) DEFAULT NULL COMMENT '班级人数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='一对一班级';

/*Data for the table `test_one_to_one_class` */

insert  into `test_one_to_one_class`(`id`,`user_id`,`class_name`,`quantity`,`create_time`) values (10,44,'一对一班级1',1,'2019-03-12 15:56:09');
insert  into `test_one_to_one_class`(`id`,`user_id`,`class_name`,`quantity`,`create_time`) values (11,44,'一对一班级2',2,'2019-03-12 15:56:25');

/*Table structure for table `test_one_to_one_user` */

DROP TABLE IF EXISTS `test_one_to_one_user`;

CREATE TABLE `test_one_to_one_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(2) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","0":"禁用"}',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remarks` text COMMENT '备注',
  `mun` decimal(20,2) DEFAULT NULL COMMENT '数字',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='一对一用户';

/*Data for the table `test_one_to_one_user` */

insert  into `test_one_to_one_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (40,'一对一用户1',1,1,'2019-03-12','第三方玩儿',213.00,'2019-03-12 15:53:54','2019-03-27 00:00:00');
insert  into `test_one_to_one_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (41,'一对一用户2',1,1,'2019-03-12','第三方玩儿',213.00,'2019-03-12 15:53:54','2019-03-27 00:00:00');
insert  into `test_one_to_one_user`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`mun`,`create_time`,`update_time`) values (43,'一对一用户4',1,1,'2019-03-12','第三方玩儿',213.00,'2019-03-12 15:53:54','2019-03-27 00:00:00');

/*Table structure for table `test_simple_code` */

DROP TABLE IF EXISTS `test_simple_code`;

CREATE TABLE `test_simple_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(2) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","0":"禁用"}',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remarks` text COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='单表代码生成';

/*Data for the table `test_simple_code` */

insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (4,'单表测试1',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (5,'单表测试2',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (6,'单表测试3',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (7,'单表测试4',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (8,'单表测试5',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (9,'单表测试6',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (10,'单表测试7',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (11,'单表测试8',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (12,'单表测试9',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (13,'单表测试10',1,0,'2019-03-12','213','2019-03-12 15:51:52');
insert  into `test_simple_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (14,'单表测试11',1,0,'2019-03-12','213','2019-03-12 15:51:52');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
