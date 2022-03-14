-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema conference
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `conference` ;

-- -----------------------------------------------------
-- Schema conference
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `conference` DEFAULT CHARACTER SET utf8 ;
USE `conference` ;

-- -----------------------------------------------------
-- Table `conference`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `conference`.`user_roles` (
  `role_id` INT NOT NULL,
  `role_description` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_description_UNIQUE` (`role_description` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `conference`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`users` ;

CREATE TABLE IF NOT EXISTS `conference`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `user_name` VARCHAR(32) NOT NULL,
  `user_surname` VARCHAR(32) NOT NULL,
  `user_password` VARCHAR(32) NOT NULL,
  `user_phone` VARCHAR(32) NOT NULL,
  `user_email` VARCHAR(32) NOT NULL,
  `user_photo_url` VARCHAR(255) NOT NULL,
  `user_address` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `user_phone_UNIQUE` (`user_phone` ASC) VISIBLE,
    FOREIGN KEY (`role_id`)
    REFERENCES `conference`.`user_roles` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`events` ;

CREATE TABLE IF NOT EXISTS `conference`.`events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_name_ua` VARCHAR(128) NOT NULL,
  `event_name_en` VARCHAR(128) NOT NULL,
  `event_place_ua` VARCHAR(128) NOT NULL,
  `event_place_en` VARCHAR(128) NOT NULL,
  `event_description_ua` VARCHAR(255) NOT NULL,
  `event_description_en` VARCHAR(255) NOT NULL,
  `event_date` DATETIME NOT NULL,
  `event_photo_url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`event_id`));


-- -----------------------------------------------------
-- Table `conference`.`reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`reports` ;

CREATE TABLE IF NOT EXISTS `conference`.`reports` (
  `report_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `report_name_ua` VARCHAR(45) NOT NULL,
  `report_name_en` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`report_id`),
    FOREIGN KEY (`event_id`)
    REFERENCES `conference`.`events` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`event_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`event_users` ;

CREATE TABLE IF NOT EXISTS `conference`.`event_users` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `present` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`event_id`)
    REFERENCES `conference`.`events` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`category` ;

CREATE TABLE IF NOT EXISTS `conference`.`category` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `conference`.`reports_speakers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`reports_speakers` ;

CREATE TABLE IF NOT EXISTS `conference`.`reports_speakers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `report_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `report_id_UNIQUE` (`report_id` ASC) VISIBLE,
    FOREIGN KEY (`report_id`)
    REFERENCES `conference`.`reports` (`report_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`speaker_preposition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`speaker_preposition` ;

CREATE TABLE IF NOT EXISTS `conference`.`speaker_preposition` (
  `id` INT NOT NULL,
  `report_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `speaker_id_UNIQUE` (`speaker_id` ASC) VISIBLE,
  UNIQUE INDEX `report_id_UNIQUE` (`report_id` ASC) VISIBLE,
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`report_id`)
    REFERENCES `conference`.`reports` (`report_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `conference`.`report_preposition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`report_preposition` ;

CREATE TABLE IF NOT EXISTS `conference`.`report_preposition` (
  `id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  `report_name_ua` VARCHAR(45) NOT NULL,
  `report_name_en` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`event_id`)
    REFERENCES `conference`.`events` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`moderator_preposition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`moderator_preposition` ;

CREATE TABLE IF NOT EXISTS `conference`.`moderator_preposition` (
  `id` INT NOT NULL,
  `report_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `speaker_id_UNIQUE` (`speaker_id` ASC) VISIBLE,
  UNIQUE INDEX `report_id_UNIQUE` (`report_id` ASC) VISIBLE,
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`report_id`)
    REFERENCES `conference`.`reports` (`report_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
