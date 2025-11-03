# API ChatGPT - Spring Boot

API REST desenvolvida em Spring Boot que integra e consome a API do ChatGPT, permitindo enviar perguntas e receber respostas através de endpoints HTTP.

## Tecnologias Utilizadas

- **Spring Boot 3.5.4**: Framework Java para desenvolvimento de aplicações
- **Spring WebFlux**: Para chamadas HTTP assíncronas e reativas
- **WebClient**: Cliente HTTP reativo para integração com APIs externas
- **Maven**: Gerenciador de dependências e build
- **Java 17**: Linguagem de programação

## Estrutura do Projeto

```
api-chatgpt/
├── src/
│   ├── main/
│   │   ├── java/com/camargo/spring_boot_gpt/
│   │   │   ├── controller/     # Controllers REST
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── config/         # Configurações (WebClient)
│   │   │   ├── ServiceChatGPT.java
│   │   │   └── SpringBootGptApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Instalação

1. **Clonar o repositório:**

```bash
git clone https://github.com/seu-usuario/api-chatgpt.git
cd api-chatgpt
```

2. **Configurar a chave da API OpenAI:**

Edite o arquivo `src/main/resources/application.properties` e configure a chave da API:

```properties
openai.api.key=sua_chave_api_aqui
```

Ou configure através de variável de ambiente:

```bash
export OPENAI_API_KEY=sua_chave_api_aqui
```

3. **Executar a aplicação:**

```bash
mvn spring-boot:run
```

Ou execute através da IDE de sua preferência.

A aplicação estará disponível em `http://localhost:8080`

## Modelos de Dados

### ChatRequest

DTO para requisições de chat:

- **pergunta** (String): A pergunta a ser enviada ao ChatGPT

### ChatResponse

DTO para respostas da API:

- **resposta** (String): A resposta retornada pelo ChatGPT

## Endpoints da API

### POST `/api/chat`

Envia uma pergunta ao ChatGPT e retorna a resposta.

**Request Body:**
```json
{
  "pergunta": "Qual é a capital do Brasil?"
}
```

**Response 200 OK:**
```json
{
  "resposta": "A capital do Brasil é Brasília."
}
```

**Response 400 Bad Request:**
```json
{
  "resposta": "Por favor, forneça uma pergunta válida."
}
```

**Response 500 Internal Server Error:**
```json
{
  "resposta": "Erro: [mensagem de erro]"
}
```

### GET `/api/chat/health`

Endpoint de verificação de saúde da API.

**Response 200 OK:**
```
API ChatGPT está funcionando!
```

## Configuração

### application.properties

O arquivo `application.properties` contém as seguintes configurações:

```properties
# Configuração do servidor
server.port=8080

# Configuração da API OpenAI
openai.api.key=${OPENAI_API_KEY:}
openai.api.url=https://api.openai.com/v1/chat/completions
openai.api.model=gpt-3.5-turbo
```

**Configurações disponíveis:**

- `server.port`: Porta em que a aplicação será executada (padrão: 8080)
- `openai.api.key`: Chave da API do OpenAI (pode ser configurada via variável de ambiente `OPENAI_API_KEY`)
- `openai.api.url`: URL da API do OpenAI (padrão: https://api.openai.com/v1/chat/completions)
- `openai.api.model`: Modelo do ChatGPT a ser utilizado (padrão: gpt-3.5-turbo)

## Desenvolvimento

O projeto utiliza Spring WebFlux com WebClient para realizar chamadas HTTP assíncronas e reativas à API do ChatGPT. O `ServiceChatGPT` encapsula a lógica de comunicação com a API, enquanto o `ChatController` expõe os endpoints REST.

### Estrutura de Camadas

- **Controller**: Gerencia requisições HTTP e respostas
- **Service**: Contém a lógica de negócio e integração com APIs externas
- **DTO**: Objetos de transferência de dados para request/response
- **Config**: Configurações da aplicação (WebClient, etc.)

### Exemplo de Uso

```bash
# Enviar uma pergunta
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"pergunta": "Explique o que é Spring Boot"}'
```

## Funcionalidades Principais

- Integração com API do ChatGPT via WebClient reativo
- Endpoint REST para envio de perguntas
- Tratamento de erros e validações
- Configuração flexível via `application.properties` ou variáveis de ambiente
- Suporte a diferentes modelos do ChatGPT
- Endpoint de health check

## Testes

Para rodar os testes:

```bash
mvn test
```

## Observações

- A chave da API do OpenAI é obrigatória para o funcionamento da aplicação
- O modelo padrão é `gpt-3.5-turbo`, mas pode ser alterado no `application.properties`
- A aplicação utiliza programação reativa com Mono do Reactor para melhor performance
- É recomendado configurar a chave da API através de variável de ambiente em produção


