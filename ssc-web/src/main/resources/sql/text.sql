SELECT * FROM ssc.t_ssc_issue;


replace into t_ssc_issue(issue,digit5,digit4,digit3,digit2,digit1,type) values(20170101,1,3,4,4,5,4);

DELETE FROM `ssc`.`t_ssc_issue`
WHERE <{where_expression}>;



CREATE TABLE `t_ssc_issue` (
  `issue` varchar(20) NOT NULL,
  `digit5` int(11) NOT NULL,
  `digit4` int(11) NOT NULL,
  `digit3` int(11) NOT NULL,
  `digit2` int(11) NOT NULL,
  `digit1` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`issue`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE DATABASE `ssc` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `ssc` /*!40100 DEFAULT CHARACTER SET utf8 */;



