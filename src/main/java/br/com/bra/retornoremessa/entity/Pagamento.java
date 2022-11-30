package br.com.bra.retornoremessa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Pagamento {
    public Pagamento(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "boleto", nullable = false)
    private Boleto boleto;

    private LocalDate data_pagamento;
    private BigDecimal valor;

    public Pagamento(Boleto boleto, LocalDate data_pagamento, BigDecimal valor) {
        this.boleto = boleto;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
    }
}
