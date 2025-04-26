# 📚 Carômetro

![Java](https://img.shields.io/badge/Java-21-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-brightgreen?logo=springboot)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-template%20engine-orange?logo=thymeleaf)
![Status](https://img.shields.io/badge/status-Em%20desenvolvimento-yellow)

---

## 🚀 Tecnologias Utilizadas

- ✅ Java 21  
- ✅ Spring Boot 3  
- ✅ Spring Web
- ✅ Spring Security    
- ✅ Spring Data JPA  
- ✅ Thymeleaf  
- ✅ Lombok  
- ✅ Banco de Dados PostgreSQL

---

## 🧠 Sobre o Projeto

Este sistema está em fase de desenvolvimento e tem como objetivo ser um **Carômetro Digital**.

A plataforma permite que **ex-alunos** realizem o cadastro de postagens com suas experiências acadêmicas e profissionais. Cada ex-aluno pode **atualizar apenas suas próprias postagens**, mantendo a segurança e integridade das informações.

O sistema conta com três perfis de usuário:

- 🚫👤 **Usuários Sem Cadastro**: podem apenas visualizar as postagens.
- 👤 **Ex-Alunos**: podem cadastrar e editar suas próprias postagens.
- 🧑‍🏫 **Coordenadores**: têm permissão para **habilitar ou desabilitar postagens**, decidindo quais podem ser exibidas publicamente.
- 👨‍💼 **Administradores**: são responsáveis por **validar ou invalidar postagens**, garantindo que apenas conteúdos apropriados e verificados sejam publicados.

A **página inicial (home)** exibe **apenas as postagens que foram habilitadas e validadas**, oferecendo uma vitrine confiável e organizada para visualização pública.

---

## ⚙️ Como Rodar o Projeto

```bash
# Clone o repositório
git clone https://github.com/leolimaferreira/carometro.git

# Acesse a pasta do projeto
cd carometro

# Configure o application.yml com seu banco de dados

# Rode a aplicação
./mvnw spring-boot:run
```

## 🐳 Comandos Docker
```docker
# Criar Network
docker network create carometro-network

# Rodar container Postgre na versão 16.3
docker run --name carometro-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=carometro -p 5432:5432 -d --network carometro-network postgres:16.3

# Rodar Pgadmin 4
docker run --name pgadmin4-carometro -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=admin -p 15432:80 -d --network carometro-network dpage/pgadmin4:8.9
```

## 🛢️ Comandos SQL
```sql
# Criar Tabela Usuário
CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    role VARCHAR(11) NOT NULL
);

# Criar Tabela Postagem
CREATE TABLE postagem (
    id UUID PRIMARY KEY,

    curso VARCHAR(100),
    ano_conclusao VARCHAR(10),
    empresa VARCHAR(100),
    funcao VARCHAR(100),
    tempo_trabalho VARCHAR(50),

    foto_url TEXT,
    linkedin TEXT,
    github TEXT,

    comentario_fatec TEXT,
    comentario_livre TEXT,

    habilitado BOOLEAN DEFAULT FALSE,
    validado BOOLEAN DEFAULT FALSE,

    usuario_id UUID UNIQUE,
    coordenador_id UUID,
    admin_id UUID,

    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_coordenador FOREIGN KEY (coordenador_id) REFERENCES usuario(id),
    CONSTRAINT fk_admin FOREIGN KEY (admin_id) REFERENCES usuario(id)
);
```

---

## 🖼️ Telas do Sistema

### 🤖 Índice
![image](https://github.com/user-attachments/assets/16a00cb1-0d93-487e-bdfa-a4ea5ad4e94c)

### 👤 Login
![image](https://github.com/user-attachments/assets/0e4210f1-334b-40d5-97a5-cf1035f92342)

### 📝 Cadastro
![image](https://github.com/user-attachments/assets/a7bf685c-01e8-4301-ad2c-f156e7ae19e7)

### 🏠 Home

- Sem Cadastro
![image](https://github.com/user-attachments/assets/ebec57d5-6e46-4fc6-9815-aee03e7c0053)

- Ex-Aluno
![image](https://github.com/user-attachments/assets/012c893c-3c25-4f33-b8ef-8add96c7f8b7)

- Coordenador
![image](https://github.com/user-attachments/assets/ed152507-6d87-4f22-9693-bc3bf9dd3d7c)

- Administrador
![image](https://github.com/user-attachments/assets/0f1863b0-10b8-4d60-85c5-f1c164419208)

### 📨 Cadastro de Nova Postagem
![Carômetro-Cadastrar-Postagem-Google-Chrome-2025-04-20-13-25-24](https://github.com/user-attachments/assets/2614febe-4f75-4d39-a5fa-44a689bd22b7)

### 📤 Habilitar Postagens
![Carômetro-Home-Google-Chrome-2025-04-20-13-32-28](https://github.com/user-attachments/assets/89aa757b-a828-4d45-ab26-79829a140883)

### ✅ Validar Postagens
![Carômetro-Home-Google-Chrome-2025-04-20-13-36-52](https://github.com/user-attachments/assets/216bf600-94cc-485f-8d5f-78e32d3cda15)

### 🧾 Ver Postagem
![Carômetro-Home-Google-Chrome-2025-04-20-13-44-10](https://github.com/user-attachments/assets/d3a61e27-0fd1-4b26-8569-e243683f0ff3)

### 🔄 Atualizar Postagem
![Carômetro-Home-Google-Chrome-2025-04-20-19-33-07](https://github.com/user-attachments/assets/a9df091a-210e-4e98-8b44-bb62fb407537)

---

## 📡 Endpoints da API

### 🔐 Autenticação
| Método | Rota      | Descrição                        |
|--------|-----------|----------------------------------|
| GET    | `/login`  | Exibe o formulário de login      |
| POST   | `/login`  | Processa o login                 |

---

### 👤 Usuário
| Método | Rota        | Descrição                              |
|--------|-------------|----------------------------------------|
| GET    | `/cadastro` | Exibe o formulário de cadastro         |
| POST   | `/cadastrar`| Cadastra um novo usuário               |

---

### 📝 Postagem
| Método | Rota                     | Descrição                                                       |
|--------|--------------------------|-----------------------------------------------------------------|
| GET    | `/postagem/cadastro`     | Exibe o formulário de cadastro de postagem                      |
| POST   | `/postagem/cadastrar`    | Cadastra uma nova postagem (ex-aluno)                           |
| GET    | `/postagem/atualizacao/{id}` | Exibe o formulário de atualização da postagem                |
| POST   | `/postagem/atualizar/{id}`   | Atualiza uma postagem existente (somente do próprio usuário)  |
| GET    | `/postagem/exibir/{id}`      | Exibe os detalhes de uma postagem                             |

---

### 🏠 Página Inicial
| Método | Rota      | Descrição                                        |
|--------|-----------|--------------------------------------------------|
| GET    | `/`       | Redireciona para `/index`                        |
| GET    | `/home`   | Exibe postagens habilitadas **e** validadas      |
| POST   | `/home`   | Faz logout da sessão guest e redireciona à home  |

---

### 🧑‍🏫 Habilitação de Postagens (Coordenadores)
| Método | Rota            | Descrição                                       |
|--------|------------------|-------------------------------------------------|
| GET    | `/habilitar`     | Lista todas as postagens para habilitação      |
| POST   | `/habilitar/{id}`| Habilita ou desabilita uma postagem específica |

---

### 👨‍💼 Validação de Postagens (Administradores)
| Método | Rota            | Descrição                                       |
|--------|------------------|-------------------------------------------------|
| GET    | `/validar`       | Lista todas as postagens para validação        |
| POST   | `/validar/{id}`  | Valida ou invalida uma postagem específica     |

