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
  PRIMARY KEY (`mid`),
  KEY `patientId` (`patient_id`),
  KEY `doctorId` (`doctor_id`),
  CONSTRAINT `tmapping_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `tpatient` (`patient_id`),
  CONSTRAINT `tmapping_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `tdoctor` (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tmapping` */

insert  into `tmapping`(`mid`,`patient_id`,`doctor_id`) values 
(1,1,1),
(2,2,1),
(3,2,2);

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
  `desc` text DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `priority` enum('HIGH','MIDDLE','LOW') DEFAULT NULL,
  `status` tinyint(1) DEFAULT 0 COMMENT '0=unread 1=read',
  PRIMARY KEY (`rid`),
  KEY `dpid` (`mid`),
  CONSTRAINT `treminder_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `tmapping` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `treminder` */

insert  into `treminder`(`rid`,`mid`,`desc`,`created_time`,`priority`,`status`) values 
(1,1,'Test reminder gan','2020-07-15 13:14:56','MIDDLE',0),
(2,2,'Dokter 1 reminder','2020-07-15 14:03:26','HIGH',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
