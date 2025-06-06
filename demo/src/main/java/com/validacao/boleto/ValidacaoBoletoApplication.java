package com.validacao.boleto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;


/**
 * Classe principal da aplicação de validação de boletos com Spring Boot.
 * Configura a aplicação web e inicializa o armazenamento de dados em memória.
 */
@SpringBootApplication
public class ValidacaoBoletoApplication implements WebMvcConfigurer {

    /**
     * Ponto de entrada da aplicação Spring Boot.
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(ValidacaoBoletoApplication.class, args);
    }

    /**
     * Configura a URL raiz ("/") para renderizar a view index.html.
     * @param registry Registro de controladores de visualização.
     */
    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    /**
     * Inicializa o armazenamento em memória com dados de exemplo de boletos.
     * @param service O serviço que gerencia o armazenamento em memória.
     * @return CommandLineRunner para executar a lógica de inicialização.
     */
    @Bean
    public CommandLineRunner demo(ValidarBoletoService service) {
        return (args) -> {
            service.save(new Boleto(
                "23793381286000000012006002021908375730000002000",
                "Bradesco",
                "1234",
                "000123456789",
                "R$ 200,00",
                "2025-06-01",
                "12.345.678/0001-00"
            ));
            service.save(new Boleto(
                "00190500954014481606906809350314337370000000100",
                "Banco do Brasil",
                "5678",
                "000987654321",
                "R$ 100,00",
                "2025-06-15",
                "98.765.432/0001-99"
            ));
        };
    }
}