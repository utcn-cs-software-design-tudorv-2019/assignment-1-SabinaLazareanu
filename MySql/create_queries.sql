-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema university_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema university_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `university_db` DEFAULT CHARACTER SET utf8 ;
USE `university_db` ;

-- -----------------------------------------------------
-- Table `university_db`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `university_db`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `PNC` VARCHAR(255) NULL,
  `ICN` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `university_db`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `university_db`.`Student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `User_id` INT NOT NULL,
  `user_name` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  `groupF` VARCHAR(255) NULL,
  PRIMARY KEY (`id`, `User_id`),
  INDEX `fk_Student_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Student_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `university_db`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `university_db`.`Teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `university_db`.`Teacher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `User_id` INT NOT NULL,
  `user_name` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  PRIMARY KEY (`id`, `User_id`),
  INDEX `fk_Teacher_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Teacher_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `university_db`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `university_db`.`Cours`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `university_db`.`Cours` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_name` VARCHAR(255) NULL,
  `examDate` DATE NULL,
  `Teacher_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Teacher_id`),
  INDEX `fk_Cours_Teacher1_idx` (`Teacher_id` ASC),
  CONSTRAINT `fk_Cours_Teacher1`
    FOREIGN KEY (`Teacher_id`)
    REFERENCES `university_db`.`Teacher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `university_db`.`Enrollement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `university_db`.`Enrollement` (
  `Student_id` INT NOT NULL,
  `Cours_id` INT NOT NULL,
  `grade` FLOAT NULL,
  PRIMARY KEY (`Student_id`, `Cours_id`),
  INDEX `fk_Student_has_Cours_Cours1_idx` (`Cours_id` ASC),
  INDEX `fk_Student_has_Cours_Student1_idx` (`Student_id` ASC),
  CONSTRAINT `fk_Student_has_Cours_Student1`
    FOREIGN KEY (`Student_id`)
    REFERENCES `university_db`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Cours_Cours1`
    FOREIGN KEY (`Cours_id`)
    REFERENCES `university_db`.`Cours` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
