package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class Pagador {
    public Pagador(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "nosso_numero")
    private Boleto boleto;

    private Long conta_produto;
    private Long carteira;

    public Pagador(Boleto boleto, Long conta_produto, Long carteira) {
        this.boleto = boleto;
        this.conta_produto = conta_produto;
        this.carteira = carteira;
    }
}