-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: new_temp_schema
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `friend_table`
--

DROP TABLE IF EXISTS `friend_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_table` (
  `friendID` bigint(20) NOT NULL AUTO_INCREMENT,
  `isAccepted` bit(1) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`friendID`),
  KEY `FK_1uca3j0uln8sqptp1qf5ak4xl` (`personId`),
  CONSTRAINT `FK_1uca3j0uln8sqptp1qf5ak4xl` FOREIGN KEY (`personId`) REFERENCES `user_table` (`personId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_table`
--

LOCK TABLES `friend_table` WRITE;
/*!40000 ALTER TABLE `friend_table` DISABLE KEYS */;
INSERT INTO `friend_table` VALUES (7,'','Request Accepted',2),(8,'','Request Accepted',1),(9,'','Request Accepted',2),(10,'','Request Accepted',4),(12,'','Request Accepted',2),(14,'','Request Accepted',4),(15,'','Request Accepted',2),(16,'','Request Accepted',5),(17,'','Request Accepted',5),(18,'','Request Accepted',4),(19,'','Request Accepted',12),(20,'','Request Accepted',4),(21,'','Request Accepted',2),(22,'','Request Accepted',13),(23,'\0','New Pending Request',1),(24,'\0','New Pending Request',14);
/*!40000 ALTER TABLE `friend_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-24  6:09:19
