CREATE DATABASE  IF NOT EXISTS `introse_mp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `introse_mp`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: introse_mp
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `receipts`
--

DROP TABLE IF EXISTS `receipts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipts` (
  `receiptID` int(11) NOT NULL AUTO_INCREMENT,
  `sold_price` double NOT NULL,
  `sold_quantity` int(11) NOT NULL,
  `sold_date` date NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `sold_branch` int(11) NOT NULL,
  `staffName` varchar(255) NOT NULL,
  `sold_ProductName` varchar(255) NOT NULL,
  `productID` int(11) NOT NULL,
  PRIMARY KEY (`receiptID`,`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipts`
--

LOCK TABLES `receipts` WRITE;
/*!40000 ALTER TABLE `receipts` DISABLE KEYS */;
INSERT INTO `receipts` VALUES (1,200,1,'2016-01-01','Jose',1,'wala1','Bench',1),(2,400,2,'2016-02-01','Alonzo',2,'wala2','Zara',2),(3,600,1,'2017-04-09','Teodoro',1,'wala3','Bench',1),(4,800,4,'2016-04-09','Rizal',2,'wala4','Zara',2),(5,200,1,'2016-04-09','mitlowp',1,'me','Bench',1),(6,2,1,'2016-04-10','2',1,'2','Bench',1),(7,2,2,'2016-05-10','2',2,'2','Bench',1),(8,50,1,'2016-04-13','wer',1,'wlaa','Bench',1),(9,2,12,'2016-04-13','wala',2,'wala','Zara',1),(10,1,18,'2016-04-15','1',1,'1','Bench',1),(11,2,1,'2016-04-15','1',1,'w1aa','Bench',1),(12,2,1,'2016-04-15','2',1,'1','Zara',2),(13,2,1,'2016-04-15','me',1,'wala','Bench',1);
/*!40000 ALTER TABLE `receipts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-16 18:24:57
