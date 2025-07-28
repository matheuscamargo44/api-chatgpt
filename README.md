# API ChatGPT - Spring Boot

Este projeto é uma API desenvolvida em Java utilizando o framework Spring Boot, visando integrar e consumir a API do ChatGPT.

## Funcionalidades
- Integração com a API do ChatGPT
- Estrutura pronta para expandir funcionalidades de chat
- Configuração simples via `application.properties`

## Requisitos
- Java 17+
- Maven 3.8+

## Como executar
1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd api-chatgpt
   ```
3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

## Estrutura do Projeto
```
api-chatgpt/
├── src/
│   ├── main/
│   │   ├── java/com/camargo/spring_boot_gpt/
│   │   │   ├── ServiceChatGPT.java
│   │   │   └── SpringBootGptApplication.java
│   └── resources/
│       ├── application.properties
│       ├── static/
│       └── templates/
└── pom.xml
```

## Configuração
Edite o arquivo `src/main/resources/application.properties` para adicionar suas configurações, como chave da API do ChatGPT.

## Testes
Para rodar os testes:
```bash
./mvnw test
```

## Autor
- Matheus Cavalheiro de Camargo

## Licença
Este projeto está sob a licença MIT.
