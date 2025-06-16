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
 * Controlador REST para gerenciar solicitações de validação de boletos e consulta de histórico.
 */
@RestController
@RequestMapping("/validar-boleto")
public class ValidarBoletoController {

    @Autowired
    private ValidarBoletoService validarBoletoService;

    /**
     * Valida um boleto com base nos dados fornecidos na solicitação.
     * @param request Os dados do boleto a serem validados.
     * @return ResponseEntity contendo o resultado da validação, detalhes do boleto e histórico.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> validar(@Valid @RequestBody BoletoRequest request) {
        Map<String, Object> response = validarBoletoService.validarBoleto(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Consulta o histórico de validações por CPF/CNPJ.
     * @param cpfCnpj O CPF ou CNPJ a ser pesquisado.
     * @return ResponseEntity contendo o histórico ou mensagem de erro.
     */
    @GetMapping("/historico")
    public ResponseEntity<Map<String, Object>> buscarHistorico(@RequestParam String cpfCnpj) {
        Map<String, Object> response = validarBoletoService.buscarHistorico(cpfCnpj);
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