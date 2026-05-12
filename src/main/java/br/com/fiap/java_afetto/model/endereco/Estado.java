package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;

}
