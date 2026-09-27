package br.edu.utfpr.td.tsi.ecommerce.produtos.apis;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoApi {

    @PostMapping
    public Map<String, String> processarPagamento(@RequestBody Map<String, String> request) {
        System.out.println("\n[PAGAMENTO API] Processando cartão: " + request.get("cartao"));
        System.out.println("[PAGAMENTO API] Valor da transação: R$ " + request.get("valor"));
        
        return Map.of("status", "APROVADO", "transacao", "TX-12345");
    }
}
