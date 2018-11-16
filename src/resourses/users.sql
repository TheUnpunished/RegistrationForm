select * from goods;
CREATE TABLE `goods` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `artikul` int(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `count` int(10) DEFAULT NULL,
  `sum` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET = utf8;
CREATE DATABASE IF NOT EXISTS tis DEFAULT CHARACTER SET  COLLATE utf8_general_ci;