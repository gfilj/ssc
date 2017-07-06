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
  `type` tinyint(1) unsigned DEFAULT 0 COMMENT '用户类型',
  `point` decimal(20,2) DEFAULT NULL COMMENT '返点',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '父用户ID',
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
DROP TABLE IF EXISTS `url`;
CREATE TABLE `url` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `create_date` int(8) DEFAULT NULL COMMENT '短链创建时间',
  `short_url` varchar(255) DEFAULT NULL COMMENT '短链接',
  `user_type` tinyint(1) unsigned DEFAULT NULL COMMENT '用户类型（0：普通会员；1：代理）',
  `point` decimal(20,2) DEFAULT NULL COMMENT '返点',
  `valid_days` int(2) DEFAULT NULL COMMENT '短链有效日期',
  `account_id` varchar(20) DEFAULT NULL COMMENT '创建用户ID',
  `create_time` int(10) DEFAULT NULL,
  `update_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of url
-- ----------------------------

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `province_id` int(11) DEFAULT NULL COMMENT '所在省份ID',
  `city` varchar(255) DEFAULT NULL COMMENT '城市名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '1', '北京市');
INSERT INTO `city` VALUES ('2', '2', '天津市');
INSERT INTO `city` VALUES ('3', '3', '上海市');
INSERT INTO `city` VALUES ('4', '4', '重庆市');
INSERT INTO `city` VALUES ('5', '5', '石家庄市');
INSERT INTO `city` VALUES ('6', '5', '唐山市');
INSERT INTO `city` VALUES ('7', '5', '秦皇岛市');
INSERT INTO `city` VALUES ('8', '5', '邯郸市');
INSERT INTO `city` VALUES ('9', '5', '邢台市');
INSERT INTO `city` VALUES ('10', '5', '保定市');
INSERT INTO `city` VALUES ('11', '5', '张家口市');
INSERT INTO `city` VALUES ('12', '5', '承德市');
INSERT INTO `city` VALUES ('13', '5', '沧州市');
INSERT INTO `city` VALUES ('14', '5', '廊坊市');
INSERT INTO `city` VALUES ('15', '5', '衡水市');
INSERT INTO `city` VALUES ('17', '34', '澳门特别行政区');
INSERT INTO `city` VALUES ('18', '33', '呼和浩特市');
INSERT INTO `city` VALUES ('19', '33', '包头市');
INSERT INTO `city` VALUES ('20', '33', '乌海市');
INSERT INTO `city` VALUES ('21', '33', '赤峰市');
INSERT INTO `city` VALUES ('22', '33', '通辽市');
INSERT INTO `city` VALUES ('23', '33', '鄂尔多斯市');
INSERT INTO `city` VALUES ('24', '33', '呼伦贝尔市');
INSERT INTO `city` VALUES ('25', '33', '巴彦淖尔市');
INSERT INTO `city` VALUES ('26', '33', '乌兰察布市');
INSERT INTO `city` VALUES ('27', '33', '锡林郭勒盟');
INSERT INTO `city` VALUES ('28', '33', '兴安盟');
INSERT INTO `city` VALUES ('29', '33', '阿拉善盟');
INSERT INTO `city` VALUES ('30', '32', '乌鲁木齐市');
INSERT INTO `city` VALUES ('31', '32', '克拉玛依市');
INSERT INTO `city` VALUES ('32', '32', '石河子市　');
INSERT INTO `city` VALUES ('33', '32', '阿拉尔市');
INSERT INTO `city` VALUES ('34', '32', '图木舒克市');
INSERT INTO `city` VALUES ('35', '32', '五家渠市');
INSERT INTO `city` VALUES ('36', '32', '吐鲁番市');
INSERT INTO `city` VALUES ('37', '32', '阿克苏市');
INSERT INTO `city` VALUES ('38', '32', '喀什市');
INSERT INTO `city` VALUES ('39', '32', '哈密市');
INSERT INTO `city` VALUES ('40', '32', '和田市');
INSERT INTO `city` VALUES ('41', '32', '阿图什市');
INSERT INTO `city` VALUES ('42', '32', '库尔勒市');
INSERT INTO `city` VALUES ('43', '32', '昌吉市　');
INSERT INTO `city` VALUES ('44', '32', '阜康市');
INSERT INTO `city` VALUES ('45', '32', '米泉市');
INSERT INTO `city` VALUES ('46', '32', '博乐市');
INSERT INTO `city` VALUES ('47', '32', '伊宁市');
INSERT INTO `city` VALUES ('48', '32', '奎屯市');
INSERT INTO `city` VALUES ('49', '32', '塔城市');
INSERT INTO `city` VALUES ('50', '32', '乌苏市');
INSERT INTO `city` VALUES ('51', '32', '阿勒泰市');
INSERT INTO `city` VALUES ('52', '31', '银川市');
INSERT INTO `city` VALUES ('53', '31', '石嘴山市');
INSERT INTO `city` VALUES ('54', '31', '吴忠市');
INSERT INTO `city` VALUES ('55', '31', '固原市');
INSERT INTO `city` VALUES ('56', '31', '中卫市');
INSERT INTO `city` VALUES ('57', '30', '拉萨市');
INSERT INTO `city` VALUES ('58', '30', '那曲地区');
INSERT INTO `city` VALUES ('59', '30', '昌都地区');
INSERT INTO `city` VALUES ('60', '30', '山南地区');
INSERT INTO `city` VALUES ('61', '30', '日喀则地区');
INSERT INTO `city` VALUES ('62', '30', '阿里地区');
INSERT INTO `city` VALUES ('63', '30', '林芝地区');
INSERT INTO `city` VALUES ('64', '29', '南宁市');
INSERT INTO `city` VALUES ('65', '29', '柳州市');
INSERT INTO `city` VALUES ('66', '29', '桂林市');
INSERT INTO `city` VALUES ('67', '29', '梧州市');
INSERT INTO `city` VALUES ('68', '29', '北海市');
INSERT INTO `city` VALUES ('69', '29', '防城港市');
INSERT INTO `city` VALUES ('70', '29', '钦州市');
INSERT INTO `city` VALUES ('71', '29', '贵港市');
INSERT INTO `city` VALUES ('72', '29', '玉林市');
INSERT INTO `city` VALUES ('73', '29', '百色市');
INSERT INTO `city` VALUES ('74', '29', '贺州市');
INSERT INTO `city` VALUES ('75', '29', '河池市');
INSERT INTO `city` VALUES ('76', '29', '来宾市');
INSERT INTO `city` VALUES ('77', '29', '崇左市');
INSERT INTO `city` VALUES ('78', '28', '西安市');
INSERT INTO `city` VALUES ('79', '28', '铜川市');
INSERT INTO `city` VALUES ('80', '28', '宝鸡市');
INSERT INTO `city` VALUES ('81', '28', '咸阳市');
INSERT INTO `city` VALUES ('82', '28', '渭南市');
INSERT INTO `city` VALUES ('83', '28', '延安市');
INSERT INTO `city` VALUES ('84', '28', '汉中市');
INSERT INTO `city` VALUES ('85', '28', '榆林市');
INSERT INTO `city` VALUES ('86', '28', '安康市');
INSERT INTO `city` VALUES ('87', '28', '商洛市');
INSERT INTO `city` VALUES ('88', '27', '西宁市');
INSERT INTO `city` VALUES ('89', '27', '海东地区');
INSERT INTO `city` VALUES ('90', '27', '海北藏族自治州');
INSERT INTO `city` VALUES ('91', '27', '黄南藏族自治州');
INSERT INTO `city` VALUES ('92', '27', '海南藏族自治州');
INSERT INTO `city` VALUES ('93', '27', '果洛藏族自治州');
INSERT INTO `city` VALUES ('94', '27', '玉树藏族自治州');
INSERT INTO `city` VALUES ('95', '27', '海西蒙古族藏族自治州');
INSERT INTO `city` VALUES ('96', '26', '昆明市');
INSERT INTO `city` VALUES ('97', '26', '曲靖市');
INSERT INTO `city` VALUES ('98', '26', '玉溪市');
INSERT INTO `city` VALUES ('99', '26', '保山市');
INSERT INTO `city` VALUES ('100', '26', '昭通市');
INSERT INTO `city` VALUES ('101', '26', '丽江市');
INSERT INTO `city` VALUES ('102', '26', '思茅市');
INSERT INTO `city` VALUES ('103', '26', '临沧市');
INSERT INTO `city` VALUES ('104', '26', '文山壮族苗族自治州');
INSERT INTO `city` VALUES ('105', '26', '红河哈尼族彝族自治州');
INSERT INTO `city` VALUES ('106', '26', '西双版纳傣族自治州');
INSERT INTO `city` VALUES ('107', '26', '楚雄彝族自治州');
INSERT INTO `city` VALUES ('108', '26', '大理白族自治州');
INSERT INTO `city` VALUES ('109', '26', '德宏傣族景颇族自治州');
INSERT INTO `city` VALUES ('110', '26', '怒江傈傈族自治州');
INSERT INTO `city` VALUES ('111', '26', '迪庆藏族自治州');
INSERT INTO `city` VALUES ('112', '25', '海口市');
INSERT INTO `city` VALUES ('113', '25', '三亚市');
INSERT INTO `city` VALUES ('114', '25', '五指山市');
INSERT INTO `city` VALUES ('115', '25', '琼海市');
INSERT INTO `city` VALUES ('116', '25', '儋州市');
INSERT INTO `city` VALUES ('117', '25', '文昌市');
INSERT INTO `city` VALUES ('118', '25', '万宁市');
INSERT INTO `city` VALUES ('119', '25', '东方市');
INSERT INTO `city` VALUES ('120', '25', '澄迈县');
INSERT INTO `city` VALUES ('121', '25', '定安县');
INSERT INTO `city` VALUES ('122', '25', '屯昌县');
INSERT INTO `city` VALUES ('123', '25', '临高县');
INSERT INTO `city` VALUES ('124', '25', '白沙黎族自治县');
INSERT INTO `city` VALUES ('125', '25', '昌江黎族自治县');
INSERT INTO `city` VALUES ('126', '25', '乐东黎族自治县');
INSERT INTO `city` VALUES ('127', '25', '陵水黎族自治县');
INSERT INTO `city` VALUES ('128', '25', '保亭黎族苗族自治县');
INSERT INTO `city` VALUES ('129', '25', '琼中黎族苗族自治县');
INSERT INTO `city` VALUES ('130', '24', '贵阳市');
INSERT INTO `city` VALUES ('131', '24', '六盘水市');
INSERT INTO `city` VALUES ('132', '24', '遵义市');
INSERT INTO `city` VALUES ('133', '24', '安顺市');
INSERT INTO `city` VALUES ('134', '24', '铜仁地区');
INSERT INTO `city` VALUES ('135', '24', '毕节地区');
INSERT INTO `city` VALUES ('136', '24', '黔西南布依族苗族自治州');
INSERT INTO `city` VALUES ('137', '24', '黔东南苗族侗族自治州');
INSERT INTO `city` VALUES ('138', '24', '黔南布依族苗族自治州');
INSERT INTO `city` VALUES ('139', '22', '成都市');
INSERT INTO `city` VALUES ('140', '22', '自贡市');
INSERT INTO `city` VALUES ('141', '22', '攀枝花市');
INSERT INTO `city` VALUES ('142', '22', '泸州市');
INSERT INTO `city` VALUES ('143', '22', '德阳市');
INSERT INTO `city` VALUES ('144', '22', '绵阳市');
INSERT INTO `city` VALUES ('145', '22', '广元市');
INSERT INTO `city` VALUES ('146', '22', '遂宁市');
INSERT INTO `city` VALUES ('147', '22', '内江市');
INSERT INTO `city` VALUES ('148', '22', '乐山市');
INSERT INTO `city` VALUES ('149', '22', '南充市');
INSERT INTO `city` VALUES ('150', '22', '眉山市');
INSERT INTO `city` VALUES ('151', '22', '宜宾市');
INSERT INTO `city` VALUES ('152', '22', '广安市');
INSERT INTO `city` VALUES ('153', '22', '达州市');
INSERT INTO `city` VALUES ('154', '22', '雅安市');
INSERT INTO `city` VALUES ('155', '22', '巴中市');
INSERT INTO `city` VALUES ('156', '22', '资阳市');
INSERT INTO `city` VALUES ('157', '22', '阿坝藏族羌族自治州');
INSERT INTO `city` VALUES ('158', '22', '甘孜藏族自治州');
INSERT INTO `city` VALUES ('159', '22', '凉山彝族自治州');
INSERT INTO `city` VALUES ('160', '21', '兰州市');
INSERT INTO `city` VALUES ('161', '21', '金昌市');
INSERT INTO `city` VALUES ('162', '21', '白银市');
INSERT INTO `city` VALUES ('163', '21', '天水市');
INSERT INTO `city` VALUES ('164', '21', '嘉峪关市');
INSERT INTO `city` VALUES ('165', '21', '武威市');
INSERT INTO `city` VALUES ('166', '21', '张掖市');
INSERT INTO `city` VALUES ('167', '21', '平凉市');
INSERT INTO `city` VALUES ('168', '21', '酒泉市');
INSERT INTO `city` VALUES ('169', '21', '庆阳市');
INSERT INTO `city` VALUES ('170', '21', '定西市');
INSERT INTO `city` VALUES ('171', '21', '陇南市');
INSERT INTO `city` VALUES ('172', '21', '临夏回族自治州');
INSERT INTO `city` VALUES ('173', '21', '甘南藏族自治州');
INSERT INTO `city` VALUES ('174', '20', '广州市');
INSERT INTO `city` VALUES ('175', '20', '深圳市');
INSERT INTO `city` VALUES ('176', '20', '珠海市');
INSERT INTO `city` VALUES ('177', '20', '汕头市');
INSERT INTO `city` VALUES ('178', '20', '韶关市');
INSERT INTO `city` VALUES ('179', '20', '佛山市');
INSERT INTO `city` VALUES ('180', '20', '江门市');
INSERT INTO `city` VALUES ('181', '20', '湛江市');
INSERT INTO `city` VALUES ('182', '20', '茂名市');
INSERT INTO `city` VALUES ('183', '20', '肇庆市');
INSERT INTO `city` VALUES ('184', '20', '惠州市');
INSERT INTO `city` VALUES ('185', '20', '梅州市');
INSERT INTO `city` VALUES ('186', '20', '汕尾市');
INSERT INTO `city` VALUES ('187', '20', '河源市');
INSERT INTO `city` VALUES ('188', '20', '阳江市');
INSERT INTO `city` VALUES ('189', '20', '清远市');
INSERT INTO `city` VALUES ('190', '20', '东莞市');
INSERT INTO `city` VALUES ('191', '20', '中山市');
INSERT INTO `city` VALUES ('192', '20', '潮州市');
INSERT INTO `city` VALUES ('193', '20', '揭阳市');
INSERT INTO `city` VALUES ('194', '20', '云浮市');
INSERT INTO `city` VALUES ('195', '19', '长沙市');
INSERT INTO `city` VALUES ('196', '19', '株洲市');
INSERT INTO `city` VALUES ('197', '19', '湘潭市');
INSERT INTO `city` VALUES ('198', '19', '衡阳市');
INSERT INTO `city` VALUES ('199', '19', '邵阳市');
INSERT INTO `city` VALUES ('200', '19', '岳阳市');
INSERT INTO `city` VALUES ('201', '19', '常德市');
INSERT INTO `city` VALUES ('202', '19', '张家界市');
INSERT INTO `city` VALUES ('203', '19', '益阳市');
INSERT INTO `city` VALUES ('204', '19', '郴州市');
INSERT INTO `city` VALUES ('205', '19', '永州市');
INSERT INTO `city` VALUES ('206', '19', '怀化市');
INSERT INTO `city` VALUES ('207', '19', '娄底市');
INSERT INTO `city` VALUES ('208', '19', '湘西土家族苗族自治州');
INSERT INTO `city` VALUES ('209', '18', '武汉市');
INSERT INTO `city` VALUES ('210', '18', '黄石市');
INSERT INTO `city` VALUES ('211', '18', '十堰市');
INSERT INTO `city` VALUES ('212', '18', '荆州市');
INSERT INTO `city` VALUES ('213', '18', '宜昌市');
INSERT INTO `city` VALUES ('214', '18', '襄樊市');
INSERT INTO `city` VALUES ('215', '18', '鄂州市');
INSERT INTO `city` VALUES ('216', '18', '荆门市');
INSERT INTO `city` VALUES ('217', '18', '孝感市');
INSERT INTO `city` VALUES ('218', '18', '黄冈市');
INSERT INTO `city` VALUES ('219', '18', '咸宁市');
INSERT INTO `city` VALUES ('220', '18', '随州市');
INSERT INTO `city` VALUES ('221', '18', '仙桃市');
INSERT INTO `city` VALUES ('222', '18', '天门市');
INSERT INTO `city` VALUES ('223', '18', '潜江市');
INSERT INTO `city` VALUES ('224', '18', '神农架林区');
INSERT INTO `city` VALUES ('225', '18', '恩施土家族苗族自治州');
INSERT INTO `city` VALUES ('226', '17', '郑州市');
INSERT INTO `city` VALUES ('227', '17', '开封市');
INSERT INTO `city` VALUES ('228', '17', '洛阳市');
INSERT INTO `city` VALUES ('229', '17', '平顶山市');
INSERT INTO `city` VALUES ('230', '17', '安阳市');
INSERT INTO `city` VALUES ('231', '17', '鹤壁市');
INSERT INTO `city` VALUES ('232', '17', '新乡市');
INSERT INTO `city` VALUES ('233', '17', '焦作市');
INSERT INTO `city` VALUES ('234', '17', '濮阳市');
INSERT INTO `city` VALUES ('235', '17', '许昌市');
INSERT INTO `city` VALUES ('236', '17', '漯河市');
INSERT INTO `city` VALUES ('237', '17', '三门峡市');
INSERT INTO `city` VALUES ('238', '17', '南阳市');
INSERT INTO `city` VALUES ('239', '17', '商丘市');
INSERT INTO `city` VALUES ('240', '17', '信阳市');
INSERT INTO `city` VALUES ('241', '17', '周口市');
INSERT INTO `city` VALUES ('242', '17', '驻马店市');
INSERT INTO `city` VALUES ('243', '17', '济源市');
INSERT INTO `city` VALUES ('244', '16', '济南市');
INSERT INTO `city` VALUES ('245', '16', '青岛市');
INSERT INTO `city` VALUES ('246', '16', '淄博市');
INSERT INTO `city` VALUES ('247', '16', '枣庄市');
INSERT INTO `city` VALUES ('248', '16', '东营市');
INSERT INTO `city` VALUES ('249', '16', '烟台市');
INSERT INTO `city` VALUES ('250', '16', '潍坊市');
INSERT INTO `city` VALUES ('251', '16', '济宁市');
INSERT INTO `city` VALUES ('252', '16', '泰安市');
INSERT INTO `city` VALUES ('253', '16', '威海市');
INSERT INTO `city` VALUES ('254', '16', '日照市');
INSERT INTO `city` VALUES ('255', '16', '莱芜市');
INSERT INTO `city` VALUES ('256', '16', '临沂市');
INSERT INTO `city` VALUES ('257', '16', '德州市');
INSERT INTO `city` VALUES ('258', '16', '聊城市');
INSERT INTO `city` VALUES ('259', '16', '滨州市');
INSERT INTO `city` VALUES ('260', '16', '菏泽市');
INSERT INTO `city` VALUES ('261', '15', '南昌市');
INSERT INTO `city` VALUES ('262', '15', '景德镇市');
INSERT INTO `city` VALUES ('263', '15', '萍乡市');
INSERT INTO `city` VALUES ('264', '15', '九江市');
INSERT INTO `city` VALUES ('265', '15', '新余市');
INSERT INTO `city` VALUES ('266', '15', '鹰潭市');
INSERT INTO `city` VALUES ('267', '15', '赣州市');
INSERT INTO `city` VALUES ('268', '15', '吉安市');
INSERT INTO `city` VALUES ('269', '15', '宜春市');
INSERT INTO `city` VALUES ('270', '15', '抚州市');
INSERT INTO `city` VALUES ('271', '15', '上饶市');
INSERT INTO `city` VALUES ('272', '14', '福州市');
INSERT INTO `city` VALUES ('273', '14', '厦门市');
INSERT INTO `city` VALUES ('274', '14', '莆田市');
INSERT INTO `city` VALUES ('275', '14', '三明市');
INSERT INTO `city` VALUES ('276', '14', '泉州市');
INSERT INTO `city` VALUES ('277', '14', '漳州市');
INSERT INTO `city` VALUES ('278', '14', '南平市');
INSERT INTO `city` VALUES ('279', '14', '龙岩市');
INSERT INTO `city` VALUES ('280', '14', '宁德市');
INSERT INTO `city` VALUES ('281', '13', '合肥市');
INSERT INTO `city` VALUES ('282', '13', '芜湖市');
INSERT INTO `city` VALUES ('283', '13', '蚌埠市');
INSERT INTO `city` VALUES ('284', '13', '淮南市');
INSERT INTO `city` VALUES ('285', '13', '马鞍山市');
INSERT INTO `city` VALUES ('286', '13', '淮北市');
INSERT INTO `city` VALUES ('287', '13', '铜陵市');
INSERT INTO `city` VALUES ('288', '13', '安庆市');
INSERT INTO `city` VALUES ('289', '13', '黄山市');
INSERT INTO `city` VALUES ('290', '13', '滁州市');
INSERT INTO `city` VALUES ('291', '13', '阜阳市');
INSERT INTO `city` VALUES ('292', '13', '宿州市');
INSERT INTO `city` VALUES ('293', '13', '巢湖市');
INSERT INTO `city` VALUES ('294', '13', '六安市');
INSERT INTO `city` VALUES ('295', '13', '亳州市');
INSERT INTO `city` VALUES ('296', '13', '池州市');
INSERT INTO `city` VALUES ('297', '13', '宣城市');
INSERT INTO `city` VALUES ('298', '12', '杭州市');
INSERT INTO `city` VALUES ('299', '12', '宁波市');
INSERT INTO `city` VALUES ('300', '12', '温州市');
INSERT INTO `city` VALUES ('301', '12', '嘉兴市');
INSERT INTO `city` VALUES ('302', '12', '湖州市');
INSERT INTO `city` VALUES ('303', '12', '绍兴市');
INSERT INTO `city` VALUES ('304', '12', '金华市');
INSERT INTO `city` VALUES ('305', '12', '衢州市');
INSERT INTO `city` VALUES ('306', '12', '舟山市');
INSERT INTO `city` VALUES ('307', '12', '台州市');
INSERT INTO `city` VALUES ('308', '12', '丽水市');
INSERT INTO `city` VALUES ('309', '11', '南京市');
INSERT INTO `city` VALUES ('310', '11', '无锡市');
INSERT INTO `city` VALUES ('311', '11', '徐州市');
INSERT INTO `city` VALUES ('312', '11', '常州市');
INSERT INTO `city` VALUES ('313', '11', '苏州市');
INSERT INTO `city` VALUES ('314', '11', '南通市');
INSERT INTO `city` VALUES ('315', '11', '连云港市');
INSERT INTO `city` VALUES ('316', '11', '淮安市');
INSERT INTO `city` VALUES ('317', '11', '盐城市');
INSERT INTO `city` VALUES ('318', '11', '扬州市');
INSERT INTO `city` VALUES ('319', '11', '镇江市');
INSERT INTO `city` VALUES ('320', '11', '泰州市');
INSERT INTO `city` VALUES ('321', '11', '宿迁市');
INSERT INTO `city` VALUES ('322', '10', '哈尔滨市');
INSERT INTO `city` VALUES ('323', '10', '齐齐哈尔市');
INSERT INTO `city` VALUES ('324', '10', '鹤岗市');
INSERT INTO `city` VALUES ('325', '10', '双鸭山市');
INSERT INTO `city` VALUES ('326', '10', '鸡市');
INSERT INTO `city` VALUES ('327', '10', '大庆市');
INSERT INTO `city` VALUES ('328', '10', '伊春市');
INSERT INTO `city` VALUES ('329', '10', '牡丹江市');
INSERT INTO `city` VALUES ('330', '10', '佳木斯市');
INSERT INTO `city` VALUES ('331', '10', '七台河市');
INSERT INTO `city` VALUES ('332', '10', '黑河市');
INSERT INTO `city` VALUES ('333', '10', '绥化市');
INSERT INTO `city` VALUES ('334', '10', '大兴安岭地区');
INSERT INTO `city` VALUES ('335', '9', '长春市');
INSERT INTO `city` VALUES ('336', '9', '吉林市');
INSERT INTO `city` VALUES ('337', '9', '四平市');
INSERT INTO `city` VALUES ('338', '9', '辽源市');
INSERT INTO `city` VALUES ('339', '9', '通化市');
INSERT INTO `city` VALUES ('340', '9', '白山市');
INSERT INTO `city` VALUES ('341', '9', '松原市');
INSERT INTO `city` VALUES ('342', '9', '白城市');
INSERT INTO `city` VALUES ('343', '9', '延边朝鲜族自治州');
INSERT INTO `city` VALUES ('344', '8', '沈阳市');
INSERT INTO `city` VALUES ('345', '8', '大连市');
INSERT INTO `city` VALUES ('346', '8', '鞍山');
INSERT INTO `city` VALUES ('347', '8', '抚顺市');
INSERT INTO `city` VALUES ('348', '8', '本溪市');
INSERT INTO `city` VALUES ('349', '8', '丹东市');
INSERT INTO `city` VALUES ('350', '8', '锦州市');
INSERT INTO `city` VALUES ('351', '8', '营口市');
INSERT INTO `city` VALUES ('352', '8', '阜新市');
INSERT INTO `city` VALUES ('353', '8', '辽阳市');
INSERT INTO `city` VALUES ('354', '8', '盘锦市');
INSERT INTO `city` VALUES ('355', '8', '铁岭市');
INSERT INTO `city` VALUES ('356', '8', '朝阳市');
INSERT INTO `city` VALUES ('357', '8', '葫芦岛市');
INSERT INTO `city` VALUES ('358', '7', '台北市');
INSERT INTO `city` VALUES ('359', '7', '高雄市');
INSERT INTO `city` VALUES ('360', '7', '基隆市');
INSERT INTO `city` VALUES ('361', '7', '台中市');
INSERT INTO `city` VALUES ('362', '7', '台南市');
INSERT INTO `city` VALUES ('363', '7', '新竹市');
INSERT INTO `city` VALUES ('364', '7', '嘉义市');
INSERT INTO `city` VALUES ('365', '7', '台北县');
INSERT INTO `city` VALUES ('366', '7', '宜兰县');
INSERT INTO `city` VALUES ('367', '7', '桃园县');
INSERT INTO `city` VALUES ('368', '7', '新竹县');
INSERT INTO `city` VALUES ('369', '7', '苗栗县');
INSERT INTO `city` VALUES ('370', '7', '台中县');
INSERT INTO `city` VALUES ('371', '7', '彰化县');
INSERT INTO `city` VALUES ('372', '7', '南投县');
INSERT INTO `city` VALUES ('373', '7', '云林县');
INSERT INTO `city` VALUES ('374', '7', '嘉义县');
INSERT INTO `city` VALUES ('375', '7', '台南县');
INSERT INTO `city` VALUES ('376', '7', '高雄县');
INSERT INTO `city` VALUES ('377', '7', '屏东县');
INSERT INTO `city` VALUES ('378', '7', '澎湖县');
INSERT INTO `city` VALUES ('379', '7', '台东县');
INSERT INTO `city` VALUES ('380', '7', '花莲县');
INSERT INTO `city` VALUES ('381', '6', '太原市');
INSERT INTO `city` VALUES ('382', '6', '大同市');
INSERT INTO `city` VALUES ('383', '6', '阳泉市');
INSERT INTO `city` VALUES ('384', '6', '长治市');
INSERT INTO `city` VALUES ('385', '6', '晋城市');
INSERT INTO `city` VALUES ('386', '6', '朔州市');
INSERT INTO `city` VALUES ('387', '6', '晋中市');
INSERT INTO `city` VALUES ('388', '6', '运城市');
INSERT INTO `city` VALUES ('389', '6', '忻州市');
INSERT INTO `city` VALUES ('390', '6', '临汾市');
INSERT INTO `city` VALUES ('391', '6', '吕梁市');

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '北京市');
INSERT INTO `province` VALUES ('2', '天津市');
INSERT INTO `province` VALUES ('3', '上海市');
INSERT INTO `province` VALUES ('4', '重庆市');
INSERT INTO `province` VALUES ('5', '河北省');
INSERT INTO `province` VALUES ('6', '山西省');
INSERT INTO `province` VALUES ('7', '台湾省');
INSERT INTO `province` VALUES ('8', '辽宁省');
INSERT INTO `province` VALUES ('9', '吉林省');
INSERT INTO `province` VALUES ('10', '黑龙江省');
INSERT INTO `province` VALUES ('11', '江苏省');
INSERT INTO `province` VALUES ('12', '浙江省');
INSERT INTO `province` VALUES ('13', '安徽省');
INSERT INTO `province` VALUES ('14', '福建省');
INSERT INTO `province` VALUES ('15', '江西省');
INSERT INTO `province` VALUES ('16', '山东省');
INSERT INTO `province` VALUES ('17', '河南省');
INSERT INTO `province` VALUES ('18', '湖北省');
INSERT INTO `province` VALUES ('19', '湖南省');
INSERT INTO `province` VALUES ('20', '广东省');
INSERT INTO `province` VALUES ('21', '甘肃省');
INSERT INTO `province` VALUES ('22', '四川省');
INSERT INTO `province` VALUES ('24', '贵州省');
INSERT INTO `province` VALUES ('25', '海南省');
INSERT INTO `province` VALUES ('26', '云南省');
INSERT INTO `province` VALUES ('27', '青海省');
INSERT INTO `province` VALUES ('28', '陕西省');
INSERT INTO `province` VALUES ('29', '广西壮族自治区');
INSERT INTO `province` VALUES ('30', '西藏自治区');
INSERT INTO `province` VALUES ('31', '宁夏回族自治区');
INSERT INTO `province` VALUES ('32', '新疆维吾尔自治区');
INSERT INTO `province` VALUES ('33', '内蒙古自治区');
INSERT INTO `province` VALUES ('34', '澳门特别行政区');
INSERT INTO `province` VALUES ('35', '香港特别行政区');



