package br.edu.utfpr.td.tsi.ecommerce.produtos.apis;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/cep")
public class CepApi {

    @GetMapping("/{cep}")
    public Map<String, String> consultarCep(@PathVariable String cep) {
        System.out.println("\n[CEP API] Consultando CEP: " + cep);
        return Map.of(
            "logradouro", "Rua das Tecnologias", 
            "cidade", "Toledo"
        );
    }
}
