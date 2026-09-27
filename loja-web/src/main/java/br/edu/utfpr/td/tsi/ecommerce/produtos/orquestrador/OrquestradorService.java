package br.edu.utfpr.td.tsi.ecommerce.produtos.orquestrador;

import br.edu.utfpr.td.tsi.ecommerce.produtos.model.CheckoutRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class OrquestradorService {

    private static final Logger logger = LoggerFactory.getLogger(OrquestradorService.class);
    private final RestTemplate rest = new RestTemplate();
    
    private final String CEP_URL       = "http://localhost:8082";
    private final String PAGAMENTO_URL = "http://localhost:8083";
    private final String EMAIL_URL     = "http://localhost:8084";
    private final String FISCAL_URL    = "http://localhost:8085";
    private final String ENTREGA_URL   = "http://localhost:8086";

    public String processarCompra(CheckoutRequest request) {
        logger.info("Iniciando fluxo de orquestração de compra para o produto: {}", request.idProduto);

        logger.info("Executando [GET] /api/cep/{} - Consultando logradouro", request.cep);
        Map<?, ?> endereco = rest.getForObject(CEP_URL + "/api/cep/" + request.cep, Map.class);
        logger.debug("Resposta CEP API: {}", endereco);

        logger.info("Executando [POST] /api/email - Disparando e-mail de confirmação do pedido");
        enviarEmail(request.emailCliente, "Confirmação de Compra", "Recebemos seu pedido do produto " + request.idProduto);

        logger.info("Executando [POST] /api/pagamento - Processando transação de crédito");
        Map<String, String> pagRequest = Map.of("cartao", request.numeroCartao, "valor", "3500.00");
        Map<?, ?> pagResponse = rest.postForObject(PAGAMENTO_URL + "/api/pagamento", pagRequest, Map.class);
        String statusPagamento = (String) pagResponse.get("status");

        logger.info("Executando [POST] /api/email - Disparando e-mail de resultado da transação");
        enviarEmail(request.emailCliente, "Resultado do Pagamento", "O status do seu pagamento é: " + statusPagamento);

        logger.info("Executando [POST] /api/fiscal - Solicitando emissão de NF e baixa de estoque");
        Map<String, String> fiscalRequest = Map.of("idProduto", request.idProduto, "cliente", request.nomeCliente);
        Map<?, ?> fiscalResponse = rest.postForObject(FISCAL_URL + "/api/fiscal", fiscalRequest, Map.class);
        String nfe = (String) fiscalResponse.get("nfe");

        logger.info("Executando [POST] /api/email - Disparando e-mail com a Nota Fiscal ({})", nfe);
        enviarEmail(request.emailCliente, "Sua Nota Fiscal", "Sua NF foi gerada com sucesso: " + nfe);

        logger.info("Executando [POST] /api/entrega - Solicitando despacho logístico");
        Map<String, String> entregaRequest = Map.of("cep", request.cep, "nfe", nfe);
        Map<?, ?> entregaResponse = rest.postForObject(ENTREGA_URL + "/api/entrega", entregaRequest, Map.class);
        String rastreio = (String) entregaResponse.get("rastreio");

        logger.info("Executando [POST] /api/email - Disparando e-mail com código de rastreamento ({})", rastreio);
        enviarEmail(request.emailCliente, "Pedido Enviado!", "Acompanhe seu pedido pelo rastreio: " + rastreio);

        logger.info("Orquestração de compra finalizada com sucesso. Rastreio gerado: {}", rastreio);
        return "Compra finalizada com sucesso! Rastreio: " + rastreio + " | NF: " + nfe;
    }

    private void enviarEmail(String destinatario, String assunto, String corpo) {
        Map<String, String> emailReq = Map.of("destinatario", destinatario, "assunto", assunto, "corpo", corpo);
        rest.postForObject(EMAIL_URL + "/api/email", emailReq, Map.class);
    }
}
