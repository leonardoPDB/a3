package com.validacao.boleto;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Classe de serviço para gerenciar dados e lógica de validação de boletos.
 * Utiliza uma Lista em memória como armazenamento de dados.
 */
@Service
public class ValidarBoletoService {

    /**
     * Armazenamento em memória para boletos.
     */
    private final List<Boleto> boletos = new ArrayList<>();

    /**
     * Contador para gerar IDs únicos.
     */
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Salva um boleto no armazenamento em memória.
     * @param boleto O boleto a ser salvo.
     */
    public void save(Boleto boleto) {
        boleto.setId(idGenerator.getAndIncrement());
        boletos.add(boleto);
    }

    /**
     * Busca um boleto pela sua linha digitável (código).
     * @param codigo A linha digitável a ser pesquisada.
     * @return O Boleto correspondente ou null se não encontrado.
     */
    public Boleto findByCodigo(String codigo) {
        return boletos.stream()
                .filter(b -> b.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    /**
     * Valida um boleto comparando os dados da solicitação com os dados armazenados.
     * @param request A solicitação contendo os dados do boleto a validar.
     * @return Um mapa contendo o resultado da validação e detalhes opcionais do boleto.
     */
    public Map<String, Object> validarBoleto(BoletoRequest request) {
        Map<String, Object> resultado = new HashMap<>();
        Boleto encontrado = findByCodigo(request.getLinhaDigitavel());

        if (encontrado == null) {
            resultado.put("mensagem", "❌ Boleto não encontrado.");
            return resultado;
        }

        int divergencias = 0;
        if (!encontrado.getAgencia().equals(request.getAgencia())) divergencias++;
        if (!encontrado.getRecebedor().equals(request.getRecebedor())) divergencias++;
        if (!encontrado.getValor().equals(request.getValor())) divergencias++;
        if (!encontrado.getVencimento().equals(request.getVencimento())) divergencias++;
        if (!encontrado.getCnpj().equals(request.getCnpj())) divergencias++;

        if (divergencias > 0) {
            resultado.put("mensagem", "❌ " + divergencias + " dado(s) divergente(s) detectado(s).");
        } else {
            resultado.put("mensagem", "✅ Boleto válido!");
            resultado.put("boleto", encontrado);
        }

        return resultado;
    }
}