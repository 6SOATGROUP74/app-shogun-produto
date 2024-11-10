-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_soat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_soat` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_soat` ;

-- -----------------------------------------------------
-- Table `db_soat`.`tb_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_soat`.`tb_produto` (
                                                      `id_produto` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `categoria` ENUM('bebida', 'lanche', 'acompanhamento', 'sobremesa') NULL DEFAULT NULL,
    `nome` VARCHAR(255) NULL DEFAULT NULL,
    `valor` DECIMAL(10,2) NULL DEFAULT NULL,
    `status` TINYINT NOT NULL DEFAULT '1',
    PRIMARY KEY (`id_produto`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 11
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

