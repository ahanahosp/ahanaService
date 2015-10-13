CREATE DATABASE  IF NOT EXISTS `ahana` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ahana`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ahana
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `floor` (
  `oid` varchar(20) NOT NULL,
  `floor_name` varchar(100) NOT NULL,
  `floor_code` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lookup`
--

DROP TABLE IF EXISTS `lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lookup` (
  `code` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `seq` bigint(20) DEFAULT '0',
  `deprecated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`code`,`category`),
  KEY `id_index` (`code`,`category`,`description`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lookup`
--

LOCK TABLES `lookup` WRITE;
/*!40000 ALTER TABLE `lookup` DISABLE KEYS */;
INSERT INTO `lookup` VALUES ('A+','BLOODGROUP','A+',1,0),('A-','BLOODGROUP','A-',2,0),('AB+','BLOODGROUP','AB+',5,0),('AB-','BLOODGROUP','AB-',6,0),('Andaman & Nicobar Islands','STATE_INDIA','Andaman & Nicobar Islands',29,0),('Andhra Pradesh','STATE_INDIA','Andhra Pradesh',2,0),('Arunachal Pradesh','STATE_INDIA','Arunachal Pradesh',3,0),('Assam','STATE_INDIA','Assam',4,0),('B+','BLOODGROUP','B+',3,0),('B-','BLOODGROUP','B-',4,0),('Bangladesh','COUNTRY','Bangladesh',3,0),('Bihar','STATE_INDIA','Bihar',5,0),('Chandigarh','STATE_INDIA','Chandigarh',30,0),('Chhattisgarh','STATE_INDIA','Chhattisgarh',6,0),('Dadra & Nagar Haveli','STATE_INDIA','Dadra & Nagar Haveli',31,0),('Daman & Diu','STATE_INDIA','Daman & Diu',32,0),('Delhi','STATE_INDIA','Delhi',33,0),('Dr','SALUTATION','Dr',3,0),('Free','CATEGORY','Free',2,0),('Goa','STATE_INDIA','Goa',7,0),('Gujarat','STATE_INDIA','Gujarat',8,0),('Haryana','STATE_INDIA','Haryana',9,0),('Himachal Pradesh','STATE_INDIA','Himachal Pradesh',10,0),('India','COUNTRY','India',1,0),('Indonesia','COUNTRY','Indonesia',4,0),('Jammu & kashmir','STATE_INDIA','Jammu & kashmir',11,0),('Jharkhand','STATE_INDIA','Jharkhand',12,0),('Karnataka','STATE_INDIA','Karnataka',13,0),('Kerala','STATE_INDIA','Kerala',14,0),('Lakshadweep','STATE_INDIA','Lakshadweep',34,0),('Madhya Pradesh','STATE_INDIA','Madhya Pradesh',15,0),('Maharashtra','STATE_INDIA','Maharashtra',16,0),('Malaysia','COUNTRY','Malaysia',2,0),('Manipur','STATE_INDIA','Manipur',17,0),('Meghalaya','STATE_INDIA','Meghalaya',18,0),('Miss','SALUTATION','Miss',2,0),('Mizoram','STATE_INDIA','Mizoram',19,0),('Mr','SALUTATION','Mr',1,0),('Ms','SALUTATION','Ms',4,0),('Nagaland','STATE_INDIA','Nagaland',20,0),('Nepal','COUNTRY','Nepal',8,0),('O+','BLOODGROUP','O+',7,0),('O-','BLOODGROUP','O-',8,0),('Orissa','STATE_INDIA','Orissa',21,0),('Pakistan','COUNTRY','Pakistan',5,0),('Philippines','COUNTRY','Philippines',11,0),('Priority','CATEGORY','Priority',3,0),('Priority-Free','CATEGORY','Priority-Free',4,0),('Prof','SALUTATION','Prof',5,0),('Puducherry','STATE_INDIA','Puducherry',35,0),('Punjab','STATE_INDIA','Punjab',22,0),('Rajasthan','STATE_INDIA','Rajasthan',23,0),('Rev','SALUTATION','Rev',6,0),('Sikkim','STATE_INDIA','Sikkim',24,0),('Singapore','COUNTRY','Singapore',6,0),('Sri Lanka','COUNTRY','Sri Lanka',7,0),('Standard-Default','CATEGORY','Standard-Default',1,0),('Tamil Nadu','STATE_INDIA','Tamil Nadu',1,0),('Thailand','COUNTRY','Thailand',9,0),('Tripura','STATE_INDIA','Tripura',25,0),('Uttar Pradesh','STATE_INDIA','Uttar Pradesh',26,0),('Uttarakhand','STATE_INDIA','Uttarakhand',27,0),('Vietnam','COUNTRY','Vietnam',10,0),('VIP','CATEGORY','VIP',5,0),('West Bengal','STATE_INDIA','West Bengal',28,0);
/*!40000 ALTER TABLE `lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `oid` varchar(20) NOT NULL,
  `organization_name` varchar(100) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `email_id` varchar(100) NOT NULL,
  `mobile_no` varchar(12) NOT NULL,
  `primary_contact` varchar(12) DEFAULT NULL,
  `secondary_contact` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES ('a0100000003000000011','Ahana Hospital','Madurai','India','Tamil Nadu','Madurai','9898798978','ahana@gmail.com','98988787898','483784738473','384738748734');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_module`
--

DROP TABLE IF EXISTS `organization_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_module` (
  `oid` varchar(20) NOT NULL,
  `module_name` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_module`
--

LOCK TABLES `organization_module` WRITE;
/*!40000 ALTER TABLE `organization_module` DISABLE KEYS */;
INSERT INTO `organization_module` VALUES ('a0100000003000000005','Configuratiion','ACT'),('a0100000003000000006','Billing','ACT'),('a0100000003000000007','Patients','ACT'),('a0100000003000000008','Patient Registration','ACT');
/*!40000 ALTER TABLE `organization_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `oid` varchar(20) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `gender` varchar(6) NOT NULL,
  `mobile` varchar(12) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `billing` varchar(50) DEFAULT NULL,
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('PAT10','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT11','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT12','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT13','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT14','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT15','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT16','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT17','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT18','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT19','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT20','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT21','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT22','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT23','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT24','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT25','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT26','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT27','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT28','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT29','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT3','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT30','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT31','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT32','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT33','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT34','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT35','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT36','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT37','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT38','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT39','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT4','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT40','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT41','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT42','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT43','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT44','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT45','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT46','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT47','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT48','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT49','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT5','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT50','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT51','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT52','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT53','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT54','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT55','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT56','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT57','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT58','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT59','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT6','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT60','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT61','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT62','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT63','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT64','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT65','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT7','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT8','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT9','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_rights`
--

DROP TABLE IF EXISTS `role_rights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_rights` (
  `oid` varchar(20) NOT NULL,
  `role_oid` varchar(20) NOT NULL,
  `module_oid` varchar(20) NOT NULL,
  `organization_oid` varchar(20) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_rights`
--

LOCK TABLES `role_rights` WRITE;
/*!40000 ALTER TABLE `role_rights` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_rights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `oid` varchar(20) NOT NULL,
  `role_name` varchar(30) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('a0100000004000000006','Admin','ACT'),('a0100000004000000007','User','ACT'),('a0100000004000000008','Super_Admin','ACT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `oid` varchar(20) NOT NULL,
  `bed_name` varchar(100) NOT NULL,
  `ward_oid` varchar(20) NOT NULL,
  `occupancy_status` varchar(20) NOT NULL DEFAULT 'Vaccunt',
  `maintenance_status` varchar(20) NOT NULL DEFAULT 'Not Available',
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seed_container`
--

DROP TABLE IF EXISTS `seed_container`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seed_container` (
  `high_oid` bigint(20) DEFAULT '0',
  `seed_id` varchar(3) DEFAULT NULL,
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `seq_id` (`seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seed_container`
--

LOCK TABLES `seed_container` WRITE;
/*!40000 ALTER TABLE `seed_container` DISABLE KEYS */;
INSERT INTO `seed_container` VALUES (5,'a01',1,'REST'),(3,'PAT',2,'PAT');
/*!40000 ALTER TABLE `seed_container` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `oid` varchar(20) NOT NULL,
  `salutation` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `password_exp_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_id` varchar(100) NOT NULL,
  `user_status` varchar(5) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `email_id` varchar(100) NOT NULL,
  `mobile_no` varchar(12) NOT NULL,
  `primary_contact` varchar(12) DEFAULT NULL,
  `secondary_contact` varchar(12) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `speciality` varchar(100) DEFAULT NULL,
  `care_provider` varchar(100) DEFAULT 'FALSE',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES ('a0100000003000000005','Mr','Selva','Raj','e08a7c49d96c2b475656cc8fe18cee8e','2015-09-21 11:04:50','selva','ACT',NULL,NULL,NULL,NULL,NULL,'selva@gmail.com','9791034869',NULL,NULL,NULL,NULL,'FALSE');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_oid` varchar(20) NOT NULL,
  `role_oid` varchar(20) NOT NULL,
  PRIMARY KEY (`user_oid`,`role_oid`),
  KEY `fk7342994993ad290f` (`role_oid`),
  CONSTRAINT `fk7342994993ad290f` FOREIGN KEY (`role_oid`) REFERENCES `roles` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ward` (
  `oid` varchar(20) NOT NULL,
  `ward_name` varchar(100) NOT NULL,
  `floor_oid` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-13 14:35:44
