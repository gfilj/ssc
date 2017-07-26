DELETE FROM `ssc`.`t_ssc_way`;

CREATE TABLE `t_ssc_way` (
  `wayId` int(11) NOT NULL COMMENT '玩法ID',
  `nameCn` varchar(45) DEFAULT NULL COMMENT '中文名称',
  `nameEn` varchar(45) DEFAULT NULL COMMENT '英文名称',
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
