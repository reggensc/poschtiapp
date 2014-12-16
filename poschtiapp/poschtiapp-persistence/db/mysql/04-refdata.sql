DROP TABLE IF EXISTS `ref_state`;

CREATE TABLE `ref_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `poschtiapp`.`ref_state`
(`stateName`)
VALUES
('Open');

INSERT INTO `poschtiapp`.`ref_state`
(`stateName`)
VALUES
('InPurchase');

INSERT INTO `poschtiapp`.`ref_state`
(`stateName`)
VALUES
('Purchased');
