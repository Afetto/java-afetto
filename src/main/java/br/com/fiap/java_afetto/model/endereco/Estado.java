package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TBL_ESTADO")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_estado")
    private UUID uuid;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sigla")
    private String sigla;

}
