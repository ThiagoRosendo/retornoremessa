package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Getter @Setter
public class Pagamento {
    @Id
    @ManyToOne
    @JoinColumn(name = "boleto_id_pgto")
    private Boleto boleto;

    private LocalDateTime data_pagamento;
    private BigDecimal valor;
}