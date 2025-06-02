package com.validacao.boleto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/validar-boleto")
public class ValidarBoletoController {

    @Autowired
    private ValidarBoletoService validarBoletoService;

    @PostMapping
    public Map<String, Object> validar(@RequestBody BoletoRequest request) {
        return validarBoletoService.validarBoleto(request);
    }
}