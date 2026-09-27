package br.edu.utfpr.td.tsi.ecommerce.produtos.orquestrador;

import br.edu.utfpr.td.tsi.ecommerce.produtos.model.CheckoutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class LojaController {

    private final OrquestradorService orquestradorService;

    public LojaController(OrquestradorService orquestradorService) {
        this.orquestradorService = orquestradorService;
    }

    @PostMapping
    public ResponseEntity<String> finalizarCompra(@RequestBody CheckoutRequest request) {
        
        String resultado = orquestradorService.processarCompra(request);
        
        return ResponseEntity.ok(resultado);
    }
}
