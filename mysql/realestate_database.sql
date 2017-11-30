CREATE DATABASE IF NOT EXISTS realestate;

USE realestate;

CREATE USER IF NOT EXISTS 'admin'@'realestate' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON * . * TO 'admin'@'realestate';

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `Roles`;
CREATE TABLE `Roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Roles` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `Roles` (`name`) VALUES ('ROLE_OWNER');
INSERT INTO `Roles` (`name`) VALUES ('ROLE_BUYER');

DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`roleId`) REFERENCES `Roles`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('admin1', 'admin1', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_ADMIN'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('admin2', 'admin2', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_ADMIN'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('admin3', 'admin3', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_ADMIN'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('owner1', 'owner1', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_OWNER'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('owner2', 'owner2', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_OWNER'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('owner3', 'owner3', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_OWNER'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('buyer1', 'buyer1', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_BUYER'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('buyer2', 'buyer2', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_BUYER'));

INSERT INTO `Users` (`login`, `password`, `roleId`) VALUES ('buyer3', 'buyer3', (SELECT id
                                                            FROM `Roles`
                                                            WHERE `name` = 'ROLE_BUYER'));

DROP TABLE IF EXISTS `DealType`;
CREATE TABLE `DealType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `DealType` (`name`) VALUES ('SALE');
INSERT INTO `DealType` (`name`) VALUES ('PURCHASE');

DROP TABLE IF EXISTS `RealEstateStatus`;
CREATE TABLE `RealEstateStatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `RealEstateStatus` (`name`) VALUES ('NEW');
INSERT INTO `RealEstateStatus` (`name`) VALUES ('APPROVED');
INSERT INTO `RealEstateStatus` (`name`) VALUES ('DELETED');

DROP TABLE IF EXISTS `RealEstates`;
CREATE TABLE `RealEstates` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `dealTypeId` INT(11) NOT NULL,
  `title` VARCHAR(512) NOT NULL,
  `description` TEXT NOT NULL,
  `ownerId` INT(11) NOT NULL,
  `statusId` INT(11) NOT NULL,
  `adminNote` VARCHAR(512) NULL,
  `address` VARCHAR(512) NOT NULL,
  `pictureCode` VARCHAR(512) NULL,
  `price` decimal(18,2) NOT NULL,
  `nearbyLocations` VARCHAR(1024) NULL,
  `lat` FLOAT(10, 6) NOT NULL,
  `lng` FLOAT(10, 6) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`dealTypeId`) REFERENCES `DealType`(id) ON UPDATE CASCADE,
  FOREIGN KEY (`ownerId`) REFERENCES `Users`(id) ON UPDATE CASCADE,
  FOREIGN KEY (`statusId`) REFERENCES `RealEstateStatus`(id) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Messages`;
CREATE TABLE `Messages` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `senderId` INT(11) NOT NULL,
  `recipientId` INT(11) NOT NULL,
  `message` VARCHAR(512) NOT NULL,
  `statusId` INT(11) NOT NULL,
  `propertyId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`recipientId`) REFERENCES `Users`(id),
  FOREIGN KEY (`propertyId`) REFERENCES `RealEstates`(id),
  FOREIGN KEY (`senderId`) REFERENCES `Users`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `HotDeals`;
CREATE TABLE `HotDeals` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `propertyId` INT(11) NOT NULL,
  `hits` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`propertyId`) REFERENCES `RealEstates`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE `Pictures` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `propertyId` int(11) NOT NULL,
--   `pictureCode` VARCHAR(128) NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `propertyId` (`propertyId`),
--   CONSTRAINT `items_ibfk_1` FOREIGN KEY (`propertyId`) REFERENCES `RealEstates` (id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET foreign_key_checks = 1;


