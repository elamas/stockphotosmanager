#USE stockphotosmanager-test;

#Por ahora para evitar problemas quito todos los "CHARACTER SET utf8 COLLATE utf8_unicode_ci" de los varchR

CREATE TABLE `sites` (
  `sit_id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `sit_name` varchar(64) UNIQUE NOT NULL,
  `sit_num_keywords` int(10),
  `sit_num_categories` int(10)
)
ENGINE=InnoDB;

CREATE TABLE `categories` (
  `cat_id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `sit_id` int(10),
  `cat_name` varchar(64) NOT NULL
)
ENGINE=InnoDB;

CREATE TABLE `keywords` (
  `key_id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `key_name` varchar(64) UNIQUE NOT NULL
)
ENGINE=InnoDB;

CREATE TABLE `keywords_categories` (
  `key_id` int(10),
  `cat_id` int(10)
)
ENGINE=InnoDB;

CREATE TABLE `photos` (
  `pho_id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `pho_dvd` varchar(64) NOT NULL,
  `pho_file_name` varchar(64) NOT NULL,
  `pho_upload_date_millis` int(10) NOT NULL,
  `pho_upload_date_string_madrid` varchar(64) NOT NULL,
  `pho_comments` varchar(1024)
)
ENGINE=InnoDB;

CREATE TABLE `photos_keywords` (
  `pho_id` int(10),
  `key_id` int(10)
)
ENGINE=InnoDB;

CREATE TABLE `photos_sites` (
  `pho_id` int(10),
  `sit_id` int(10),
  `ps_status` int(10) NOT NULL,
  `ps_uplodad_date_millis` int(10) NOT NULL,
  `ps_uplodad_date_string_madrid` varchar(64) NOT NULL,
  `ps_last_revision_date_millis` int(10),
  `ps_last_revision_date_string_madrid` varchar(64),
  `ps_comments` varchar(1024)
)
ENGINE=InnoDB;

/*
ALTER TABLE `categories` ADD FOREIGN KEY (`sit_id`) REFERENCES `sites` (`sit_id`);

ALTER TABLE `keywords_categories` ADD FOREIGN KEY (`key_id`) REFERENCES `keywords` (`key_id`);

ALTER TABLE `keywords_categories` ADD FOREIGN KEY (`cat_id`) REFERENCES `categories` (`cat_id`);

ALTER TABLE `photos_keywords` ADD FOREIGN KEY (`pho_id`) REFERENCES `photos` (`pho_id`);

ALTER TABLE `photos_keywords` ADD FOREIGN KEY (`key_id`) REFERENCES `keywords` (`key_id`);

ALTER TABLE `photos_sites` ADD FOREIGN KEY (`pho_id`) REFERENCES `photos` (`pho_id`);

ALTER TABLE `photos_sites` ADD FOREIGN KEY (`sit_id`) REFERENCES `sites` (`sit_id`)
*/ 

#insert into photos(`pho_dvd`, `pho_file_name`, `pho_upload_date_millis`, `pho_upload_date_string_madrid`, `pho_comments`) values('dvdtest', 'filetest', 1111, '1111', 'bcbbcbv');

