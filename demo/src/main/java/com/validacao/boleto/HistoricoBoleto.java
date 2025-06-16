package com.validacao.boleto;

import lombok.Data;

/**
 * Representa o histórico de validações de boletos associado a um CPF ou CNPJ.
 * Armazena a quantidade de boletos válidos e fraudulentos.
 */
@Data
public class HistoricoBoleto {

    /**
     * CPF ou CNPJ associado ao histórico.
     */
    private String cpfCnpj;

    /**
     * Quantidade de boletos válidos.
     */
    private int validos;

    /**
     * Quantidade de boletos fraudulentos.
     */
    private int fraudulentos;

    /**
     * Construtor para criar uma instância de HistoricoBoleto.
     * @param cpfCnpj O CPF ou CNPJ associado.
     */
    public HistoricoBoleto(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        this.validos = 0;
        this.fraudulentos = 0;
    }

    /**
     * Incrementa o contador de boletos válidos.
     */
    public void incrementarValidos() {
        this.validos++;
    }

    /**
     * Incrementa o contador de boletos fraudulentos.
     */
    public void incrementarFraudulentos() {
        this.fraudulentos++;
    }
}