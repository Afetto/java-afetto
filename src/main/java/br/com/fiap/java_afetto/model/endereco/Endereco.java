package br.com.fiap.java_afetto.model.endereco;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TBL_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_endereco")
    private UUID uuid;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "cep")
    private String cep;
    @Column(name = "latitude", precision = 10, scale = 7)
    private BigDecimal latitude;
    @Column(name = "longitude", precision = 10, scale = 7)
    private BigDecimal longitude;

    @ManyToOne
    @JoinColumn(name = "id_logradouro", nullable = false)
    private Logradouro logradouro;
}
