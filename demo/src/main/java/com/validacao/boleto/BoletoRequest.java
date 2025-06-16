package com.validacao.boleto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Objeto de Transferência de Dados (DTO) para receber solicitações de validação de boleto via API REST.
 * Inclui anotações de validação para garantir a correção dos dados de entrada.
 */
@Data
public class BoletoRequest {

    /**
     * A linha digitável, deve ser não vazia e corresponder ao formato esperado (47 ou 48 dígitos).
     */
    @NotBlank(message = "Linha digitável é obrigatória")
    @Pattern(regexp = "\\d{47,48}", message = "Linha digitável deve conter 47 ou 48 dígitos")
    private String linhaDigitavel;

    /**
     * Código da agência, deve ser não vazio.
     */
    @NotBlank(message = "Agência é obrigatória")
    private String agencia;

    /**
     * Conta/código do recebedor, deve ser não vazio.
     */
    @NotBlank(message = "Recebedor é obrigatório")
    private String recebedor;

    /**
     * Valor do boleto, deve ser não vazio e estar no formato de moeda válido.
     */
    @NotBlank(message = "Valor é obrigatório")
    @Pattern(regexp = "R\\$ \\d+(\\.\\d{3})*(,\\d{2})", message = "Valor deve estar no formato R$ XXX,XX")
    private String valor;

    /**
     * Data de vencimento no formato AAAA-MM-DD, deve ser não vazia.
     */
    @NotBlank(message = "Vencimento é obrigatório")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Vencimento deve estar no formato AAAA-MM-DD")
    private String vencimento;

    /**
     * CNPJ do recebedor, deve ser não vazio e estar no formato válido.
     */
    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX")
    private String cnpj;
}