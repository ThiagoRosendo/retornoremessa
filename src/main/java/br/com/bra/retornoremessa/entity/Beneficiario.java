package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class Beneficiario {

    public Beneficiario() {
    }

    @Id
    private String cnpj;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.REMOVE)
    private Set<Boleto> boletos;

    private String nome;
    private Long agencia;
    private Long conta;
    private Long carteira;
    private Long contrato;

    public Beneficiario(String cnpj, String nome, Long agencia, Long conta, Long carteira, Long contrato) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.carteira = carteira;
        this.contrato = contrato;
    }

}
