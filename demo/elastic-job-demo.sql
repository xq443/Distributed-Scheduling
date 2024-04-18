/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : elastic-job-demo

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2020-05-08 11:23:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_file_custom
-- ----------------------------
DROP TABLE IF EXISTS `t_file_custom`;
CREATE TABLE `t_file_custom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `backedUp` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file_custom
-- ----------------------------
INSERT INTO `t_file_custom` VALUES ('1', '文件1', '内容1', 'text', '1');
INSERT INTO `t_file_custom` VALUES ('2', '文件2', '内容2', 'text', '1');
INSERT INTO `t_file_custom` VALUES ('3', '文件3', '内容3', 'text', '1');
INSERT INTO `t_file_custom` VALUES ('4', '文件4', '内容4', 'image', '1');
INSERT INTO `t_file_custom` VALUES ('5', '文件5', '内容5', 'image', '1');
INSERT INTO `t_file_custom` VALUES ('6', '文件6', '内容6', 'text', '1');
INSERT INTO `t_file_custom` VALUES ('7', '文件6', '内容7', 'radio', '1');
INSERT INTO `t_file_custom` VALUES ('8', '文件8', '内容8', 'radio', '1');
INSERT INTO `t_file_custom` VALUES ('9', '文件9', '内容9', 'vedio', '1');
INSERT INTO `t_file_custom` VALUES ('10', '文件10', '内容10', 'vedio', '1');
INSERT INTO `t_file_custom` VALUES ('11', '文件11', '内容11', 'vedio', '1');
INSERT INTO `t_file_custom` VALUES ('12', '文件12', '内容12', 'vedio', '1');
INSERT INTO `t_file_custom` VALUES ('13', '文件13', '内容13', 'image', '1');
INSERT INTO `t_file_custom` VALUES ('14', '文件14', '内容14', 'text', '1');
INSERT INTO `t_file_custom` VALUES ('15', '文件15', '内容15', 'image', '1');
INSERT INTO `t_file_custom` VALUES ('16', '文件16', '内容16', 'text', '1');
INSERT INTO `t_file_custom` VALUES ('17', '文件17', '内容17', 'radio', '1');
INSERT INTO `t_file_custom` VALUES ('18', '文件18', '内容18', 'image', '1');
INSERT INTO `t_file_custom` VALUES ('19', '文件19', '内容19', 'radio', '1');
INSERT INTO `t_file_custom` VALUES ('20', '文件20', '内容20', 'vedio', '1');
