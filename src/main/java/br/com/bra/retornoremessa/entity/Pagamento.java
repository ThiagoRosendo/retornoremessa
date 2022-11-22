package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Pagamento {
    public Pagamento(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "nosso_numero", nullable = false)
    private Boleto boleto;

    private LocalDateTime data_pagamento;
    private BigDecimal valor;
}
