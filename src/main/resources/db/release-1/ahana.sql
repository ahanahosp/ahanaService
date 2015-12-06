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
INSERT INTO `alert_type` VALUES ('a010000001900000001a','New','ACT'),('a010000001900000001b','Alert','ACT'),('a010000001900000001c','Warning','ACT'),('a0100000022000000023','Emergency','ACT');
/*!40000 ALTER TABLE `alert_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `allied_charges`
--

DROP TABLE IF EXISTS `allied_charges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allied_charges` (
  `oid` varchar(20) NOT NULL,
  `allied_charges` varchar(100) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allied_charges`
--

LOCK TABLES `allied_charges` WRITE;
/*!40000 ALTER TABLE `allied_charges` DISABLE KEYS */;
INSERT INTO `allied_charges` VALUES ('a01000000af0000000b0','Test Charges123','INACT'),('a01000000af0000000b1','Needle Charges','ACT');
/*!40000 ALTER TABLE `allied_charges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bed_vs_rooms`
--

DROP TABLE IF EXISTS `bed_vs_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bed_vs_rooms` (
  `room_type_oid` varchar(20) NOT NULL,
  `room_and_bed_type_oid` varchar(20) NOT NULL,
  PRIMARY KEY (`room_type_oid`,`room_and_bed_type_oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bed_vs_rooms`
--

LOCK TABLES `bed_vs_rooms` WRITE;
/*!40000 ALTER TABLE `bed_vs_rooms` DISABLE KEYS */;
INSERT INTO `bed_vs_rooms` VALUES ('a0100000013000000014','a01000000fd0000000fe'),('a0100000013000000014','a010000010e00000010f'),('a0100000013000000014','a0100000125000000126'),('a0100000013000000015','a01000000fd0000000fe'),('a0100000013000000015','a010000010e00000010f'),('a0100000013000000015','a0100000125000000126'),('a0100000013000000016','a010000010e00000010f'),('a0100000013000000016','a0100000125000000126');
/*!40000 ALTER TABLE `bed_vs_rooms` ENABLE KEYS */;
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
INSERT INTO `category_item` VALUES ('a010000000a00000000c','General','Test General','ACT'),('a0100000010000000011','Special','Special','ACT');
/*!40000 ALTER TABLE `category_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `charges_category_view`
--

DROP TABLE IF EXISTS `charges_category_view`;
/*!50001 DROP VIEW IF EXISTS `charges_category_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `charges_category_view` AS SELECT 
 1 AS `oid`,
 1 AS `subCategoryName`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `charges_for_category`
--

DROP TABLE IF EXISTS `charges_for_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charges_for_category` (
  `oid` varchar(20) NOT NULL,
  `category` varchar(100) NOT NULL,
  `sub_category_oid` varchar(20) NOT NULL,
  `default_value` varchar(20) DEFAULT NULL,
  `free` varchar(20) DEFAULT NULL,
  `standard_default` varchar(20) DEFAULT NULL,
  `priority` varchar(20) DEFAULT NULL,
  `priority_free` varchar(20) DEFAULT NULL,
  `vip` varchar(20) DEFAULT NULL,
  `vip_free` varchar(20) DEFAULT NULL,
  `ac` varchar(20) DEFAULT NULL,
  `ac_small` varchar(20) DEFAULT NULL,
  `deluxe` varchar(20) DEFAULT NULL,
  `economy_non_ac` varchar(20) DEFAULT NULL,
  `non_ac` varchar(20) DEFAULT NULL,
  `non_ac_small` varchar(20) DEFAULT NULL,
  `semi_economy_non_ac` varchar(20) DEFAULT NULL,
  `special_care_room` varchar(20) DEFAULT NULL,
  `suite` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charges_for_category`
--

LOCK TABLES `charges_for_category` WRITE;
/*!40000 ALTER TABLE `charges_for_category` DISABLE KEYS */;
INSERT INTO `charges_for_category` VALUES ('a01000000c50000000c6','Allied Charges','a01000000af0000000b1','Test','Test','Test','Test','Test','Test','Test','Test','Test','Test','TEst','Test','Test','Test','Test','Test'),('a010000011e00000011f','Allied Charges','a01000000af0000000b1','Teat','666','hjhjk','njknkj','njknkjn','knkn','knknknk','njnkn','nk','nk','nkn','kn','kn','k','nk','nkn');
/*!40000 ALTER TABLE `charges_for_category` ENABLE KEYS */;
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
-- Table structure for table `config_room_charges`
--

