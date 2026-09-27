package br.edu.utfpr.td.tsi.ecommerce.produtos.apis;

import br.edu.utfpr.td.tsi.ecommerce.produtos.repository.EstoqueRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/fiscal")
public class FiscalApi {

    @PostMapping
    public Map<String, String> gerarNotaFiscal(@RequestBody Map<String, String> request) {
        String id = request.get("idProduto");
        System.out.println("\n[FISCAL API]  Gerando NF para o produto: " + id + " (Cliente: " + request.get("cliente") + ")");
        
        boolean baixou = EstoqueRepository.baixa(id);
        if (baixou) {
            System.out.println("[FISCAL API]  Baixa de estoque registrada. Restam " + EstoqueRepository.getQuantidade(id) + " unidades.");
        } else {
            System.out.println("[FISCAL API]  AVISO: ESTOQUE INSUFICIENTE para o produto " + id + "!");
        }
        
        return Map.of("nfe", "NF-999888");
    }
}
