-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema puppey
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema puppey
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `puppey` DEFAULT CHARACTER SET latin1 ;
USE `puppey` ;

-- -----------------------------------------------------
-- Table `puppey`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puppey`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `enabled` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `puppey`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puppey`.`authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  INDEX `fk_authorities_users` (`username` ASC),
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`username`)
    REFERENCES `puppey`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `puppey`.`tournament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puppey`.`tournament` (
  `t_id` INT(11) NOT NULL AUTO_INCREMENT,
  `t_name` VARCHAR(45) NOT NULL,
  `t_start` DATETIME NULL DEFAULT NULL,
  `t_num_matchups` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 77
DEFAULT CHARACTER SET = utf8
COMMENT = 'Table that holds tournament basic information';


-- -----------------------------------------------------
-- Table `puppey`.`matchup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puppey`.`matchup` (
  `m_id` INT(11) NOT NULL AUTO_INCREMENT,
  `team_1` INT(11) NULL DEFAULT NULL,
  `team_2` INT(11) NULL DEFAULT NULL,
  `winner` INT(11) NULL DEFAULT NULL,
  `t_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  INDEX `FK_7baokll3vnqredc5m5s6jhk8b` (`t_id` ASC),
  CONSTRAINT `FK_7baokll3vnqredc5m5s6jhk8b`
    FOREIGN KEY (`t_id`)
    REFERENCES `puppey`.`tournament` (`t_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `puppey`.`round`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puppey`.`round` (
  `r_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `weight` INT(11) NULL DEFAULT NULL,
  `t_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`),
  INDEX `t_id_idx` (`t_id` ASC),
  CONSTRAINT `t_id`
    FOREIGN KEY (`t_id`)
    REFERENCES `puppey`.`tournament` (`t_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `puppey`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puppey`.`status` (
  `status_id` INT(11) NOT NULL AUTO_INCREMENT,
  `desc` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'status descriptions';


-- -----------------------------------------------------
-- Table `puppey`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puppey`.`team` (
  `team_id` INT(11) NOT NULL AUTO_INCREMENT,
  `team_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`team_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COMMENT = 'team information';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
