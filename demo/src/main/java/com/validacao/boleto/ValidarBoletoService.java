package com.validacao.boleto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidarBoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    public Map<String, Object> validarBoleto(BoletoRequest request) {
        Boleto encontrado = boletoRepository.findByCodigo(request.getLinhaDigitavel());

        Map<String, Object> resultado = new HashMap<>();

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