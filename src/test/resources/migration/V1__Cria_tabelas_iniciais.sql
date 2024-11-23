CREATE TABLE IF NOT EXISTS "tb_produto"
(
    "id_produto" BIGINT NOT NULL AUTO_INCREMENT,
    "categoria" VARCHAR NOT NULL,
    "nome" VARCHAR(255) NOT NULL,
    "valor" DECIMAL(10,2) NOT NULL,
    "status" TINYINT NOT NULL DEFAULT '1',
    PRIMARY KEY ("id_produto"));

