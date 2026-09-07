package br.com.fiap.java_afetto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "PAGE-CONTROLLER")
public class PageController {

    @GetMapping("/login")
    @Operation(summary = "Acessa a page de login")
    public String login() {
        return "login";
    }
}