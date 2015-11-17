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
-- Table structure for table `alert_type`
--

DROP TABLE IF EXISTS `alert_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alert_type` (
  `oid` varchar(20) NOT NULL,
  `alert_name` varchar(30) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_type`
--

LOCK TABLES `alert_type` WRITE;
/*!40000 ALTER TABLE `alert_type` DISABLE KEYS */;
INSERT INTO `alert_type` VALUES ('a010000001900000001a','New','ACT'),('a010000001900000001b','Alert','ACT'),('a010000001900000001c','Warning','ACT'),('a0100000022000000023','Test','ACT');
/*!40000 ALTER TABLE `alert_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_item`
--

DROP TABLE IF EXISTS `category_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_item` (
  `oid` varchar(20) NOT NULL,
  `category` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_item`
--

LOCK TABLES `category_item` WRITE;
/*!40000 ALTER TABLE `category_item` DISABLE KEYS */;
INSERT INTO `category_item` VALUES ('a010000000a00000000c','General','Test General','ACT'),('a0100000010000000011','Test Category','Test Description','ACT');
/*!40000 ALTER TABLE `category_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `oid` varchar(20) NOT NULL,
  `client_id` varchar(100) NOT NULL,
  `api_key` varchar(20) DEFAULT NULL,
  `authorized_services` varchar(1024) DEFAULT NULL,
  `auth_ip_addresses` varchar(255) DEFAULT NULL,
  `client_type` varchar(20) NOT NULL,
  `contract_started_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `contract_ends_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `api_key_expiration_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` varchar(100) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('o0100000402000000501','ahana','574EBDD5B9D10563','*','*','Internal','2010-11-01 14:34:04','2020-12-30 18:30:00','2020-12-30 18:30:00','2010-11-01 14:34:04','2010-11-01 14:34:04','selva','selva');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

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
  `status` varchar(5) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES ('a010000000800000000a','First Floor','FF','ACT'),('a010000001c00000001d','Second Floor','SEF','ACT');
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
INSERT INTO `lookup` VALUES ('A+','BLOODGROUP','A+',1,0),('A-','BLOODGROUP','A-',2,0),('AB+','BLOODGROUP','AB+',5,0),('AB-','BLOODGROUP','AB-',6,0),('Andaman & Nicobar Islands','STATE_INDIA','Andaman & Nicobar Islands',29,0),('Andhra Pradesh','STATE_INDIA','Andhra Pradesh',2,0),('Arunachal Pradesh','STATE_INDIA','Arunachal Pradesh',3,0),('Assam','STATE_INDIA','Assam',4,0),('B+','BLOODGROUP','B+',3,0),('B-','BLOODGROUP','B-',4,0),('Bangladesh','COUNTRY','Bangladesh',3,0),('Bihar','STATE_INDIA','Bihar',5,0),('Chandigarh','STATE_INDIA','Chandigarh',30,0),('Chennai','CITY_TAMIL NADU','Chennai',30,0),('Chhattisgarh','STATE_INDIA','Chhattisgarh',6,0),('Coimbatore','CITY_TAMIL NADU','Coimbatore',2,0),('Cuddalore','CITY_TAMIL NADU','Cuddalore',3,0),('Dadra & Nagar Haveli','STATE_INDIA','Dadra & Nagar Haveli',31,0),('Daman & Diu','STATE_INDIA','Daman & Diu',32,0),('Delhi','STATE_INDIA','Delhi',33,0),('Dharmapuri','CITY_TAMIL NADU','Dharmapuri',4,0),('Dindigul','CITY_TAMIL NADU','Dindigul',5,0),('Dr','SALUTATION','Dr',3,0),('Erode','CITY_TAMIL NADU','Erode',6,0),('Free','CATEGORY','Free',2,0),('Goa','STATE_INDIA','Goa',7,0),('Gujarat','STATE_INDIA','Gujarat',8,0),('Haryana','STATE_INDIA','Haryana',9,0),('Himachal Pradesh','STATE_INDIA','Himachal Pradesh',10,0),('India','COUNTRY','India',1,0),('Indonesia','COUNTRY','Indonesia',4,0),('Jammu & kashmir','STATE_INDIA','Jammu & kashmir',11,0),('Jharkhand','STATE_INDIA','Jharkhand',12,0),('Kanchipuram','CITY_TAMIL NADU','Kanchipuram',7,0),('Kanyakumari','CITY_TAMIL NADU','Kanyakumari',8,0),('Karnataka','STATE_INDIA','Karnataka',13,0),('Karur','CITY_TAMIL NADU','Karur',9,0),('Kerala','STATE_INDIA','Kerala',14,0),('Krishnagiri','CITY_TAMIL NADU','Krishnagiri',10,0),('Lakshadweep','STATE_INDIA','Lakshadweep',34,0),('Madhya Pradesh','STATE_INDIA','Madhya Pradesh',15,0),('Madurai','CITY_TAMIL NADU','Madurai',1,0),('Maharashtra','STATE_INDIA','Maharashtra',16,0),('Malaysia','COUNTRY','Malaysia',2,0),('Manipur','STATE_INDIA','Manipur',17,0),('Meghalaya','STATE_INDIA','Meghalaya',18,0),('Miss','SALUTATION','Miss',2,0),('Mizoram','STATE_INDIA','Mizoram',19,0),('Mr','SALUTATION','Mr',1,0),('Ms','SALUTATION','Ms',4,0),('Nagaland','STATE_INDIA','Nagaland',20,0),('Nagapattinam','CITY_TAMIL NADU','Nagapattinam',11,0),('Namakkal','CITY_TAMIL NADU','Namakkal',12,0),('Nepal','COUNTRY','Nepal',8,0),('O+','BLOODGROUP','O+',7,0),('O-','BLOODGROUP','O-',8,0),('Orissa','STATE_INDIA','Orissa',21,0),('Pakistan','COUNTRY','Pakistan',5,0),('Perambalur','CITY_TAMIL NADU','Perambalur',13,0),('Philippines','COUNTRY','Philippines',11,0),('Priority','CATEGORY','Priority',3,0),('Priority-Free','CATEGORY','Priority-Free',4,0),('Prof','SALUTATION','Prof',5,0),('Puducherry','STATE_INDIA','Puducherry',35,0),('Pudukkottai','CITY_TAMIL NADU','Pudukkottai',14,0),('Punjab','STATE_INDIA','Punjab',22,0),('Rajasthan','STATE_INDIA','Rajasthan',23,0),('Ramanathapuram','CITY_TAMIL NADU','Ramanathapuram',15,0),('Rev','SALUTATION','Rev',6,0),('Salem','CITY_TAMIL NADU','Salem',16,0),('Sikkim','STATE_INDIA','Sikkim',24,0),('Singapore','COUNTRY','Singapore',6,0),('Sivaganga','CITY_TAMIL NADU','Sivaganga',17,0),('Sri Lanka','COUNTRY','Sri Lanka',7,0),('Standard-Default','CATEGORY','Standard-Default',1,0),('Tamil Nadu','STATE_INDIA','Tamil Nadu',1,0),('Thailand','COUNTRY','Thailand',9,0),('Thanjavur','CITY_TAMIL NADU','Thanjavur',18,0),('The Nilgiris','CITY_TAMIL NADU','The Nilgiris',19,0),('Theni','CITY_TAMIL NADU','Theni',20,0),('Thoothukudi','CITY_TAMIL NADU','Thoothukudi',21,0),('Tiruchirapalli','CITY_TAMIL NADU','Tiruchirapalli',22,0),('Tirunelveli','CITY_TAMIL NADU','Tirunelveli',23,0),('Tiruvallur','CITY_TAMIL NADU','Tiruvallur',24,0),('Tiruvannamalai','CITY_TAMIL NADU','Tiruvannamalai',25,0),('Tiruvarur','CITY_TAMIL NADU','Tiruvarur',26,0),('Tripura','STATE_INDIA','Tripura',25,0),('Uttar Pradesh','STATE_INDIA','Uttar Pradesh',26,0),('Uttarakhand','STATE_INDIA','Uttarakhand',27,0),('Vellore','CITY_TAMIL NADU','Vellore',27,0),('Vietnam','COUNTRY','Vietnam',10,0),('Viluppuram','CITY_TAMIL NADU','Viluppuram',28,0),('VIP','CATEGORY','VIP',5,0),('Virudhunagar','CITY_TAMIL NADU','Virudhunagar',29,0),('West Bengal','STATE_INDIA','West Bengal',28,0);
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
INSERT INTO `organization` VALUES ('ahanahospital','Ahana Hospital','No.11 Subraman Street,Gandhi Nagar','India','Tamil Nadu','Madurai','0452253311','','9677725588','0452-253444','');
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
INSERT INTO `patient` VALUES ('a010000000c00000000d','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c00000000e','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c00000000f','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000010','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000011','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000012','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000013','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000014','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000015','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000d00000000e','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000d00000000f','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a0100000014000000015','test','test','M','1234567890',12,'test','Madurai','Tamil Nadu','India','12312312','Standard','Normal','2015-10-21 18:30:00'),('a0100000016000000017','Selva','Raj','M','9898989898',32,'ewewewew','Madurai','Tamil Nadu','India',NULL,'Standard','Normal','2015-10-17 15:19:57'),('PAT10','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT11','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT12','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT13','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT14','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT15','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT16','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT17','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT18','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT19','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT20','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT21','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT22','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT23','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT24','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT25','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT26','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT27','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT28','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT29','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT3','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT30','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT31','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT32','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT33','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT34','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT35','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT36','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT37','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT38','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT39','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT4','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT40','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT41','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT42','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT43','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT44','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT45','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT46','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT47','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT48','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT49','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT5','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT50','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT51','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT52','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT53','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT54','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT55','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT56','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT57','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT58','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT59','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT6','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT60','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT61','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT62','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT63','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT64','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT65','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT7','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT8','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT9','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures`
--

DROP TABLE IF EXISTS `procedures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedures` (
  `oid` varchar(20) NOT NULL,
  `procedures_name` varchar(100) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures`
--

LOCK TABLES `procedures` WRITE;
/*!40000 ALTER TABLE `procedures` DISABLE KEYS */;
INSERT INTO `procedures` VALUES ('a010000000b00000000d','Test Procedure 2','ACT'),('a010000000b00000000e','Test Procedure 1','ACT'),('a0100000011000000012','Test Procedures 3','ACT');
/*!40000 ALTER TABLE `procedures` ENABLE KEYS */;
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
INSERT INTO `role_rights` VALUES ('a0100000020000000021','a0100000004000000007','a0100000003000000005','ahanahospital'),('a0100000020000000022','a0100000004000000007','a0100000003000000006','ahanahospital');
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
INSERT INTO `roles` VALUES ('a0100000004000000006','Admin-Module','InACT'),('a0100000004000000007','Role-User','ACT'),('a0100000004000000008','Super_Admin','ACT'),('a0100000015000000016','Role-Billing','ACT'),('a0100000021000000022','ROLE-DOCTOR','ACT'),('a0100000023000000024','test','ACT');
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
INSERT INTO `room` VALUES ('a010000000900000000b','Sugery Ward','a0100000007000000009','vacant','Not Available','ACT'),('a010000001b00000001c','Test','a010000000700000000b','Free','Free','ACT');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_charges`
--

DROP TABLE IF EXISTS `room_charges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_charges` (
  `oid` varchar(20) NOT NULL,
  `room_charge_items_oid` varchar(20) NOT NULL,
  `charge` int(10) DEFAULT NULL,
  `room_type_oid` varchar(20) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_charges`
--

LOCK TABLES `room_charges` WRITE;
/*!40000 ALTER TABLE `room_charges` DISABLE KEYS */;
INSERT INTO `room_charges` VALUES ('a010000001d00000001e','a0100000017000000018',10000,'a0100000013000000015','ACT');
/*!40000 ALTER TABLE `room_charges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_charges_item`
--

DROP TABLE IF EXISTS `room_charges_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_charges_item` (
  `oid` varchar(20) NOT NULL,
  `item` varchar(100) NOT NULL,
  `code` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `category_oid` varchar(20) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_charges_item`
--

LOCK TABLES `room_charges_item` WRITE;
/*!40000 ALTER TABLE `room_charges_item` DISABLE KEYS */;
INSERT INTO `room_charges_item` VALUES ('a0100000017000000018','Item Test','Code Test','Description Test','a010000000a00000000c','ACT');
/*!40000 ALTER TABLE `room_charges_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_maintance_details`
--

DROP TABLE IF EXISTS `room_maintance_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_maintance_details` (
  `oid` varchar(20) NOT NULL,
  `maintenance_name` varchar(100) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_maintance_details`
--

LOCK TABLES `room_maintance_details` WRITE;
/*!40000 ALTER TABLE `room_maintance_details` DISABLE KEYS */;
INSERT INTO `room_maintance_details` VALUES ('a0100000018000000019','Not Available Update','ACT'),('a010000001a00000001b','Under Maintenance','ACT');
/*!40000 ALTER TABLE `room_maintance_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_type` (
  `oid` varchar(20) NOT NULL,
  `room_name` varchar(100) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES ('a0100000013000000014','AC','ACT'),('a0100000013000000015','NON-AC-2','ACT'),('a0100000013000000016','Delux','ACT');
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
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
INSERT INTO `seed_container` VALUES (43,'a01',1,'REST'),(4,'PAT',2,'PAT');
/*!40000 ALTER TABLE `seed_container` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speciality_details`
--

DROP TABLE IF EXISTS `speciality_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speciality_details` (
  `oid` varchar(20) NOT NULL,
  `speciality_name` varchar(100) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speciality_details`
--

LOCK TABLES `speciality_details` WRITE;
/*!40000 ALTER TABLE `speciality_details` DISABLE KEYS */;
INSERT INTO `speciality_details` VALUES ('a0100000012000000013','Test Speciality 1','ACT');
/*!40000 ALTER TABLE `speciality_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `oid` varchar(20) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `creator_client_id` varchar(100) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` varchar(100) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES ('a010000002a00000002b','selva','ahana','2015-11-15 17:09:01','2015-11-15 17:09:01','selva','selva');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
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
  `activation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `designation` varchar(100) DEFAULT NULL,
  `speciality` varchar(100) DEFAULT NULL,
  `care_provider` varchar(10) DEFAULT 'FALSE',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES ('a010000001e00000001f','Mr','Selva','Raj','cc03e747a6afbbcbf8be7668acfebee5','2015-11-24 18:30:00','selva','ACT','Test','India','Tamil Nadu','Madurai','625014','selva@gmail.com','9898989898','2015-10-17 18:30:00','Test','a0100000012000000013','Yes');
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
INSERT INTO `user_roles` VALUES ('a010000001e00000001f','a0100000004000000007'),('a010000001e00000001f','a0100000004000000008'),('a010000001e00000001f','a0100000015000000016');
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
INSERT INTO `ward` VALUES ('a0100000007000000009','Sugery Ward1','a010000000800000000a','ACT'),('a010000000700000000a','Sugery Ward','a010000000800000000a','ACT'),('a010000000700000000b','Sugery Ward 2','a010000000800000000a','ACT'),('a010000000700000000c','Sugery Ward 3','a010000000e00000000f','ACT'),('a010000000f000000010','Test Ward','a010000000e00000000f','ACT');
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

-- Dump completed on 2015-11-17 15:20:19
