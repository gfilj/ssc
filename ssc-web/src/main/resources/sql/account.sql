/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : ssc

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-07-02 21:11:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `pay_password` varchar(32) DEFAULT NULL COMMENT '金额密码（涉及提现使用）',
  `payee_name` varchar(255) DEFAULT NULL COMMENT '收款人姓名',
  `remain_amount` decimal(20,2) NOT NULL DEFAULT '0.00',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'test', '96e79218965eb72c92a549dd5a330112', null, null, '0.00', '1498283591', '1498298980');
INSERT INTO `account` VALUES ('2', 'test11', '111', null, null, '0.00', '1498292584', '1498292584');

-- ----------------------------
-- Table structure for `bank`
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL COMMENT '银行卡所属用户',
  `card_number` int(11) DEFAULT NULL COMMENT '银行卡号',
  `open_bank` varchar(255) DEFAULT NULL COMMENT '开户行',
  `province` varchar(255) DEFAULT NULL COMMENT '开户省份',
  `city` varchar(255) DEFAULT NULL COMMENT '开户城市',
  `lattice_point` varchar(255) DEFAULT NULL COMMENT '开户网点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank
-- ----------------------------

-- ----------------------------
-- Table structure for `group_report`
-- ----------------------------
DROP TABLE IF EXISTS `group_report`;
CREATE TABLE `group_report` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_report
-- ----------------------------

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL COMMENT '创建链接的用户ID',
  `channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `open_type` tinyint(1) unsigned DEFAULT NULL COMMENT '开户类型（1：普通用户；2：渠道）',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态（1：正常；2：异常）',
  `rebate` decimal(10,2) DEFAULT NULL COMMENT '返点',
  `valid_day` tinyint(1) unsigned DEFAULT NULL COMMENT '有效天数',
  `create_time` int(10) DEFAULT NULL,
  `update_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------

-- ----------------------------
-- Table structure for `personal_report`
-- ----------------------------
DROP TABLE IF EXISTS `personal_report`;
CREATE TABLE `personal_report` (
  `id` int(11) NOT NULL,
  `date` int(8) DEFAULT NULL COMMENT '日期（yyyyMMdd）',
  `account_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `recharge_amount` decimal(20,2) DEFAULT NULL COMMENT '充值金额',
  `withdrawal_amount` decimal(20,2) DEFAULT NULL COMMENT '提现金额',
  `effective_betting_number` decimal(20,2) DEFAULT NULL COMMENT '有效投注额',
  `prize_amount` decimal(20,2) DEFAULT NULL COMMENT '反奖金额',
  `rebate_amount` decimal(20,2) DEFAULT NULL COMMENT '返点金额',
  `activity_amount` decimal(20,2) DEFAULT NULL COMMENT '活动金额',
  `profit_and_loss` decimal(20,2) DEFAULT NULL COMMENT '盈亏',
  `create_time` int(11) DEFAULT NULL,
  `update_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_report
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) DEFAULT NULL COMMENT '登陆用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `pay_password` varchar(32) DEFAULT NULL COMMENT '支付密码',
  `remain_amount` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `rebate` decimal(10,2) DEFAULT NULL COMMENT '返点',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '用户类型（1：普通用户；2：渠道）',
  `create_time` int(11) DEFAULT NULL,
  `update_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `url`
-- ----------------------------
CREATE TABLE `url` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `short_url` varchar(255) DEFAULT NULL COMMENT '短链接',
  `long_url` varchar(255) DEFAULT NULL COMMENT '长链接',
  `account_id` int(11) DEFAULT NULL COMMENT '创建用户ID',
  `create_time` int(10) DEFAULT NULL,
  `update_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of url
-- ----------------------------
