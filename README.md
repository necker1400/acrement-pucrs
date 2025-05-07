# ACMERENT - Sistema de Gerenciamento de Aluguel de Carros

## Visão Geral

**ACMERENT** é um sistema abrangente de gerenciamento de aluguel de carros construído com Spring Boot. Esta aplicação fornece uma API RESTful para gerenciar veículos, clientes e operações de aluguel. Foi projetada para lidar com o ciclo de vida completo de aluguéis de carros, desde o gerenciamento de inventário até o processamento de aluguéis.

---

## 🛠️ Stack Tecnológica

- **Framework Backend**: Spring Boot 3.2.3  
- **Versão Java**: Java 17  
- **Banco de Dados**: H2 (em memória com JPA)  
- **Tipo de API**: RESTful  
- **Ferramenta de Build**: Maven  
- **Estrutura do Projeto**: Padrão Maven  

---

## 📦 Dependências

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-devtools`
- `h2` (banco de dados em memória)
- `spring-boot-starter-test`

---

## ✨ Funcionalidades

### Gerenciamento de Veículos

- Rastreamento de inventário
- Atualização de status (DISPONIVEL, INDISPONIVEL, RETIRADO)
- Validação de veículos

### Gerenciamento de Clientes

- Registro e consulta de clientes

### Processamento de Aluguéis

- Criação de aluguéis
- Cálculo automático de preço com desconto
- Finalização e rastreamento de status

### Inicialização de Dados

- Dados de exemplo gerados na primeira execução

---
