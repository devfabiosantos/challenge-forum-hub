# Challenge Fórum Hub

🚀 **Challenge Fórum Hub** é uma API REST desenvolvida em Java com Spring Boot para gerenciamento de tópicos de um fórum. Este projeto faz parte de um desafio técnico e demonstra habilidades de back-end, autenticação JWT e práticas de segurança com Spring Security.

## 📋 Descrição

A aplicação permite:
- **Cadastro de usuários e autenticação via JWT**
- **Criação, listagem, detalhamento, atualização e exclusão de tópicos**
- Controle de acesso com **Spring Security**
- Banco de dados relacional gerenciado com **Flyway e MySQL**

## ⚙️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **MySQL**
- **Flyway**
- **Lombok**
- **JWT (io.jsonwebtoken)**
- **Insomnia** (para testes de API)

## 📁 Estrutura do Projeto

- `controller` → Endpoints REST
- `service` → Regras de negócio
- `repository` → Repositórios JPA
- `security` → Filtros e geração/validação de Token JWT
- `config` → Configurações globais
- `domain.model` → Entidades
- `dto` → Objetos de transferência de dados

## ✅ Funcionalidades

- `POST /login` → Gera token JWT válido para autenticação
- `GET /topicos` → Lista todos os tópicos
- `GET /topicos/{id}` → Detalha um tópico específico
- `POST /topicos` → Registra um novo tópico (autenticado)
- `PUT /topicos/{id}` → Atualiza um tópico (autenticado)
- `DELETE /topicos/{id}` → Exclui um tópico (autenticado)

## 🔒 Autenticação JWT

Após login:
- Copie o token retornado.
- Envie o token no header `Authorization` em requisições protegidas:
  Authorization: Bearer SEU_TOKEN_AQUI


## ⚡ Pré-requisitos

- **Java 21**
- **Maven**
- **MySQL** configurado
- Ferramenta de teste de API (ex: Insomnia ou Postman)

## ⚙️ Configuração do Banco de Dados

No arquivo `application.properties`:

spring.datasource.url=jdbc:mysql://localhost/challenge_forum_hub
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true

## 🏃 Como Executar Localmente
1. Clone o repositório:
git clone https://github.com/devfabiosantos/challenge-forum-hub.git
cd challenge-forum-hub
2. Configure o banco de dados:
* Crie o banco challenge_forum_hub no MySQL.

* Verifique suas credenciais no application.properties.

3. Rode as migrações Flyway:
O Flyway executará automaticamente na inicialização.

4. Compile e execute:
   mvn spring-boot:run
5. Teste a API:
Use Insomnia ou Postman para autenticar (/login) e consumir os endpoints.
## 💡 Observações
   A autenticação JWT garante que apenas usuários autenticados possam criar, atualizar ou excluir tópicos.

Endpoints de listagem são abertos.

O projeto segue boas práticas RESTful.

## 🤝 Contato
Desenvolvido por Fabio Antonio dos Santos 🚀