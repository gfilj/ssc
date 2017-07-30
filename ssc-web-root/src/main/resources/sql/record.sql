DROP TABLE IF EXISTS `t_ssc_record`;

CREATE TABLE `t_ssc_record` (
  `orderItemId` int(11) NOT NULL COMMENT '订单号',
  `userName` varchar(45) DEFAULT NULL,
  `userId` varchar(45) DEFAULT NULL,
  `orderTime` date DEFAULT NULL,
  `lottery` varchar(45) DEFAULT NULL,
  `lotteryId` varchar(45) DEFAULT NULL,
  `method` varchar(45) DEFAULT NULL,
  `issue` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `amount` double(7,2) DEFAULT NULL,
  `awardMoney` double(7,2) DEFAULT NULL,
  `winningNumber` varchar(45) DEFAULT NULL,
  `istrace` int(11) DEFAULT NULL,
  `perAmount` double(7,2) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `oddsMode` int(11) DEFAULT NULL,
  `odds` varchar(45) DEFAULT NULL,
  `cancelStatus` int(11) DEFAULT NULL,
  `canCancel` boolean DEFAULT NULL,
  `nums` int(11) DEFAULT NULL,
  `point` double(7,2) DEFAULT NULL,
  PRIMARY KEY (`orderItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT * FROM t_ssc_record;
