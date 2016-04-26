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
-- Table structure for table `chat_message_table`
--

DROP TABLE IF EXISTS `chat_message_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_message_table` (
  `messageID` bigint(20) NOT NULL AUTO_INCREMENT,
  `contentType` varchar(255) DEFAULT NULL,
  `isArchived` bit(1) DEFAULT NULL,
  `isRead` bit(1) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `chatSession_chatSessionID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`messageID`),
  KEY `FK_gyhjthe8fi3dxiancyfwlwcta` (`chatSession_chatSessionID`),
  CONSTRAINT `FK_gyhjthe8fi3dxiancyfwlwcta` FOREIGN KEY (`chatSession_chatSessionID`) REFERENCES `chatSession_table` (`chatSessionID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message_table`
--

LOCK TABLES `chat_message_table` WRITE;
/*!40000 ALTER TABLE `chat_message_table` DISABLE KEYS */;
INSERT INTO `chat_message_table` VALUES (1,'String','\0',NULL,'/aapeshave;hi daddu','2016-04-25 18:18:40',4),(2,'String','\0',NULL,'/sanket007;kasay be?','2016-04-25 18:19:24',4),(3,'String','\0',NULL,'/aapeshave;chan ahe!','2016-04-25 18:19:41',4),(4,'String','\0',NULL,'/sanket007;zala ka chat?','2016-04-25 18:19:59',4),(5,'String','\0',NULL,'/aapeshave;nahi','2016-04-25 18:20:21',4),(6,'String','\0',NULL,'/sanket007;barr','2016-04-25 18:20:44',4),(24,'String','\0',NULL,'/parisa.93;hiii','2016-04-26 01:56:47',10),(25,'String','\0',NULL,'/aapeshave;hiiiiii','2016-04-26 01:57:21',10),(26,'String','\0',NULL,'/parisa.93;loooll','2016-04-26 01:58:09',10);
/*!40000 ALTER TABLE `chat_message_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-26  5:59:14
