/*
SQLyog  v12.2.6 (64 bit)
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
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COMMENT='图片管理';

/*Data for the table `file_images` */

LOCK TABLES `file_images` WRITE;

insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (82,'打的打的','2019/01/22/20190122165404test.png','2019-01-22 16:54:05',1);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (83,'打的打的','2019/01/22/20190122165404test.png','2019-01-22 16:54:05',1);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (84,'打的打的','2019/01/22/20190122165404test.png','2019-01-22 16:54:05',1);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (85,'打的打的','2019/01/22/20190122165404test.png','2019-01-22 16:54:05',1);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (86,'测试中','2019/01/22/20190122165220QQ截图20190122151326.png','2019-01-22 16:22:43',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (87,'test3','2019/01/22/20190122162303QQ截图20190122151326.png','2019-01-22 16:23:05',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (88,'test3','2019/01/22/20190122162303QQ截图20190122151326.png','2019-01-22 16:23:05',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (89,'test3','2019/01/22/20190122162303QQ截图20190122151326.png','2019-01-22 16:23:05',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (90,'测试中','2019/01/22/20190122165220QQ截图20190122151326.png','2019-01-22 16:22:43',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (91,'测试中','2019/01/22/20190122165220QQ截图20190122151326.png','2019-01-22 16:22:43',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (92,'测试中','2019/01/22/20190122165220QQ截图20190122151326.png','2019-01-22 16:22:43',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (93,'test3','2019/01/22/20190122162303QQ截图20190122151326.png','2019-01-22 16:23:05',NULL);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (94,'打的打的','2019/01/22/20190122165404test.png','2019-01-22 16:54:05',1);
insert  into `file_images`(`id`,`name`,`image_url`,`create_time`,`create_user_id`) values (95,'打的打的','2019/01/22/20190122165404test.png','2019-01-22 16:54:05',1);

UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='后台权限';

/*Data for the table `sys_auth` */

LOCK TABLES `sys_auth` WRITE;

insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (1,0,NULL,'系统设置',1,'','layui-icon layui-icon layui-icon layui-icon-password',0,1,'2019-01-17 15:46:30',1,'2019-01-22 13:43:27');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (2,0,NULL,'日志管理',2,'','layui-icon layui-icon-dialogue',0,1,'2019-01-23 15:01:12',1,'2019-01-23 18:18:46');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (3,0,NULL,'代码生成',3,'','layui-icon layui-icon-fonts-code',0,1,'2019-01-23 17:52:37',1,'2019-01-23 18:18:47');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (4,0,NULL,'报表管理',4,'','layui-icon layui-icon-chart',0,1,'2019-01-17 17:32:27',1,'2019-01-23 17:54:11');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (7,4,NULL,'折线图',1,'sysUserCharts/gotoLine','layui-icon layui-icon layui-icon layui-icon layui-icon-chart-screen',0,1,'2019-01-17 17:32:45',1,'2019-01-24 09:42:44');
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (9,11,NULL,'权限设置',1,'sysAuth/gotoList','layui-icon layui-icon layui-icon-auz',0,1,'2019-01-17 17:30:48',1,'2019-01-24 09:34:02');
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
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (61,60,NULL,'查询',1,'testCode/selectAll','layui-icon layui-icon-search',1,NULL,'2019-01-23 18:18:30',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (62,60,NULL,'导出',2,'testCode/export','layui-icon layui-icon-next',1,NULL,'2019-01-23 18:18:30',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (63,60,NULL,'编辑',3,'testCode/save','layui-icon layui-icon-edit',1,NULL,'2019-01-23 18:18:30',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (64,60,NULL,'删除',4,'testCode/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-01-23 18:18:30',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (65,9,NULL,'查询',1,'sysAuth/selectAll','layui-icon layui-icon-search',1,1,'2019-01-24 09:39:22',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (66,9,NULL,'删除',2,'sysAuth/selectByPrimaryKey','layui-icon layui-icon-fonts-del',1,1,'2019-01-24 09:39:48',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (67,9,NULL,'编辑',3,'sysAuth/save','layui-icon layui-icon-edit',1,1,'2019-01-24 09:40:27',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (68,9,NULL,'导出',4,'sysAuth/export','layui-icon layui-icon-next',1,1,'2019-01-24 09:41:00',1,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (69,3,NULL,'测试生成代码',1,'testCode/gotoList','layui-icon layui-icon-file-b',0,NULL,'2019-01-24 10:05:05',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (70,69,NULL,'查询',1,'testCode/selectAll','layui-icon layui-icon-search',1,NULL,'2019-01-24 10:05:05',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (71,69,NULL,'导出',2,'testCode/export','layui-icon layui-icon-next',1,NULL,'2019-01-24 10:05:05',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (72,69,NULL,'编辑',3,'testCode/save','layui-icon layui-icon-edit',1,NULL,'2019-01-24 10:05:05',NULL,NULL);
insert  into `sys_auth`(`id`,`parent_auth_id`,`parent_auth_ids`,`name`,`sort`,`href`,`icon`,`shows`,`create_user_id`,`create_time`,`update_user_id`,`update_time`) values (73,69,NULL,'删除',4,'testCode/deleteByPrimaryKey','layui-icon layui-icon-fonts-del',1,NULL,'2019-01-24 10:05:05',NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(也是登录时间)',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `browser` varchar(200) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';

/*Data for the table `sys_login_log` */

LOCK TABLES `sys_login_log` WRITE;

UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

/*Data for the table `sys_operation_log` */

LOCK TABLES `sys_operation_log` WRITE;

UNLOCK TABLES;

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

LOCK TABLES `sys_role` WRITE;

insert  into `sys_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (10,'超级管理员',0,NULL,'2019-01-21 17:44:50',1,'2019-01-22 13:44:22',NULL);
insert  into `sys_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (11,'测试普通角色',0,NULL,'2019-01-21 17:45:49',1,'2019-01-23 14:18:26',NULL);
insert  into `sys_role`(`id`,`name`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`dels`) values (12,'测试2',0,1,'2019-01-22 13:49:26',1,'2019-01-22 13:49:45',NULL);

UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8 COMMENT='角色权限';

/*Data for the table `sys_role_auth` */

LOCK TABLES `sys_role_auth` WRITE;

insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (181,1,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (182,2,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (183,3,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (184,5,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (185,14,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (186,23,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (187,4,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (188,15,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (189,16,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (190,22,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (191,17,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (192,18,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (193,19,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (194,20,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (195,21,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (196,6,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (197,7,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (198,28,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (199,29,10,'2019-01-22 13:48:57');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (200,6,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (201,7,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (202,28,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (203,29,12,'2019-01-22 13:49:26');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (226,1,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (227,2,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (228,3,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (229,14,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (230,23,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (231,15,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (232,22,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (233,18,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (234,19,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (235,20,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (236,21,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (237,32,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (238,33,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (239,34,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (240,35,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (241,36,11,'2019-01-23 14:32:25');
insert  into `sys_role_auth`(`id`,`sys_auth_id`,`sys_role_id`,`create_time`) values (242,37,11,'2019-01-23 14:32:25');

UNLOCK TABLES;

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

LOCK TABLES `sys_user` WRITE;

insert  into `sys_user`(`id`,`login_name`,`icon`,`password`,`no`,`name`,`email`,`mobile`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remarks`) values (1,'ztp','2019/01/22/20190122165404test.png','96e79218965eb72c92a549dd5a330112','123','123','3213','21123',0,NULL,'2019-01-18 16:37:59',1,'2019-01-23 10:21:15','范菲菲213213');
insert  into `sys_user`(`id`,`login_name`,`icon`,`password`,`no`,`name`,`email`,`mobile`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remarks`) values (2,'test1','2019/01/22/20190122165220QQ截图20190122151326.png','96e79218965eb72c92a549dd5a330112','test1','测试一','12323@qq.com','18502075220',0,NULL,'2019-01-21 17:46:46',2,'2019-01-23 14:30:24','测试用户一');
insert  into `sys_user`(`id`,`login_name`,`icon`,`password`,`no`,`name`,`email`,`mobile`,`status`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remarks`) values (3,'test2',NULL,'96e79218965eb72c92a549dd5a330112','test2','测试2','2312@qq.com','18502075222',1,1,'2019-01-22 13:50:41',1,'2019-01-22 13:50:52','1');

UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*Data for the table `sys_user_role` */

LOCK TABLES `sys_user_role` WRITE;

insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (24,3,12,'2019-01-22 13:50:41');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (29,1,10,'2019-01-23 10:21:15');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (30,1,11,'2019-01-23 10:21:15');
insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`,`create_time`) values (31,2,11,'2019-01-23 14:30:24');

UNLOCK TABLES;

/*Table structure for table `test_code` */

DROP TABLE IF EXISTS `test_code`;

CREATE TABLE `test_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '测试生成代码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(2) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","0":"禁用"}',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remarks` text COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='测试生成代码';

/*Data for the table `test_code` */

LOCK TABLES `test_code` WRITE;

insert  into `test_code`(`id`,`name`,`age`,`status`,`birthday`,`remarks`,`create_time`) values (2,'而案发',12,1,'2019-01-24','的分玩法','2019-01-24 10:07:06');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
