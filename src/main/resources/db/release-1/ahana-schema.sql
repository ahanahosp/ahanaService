--
-- Table structure for table `lookup`
--
DROP TABLE IF EXISTS `lookup`;
CREATE TABLE `lookup` (
  `code` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `seq` bigint(20) DEFAULT '0',
  `deprecated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`code`,`category`),
  KEY `id_index` (`code`,`category`,`description`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `patient`
--
DROP TABLE IF EXISTS `patient`;
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

--
-- Table structure for table `roles`
--
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `oid` varchar(20) NOT NULL,
  `role_name` varchar(30) NOT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `seed_container`
--
DROP TABLE IF EXISTS `seed_container`;
CREATE TABLE `seed_container` (
  `high_oid` bigint(20) DEFAULT '0',
  `seed_id` varchar(3) DEFAULT NULL,
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `seq_id` (`seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `user_profile`
--
DROP TABLE IF EXISTS `user_profile`;
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

--
-- Table structure for table `user_roles`
--
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_oid` varchar(20) NOT NULL,
  `role_oid` varchar(20) NOT NULL,
  PRIMARY KEY (`user_oid`,`role_oid`),
  KEY `fk7342994993ad290f` (`role_oid`),
  CONSTRAINT `fk7342994993ad290f` FOREIGN KEY (`role_oid`) REFERENCES `roles` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `user_profile` (
  `oid` varchar(20) NOT NULL,
  `salutation` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50)  NOT NULL,
  `password` varchar(100) NOT NULL,
  `password_exp_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_id` varchar(100) NOT NULL,
  `user_status` varchar(5) NOT NULL,
  `address` varchar(100),  
  `country` varchar(50) ,
  `state` varchar(50),
  `city` varchar(50),
  `zip` varchar(10),
  `email_id` varchar(100) NOT NULL,
  `mobile_no` varchar(12) NOT NULL,
  `primary_contact` varchar(12) DEFAULT NULL,
  `secondary_contact` varchar(12) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `speciality` varchar(100) DEFAULT NULL,
  `care_provider` varchar(100) DEFAULT "FALSE",
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
