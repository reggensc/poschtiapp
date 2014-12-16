DROP TABLE IF EXISTS `map_owners`;
DROP TABLE IF EXISTS `tbl_user`;
DROP TABLE IF EXISTS `tbl_shoppingitem`;
DROP TABLE IF EXISTS `tbl_shoppingcategory`;
DROP TABLE IF EXISTS `tbl_shoppinglist`;
DROP TABLE IF EXISTS `ref_unit`;
DROP TABLE IF EXISTS `ref_state`;
DROP TABLE IF EXISTS `aud_tbl_user`;
DROP TABLE IF EXISTS `aud_tbl_shoppinglist`;
DROP TABLE IF EXISTS `aud_tbl_shoppingitem`;
DROP TABLE IF EXISTS `aud_tbl_shoppingcategory`;
DROP TABLE IF EXISTS `aud_shoppinglist_shoppingcategory`;
DROP TABLE IF EXISTS `aud_shoppingcategory_shoppingitem`;
DROP TABLE IF EXISTS `aud_ref_unit`;
DROP TABLE IF EXISTS `aud_ref_state`;
DROP TABLE IF EXISTS `aud_map_owners`;
DROP TABLE IF EXISTS `revinfo`;

CREATE TABLE `revinfo` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_map_owners` (
  `REV` int(11) NOT NULL,
  `list_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`list_id`,`user_id`),
  CONSTRAINT `fk_aud_map_owners_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_ref_state` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `designator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  CONSTRAINT `fk_aud_ref_state_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_ref_unit` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `designator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  CONSTRAINT `fk_aud_ref_unit_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_shoppingcategory_shoppingitem` (
  `REV` int(11) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `category_index` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`category_id`,`id`,`category_index`),
  CONSTRAINT `fk_aud_shoppingcategory_shoppingitem_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_shoppinglist_shoppingcategory` (
  `REV` int(11) NOT NULL,
  `list_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `list_index` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`list_id`,`id`,`list_index`),
  CONSTRAINT `fk_aud_shoppinglist_shoppingcategory_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_tbl_shoppingcategory` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  CONSTRAINT `fk_aud_tbl_shoppingcategory_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_tbl_shoppingitem` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  CONSTRAINT `fk_aud_tbl_shoppingitem_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_tbl_shoppinglist` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  CONSTRAINT `fk_aud_tbl_shoppinglist_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aud_tbl_user` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  CONSTRAINT `fk_aud_tbl_user_revinfo` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ref_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `designator` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_designator` (`designator`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `ref_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `designator` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_designator` (`designator`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_shoppinglist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5q8wdfuy6plk08vc9qhwdod3h` (`state_id`),
  CONSTRAINT `FK_5q8wdfuy6plk08vc9qhwdod3h` FOREIGN KEY (`state_id`) REFERENCES `ref_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_shoppingcategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `list_id` bigint(20) DEFAULT NULL,
  `list_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cc6klxwo857nu62i3el0kdsw9` (`list_id`),
  CONSTRAINT `FK_cc6klxwo857nu62i3el0kdsw9` FOREIGN KEY (`list_id`) REFERENCES `tbl_shoppinglist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_shoppingitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `category_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ce8j5qq1jpw9q5lbtpfpslw4m` (`unit_id`),
  KEY `FK_lrw45uu6x9pa9b7eu4oxm0p5t` (`category_id`),
  CONSTRAINT `FK_ce8j5qq1jpw9q5lbtpfpslw4m` FOREIGN KEY (`unit_id`) REFERENCES `ref_unit` (`id`),
  CONSTRAINT `FK_lrw45uu6x9pa9b7eu4oxm0p5t` FOREIGN KEY (`category_id`) REFERENCES `tbl_shoppingcategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255),
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `map_owners` (
  `list_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`list_id`,`user_id`),
  KEY `FK_kd9qklvuhx01q09bc90969h1k` (`user_id`),
  CONSTRAINT `FK_kd9qklvuhx01q09bc90969h1k` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`),
  CONSTRAINT `FK_nns0dwr8u130c36r13vmbimeq` FOREIGN KEY (`list_id`) REFERENCES `tbl_shoppinglist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
