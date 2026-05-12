package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TBL_BAIRRO")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_bairro")
    private UUID uuid;
    @Column(name = "nome")
    private String nome;

}
