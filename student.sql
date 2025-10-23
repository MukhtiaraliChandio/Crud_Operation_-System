/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.1.21-MariaDB : Database - crud_desktop_java
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crud_desktop_java` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `crud_desktop_java`;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `std_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `cnic` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `hobbies` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`std_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`std_id`,`first_name`,`last_name`,`father_name`,`date_of_birth`,`surname`,`cnic`,`contact_number`,`email_id`,`gender`,`hobbies`,`province`,`country`,`address`) values (1,'Ali','Raza','Ghullam','4-2-2005','Buriro','4130660574701','03091135133','aliraza@gmail.com','Male','[Reading, Writing, Music]','sindh','pakistan','Railway Colony'),(2,'Hujaj','Ali','Taj Muhammad','1-5-1996','Qambrani','4130660574701','03091135121','hujio12@gmail.com','Male','[Reading, painting, Music]','punjab','london','Islambad'),(7,'Bisma','Ali','Ashique','1-5-2008','Chandio','4130660574701','030911351221','bisma1122@gmail.com','Female','[Reading, painting]','Sindh','Pakistan','Qasimabad Hyd'),(8,'Shahida','Ali','Altaf','2-5-2001','Chandio','41306-6057470-3','03091135123','shahidaali@gmail.com','Female','[Reading, Writing, painting]','Sindh','Pakistan','v.good'),(9,'Shahida','Ali','Altaf','2-5-2001','Chandio','41306-6057470-3','03091135123','shahidaali@gmail.com','Female','[Reading, Writing, painting]','Sindh','Pakistan','v.good');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
