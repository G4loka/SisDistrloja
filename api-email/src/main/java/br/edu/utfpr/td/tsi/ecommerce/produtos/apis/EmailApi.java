package br.edu.utfpr.td.tsi.ecommerce.produtos.apis;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailApi {

    @PostMapping
    public Map<String, String> enviarEmail(@RequestBody Map<String, String> request) {
        System.out.println("\n[EMAIL API]  Enviando e-mail para: " + request.get("destinatario"));
        System.out.println("[EMAIL API]  Assunto: " + request.get("assunto"));
        System.out.println("[EMAIL API]  Corpo: " + request.get("corpo"));
        
        return Map.of("status", "Enviado com sucesso");
    }
}
