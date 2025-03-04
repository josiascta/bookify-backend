CREATE TABLE AUTOR (
                       id_autor SERIAL PRIMARY KEY,
                       nome VARCHAR(100) UNIQUE
);

CREATE TABLE LIVRO (
                       id_livro SERIAL PRIMARY KEY,
                       titulo VARCHAR(100),
                       quantidade_estoque INTEGER,
                       preco DECIMAL(10, 2),
                       categoria VARCHAR(50) NOT NULL
);

CREATE TABLE CARRINHO (
                          id_carrinho SERIAL PRIMARY KEY
);

CREATE TABLE ITEM_CARRINHO (
                               id_item_carrinho SERIAL PRIMARY KEY,
                               id_carrinho INTEGER,
                               id_livro INTEGER,
                               quantidade INTEGER,
                               CONSTRAINT fk_carrinho FOREIGN KEY (id_carrinho) REFERENCES CARRINHO(id_carrinho) ON DELETE CASCADE,
                               CONSTRAINT fk_livro FOREIGN KEY (id_livro) REFERENCES LIVRO(id_livro) ON DELETE CASCADE
);

CREATE TABLE PAGAMENTO (
                           id_pagamento SERIAL PRIMARY KEY,
                           id_carrinho INTEGER,
                           valor_total DECIMAL(10,2),
                           CONSTRAINT fk_pagamento_carrinho FOREIGN KEY (id_carrinho) REFERENCES CARRINHO(id_carrinho) ON DELETE CASCADE
);

CREATE TABLE LIVRO_AUTOR (
                             id_livro INTEGER,
                             id_autor INTEGER,
                             PRIMARY KEY (id_livro, id_autor),
                             CONSTRAINT fk_livro_autor_livro FOREIGN KEY (id_livro) REFERENCES LIVRO(id_livro) ON DELETE CASCADE,
                             CONSTRAINT fk_livro_autor_autor FOREIGN KEY (id_autor) REFERENCES AUTOR(id_autor) ON DELETE CASCADE
);

CREATE TABLE USUARIO (
                         id_usuario SERIAL PRIMARY KEY,
                         login VARCHAR(100) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL
);

CREATE TABLE CARGO (
                       id_cargo SERIAL PRIMARY KEY,
                       nome VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE USUARIO_CARGO (
                               id_usuario INTEGER,
                               id_cargo INTEGER,
                               PRIMARY KEY (id_usuario, id_cargo),
                               CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario) ON DELETE CASCADE,
                               CONSTRAINT fk_cargo FOREIGN KEY (id_cargo) REFERENCES CARGO(id_cargo) ON DELETE CASCADE
);

