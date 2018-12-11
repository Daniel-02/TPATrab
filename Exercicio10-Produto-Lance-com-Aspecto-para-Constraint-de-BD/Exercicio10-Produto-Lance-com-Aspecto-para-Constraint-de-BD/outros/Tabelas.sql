DROP USER JPA05 CASCADE;
CREATE USER JPA05 IDENTIFIED BY JPA05;
GRANT CONNECT, RESOURCE TO JPA05;

CONNECT JPA05/JPA05

DROP TABLE LANCE CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_LANCE;

DROP TABLE PRODUTO CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_PRODUTO;

CREATE SEQUENCE SEQ_LANCE;
CREATE SEQUENCE SEQ_PRODUTO;

CREATE TABLE PRODUTO
(ID             NUMBER(5)
   CONSTRAINT PRODUTO_ID_PK
   PRIMARY KEY,
 NOME           VARCHAR2(30) 
   CONSTRAINT PRODUTO_NOME_NN
   NOT NULL
   CONSTRAINT PRODUTO_NOME_UN
   UNIQUE,
 DESCRICAO      VARCHAR2(40),
 LANCE_MINIMO  NUMBER(8,2)
   CONSTRAINT PRODUTO_LANCE_MINIMO_NN
   NOT NULL,
 DATA_CADASTRO  DATE
   CONSTRAINT PRODUTO_DATA_CADASTRO_NN
   NOT NULL,
 DATA_VENDA     DATE);

CREATE TABLE LANCE
(ID             NUMBER(5)
   CONSTRAINT LANCE_ID_PK
   PRIMARY KEY,
 VALOR          NUMBER(8,2)
   CONSTRAINT LANCE_VALOR_NN
   NOT NULL,
 DATA_CRIACAO   DATE
   CONSTRAINT LANCE_DATA_CRIACAO_NN
   NOT NULL,
 PRODUTO_ID     NUMBER(5)
   CONSTRAINT LANCE_PRODUTO_FK
   REFERENCES PRODUTO(ID)
   CONSTRAINT LANCE_PRODUTO_ID_NN
   NOT NULL);

INSERT INTO PRODUTO(ID, NOME, DESCRICAO, LANCE_MINIMO, DATA_CADASTRO)
VALUES(SEQ_PRODUTO.NEXTVAL, 'TV SAMSUNG 20 POL', 'TV SAMSUNG 20 POL TELA PLANA', 2000, SYSDATE);

INSERT INTO LANCE(ID, VALOR, DATA_CRIACAO, PRODUTO_ID)
VALUES(SEQ_LANCE.NEXTVAL, 2100, SYSDATE, SEQ_PRODUTO.CURRVAL);

INSERT INTO LANCE(ID, VALOR, DATA_CRIACAO, PRODUTO_ID)
VALUES(SEQ_LANCE.NEXTVAL, 2200, SYSDATE, SEQ_PRODUTO.CURRVAL);

INSERT INTO PRODUTO(ID, NOME, DESCRICAO, LANCE_MINIMO, DATA_CADASTRO)
VALUES(SEQ_PRODUTO.NEXTVAL, 'TV SAMSUNG 22 POL', 'TV SAMSUNG 22 POL TELA PLANA', 2500, SYSDATE);

INSERT INTO LANCE(ID, VALOR, DATA_CRIACAO, PRODUTO_ID)
VALUES(SEQ_LANCE.NEXTVAL, 2600, SYSDATE, SEQ_PRODUTO.CURRVAL);

INSERT INTO LANCE(ID, VALOR, DATA_CRIACAO, PRODUTO_ID)
VALUES(SEQ_LANCE.NEXTVAL, 2700, SYSDATE, SEQ_PRODUTO.CURRVAL);

COMMIT;


Para exibir os dados cadastrados:
================================

alter session set nls_date_format='dd/MM/yyyy hh24:mi:ss';

select id, nome, PRECO_INICIAL, DATA_INICIAL
from produtos;

select * from lances;
