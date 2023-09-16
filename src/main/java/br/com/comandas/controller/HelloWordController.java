package br.com.comandas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWordController {

    @GetMapping
    public String olaMundo() {
        return "Olá mundo Spring Boot";
    }
}
