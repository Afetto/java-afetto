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
    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }
}
