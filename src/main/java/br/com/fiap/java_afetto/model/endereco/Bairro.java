package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;
    @OneToMany(mappedBy = "bairro")
    private List<Logradouro> logradouros;

}
