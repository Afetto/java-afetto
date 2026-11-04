
package br.com.fiap.java_afetto.controller;

import br.com.fiap.java_afetto.repository.UsuarioRepository;
import br.com.fiap.java_afetto.service.DistanciaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class DistanciaPageController {

    private final UsuarioRepository usuarioRepository;
    private final DistanciaService distanciaService;

    public DistanciaPageController(
            UsuarioRepository usuarioRepository,
            DistanciaService distanciaService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.distanciaService = distanciaService;
    }

    @GetMapping("/distancia")
    public String pagina(Model model) {
        carregarUsuarios(model);
        return "distancia";
    }

    @PostMapping("/distancia")
    public String calcular(
            @RequestParam UUID idUsuario1,
            @RequestParam UUID idUsuario2,
            Model model
    ) {
        carregarUsuarios(model);

        model.addAttribute("idUsuario1", idUsuario1);
        model.addAttribute("idUsuario2", idUsuario2);

        if (idUsuario1.equals(idUsuario2)) {
            model.addAttribute("erro",
                    "Selecione dois usuários diferentes.");
            return "distancia";
        }

        try {
            double distancia = distanciaService.calcularDistancia(
                    idUsuario1, idUsuario2
            );

            model.addAttribute("distancia",
                    String.format("%.2f", distancia));

        } catch (IllegalArgumentException |
                 IllegalStateException |
                 jakarta.persistence.EntityNotFoundException e) {

            model.addAttribute("erro", e.getMessage());
        }

        return "distancia";
    }

    private void carregarUsuarios(Model model) {
        model.addAttribute(
                "usuarios",
                usuarioRepository.findAll()
                        .stream()
                        .map(u -> new UsuarioOpcao(
                                u.getId(), u.getNome()))
                        .toList()
        );
    }

    public record UsuarioOpcao(UUID id, String nome) {}
}
