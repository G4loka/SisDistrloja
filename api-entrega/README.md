# 🛒 Microsserviço de Produtos (`ecommerce.produtos`)

Projeto de referência para catálogo e gerenciamento de produtos desenvolvido no contexto do curso de Tecnologia em Sistemas para Internet (TSI - UTFPR).

---

## 🚀 Tecnologias

- **Java:** 17
- **Spring Boot:** 3.3.5
- **Spring Web**
- **Spring Boot DevTools**
- **Maven**

---

## ⚙️ Como Executar

### Pré-requisitos
- JDK 17 instalado e configurado no PATH
- Maven 3.8+ (ou utilize o plugin da sua IDE: Eclipse / VS Code / IntelliJ)

### Execução via Linha de Comando
```bash
# Na pasta do projeto:
mvn clean spring-boot:run
```

Ou execute diretamente a classe principal:
- `br.edu.utfpr.td.tsi.ecommerce.produtos.Main`

A aplicação iniciará na porta **`8081`** com o context-path **`/ecommerce.produtos`**.

---

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `http://localhost:8081/ecommerce.produtos/catalogo` | Retorna a lista de produtos disponíveis em formato JSON |

---

## 🧪 Testando com o Cliente Console

O projeto inclui a classe `CatalogoCliente`:
- Execute `br.edu.utfpr.td.tsi.ecommerce.produtos.CatalogoCliente` para fazer uma requisição HTTP `GET` diretamente no endpoint e visualizar o status e o JSON retornado no console.

---

## 📝 Documentação no Obsidian

Este projeto está conectado e documentado no Obsidian Vault:
- **Localização:** `esquizofrenia/Conversas Ant/2026/Ecommerce-Produtos/2026-09-23 - Revisao e Arquitetura do Microservico de Produtos.md`
- **Índice geral:** `esquizofrenia/Conversas Ant/INDICE.md`
