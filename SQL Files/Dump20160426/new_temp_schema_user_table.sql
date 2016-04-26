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
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table` (
  `email` varchar(255) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `isCreatedOn` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `queueName` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `personId` bigint(20) NOT NULL,
  `profilePictureName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personId`),
  CONSTRAINT `FK_p7l32w75l0pt3ouela1hkb8bu` FOREIGN KEY (`personId`) REFERENCES `person_table` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES ('sanket.bhavsar7@gmail.com','','2016-04-19 18:01:12','sanket007','sanket007Sanket','sanket007',1,NULL),('aapeshave@gmail.com','','2016-04-19 18:02:14','admin123','aapeshaveAjinkya','aapeshave',2,'/resources/images/IMG_0617.jpg'),('paris130793@gmail.com','','2016-04-19 21:21:32','parisa.93','parisa.93Parisa','parisa.93',4,'/resources/images/IMG_0822.jpg'),('yeragi.a@husky.neu.edu','\0','2016-04-20 21:24:24','1234','apoorva.aApoorva','apoorva.a',5,NULL),('aloni.s@husky.neu.edu','','2016-04-23 20:26:15','sam11','sampada.aloniSampada','sampada.aloni',12,'/resources/images/1461457575727IMG_0433.jpg'),('apeshave@gmail.com','','2016-04-24 03:21:34','admin123','apeshaveAditya','apeshave',13,'/resources/images/1461482494775IMG_4396.jpg'),('bhagwat.s@gmail.com','','2016-04-24 03:28:22','sbhagwat','sbhagwat3333Suvedh','sbhagwat3333',14,'/resources/images/146148290252401smith1.jpg'),('wandrekar.s@gmail.com','','2016-04-24 18:38:49','admin','sWandrekarSwapnil','sWandrekar',15,'/resources/images/1461537529445IMG_4463.jpg'),('deo.s@husky.neu.edu','','2016-04-25 15:59:11','sumit','deosSumit','deos',16,'/resources/images/FullSizeRender.jpg');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-26  5:59:15
