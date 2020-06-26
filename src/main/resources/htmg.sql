/*
 Navicat Premium Data Transfer

 Source Server         : Anon
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 49.232.3.90:3306
 Source Schema         : htmg

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 19/05/2020 19:47:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Role
-- ----------------------------
DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `UserID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `Role_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Role
-- ----------------------------
BEGIN;
INSERT INTO `Role` VALUES (1, 'ROLE_ADMIN', 'ROLE_ADMIN', 'fb08e6c7-1cc2-43a0-b26b-01c4f318895e');
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `ID` varchar(255) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO `User` VALUES ('fb08e6c7-1cc2-43a0-b26b-01c4f318895e', 'Anon', '$2a$10$/viQGLV9KIHp5SrW5GBDbuft/wR/2jCfIFuNXA7pTkXkY/.d/V8By');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
