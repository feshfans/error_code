DROP TABLE IF EXISTS `error_code`;
CREATE TABLE `error_code` (
  `id` varchar(40) NOT NULL,
  `code` varchar(20) NOT NULL,
  `terminal_id` varchar(40) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_terminal` (`terminal_id`),
  CONSTRAINT `fk_terminal` FOREIGN KEY (`terminal_id`) REFERENCES `terminal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `terminal`;
CREATE TABLE `terminal` (
  `id` varchar(40) NOT NULL,
  `name` varchar(50) NOT NULL,
  `project_name` varchar(50) DEFAULT NULL,
  `responsible_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


commit;