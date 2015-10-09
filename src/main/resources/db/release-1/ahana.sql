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
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `oid` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `blood_group` varchar(10) DEFAULT NULL,
  `care_taker_name` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `dob` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email_id` varchar(100) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `father_or_spouse_name` varchar(100) DEFAULT NULL,
  `marital_status` varchar(15) NOT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `patient_reference` varchar(15) DEFAULT NULL,
  `patient_type` varchar(50) NOT NULL,
  `permanent_address` varchar(100) DEFAULT NULL,
  `permanent_city` varchar(50) DEFAULT NULL,
  `permanent_country` varchar(50) DEFAULT NULL,
  `permanent_state` varchar(50) DEFAULT NULL,
  `permanent_zip` varchar(10) DEFAULT NULL,
  `primary_contact` varchar(12) DEFAULT NULL,
  `referal_doctor` varchar(100) DEFAULT NULL,
  `referal_hospital` varchar(100) DEFAULT NULL,
  `registration_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `registration_mode` varchar(10) DEFAULT NULL,
  `salutation` varchar(10) NOT NULL,
  `secondary_contact` varchar(12) DEFAULT NULL,
  `state` varchar(50) NOT NULL,
  `zip` varchar(10) NOT NULL,
  `care_taker_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
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
INSERT INTO `seed_container` VALUES (0,'a01',1,'REST'),(1,'PAT',2,'PAT');
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
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `last_logged_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(100) NOT NULL,
  `password_exp_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_id` varchar(100) NOT NULL,
  `user_status` varchar(5) NOT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  `account_expired` tinyint(1) NOT NULL DEFAULT '0',
  `account_locked` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`),
  UNIQUE KEY `user_profile_user_id_key` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-08 23:15:15
