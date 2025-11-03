package com.camargo.spring_boot_gpt.dto;

public class ChatRequest {
    private String pergunta;
    
    public ChatRequest() {
    }
    
    public ChatRequest(String pergunta) {
        this.pergunta = pergunta;
    }
    
    public String getPergunta() {
        return pergunta;
    }
    
    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}

