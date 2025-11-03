package com.camargo.spring_boot_gpt.controller;

import com.camargo.spring_boot_gpt.dto.ChatRequest;
import com.camargo.spring_boot_gpt.dto.ChatResponse;
import com.camargo.spring_boot_gpt.ServiceChatGPT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {
    
    private final ServiceChatGPT serviceChatGPT;
    
    public ChatController(ServiceChatGPT serviceChatGPT) {
        this.serviceChatGPT = serviceChatGPT;
    }
    
    @PostMapping
    public Mono<ResponseEntity<ChatResponse>> enviarPergunta(@RequestBody ChatRequest request) {
        if (request.getPergunta() == null || request.getPergunta().trim().isEmpty()) {
            return Mono.just(ResponseEntity.badRequest()
                    .body(new ChatResponse("Por favor, forneça uma pergunta válida.")));
        }
        
        return serviceChatGPT.enviarPergunta(request.getPergunta())
                .map(resposta -> ResponseEntity.ok(new ChatResponse(resposta)))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ChatResponse("Erro: " + e.getMessage()))));
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("API ChatGPT está funcionando!");
    }
}

