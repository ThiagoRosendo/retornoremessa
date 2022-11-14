package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter
public class Historico {
    public Historico(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "boleto_id", nullable = false)
    private Boleto boleto;

    private String status;
    private String descricao;
    private LocalDateTime data;
}