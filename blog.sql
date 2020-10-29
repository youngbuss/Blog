/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.21 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `blog`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `picture` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `views` int DEFAULT NULL,
  `type` bigint DEFAULT NULL,
  `user` bigint DEFAULT NULL,
  `tagids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpk1w92t3gjjrk7wich8n3urp9` (`type`),
  KEY `FKpxk2jtysqn41oop7lvxcp6dqq` (`user`),
  CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1321398794499657731 DEFAULT CHARSET=utf8;

/*Data for the table `blog` */

insert  into `blog`(`id`,`appreciation`,`commentabled`,`content`,`create_time`,`description`,`picture`,`flag`,`published`,`recommend`,`share`,`title`,`update_time`,`views`,`type`,`user`,`tagids`) values (2,'\0','','**Enjoy Pokemon**','2020-10-27 07:36:40.459000','宝可梦小知识','https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1122115076,3810357403&fm=26&gp=0.jpg','原创','','','\0','宝可梦剑盾','2020-10-27 13:14:00.658000',100,1320989541762928641,1,'5'),(1320993889284907009,'','','**皇家马德里**','2020-10-27 07:41:00.744000','Hala Madird','https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1122115076,3810357403&fm=26&gp=0.jpg','原创','','','\0','皇马','2020-10-26 13:31:38.000000',100,1,1,'6'),(1321004955045822466,'\0','\0','demo','2020-10-27 08:24:59.026000','demo','https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1122115076,3810357403&fm=26&gp=0.jpg','原创','','\0','\0','demo','2020-10-25 08:24:59.000000',100,1,1,'1,2,3'),(1321009140441128961,'','','11111','2020-10-27 08:41:36.902000','1111','https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1122115076,3810357403&fm=26&gp=0.jpg','原创','','','\0','Demo','2020-10-24 08:41:36.000000',100,1,1,'2'),(1321398794499657730,'\0','\0','##Java\r\n#### SpringBoot\r\n记录下学习的过程','2020-10-28 10:29:57.670000','Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2356203703,1335541673&fm=26&gp=0.jpg','原创','','','\0','Java','2020-10-28 10:29:57.670000',NULL,3,1,'1');

/*Table structure for table `blog_tag` */

DROP TABLE IF EXISTS `blog_tag`;

CREATE TABLE `blog_tag` (
  `blogid` bigint NOT NULL,
  `tagid` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `blog_tag` */

insert  into `blog_tag`(`blogid`,`tagid`) values (2,5),(1320993889284907009,6),(1321004955045822466,1),(1321004955045822466,2),(1321004955045822466,3),(1321009140441128961,2);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint NOT NULL,
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `blogid` bigint DEFAULT NULL,
  `parentcommentid` bigint DEFAULT NULL,
  `admincomment` bit(1) DEFAULT NULL,
  `rootid` bigint DEFAULT NULL,
  `parentcommentname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkap39f76wn135k7ru564fbjb7` (`blogid`),
  KEY `FKhvh0e2ybgg16bpu229a5teje7` (`parentcommentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`avatar`,`content`,`create_time`,`email`,`nickname`,`blogid`,`parentcommentid`,`admincomment`,`rootid`,`parentcommentname`) values (1321452788123234306,'https://images.unsplash.com/photo-1603538122780-22c3d05496fa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1355&q=80','测试','2020-10-28 14:04:30.752000','SG@163.com','BuBu',2,-1,'',-1,NULL),(1321462710512762882,'https://images.unsplash.com/photo-1603688042009-67d3ecaa3bca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80','测试数据','2020-10-28 14:43:56.434000','Jerry@163.com','yyy',2,-1,'\0',-1,NULL),(1321465534608920577,'https://images.unsplash.com/photo-1593006061635-58c1f86a0da2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1355&q=80','测试子评论','2020-10-28 14:55:09.751000','SG@163.com','bu',2,1321462710512762882,'\0',1321462710512762882,'yyy'),(1321472953527422977,'https://images.unsplash.com/photo-1603538122780-22c3d05496fa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1355&q=80','kkkkkkk','2020-10-28 15:24:38.565000','SG@163.com','yy',2,1321465534608920577,'\0',1321462710512762882,'bu'),(1321476890200502273,'https://images.unsplash.com/photo-1603688042009-67d3ecaa3bca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80','剑盾真的好玩啊推荐！','2020-10-28 15:40:17.135000','HH@163.com','hh',2,-1,'\0',-1,NULL),(1321649430340399106,'https://images.unsplash.com/photo-1603688042009-67d3ecaa3bca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80','真好玩啊','2020-10-29 03:05:53.910000','GGB@163.com','ggb',2,1321472953527422977,'\0',1321462710512762882,'yy'),(1321650013969432578,'https://images.unsplash.com/photo-1603688042009-67d3ecaa3bca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80','推荐','2020-10-29 03:08:13.058000','hrb@126.com','hrb',2,1321472953527422977,'\0',1321462710512762882,'yy');

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (1),(1),(1),(1),(1);

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

insert  into `tag`(`id`,`name`) values (1,'Java'),(2,'Python'),(3,'曼联'),(4,'塞尔达'),(5,'宝可梦'),(6,'皇马'),(1320987485706682369,'北邮'),(1320988785857105921,'漳州');

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `type` */

insert  into `type`(`id`,`name`) values (1,'足球'),(2,'娱乐'),(3,'学术'),(5,'Pokemon'),(1320989541762928641,'任天堂');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `create_time` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`avatar`,`create_time`,`email`,`nickname`,`password`,`type`,`update_time`,`username`) values (1,'https://images.unsplash.com/photo-1603538122780-22c3d05496fa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1355&q=80','2020-10-26 16:34:29.000000','bubu@163.com','BuBu','e10adc3949ba59abbe56e057f20f883e',1,'2020-10-26 16:35:29.000000','bubu');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
