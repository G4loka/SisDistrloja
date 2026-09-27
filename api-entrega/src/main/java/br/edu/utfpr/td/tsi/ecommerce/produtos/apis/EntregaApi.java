package br.edu.utfpr.td.tsi.ecommerce.produtos.apis;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/entrega")
public class EntregaApi {

    @PostMapping
    public Map<String, String> agendarEntrega(@RequestBody Map<String, String> request) {
        System.out.println("\n[ENTREGA API]  Agendando entrega para o CEP: " + request.get("cep") + " referente à NF: " + request.get("nfe"));
        
        return Map.of("rastreio", "BR123456UTFPR");
    }
}
