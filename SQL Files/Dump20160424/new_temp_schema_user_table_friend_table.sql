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
-- Table structure for table `user_table_friend_table`
--

DROP TABLE IF EXISTS `user_table_friend_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table_friend_table` (
  `userList_personId` bigint(20) NOT NULL,
  `friendList_friendID` bigint(20) NOT NULL,
  KEY `FK_t0fk0wk58300c9qd24rt5x8ao` (`friendList_friendID`),
  KEY `FK_ksipkcr6it0p8j7jpu9l6se2o` (`userList_personId`),
  CONSTRAINT `FK_ksipkcr6it0p8j7jpu9l6se2o` FOREIGN KEY (`userList_personId`) REFERENCES `user_table` (`personId`),
  CONSTRAINT `FK_t0fk0wk58300c9qd24rt5x8ao` FOREIGN KEY (`friendList_friendID`) REFERENCES `friend_table` (`friendID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table_friend_table`
--

LOCK TABLES `user_table_friend_table` WRITE;
/*!40000 ALTER TABLE `user_table_friend_table` DISABLE KEYS */;
INSERT INTO `user_table_friend_table` VALUES (5,15),(5,18),(4,9),(4,17),(4,19),(12,20),(13,21),(2,8),(2,10),(2,16),(2,22),(14,23),(1,7),(1,24);
/*!40000 ALTER TABLE `user_table_friend_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-24  6:09:20
