# Bookify API

Bookify API é uma API REST desenvolvida em Java com Spring Boot para gerenciar uma livraria online. O projeto utiliza PostgreSQL como banco de dados, rodando em um container Docker.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.3**
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Security
  - Spring Boot Starter Validation
  - Spring Boot Starter Test
- **Lombok 1.18.22**
- **PostgreSQL**
- **Docker**
- **JWT (JSON Web Token) para Autenticação**

## Endpoints Principais

### Autenticação

- `POST /auth/register` - Registra um novo usuário
- `POST /auth/token` - Gera token JWT

### Autores

- `GET /autor/{id}` - Obtém um autor pelo ID
- `GET /autor` - Lista autores com paginação
- `POST /autor` - Cria um novo autor
- `DELETE /autor/{id}` - Deleta um autor

### Livros

- `POST /livro` - Adiciona um novo livro
- `GET /livro/{id}` - Obtém detalhes de um livro
- `PUT /livro/{id}` - Atualiza informações de um livro
- `DELETE /livro/{id}` - Remove um livro
- `GET /livro` - Lista livros com paginação

### Categorias

- `GET /categoria` - Lista todas as categorias

## Contribuição

1. Fork este repositório
2. Crie uma nova branch (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m 'Minha nova feature'`)
4. Envie para o repositório remoto (`git push origin minha-feature`)
5. Abra um Pull Request

## Sobre o Projeto

Este projeto foi desenvolvido por **Davi Rodrigues de Almeida** e **Josias Carneiro Teixeira de Araújo** como parte da disciplina **Desenvolvimento de Aplicações Corporativas (DAC)** no **Instituto Federal da Paraíba (IFPB)**.


