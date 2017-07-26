DROP TABLE IF EXISTS `ssc`.`t_ssc_way`;

CREATE TABLE `t_ssc_way` (
  `wayId` int(11) NOT NULL COMMENT '玩法ID',
  `nameCn` varchar(200) DEFAULT NULL COMMENT '中文名称',
  `nameEn` varchar(200) DEFAULT NULL COMMENT '英文名称',
  PRIMARY KEY (`wayId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*
-- Query: SELECT * FROM ssc.t_ssc_way
LIMIT 0, 1000

-- Date: 2017-07-26 09:10
*/
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (1,'前三直选直选单式','qiansan.zhixuan.danshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (6,'四星直选直选单式','sixing.zhixuan.danshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (7,'五星直选直选单式','wuxing.zhixuan.danshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (13,'前三组选混合组选','qiansan.zuxuan.hunhezuxuan');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (16,'前三组选组三','qiansan.zuxuan.zusan');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (17,'前三组选组六','qiansan.zuxuan.zuliu');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (24,'四星组选组选6','sixing.zuxuan.zuxuan6');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (25,'四星组选组选12','sixing.zuxuan.zuxuan12');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (26,'四星组选组选24','sixing.zuxuan.zuxuan24');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (27,'五星组选组选5','wuxing.zuxuan.zuxuan5');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (28,'五星组选组选10','wuxing.zuxuan.zuxuan10');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (29,'五星组选组选20','wuxing.zuxuan.zuxuan20');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (30,'五星组选组选30','wuxing.zuxuan.zuxuan30');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (31,'五星组选组选60','wuxing.zuxuan.zuxuan60');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (32,'五星组选组选120','wuxing.zuxuan.zuxuan120');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (65,'前三直选直选复式','qiansan.zhixuan.fushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (67,'四星直选直选复式','sixing.zhixuan.fushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (68,'五星直选直选复式','wuxing.zhixuan.fushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (71,'前三直选直选和值','qiansan.zhixuan.hezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (75,'前三组选组选和值','qiansan.zuxuan.zuxuanhezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (142,'中三直选直选单式','zhongsan.zhixuan.danshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (145,'中三组选组三','zhongsan.zuxuan.zusan');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (146,'中三组选组六','zhongsan.zuxuan.zuliu');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (150,'中三直选直选复式','zhongsan.zhixuan.fushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (151,'中三直选直选和值','zhongsan.zhixuan.hezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (152,'中三组选混合组选','zhongsan.zuxuan.hunhezuxuan');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (154,'中三组选组选和值','zhongsan.zuxuan.hezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (4910,'中三组选组三胆拖','zhongsan.zuxuan.zusandt');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (4911,'前三组选组三胆拖','qiansan.zuxuan.zusandt');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (5010,'中三组选组六胆拖','zhongsan.zuxuan.zusandt');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (5011,'前三组选组三胆拖','qiansan.zuxuan.zuliudt');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (69,'后三直选直选复式','housan.zhixuan.fushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (8,'后三直选直选单式','housan.zhixuan.danshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (73,'后三直选直选和值','housan.zhixuan.hezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (49,'后三组选组三','housan.zuxuan.zusan');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (491,'后三组选组三胆拖','housan.zuxuan.zusandt');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (50,'后三组选组六','housan.zuxuan.zuliu');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (501,'后三组选组六','housan.zuxuan.zuliudt');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (81,'后三组选混合组选','housan.zuxuan.hunhezuxuan');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (80,'后三组选组选和值','housan.zuxuan.hezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (70,'二星直选后二复式','erxing.zhixuan.houerfushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (59,'二星组选后二复式','erxing.zuxuan.houerfushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (66,'二星直选前二复式','erxing.zhixuan.qianerfushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (20,'二星组选前二复式','erxing.zuxuan.qianerfushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (11,'二星直选后二单式','erxing.zhixuan.houerdanshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (12,'二星组选后二单式','erxing.zuxuan.houerdanshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (4,'二星直选前二单式','erxing.zhixuan.qianerdanshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (5,'二星组选前二单式','erxing.zuxuan.qianerdanshi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (74,'二星直选后二和值','erxing.zhixuan.houerhezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (72,'二星直选前二和值','erxing.zhixuan.qianerhezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (76,'二星组选前二和值','erxing.zuxuan.qianerhezhi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (78,'定位胆','yixing.dingweidan.fushi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (51,'不定位三星不定位后三一码不定位','budingwei.sanxingbudingwei.housanyimabudingwei');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (52,'不定位三星不定位后三二码不定位','budingwei.sanxingbudingwei.housanermabudingwei');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (21,'不定位三星不定位前三二码不定位','budingwei.sanxingbudingwei.qiansanermabudingwei');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (18,'不定位三星不定位前三一码不定位','budingwei.sanxingbudingwei.qiansanyimabudingwei');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (58,'后二大小单双','houerdaxiaodanshuang');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (19,'前二大小单双','qianerdaxiaodanshuang');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (44,'趣味特殊一帆风顺','quwei.teshu.yifanfenshun');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (45,'趣味特殊好事成双','quwei.teshu.haoshichengshuang');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (46,'趣味特殊三星报喜','quwei.teshu.sanxingbaoxi');
INSERT INTO `t_ssc_way` (`wayId`,`nameCn`,`nameEn`) VALUES (47,'趣味特殊四季发财','quwei.teshu.sijifacai');

