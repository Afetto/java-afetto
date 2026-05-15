package br.com.fiap.java_afetto.model.pet;

import br.com.fiap.java_afetto.model.Usuario;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TBL_PET")
public class Pet {

    @Id
    @GeneratedValue
    @Column(name = "id_pet")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

}
