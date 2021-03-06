SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`PERSON`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`PERSON` (
  `ID` INT NOT NULL ,
  `NICKNAME` VARCHAR(15) NULL ,
  `EMAIL` VARCHAR(100) NOT NULL ,
  `FIRSTNAME` VARCHAR(50) NULL ,
  `LASTNAME` VARCHAR(50) NULL ,
  `REGISTERED` TINYINT(1) NULL ,
  `PASSWORD` VARCHAR(32) NULL COMMENT 'MD5 Encrypted Password' ,
  `CREATED_TIME` DATETIME NOT NULL ,
  `CREATED_VIA` ENUM('FORM','COMMENT','ARTICLE_LIKE') NULL COMMENT 'FORM : created by using registeration form\nCOMMENT: created by using comment form' ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) ,
  UNIQUE INDEX `NICKNAME_UNIQUE` (`NICKNAME` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CATEGORY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`CATEGORY` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `PARENT_ID` INT NULL ,
  `NAME` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ARTICLE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`ARTICLE` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `AUTHOR_ID` INT NOT NULL ,
  `CATEGORY_ID` INT NULL ,
  `TITLE` VARCHAR(255) NOT NULL ,
  `INTRO_TEXT` VARCHAR(500) NULL ,
  `FULL_TEXT` LONGTEXT NULL ,
  `CREATED_TIME` DATETIME NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_AUTHOR` (`AUTHOR_ID` ASC) ,
  INDEX `FK_CATEGORY` (`CATEGORY_ID` ASC) ,
  CONSTRAINT `FK_AUTHOR`
    FOREIGN KEY (`AUTHOR_ID` )
    REFERENCES `mydb`.`PERSON` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CATEGORY`
    FOREIGN KEY (`CATEGORY_ID` )
    REFERENCES `mydb`.`CATEGORY` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LOG_ARTICLE_VIEW`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`LOG_ARTICLE_VIEW` (
  `ID` VARCHAR(45) NOT NULL ,
  `ARTICLE_ID` INT NOT NULL ,
  `REFERRER` TINYTEXT NULL ,
  `VIEW_TIME` DATETIME NOT NULL ,
  `IP` VARCHAR(15) NULL ,
  `VIEWED_BY` INT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_ARTICLE` (`ARTICLE_ID` ASC) ,
  INDEX `FK_VIEWED_BY` (`VIEWED_BY` ASC) ,
  CONSTRAINT `FK_ARTICLE`
    FOREIGN KEY (`ARTICLE_ID` )
    REFERENCES `mydb`.`ARTICLE` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_VIEWED_BY`
    FOREIGN KEY (`VIEWED_BY` )
    REFERENCES `mydb`.`PERSON` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ENTITY_TYPE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`ENTITY_TYPE` (
  `TYPE` VARCHAR(15) NOT NULL ,
  UNIQUE INDEX `UNQ_TYPE` (`TYPE` ASC) ,
  PRIMARY KEY (`TYPE`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`COMMENT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`COMMENT` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `WRITTEN_BY` INT NOT NULL COMMENT 'Comment girişinde username ve email adresi girişleri, person tablosuna kaydedilecek. ama Registered flag\'i boş olacak.' ,
  `COMMENT` TEXT NOT NULL ,
  `CREATED_TIME` DATETIME NOT NULL ,
  `ENTITY_ID` INT NOT NULL ,
  `ENTITY_TYPE` VARCHAR(15) NOT NULL ,
  `PARENT_ID` INT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_WRITTEN_BY` (`WRITTEN_BY` ASC) ,
  INDEX `FK_ENTITY_TYPE` (`ENTITY_TYPE` ASC) ,
  INDEX `FK_PARENT` (`PARENT_ID` ASC) ,
  CONSTRAINT `FK_WRITTEN_BY`
    FOREIGN KEY (`WRITTEN_BY` )
    REFERENCES `mydb`.`PERSON` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ENTITY_TYPE`
    FOREIGN KEY (`ENTITY_TYPE` )
    REFERENCES `mydb`.`ENTITY_TYPE` (`TYPE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PARENT`
    FOREIGN KEY (`PARENT_ID` )
    REFERENCES `mydb`.`COMMENT` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`STAT_ENTITIES`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`STAT_ENTITIES` (
  `ENTITY_ID` INT NOT NULL ,
  `ENTITY_TYPE` VARCHAR(15) NOT NULL ,
  `COMMENT_COUNT` INT NULL DEFAULT 0 ,
  `VIEW_COUNT` INT NULL DEFAULT 0 ,
  `RATING` INT NULL DEFAULT 0 ,
  `LIKE_COUNT` INT NULL DEFAULT 0 ,
  PRIMARY KEY (`ENTITY_ID`, `ENTITY_TYPE`) ,
  INDEX `FK_ENTITY_TYPE` (`ENTITY_TYPE` ASC) ,
  CONSTRAINT `FK_ENTITY_TYPE`
    FOREIGN KEY (`ENTITY_TYPE` )
    REFERENCES `mydb`.`ENTITY_TYPE` (`TYPE` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LOG_LIKES`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`LOG_LIKES` (
  `ENTITY_ID` INT NOT NULL ,
  `ENTITY_TYPE` VARCHAR(15) NOT NULL COMMENT 'ENTITY_TYPE {COMMENT,ARTICLE,IMAGE}' ,
  `LIKED_BY` INT NULL ,
  `LIKE_TIME` DATETIME NOT NULL ,
  PRIMARY KEY (`ENTITY_ID`, `ENTITY_TYPE`) ,
  INDEX `FK_LIKED_BY` (`LIKED_BY` ASC) ,
  INDEX `FK_ENTITY_TYPE` (`ENTITY_TYPE` ASC) ,
  CONSTRAINT `FK_LIKED_BY`
    FOREIGN KEY (`LIKED_BY` )
    REFERENCES `mydb`.`PERSON` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ENTITY_TYPE`
    FOREIGN KEY (`ENTITY_TYPE` )
    REFERENCES `mydb`.`ENTITY_TYPE` (`TYPE` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TAG_VALUE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`TAG_VALUE` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `TEXT` VARCHAR(50) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `IDX_TEXT` (`TEXT` ASC) ,
  UNIQUE INDEX `UNQ_TEXT` (`TEXT` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ENTITY_TAG`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`ENTITY_TAG` (
  `ENTITY_ID` INT NOT NULL ,
  `ENTITY_TYPE` VARCHAR(15) NOT NULL ,
  `TAG_ID` INT NOT NULL ,
  PRIMARY KEY (`ENTITY_ID`, `ENTITY_TYPE`, `TAG_ID`) ,
  INDEX `FK_TAG` (`TAG_ID` ASC) ,
  INDEX `FK_ENTITY_TYPE` (`ENTITY_TYPE` ASC) ,
  CONSTRAINT `FK_TAG`
    FOREIGN KEY (`TAG_ID` )
    REFERENCES `mydb`.`TAG_VALUE` (`ID` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ENTITY_TYPE`
    FOREIGN KEY (`ENTITY_TYPE` )
    REFERENCES `mydb`.`ENTITY_TYPE` (`TYPE` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
