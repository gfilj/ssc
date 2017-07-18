/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : ssc

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-07-16 23:28:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_ssc_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_ssc_record`;
CREATE TABLE `t_ssc_record` (
  `id` int(32) NOT NULL,
  `gamer` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '玩家',
  `type` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '玩法 按照:显示上的坐标 第一坐标为上面选择项目,第二坐标为下面选择项目,按照自然排序,中间分隔符为-例如(1-1)',
  `data` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '按照页面的选项英文对应字符串传入',
  `multiple` int(11) DEFAULT NULL COMMENT '倍数',
  `status` int(11) DEFAULT NULL COMMENT '0为加入购物篮 1为下注',
  `rebate` int(11) DEFAULT NULL COMMENT '返点',
  `note` int(11) DEFAULT NULL COMMENT '注数',
  `reprize` int(11) DEFAULT NULL COMMENT '返还价钱',
  `allprize` int(11) DEFAULT NULL COMMENT '共投注价钱',
  `settlement` int(2) DEFAULT 0 COMMENT '是否结算' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_ssc_record
-- ----------------------------
COMMIT;

SELECT * FROM t_ssc_record;