package br.edu.utfpr.td.tsi.ecommerce.produtos.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EstoqueRepository {

   
    private static final Map<String, Integer> estoque = new ConcurrentHashMap<>();

    static {
        estoque.put("p001", 15); // Notebook
        estoque.put("p002", 20); // Teclado
        estoque.put("p003", 30); // Mouse
        estoque.put("p004", 10); // Monitor
    }

   
    public static boolean baixa(String idProduto) {
        if (!estoque.containsKey(idProduto)) {
            return false;
        }
        
        int qtdAtual = estoque.get(idProduto);
        if (qtdAtual > 0) {
            estoque.put(idProduto, qtdAtual - 1);
            return true;
        }
        
        return false;
    }

    public static int getQuantidade(String idProduto) {
        return estoque.getOrDefault(idProduto, 0);
    }
}
