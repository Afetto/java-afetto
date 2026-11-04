
package br.com.fiap.java_afetto.controller;

import br.com.fiap.java_afetto.repository.PetRepository;
import br.com.fiap.java_afetto.repository.VacinaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class CarteirinhaPageController {

    private final PetRepository petRepository;
    private final VacinaRepository vacinaRepository;

    public CarteirinhaPageController(
            PetRepository petRepository,
            VacinaRepository vacinaRepository
    ) {
        this.petRepository = petRepository;
        this.vacinaRepository = vacinaRepository;
    }

    @GetMapping("/carteirinha")
    public String carteirinha(
            @RequestParam(required = false) UUID idPet,
            Model model
    ) {

        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("idPetSelecionado", idPet);

        if (idPet != null) {

            var pet = petRepository.findById(idPet)
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Pet não encontrado"
                            )
                    );

            var vacinas = vacinaRepository
                    .findByPetIdOrderByDataAplicacaoDesc(idPet);

            LocalDate hoje = LocalDate.now();
            LocalDate limite = hoje.plusDays(30);

            var registros = vacinas.stream()
                    .map(vacina -> {

                        String situacao = "EM DIA";

                        if (vacina.getProximaDose() != null) {

                            if (vacina.getProximaDose().isBefore(hoje)) {
                                situacao = "ATRASADA";

                            } else if (!vacina.getProximaDose()
                                    .isAfter(limite)) {
                                situacao = "PRÓXIMA DOSE";
                            }
                        }

                        return new RegistroVacina(
                                vacina.getNomeVacina(),
                                vacina.getFabricante(),
                                vacina.getLote(),
                                vacina.getDataAplicacao(),
                                vacina.getProximaDose(),
                                vacina.getObservacoes(),
                                situacao
                        );
                    })
                    .toList();

            model.addAttribute("pet", pet);
            model.addAttribute("vacinas", registros);
            model.addAttribute("totalVacinas", registros.size());
        }

        return "carteirinha";
    }

    public record RegistroVacina(
            String nomeVacina,
            String fabricante,
            String lote,
            LocalDate dataAplicacao,
            LocalDate proximaDose,
            String observacoes,
            String situacao
    ) {}
}
