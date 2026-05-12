package br.com.fiap.java_afetto.repository;

import br.com.fiap.java_afetto.model.endereco.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaisRepository extends JpaRepository<Pais, UUID> {
}
