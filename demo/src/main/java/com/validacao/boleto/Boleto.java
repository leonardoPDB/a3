package com.validacao.boleto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa uma entidade Boleto (título de pagamento brasileiro).
 * Esta classe modela a estrutura de dados de um boleto, incluindo seus atributos.
 */
@Data
@NoArgsConstructor
public class Boleto {

    /**
     * Identificador único do boleto.
     */
    private Long id;

    /**
     * A linha digitável (código de barras), deve ser única e não nula.
     */
    private String codigo;

    /**
     * Descrição do boleto (por exemplo, banco emissor).
     */
    private String descricao;

    /**
     * Código da agência associada ao boleto.
     */
    private String agencia;

    /**
     * Conta/código do recebedor.
     */
    private String recebedor;

    /**
     * Valor do boleto (por exemplo, "R$ 200,00").
     */
    private String valor;

    /**
     * Data de vencimento do boleto no formato AAAA-MM-DD.
     */
    private String vencimento;

    /**
     * CNPJ (identificador fiscal da empresa) do recebedor.
     */
    private String cnpj;

    /**
     * Construtor para criar uma instância de Boleto com os atributos fornecidos.
     * @param codigo A linha digitável.
     * @param descricao Descrição do boleto.
     * @param agencia Código da agência.
     * @param recebedor Conta/código do recebedor.
     * @param valor Valor do boleto.
     * @param vencimento Data de vencimento.
     * @param cnpj CNPJ do recebedor.
     */
    public Boleto(String codigo, String descricao, String agencia, String recebedor, String valor, String vencimento, String cnpj) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.agencia = agencia;
        this.recebedor = recebedor;
        this.valor = valor;
        this.vencimento = vencimento;
        this.cnpj = cnpj;
    }
}