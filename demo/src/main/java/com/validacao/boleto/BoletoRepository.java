package com.validacao.boleto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<Boleto, Long> {
    Boleto findByCodigo(String codigo);
}