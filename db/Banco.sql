CREATE USER BOOKIFY IDENTIFIED BY oracle;
GRANT CONNECT TO BOOKIFY;
GRANT CONNECT, RESOURCE, DBA TO BOOKIFY;
GRANT CREATE SESSION TO BOOKIFY;
GRANT DBA TO BOOKIFY;
GRANT CREATE VIEW, CREATE PROCEDURE, CREATE SEQUENCE to BOOKIFY;
GRANT UNLIMITED TABLESPACE TO BOOKIFY;
GRANT CREATE MATERIALIZED VIEW TO BOOKIFY;
GRANT CREATE TABLE TO BOOKIFY;
GRANT GLOBAL QUERY REWRITE TO BOOKIFY;
GRANT SELECT ANY TABLE TO BOOKIFY;

-- Tabela de Categorias
CREATE TABLE CATEGORIA (
    id_categoria NUMBER PRIMARY KEY,
    nome VARCHAR2(100) UNIQUE
);

CREATE SEQUENCE SEQ_CATEGORIA
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela de Autores
CREATE TABLE AUTOR (
    id_autor NUMBER PRIMARY KEY,
    nome VARCHAR2(100) UNIQUE
);

CREATE SEQUENCE SEQ_AUTOR
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela de Administradores
CREATE TABLE ADMINISTRADOR (
    id_administrador NUMBER PRIMARY KEY,
    nome VARCHAR2(100),
    email VARCHAR2(100) UNIQUE,
    senha VARCHAR2(100)
);

CREATE SEQUENCE SEQ_ADMINISTRADOR
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela de Livros
CREATE TABLE LIVRO (
    id_livro NUMBER PRIMARY KEY,
    titulo VARCHAR2(100),
    quantidade_estoque NUMBER,
    preco DECIMAL(10, 2)
);

CREATE SEQUENCE SEQ_LIVRO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela Cliente
CREATE TABLE CLIENTE (
    id_cliente NUMBER PRIMARY KEY,
    nome VARCHAR2(100),
    email VARCHAR2(100) UNIQUE,
    telefone VARCHAR2(13)
);

CREATE SEQUENCE SEQ_CLIENTE
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela Carrinho
CREATE TABLE CARRINHO (
    id_carrinho NUMBER PRIMARY KEY,
    id_cliente NUMBER NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente) ON DELETE CASCADE
);

CREATE SEQUENCE SEQ_CARRINHO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela Item de Carrinho
CREATE TABLE ITEM_CARRINHO (
    id_item_carrinho NUMBER PRIMARY KEY,
    id_carrinho NUMBER,
    id_livro NUMBER,
    quantidade NUMBER,
    CONSTRAINT fk_carrinho FOREIGN KEY (id_carrinho) REFERENCES CARRINHO(id_carrinho) ON DELETE CASCADE,
    CONSTRAINT fk_livro FOREIGN KEY (id_livro) REFERENCES LIVRO(id_livro) ON DELETE CASCADE
);

CREATE SEQUENCE SEQ_ITEM_CARRINHO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela de Pagamentos
CREATE TABLE PAGAMENTO (
    id_pagamento NUMBER PRIMARY KEY,
    id_carrinho NUMBER,
    valor_total DECIMAL(10 ,2),
    CONSTRAINT fk_pagamento_carrinho FOREIGN KEY (id_carrinho) REFERENCES CARRINHO(id_carrinho) ON DELETE CASCADE
);

CREATE SEQUENCE SEQ_PAGAMENTO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Tabela Intermediária para Relação Many-to-Many entre LIVRO e AUTOR
CREATE TABLE LIVRO_AUTOR (
    id_livro NUMBER,
    id_autor NUMBER,
    PRIMARY KEY (id_livro, id_autor),
    CONSTRAINT fk_livro_autor_livro FOREIGN KEY (id_livro) REFERENCES LIVRO(id_livro) ON DELETE CASCADE,
    CONSTRAINT fk_livro_autor_autor FOREIGN KEY (id_autor) REFERENCES AUTOR(id_autor) ON DELETE CASCADE
);

-- Tabela Intermediária para Relação Many-to-Many entre LIVRO e CATEGORIA
CREATE TABLE LIVRO_CATEGORIA (
    id_livro NUMBER,
    id_categoria NUMBER,
    PRIMARY KEY (id_livro, id_categoria),
    CONSTRAINT fk_livro_categoria_livro FOREIGN KEY (id_livro) REFERENCES LIVRO(id_livro) ON DELETE CASCADE,
    CONSTRAINT fk_livro_categoria_categoria FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria) ON DELETE CASCADE
);
