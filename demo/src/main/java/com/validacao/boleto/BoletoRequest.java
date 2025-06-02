package com.validacao.boleto;

import lombok.Data;

@Data
public class BoletoRequest {
    private String linhaDigitavel;
    private String agencia;
    private String recebedor;
    private String valor;
    private String vencimento;
    private String cnpj;
}