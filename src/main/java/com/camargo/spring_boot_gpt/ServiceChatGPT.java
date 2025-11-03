package com.camargo.spring_boot_gpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceChatGPT {
    
    private final WebClient webClient;
    
    @Value("${openai.api.key:}")
    private String apiKey;
    
    @Value("${openai.api.url:https://api.openai.com/v1/chat/completions}")
    private String apiUrl;
    
    @Value("${openai.api.model:gpt-3.5-turbo}")
    private String model;
    
    public ServiceChatGPT(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    
    public Mono<String> enviarPergunta(String pergunta) {
        if (apiKey == null || apiKey.isEmpty()) {
            return Mono.error(new IllegalStateException("Chave da API do OpenAI não configurada. Configure 'openai.api.key' no application.properties"));
        }
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("messages", List.of(
            Map.of("role", "user", "content", pergunta)
        ));
        
        return webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    Map<String, Object> firstChoice = choices.get(0);
                    @SuppressWarnings("unchecked")
                    Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
                    return (String) message.get("content");
                })
                .onErrorMap(Exception.class, e -> new RuntimeException("Erro ao comunicar com ChatGPT: " + e.getMessage(), e));
    }
}