DROP TABLE IF EXISTS `config_room_charges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_room_charges` (
  `oid` varchar(20) NOT NULL,
  `discount` double NOT NULL,
  `format` varchar(20) NOT NULL,
  `start_time` varchar(10) DEFAULT NULL,
  `end_time` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_room_charges`
--

LOCK TABLES `config_room_charges` WRITE;
/*!40000 ALTER TABLE `config_room_charges` DISABLE KEYS */;
INSERT INTO `config_room_charges` VALUES ('276372637sgeufud',3.5,'24','01:00 AM','01:00 PM');
/*!40000 ALTER TABLE `config_room_charges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_schedule`
--

DROP TABLE IF EXISTS `doctor_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor_schedule` (
  `oid` varchar(20) NOT NULL,
  `doctor_oid` varchar(20) NOT NULL,
  `visiting_day` varchar(20) NOT NULL,
  `start_time` varchar(10) DEFAULT NULL,
  `end_time` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_schedule`
--

LOCK TABLES `doctor_schedule` WRITE;
/*!40000 ALTER TABLE `doctor_schedule` DISABLE KEYS */;
INSERT INTO `doctor_schedule` VALUES ('a010000001e00000001f','a010000001e00000001f','All Day','1.00 PM','6.00 PM');
/*!40000 ALTER TABLE `doctor_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `doctor_schedule_view`
--

DROP TABLE IF EXISTS `doctor_schedule_view`;
/*!50001 DROP VIEW IF EXISTS `doctor_schedule_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `doctor_schedule_view` AS SELECT 
 1 AS `oid`,
 1 AS `salutation`,
 1 AS `fullName`,
 1 AS `designation`,
 1 AS `speciality`,
 1 AS `careProvider`,
 1 AS `userStatus`,
 1 AS `visitingDay`,
 1 AS `startTime`,
 1 AS `endTime`*/;
SET character_set_client = @saved_cs_client;

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
INSERT INTO `floor` VALUES ('a010000000800000000a','First Floor','FF','ACT'),('a010000001c00000001d','Second Floor Test','SEF','ACT');
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `user_id` varchar(100) NOT NULL,
  `user_oid` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `activation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `password_exp_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` varchar(5) NOT NULL DEFAULT 'ACT',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('ahana','a010000001e00000001f','e0f7a4d0ef9b84b83b693bbf3feb8e6e','2015-11-28 18:30:00','2016-11-28 18:30:00','ACT'),('harim','a01000000d10000000d2','044b8e7cc52bce80fe9afd70f8f4d44e','2015-11-29 18:30:00','2015-12-30 18:30:00','ACT');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
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
INSERT INTO `lookup` VALUES ('A+','BLOODGROUP','A+',1,0),('A-','BLOODGROUP','A-',2,0),('AB+','BLOODGROUP','AB+',5,0),('AB-','BLOODGROUP','AB-',6,0),('All Days','DAYS','All Days',1,0),('Allied Charges','CHARGESCATEGORY','Allied Charges',1,0),('Andaman & Nicobar Islands','STATE_INDIA','Andaman & Nicobar Islands',29,0),('Andhra Pradesh','STATE_INDIA','Andhra Pradesh',2,0),('Arunachal Pradesh','STATE_INDIA','Arunachal Pradesh',3,0),('Assam','STATE_INDIA','Assam',4,0),('B+','BLOODGROUP','B+',3,0),('B-','BLOODGROUP','B-',4,0),('Bangladesh','COUNTRY','Bangladesh',3,0),('Bihar','STATE_INDIA','Bihar',5,0),('Chandigarh','STATE_INDIA','Chandigarh',30,0),('Chennai','CITY_TAMIL NADU','Chennai',30,0),('Chhattisgarh','STATE_INDIA','Chhattisgarh',6,0),('Coimbatore','CITY_TAMIL NADU','Coimbatore',2,0),('Cuddalore','CITY_TAMIL NADU','Cuddalore',3,0),('Dadra & Nagar Haveli','STATE_INDIA','Dadra & Nagar Haveli',31,0),('Daman & Diu','STATE_INDIA','Daman & Diu',32,0),('Delhi','STATE_INDIA','Delhi',33,0),('Dharmapuri','CITY_TAMIL NADU','Dharmapuri',4,0),('Dindigul','CITY_TAMIL NADU','Dindigul',5,0),('Dr','SALUTATION','Dr',3,0),('Erode','CITY_TAMIL NADU','Erode',6,0),('Free','CATEGORY','Free',2,0),('Friday','DAYS','Friday',7,0),('Goa','STATE_INDIA','Goa',7,0),('Gujarat','STATE_INDIA','Gujarat',8,0),('Haryana','STATE_INDIA','Haryana',9,0),('Himachal Pradesh','STATE_INDIA','Himachal Pradesh',10,0),('India','COUNTRY','India',1,0),('Indonesia','COUNTRY','Indonesia',4,0),('Jammu & kashmir','STATE_INDIA','Jammu & kashmir',11,0),('Jharkhand','STATE_INDIA','Jharkhand',12,0),('Kanchipuram','CITY_TAMIL NADU','Kanchipuram',7,0),('Kanyakumari','CITY_TAMIL NADU','Kanyakumari',8,0),('Karnataka','STATE_INDIA','Karnataka',13,0),('Karur','CITY_TAMIL NADU','Karur',9,0),('Kerala','STATE_INDIA','Kerala',14,0),('Krishnagiri','CITY_TAMIL NADU','Krishnagiri',10,0),('Laboratory Charges','CHARGESCATEGORY','Laboratory Charges',5,0),('Lakshadweep','STATE_INDIA','Lakshadweep',34,0),('Madhya Pradesh','STATE_INDIA','Madhya Pradesh',15,0),('Madurai','CITY_TAMIL NADU','Madurai',1,0),('Maharashtra','STATE_INDIA','Maharashtra',16,0),('Malaysia','COUNTRY','Malaysia',2,0),('Manipur','STATE_INDIA','Manipur',17,0),('Meghalaya','STATE_INDIA','Meghalaya',18,0),('Miss','SALUTATION','Miss',2,0),('Mizoram','STATE_INDIA','Mizoram',19,0),('Monday','DAYS','Monday',3,0),('Mr','SALUTATION','Mr',1,0),('Ms','SALUTATION','Ms',4,0),('Nagaland','STATE_INDIA','Nagaland',20,0),('Nagapattinam','CITY_TAMIL NADU','Nagapattinam',11,0),('Namakkal','CITY_TAMIL NADU','Namakkal',12,0),('Nepal','COUNTRY','Nepal',8,0),('O+','BLOODGROUP','O+',7,0),('O-','BLOODGROUP','O-',8,0),('Orissa','STATE_INDIA','Orissa',21,0),('Pakistan','COUNTRY','Pakistan',5,0),('Perambalur','CITY_TAMIL NADU','Perambalur',13,0),('Philippines','COUNTRY','Philippines',11,0),('Priority','CATEGORY','Priority',3,0),('Priority-Free','CATEGORY','Priority-Free',4,0),('Procedures Charges','CHARGESCATEGORY','Procedures Charges',4,0),('Prof','SALUTATION','Prof',5,0),('Professional Charges','CHARGESCATEGORY','Professional Charges',3,0),('Puducherry','STATE_INDIA','Puducherry',35,0),('Pudukkottai','CITY_TAMIL NADU','Pudukkottai',14,0),('Punjab','STATE_INDIA','Punjab',22,0),('Rajasthan','STATE_INDIA','Rajasthan',23,0),('Ramanathapuram','CITY_TAMIL NADU','Ramanathapuram',15,0),('Rev','SALUTATION','Rev',6,0),('Room Charges','CHARGESCATEGORY','Room Charges',2,0),('Salem','CITY_TAMIL NADU','Salem',16,0),('Saturday','DAYS','Saturday',8,0),('Sikkim','STATE_INDIA','Sikkim',24,0),('Singapore','COUNTRY','Singapore',6,0),('Sivaganga','CITY_TAMIL NADU','Sivaganga',17,0),('Sri Lanka','COUNTRY','Sri Lanka',7,0),('Standard-Default','CATEGORY','Standard-Default',1,0),('Sunday','DAYS','Sunday',2,0),('Tamil Nadu','STATE_INDIA','Tamil Nadu',1,0),('Thailand','COUNTRY','Thailand',9,0),('Thanjavur','CITY_TAMIL NADU','Thanjavur',18,0),('The Nilgiris','CITY_TAMIL NADU','The Nilgiris',19,0),('Theni','CITY_TAMIL NADU','Theni',20,0),('Thoothukudi','CITY_TAMIL NADU','Thoothukudi',21,0),('Thursday','DAYS','Thursday',6,0),('Tiruchirapalli','CITY_TAMIL NADU','Tiruchirapalli',22,0),('Tirunelveli','CITY_TAMIL NADU','Tirunelveli',23,0),('Tiruvallur','CITY_TAMIL NADU','Tiruvallur',24,0),('Tiruvannamalai','CITY_TAMIL NADU','Tiruvannamalai',25,0),('Tiruvarur','CITY_TAMIL NADU','Tiruvarur',26,0),('Tripura','STATE_INDIA','Tripura',25,0),('Tueday','DAYS','Tueday',4,0),('Uttar Pradesh','STATE_INDIA','Uttar Pradesh',26,0),('Uttarakhand','STATE_INDIA','Uttarakhand',27,0),('Vellore','CITY_TAMIL NADU','Vellore',27,0),('Vietnam','COUNTRY','Vietnam',10,0),('Viluppuram','CITY_TAMIL NADU','Viluppuram',28,0),('VIP','CATEGORY','VIP',5,0),('Virudhunagar','CITY_TAMIL NADU','Virudhunagar',29,0),('Wednesday','DAYS','Wednesday',5,0),('West Bengal','STATE_INDIA','West Bengal',28,0);
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
INSERT INTO `patient` VALUES ('a010000000c00000000d','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c00000000e','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c00000000f','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000010','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000011','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000012','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000013','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000014','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000c000000015','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000d00000000e','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a010000000d00000000f','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('a0100000014000000015','test','test','M','1234567890',12,'test','Madurai','Tamil Nadu','India','12312312','Standard','Normal','2015-10-21 18:30:00'),('a0100000016000000017','Selva','Raj','M','9898989898',32,'ewewewew','Madurai','Tamil Nadu','India',NULL,'Standard','Normal','2015-10-17 15:19:57'),('a0100000063000000064','Selva','Raj','M','9791034869',32,'Test','Madurai','Tamil Nadu','India','676878','Standard','Normal','2015-10-15 18:30:00'),('PAT10','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT11','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT12','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT13','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT14','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT15','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT16','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT17','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT18','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT19','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT20','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT21','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT22','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT23','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT24','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT25','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT26','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT27','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT28','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT29','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT3','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT30','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT31','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT32','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT33','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT34','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT35','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT36','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT37','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT38','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT39','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT4','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT40','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT41','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT42','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT43','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT44','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT45','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT46','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT47','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT48','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT49','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT5','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT50','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT51','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT52','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT53','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT54','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT55','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT56','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT57','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT58','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT59','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT6','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT60','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT61','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT62','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT63','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT64','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT65','TestAbc','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT7','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT8','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00'),('PAT9','test','test','Male','9898989899',53,'tes123232t','Madurai','TamilNadu','india','78787878','Standard','standard','2015-10-06 18:30:00');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_category`
--

DROP TABLE IF EXISTS `patient_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_category` (
  `oid` varchar(100) NOT NULL,
  `category_name` varchar(20) NOT NULL,
  `activation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` varchar(5) NOT NULL DEFAULT 'ACT',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_category`
--

LOCK TABLES `patient_category` WRITE;
/*!40000 ALTER TABLE `patient_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_category` ENABLE KEYS */;
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
INSERT INTO `procedures` VALUES ('a010000000b00000000d','Test Procedure','ACT'),('a0100000011000000012','Test Procedures 3','ACT'),('a010000009900000009a','Test 123','ACT'),('a010000009900000009b','Test Test','InACT');
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
INSERT INTO `role_rights` VALUES ('a0100000092000000095','a0100000021000000022','a0100000003000000007','ahanahospital'),('a0100000096000000097','a0100000004000000007','a0100000003000000005','ahanahospital'),('a0100000096000000098','a0100000004000000007','a0100000003000000007','ahanahospital'),('a0100000096000000099','a0100000004000000007','a0100000003000000008','ahanahospital'),('a010000009600000009a','a0100000004000000008','a0100000003000000005','ahanahospital'),('a010000009600000009b','a0100000004000000008','a0100000003000000006','ahanahospital'),('a010000009600000009c','a0100000015000000016','a0100000003000000005','ahanahospital'),('a010000009600000009d','a0100000015000000016','a0100000003000000008','ahanahospital'),('a010000009600000009e','a0100000015000000016','a0100000003000000007','ahanahospital'),('a010000009600000009f','a0100000023000000024','a0100000003000000008','ahanahospital'),('a01000000960000000a0','a0100000050000000051','a0100000003000000008','ahanahospital'),('a01000000960000000a1','a0100000050000000051','a0100000003000000006','ahanahospital'),('a01000000d30000000d4','a01000000d20000000d3','a0100000003000000005','ahanahospital'),('a01000000d30000000d5','a01000000d20000000d3','a0100000003000000006','ahanahospital'),('a01000000d30000000d6','a01000000d20000000d3','a0100000003000000007','ahanahospital'),('a01000000d30000000d7','a01000000d20000000d3','a0100000003000000008','ahanahospital'),('a01000000e60000000e7','a0100000004000000006','a0100000003000000005','ahanahospital'),('a01000000e60000000e8','a0100000004000000006','a0100000003000000008','ahanahospital'),('a01000000e60000000e9','a0100000004000000006','a0100000003000000006','ahanahospital'),('a01000000e60000000ea','a0100000004000000006','a0100000003000000007','ahanahospital');
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
INSERT INTO `roles` VALUES ('a0100000004000000006','Role-Admin','ACT'),('a0100000004000000007','Role-User','ACT'),('a0100000004000000008','Role-Super-Admin','ACT'),('a0100000015000000016','Role-Billing','ACT'),('a0100000021000000022','ROLE-DOCTOR','ACT'),('a0100000023000000024','Role-Test','ACT'),('a0100000050000000051','New Test Role','ACT'),('a0100000057000000058','Role ABc','ACT'),('a01000000d20000000d3','Test Role Name','INACT');
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
  `status` varchar(20) NOT NULL,
  `maintenance_oid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('a010000000900000000b','Sugery Ward','a0100000007000000009','vacant','ACT','a0100000018000000019'),('a010000001b00000001c','Emergency','a010000000700000000b','Free','ACT','a010000001a00000001b'),('a010000011f000000120','Accident','a010000000700000000b','Accident','ACT','a0100000018000000019');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_bed_type`
--

DROP TABLE IF EXISTS `room_bed_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_bed_type` (
  `oid` varchar(20) NOT NULL,
  `order_no` varchar(5) NOT NULL,
  `bed_no` varchar(100) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_bed_type`
--

LOCK TABLES `room_bed_type` WRITE;
/*!40000 ALTER TABLE `room_bed_type` DISABLE KEYS */;
INSERT INTO `room_bed_type` VALUES ('a01000000fd0000000fe','1','100','INACT'),('a010000010e00000010f','1','100','INACT'),('a0100000125000000126','1','1001','ACT');
/*!40000 ALTER TABLE `room_bed_type` ENABLE KEYS */;
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
INSERT INTO `room_charges` VALUES ('a010000001d00000001e','a0100000017000000018',50001,'a0100000013000000014','ACT');
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
INSERT INTO `room_charges_item` VALUES ('a0100000017000000018','Item 01','IT01','Description Test 1','a010000000a00000000c','ACT');
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
INSERT INTO `room_maintance_details` VALUES ('a0100000018000000019','Not Available','ACT'),('a010000001a00000001b','Under Maintenance','ACT');
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
INSERT INTO `room_type` VALUES ('a0100000013000000014','AC','ACT'),('a0100000013000000015','Non-AC','ACT'),('a0100000013000000016','Delux','ACT');
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
INSERT INTO `seed_container` VALUES (308,'a01',1,'REST'),(4,'PAT',2,'PAT');
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
INSERT INTO `speciality_details` VALUES ('a0100000012000000013','General Surgen','ACT'),('a010000009a00000009b','MD','ACT');
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
INSERT INTO `token` VALUES ('a0100000132000000133','ahana','ahana','2015-12-06 13:04:29','2015-12-06 13:04:29','ahana','ahana'),('a0100000133000000134','ahana','ahana','2015-12-06 13:07:45','2015-12-06 13:07:45','ahana','ahana');
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
  `user_status` varchar(5) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `email_id` varchar(100) NOT NULL,
  `mobile_no` varchar(12) NOT NULL,
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
INSERT INTO `user_profile` VALUES ('a010000001e00000001f','Mr','Vikhram','S','ACT','Thiruppalai','India','Tamil Nadu','Madurai','625019','vikhram@ahana.com','9898909800','Doctor','a0100000012000000013','Yes'),('a01000000d10000000d2','Mr','Hari','M','ACT','Madurai','India','Tamil Nadu','Madurai','625014','hari@gmail.com','9754343457','Developer',NULL,'FALSE'),('a01000000db0000000dc','Mr','System','System','ACT','Madurai','India','Tamil Nadu','Madurai','625014','admin@gmail.com','9897867625','Admin','a010000009a00000009b','Yes');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_profile_view`
--

DROP TABLE IF EXISTS `user_profile_view`;
/*!50001 DROP VIEW IF EXISTS `user_profile_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_profile_view` AS SELECT 
 1 AS `oid`,
 1 AS `salutation`,
 1 AS `firstName`,
 1 AS `lastName`,
 1 AS `fullName`,
 1 AS `designation`,
 1 AS `emailId`,
 1 AS `mobileNo`,
 1 AS `userId`,
 1 AS `password`,
 1 AS `passwordExpDate`,
 1 AS `activationDate`,
 1 AS `careProvider`,
 1 AS `userStatus`,
 1 AS `roles`,
 1 AS `speciality`,
 1 AS `specialityName`,
 1 AS `address`,
 1 AS `country`,
 1 AS `state`,
 1 AS `city`,
 1 AS `zip`,
 1 AS `loginStatus`*/;
SET character_set_client = @saved_cs_client;

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
INSERT INTO `user_roles` VALUES ('a010000001e00000001f','a0100000004000000006'),('a01000000d10000000d2','a0100000004000000006'),('a010000001e00000001f','a0100000004000000007'),('a0100000061000000062','a0100000004000000007'),('a0100000061000000063','a0100000004000000007'),('a01000000d10000000d2','a0100000004000000007'),('a010000001e00000001f','a0100000004000000008'),('a01000000d10000000d2','a0100000004000000008'),('a010000001e00000001f','a0100000015000000016'),('a0100000061000000062','a0100000015000000016'),('a01000000d10000000d2','a0100000015000000016'),('a010000001e00000001f','a0100000021000000022'),('a0100000061000000062','a0100000021000000022'),('a01000000d10000000d2','a0100000021000000022'),('a010000001e00000001f','a0100000023000000024'),('a0100000061000000062','a0100000023000000024'),('a01000000d10000000d2','a0100000023000000024'),('a010000001e00000001f','a0100000050000000051'),('a01000000d10000000d2','a0100000050000000051'),('a010000001e00000001f','a0100000057000000058'),('a0100000061000000063','a0100000057000000058'),('a01000000d10000000d2','a0100000057000000058'),('a01000000d10000000d2','a01000000d20000000d3');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_view`
--

DROP TABLE IF EXISTS `user_view`;
/*!50001 DROP VIEW IF EXISTS `user_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_view` AS SELECT 
 1 AS `oid`,
 1 AS `salutation`,
 1 AS `firstName`,
 1 AS `lastName`,
 1 AS `fullName`,
 1 AS `designation`,
 1 AS `emailId`,
 1 AS `mobileNo`,
 1 AS `speciality`,
 1 AS `careProvider`,
 1 AS `userStatus`*/;
SET character_set_client = @saved_cs_client;

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
INSERT INTO `ward` VALUES ('a0100000007000000009','OG Ward','a010000000800000000a','ACT'),('a010000000700000000a','Sugery Ward','a010000000800000000a','ACT'),('a010000000700000000b','ICU','a010000000800000000a','ACT'),('a010000000700000000c','Sugery Ward 3','a010000000e00000000f','ACT'),('a010000000f000000010','Test Ward','a010000000e00000000f','ACT');
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `charges_category_view`
--

/*!50001 DROP VIEW IF EXISTS `charges_category_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `charges_category_view` AS select `ca`.`oid` AS `oid`,(case `ca`.`category` when 'Allied Charges' then (select `ac`.`allied_charges` from `allied_charges` `ac` where (`ac`.`oid` = `ca`.`sub_category_oid`)) when 'Procedures Charges' then (select `p`.`procedures_name` from `procedures` `p` where (`p`.`oid` = `ca`.`sub_category_oid`)) when 'Laboratory Charges' then 'Stock Adjustment' when 'Professional Charges' then 'Stock Adjustment' when 'Room Charges' then 'Stock Adjustment' else '' end) AS `subCategoryName` from `charges_for_category` `ca` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `doctor_schedule_view`
--

/*!50001 DROP VIEW IF EXISTS `doctor_schedule_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `doctor_schedule_view` AS select distinct `up`.`oid` AS `oid`,`up`.`salutation` AS `salutation`,concat(`up`.`first_name`,' ',`up`.`last_name`) AS `fullName`,`up`.`designation` AS `designation`,`sp`.`speciality_name` AS `speciality`,`up`.`care_provider` AS `careProvider`,`up`.`user_status` AS `userStatus`,`ds`.`visiting_day` AS `visitingDay`,`ds`.`start_time` AS `startTime`,`ds`.`end_time` AS `endTime` from ((`user_profile` `up` left join `speciality_details` `sp` on((`up`.`speciality` = `sp`.`oid`))) left join `doctor_schedule` `ds` on((`up`.`oid` = `ds`.`doctor_oid`))) where (`up`.`care_provider` = 'Yes') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_profile_view`
--

/*!50001 DROP VIEW IF EXISTS `user_profile_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_profile_view` AS select distinct `up`.`oid` AS `oid`,`up`.`salutation` AS `salutation`,`up`.`first_name` AS `firstName`,`up`.`last_name` AS `lastName`,concat(`up`.`first_name`,' ',`up`.`last_name`) AS `fullName`,`up`.`designation` AS `designation`,`up`.`email_id` AS `emailId`,`up`.`mobile_no` AS `mobileNo`,`lo`.`user_id` AS `userId`,`lo`.`password` AS `password`,`lo`.`password_exp_date` AS `passwordExpDate`,`lo`.`activation_date` AS `activationDate`,`up`.`care_provider` AS `careProvider`,`up`.`user_status` AS `userStatus`,(select distinct group_concat(`r`.`role_name` separator ',') AS `roles` from (`roles` `r` join `user_roles` `ur` on((`ur`.`role_oid` = `r`.`oid`))) where (`ur`.`user_oid` = `up`.`oid`)) AS `roles`,`up`.`speciality` AS `speciality`,(select `sd`.`speciality_name` from `speciality_details` `sd` where (`sd`.`oid` = `up`.`speciality`)) AS `specialityName`,`up`.`address` AS `address`,`up`.`country` AS `country`,`up`.`state` AS `state`,`up`.`city` AS `city`,`up`.`zip` AS `zip`,`lo`.`status` AS `loginStatus` from (`user_profile` `up` join `login` `lo` on((`up`.`oid` = `lo`.`user_oid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_view`
--

/*!50001 DROP VIEW IF EXISTS `user_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_view` AS select distinct `up`.`oid` AS `oid`,`up`.`salutation` AS `salutation`,`up`.`first_name` AS `firstName`,`up`.`last_name` AS `lastName`,concat(`up`.`first_name`,' ',`up`.`last_name`) AS `fullName`,`up`.`designation` AS `designation`,`up`.`email_id` AS `emailId`,`up`.`mobile_no` AS `mobileNo`,`sp`.`speciality_name` AS `speciality`,`up`.`care_provider` AS `careProvider`,`up`.`user_status` AS `userStatus` from (`user_profile` `up` left join `speciality_details` `sp` on((`up`.`speciality` = `sp`.`oid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-06 19:26:08
