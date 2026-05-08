# 🚧 Ordem de Serviço API

API REST desenvolvida em Java com Spring Boot para gerenciamento de ordens de serviço.

O projeto está sendo construído com foco em boas práticas de arquitetura backend, utilizando Clean Architecture, separação de responsabilidades e princípios de desenvolvimento escalável.

Além das funcionalidades de negócio, o sistema também serve como laboratório de estudos para tecnologias modernas do ecossistema Java e arquitetura de software.

---

# 🚀 Tecnologias utilizadas

## Backend

- Java
- Spring Boot
- Maven

## Arquitetura e boas práticas

- Clean Architecture
- SOLID
- Domain Driven Design (DDD)
- DTO Pattern
- Exceptions Handling
- Testes unitários

## Banco de dados

- Spring Data JPA *(em implementação)*
- Hibernate *(em implementação)*

## Segurança

- Spring Security *(em implementação)*
- JWT Authentication *(planejado)*

## Mensageria

- Apache Kafka *(planejado)*

## Infraestrutura

- Docker *(planejado)*
- Docker Compose *(planejado)*

## Ferramentas

- Git
- GitHub
- Postman
- IntelliJ IDEA

---

# 🎯 Objetivos do projeto

Este projeto foi criado com o objetivo de aprofundar conhecimentos em:

- arquitetura backend moderna;
- APIs REST;
- modelagem de domínio;
- segurança de aplicações;
- persistência de dados;
- mensageria;
- testes automatizados;
- conteinerização;
- boas práticas de desenvolvimento.

---

# 🧠 Conceitos aplicados

Durante o desenvolvimento estão sendo aplicados conceitos importantes de engenharia de software:

- Clean Architecture
- Separação de camadas
- Inversão de dependência
- Encapsulamento
- Casos de uso
- Regras de negócio desacopladas
- Injeção de dependência
- Tratamento global de exceções
- Validações de domínio
- Testes unitários

---

# 📂 Estrutura do projeto

```bash
src
 ├── main
 │    ├── java
 │    │     └── br/com/lucascrippa/ordemservico
 │    │            ├── application
 │    │            ├── domain
 │    │            ├── infrastructure
 │    │            └── presentation
 │    └── resources
 └── test
```

---

# 🏛️ Organização das camadas

## 📁 Domain

Contém as regras de negócio e entidades da aplicação.

Exemplos:
- entidades;
- enums;
- validações de domínio;
- contratos/interfaces.

---

## 📁 Application

Responsável pelos casos de uso da aplicação.

Exemplos:
- serviços;
- DTOs;
- regras de aplicação;
- orquestração de fluxos.

---

## 📁 Infrastructure

Camada responsável pela comunicação externa.

Exemplos:
- persistência;
- segurança;
- mensageria;
- configurações;
- integrações.

---

## 📁 Presentation

Responsável pela exposição da API REST.

Exemplos:
- controllers;
- requests;
- responses;
- handlers de exceção.

---

# 📋 Funcionalidades atuais

- Cadastro de usuários
- Controle de permissões
- Ativação e desativação de usuários
- Estrutura de ordens de serviço
- Validações de domínio
- Tratamento de exceções
- Testes unitários

---

# 🔒 Funcionalidades planejadas

- Autenticação com JWT
- Spring Security
- Persistência com JPA
- Integração com Kafka
- Conteinerização com Docker
- Docker Compose
- Logs estruturados
- Observabilidade
- Testes de integração
- Documentação com Swagger/OpenAPI

---

# ▶️ Como executar o projeto

## Pré-requisitos

- Java 17+
- Maven
- IDE Java (IntelliJ recomendado)

---

## Clone o repositório

```bash
git clone https://github.com/lucascrippa7/Ordem-Servico-API.git
```

---

## Acesse a pasta do projeto

```bash
cd Ordem-Servico-API
```

---

## Execute a aplicação

```bash
mvn spring-boot:run
```

Ou execute a classe principal pela IDE.

---

# 🧪 Testes

Para executar os testes:

```bash
mvn test
```

---

# 📚 Objetivo do projeto

Este projeto representa a evolução prática no desenvolvimento backend Java, consolidando conhecimentos utilizados em aplicações modernas de mercado.

O foco principal está em construir uma aplicação escalável, organizada e alinhada com boas práticas de arquitetura de software.

---

# 👨‍💻 Autor

Desenvolvido por Lucas Crippa.

GitHub:

https://github.com/lucascrippa7

---
