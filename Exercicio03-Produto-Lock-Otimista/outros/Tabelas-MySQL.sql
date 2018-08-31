https://dev.mysql.com/downloads/

Download MySQL Community Server

Últimas versões:
- 5.5
- 5.6
- 5.7
- 8.0.12 (atual)

DROP TABLE PRODUTO;

CREATE TABLE banco1.produtoV (
  id INT(11) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(30) NOT NULL,
  lance_minimo DECIMAL(8, 2) NOT NULL,
  data_cadastro DATE NOT NULL,
  data_venda DATE DEFAULT NULL,
  versao INT(11),
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO produtoV(NOME, LANCE_MINIMO, DATA_CADASTRO, VERSAO)
VALUES('TV SAMSUNG 20 POL', 2000, now(), 0);

INSERT INTO produtoV(NOME, LANCE_MINIMO, DATA_CADASTRO, VERSAO)
VALUES('TV SAMSUNG 22 POL', 2500, now(), 0);
