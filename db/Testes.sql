--I nserts para teste de código
-- Criar autores
INSERT INTO autor (nome) VALUES
                             ('J.K. Rowling'),
                             ('George Orwell'),
                             ('Agatha Christie'),
                             ('J.R.R. Tolkien'),
                             ('Leo Tolstoy'),
                             ('Harper Lee'),
                             ('F. Scott Fitzgerald'),
                             ('Mark Twain'),
                             ('Ernest Hemingway'),
                             ('Charles Dickens');

-- Criar livros
INSERT INTO livro (titulo, quantidade_estoque, preco, categoria) VALUES
                                                                     ('Harry Potter e a Pedra Filosofal', 10, 39.90, 'FANTASIA'),
                                                                     ('1984', 8, 29.90, 'DISTOPIA'),
                                                                     ('Assassinato no Expresso do Oriente', 12, 49.90, 'MISTERIO'),
                                                                     ('O Senhor dos Anéis: A Sociedade do Anel', 7, 59.90, 'FANTASIA'),
                                                                     ('Guerra e Paz', 5, 89.90, 'HISTORIA'),
                                                                     ('O Sol é Para Todos', 15, 34.90, 'DRAMA'),
                                                                     ('O Grande Gatsby', 6, 24.90, 'ROMANCE'),
                                                                     ('As Aventuras de Tom Sawyer', 20, 19.90, 'AVENTURA'),
                                                                     ('O Velho e o Mar', 9, 27.90, 'DRAMA'),
                                                                     ('Oliver Twist', 10, 22.90, 'DRAMA');

-- Associar livros aos autores
INSERT INTO livro_autor (id_livro, id_autor) VALUES
                                                 (1, 1),
                                                 (2, 2),
                                                 (3, 3),
                                                 (4, 4),
                                                 (5, 5),
                                                 (6, 6),
                                                 (7, 7),
                                                 (8, 8),
                                                 (9, 9),
                                                 (10, 10);

-- Criar cargos
INSERT INTO CARGO (nome) VALUES
                             ('ROLE_ADMIN'),
                             ('ROLE_USER'),
                             ('ROLE_MANAGER');

