-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema travel_management_portal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travel_management_portal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel_management_portal` DEFAULT CHARACTER SET utf8 ;
USE `travel_management_portal` ;

-- -----------------------------------------------------
-- Table `travel_management_portal`.`app_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_management_portal`.`app_users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NULL DEFAULT NULL,
  `user_password` VARCHAR(45) NULL DEFAULT NULL,
  `user_paypallets_account_id` VARCHAR(45) NULL DEFAULT NULL,
  `user_paypallets_account_amount` DOUBLE NULL DEFAULT NULL,
  `user_paypallets_account_currency` VARCHAR(3) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_management_portal`.`purchased_tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_management_portal`.`purchased_tickets` (
  `ticket_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `from_place` VARCHAR(45) NULL DEFAULT NULL,
  `to_place` VARCHAR(45) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `currency` VARCHAR(3) NULL DEFAULT NULL,
  `amount` INT(11) NULL DEFAULT NULL,
  `purchasing_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  INDEX `user_fk_idx` (`user_id` ASC),
  CONSTRAINT `user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel_management_portal`.`app_users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
