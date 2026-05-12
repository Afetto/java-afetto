package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TBL_CIDADE")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cidade")
    private UUID uuid;
    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;
    @OneToMany(mappedBy = "cidade")
    private List<Bairro> bairros;

}
