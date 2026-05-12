package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TBL_LOGRADOURO")
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_logradouro")
    private UUID uuid;
    @Column(name = "nome")
    private String nome;

}
