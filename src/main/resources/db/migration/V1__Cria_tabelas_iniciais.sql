
-- -----------------------------------------------------
-- Schema db_soat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_produto` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_produto` ;

-- -----------------------------------------------------
-- Table `db_soat`.`tb_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_produto`.`tb_produto`
(
    `id_produto` BIGINT NOT NULL AUTO_INCREMENT,
    `categoria` ENUM('bebida', 'lanche', 'acompanhamento', 'sobremesa') NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `valor` DECIMAL(10,2) NOT NULL,
    `status` TINYINT NOT NULL DEFAULT '1',
    PRIMARY KEY (`id_produto`));
