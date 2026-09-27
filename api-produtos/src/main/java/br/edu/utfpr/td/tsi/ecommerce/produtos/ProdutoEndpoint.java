package br.edu.utfpr.td.tsi.ecommerce.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProdutoEndpoint {

	@GetMapping(value = "/catalogo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> carregarCatalogo() {
		List<Produto> produtos = criarProdutos();
		return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}

	private List<Produto> criarProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();

		produtos.add(new Produto().id("p001").nome("Notebook Dell").preco(new BigDecimal("3500.00")).quantidadeEmEstoque(br.edu.utfpr.td.tsi.ecommerce.produtos.repository.EstoqueRepository.getQuantidade("p001")));
		produtos.add(new Produto().id("p002").nome("Teclado Mecânico RGB").preco(new BigDecimal("250.00")).quantidadeEmEstoque(br.edu.utfpr.td.tsi.ecommerce.produtos.repository.EstoqueRepository.getQuantidade("p002")));
		produtos.add(new Produto().id("p003").nome("Mouse Gamer 12000 DPI").preco(new BigDecimal("120.00")).quantidadeEmEstoque(br.edu.utfpr.td.tsi.ecommerce.produtos.repository.EstoqueRepository.getQuantidade("p003")));
		produtos.add(new Produto().id("p004").nome("Monitor Ultrawide 29\"").preco(new BigDecimal("1200.00")).quantidadeEmEstoque(br.edu.utfpr.td.tsi.ecommerce.produtos.repository.EstoqueRepository.getQuantidade("p004")));

		return produtos;
	}

}
