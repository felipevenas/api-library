# 📦 API Library

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
</p>

<h1 align="center"> API de Gerenciamento de Livraria </h1>

## 📌 Descrição

API REST desenvolvida em Java com Spring Boot para gerenciamento de uma livraria.

O sistema oferece operações completas de CRUD e filtragem de itens por categoria e status.

## 🚀 Funcionalidades

✅ Adicionar, listar, atualizar e excluir autores e livros da lista
🔍 Busca de autores e livros por ID

🧪 Testes automatizados com JUnit

## 🛠 Tecnologias Utilizadas

| Tecnologia      |	Descrição |
|-----------------|-----------|
| **Java 21** ☕	| Linguagem principal do projeto |
| **Spring Boot** 🌱 | Framework para criação da API REST |
| **Spring Data JPA** 📊 |	Abstração para acesso e persistência de dados |
| **Spring Web** 🌍 |	Módulo para criação de endpoints HTTP |
| **Maven** 📦 |	Gerenciador de dependências e build |
| **Hibernate** 🛢️ | ORM utilizado pelo JPA |
| **PostgreSQL** 🐘 |	Banco de dados relacional |
| **Lombok** ✍️ |	Reduz a verbosidade do código Java |
| **JUnit & Mockito** ✅ |	Testes unitários e mocks |

## 📁 Estrutura do Projeto

O projeto segue a arquitetura em camadas, separando responsabilidades de forma clara:

| Camada         | Funcionalidade                                                      |
|----------------|---------------------------------------------------------------------|
| **Controller** | Recebe as requisições HTTP e delega para os serviços                |
| **Model**      | Define as entidades que representam as tabelas do banco de dados    |
| **Repository** | Acesso e manipulação dos dados no banco via Spring Data JPA         |
| **Service**    | Contém a lógica de negócio e orquestra chamadas para outras camadas |

---

## 🧪 Modos de execução

Você pode executar a API:
- **Localmente**, com PostgreSQL instalado e variáveis de ambiente configuradas
  
---

## 🚀 Como rodar localmente

### ✅ Pré-requisitos

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)
- Criar o banco de dados no PostgreSQL
- Configurar as variáveis de ambiente
---

### 🔐 Variáveis de Ambiente

Antes de executar a aplicação, configure um arquivo `.yml` ou defina as variáveis no ambiente do sistema:

```
DB_URL=jdbc:postgresql://localhost:5432/library
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```
💡 O uso de variáveis de ambiente melhora a segurança e facilita a configuração em diferentes ambientes (desenvolvimento, produção, etc).


### ▶️ Executar a aplicação

```bash
git clone https://github.com/felipevenas/api-library
cd api-library
mvn clean install
mvn spring-boot:run
```

---

## 📌 Próximos passos

- 🔨 Desenvolver o aplicativo Android com integração à API
- 🧠 Melhorar o modelo de categorização da API Python
- 🔐 Implementar autenticação (JWT)
---

## 👨‍💻 Autor

**Felipe Venas**

Desenvolvedor Java | Apaixonado por resolver problemas com código limpo e boas práticas

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Perfil-blue?logo=linkedin)](https://www.linkedin.com/in/felipevenas/) | [![GitHub](https://img.shields.io/badge/GitHub-Perfil-black?logo=github)](https://github.com/felipevenas)
