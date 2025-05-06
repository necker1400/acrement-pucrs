package com.acmerent.acmerent_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bem-vindo u00e0 API do ACMERent! Acesse /clientes, /automoveis ou /locacoes para ver os dados.";
    }
}