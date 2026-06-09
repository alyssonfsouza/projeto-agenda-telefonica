# 📒 Projeto Agenda Telefônica — Projeto Integrador II-A

**Desenvolvido por:** Alysson F. de Souza
**Disciplina:** Análise e Desenvolvimento de Sistemas
**Instituição:** PUC Goiás

---

Olá, professor JOSE RICARDO! Este é o repositório do meu projeto de Agenda Telefônica interativa via terminal, com CRUD completo. Abaixo está o passo a passo para configurar o ambiente e executar a aplicação localmente.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Banco de Dados:** MySQL
- **Driver:** MySQL Connector/J (JDBC)

---

## 🚀 Passo a Passo para Execução

### 1. Preparando o Banco de Dados

Para que o sistema funcione, é necessário criar o banco de dados e a tabela de contatos no servidor MySQL local.

Abra o SGBD de sua preferência (HeidiSQL, DBeaver, MySQL Workbench etc.) e execute o script abaixo:

```sql
-- Cria o banco de dados
CREATE DATABASE projeto_agenda;

-- Seleciona o banco para uso
USE projeto_agenda;

-- Cria a tabela de contatos
CREATE TABLE contatos (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    telefone  VARCHAR(20)  NOT NULL,
    email     VARCHAR(100) NOT NULL
);
```

---

### 2. Configurando as Credenciais

> ⚠️ **Atenção:** as credenciais de banco de dados variam de máquina para máquina. Ajuste antes de executar.

1. Abra a pasta `src` e acesse o arquivo **`ConexaoDB.java`**.
2. Localize a variável `PASSWORD` e substitua pelo valor correspondente à senha do seu usuário `root` do MySQL local.

---

### 3. Adicionando o Driver JDBC

O projeto depende do MySQL Connector/J para se comunicar com o banco de dados.

1. Baixe o arquivo `.jar` do [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/).
2. Na sua IDE (VS Code, Eclipse ou IntelliJ), adicione o `.jar` em **Referenced Libraries** (ou **Build Path**) do projeto.

---

### 4. Executando a Aplicação

Com o banco criado, a senha configurada e o driver adicionado:

1. Abra o arquivo principal: **`Agenda.java`**.
2. Execute a aplicação.
3. O menu interativo será exibido no terminal com as seguintes opções:

| Operação  | Descrição                        |
|-----------|----------------------------------|
| Adicionar | Cadastra um novo contato         |
| Listar    | Exibe todos os contatos salvos   |
| Buscar    | Localiza um contato pelo nome    |
| Atualizar | Edita os dados de um contato     |
| Remover   | Exclui um contato do banco       |

---

## 📁 Estrutura do Projeto

```
projeto-agenda/
├── src/
│   ├── Agenda.java        # Ponto de entrada — menu interativo
│   ├── ConexaoDB.java     # Configuração da conexão JDBC
│   ├── Contato.java       # Modelo de dados
│   └── AgendaTelefonica.java    # Operações CRUD no banco
└── README.md
```

---