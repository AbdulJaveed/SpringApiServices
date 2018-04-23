-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ems_new1
-- ------------------------------------------------------
-- Server version	5.5.25

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
-- Table structure for table `osi_addresses`
--

DROP TABLE IF EXISTS `osi_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_addresses` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(500) DEFAULT NULL,
  `address_line2` varchar(500) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `object_type` varchar(50) DEFAULT NULL,
  `object_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `IX_Relationship4` (`state_id`),
  CONSTRAINT `Relationship4` FOREIGN KEY (`state_id`) REFERENCES `osi_states` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_addresses`
--

LOCK TABLES `osi_addresses` WRITE;
/*!40000 ALTER TABLE `osi_addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_assignments`
--

DROP TABLE IF EXISTS `osi_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_assignments` (
  `version` int(11) DEFAULT '0' COMMENT 'for versioning',
  `assignment_id` int(11) NOT NULL AUTO_INCREMENT,
  `assignment_type` varchar(20) DEFAULT NULL,
  `effective_start_date` date DEFAULT NULL,
  `effective_end_date` date DEFAULT NULL,
  `contract_start_date` timestamp NULL DEFAULT NULL,
  `contract_end_date` timestamp NULL DEFAULT NULL,
  `is_manager` int(11) DEFAULT NULL,
  `supervisor_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `referred_by_id` int(11) DEFAULT NULL,
  `change_reason` varchar(200) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `on_probation` int(11) DEFAULT NULL,
  `probation_unit` varchar(50) DEFAULT NULL,
  `probation_unit_value` int(11) DEFAULT NULL,
  `probation_end_date` date DEFAULT NULL,
  `pay_grade_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `IX_Relationship19` (`grade_id`),
  KEY `IX_Relationship20` (`job_id`),
  KEY `IX_Relationship25` (`employee_id`),
  CONSTRAINT `Relationship20` FOREIGN KEY (`job_id`) REFERENCES `osi_job_codes` (`job_code_id`),
  CONSTRAINT `Relationship19` FOREIGN KEY (`grade_id`) REFERENCES `osi_grades` (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_assignments`
--

LOCK TABLES `osi_assignments` WRITE;
/*!40000 ALTER TABLE `osi_assignments` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_attachments`
--

DROP TABLE IF EXISTS `osi_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_attachments` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `original_file_name` varchar(100) DEFAULT NULL,
  `duplicate_file_name` varchar(100) DEFAULT NULL,
  `file_type` varchar(20) DEFAULT NULL,
  `file_content` varchar(100) DEFAULT NULL,
  `attachment_type` varchar(50) DEFAULT NULL,
  `object_type` varchar(50) DEFAULT NULL,
  `object_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`attachment_id`),
  KEY `IX_Relationship24` (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_attachments`
--

LOCK TABLES `osi_attachments` WRITE;
/*!40000 ALTER TABLE `osi_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_business_group`
--

DROP TABLE IF EXISTS `osi_business_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_business_group` (
  `BUSINESS_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_CODE` longtext NOT NULL,
  `BUSINESS_GROUP_NAME` longtext NOT NULL,
  `BUSINESS_GROUP_SHORT_NAME` longtext NOT NULL,
  `ADDRESS1` longtext NOT NULL,
  `ADDRESS2` longtext,
  `CITY` longtext NOT NULL,
  `PROVINCE` longtext NOT NULL,
  `STATE` longtext,
  `ZIPCODE` longtext,
  `COUNTRY` longtext,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `DATE_FROM` date DEFAULT NULL,
  `DATE_TO` date DEFAULT NULL,
  `DEFAULT_CURRENCY_CODE` longtext,
  PRIMARY KEY (`BUSINESS_GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_business_group`
--

LOCK TABLES `osi_business_group` WRITE;
/*!40000 ALTER TABLE `osi_business_group` DISABLE KEYS */;
INSERT INTO `osi_business_group` VALUES (1,'OSI101','OSI','OSI','Hyd',NULL,'Hyd','TG','TG','500083','India',1,NULL,NULL,NULL,NULL,NULL,'INR');
/*!40000 ALTER TABLE `osi_business_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_categories`
--

DROP TABLE IF EXISTS `osi_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(500) DEFAULT NULL,
  `table_name` varchar(50) NOT NULL,
  `org_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `IX_Relationship10` (`org_id`),
  CONSTRAINT `Relationship10` FOREIGN KEY (`org_id`) REFERENCES `osi_organizations` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_categories`
--

LOCK TABLES `osi_categories` WRITE;
/*!40000 ALTER TABLE `osi_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_category_fields`
--

DROP TABLE IF EXISTS `osi_category_fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_category_fields` (
  `category_field_id` int(11) NOT NULL AUTO_INCREMENT,
  `column_name` varchar(500) DEFAULT NULL,
  `column_value` varchar(100) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `column_type` varchar(50) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `column_source_type` varchar(20) DEFAULT NULL,
  `column_source` varchar(50) DEFAULT NULL,
  `is_mandatory` int(11) DEFAULT NULL,
  `javascript_validation` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`category_field_id`),
  KEY `IX_Relationship8` (`category_id`),
  CONSTRAINT `Relationship8` FOREIGN KEY (`category_id`) REFERENCES `osi_categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_category_fields`
--

LOCK TABLES `osi_category_fields` WRITE;
/*!40000 ALTER TABLE `osi_category_fields` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_category_fields` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_certifications`
--

DROP TABLE IF EXISTS `osi_certifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_certifications` (
  `certification_id` int(11) NOT NULL AUTO_INCREMENT,
  `certification_name` varchar(100) DEFAULT NULL,
  `certification_code` varchar(50) DEFAULT NULL,
  `certification_add_info` varchar(100) DEFAULT NULL,
  `issued_by` varchar(100) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`certification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_certifications`
--

LOCK TABLES `osi_certifications` WRITE;
/*!40000 ALTER TABLE `osi_certifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_certifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_contacts`
--

DROP TABLE IF EXISTS `osi_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_contacts` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `contact_name` varchar(100) DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `country_code` varchar(20) DEFAULT NULL,
  `contact_type` varchar(20) DEFAULT NULL,
  `relation` varchar(20) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `IX_Relationship27` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_contacts`
--

LOCK TABLES `osi_contacts` WRITE;
/*!40000 ALTER TABLE `osi_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_countries`
--

DROP TABLE IF EXISTS `osi_countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_countries` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(500) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_countries`
--

LOCK TABLES `osi_countries` WRITE;
/*!40000 ALTER TABLE `osi_countries` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_country_visas`
--

DROP TABLE IF EXISTS `osi_country_visas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_country_visas` (
  `country_visa_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) DEFAULT NULL,
  `visa_type` varchar(50) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`country_visa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_country_visas`
--

LOCK TABLES `osi_country_visas` WRITE;
/*!40000 ALTER TABLE `osi_country_visas` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_country_visas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_currencies`
--

DROP TABLE IF EXISTS `osi_currencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_currencies` (
  `currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `currency_name` varchar(100) DEFAULT NULL,
  `currency_code` varchar(20) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`currency_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_currencies`
--

LOCK TABLES `osi_currencies` WRITE;
/*!40000 ALTER TABLE `osi_currencies` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_emp_bank_details`
--

DROP TABLE IF EXISTS `osi_emp_bank_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_emp_bank_details` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_holder_name` varchar(100) DEFAULT NULL,
  `branch_name` varchar(100) DEFAULT NULL,
  `ifsc_code` varchar(100) DEFAULT NULL,
  `account_number` varchar(100) DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_emp_bank_details`
--

LOCK TABLES `osi_emp_bank_details` WRITE;
/*!40000 ALTER TABLE `osi_emp_bank_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_emp_bank_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_emp_certifications`
--

DROP TABLE IF EXISTS `osi_emp_certifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_emp_certifications` (
  `emp_certification_id` int(11) NOT NULL AUTO_INCREMENT,
  `certification_id` int(11) DEFAULT NULL,
  `year_of_passing` int(11) DEFAULT NULL,
  `gpa_percentage` decimal(10,2) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `attachment_id` int(11) DEFAULT NULL,
  `is_verified` int(11) DEFAULT '0',
  `verified_by` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  UNIQUE KEY `card__type_id` (`emp_certification_id`),
  KEY `IX_Relationship15` (`certification_id`),
  KEY `IX_Relationship23` (`employee_id`),
  KEY `IX_Relationship31` (`attachment_id`),
  CONSTRAINT `Relationship15` FOREIGN KEY (`certification_id`) REFERENCES `osi_certifications` (`certification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_emp_certifications`
--

LOCK TABLES `osi_emp_certifications` WRITE;
/*!40000 ALTER TABLE `osi_emp_certifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_emp_certifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_emp_skills`
--

DROP TABLE IF EXISTS `osi_emp_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_emp_skills` (
  `emp_skill_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_id` int(11) DEFAULT NULL,
  `proficiency` varchar(20) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `last_used_date` date DEFAULT NULL,
  `years_of_exp` int(11) DEFAULT NULL,
  `months_of_exp` int(11) DEFAULT NULL,
  `is_verified` int(11) DEFAULT '0',
  `verified_by` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`emp_skill_id`),
  KEY `IX_Relationship17` (`skill_id`),
  CONSTRAINT `Relationship17` FOREIGN KEY (`skill_id`) REFERENCES `osi_skils` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_emp_skills`
--

LOCK TABLES `osi_emp_skills` WRITE;
/*!40000 ALTER TABLE `osi_emp_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_emp_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_emp_visa_details`
--

DROP TABLE IF EXISTS `osi_emp_visa_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_emp_visa_details` (
  `visa_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL,
  `visa_number` varchar(100) DEFAULT NULL,
  `date_of_issue` date DEFAULT NULL,
  `date_of_expiry` date DEFAULT NULL,
  `issuance_authority` varchar(100) DEFAULT NULL,
  `place_of_issue` varchar(100) DEFAULT NULL,
  `visa_type` varchar(20) DEFAULT NULL,
  `country_of_visa` varchar(50) DEFAULT NULL,
  `single_multiple` varchar(20) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`visa_id`),
  KEY `IX_Relationship30` (`visa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_emp_visa_details`
--

LOCK TABLES `osi_emp_visa_details` WRITE;
/*!40000 ALTER TABLE `osi_emp_visa_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_emp_visa_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_employees`
--

DROP TABLE IF EXISTS `osi_employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_employees` (
  `employee_id` int(11) DEFAULT NULL,
  `employee_number` varchar(20) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `middle_name` varchar(100) DEFAULT NULL,
  `full_name` varchar(200) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `suffix` varchar(20) DEFAULT NULL,
  `prefix` varchar(20) DEFAULT NULL,
  `employee_type` varchar(20) DEFAULT NULL,
  `applicant_number` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `effective_start_date` timestamp NULL DEFAULT NULL,
  `effective_end_date` timestamp NULL DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `blood_type` varchar(20) DEFAULT NULL,
  `background_check_status` int(11) DEFAULT NULL,
  `background_date_check` date DEFAULT NULL,
  `correspondence_language` varchar(20) DEFAULT 'English' COMMENT 'Default to English',
  `office_email` varchar(50) DEFAULT NULL,
  `personal_email` varchar(100) DEFAULT NULL,
  `expense_check_send_to_address_id` int(11) DEFAULT NULL COMMENT 'Default to mail address',
  `fte_capacity` int(11) DEFAULT NULL COMMENT 'Full–time employment capacity',
  `hold_applicant_date_until` date DEFAULT NULL,
  `mail_stop` varchar(100) DEFAULT NULL,
  `known_as` varchar(100) DEFAULT NULL,
  `last_medical_test_date` date DEFAULT NULL,
  `last_medical_test_by` varchar(100) DEFAULT NULL,
  `nationality` varchar(100) DEFAULT NULL,
  `marital_status` varchar(50) DEFAULT NULL,
  `national_identifier` varchar(100) DEFAULT NULL,
  `on_military_service` int(11) DEFAULT NULL,
  `previous_last_name` varchar(100) DEFAULT NULL,
  `rehire_reason` varchar(100) DEFAULT NULL,
  `rehire_recommendation` int(11) DEFAULT NULL,
  `resume_exists` int(11) DEFAULT NULL,
  `resume_last_updated` timestamp NULL DEFAULT NULL,
  `resume_id` int(11) DEFAULT NULL COMMENT 'from attachment table',
  `second_passport_exists` int(11) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `work_schedule_id` int(11) DEFAULT NULL,
  `receipt_of_death_cert_date` date DEFAULT NULL,
  `uses_tobacco_flag` int(11) DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL,
  `date_of_death` date DEFAULT NULL,
  `original_date_of_hire` date DEFAULT NULL,
  `passport_number` varchar(50) DEFAULT NULL,
  `passport_date_of_issue` date DEFAULT NULL,
  `passport_date_of_expiry` date DEFAULT NULL,
  `passport_issuance_authority` varchar(200) DEFAULT NULL,
  `passport_place_of_issue` varchar(100) DEFAULT NULL,
  `mail_address_id` int(11) DEFAULT NULL,
  `permanent_address_id` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0' COMMENT 'for versioning',
  `Attribute1` varchar(100) DEFAULT NULL,
  `Attribute2` varchar(100) DEFAULT NULL,
  `Attribute3` varchar(100) DEFAULT NULL,
  `Attribute4` varchar(100) DEFAULT NULL,
  `Attribute5` varchar(100) DEFAULT NULL,
  `Attribute6` varchar(100) DEFAULT NULL,
  `Attribute7` varchar(100) DEFAULT NULL,
  `Attribute8` varchar(100) DEFAULT NULL,
  `Attribute9` varchar(100) DEFAULT NULL,
  `Attribute10` varchar(100) DEFAULT NULL,
  `Attribute11` varchar(100) DEFAULT NULL,
  `Attribute12` varchar(100) DEFAULT NULL,
  `Attribute13` varchar(100) DEFAULT NULL,
  `Attribute14` varchar(100) DEFAULT NULL,
  `Attribute15` varchar(100) DEFAULT NULL,
  `Attribute16` varchar(100) DEFAULT NULL,
  `Attribute17` varchar(100) DEFAULT NULL,
  `Attribute18` varchar(100) DEFAULT NULL,
  `Attribute19` varchar(100) DEFAULT NULL,
  `Attribute20` varchar(100) DEFAULT NULL,
  `Attribute21` varchar(100) DEFAULT NULL,
  `Attribute22` varchar(100) DEFAULT NULL,
  `Attribute23` varchar(100) DEFAULT NULL,
  `Attribute24` varchar(100) DEFAULT NULL,
  `Attribute25` varchar(100) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  `attachment_id` int(11) DEFAULT NULL,
  KEY `IX_Relationship12` (`org_id`),
  KEY `IX_Relationship33` (`attachment_id`),
  CONSTRAINT `Relationship12` FOREIGN KEY (`org_id`) REFERENCES `osi_organizations` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_employees`
--

LOCK TABLES `osi_employees` WRITE;
/*!40000 ALTER TABLE `osi_employees` DISABLE KEYS */;
INSERT INTO `osi_employees` VALUES (1,'NS0560','Raju','Donepudi','','Raju Donepudi','Mr',NULL,NULL,'Employee',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'English','rdonepudi@osius.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `osi_employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_func_operations`
--

DROP TABLE IF EXISTS `osi_func_operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_func_operations` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `FUNC_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `OP_ID` int(11) NOT NULL,
  `OP_URL` longtext,
  `START_DATE` datetime DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_func_operations`
--

LOCK TABLES `osi_func_operations` WRITE;
/*!40000 ALTER TABLE `osi_func_operations` DISABLE KEYS */;
INSERT INTO `osi_func_operations` VALUES (1,1,1,1,NULL,NULL,NULL,1,'/create','2017-09-12 00:00:00','2019-09-12 00:00:00'),(2,1,1,1,NULL,NULL,NULL,2,'/edit','2017-09-12 00:00:00','2019-09-12 00:00:00'),(3,1,1,1,NULL,NULL,NULL,3,'/delete','2017-09-12 00:00:00','2019-09-12 00:00:00'),(4,1,2,1,NULL,NULL,NULL,1,'/create','2017-09-12 00:00:00','2019-09-12 00:00:00'),(5,1,2,1,NULL,NULL,NULL,2,'/edit','2017-09-12 00:00:00','2019-09-12 00:00:00'),(6,1,2,1,NULL,NULL,NULL,3,'/delete','2017-09-12 00:00:00','2019-09-12 00:00:00'),(7,1,3,1,NULL,NULL,NULL,1,'/create','2017-09-12 00:00:00','2019-09-12 00:00:00'),(8,1,3,1,NULL,NULL,NULL,2,'/edit','2017-09-12 00:00:00','2019-09-12 00:00:00'),(9,1,3,1,NULL,NULL,NULL,3,'/delete','2017-09-12 00:00:00','2019-09-12 00:00:00'),(10,1,4,1,NULL,NULL,NULL,1,'/create','2017-09-12 00:00:00','2019-09-12 00:00:00'),(11,1,4,1,NULL,NULL,NULL,2,'/edit','2017-09-12 00:00:00','2019-09-12 00:00:00'),(12,1,4,1,NULL,NULL,NULL,3,'/delete','2017-09-12 00:00:00','2019-09-12 00:00:00'),(13,1,5,1,NULL,NULL,NULL,1,'/create','2017-09-12 00:00:00','2019-09-12 00:00:00'),(14,1,5,1,NULL,NULL,NULL,2,'/edit','2017-09-12 00:00:00','2019-09-12 00:00:00'),(15,1,5,1,NULL,NULL,NULL,3,'/delete','2017-09-12 00:00:00','2019-09-12 00:00:00'),(16,1,7,1,NULL,NULL,NULL,1,'/create','2017-09-12 00:00:00','2019-09-12 00:00:00'),(17,1,7,1,NULL,NULL,NULL,2,'/edit','2017-09-12 00:00:00','2019-09-12 00:00:00'),(18,1,7,1,NULL,NULL,NULL,3,'/delete','2017-09-12 00:00:00','2019-09-12 00:00:00'),(83,1,8,1,'2017-09-20 12:22:43',NULL,NULL,2,'/edit','2017-09-20 00:00:00','2100-12-31 00:00:00'),(84,1,8,1,'2017-09-20 12:22:43',NULL,NULL,3,'/view','2017-09-20 00:00:00','2100-12-31 00:00:00'),(85,1,8,1,'2017-09-20 12:22:43',NULL,NULL,1,'/create','2017-09-20 00:00:00','2100-12-31 00:00:00'),(87,1,9,1,'2017-09-20 12:23:35',NULL,NULL,1,'/create','2017-09-20 00:00:00','2100-12-31 00:00:00'),(88,1,9,1,'2017-09-20 12:23:35',NULL,NULL,2,'/edit','2017-09-20 00:00:00','2100-12-31 00:00:00'),(89,1,9,1,'2017-09-20 12:23:35',NULL,NULL,3,'/view','2017-09-20 00:00:00','2100-12-31 00:00:00');
/*!40000 ALTER TABLE `osi_func_operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_functions`
--

DROP TABLE IF EXISTS `osi_functions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_functions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `FUNC_TYPE` longtext,
  `FUNC_NAME` longtext,
  `FUNC_VALUE` longtext,
  `PARAMETERS` longtext,
  `ACTIVE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_functions`
--

LOCK TABLES `osi_functions` WRITE;
/*!40000 ALTER TABLE `osi_functions` DISABLE KEYS */;
INSERT INTO `osi_functions` VALUES (1,1,1,NULL,1,'2017-09-15 15:35:07','URL','Menu','/menu',NULL,1),(2,1,1,NULL,NULL,NULL,'URL','Menu Entries','/menuEntries',NULL,1),(3,1,1,NULL,NULL,NULL,'URL','Function','/function',NULL,1),(4,1,1,NULL,1,'2017-09-15 15:15:48','URL','Responsibility','/osiResponsibilitiesList',NULL,1),(5,1,1,NULL,1,'2017-09-12 16:24:32','URL','Users','/users',NULL,1),(6,1,1,NULL,NULL,NULL,'URL','Operation','/operation',NULL,1),(7,1,1,NULL,NULL,NULL,'URL','Employee','/employee',NULL,1),(8,1,1,'2017-09-20 12:22:42',1,'2017-09-20 12:22:42','URL','Lookup','/lookup',NULL,1),(9,1,1,'2017-09-20 12:23:34',1,'2017-09-20 12:23:34','URL','Segment Hierarchy','/segmenthirarchy',NULL,1);
/*!40000 ALTER TABLE `osi_functions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_grades`
--

DROP TABLE IF EXISTS `osi_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_grades` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(20) DEFAULT NULL,
  `grade_description` varchar(200) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  KEY `IX_Relationship7` (`org_id`),
  CONSTRAINT `Relationship7` FOREIGN KEY (`org_id`) REFERENCES `osi_organizations` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_grades`
--

LOCK TABLES `osi_grades` WRITE;
/*!40000 ALTER TABLE `osi_grades` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_job_codes`
--

DROP TABLE IF EXISTS `osi_job_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_job_codes` (
  `job_code_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_code_name` varchar(20) DEFAULT NULL,
  `job_code_description` varchar(500) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`job_code_id`),
  KEY `IX_Relationship9` (`org_id`),
  CONSTRAINT `Relationship9` FOREIGN KEY (`org_id`) REFERENCES `osi_organizations` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_job_codes`
--

LOCK TABLES `osi_job_codes` WRITE;
/*!40000 ALTER TABLE `osi_job_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_job_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_key_flex`
--

DROP TABLE IF EXISTS `osi_key_flex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_key_flex` (
  `KEY_FLEX_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `VALUE` varchar(50) DEFAULT NULL,
  `BUSINESS_GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`KEY_FLEX_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_key_flex`
--

LOCK TABLES `osi_key_flex` WRITE;
/*!40000 ALTER TABLE `osi_key_flex` DISABLE KEYS */;
INSERT INTO `osi_key_flex` VALUES (1,'Department Structure - OSI Consulting Pvt Ltd','Department Structure - OSI Consulting Pvt Ltd',1);
/*!40000 ALTER TABLE `osi_key_flex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_locations`
--

DROP TABLE IF EXISTS `osi_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_locations` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_name` varchar(100) DEFAULT NULL,
  `is_primary` int(11) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `IX_Relationship2` (`address_id`),
  CONSTRAINT `Relationship2` FOREIGN KEY (`address_id`) REFERENCES `osi_addresses` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_locations`
--

LOCK TABLES `osi_locations` WRITE;
/*!40000 ALTER TABLE `osi_locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_lookup_types`
--

DROP TABLE IF EXISTS `osi_lookup_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_lookup_types` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOOKUP_NAME` longtext,
  `LOOKUP_CODE` longtext NOT NULL,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_lookup_types`
--

LOCK TABLES `osi_lookup_types` WRITE;
/*!40000 ALTER TABLE `osi_lookup_types` DISABLE KEYS */;
INSERT INTO `osi_lookup_types` VALUES (1,'Business Unit','BU',1,1,'2017-09-20 12:31:55',1,'2017-09-20 12:31:55',1),(2,'Division','DIVISION',1,1,'2017-09-20 12:32:38',1,'2017-09-20 12:32:38',1),(3,'Practice','PRACTICE',1,1,'2017-09-20 12:33:38',1,'2017-09-20 12:33:38',1);
/*!40000 ALTER TABLE `osi_lookup_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_lookup_values`
--

DROP TABLE IF EXISTS `osi_lookup_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_lookup_values` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOOKUP_VALUE` longtext NOT NULL,
  `LOOKUP_DESC` longtext,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `LOOKUP_TYPE_ID` int(11) NOT NULL,
  `LOOKUP_SEQ_NUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_lookup_values`
--

LOCK TABLES `osi_lookup_values` WRITE;
/*!40000 ALTER TABLE `osi_lookup_values` DISABLE KEYS */;
INSERT INTO `osi_lookup_values` VALUES (1,'EA','EA',1,NULL,NULL,NULL,NULL,1,2),(2,'ET','ET',1,NULL,NULL,NULL,NULL,1,1),(3,'OPS','Ops',1,NULL,NULL,NULL,NULL,1,3),(4,'DIV2','Division2',1,NULL,NULL,NULL,NULL,2,2),(5,'DIV1','Division1',1,NULL,NULL,NULL,NULL,2,1),(6,'Infra','Infra',1,NULL,NULL,NULL,NULL,3,2),(7,'Engineering','Engineering',1,NULL,NULL,NULL,NULL,3,1);
/*!40000 ALTER TABLE `osi_lookup_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_menu_entries`
--

DROP TABLE IF EXISTS `osi_menu_entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_menu_entries` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SEQ` int(11) DEFAULT NULL,
  `PROMPT` longtext,
  `MENU_ID` int(11) DEFAULT NULL,
  `SUB_MENU_ID` int(11) DEFAULT NULL,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `FUNC_ID` int(11) DEFAULT NULL,
  `ACTIVE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_menu_entries`
--

LOCK TABLES `osi_menu_entries` WRITE;
/*!40000 ALTER TABLE `osi_menu_entries` DISABLE KEYS */;
INSERT INTO `osi_menu_entries` VALUES (1,10,'Administration',NULL,1,1,1,NULL,NULL,NULL,'2017-09-12 00:00:00','2019-09-12 00:00:00',NULL,1),(2,11,'Menu',1,NULL,1,1,NULL,NULL,NULL,'2017-09-12 00:00:00','2019-09-12 00:00:00',1,1),(3,12,'Menu Entries',1,NULL,1,1,NULL,NULL,NULL,'2017-09-12 00:00:00','2019-09-12 00:00:00',2,1),(4,13,'Function',1,NULL,1,1,NULL,NULL,NULL,'2017-09-12 00:00:00','2019-09-12 00:00:00',3,1),(5,14,'Responsibility',1,NULL,1,1,'2017-09-12 16:19:14',1,'2017-09-12 16:19:57','2017-09-12 00:00:00','2017-09-12 00:00:00',4,1),(6,15,'Users',1,NULL,1,1,'2017-09-12 16:19:14',1,'2017-11-03 16:48:49','2017-09-12 00:00:00','2017-11-03 00:00:00',5,0),(7,18,'Employee',1,NULL,1,1,'2017-09-12 18:30:34',1,'2017-11-03 16:48:45','2017-09-12 00:00:00','2017-11-03 00:00:00',7,0),(8,16,'Lookup',1,NULL,1,1,'2017-09-20 12:24:31',1,'2017-09-20 12:24:31','2017-09-20 00:00:00','2017-09-20 00:00:00',8,1),(9,17,'Segment Hierarchy',1,NULL,1,1,'2017-09-20 12:24:32',1,'2017-09-20 12:24:32','2017-09-20 00:00:00','2017-09-20 00:00:00',9,1);
/*!40000 ALTER TABLE `osi_menu_entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_menu_resp`
--

DROP TABLE IF EXISTS `osi_menu_resp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_menu_resp` (
  `MENU_RESP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RESP_ID` int(11) DEFAULT NULL,
  `MENU_ID` int(11) DEFAULT NULL,
  `BUSINESS_GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`MENU_RESP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_menu_resp`
--

LOCK TABLES `osi_menu_resp` WRITE;
/*!40000 ALTER TABLE `osi_menu_resp` DISABLE KEYS */;
INSERT INTO `osi_menu_resp` VALUES (2,1,1,1);
/*!40000 ALTER TABLE `osi_menu_resp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_menus`
--

DROP TABLE IF EXISTS `osi_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_menus` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_NAME` longtext,
  `DESCRIPTION` longtext,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `ACTIVE` int(11) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `RPT_GRP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_menus`
--

LOCK TABLES `osi_menus` WRITE;
/*!40000 ALTER TABLE `osi_menus` DISABLE KEYS */;
INSERT INTO `osi_menus` VALUES (1,'Administration','Administration',1,1,1,NULL,1,'2017-09-15 18:27:15',1);
/*!40000 ALTER TABLE `osi_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_operations`
--

DROP TABLE IF EXISTS `osi_operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_operations` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `NAME` longtext NOT NULL,
  `DESCRIPTION` longtext,
  `ACTIVE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_operations`
--

LOCK TABLES `osi_operations` WRITE;
/*!40000 ALTER TABLE `osi_operations` DISABLE KEYS */;
INSERT INTO `osi_operations` VALUES (1,1,1,NULL,NULL,NULL,'Create','Create',1),(2,1,1,NULL,NULL,NULL,'Edit','Edit',1),(3,1,1,NULL,NULL,NULL,'View','View',1),(4,1,1,NULL,NULL,NULL,'Delete','Delete',1);
/*!40000 ALTER TABLE `osi_operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_organizations`
--

DROP TABLE IF EXISTS `osi_organizations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_organizations` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(100) DEFAULT NULL,
  `org_short_name` varchar(20) DEFAULT NULL,
  `website` varchar(20) DEFAULT NULL,
  `fax_number` varchar(100) DEFAULT NULL,
  `country_code` varchar(45) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `parent_org_id` int(11) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `contact_person_id` int(11) DEFAULT NULL,
  `base_currency_id` int(11) DEFAULT NULL,
  `reporting_currency_id` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`org_id`),
  KEY `IX_Relationship5` (`base_currency_id`),
  KEY `IX_Relationship6` (`reporting_currency_id`),
  CONSTRAINT `Relationship5` FOREIGN KEY (`base_currency_id`) REFERENCES `osi_currencies` (`currency_id`),
  CONSTRAINT `Relationship6` FOREIGN KEY (`reporting_currency_id`) REFERENCES `osi_currencies` (`currency_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_organizations`
--

LOCK TABLES `osi_organizations` WRITE;
/*!40000 ALTER TABLE `osi_organizations` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_organizations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_reports`
--

DROP TABLE IF EXISTS `osi_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_reports` (
  `REPORT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REPORT_NAME` varchar(40) DEFAULT NULL,
  `USER_REPORT_NAME` varchar(500) DEFAULT NULL,
  `SRC_FILE_NAME` varchar(500) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATION_DATE` date DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATE_DATE` date DEFAULT NULL,
  `REPORT_TYPE` varchar(50) DEFAULT NULL,
  `OUTPUT_TYPE` varchar(20) DEFAULT NULL,
  `BUSINESS_GROUP_ID` int(11) DEFAULT NULL,
  `LOCK_ON_REPORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`REPORT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_reports`
--

LOCK TABLES `osi_reports` WRITE;
/*!40000 ALTER TABLE `osi_reports` DISABLE KEYS */;
INSERT INTO `osi_reports` VALUES (1,'Users','Users','user.jasper',1,NULL,NULL,NULL,'JASPER','PDF',1,1);
/*!40000 ALTER TABLE `osi_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_resp_user`
--

DROP TABLE IF EXISTS `osi_resp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_resp_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEFAULT_RESP` int(11) DEFAULT NULL,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `RESP_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_resp_user`
--

LOCK TABLES `osi_resp_user` WRITE;
/*!40000 ALTER TABLE `osi_resp_user` DISABLE KEYS */;
INSERT INTO `osi_resp_user` VALUES (1,1,1,1,NULL,NULL,NULL,'2017-09-12 00:00:00','2019-09-12 00:00:00',1,1);
/*!40000 ALTER TABLE `osi_resp_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_responsibilities`
--

DROP TABLE IF EXISTS `osi_responsibilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_responsibilities` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RESP_NAME` longtext,
  `DESCRIPTION` longtext,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `ACTIVE` int(11) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `MENU_ID` int(11) DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `RPT_GRP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_responsibilities`
--

LOCK TABLES `osi_responsibilities` WRITE;
/*!40000 ALTER TABLE `osi_responsibilities` DISABLE KEYS */;
INSERT INTO `osi_responsibilities` VALUES (1,'Administration','Administration',1,1,1,NULL,1,'2017-09-15 18:27:21',1,'2017-09-12 00:00:00','2019-09-12 00:00:00',NULL);
/*!40000 ALTER TABLE `osi_responsibilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_rpt_groups`
--

DROP TABLE IF EXISTS `osi_rpt_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_rpt_groups` (
  `RPT_GRP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTIVE` int(11) DEFAULT NULL,
  `RPT_GRP_NAME` longtext,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATION_DATE` date DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATE_DATE` date DEFAULT NULL,
  `BUSINESS_GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RPT_GRP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_rpt_groups`
--

LOCK TABLES `osi_rpt_groups` WRITE;
/*!40000 ALTER TABLE `osi_rpt_groups` DISABLE KEYS */;
INSERT INTO `osi_rpt_groups` VALUES (1,1,'Administration',1,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `osi_rpt_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_rpt_grp_rpts`
--

DROP TABLE IF EXISTS `osi_rpt_grp_rpts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_rpt_grp_rpts` (
  `RPT_GRP_RPTS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REPORT_ID` int(11) DEFAULT NULL,
  `RPT_GRP_ID` int(11) DEFAULT NULL,
  `BUSINESS_GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RPT_GRP_RPTS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_rpt_grp_rpts`
--

LOCK TABLES `osi_rpt_grp_rpts` WRITE;
/*!40000 ALTER TABLE `osi_rpt_grp_rpts` DISABLE KEYS */;
INSERT INTO `osi_rpt_grp_rpts` VALUES (1,1,1,1);
/*!40000 ALTER TABLE `osi_rpt_grp_rpts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_segment_structure_details`
--

DROP TABLE IF EXISTS `osi_segment_structure_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_segment_structure_details` (
  `SEGMENT_STRUCTURE_DETAILS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SEGMENT_STRUCTURE_DETAILS_DESC` longtext NOT NULL,
  `SEGMENT_STRUCTURE_DETAILS_SEQ` int(11) NOT NULL,
  `SEGMENT_STRUCTURE_HDR_ID` int(11) NOT NULL,
  `DEP_SEGMENT_STRUCTURE_DETAILS_ID` int(11) DEFAULT NULL,
  `IS_SQL_REQD_FOR_VALIDATION` int(11) DEFAULT NULL,
  `LOV_DATA_FOR_VALIDATION` longtext,
  `SQL_QUERY_FOR_VALIDATION` longtext,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  PRIMARY KEY (`SEGMENT_STRUCTURE_DETAILS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_segment_structure_details`
--

LOCK TABLES `osi_segment_structure_details` WRITE;
/*!40000 ALTER TABLE `osi_segment_structure_details` DISABLE KEYS */;
INSERT INTO `osi_segment_structure_details` VALUES (4,'Division',2,1,NULL,NULL,'DIVISION',NULL,1),(5,'Practice',3,1,NULL,NULL,'PRACTICE',NULL,1),(6,'Business Unit',1,1,NULL,NULL,'BU',NULL,1);
/*!40000 ALTER TABLE `osi_segment_structure_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_segment_structure_hdr`
--

DROP TABLE IF EXISTS `osi_segment_structure_hdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_segment_structure_hdr` (
  `SEGMENT_STRUCTURE_HDR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SEGMENT_STRUCTURE_HDR_DESC` longtext NOT NULL,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `ACTIVE` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`SEGMENT_STRUCTURE_HDR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_segment_structure_hdr`
--

LOCK TABLES `osi_segment_structure_hdr` WRITE;
/*!40000 ALTER TABLE `osi_segment_structure_hdr` DISABLE KEYS */;
INSERT INTO `osi_segment_structure_hdr` VALUES (1,'Department Structure - OSI Consulting Pvt Ltd',1,1,1,'2017-09-20 12:36:24',1,'2017-09-20 12:37:56');
/*!40000 ALTER TABLE `osi_segment_structure_hdr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_skils`
--

DROP TABLE IF EXISTS `osi_skils`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_skils` (
  `skill_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(100) DEFAULT NULL,
  `skill_display_name` varchar(100) DEFAULT NULL,
  `skill_description` varchar(500) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_skils`
--

LOCK TABLES `osi_skils` WRITE;
/*!40000 ALTER TABLE `osi_skils` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_skils` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_states`
--

DROP TABLE IF EXISTS `osi_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_states` (
  `state_id` int(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(500) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`state_id`),
  KEY `IX_Relationship3` (`country_id`),
  CONSTRAINT `Relationship3` FOREIGN KEY (`country_id`) REFERENCES `osi_countries` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_states`
--

LOCK TABLES `osi_states` WRITE;
/*!40000 ALTER TABLE `osi_states` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_user`
--

DROP TABLE IF EXISTS `osi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` int(11) NOT NULL,
  `USER_NAME` longtext NOT NULL,
  `PASSWORD` longtext NOT NULL,
  `FIRST_NAME` longtext,
  `LAST_NAME` longtext,
  `FULL_NAME` longtext,
  `EMAIL_ID` longtext NOT NULL,
  `MOBILE_NUMBER` longtext,
  `EMP_NUMBER` longtext,
  `START_DATE` datetime DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `HAS_DEFAULT_PWD` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_user`
--

LOCK TABLES `osi_user` WRITE;
/*!40000 ALTER TABLE `osi_user` DISABLE KEYS */;
INSERT INTO `osi_user` VALUES (1,1,1,NULL,NULL,NULL,1,'rdonepudi','cmRvbmVwdWRp','Raju','D',NULL,'rdonepudi@osius.com','8297251955','NS0560','2017-09-12 00:00:00','2019-09-12 00:00:00',0);
/*!40000 ALTER TABLE `osi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_user_func_excl`
--

DROP TABLE IF EXISTS `osi_user_func_excl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_user_func_excl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `FUNC_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_user_func_excl`
--

LOCK TABLES `osi_user_func_excl` WRITE;
/*!40000 ALTER TABLE `osi_user_func_excl` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_user_func_excl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_user_logins`
--

DROP TABLE IF EXISTS `osi_user_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_user_logins` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `LOGIN_TYPE` longtext,
  `TOKEN` longtext NOT NULL,
  `TOKEN_EXP_TIME` longtext NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `PID` int(11) DEFAULT NULL,
  `BROWSER_TAB_ID` decimal(18,4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_user_logins`
--

LOCK TABLES `osi_user_logins` WRITE;
/*!40000 ALTER TABLE `osi_user_logins` DISABLE KEYS */;
INSERT INTO `osi_user_logins` VALUES (1,1,1,NULL,'Y21SdmJtVndkV1JwTURjek1EWTJaR1l0TnpZNVpDMDBaV1EzTFRnM01Ea3RabVJoWTJZd016SmxZMk13','1800','2017-09-12 15:47:28','2017-11-03 00:00:00',NULL,NULL),(2,1,1,NULL,'Y21SdmJtVndkV1JwWTJOa05XSTFZbVF0TURkaE55MDBZV0V6TFRsa09Ea3RObUZpWldFMk1qazFNemRo','1800','2017-09-12 15:48:19','2017-11-03 00:00:00',NULL,NULL),(3,1,1,NULL,'Y21SdmJtVndkV1JwWkdRek5qTmtOemN0TlRZNU5TMDBNR0poTFRnNVpERXRPV1l4TURBME5EUmtNRGt5','1800','2017-09-12 15:53:28','2017-11-03 00:00:00',NULL,NULL),(4,1,1,NULL,'Y21SdmJtVndkV1JwTlRRM05ESXpOVFV0TkdSbE9DMDBOVEpqTFRrM1pEQXRNbUU0TmpWaU5XUTRaalV5','1800','2017-09-12 15:59:52','2017-11-03 00:00:00',NULL,NULL),(5,1,1,NULL,'Y21SdmJtVndkV1JwWmpCbU0yTmtNalV0T1dJeE1pMDBaRFF6TFdJNVlqa3RaamhsTnpkak1tTmlaRGho','1800','2017-09-12 16:00:18','2017-11-03 00:00:00',NULL,NULL),(6,1,1,NULL,'Y21SdmJtVndkV1JwTW1WbVpHRXlZbU10WkRBMk55MDBORGN4TFRrMk1EWXRNVFUzWmpkbU1UUTFZMlV4','1800','2017-09-12 16:04:34','2017-11-03 00:00:00',NULL,NULL),(7,1,1,NULL,'Y21SdmJtVndkV1JwTUdSbVltWTBORFl0TnpGa1lTMDBaVEJrTFRoa01qRXRNRGRoWWpWbE1qRTFNVGRs','1800','2017-09-12 16:05:43','2017-11-03 00:00:00',NULL,NULL),(8,1,1,NULL,'Y21SdmJtVndkV1JwTlRsa05XUTVZemN0WVdNMU1pMDBOMkl5TFdJNE16WXRaVE5rTlRWaE16WmhZakZp','1800','2017-09-12 16:11:41','2017-11-03 00:00:00',NULL,NULL),(9,1,1,NULL,'Y21SdmJtVndkV1JwWXpNNU9XRmpZall0WkRsak1TMDBZakE0TFRnM1l6QXRaRE5tTkdFME9UQTBaakJo','1800','2017-09-12 16:18:32','2017-11-03 00:00:00',NULL,NULL),(10,1,1,NULL,'Y21SdmJtVndkV1JwTkRCa1ptVmpNek10TXpkbE1DMDBZalV6TFdFMVpXSXRPREkzWldGbE5HRXpNekkx','1800','2017-09-12 16:19:29','2017-11-03 00:00:00',NULL,NULL),(11,1,1,NULL,'Y21SdmJtVndkV1JwWVdReVpETmtNVGt0TXpWallTMDBZbUZtTFRoaU1XTXRZMlV3TXpOaU9XWTJZemc0','1800','2017-09-12 16:20:08','2017-11-03 00:00:00',NULL,NULL),(12,1,1,NULL,'Y21SdmJtVndkV1JwWTJReE1qa3dZV1F0TVRjeU9TMDBOV0k1TFdFMllUY3RaR1U0TTJJMk5EWXhORFJo','1800','2017-09-12 16:21:35','2017-11-03 00:00:00',NULL,NULL),(13,1,1,NULL,'Y21SdmJtVndkV1JwWVRZd09UQmxabUV0TmpJNE9TMDBPVEUwTFdFNVlqWXRaR0k1T1dZMFpHRXpZVEkx','1800','2017-09-12 16:23:19','2017-11-03 00:00:00',NULL,NULL),(14,1,1,NULL,'Y21SdmJtVndkV1JwWWpnelpXVTNaalF0TURaaVl5MDBPVE01TFRnMVpXRXROakUyWXpZM1l6Y3laRFUw','1800','2017-09-12 16:24:46','2017-11-03 00:00:00',NULL,NULL),(15,1,1,NULL,'Y21SdmJtVndkV1JwWXpjMk4yRmtZamt0TkRKbE5DMDBZVEkzTFRneVpERXROVGt5TnpNME5tWXhOelEy','1800','2017-09-12 17:02:59','2017-11-03 00:00:00',NULL,NULL),(16,1,1,NULL,'Y21SdmJtVndkV1JwWVdSalpUY3hNRFl0TUdJeVl5MDBaV1V6TFRrek9HSXRZV0UxTkdFeU1qQmpORFV6','1800','2017-09-12 17:09:36','2017-11-03 00:00:00',NULL,NULL),(17,1,1,NULL,'Y21SdmJtVndkV1JwTVdVMVpUSTRZbUV0WWpabFl5MDBORFZpTFRoaE5UUXRPVEZqTVRVeU9HTXlOall3','1800','2017-09-12 17:10:49','2017-11-03 00:00:00',NULL,NULL),(18,1,1,NULL,'Y21SdmJtVndkV1JwTkRRNFpUSXdOekV0TXpSaU5TMDBORGRrTFdFMFl6SXRNakUyWkRVNE1ESmxaVGcx','1800','2017-09-12 17:29:22','2017-11-03 00:00:00',NULL,NULL),(19,1,1,NULL,'Y21SdmJtVndkV1JwTURrd1pqWTFZemN0TXpobU15MDBOVGswTFdKaE1EZ3RPVE0wWm1Oa05qUXlZekZt','1800','2017-09-12 17:33:42','2017-11-03 00:00:00',NULL,NULL),(20,1,1,NULL,'Y21SdmJtVndkV1JwWTJOa1lUQmxNREV0TkdOaU15MDBObUl5TFdGbU1UVXRNVGN5TVRkbE5ETTBNMkU1','1800','2017-09-12 17:35:57','2017-11-03 00:00:00',NULL,NULL),(21,1,1,NULL,'Y21SdmJtVndkV1JwTXprNE1XRmtNVFF0TmpjMVpDMDBZV00zTFdGaFpqUXROMkU0TUdFNU9EaGlZbUV4','1800','2017-09-12 17:37:31','2017-11-03 00:00:00',NULL,NULL),(22,1,1,NULL,'Y21SdmJtVndkV1JwT1RFeE9UazVNR0l0WXpkalpTMDBOMk0zTFdKbU1ETXRNbUl3WWpreVptWXlZVFl3','1800','2017-09-12 18:28:37','2017-11-03 00:00:00',NULL,NULL),(23,1,1,NULL,'Y21SdmJtVndkV1JwTmpJeFl6UmpPREl0TVdZNE1pMDBZVGt3TFdFd016Z3RPV1ZrTWpGbU16VTNNamhq','1800','2017-09-12 18:30:51','2017-11-03 00:00:00',NULL,NULL),(24,1,1,NULL,'Y21SdmJtVndkV1JwWXpNek5EQTNPV1l0T0RObU1DMDBZamRsTFRrM016VXRPVGsyTlROaE5XRXpNelEy','1800','2017-09-12 18:32:38','2017-11-03 00:00:00',NULL,NULL),(25,1,1,NULL,'Y21SdmJtVndkV1JwT0RkaU5XRTBOalF0TmpNeE9TMDBZbUV6TFdGaE5qSXRNREUyTWprd05UZ3pNMkUx','1800','2017-09-12 18:33:30','2017-11-03 00:00:00',NULL,NULL),(26,1,1,NULL,'Y21SdmJtVndkV1JwWVRWak56azFOemN0TlRsaE5TMDBZemcwTFdJM09UTXROVFJoWlRjMU4yVTJaV0V3','1800','2017-09-12 18:37:40','2017-11-03 00:00:00',NULL,NULL),(27,1,1,NULL,'Y21SdmJtVndkV1JwTlRRMU56ZGtPR0l0Tm1Jd1pDMDBNVEE0TFRneVpHWXRaR0ZpWkRnNE9XVmpOV0pt','1800','2017-09-12 18:43:29','2017-11-03 00:00:00',NULL,NULL),(28,1,1,NULL,'Y21SdmJtVndkV1JwTnprMk56WTJaVGN0WmpGa05TMDBaVE5sTFdGak1URXRPREV4WXprNFpXTTVaRFpp','1800','2017-09-12 18:49:23','2017-11-03 00:00:00',NULL,NULL),(29,1,1,NULL,'Y21SdmJtVndkV1JwT1RrMk1UQTVNRGN0TXpCak1pMDBOMlF3TFRoaVpqVXRaRE15WVRKak9XWTNOall5','1800','2017-09-12 19:19:35','2017-11-03 00:00:00',NULL,NULL),(30,1,1,NULL,'Y21SdmJtVndkV1JwTkRVNU9HTTFZVEF0TkRkbVlpMDBZek13TFdFM1pUTXRNMk0xTldJek9EVXhOalEx','1800','2017-09-12 19:21:33','2017-11-03 00:00:00',NULL,NULL),(31,1,1,NULL,'Y21SdmJtVndkV1JwTURkbFpqZGhZV1l0TVdVek55MDBPREU1TFRrd1lXRXROREEzT0dGaVkySXdNemxt','1800','2017-09-14 19:49:55','2017-11-03 00:00:00',NULL,NULL),(32,1,1,NULL,'Y21SdmJtVndkV1JwTXpBM09XVXdNbVV0T0RZd1l5MDBOVFE0TFdFeVlUTXRNMkl5TXpoallXRmxaVFZr','1800','2017-09-14 20:05:00','2017-11-03 00:00:00',NULL,NULL),(33,1,1,NULL,'Y21SdmJtVndkV1JwT0RoalptVmtOMlF0TURCaE1DMDBOalU0TFRnMk56Z3RORE0wWkRBNU16SmlZVEU1','1800','2017-09-14 20:06:54','2017-11-03 00:00:00',NULL,NULL),(34,1,1,NULL,'Y21SdmJtVndkV1JwTlRWaVpETmhPR1l0TWpnMk1TMDBNbUV3TFdFeVpEQXRPV05sT1RJek1UZ3dZMkps','1800','2017-09-14 20:13:07','2017-11-03 00:00:00',NULL,NULL),(35,1,1,NULL,'Y21SdmJtVndkV1JwTnpVMk56VXlNR0V0TW1KaFpTMDBNelppTFdKaVpETXRZamN4TkRRMVlqSXpNakkz','1800','2017-09-15 10:49:56','2017-11-03 00:00:00',NULL,NULL),(36,1,1,NULL,'Y21SdmJtVndkV1JwT0Raa1ltUmtaREF0TTJaaU1TMDBPRFEyTFdJNFlUWXRNR00yWXpKaE56YzBORGxo','1800','2017-09-15 11:10:39','2017-11-03 00:00:00',NULL,NULL),(37,1,1,NULL,'Y21SdmJtVndkV1JwTWpRNVl6WXlZV0V0TlRjMU15MDBNbVF5TFdJeE5UQXRNMlU1WXpRNE5tRmxNMll5','1800','2017-09-15 14:52:46','2017-11-03 00:00:00',NULL,NULL),(38,1,1,NULL,'Y21SdmJtVndkV1JwWlRReU9EY3hPRFF0WWprMVpDMDBPV1U1TFdJeE0yUXRaR1UwTlRKa1lUZ3hOalpr','1800','2017-09-15 15:25:06','2017-11-03 00:00:00',NULL,NULL),(39,1,1,NULL,'Y21SdmJtVndkV1JwWVdGak4yUmlNMll0TVRNM1lpMDBNVEEyTFRrME9XUXRaVFF6TkRJME16aGpZV0ps','1800','2017-09-15 18:20:05','2017-11-03 00:00:00',NULL,NULL),(40,1,1,NULL,'Y21SdmJtVndkV1JwTlRBek1qRTFObVl0WkdFNU1TMDBabUl5TFdJM056Y3RZakJqTUdNd016ZGlZMlV4','1800','2017-09-15 18:23:07','2017-11-03 00:00:00',NULL,NULL),(41,1,1,NULL,'Y21SdmJtVndkV1JwTXpRellqa3lOall0WmpKaVppMDBZMk01TFRoaFlqZ3RaRE0yT0RnMFpHVTJORGht','1800','2017-09-15 18:26:45','2017-11-03 00:00:00',NULL,NULL),(42,1,1,NULL,'Y21SdmJtVndkV1JwTUdNeVpqY3hNVGt0Tmpsak15MDBPR0ZpTFRrNU5tSXRZemt5TW1FNE1ERmhaV1F5','1800','2017-09-19 18:16:24','2017-11-03 00:00:00',NULL,NULL),(43,1,1,NULL,'Y21SdmJtVndkV1JwTldZeU16VmhZbUl0TlRBek5pMDBNelV6TFdFeE5Ea3RZemN6TkRFNE56UTBaRE0y','1800','2017-09-19 18:22:00','2017-11-03 00:00:00',NULL,NULL),(44,1,1,NULL,'Y21SdmJtVndkV1JwTkdZeU5UWTVOelF0WlRnNE5DMDBOVFJoTFRobE5Ua3RZVEUxTWpFMVl6TTJZbU0z','1800','2017-09-19 19:06:40','2017-11-03 00:00:00',NULL,NULL),(45,1,1,NULL,'Y21SdmJtVndkV1JwTURnek9XWmlaVFV0WlRobE1pMDBOakkxTFdFMU4yUXRZbVZsWVRFME56WTBZMll5','1800','2017-09-19 19:24:30','2017-11-03 00:00:00',NULL,NULL),(46,1,1,NULL,'Y21SdmJtVndkV1JwTWpZNFpqSmhNMk10TkRVd01TMDBNVEJqTFRoalpUVXRNR1F3T1dFeU1XSmlaRGd5','1800','2017-09-20 12:21:46','2017-11-03 00:00:00',NULL,NULL),(47,1,1,NULL,'Y21SdmJtVndkV1JwWlRVME1UVTBZVGt0WXprME55MDBaV0UyTFRsa056UXRObUl5WXpnek5UQmhNVFpr','1800','2017-09-20 12:24:42','2017-11-03 00:00:00',NULL,NULL),(48,1,1,NULL,'Y21SdmJtVndkV1JwWW1RNU56QmlZell0WWprME1DMDBPVEV6TFdJNVlXTXRaR1l5WkRVek1qWTJOVGMy','1800','2017-09-20 12:30:27','2017-11-03 00:00:00',NULL,NULL),(49,1,1,NULL,'Y21SdmJtVndkV1JwT0RBMU0yVTRabVF0WWpGak9TMDBaRFF5TFRrNVpXUXRZVEkwTnpOalpUazJZemRt','1800','2017-09-20 12:42:41','2017-11-03 00:00:00',NULL,NULL),(50,1,1,NULL,'Y21SdmJtVndkV1JwTldJMk1XVXhZemN0TnpjeE9TMDBNVGMzTFdJMk4yTXRPVEkyTnpVd01tTmtZamhp','1800','2017-09-20 14:48:59','2017-11-03 00:00:00',NULL,NULL),(51,1,1,NULL,'Y21SdmJtVndkV1JwWldZMk5HWXdNVEF0WVRnMU1pMDBZVFUyTFRnMVlqSXRZV1ZqTkRFd09UQmtNekU1','1800','2017-09-21 11:07:28','2017-11-03 00:00:00',NULL,NULL),(52,1,1,NULL,'Y21SdmJtVndkV1JwTVRKa056ZzFPRFF0WkdGak1DMDBNVGc1TFdKa09XUXRaVGs1TlRkaFltSXhZMll5','1800','2017-09-21 11:18:03','2017-11-03 00:00:00',NULL,NULL),(53,1,1,NULL,'Y21SdmJtVndkV1JwWkRJNFpUZG1PVE10TTJJeU5pMDBPRFk1TFdFeFpUa3RZV1UyT1Rjd016Y3hOREE0','1800','2017-09-21 17:11:38','2017-11-03 00:00:00',NULL,NULL),(54,1,1,NULL,'Y21SdmJtVndkV1JwWmpsaFl6azBZelF0TkRNelpTMDBZMkZrTFRreVkyWXRNamhpWXpjNVlqbGhNbVZs','1800','2017-09-21 17:15:07','2017-11-03 00:00:00',NULL,NULL),(55,1,1,NULL,'Y21SdmJtVndkV1JwT0RaaFpHUXdNVE10TTJReE5DMDBaVGcwTFdFMU5ERXRaVFUxWlRnMFltVTVOVE16','1800','2017-09-21 17:24:41','2017-11-03 00:00:00',NULL,NULL),(56,1,1,NULL,'Y21SdmJtVndkV1JwTldNd1l6QmtZekV0TkRNNE9DMDBOV0UzTFRobE1ETXRNVGt5TkRGbU5XTmlPRE5r','1800','2017-09-21 17:39:52','2017-11-03 00:00:00',NULL,NULL),(57,1,1,NULL,'Y21SdmJtVndkV1JwWmpBeU9ETmpObUV0TVdFMllpMDBaVGMxTFdJNE5Ea3RaREV5WVdFM05XWTFNbVps','1800','2017-09-21 17:45:57','2017-11-03 00:00:00',NULL,NULL),(58,1,1,NULL,'Y21SdmJtVndkV1JwTXpBMU1XWXhabUl0TjJSa05pMDBaVE01TFRneE9UUXRZVFJoWXpRM1lUQmpOR0kw','1800','2017-09-21 17:48:58','2017-11-03 00:00:00',NULL,NULL),(59,1,1,NULL,'Y21SdmJtVndkV1JwT1RJNU5qUXlZMkV0TUdVd05TMDBZamM0TFdFNU5XVXRPRFkyWVdKaE56RXhPVGMw','1800','2017-09-22 10:28:04','2017-11-03 00:00:00',NULL,NULL),(60,1,1,NULL,'Y21SdmJtVndkV1JwWVRKalpqQXhNMll0WWpJeFppMDBaREpoTFdGaU9EY3RPRE5sTWpVM01tWTFZemN5','1800','2017-09-22 10:36:57','2017-11-03 00:00:00',NULL,NULL),(61,1,1,NULL,'Y21SdmJtVndkV1JwTVdNd09USTBORE10WTJJME5DMDBNelkzTFdFNFlqZ3ROall4TmpWa1ptRXpOV0l6','1800','2017-09-22 10:50:56','2017-11-03 00:00:00',NULL,NULL),(62,1,1,NULL,'Y21SdmJtVndkV1JwTW1RMk5UWXhaamt0TWprd09TMDBNV0kxTFdFMU1HTXRaRE0xTjJWa09EY3dZbVk0','1800','2017-09-22 10:51:33','2017-11-03 00:00:00',NULL,NULL),(63,1,1,NULL,'Y21SdmJtVndkV1JwTUdaak1qZzJOMll0WlRVeU5pMDBOV1V5TFdJME9HRXRObVZoTURReVlUYzNORE5r','1800','2017-09-22 10:53:51','2017-11-03 00:00:00',NULL,NULL),(64,1,1,NULL,'Y21SdmJtVndkV1JwTTJNMk9UQTRZMk10TXpVMVppMDBaakJsTFdGaFl6Y3ROamxtWldSaVkyUTJOalkz','1800','2017-09-22 10:55:03','2017-11-03 00:00:00',NULL,NULL),(65,1,1,NULL,'Y21SdmJtVndkV1JwTURCa05tRXlNMll0WVRFNVppMDBPVGhsTFRnMlltSXRPREptWVRjeE9HWmhOekps','1800','2017-09-22 10:56:57','2017-11-03 00:00:00',NULL,NULL),(66,1,1,NULL,'Y21SdmJtVndkV1JwWlRBMk9EZzRZemN0TWpkbE9TMDBZekl6TFRoaE1qSXRNbU0zTXpGalpqVmhOVFk1','1800','2017-09-22 11:02:33','2017-11-03 00:00:00',NULL,NULL),(67,1,1,NULL,'Y21SdmJtVndkV1JwWW1ZNU1EaGtNVEV0TUdGbU5pMDBOalJoTFRrd01qTXRaakU0WW1RNU16RXdaRGRo','1800','2017-09-22 11:03:23','2017-11-03 00:00:00',NULL,NULL),(68,1,1,NULL,'Y21SdmJtVndkV1JwTTJZNE5UYzROR1l0TlRKbFl5MDBZamM0TFRobU5EY3RaVEEzTVdJeE5HTTNOelUy','1800','2017-09-22 11:03:57','2017-11-03 00:00:00',NULL,NULL),(69,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 15:24:38','2017-11-03 00:00:00',NULL,NULL),(70,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 15:29:48','2017-11-03 00:00:00',NULL,NULL),(71,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 15:37:07','2017-11-03 00:00:00',NULL,NULL),(72,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 15:43:29','2017-11-03 00:00:00',NULL,NULL),(73,1,2,NULL,'vpulyala@osius.com','1800','2017-09-22 15:52:49','2017-09-22 00:00:00',NULL,NULL),(74,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 15:54:17','2017-11-03 00:00:00',NULL,NULL),(75,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 15:55:12','2017-11-03 00:00:00',NULL,NULL),(76,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 16:06:36','2017-11-03 00:00:00',NULL,NULL),(77,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 16:08:22','2017-11-03 00:00:00',NULL,NULL),(78,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 16:28:55','2017-11-03 00:00:00',NULL,NULL),(79,1,3,NULL,'rbolireddi@osius.com','1800','2017-09-22 16:31:57','2017-09-22 00:00:00',NULL,NULL),(80,1,4,NULL,'plachi@osius.com','1800','2017-09-22 17:43:20','2017-09-22 00:00:00',NULL,NULL),(81,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-22 17:52:36','2017-11-03 00:00:00',NULL,NULL),(82,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 14:45:05','2017-11-03 00:00:00',NULL,NULL),(83,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 15:15:58','2017-11-03 00:00:00',NULL,NULL),(84,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 15:20:11','2017-11-03 00:00:00',NULL,NULL),(85,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 16:54:38','2017-11-03 00:00:00',NULL,NULL),(86,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 17:40:57','2017-11-03 00:00:00',NULL,NULL),(87,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 17:42:26','2017-11-03 00:00:00',NULL,NULL),(88,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 18:12:20','2017-11-03 00:00:00',NULL,NULL),(89,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 19:24:08','2017-11-03 00:00:00',NULL,NULL),(90,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 19:41:43','2017-11-03 00:00:00',NULL,NULL),(91,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 19:43:19','2017-11-03 00:00:00',NULL,NULL),(92,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-25 19:46:07','2017-11-03 00:00:00',NULL,NULL),(93,1,1,NULL,'rdonepudi@osius.com','1800','2017-09-26 10:50:57','2017-11-03 00:00:00',NULL,NULL),(94,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldSak16VTRZVEl5TFRGak9XUXRORFpsWWkwNU1EZ3lMVE0yTnpNeE1tRmhaREZoTVE9PQ==','1800','2017-09-26 12:03:42','2017-11-03 00:00:00',NULL,NULL),(95,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldNMllXVmtOV1V3TFRZNU1HSXROREJoWkMxaU5XUm1MV1ZqTldSa04ySXlNRFV6T1E9PQ==','1800','2017-09-26 12:07:35','2017-11-03 00:00:00',NULL,NULL),(96,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJNE9UYzFZV1JtTFRGaFltRXRORGs0TXkxaU9HVmhMV1JtTlRZelpETmhZMlF6T0E9PQ==','1800','2017-09-26 12:09:10','2017-11-03 00:00:00',NULL,NULL),(97,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZd016aGhaRGhqTFRaa09UZ3ROREZtTnkwNU1qZGlMVGt6TmpGaVltVm1aRFF4TXc9PQ==','1800','2017-09-26 12:10:32','2017-11-03 00:00:00',NULL,NULL),(98,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJeVlXRTFZelJrTFRWaVptUXROR1F3WXkxaE1qY3hMVFUwWm1ZMk1HTmxZalJqT0E9PQ==','1800','2017-09-26 12:13:06','2017-11-03 00:00:00',NULL,NULL),(99,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRoaE5EWXpOV1EwTFdaaE9EWXROREk1WXkwNU9EQTJMVEJtTjJJeVltRXpPR0ZtTUE9PQ==','1800','2017-09-26 12:16:16','2017-11-03 00:00:00',NULL,NULL),(100,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRoak5UUmtNekpqTFRZeE5qVXRORGxqWWkxaU9HVXhMVE13TjJNMU5qVTFPR001Tnc9PQ==','1800','2017-09-26 12:17:14','2017-11-03 00:00:00',NULL,NULL),(101,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVMVpEZGlZbUV6TFRZNVpqZ3ROR0pqTmkxaU1qRXlMV1l4TW1WaVlXRXhZalU1T0E9PQ==','1800','2017-09-26 12:56:11','2017-11-03 00:00:00',NULL,NULL),(102,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRZelpUUmhaV05rTFdVMVpESXRORFUyT1MwNVpUbGpMV0ZpTXpRelpERmpNbVZqWkE9PQ==','1800','2017-09-26 13:04:33','2017-11-03 00:00:00',NULL,NULL),(103,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldReU9EUmxZVFpqTFRKbU1UQXRORGs1TmkwNVpUQXhMV013WVdZNE1tUXdaalV5WkE9PQ==','1800','2017-09-26 13:08:00','2017-11-03 00:00:00',NULL,NULL),(104,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRBellqZGpOVEE0TFRNMU9HSXRORFV6WVMxaVpEWmpMV1k1TVRJek5URTNOelUwTkE9PQ==','1800','2017-09-26 13:09:28','2017-11-03 00:00:00',NULL,NULL),(105,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRSak1ETmxNbUptTFdWak5UUXRORFJpT0MxaVpUTmlMVE0wTlRBMk5XRTRNREpoTXc9PQ==','1800','2017-09-26 13:17:11','2017-11-03 00:00:00',NULL,NULL),(106,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRWaE5HVTBaR1JtTFdReU1XWXROR1prTWkwNU5HUTBMVFk1WldVME1qRTBaalUwTVE9PQ==','1800','2017-09-26 13:18:36','2017-11-03 00:00:00',NULL,NULL),(107,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnM1kyVTVNamd6TFdFelpHRXROR1l3TlMxaE5XVmtMV1U1WlRKbE56YzRaR1l3TWc9PQ==','1800','2017-09-26 14:46:36','2017-11-03 00:00:00',NULL,NULL),(108,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldWbE5qbGhNV1UzTFRkbU5qRXROREF3WmkwNU9HTTFMVE16TXpKaE0yRXhOVGd5Tmc9PQ==','1800','2017-09-26 14:50:26','2017-11-03 00:00:00',NULL,NULL),(109,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRabVptWTBOR1ExTFdJNU5XSXROR0V4WXkwNU1UZzFMV1JtTWpFNE5qQmtNVEEyT0E9PQ==','1800','2017-09-26 14:54:29','2017-11-03 00:00:00',NULL,NULL),(110,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldOaU16SmpZakEwTFRGaU9HWXROR013TVMwNVlqWXhMVGxqWW1GbE5EZzVObUkwTWc9PQ==','1800','2017-09-26 14:59:57','2017-11-03 00:00:00',NULL,NULL),(111,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkaFlUY3paR05sTFRobFpEY3ROR0ZsTVMwNE4yVXpMVGxoTVdVeFptUTJZVE15TVE9PQ==','1800','2017-09-26 15:02:05','2017-11-03 00:00:00',NULL,NULL),(112,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRoaFlUUTVPVEpoTFdJd1pqTXRORGMyTUMxaVpEVTBMVEUyWTJRNFpUUmtOVFkwWmc9PQ==','1800','2017-09-26 15:02:43','2017-11-03 00:00:00',NULL,NULL),(113,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldWaU16SXhOamswTFdGbE1EZ3ROR0kzWmkwNVpEWTFMVEJtWVRaa1ltRTFNbUUxT1E9PQ==','1800','2017-09-26 15:05:08','2017-11-03 00:00:00',NULL,NULL),(114,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKa01EZGpZalV6TFdaak0yUXRORFkxTmkxaU1qSmtMV0V3TkdGbVpXVTRZMlE0TUE9PQ==','1800','2017-09-26 15:06:45','2017-11-03 00:00:00',NULL,NULL),(115,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldWbU1tSm1aR1kxTFRaa1ltWXROR0prWWkwNE56VmxMV1ZrTWpKaFltUXpaamd6WXc9PQ==','1800','2017-09-26 15:10:27','2017-11-03 00:00:00',NULL,NULL),(116,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldJNU5tWXpNVEJqTFRJelpUVXRORFUwTlMwNFlUYzNMVGs0WVdOak1EUm1OVEE1WVE9PQ==','1800','2017-09-26 15:18:53','2017-11-03 00:00:00',NULL,NULL),(117,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldOallXUmxOemxtTFRGak1UVXROREUxTkMxaU5EVXdMVE0wTXpBeE5Ea3haVGxrWkE9PQ==','1800','2017-09-26 15:20:41','2017-11-03 00:00:00',NULL,NULL),(118,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFMFptVTFOVGMyTFRRM05XWXROREF6TWkxaVpqSm1MVFE1TkRBNU1EUXpORGhoTXc9PQ==','1800','2017-09-26 15:22:04','2017-11-03 00:00:00',NULL,NULL),(119,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldNek1qUTBaak13TFRjd056SXROR0V6WmkxaFltTmhMVGRoT1RBM05Ea3lNelJpTVE9PQ==','1800','2017-09-26 15:23:54','2017-11-03 00:00:00',NULL,NULL),(120,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkaFlqZ3paakZtTFdGak1HTXROR0V3WkMxaE56QXhMV1ZtTnpNMFpUTmpOVFkwTUE9PQ==','1800','2017-09-26 15:26:00','2017-11-03 00:00:00',NULL,NULL),(121,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRKbVpHRm1PV1EyTFdGa1ptRXRORGsyTUMwNFpHVmlMV00zWVdVd1kyRXlNbUl6WWc9PQ==','1800','2017-09-26 15:28:32','2017-11-03 00:00:00',NULL,NULL),(122,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFeFptRmtaR0l4TFdSa056QXRORFprTXkxaVl6VXhMVE5tT1RnM09XVTVaRGN4TVE9PQ==','1800','2017-09-26 15:32:20','2017-11-03 00:00:00',NULL,NULL),(123,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZNVlqVmxNemN4TFdZd05tVXRORFZpWXkwNVpHVmhMVFkyWVdNM1ltUXhNR1F6TlE9PQ==','1800','2017-09-26 15:34:55','2017-11-03 00:00:00',NULL,NULL),(124,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnM1lXWmpPV1UyTFRJNU1HRXROREF4WXkwNE9UVmhMVGhsWldObU1UYzBPVEEyTUE9PQ==','1800','2017-09-26 15:53:08','2017-11-03 00:00:00',NULL,NULL),(125,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJeFlUUTBNV00yTFRabVlXUXROR1U0T1MxaVptWTRMVGRpWVRJNFlqYzFOelJpWWc9PQ==','1800','2017-09-26 15:57:06','2017-11-03 00:00:00',NULL,NULL),(126,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldSbE5tUXhZV1l5TFdNMFpHVXRORGt5TkMxaE5HSTVMVEV4WkdGbU5XVTROR0l5Tmc9PQ==','1800','2017-09-26 15:58:28','2017-11-03 00:00:00',NULL,NULL),(127,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRZeE9XVTJZV0ZrTFRKaU56SXRORFZpTUMwNU9HTmxMVFE0WWpoaFlXWTNZakEzTVE9PQ==','1800','2017-09-26 16:00:07','2017-11-03 00:00:00',NULL,NULL),(128,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFMk1EVTNZbVF4TFRKaE56a3ROR1EwT1MxaFpUQXlMVEpoT0RSbU5HUTJaR0kyWkE9PQ==','1800','2017-09-26 16:02:24','2017-11-03 00:00:00',NULL,NULL),(129,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlROaU9EWXhNMlkxTFRVek16RXRORGxqWmkxaVpqVmtMVGhrWm1WaU9HTmlaamN4TUE9PQ==','1800','2017-09-26 16:03:43','2017-11-03 00:00:00',NULL,NULL),(130,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkbU0yRmxNbU16TFdOa1ptRXROR1kzT0MwNVpEVm1MVE0wTURNMU9HUmlZVGhpWkE9PQ==','1800','2017-09-26 16:07:06','2017-11-03 00:00:00',NULL,NULL),(131,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVeU56QmxOelUzTFRrNFl6SXROREU0TWkxaVlUZzFMVEkxT1dRNFlqa3dZek5qT1E9PQ==','1800','2017-09-26 16:09:18','2017-11-03 00:00:00',NULL,NULL),(132,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnd05EQXhNalV4TFdFMk1tSXRORE15WVMxaVlXVXlMVFl4TURrd05HUmxNbVprWlE9PQ==','1800','2017-09-26 16:10:53','2017-11-03 00:00:00',NULL,NULL),(133,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRsaVpqRXpObVUxTFdNeU9XRXRORFV5WmkxaVlqRmpMVFpsTUdOaVltUTNZV1l6TXc9PQ==','1800','2017-09-26 16:12:05','2017-11-03 00:00:00',NULL,NULL),(134,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJNE1HWTBObVl4TFRGbE5qY3RORFF6TnkwNU9EaGtMVGt4TW1aaU5XWmhNelpqTXc9PQ==','1800','2017-09-26 16:17:19','2017-11-03 00:00:00',NULL,NULL),(135,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFME56WXlNVGc1TFRJeVpUa3RORGhqTWkxaFl6WTJMV1EwWlRBNE1HRTNNVEExTlE9PQ==','1800','2017-09-26 16:22:30','2017-11-03 00:00:00',NULL,NULL),(136,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRKaU4yVmlZbVkzTFdZM05UY3RORGN5WWkwNE16Rm1MVGcwWkRBM1pqTXlOMkk1T1E9PQ==','1800','2017-09-26 16:23:32','2017-11-03 00:00:00',NULL,NULL),(137,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRObFpEaGtZVEJoTFROa1ptUXRORGt3TlMwNFlqQXpMVGt4WW1Fek9HRmhNV0l5TkE9PQ==','1800','2017-09-26 16:26:17','2017-11-03 00:00:00',NULL,NULL),(138,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52Yldaak5qbGxNamMzTFdNMk5XSXRORGczTnkwNE5tUmhMVGczTW1KaVlqSTJOVEE1TWc9PQ==','1800','2017-09-26 16:36:16','2017-11-03 00:00:00',NULL,NULL),(139,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRKbE5XVm1aV1V6TFROaE1UTXRORFV3WVMxaU5UUXpMVFkwTURnMFpHUTBOakJoWVE9PQ==','1800','2017-09-26 16:40:10','2017-11-03 00:00:00',NULL,NULL),(140,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRaa01qWXdORGcyTFRBMU1EY3ROR1ZrTWkwNE5UUmlMV1JtWkdReE1XUmhOVEZqWWc9PQ==','1800','2017-09-26 16:41:02','2017-11-03 00:00:00',NULL,NULL),(141,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkbVpqZGlPVFl6TFdVeE1tTXRORGc0TXkxaE1HWmxMV0k0TkRkak5EUmlaVEpsTXc9PQ==','1800','2017-09-26 16:42:18','2017-11-03 00:00:00',NULL,NULL),(142,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVME5HVTJZVGhqTFRaaU56SXROR0l4WlMwNU16aG1MVEF4WmpsbE5ERmhPRFEyTUE9PQ==','1800','2017-09-26 16:43:17','2017-11-03 00:00:00',NULL,NULL),(143,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRWa01HSXdPVFF3TFRBM05qRXRORGxrT0MxaU1tTXhMV1JpTXpsaFpHVmxOalpoT0E9PQ==','1800','2017-09-26 16:44:56','2017-11-03 00:00:00',NULL,NULL),(144,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRNMU9ESmlObUV6TFRBd09HRXROR1k0WlMxaU1qQm1MVEZrWVRkaU1qSTVPRE15T1E9PQ==','1800','2017-09-26 16:46:04','2017-11-03 00:00:00',NULL,NULL),(145,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFMU5ERTJObVZtTFdZeU9XVXRORFZrWkMxaFpUTTNMVFppTkRJek5URTNaV000Tnc9PQ==','1800','2017-09-26 16:47:56','2017-11-03 00:00:00',NULL,NULL),(146,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRKbU5HRTRZMkpoTFRrellUVXROR1F4TXkwNE5tUTRMVE0xT0RrNU16bGhOVEZrWmc9PQ==','1800','2017-09-26 16:49:53','2017-11-03 00:00:00',NULL,NULL),(147,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnM1pEaGpNalF3TFRCa01HRXROR0UyTkMxaE9XTTBMVEk1TkRFMVlUTmxOamd5Tmc9PQ==','1800','2017-09-26 16:53:01','2017-11-03 00:00:00',NULL,NULL),(148,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVME1qUTFaRFJqTFdFek0yUXROR0ZqTnkwNFpqTTRMVEUzWmpneFlqUmpOV1F6TlE9PQ==','1800','2017-09-26 16:57:21','2017-11-03 00:00:00',NULL,NULL),(149,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRoak9EazVOR1JtTFRjNE5XRXRORGxqTkMxaFpUaGxMVEl3TldKbE4yUTVORFkxWmc9PQ==','1800','2017-09-26 16:58:45','2017-11-03 00:00:00',NULL,NULL),(150,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRNVlXWXlaamM0TFdJeFpHVXRORGt4T1MxaFkyRXhMVGt5WmpnNU56WmxOV1EwTlE9PQ==','1800','2017-09-26 17:01:44','2017-11-03 00:00:00',NULL,NULL),(151,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRME5qWTRZems0TFRJelptWXRORFprT1MxaVkySTVMVGMwT1RWaE9XWXpNMlJtTUE9PQ==','1800','2017-09-26 17:03:02','2017-11-03 00:00:00',NULL,NULL),(152,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldWaE1HWm1NelZpTFdSaFlXVXRORFExT1MwNU1XWXdMV1V4Tm1FM056VXpZMkl3T1E9PQ==','1800','2017-09-26 17:05:13','2017-11-03 00:00:00',NULL,NULL),(153,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRobE5ERmxNall4TFdFeVlUTXROREExTXkxaE9XUmlMVGRoWW1OaE5UWmlPVEl6Tnc9PQ==','1800','2017-09-26 17:06:24','2017-11-03 00:00:00',NULL,NULL),(154,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGa1lUUTJOV1V5TFdSaE4yUXRORFprTUMwNVkyTXpMV1kxWXpWaE9HSXpNalprTnc9PQ==','1800','2017-09-26 17:10:17','2017-11-03 00:00:00',NULL,NULL),(155,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRME9HSTNObUV5TFRaaE56RXROR1JsTlMxaU1qQm1MVEZtTVRJd05tUmtaVGcxWkE9PQ==','1800','2017-09-26 17:11:58','2017-11-03 00:00:00',NULL,NULL),(156,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRM01UZGtPRFU0TFdWallUa3RORGhqTmkwNU9XSmxMVFkzTmpWbE9XVXdObUpqTXc9PQ==','1800','2017-09-26 17:12:11','2017-11-03 00:00:00',NULL,NULL),(157,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVeE5EaGtaV1psTFdGaU5XRXRORFZrTnkwNE5qSmhMVEZsTkRGbFpETTVOR05sWXc9PQ==','1800','2017-09-26 17:13:57','2017-11-03 00:00:00',NULL,NULL),(158,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRNM05qWm1NbVZqTFRnMVltRXROR1ZpTWkwNVltTXlMV1ptWmpBNU1EUmlZakl6WkE9PQ==','1800','2017-09-26 17:20:11','2017-11-03 00:00:00',NULL,NULL),(159,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFelpqUXdNemd6TFRjM04yUXROR1poTlMwNE1qWTFMVEF3WkRSak4yTTFabVF6Tmc9PQ==','1800','2017-09-26 17:22:11','2017-11-03 00:00:00',NULL,NULL),(160,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVMU5XRTFaRE5pTFRjME1XRXROREEwT0MwNU1tWTBMV1ZoTXpFeFpUaGxNalpoTVE9PQ==','1800','2017-09-26 17:23:12','2017-11-03 00:00:00',NULL,NULL),(161,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFd1lXUTFNbVZsTFRZME9HVXROR1k1WkMwNE16YzFMV05sTW1Nek5EaGxNVGd6Wmc9PQ==','1800','2017-09-26 17:27:19','2017-11-03 00:00:00',NULL,NULL),(162,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVNU5qZzVPVGN5TFRKaVlXSXRORFpqWmkxaU1EaG1MVFkzWTJWaE5UZzRORFV4WWc9PQ==','1800','2017-09-26 17:33:36','2017-11-03 00:00:00',NULL,NULL),(163,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVNU9HRXhZbVV4TFdFellUVXROR1ZoTnkwNE1qUXhMV05oWXpJNFpUZGlPREV5Tnc9PQ==','1800','2017-09-26 17:38:23','2017-11-03 00:00:00',NULL,NULL),(164,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJNE0yVXlaRGd6TFdKaE1XVXROR0V3TWkxaVptUTBMVGcxTnpobE1ETmhOV1JpWkE9PQ==','1800','2017-09-26 17:39:12','2017-11-03 00:00:00',NULL,NULL),(165,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVellUazJZVFZoTFdFM01UWXROR1V3WlMwNE1qazRMV1kzWlRkaE1EZzJPR0ZsWlE9PQ==','1800','2017-09-26 17:40:45','2017-11-03 00:00:00',NULL,NULL),(166,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjME5qWTRZelU1TFdWaU5qY3RORGszTnkwNFkyWTJMV0UwTUdFNU1tTXpNREF4Wmc9PQ==','1800','2017-09-26 17:42:09','2017-11-03 00:00:00',NULL,NULL),(167,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZeFpUWTRZV1poTFRrM1pqVXROREUzTXkwNVpqSmxMVEZrWXpGbVl6QmxNV0l6TXc9PQ==','1800','2017-09-26 17:44:05','2017-11-03 00:00:00',NULL,NULL),(168,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnd09EazFNVEZpTFdGalpUWXROR0k0TUMwNE1qZzJMV1ppWlRCa05UZGpZVEF6TXc9PQ==','1800','2017-09-26 17:51:11','2017-11-03 00:00:00',NULL,NULL),(169,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFMU1USXdNRE0zTFdObU1UY3ROREV5TXkxaE1qSTRMVGcwWTJVM01XTXdOR1F6TkE9PQ==','1800','2017-09-26 17:56:55','2017-11-03 00:00:00',NULL,NULL),(170,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRaaU1qTTNORFZrTFRVME9XSXROR1l5TVMwNU1UWTBMV1psWXpkbE5tRmpPVGd4TXc9PQ==','1800','2017-09-26 18:01:28','2017-11-03 00:00:00',NULL,NULL),(171,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRBNE5qbGtZekF6TFdRNU5qVXRORGMyTlMxaE1EazFMVGxoT1RGaU5HTXlZMkkwWmc9PQ==','1800','2017-09-26 18:04:48','2017-11-03 00:00:00',NULL,NULL),(172,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZMk56a3lPVGxpTFdKbFpUSXRORGxoWmkxaE5XTTRMVEEzTm1ZeE1XTTVZemswWkE9PQ==','1800','2017-09-26 18:06:33','2017-11-03 00:00:00',NULL,NULL),(173,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRMFpUQTVPVGhpTFdJMlpUSXROREJpTlMxaU9XUXdMVEpqWWpVNE1ETXlNakV3WXc9PQ==','1800','2017-09-26 18:07:56','2017-11-03 00:00:00',NULL,NULL),(174,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRaa09XUXlNMlV6TFRrME9URXRORFE0TWkxaE9XTm1MVE5tTWpnMk5tTTFaRFJrTlE9PQ==','1800','2017-09-26 18:09:25','2017-11-03 00:00:00',NULL,NULL),(175,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRWaVpEUmhORGd3TFRFME56QXROREl4TWkxaU5USmxMV1JoWkdRME1qUXdOell4TUE9PQ==','1800','2017-09-26 18:10:18','2017-11-03 00:00:00',NULL,NULL),(176,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnM1lUTXlZV001TFRNMU1HWXROREUxTkMxaU9ERTNMVEUzTURjd09EazVaakF6WkE9PQ==','1800','2017-09-26 18:16:34','2017-11-03 00:00:00',NULL,NULL),(177,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjeVkyRm1OR1kyTFdSaVptTXROR1ZpT1MxaE9EUXlMVFV3Tm1NeVpqaGhaR0k0TWc9PQ==','1800','2017-09-26 18:17:50','2017-11-03 00:00:00',NULL,NULL),(178,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRneU9UWTBPRFV3TFRBMk1EZ3RORFF6WlMxaFl6YzVMVEV6TWpjeU5tTTFaVGs0WkE9PQ==','1800','2017-09-26 18:19:34','2017-11-03 00:00:00',NULL,NULL),(179,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRSaU1HSmxaV0k0TFRVM01XSXRORGxrTUMwNU5tUTNMVFl4TVdOak1HVmpOR0pqWkE9PQ==','1800','2017-09-26 18:21:17','2017-11-03 00:00:00',NULL,NULL),(180,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRZM05UQTVNR1ZtTFdOak9XUXROREJtTXkxaVptRTFMVGt5WVRSak1qZ3pZell5TVE9PQ==','1800','2017-09-26 18:23:10','2017-11-03 00:00:00',NULL,NULL),(181,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGa1pqZGlNelptTFdSbFpXWXROR0k1TkMxaVl6SXhMVGRpWTJFMVpXWTJZemN4WWc9PQ==','1800','2017-09-26 18:25:03','2017-11-03 00:00:00',NULL,NULL),(182,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVM05qSTBaVFkzTFdaaE9EUXROR1kwT1MwNVl6UTVMVE14TTJFeFlUQm1OVFJrTXc9PQ==','1800','2017-09-26 18:26:52','2017-11-03 00:00:00',NULL,NULL),(183,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldJNE5tSXlObVkyTFRJek9HSXRORGhqWkMwNVpqY3lMVGs1TUdFeVpUWXlOell4Tnc9PQ==','1800','2017-09-26 18:27:47','2017-11-03 00:00:00',NULL,NULL),(184,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldNeE9UVTBNVGxtTFRJM05qRXROREZpWWkxaE9UUTVMVFl4T1dabFlUSmpaREV6TkE9PQ==','1800','2017-09-26 18:29:34','2017-11-03 00:00:00',NULL,NULL),(185,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRKbE5HSTVaRGt3TFRoaVl6RXROREF3TVMxaE1EVXlMVEUzWmpNNU5UZ3lNekUwWWc9PQ==','1800','2017-09-26 18:30:32','2017-11-03 00:00:00',NULL,NULL),(186,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRObU1UWTRZVFkyTFdVNE1HUXROR1kzTWkwNE5ESTFMVFUzTkRObFl6UTVZek0yWlE9PQ==','1800','2017-09-26 18:31:17','2017-11-03 00:00:00',NULL,NULL),(187,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRobU56RTVObVJqTFRZMk4yTXROR0U0TUMxaVpUWTVMVEF6TkdaalptVmpZVFJsWXc9PQ==','1800','2017-09-26 18:31:58','2017-11-03 00:00:00',NULL,NULL),(188,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRaa09UVTVORFV6TFRJelptUXROR0ZsTnkxaFpEUTFMVEJtTkRjME5UazBZMkZsTVE9PQ==','1800','2017-09-26 18:32:52','2017-11-03 00:00:00',NULL,NULL),(189,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRBNVl6QXhOalkwTFRsbU16RXROR05tWXkxaVpXUTNMV1E0TVRaaE9ETTBabVZpWVE9PQ==','1800','2017-09-26 18:35:29','2017-11-03 00:00:00',NULL,NULL),(190,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldOa1pqTmpaR0ZtTFRoak1EVXRORFUzTkMxaFpqZzVMVEZtTlRrMFpUTmhaalUzWkE9PQ==','1800','2017-09-26 18:37:17','2017-11-03 00:00:00',NULL,NULL),(191,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnNU9XSmhOekZpTFdaaU5qQXROR1UyTnkwNE1qZGhMVFF3Tm1SbVpHVTVOVGRtWWc9PQ==','1800','2017-09-26 18:38:10','2017-11-03 00:00:00',NULL,NULL),(192,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkbE1UUTBPR1poTFRrMk1UVXRORGhoTXkwNE5tTTRMVFF6WXpSalpUa3hZV1k0TkE9PQ==','1800','2017-09-26 18:39:26','2017-11-03 00:00:00',NULL,NULL),(193,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZMk5tUmlaRFpqTFdGbVlqRXRORFZoTlMwNE1qWTRMV0l4Tnpnek5EWmpNV1JsTkE9PQ==','1800','2017-09-26 18:40:40','2017-11-03 00:00:00',NULL,NULL),(194,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRneFlXWmlaakUzTFdJeU1HRXROR0prWVMxaVpqY3lMV0l5TUdKaVlUWXdaak16TkE9PQ==','1800','2017-09-26 18:43:44','2017-11-03 00:00:00',NULL,NULL),(195,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFM1pqY3hNbU01TFRBd1lqUXROREk1WkMxaE5tSXhMVGs1Wm1SaE1UQXhaVFV4Tmc9PQ==','1800','2017-09-26 18:46:34','2017-11-03 00:00:00',NULL,NULL),(196,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkaFlqbGpNREpoTFRobE4yVXROREF6TlMxaE1HTmxMVEE1WTJJMlltSmhOelExWlE9PQ==','1800','2017-09-26 18:47:29','2017-11-03 00:00:00',NULL,NULL),(197,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVd1lUTmhZall4TFRBd09UVXROR1V3TXkxaE56ZzRMVEppT1Rnek9HTTBZVGxtWlE9PQ==','1800','2017-09-26 18:50:29','2017-11-03 00:00:00',NULL,NULL),(198,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVMk5EVTVaamcyTFRZd1lXUXROR1l6TlMwNFl6RXpMV0UzTWpZNVl6ZGpaRFF3Wmc9PQ==','1800','2017-09-26 18:51:24','2017-11-03 00:00:00',NULL,NULL),(199,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkaE5qRmxNek5rTFRWa1pXVXROR1JpWVMxaU1UaGtMVFEzWVRrNE9EZzBaRGN6Tnc9PQ==','1800','2017-09-26 18:53:23','2017-11-03 00:00:00',NULL,NULL),(200,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRNFl6WTRZek15TFRrMk9XTXRORE5pT0MxaU9URmpMV1EzTlROaFlqTXpNRGt6TlE9PQ==','1800','2017-09-26 18:55:23','2017-11-03 00:00:00',NULL,NULL),(201,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjM1pUUmtabVJtTFdJME16SXRORGN6TWkwNFlURTNMVFEyTkdOa05EZG1NREE1TVE9PQ==','1800','2017-09-26 19:02:36','2017-11-03 00:00:00',NULL,NULL),(202,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRrNE5qaGlOalptTFRkak9UTXROREl3TlMwNE1ETXhMV1F5WldRNU1tSmpaalJsWWc9PQ==','1800','2017-09-26 19:04:16','2017-11-03 00:00:00',NULL,NULL),(203,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGbU5XTmlNV1ExTFRNelpEa3RORGt3TkMwNU5qWTRMVE0wTXpKaFpUUTNOakZqTmc9PQ==','1800','2017-09-26 19:05:13','2017-11-03 00:00:00',NULL,NULL),(204,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFMVpESXdNRGcyTFRRMU9XSXROR1U1TUMxaFpUSTBMVGRoTURGa05UTTRaVFkzWlE9PQ==','1800','2017-09-26 19:06:53','2017-11-03 00:00:00',NULL,NULL),(205,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFMk1tRTRaRGt3TFRSaFlqY3ROR1ZoTVMwNVkyWmhMVFl4TkdNNFptTXdObVZtTXc9PQ==','1800','2017-09-26 19:08:35','2017-11-03 00:00:00',NULL,NULL),(206,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldRd1ptSm1OakF3TFRRNVlUTXRORFpsWXkxaE1HUmtMVGN6WlRNeE5XVm1ZelF3WlE9PQ==','1800','2017-09-26 19:09:16','2017-11-03 00:00:00',NULL,NULL),(207,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjek5UWTFabUZoTFRJM05Ea3ROR1l3T1MwNU5qaGtMVEpoWmpVMlpURXhNekpsWmc9PQ==','1800','2017-09-26 19:10:20','2017-11-03 00:00:00',NULL,NULL),(208,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRSbFpHSmpOMk16TFRFMFpURXRORGhpT1MwNVlqWmpMVEptTVdFeE1qRXpPVGN4WXc9PQ==','1800','2017-09-26 19:14:37','2017-11-03 00:00:00',NULL,NULL),(209,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGaFl6QmlaV1UwTFRjNU5EWXRORFEzTWkxaU56WTRMVGt4TURjeU9ERXpNelF6T1E9PQ==','1800','2017-09-26 19:35:46','2017-11-03 00:00:00',NULL,NULL),(210,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZMFpqVmlOR0UzTFRRNU9UY3RORFUxWXkwNE1XTXhMVE0xTURZNE1EYzJOemhoTlE9PQ==','1800','2017-09-26 19:37:57','2017-11-03 00:00:00',NULL,NULL),(211,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVelpEVmpZek0wTFRRNE5qVXROR0UyWmkxaE9EZzJMVGcxTXpFek56YzBOMlF6TVE9PQ==','1800','2017-09-27 10:39:40','2017-11-03 00:00:00',NULL,NULL),(212,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRCaVlUazVZelEzTFdFMFlUSXROREUxT0MxaE56QXdMVFV3WkdWbU1XSmhOMkkxWWc9PQ==','1800','2017-09-27 10:43:49','2017-11-03 00:00:00',NULL,NULL),(213,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRReE9URmxaalkzTFRnNU1qTXROR1F4WmkwNE9EUTBMVGRpT1RJek1qSXdOemd4WlE9PQ==','1800','2017-09-27 10:46:23','2017-11-03 00:00:00',NULL,NULL),(214,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRZNFpERmhaVGRoTFdNME5qY3RORFF3WXkwNFpEZGhMVEE0WXpnMU1EWTVaalJrWlE9PQ==','1800','2017-09-27 10:49:03','2017-11-03 00:00:00',NULL,NULL),(215,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldJM01UUTJNR0ppTFRCbU0yTXRORFExT0MwNFkyWmpMV0l4WTJJeE5tRmlPVEJsTXc9PQ==','1800','2017-09-27 10:55:05','2017-11-03 00:00:00',NULL,NULL),(216,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFek1tWmhNbU5qTFdJek9HSXRORFZrWWkwNVpEUXlMVGxqTlRRd1lUTXdZekkwTmc9PQ==','1800','2017-09-27 10:57:28','2017-11-03 00:00:00',NULL,NULL),(217,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFNU16UTFNamcxTFRaa1pXUXROR1V6T1MwNU5USXpMVGt6TWpneU1ERmlNR1ZoT1E9PQ==','1800','2017-09-27 10:59:37','2017-11-03 00:00:00',NULL,NULL),(218,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRnd05XTm1OVE0xTFRCa056RXROREEyWVMxaU5URXpMVGd5WWpNM00yVmlNV015TkE9PQ==','1800','2017-09-27 11:05:29','2017-11-03 00:00:00',NULL,NULL),(219,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldNMk1tWmxNMkkzTFdNNE1UVXRORFE0TWkwNU9UWXpMV1pqTUdNNU56WmpNVGRrTkE9PQ==','1800','2017-09-27 11:09:04','2017-11-03 00:00:00',NULL,NULL),(220,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJMFpqTTBOVE0yTFdWbVpEa3RORGRsWWkwNU9ESTFMV1kzT0RJMFpUYzNPR1k0Wmc9PQ==','1800','2017-09-27 11:11:53','2017-11-03 00:00:00',NULL,NULL),(221,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRWbU16ZzJaREkzTFRNNE9ERXRORE0wWXkwNFpUWTRMV0ZrTlRnMU9UWmpOVFV4TkE9PQ==','1800','2017-09-27 11:14:38','2017-11-03 00:00:00',NULL,NULL),(222,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkbE1HTTRZMll6TFdRM1lqRXRORGd6TVMxaU5HUXpMVGcwWVdZd1lXVXdOV0UyWWc9PQ==','1800','2017-09-27 11:17:10','2017-11-03 00:00:00',NULL,NULL),(223,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjek5UaGxPVFk0TFdJMFpERXROR1psT1MxaU9Ea3dMVEpoTUdZelpqVm1aamMxTmc9PQ==','1800','2017-09-27 11:20:20','2017-11-03 00:00:00',NULL,NULL),(224,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRObE9UazBZbVZoTFdJMU5qTXROR016WmkwNE9HWm1MV0V5T0dKbE1XTm1aRGt4Wmc9PQ==','1800','2017-09-27 11:21:37','2017-11-03 00:00:00',NULL,NULL),(225,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJNE5XVmlNelZpTFRWa00yWXRORGxrWmkxaE5UVTVMVE01TVRFNU9XSTFaakV5TkE9PQ==','1800','2017-09-27 11:27:16','2017-11-03 00:00:00',NULL,NULL),(226,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldRNE5HTmxORFppTFRneU9EZ3RORGsyWlMwNVpURTVMV1kxTXpJME5qSTROR1ExWXc9PQ==','1800','2017-09-27 11:29:45','2017-11-03 00:00:00',NULL,NULL),(227,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjME56SXpZVEkzTFRFNU1UVXROR05sTkMxaE1XVmpMVGt5WlRCaVptRmtPREU0TXc9PQ==','1800','2017-09-27 11:32:07','2017-11-03 00:00:00',NULL,NULL),(228,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRrd01UQXdPVEUzTFdJMVlXRXRORFV4WmkwNE0yVmhMVGsxTWpjMVpqUTRaamd4Tnc9PQ==','1800','2017-09-27 11:33:05','2017-11-03 00:00:00',NULL,NULL),(229,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldNMFlqVTBaVGN4TFdJMk1HSXROR015T1MxaFpUYzBMV1ZpWkRKa01EUTFPR1EyWVE9PQ==','1800','2017-09-27 11:35:49','2017-11-03 00:00:00',NULL,NULL),(230,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGa056ZGlNekF5TFRaak5HWXROR1ZsTWkxaVkySTVMVE0zWkRCaU1UUTJPRFUxWXc9PQ==','1800','2017-09-27 11:37:25','2017-11-03 00:00:00',NULL,NULL),(231,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRabU1tUm1PR0ZpTFRBM05HWXROREUzT0MxaE1HVTRMV1JsT0RWaFptSXpabUU0TVE9PQ==','1800','2017-09-27 11:38:48','2017-11-03 00:00:00',NULL,NULL),(232,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJek5XUXpabVl4TFdZM09EQXROR000TmkxaE5ERm1MVGxtWWpVMk5EZ3laREEwTmc9PQ==','1800','2017-09-27 11:43:50','2017-11-03 00:00:00',NULL,NULL),(233,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZNE5tTXpNR0UzTFRNM01ESXROR013WVMxaVlqTXdMVFV3TldGbU9ETm1OelptT0E9PQ==','1800','2017-09-27 11:51:15','2017-11-03 00:00:00',NULL,NULL),(234,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRsaVpXSmxNMlkxTFdaak1HSXROR0V6TkMwNE5qSTNMVFV6WVRneVpqZzBOR0UwTlE9PQ==','1800','2017-09-27 11:52:07','2017-11-03 00:00:00',NULL,NULL),(235,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGa05XVm1OMk01TFROaFkyRXROR1JtWmkxaE4yUm1MVGs1T1RZNU5tSTVORGs1TWc9PQ==','1800','2017-09-27 12:00:30','2017-11-03 00:00:00',NULL,NULL),(236,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFME5UVTFOR0k0TFRnMk9ERXRORGRoWVMwNE9HTTNMV0ZoWXpnM01UTTRPV1ZpWlE9PQ==','1800','2017-09-27 12:04:33','2017-11-03 00:00:00',NULL,NULL),(237,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldObFltRm1OV1UwTFRka05XWXRORGxqT0MxaVpUTXhMVFpqWkdSbU5qSTBOelJsWlE9PQ==','1800','2017-09-27 12:07:51','2017-11-03 00:00:00',NULL,NULL),(238,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRBMFpqTTRPV0l6TFRVMlkyTXROR0k1TVMwNE9USmlMVEprT1dWbE1qUTJOelZoTmc9PQ==','1800','2017-09-27 12:09:38','2017-11-03 00:00:00',NULL,NULL),(239,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKbE1qSm1OV0V6TFRFNVpHTXRORGRrWXkwNFpEVmtMVFZrTm1FME5UVmtPRFkxTkE9PQ==','1800','2017-09-27 12:11:18','2017-11-03 00:00:00',NULL,NULL),(240,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRZM05UQTFNekF4TFROaU16VXRORE0zWVMwNE1qaGtMVGhqWTJZNFlXUTBaVFV3Tnc9PQ==','1800','2017-09-27 12:16:40','2017-11-03 00:00:00',NULL,NULL),(241,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRka09UaGlZVEpsTFdabU4yTXRORGhrTnkxaU0yWmhMV1kyTUdZek5tSXhNMlppWVE9PQ==','1800','2017-09-27 12:21:30','2017-11-03 00:00:00',NULL,NULL),(242,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFM01HUTBaREF3TFRJeFpXSXRORGMyT1MxaE56WTFMVGhrTnpobFptUmpOVGRrWlE9PQ==','1800','2017-09-27 12:24:24','2017-11-03 00:00:00',NULL,NULL),(243,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZM05XUXpOekZoTFdZMlpqY3ROR016TWkwNFl6WTBMVEkyTXprd1pEQTJabUk1TVE9PQ==','1800','2017-09-27 12:25:26','2017-11-03 00:00:00',NULL,NULL),(244,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVM1pESTNZMlZoTFRKbFptTXROREJoWXkxaU1HWTNMVFk0TWpRek9ERTBPV1F5Tmc9PQ==','1800','2017-09-27 12:26:28','2017-11-03 00:00:00',NULL,NULL),(245,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKalptTmlNbVJoTFRaaFpEUXRORGswT1MwNE9ESTBMVEk0WXpsaVpHVmpOVGcxWkE9PQ==','1800','2017-09-27 12:28:11','2017-11-03 00:00:00',NULL,NULL),(246,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFNFltRTVNRFZoTFdOa1lUTXRORFZqWmkwNVpUY3dMVGxpTldJMFlUQTRPVFptTUE9PQ==','1800','2017-09-27 12:29:03','2017-11-03 00:00:00',NULL,NULL),(247,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRreE1qY3pNRGt6TFdVNE1qa3RORE0yWkMwNU5qVTRMVFJqT0dSak56TTNPR0ZpWVE9PQ==','1800','2017-09-27 12:30:43','2017-11-03 00:00:00',NULL,NULL),(248,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKbE16a3pPRFZpTFRrd05UWXROREJpT0MxaU5qSXlMV1V5TlRNek5EazNNRFprTmc9PQ==','1800','2017-09-27 12:31:16','2017-11-03 00:00:00',NULL,NULL),(249,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFNU1HRXlOR1kzTFRWa01EZ3RORGt4WmkwNU5ETXpMV0kyTlRZMk0yRm1Namd5TWc9PQ==','1800','2017-09-27 12:36:25','2017-11-03 00:00:00',NULL,NULL),(250,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlROaVpHVXhZVEUyTFRnNE1HUXROR1ZpTXkwNE5EWmpMVEJsT0RZeU56QXdNak16TVE9PQ==','1800','2017-09-27 12:37:20','2017-11-03 00:00:00',NULL,NULL),(251,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRrek1qa3hOV1ExTFRSaE56Y3ROREF3TlMxaE56RmlMVGd4T0dWbU0ySXhNek5rTkE9PQ==','1800','2017-09-27 12:39:08','2017-11-03 00:00:00',NULL,NULL),(252,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjMU1UZGxPVE5sTFRZelpUZ3ROR1ZsT0MxaVl6QmpMVGM0TmpJeU4yVTRObUU1T1E9PQ==','1800','2017-09-27 12:42:44','2017-11-03 00:00:00',NULL,NULL),(253,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRNNE1HSm1aR0U1TFRnME5UVXRORFJsWVMxaE9HRTNMV0kzWm1GbE1qY3pZMlpsTkE9PQ==','1800','2017-09-27 12:51:39','2017-11-03 00:00:00',NULL,NULL),(254,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRME0yRTFOVGs0TFRsaU1tRXRORGRtTlMxaFl6ZGtMV0U1TkdRNVl6UTRZelkzTmc9PQ==','1800','2017-09-27 13:14:16','2017-11-03 00:00:00',NULL,NULL),(255,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRZNVl6UTFOR0ZqTFdNME5qTXRORE01WlMxaE4yTmpMVGhsTXpJMlkyVXpObU0wTnc9PQ==','1800','2017-09-27 13:16:36','2017-11-03 00:00:00',NULL,NULL),(256,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldRNFptVXlNVE5qTFRNNE9HUXROR0psT1MwNU9EUXlMVFJqTUdVMFltWTBZV1EyWWc9PQ==','1800','2017-09-27 13:17:30','2017-11-03 00:00:00',NULL,NULL),(257,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldRd09ERTRZVE5qTFdVME5qRXROREJtTnkwNU5EWmpMV0kwWldZd01ESmlZVEkyTnc9PQ==','1800','2017-09-27 13:21:41','2017-11-03 00:00:00',NULL,NULL),(258,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKbE1tWmxZekkwTFRObU5tTXROREZsTXkwNE1tRXdMVFkyWmpFeU5qTTBOMkpsTUE9PQ==','1800','2017-09-27 13:29:31','2017-11-03 00:00:00',NULL,NULL),(259,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldNME5qVmhOVE0wTFRobE56a3ROR1ZoTkMwNVl6ZzFMVEk1WW1FMFlqTm1PRGN4TWc9PQ==','1800','2017-09-27 14:40:18','2017-11-03 00:00:00',NULL,NULL),(260,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldSaE1UazJNMlV6TFRjMVlUZ3ROR0l3TVMwNU9ERTRMVFk1WW1FMFpXWXhaV1psTWc9PQ==','1800','2017-09-27 14:42:03','2017-11-03 00:00:00',NULL,NULL),(261,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldJeVl6VmtObU0wTFRreFpXSXROR1ZpTmkxaU4yRTVMVFEwWWpGalpqSmhaR013TUE9PQ==','1800','2017-09-27 14:43:23','2017-11-03 00:00:00',NULL,NULL),(262,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52Yldaa01EZzVPRGhsTFdObU9ERXROR1prWWkxaFpXWmhMVEUxTVdKa01XUXpObU0wTnc9PQ==','1800','2017-09-27 14:45:23','2017-11-03 00:00:00',NULL,NULL),(263,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVd09EUmlaR013TFdFeE1qTXRORGM1TXkxaU5HRmhMV1JpWm1ZeVlUUTFaalZpWVE9PQ==','1800','2017-09-27 14:46:25','2017-11-03 00:00:00',NULL,NULL),(264,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGbFpqQmxNRGhpTFdFMVpEY3RORGc1TkMwNVpHTTVMV1kxTXpKaVl6bGtNalZoWXc9PQ==','1800','2017-09-27 14:47:40','2017-11-03 00:00:00',NULL,NULL),(265,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRRNU5qRmlZbVprTFdFNE5qRXROR1k1T0MwNE16ZzVMV0pqTXpObE1XTXhabVZqWlE9PQ==','1800','2017-09-27 14:53:01','2017-11-03 00:00:00',NULL,NULL),(266,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldFM1pXRmtNakJsTFRjeU0yUXRORFZpWkMxaU9XUmtMVEEwTlRrME9UQmtZV1V4Wmc9PQ==','1800','2017-09-27 14:56:22','2017-11-03 00:00:00',NULL,NULL),(267,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRKbE5EVmhZV0ZpTFdJd09HVXROR1ZtWlMxaE1qQmpMV1V6TURGa1ptRTRNelJsTlE9PQ==','1800','2017-09-27 15:01:36','2017-11-03 00:00:00',NULL,NULL),(268,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRBME5EWTRNMlprTFdFMU1Ua3RORFkwTmkwNU5EbGlMVE5oT1RJeU1Ea3dOekl4WXc9PQ==','1800','2017-09-27 15:04:16','2017-11-03 00:00:00',NULL,NULL),(269,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRNNE5UVTFOR0ZpTFRnd01URXRORFJoTWkwNE9XRmlMV1UxTXpabU5HWmhNMlV6WXc9PQ==','1800','2017-09-27 15:04:55','2017-11-03 00:00:00',NULL,NULL),(270,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRjMFl6TXpOMlZpTFRBNE16VXRORGN5TXkwNE1ESTFMVFZoWmprd09HTmxNelk1WlE9PQ==','1800','2017-09-27 15:08:51','2017-11-03 00:00:00',NULL,NULL),(271,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRrM1lUQTBaR1l5TFdKbVptWXRORGRqWkMwNE9ERm1MVEUzWVdNeU9HVmtPRGswWXc9PQ==','1800','2017-09-27 15:09:58','2017-11-03 00:00:00',NULL,NULL),(272,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGalpHRTNZelF6TFdNeE9ERXROREprWXkxaFpXRTNMVGRrT1dVeE5XSm1Zamc1TWc9PQ==','1800','2017-09-27 15:11:07','2017-11-03 00:00:00',NULL,NULL),(273,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKaVlUQmtNV1V3TFRVeE9XWXRORGxqTkMwNVlqZzVMV0kyTlRSak56azFOVEE1WXc9PQ==','1800','2017-09-27 15:13:04','2017-11-03 00:00:00',NULL,NULL),(274,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGaU1qWmhZV001TFRNek5UWXRORGsxWWkwNE5HSXlMVFk1WVRabE16WTRPVEV3WWc9PQ==','1800','2017-09-27 15:16:15','2017-11-03 00:00:00',NULL,NULL),(275,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGbFpXSXhNVFE1TFRsbVlUWXROR1E0WlMxaE9URXpMV1UwT1RFME9EUTJaak5pWVE9PQ==','1800','2017-09-27 15:17:30','2017-11-03 00:00:00',NULL,NULL),(276,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFM1lUYzJaV1kyTFRNM05HRXROREJrWkMxaFlUYzBMV1JoWm1KaU5UazBOalV6TkE9PQ==','1800','2017-09-27 15:24:34','2017-11-03 00:00:00',NULL,NULL),(277,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRsak1URmlZVFJrTFdJME5qRXRORFJtT0MxaE5EWTNMVFk1WWpKbU16azNZVFJpWmc9PQ==','1800','2017-09-27 15:33:21','2017-11-03 00:00:00',NULL,NULL),(278,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKbE9UTmpZekEyTFdFNE1XTXROREJoTVMwNE16TTBMV0ppT0dJNE1HWXhNVGxtTVE9PQ==','1800','2017-09-27 15:34:42','2017-11-03 00:00:00',NULL,NULL),(279,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRKak56WmlNRFZqTFRReE5UWXROR0ZrTWkwNU1qSmtMVFUzT1RGaFlqQmhNMlZqT1E9PQ==','1800','2017-09-27 15:35:47','2017-11-03 00:00:00',NULL,NULL),(280,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldNek56QXpZemRtTFRNd01qWXRORFUwTWkwNU9USXhMV1UxTVRjME5ETTRZemM1TVE9PQ==','1800','2017-09-27 15:37:52','2017-11-03 00:00:00',NULL,NULL),(281,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZeU1qTmxNMlUzTFRGaE5EUXRORFkyWXkwNVpXSmpMVGd3WTJVeE5EVmxZemM0WVE9PQ==','1800','2017-09-27 15:41:55','2017-11-03 00:00:00',NULL,NULL),(282,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJMU5qVTBOakl4TFRReE1XRXROR1k1WWkwNU5tUTFMVGxtT0RZM05XRTVNMk5tWVE9PQ==','1800','2017-09-27 15:44:34','2017-11-03 00:00:00',NULL,NULL),(283,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldJNE1qZGlNRFl3TFdKaVlqRXRORGMwWlMxaU5HRmlMVEU1WlRrMk1XVTFNV1EyTUE9PQ==','1800','2017-09-27 15:47:03','2017-11-03 00:00:00',NULL,NULL),(284,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVMVlqazJOREJqTFROa1pEa3RORGxoT1MxaE1qQmxMV0k0TURGak5ERm1abVZoWmc9PQ==','1800','2017-09-27 15:47:51','2017-11-03 00:00:00',NULL,NULL),(285,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRNNE9EbGhZVGMyTFRVeU9Ua3RORGd4WkMxaU9EVTNMVEl6WXpoaFl6RXpOREJqWWc9PQ==','1800','2017-09-27 15:48:50','2017-11-03 00:00:00',NULL,NULL),(286,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRaaE9EbGpNV0ZrTFRWa1pqRXROREl4T0MxaE9EWmxMVEV4TVRJMVltWmpZMkZtT0E9PQ==','1800','2017-09-27 15:51:06','2017-11-03 00:00:00',NULL,NULL),(287,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRkbU1XSTBZV1JsTFdVell6VXRORGxtTlMwNE9EVm1MV0ZqWVRGaE1tWTBNVFpqTnc9PQ==','1800','2017-09-27 15:53:31','2017-11-03 00:00:00',NULL,NULL),(288,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRsbFlXUTNOamhrTFRBNE9UY3RORE5pTkMwNE1UUXpMVFF5TVROa1pUWm1PVFV5T0E9PQ==','1800','2017-09-27 15:54:16','2017-11-03 00:00:00',NULL,NULL),(289,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZNU1tTmtOV1ZtTFRCaVptWXROR0l3TnkwNE56ZzBMV1JrTjJRNE5HRmpORGRpTkE9PQ==','1800','2017-09-27 15:59:06','2017-11-03 00:00:00',NULL,NULL),(290,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52Yldaak5qQmhNbVEyTFRSbE1tRXRORFE0TWkwNFpERTBMVFE0WXpWbU1EWmtZamt4TXc9PQ==','1800','2017-09-27 16:00:48','2017-11-03 00:00:00',NULL,NULL),(291,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldZMlpHSmtNbVJpTFdKaE1qTXROR0ppTXkwNFpUTXhMVEJoTnpjell6VTJabUV4WVE9PQ==','1800','2017-09-27 16:06:24','2017-11-03 00:00:00',NULL,NULL),(292,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldRelpUUmlaRE01TFdaaE1UY3RORE0xWXkwNU5qSXhMV1kzT0RFd05UVmxNekl5TlE9PQ==','1800','2017-09-27 16:07:40','2017-11-03 00:00:00',NULL,NULL),(293,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldSbU5qRTVNVGt3TFRVMlkyVXRORGMwWlMxaE5qWmtMVEl4WWpabE1EUm1NMkV4WXc9PQ==','1800','2017-09-27 16:10:49','2017-11-03 00:00:00',NULL,NULL),(294,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKak56azRZMlF4TFdJMU9XWXRORFJpWkMxaE9UYzNMVFl4Tm1GaFptTmpaak0xTWc9PQ==','1800','2017-09-27 16:13:30','2017-11-03 00:00:00',NULL,NULL),(295,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVNFpHWm1OalZqTFdReU9UUXRORFF5TmkwNVpqY3dMV05oWVdabFpHWXhOek5tTkE9PQ==','1800','2017-09-27 16:25:31','2017-11-03 00:00:00',NULL,NULL),(296,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRJd05UUmtNMll3TFdSbU5UWXROR1kwWWkwNFpEYzJMV05sTVRobE1HVXhZVFZrTnc9PQ==','1800','2017-09-27 16:29:56','2017-11-03 00:00:00',NULL,NULL),(297,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVd1pUZ3hZVGsxTFRrMU5qZ3RORFpqTVMxaE9EWTVMVEU1WW1VMU1qa3hNbVk1Tmc9PQ==','1800','2017-09-27 16:31:10','2017-11-03 00:00:00',NULL,NULL),(298,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGaU5qazVabVpqTFRnNVpqSXROR0ppTXkxaU56bGlMVFF6WkRjeE1tWmxZVFUzTkE9PQ==','1800','2017-09-27 16:32:13','2017-11-03 00:00:00',NULL,NULL),(299,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRWaU0yWmxaVEE0TFdSbFkySXRORGd3TUMwNU5qQXhMVFpqWkRFellUTTVOV0V3Tnc9PQ==','1800','2017-09-27 16:33:24','2017-11-03 00:00:00',NULL,NULL),(300,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldRelltUTFZVEl3TFRFMU9XRXRORGhtWVMwNVlqVmpMVGhpT1RabFpHUXhOV05oWVE9PQ==','1800','2017-09-27 16:34:26','2017-11-03 00:00:00',NULL,NULL),(301,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldKbU9HWTJNRFZoTFdGbFltTXROR0l5WVMwNE9ERXdMVFUzWkdFeE1XUmxOV0kzWXc9PQ==','1800','2017-09-27 16:40:53','2017-11-03 00:00:00',NULL,NULL),(302,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRGaVptSmhaakV3TFdRMlpqQXRORFZoT1MwNU1EVTNMVGszT0dKbU9UVmtPVGc0TWc9PQ==','1800','2017-09-27 16:46:13','2017-11-03 00:00:00',NULL,NULL),(303,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRVMVlURTBOalpsTFRsa1pUVXRORFZqTmkxaVpETXpMV1ZtTnpKalpEVXdORE0yWlE9PQ==','1800','2017-09-27 16:53:57','2017-11-03 00:00:00',NULL,NULL),(304,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRZeFpqTXhNamd6TFRJelpETXROREkzWWkxaE9HUTNMV0V3TmpWbE9HUm1OalJoTVE9PQ==','1800','2017-09-27 17:30:37','2017-11-03 00:00:00',NULL,NULL),(305,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldJM05XTTJNakptTFRRMFlqWXRORFZpWmkxaE5ETTVMVFE0WVdRek16TmlOMkV5T1E9PQ==','1800','2017-09-27 17:50:03','2017-11-03 00:00:00',NULL,NULL),(306,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRBd1pqUXpaR00zTFRsalpqWXROREZpTmkxaE5ETXlMVEZrTkdWbU56bG1PVGs1T1E9PQ==','1800','2017-09-27 17:55:44','2017-11-03 00:00:00',NULL,NULL),(307,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRFd056RmtaRGd4TFRJMk4yTXRORFl5TkMwNU1qZzVMVEZpWmpjeU16RTVZMkptT0E9PQ==','1800','2017-09-27 19:04:43','2017-11-03 00:00:00',NULL,NULL),(308,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRsaU5qaG1OamhrTFdKaFlXRXROREE1WlMxaE9EQXdMVGhtT0RNNU9ESmhNR1ZoTlE9PQ==','1800','2017-10-06 16:26:54','2017-11-03 00:00:00',NULL,NULL),(309,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRreU5UWmpZVFJrTFRobE9XTXRORE16TWkxaE1qUmpMVFkyWXpaaU1URTFPREJpWWc9PQ==','1800','2017-10-10 13:07:09','2017-11-03 00:00:00',NULL,NULL),(310,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldVMU5EWXpOVGcyTFRSbU16QXROR1prWlMxaFlUazRMVE0wT0Rnd05URXpOamsyTWc9PQ==','1800','2017-11-03 16:42:30','2017-11-03 00:00:00',NULL,NULL),(311,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRCbE1EaGpPV1ZrTFdWaU1tWXRORFpsTUMwNE9UQTVMV1UxT0RZeVlXUTFZVGM1WkE9PQ==','1800','2017-11-03 16:44:32','2017-11-03 00:00:00',NULL,NULL),(312,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YlRNeFlUVTNObVl6TFRJNE5qUXROR1l6WmkwNU5EaGtMV0kxTmpnd05HSTBPRGt3TXc9PQ==','1800','2017-11-03 16:45:03','2017-11-03 00:00:00',NULL,NULL),(313,1,1,NULL,'Y21SdmJtVndkV1JwUUc5emFYVnpMbU52YldGbE1EYzBOekptTFRjek5UWXROR1JpTlMxaVlXWTNMVE16TTJOallXTXlaakEyTmc9PQ==','1800','2017-11-03 16:48:59',NULL,NULL,NULL);
/*!40000 ALTER TABLE `osi_user_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osi_user_operation_excl`
--

DROP TABLE IF EXISTS `osi_user_operation_excl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osi_user_operation_excl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUSINESS_GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `FUNC_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `OP_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osi_user_operation_excl`
--

LOCK TABLES `osi_user_operation_excl` WRITE;
/*!40000 ALTER TABLE `osi_user_operation_excl` DISABLE KEYS */;
/*!40000 ALTER TABLE `osi_user_operation_excl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ems_new1'
--
/*!50003 DROP PROCEDURE IF EXISTS `menuTreeData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `menuTreeData`(
p_usrId int)
BEGIN

      DECLARE
         v_query1 LONGTEXT; DECLARE 

         v_query2 LONGTEXT; DECLARE 

         v_query3 LONGTEXT; DECLARE 
		 v_orderByquery LONGTEXT; DECLARE 
         v_query4 LONGTEXT; DECLARE 

         v_query5 LONGTEXT; DECLARE 

         v_menuIds LONGTEXT; DECLARE 

         v_funcExl LONGTEXT;

      DECLARE
         v_levels int; DECLARE 
         v_queue_length int;
		 SET v_query1 = '';
		 SET v_query2 = '';
		 SET v_query3 = '';
		 SET v_orderByquery = '';
		 SET v_query4 = '';
		 SET v_query5 = '';
		 SET v_queue_length = 2;
		
            SELECT GROUP_CONCAT(DISTINCT ufe.func_id  
			ORDER BY  ufe.func_id ASC SEPARATOR ',') func_id into v_funcExl 
			FROM osi_user_func_excl ufe where ufe.user_id =p_usrId;
        
			
		 SELECT GROUP_CONCAT(DISTINCT me.menu_id ORDER BY  me.menu_id ASC SEPARATOR ',') menu_id into v_menuIds
			FROM osi_user  AS u, osi_responsibilities  AS r, OSI_MENU_RESP mr,osi_menu_entries me, osi_resp_user  AS ru
		WHERE 
         u.id = ru.user_id AND 
         ru.resp_id = r.id AND 
         r.id = mr.RESP_ID AND
         u.id = p_usrId AND 
         r.start_date <= (CAST(now() AS DATE)) AND 
         r.end_date >= (CAST(now() AS DATE)) AND 
         ru.start_date <= (CAST(now() AS DATE)) AND 
         ru.end_date >= (CAST(now() AS DATE)) AND
		    (me.MENU_ID = mr.MENU_ID or mr.MENU_ID = me.SUB_MENU_ID) AND
         me.FUNC_ID not in (select func_id from osi_user_func_excl where USER_ID = p_usrId);

      IF v_menuIds IS NOT NULL
         THEN

            SET v_levels = 5;
			SET v_query1 = Concat('select a.*, b.default_resp, b.rpt_grp_id from ( select ',cast(v_levels as CHAR),' levels, t1.sub_menu_id lev1_sub, null lev1_menu_id, t1.func_id, null lev1_func_value, rs.menu_name AS lev1_prompt, t1.seq as lev1_seq');
			SET v_query2 = ' FROM osi_menus rs LEFT JOIN osi_menu_entries AS t1 ON t1.sub_menu_id = rs.id ';
			SET v_query3 = CONCAT(' WHERE t1.active = 1 and rs.active = 1 and rs.id = t1.sub_menu_id and t1.sub_menu_id in (' , v_menuIds , ')');
			SET v_orderByquery = ' order by a.lev1_seq';
			SET v_query4 = CONCAT(') a left join (SELECT m.id menu_id, ru.default_resp, m.rpt_grp_id FROM osi_user u, osi_responsibilities r, OSI_MENU_RESP mr, osi_menus m, osi_resp_user ru where m.active = 1 and m.id = mr.MENU_ID and u.id = ru.user_id and r.id = mr.RESP_ID and ru.resp_id = r.id and u.id =', cast(p_usrId as CHAR) , ' and  r.start_date <= (CAST(now() AS DATE)) AND r.end_date >= (CAST(now() AS DATE)) AND ru.start_date <= (CAST(now() AS DATE)) and ru.end_date >= (CAST(now() AS DATE))) b on menu_id = lev1_sub');
            WHILE (v_queue_length <= v_levels)
               DO
				SET v_query1 = Concat(v_query1, ', t',cast(v_queue_length as CHAR),'.sub_menu_id lev' , cast(v_queue_length as CHAR),'_sub, t',cast(v_queue_length as CHAR),'.menu_id lev',cast(v_queue_length as CHAR),'_menu_id, t',cast(v_queue_length as CHAR),'.func_id lev',cast(v_queue_length as CHAR),'_func_id, concat(f',cast(v_queue_length as CHAR),'.func_value, IFNULL(f',cast(v_queue_length as CHAR),'.parameters,'''')) lev',cast(v_queue_length as CHAR),'_func_value, t',cast(v_queue_length as CHAR),'.prompt as lev' ,cast(v_queue_length as CHAR),'_prompt, t',cast(v_queue_length as CHAR),'.seq as lev',cast(v_queue_length as CHAR),'_seq'); 
					IF v_funcExl IS NULL
						THEN
							 SET v_query2 = Concat(v_query2,' LEFT JOIN osi_menu_entries AS t',cast(v_queue_length as CHAR),' ON t',cast(v_queue_length as CHAR),'.active = 1 and t',cast(v_queue_length as CHAR),'.menu_id = t',cast((v_queue_length-1) as CHAR),'.sub_menu_id LEFT JOIN osi_functions f',cast(v_queue_length as CHAR),' on t',cast(v_queue_length as CHAR),'.func_id = f',cast(v_queue_length as CHAR),'.id  and f',cast(v_queue_length as CHAR),'.active=1 ');
					ELSE
						 SET v_query2 = Concat(v_query2, ' LEFT JOIN osi_menu_entries AS t',cast(v_queue_length as CHAR),' ON t',cast(v_queue_length as CHAR),'.active = 1 and t',cast(v_queue_length as CHAR),'.menu_id = t',cast((v_queue_length-1) as CHAR),'.sub_menu_id  and (t',cast(v_queue_length as CHAR),'.func_id not in (',cast(v_funcExl as CHAR),') or t',cast(v_queue_length as CHAR),'.func_id is null) LEFT JOIN osi_functions f',cast(v_queue_length as CHAR),' on t',cast(v_queue_length as CHAR),'.func_id = f',cast(v_queue_length as CHAR),'.id and f',cast(v_queue_length as CHAR),'.active=1 ');
						END IF;
				SET v_orderByquery = CONCAT(v_orderByquery,', a.lev',cast(v_queue_length as CHAR),'_seq ');
				SET v_queue_length = v_queue_length + 1;
			  END WHILE; 	
			SET @v_query4 = Concat(v_query4,v_orderByquery);
			SET @v_query5 = Concat(v_query1,v_query2,v_query3,v_query4);
	   ELSE
			SET @v_query5 ='select 1';	
		END IF; 

		  --  select @v_query5;
		   PREPARE stmt FROM @v_query5;
		   EXECUTE stmt;
		   DEALLOCATE PREPARE stmt;  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-03 16:51:58
