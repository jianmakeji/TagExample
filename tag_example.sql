# Host: 192.168.64.128  (Version 5.6.36-debug)
# Date: 2017-09-24 14:06:27
# Generator: MySQL-Front 6.0  (Build 1.176)


#
# Structure for table "article"
#

CREATE TABLE `article` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `article_name` varchar(100) NOT NULL DEFAULT '',
  `article_abstract` varchar(255) NOT NULL DEFAULT '',
  `article_content` text,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "article"
#


#
# Structure for table "tag_category"
#

CREATE TABLE `tag_category` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tag_category"
#


#
# Structure for table "tag"
#

CREATE TABLE `tag` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_category_id` int(11) NOT NULL DEFAULT '0',
  `tag_name` varchar(20) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `category_relation` (`tag_category_id`),
  CONSTRAINT `category_relation` FOREIGN KEY (`tag_category_id`) REFERENCES `tag_category` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tag"
#


#
# Structure for table "article_tag"
#

CREATE TABLE `article_tag` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) NOT NULL DEFAULT '0',
  `tagId` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `article_relation` (`articleId`),
  KEY `tag_relation` (`tagId`),
  CONSTRAINT `article_relation` FOREIGN KEY (`articleId`) REFERENCES `article` (`Id`),
  CONSTRAINT `tag_relation` FOREIGN KEY (`tagId`) REFERENCES `tag` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "article_tag"
#

