DROP TABLE IF EXISTS `t_ssc_lottery`;

CREATE TABLE `t_ssc_lottery` (
  `id` int(11) NOT NULL,
  `code` varchar(200) DEFAULT NULL,
  `cn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (1, "CQSSC", "重庆时时彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (2, "GD11Y", "广东11选5");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (4, "MKG5FC", "GOD5分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (8, "XJSSC", "新疆时时彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (11, "MKGFFC", "新腾讯分分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (12, "MKGMMC", "GOD秒秒彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (13, "CQSSCN", "新重庆时时彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (21, "XGSSC", "香港时时彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (23, "RBSSC", "日本时时彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (31, "HGSSC", "韩国1.5分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (32, "BJPK10", "北京PK10");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (34, "XJPSSC", "新加坡2分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (41, "JDSSC", "京都1.5分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (45, "HG1FSSC", "韩国1分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (46, "TG30SSC", "泰国30秒");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (47, "CNDF1", "极速赛车");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (61, "BL11Y", "巴黎11选5");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (63, "MG3SSC", "美国3.5分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (64, "LSWJSSSC", "拉斯维加斯1.5分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (65, "BLSSC", "巴黎1分彩");
INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (66, "MGSSC", "美国1.5分彩");

