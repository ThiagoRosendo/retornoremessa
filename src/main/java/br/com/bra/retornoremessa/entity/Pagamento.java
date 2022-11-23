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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn (name = "nosso_numero", nullable = false)
    private Boleto boleto;

    private String data_pagamento;
    private String valor;

    public Pagamento(Boleto boleto, String data_pagamento, String valor) {
        this.boleto = boleto;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
    }
}
