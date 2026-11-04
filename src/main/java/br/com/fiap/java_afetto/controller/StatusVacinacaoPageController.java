
package br.com.fiap.java_afetto.controller;

import br.com.fiap.java_afetto.dto.pet.PetLista;
import br.com.fiap.java_afetto.dto.pet.PetStatusVacinacaoResponse;
import br.com.fiap.java_afetto.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class StatusVacinacaoPageController {

    private final PetService petService;

    public StatusVacinacaoPageController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/status-vacinacao")
    public String statusVacinacao(
            @RequestParam(required = false) UUID idPet,
            Model model
    ) {

        var pets = petService.read(PageRequest.of(0, 100))
                .getContent();

        model.addAttribute("pets", pets);
        model.addAttribute("idPetSelecionado", idPet);

        if (idPet != null) {
            try {
                PetStatusVacinacaoResponse status =
                        petService.statusVacinacao(idPet);

                model.addAttribute("statusVacinal", status);

            } catch (EntityNotFoundException e) {
                model.addAttribute("erro", e.getMessage());
            }
        }

        return "status-vacinacao";
    }
}
