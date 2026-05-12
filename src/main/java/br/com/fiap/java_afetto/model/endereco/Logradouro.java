package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "id_bairro", nullable = false)
    private Bairro bairro;
    @OneToMany(mappedBy = "logradouro")
    private List<Endereco> enderecos;

}
