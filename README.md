### Projeto: LiterAlura

#### Descrição

Este projeto faz parte do programa Alura + Oracle ONE - Ifood 6, especificamente do desafio "Spring Boot: Challenge LiterAlura". O objetivo deste desafio é desenvolver uma aplicação que utiliza Spring Boot para realizar operações de busca e gerenciamento de livros e autores.

#### Características

- **Tecnologias Utilizadas**:
  - Spring Boot
  - Spring Data JPA
  - Hibernate
  - PostgreSQL
  - Maven

- **Funcionalidades**:
  - **Buscar Livro pelo Título**: Integração com a API do Gutendex para buscar livros pelo título.
  - **Listar Livros Registrados**: Listagem de todos os livros cadastrados no banco de dados.
  - **Listar Nossos Autores**: Listagem de todos os autores cadastrados no banco de dados.
  - **Listar Autores por Ano de Nascimento**: Listagem de autores filtrados por ano de nascimento.
  - **Listar Livros por Idioma**: Listagem de livros filtrados por idioma (português, inglês, espanhol, francês).

#### Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd literalura
   ```

2. Configure o banco de dados PostgreSQL no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação no terminal e siga as instruções do menu.

#### Estrutura do Projeto

- **Model**: Classes que representam as entidades do banco de dados (Livro, Autor).
- **Repository**: Interfaces que extendem JpaRepository para realizar operações CRUD.
- **Service**: Contém a lógica de negócios, como buscar livros na API externa e salvar no banco de dados.
- **Principal**: Classe principal que interage com o usuário via terminal, exibindo o menu e capturando as escolhas do usuário.

#### Autor

Paulo Rodrigo  
[LinkedIn](https://www.linkedin.com/in/paulorodrigo/)
