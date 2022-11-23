package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter
public class Historico {
    public Historico(){}

    public Historico(Boleto boleto, String status, String descricao, String data) {
        this.boleto = boleto;
        this.status = status;
        this.descricao = descricao;
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn (name = "boleto_id", nullable = false)
    private Boleto boleto;

    private String status;
    private String descricao;
    private String data;

}