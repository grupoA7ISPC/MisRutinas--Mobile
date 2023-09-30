SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MisRutinasApp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `MisRutinasApp` ;

-- -----------------------------------------------------
-- Schema MisRutinasApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MisRutinasApp` DEFAULT CHARACTER SET utf8 ;
USE `MisRutinasApp` ;

-- -----------------------------------------------------
-- Table `MisRutinasApp`.`rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`rol` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`rol` (
  `id_rol` INT NOT NULL AUTO_INCREMENT,
  `nombre_rol` CHAR(10) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE INDEX `id_rol_UNIQUE` (`id_rol` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`usuario` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `dni` INT NULL,
  `fec_nac` DATE NULL,
  `email` VARCHAR(45) NULL,
  `pass` VARCHAR(45) NULL,
  `active` TINYINT NULL,
  `usuariocol` VARCHAR(45) NULL,
  `fk_rol` INT NOT NULL,
  PRIMARY KEY (`id_usuario`, `fk_rol`),
  UNIQUE INDEX `id_socio_UNIQUE` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_usuario_rol1_idx` (`fk_rol` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_rol1`
    FOREIGN KEY (`fk_rol`)
    REFERENCES `MisRutinasApp`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`socio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`socio` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`socio` (
  `id_socio` INT NOT NULL AUTO_INCREMENT,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`id_socio`, `fk_usuario`),
  UNIQUE INDEX `id_socio_UNIQUE` (`id_socio` ASC) VISIBLE,
  INDEX `fk_socio_usuario1_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_socio_usuario1`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `MisRutinasApp`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`entrenador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`entrenador` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`entrenador` (
  `id_entrenador` INT NOT NULL,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`id_entrenador`, `fk_usuario`),
  INDEX `fk_entrenador_usuario1_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_entrenador_usuario1`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `MisRutinasApp`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`calendario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`calendario` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`calendario` (
  `id_calen` INT NOT NULL AUTO_INCREMENT,
  `hora` TIME NOT NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`id_calen`),
  UNIQUE INDEX `id_calen_UNIQUE` (`id_calen` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`clase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`clase` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`clase` (
  `id_clase` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DECIMAL NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `clasecol` VARCHAR(45) NULL,
  PRIMARY KEY (`id_clase`),
  UNIQUE INDEX `id_clase_UNIQUE` (`id_clase` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`horario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`horario` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`horario` (
  `id_hor` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `inicio` TIME NOT NULL,
  `fin` TIME NOT NULL,
  PRIMARY KEY (`id_hor`),
  UNIQUE INDEX `id_hor_UNIQUE` (`id_hor` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`dia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`dia` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`dia` (
  `id_dia` INT NOT NULL AUTO_INCREMENT,
  `nombre` CHAR(9) NOT NULL,
  PRIMARY KEY (`id_dia`),
  UNIQUE INDEX `id_dia_UNIQUE` (`id_dia` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`turno` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`turno` (
  `id_turno` INT NOT NULL AUTO_INCREMENT,
  `nombre` CHAR(6) NOT NULL,
  `fk_dia` INT NOT NULL,
  `fk_horario` INT NOT NULL,
  PRIMARY KEY (`id_turno`, `fk_dia`, `fk_horario`),
  UNIQUE INDEX `id_turno_UNIQUE` (`id_turno` ASC) VISIBLE,
  INDEX `fk_turno_dia1_idx` (`fk_dia` ASC) VISIBLE,
  INDEX `fk_turno_horario1_idx` (`fk_horario` ASC) VISIBLE,
  CONSTRAINT `fk_turno_dia1`
    FOREIGN KEY (`fk_dia`)
    REFERENCES `MisRutinasApp`.`dia` (`id_dia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turno_horario1`
    FOREIGN KEY (`fk_horario`)
    REFERENCES `MisRutinasApp`.`horario` (`id_hor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`clase_turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`clase_turno` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`clase_turno` (
  `fk_clase` INT NOT NULL,
  `fk_turno` INT NOT NULL,
  `fk_entren` INT NOT NULL,
  `id_clase_turno` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`fk_clase`, `fk_turno`, `fk_entren`, `id_clase_turno`),
  INDEX `fk_clase_has_turno_turno1_idx` (`fk_turno` ASC) VISIBLE,
  INDEX `fk_clase_has_turno_clase_idx` (`fk_clase` ASC) VISIBLE,
  UNIQUE INDEX `id_clase_turno_UNIQUE` (`id_clase_turno` ASC) VISIBLE,
  INDEX `fk_clase_turno_entrenador1_idx` (`fk_entren` ASC) VISIBLE,
  CONSTRAINT `fk_clase_has_turno_clase`
    FOREIGN KEY (`fk_clase`)
    REFERENCES `MisRutinasApp`.`clase` (`id_clase`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clase_has_turno_turno1`
    FOREIGN KEY (`fk_turno`)
    REFERENCES `MisRutinasApp`.`turno` (`id_turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clase_turno_entrenador1`
    FOREIGN KEY (`fk_entren`)
    REFERENCES `MisRutinasApp`.`entrenador` (`id_entrenador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`socio_clase_turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`socio_clase_turno` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`socio_clase_turno` (
  `id_soc_cla` INT NOT NULL AUTO_INCREMENT,
  `fk_socio` INT NOT NULL,
  `fk_clase_turno` INT NOT NULL,
  PRIMARY KEY (`id_soc_cla`, `fk_socio`, `fk_clase_turno`),
  UNIQUE INDEX `id_soc_cla_UNIQUE` (`id_soc_cla` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_socio1_idx` (`fk_socio` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_clase_turno1_idx` (`fk_clase_turno` ASC) VISIBLE,
  CONSTRAINT `fk_socio_clase_turno_socio1`
    FOREIGN KEY (`fk_socio`)
    REFERENCES `MisRutinasApp`.`socio` (`id_socio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_socio_clase_turno_clase_turno1`
    FOREIGN KEY (`fk_clase_turno`)
    REFERENCES `MisRutinasApp`.`clase_turno` (`id_clase_turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`socio_clase_turno_calendario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`socio_clase_turno_calendario` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`socio_clase_turno_calendario` (
  `id_soc_cla_cal` INT NOT NULL AUTO_INCREMENT,
  `fk_socio_clase_turno` INT NOT NULL,
  `fk_calendario` INT NOT NULL,
  PRIMARY KEY (`id_soc_cla_cal`, `fk_socio_clase_turno`, `fk_calendario`),
  UNIQUE INDEX `id_soc_cla_cal_UNIQUE` (`id_soc_cla_cal` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_calendario_socio_clase_turno1_idx` (`fk_socio_clase_turno` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_calendario_calendario1_idx` (`fk_calendario` ASC) VISIBLE,
  CONSTRAINT `fk_socio_clase_turno_calendario_socio_clase_turno1`
    FOREIGN KEY (`fk_socio_clase_turno`)
    REFERENCES `MisRutinasApp`.`socio_clase_turno` (`id_soc_cla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_socio_clase_turno_calendario_calendario1`
    FOREIGN KEY (`fk_calendario`)
    REFERENCES `MisRutinasApp`.`calendario` (`id_calen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
