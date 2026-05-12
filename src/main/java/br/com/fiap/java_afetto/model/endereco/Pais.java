package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TBL_PAIS")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_pais")
    private UUID id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sigla")
    private String sigla;

    @OneToMany(mappedBy = "pais")
    private List<Estado> estados;

}
