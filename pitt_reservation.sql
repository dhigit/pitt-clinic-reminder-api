/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.6-MariaDB : Database - reservation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`reservation` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `reservation`;

/*Table structure for table `tdoctor` */

DROP TABLE IF EXISTS `tdoctor`;

CREATE TABLE `tdoctor` (
  `doctor_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(200) NOT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tdoctor` */

insert  into `tdoctor`(`doctor_id`,`doctor_name`) values 
(1,'adi'),
(2,'budi'),
(3,'caca');

/*Table structure for table `tmapping` */

DROP TABLE IF EXISTS `tmapping`;

CREATE TABLE `tmapping` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) unsigned DEFAULT NULL,
  `doctor_id` int(11) unsigned DEFAULT NULL,
  `unfinished_high` int(11) DEFAULT 0,
  `unfinished_middle` int(11) DEFAULT 0,
  `unfinished_low` int(11) DEFAULT 0,
  PRIMARY KEY (`mid`),
  KEY `patientId` (`patient_id`),
  KEY `doctorId` (`doctor_id`),
  CONSTRAINT `tmapping_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `tpatient` (`patient_id`),
  CONSTRAINT `tmapping_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `tdoctor` (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tmapping` */

insert  into `tmapping`(`mid`,`patient_id`,`doctor_id`,`unfinished_high`,`unfinished_middle`,`unfinished_low`) values 
(1,1,1,0,0,1),
(2,1,1,0,0,0),
(3,2,2,0,0,0);

/*Table structure for table `tpatient` */

DROP TABLE IF EXISTS `tpatient`;

CREATE TABLE `tpatient` (
  `patient_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `patient_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tpatient` */

insert  into `tpatient`(`patient_id`,`patient_name`) values 
(1,'danu'),
(2,'eka'),
(3,'fajar');

/*Table structure for table `treminder` */

DROP TABLE IF EXISTS `treminder`;

CREATE TABLE `treminder` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mid` int(10) unsigned DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `desc` varchar(500) DEFAULT NULL,
  `duration` int(11) DEFAULT 0 COMMENT 'in hour',
  `created_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `priority` enum('HIGH','MIDDLE','LOW') DEFAULT NULL,
  `status` int(1) DEFAULT 0 COMMENT '0=unread 1=read',
  PRIMARY KEY (`rid`),
  KEY `dpid` (`mid`),
  CONSTRAINT `treminder_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `tmapping` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `treminder` */

insert  into `treminder`(`rid`,`mid`,`title`,`desc`,`duration`,`created_time`,`priority`,`status`) values 
(0,1,'Reminder 1','The JSON-Java library is also known as org.json (not to be confused with Google\'s org.json.simple) provides us with classes that are used to parse and manipulate JSON in Java.',1,'2020-07-15 14:03:26','MIDDLE',1),
(1,1,'Reminder 2','The JSON-Java library is also known as org.json (not to be confused with Google\'s org.json.simple) provides us with classes that are used to parse and manipulate JSON in Java.',0,'2020-07-15 13:14:56','LOW',0),
(4,2,'Reminder 3','The JSON-Java library is also known as org.json (not to be confused with Google\'s org.json.simple) provides us with classes that are used to parse and manipulate JSON in Java.',0,'2020-07-16 12:09:28','LOW',1);

/* Trigger structure for table `treminder` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trig_unfinished_count` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trig_unfinished_count` AFTER UPDATE ON `treminder` FOR EACH ROW BEGIN
	UPDATE `tmapping` a
	SET 
		a.`unfinished_high` 	= (SELECT COUNT(*) FROM `treminder` b WHERE b.status=0 AND b.priority='HIGH' AND b.`mid`=old.mid),
		a.`unfinished_middle` 	= (SELECT COUNT(*) FROM `treminder` b WHERE b.status=0 AND b.priority='MIDDLE' AND b.`mid`=old.mid),
		a.`unfinished_low` 	= (SELECT COUNT(*) FROM `treminder` b WHERE b.status=0 AND b.priority='LOW' AND b.`mid`=old.mid)
	WHERE
		`mid` = old.mid;
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
