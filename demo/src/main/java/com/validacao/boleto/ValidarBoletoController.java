package com.validacao.boleto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciar solicitações de validação de boletos.
 */
@RestController
@RequestMapping("/validar-boleto")
public class ValidarBoletoController {

    @Autowired
    private ValidarBoletoService validarBoletoService;

    /**
     * Valida um boleto com base nos dados fornecidos na solicitação.
     * @param request Os dados do boleto a serem validados.
     * @return ResponseEntity contendo o resultado da validação ou mensagem de erro.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> validar(@Valid @RequestBody BoletoRequest request) {
        Map<String, Object> response = validarBoletoService.validarBoleto(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Trata erros de validação do corpo da solicitação.
     * @param ex A exceção de validação.
     * @return ResponseEntity com um mapa de erros de campo.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
                ));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}