
package br.com.fiap.java_afetto.repository;

import br.com.fiap.java_afetto.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VacinaRepository extends JpaRepository<Vacina, UUID> {

    List<Vacina> findByPetIdOrderByDataAplicacaoDesc(UUID idPet);

}
