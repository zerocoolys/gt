# CREATE DATABASE gt IF NOT EXISTS 'gt';

CREATE TABLE `gt`.`accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `balance` VARCHAR(45) NULL,
  `last_login` INT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `gt`.`transactions` (
  `id` INT NOT NULL,
  `no` VARCHAR(128) NULL,
  `from` INT NULL,
  `to` INT NULL,
  `datetime` DATETIME NULL,
  `amount` DECIMAL(16,2) NULL,
  `type` INT(1) NULL,
  PRIMARY KEY (`id`));

