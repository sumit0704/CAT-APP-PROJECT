-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: catapp
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `file_info`
--

DROP TABLE IF EXISTS `file_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_info` (
  `File_name` varchar(200) DEFAULT NULL,
  `File_path` varchar(300) DEFAULT NULL,
  `Cell_line` varchar(50) DEFAULT NULL,
  `Phenotype` varchar(100) DEFAULT NULL,
  `File_type` varchar(100) DEFAULT NULL,
  `File_owner_ID` varchar(20) DEFAULT NULL,
  `Supervisor_owner_ID` varchar(20) DEFAULT NULL,
  `Row_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Row_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_info`
--

LOCK TABLES `file_info` WRITE;
/*!40000 ALTER TABLE `file_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `User_ID` varchar(20) DEFAULT NULL,
  `First_Name` varchar(20) DEFAULT NULL,
  `Last_Name` varchar(20) DEFAULT NULL,
  `Institution` varchar(100) DEFAULT NULL,
  `Phone_Number` varchar(40) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Supervisor_ID` varchar(20) DEFAULT NULL,
  `Supervisor_First_Name` varchar(20) DEFAULT NULL,
  `Supervisor_Last_Name` varchar(20) DEFAULT NULL,
  `Supervisor_Phone_Number` varchar(40) DEFAULT NULL,
  `Supervisor_Email` varchar(50) DEFAULT NULL,
  `Last_login_time` datetime DEFAULT NULL,
  `Admin_or_not` varchar(3) DEFAULT NULL,
  `Row_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Row_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('test-user-1','User-1','test','TAMU','979-123-4567','test_user1@test.com','Supervisor-1','Supervisor-1','Boss-1','979-123-super','Supervisor_Email@test.com',NULL,'no',1,'test-user-1'),('test-user-2','User-2','test','TAMU','979-123-4567','test_user2@test.com','Supervisor-1','Supervisor-1','Boss-1','979-123-super','Supervisor_Email@test.com',NULL,'no',2,'test-user-2'),('test-user-3','User-3','test','TAMU','979-123-4567','test_user2@test.com','supervisor-2','Supervisor-1','Boss-1','979-123-super','Supervisor_Email@test.com',NULL,'no',3,'test-user-3'),('supervisor-1','Supervisor-1','Boss-1','TAMU','979-123-4567','supervisor-1@test.com','Supervisor-1','Supervisor-1','Boss-1','979-123-super','Supervisor_Email@test.com',NULL,'no',4,'supervisor-1'),('supervisor-2','Supervisor-2','Boss-2','TAMU','979-123-4567','supervisor-2@test.com','Supervisor-2','Supervisor-2','Boss-2','979-123-super','Supervisor2_Email@test.com',NULL,'no',5,'supervisor-2'),('admin','admin','admin','TAMU','979-123-4567','admin@test.com','admin','admin','admin','979-123-admin','admin_Email@test.com',NULL,'yes',6,'admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-24 12:39:49
