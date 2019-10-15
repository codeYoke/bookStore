# Host: localhost  (Version: 5.5.27)
# Date: 2018-05-02 17:01:03
# Generator: MySQL-Front 5.3  (Build 4.9)

/*!40101 SET NAMES utf8 */;

#
# Source for table "books"
#
create database books;
use books;

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `BID` int(11) NOT NULL DEFAULT '0' COMMENT '主键',

	`BOOKNAME` varchar(50) DEFAULT NULL,
  
	`B_PRICE` varchar(10) DEFAULT NULL,
  
	`IMAGE` varchar(100) DEFAULT NULL,
  
	`STOCK` int(11) DEFAULT NULL COMMENT '库存数',
  
	PRIMARY KEY (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "books"
#

INSERT INTO `books` VALUES (1,'php','35.00','image/book/book_02.gif',88),
	(2,'C++','36.00','image/book/book_03.gif',31),
	(3,'java','55.00','image/book/book_01.gif',32),
	(4,'python','80.00','image/book/book_04.gif',79),
	(5,'NET','28.00','image/book/book_05.gif',12),
	(6,'数据结构','55.50','image/book/book_06.gif',66),
	(7,'数据库','34.00','image/book/book_07.gif',34),
	(8,'疯狂Java','45.00','image/book/book_08.gif',22),
	(9,'java web','57.00','image/book/book_09.gif',24),
	(10,'tensorflow','99.9','image/book/book_10.gif',33),
	(11,'java 面向对象','54.00','image/book/book_02.gif',68);

#
# Source for table "items"
#

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `IID` int(11) NOT NULL DEFAULT '0',
  
	`OID` int(11) DEFAULT NULL,
  `BID` int(11) DEFAULT NULL,
  
	`CREATEDATE` datetime DEFAULT NULL,
  
	`COUNT` int(11) DEFAULT NULL COMMENT '购买数量',
  
	`PRICE` double DEFAULT NULL COMMENT '单价',
  
	`STATE` int(11) DEFAULT NULL,
  
	`TOTAL_PRICE` double DEFAULT NULL COMMENT '总价',
  
	PRIMARY KEY (`IID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "items"
#

#INSERT INTO `items` VALUES (1,1,2,'2018-07-02 09:16:15',1,36,0,36),(2,2,2,'2018-07-02 09:16:25',1,36,0,36),(3,2,3,'2018-07-02 09:16:25',1,55,0,55),(4,3,2,'2018-07-02 10:21:14',2,36,0,72),(5,4,2,'2018-07-02 11:09:22',3,36,0,108),(6,4,3,'2018-07-02 11:09:22',1,55,0,55),(7,5,2,'2018-07-02 11:09:39',1,36,0,36),(8,6,2,'2018-07-02 16:01:36',1,36,0,36),(9,6,9,'2018-07-02 16:01:36',34,57,0,1938);

#
# Source for table "orders"
#

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `OID` int(11) DEFAULT NULL,
  
	`USERNAME` varchar(50) DEFAULT NULL,
  
	KEY `OID` (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "orders"
#

#INSERT INTO `orders` VALUES (1,'1'),(2,'1'),(3,'2'),(4,'1'),(5,'1'),(6,'1');

#
# Source for table "userinfo"
#

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `username` varchar(50) NOT NULL DEFAULT '',
  
	`password` varchar(50) DEFAULT NULL,
  
	`email` varchar(50) DEFAULT NULL,
  
	PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "userinfo"
#

#INSERT INTO `userinfo` VALUES ('','',''),('1','1','1@qq.com'),('2','12345678','123@qq.com'),('3','12345678','123@qq.com'),('4','12345678','123@qq.com'),('5','123','123@qq.com'),('6','12345678','123@qq.com'),('7','12345678','123@qq.com'),('å','12345678','123@qq.com'),('ä¸','12345678','123@qq.com'),('è¥¿','12345678','123@qq.com'),('kpit','123456','123@qq.com'),('南','12345678','123@qq.com');
