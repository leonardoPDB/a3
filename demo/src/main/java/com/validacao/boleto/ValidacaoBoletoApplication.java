package com.validacao.boleto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@SpringBootApplication
public class ValidacaoBoletoApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(ValidacaoBoletoApplication.class, args);
    }
    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public CommandLineRunner demo(BoletoRepository repository) {
        return (args) -> {
            repository.save(new Boleto("23793381286000000012006002021908375730000002000", "Bradesco",
                    "1234", "000123456789", "R$ 200,00", "2025-06-01", "12.345.678/0001-00"));
            repository.save(new Boleto("00190500954014481606906809350314337370000000100", "Banco do Brasil",
                    "5678", "000987654321", "R$ 100,00", "2025-06-15", "98.765.432/0001-99"));
        };
    }
}