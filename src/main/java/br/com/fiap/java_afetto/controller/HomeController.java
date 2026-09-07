package br.com.fiap.java_afetto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Front-end")
public class HomeController {

    @GetMapping("/")
    @Operation(summary = "Acessa o index")
    public String home() {
        return "index";
    }
}