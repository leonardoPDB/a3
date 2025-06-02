package com.validacao.boleto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo; // linha digitável

    private String descricao;
    private String agencia;
    private String recebedor;
    private String valor;
    private String vencimento;
    private String cnpj;

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