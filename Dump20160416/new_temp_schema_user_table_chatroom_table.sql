CREATE DATABASE  IF NOT EXISTS `new_temp_schema` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `new_temp_schema`;
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
-- Table structure for table `user_table_chatroom_table`
--

DROP TABLE IF EXISTS `user_table_chatroom_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table_chatroom_table` (
  `participants_personId` bigint(20) NOT NULL,
  `joinedChatRooms_chatRoomID` bigint(20) NOT NULL,
  KEY `FK_1xfjytf4ir0jk8kwfkygsaww` (`joinedChatRooms_chatRoomID`),
  KEY `FK_p1xkhssdoyhrjk7rmox8s04rp` (`participants_personId`),
  CONSTRAINT `FK_1xfjytf4ir0jk8kwfkygsaww` FOREIGN KEY (`joinedChatRooms_chatRoomID`) REFERENCES `chatroom_table` (`chatRoomID`),
  CONSTRAINT `FK_p1xkhssdoyhrjk7rmox8s04rp` FOREIGN KEY (`participants_personId`) REFERENCES `user_table` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table_chatroom_table`
--

LOCK TABLES `user_table_chatroom_table` WRITE;
/*!40000 ALTER TABLE `user_table_chatroom_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_table_chatroom_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-16  9:32:29
