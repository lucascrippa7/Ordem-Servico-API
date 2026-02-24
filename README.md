# 📌 Ordem de Serviço API

API REST para gerenciamento de Ordens de Serviço, desenvolvida com Spring Boot e estruturada seguindo princípios de Clean Architecture.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven

---

## 🏗 Arquitetura

O projeto foi estruturado utilizando princípios de Clean Architecture, separando responsabilidades em camadas bem definidas:

- **Domain** → Entidades e regras de negócio
- **Application** → Casos de uso
- **Infrastructure** → Persistência e integrações externas
- **Presentation** → Controllers e exposição da API

Essa abordagem garante:
- Baixo acoplamento
- Alta coesão
- Facilidade de testes
- Evolução sustentável do sistema

---

## 🔐 Segurança

A API utiliza autenticação baseada em JWT (JSON Web Token), garantindo controle de acesso aos endpoints protegidos.

---

## 📦 Funcionalidades

- Cadastro de Ordem de Serviço
- Listagem de Ordens
- Busca por filtros
- Atualização de status
- Exclusão de Ordem
- Autenticação de usuários

---

## 🗄 Banco de Dados

O projeto utiliza PostgreSQL como banco de dados relacional.

---

## ▶️ Como Executar

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/seu-usuario/ordem-servico-api.git
