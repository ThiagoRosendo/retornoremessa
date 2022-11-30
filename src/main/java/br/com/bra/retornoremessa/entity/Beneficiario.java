package br.com.bra.retornoremessa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Beneficiario {

    public Beneficiario() {
    }

    @Id
    private String cnpj;

    @OneToMany(mappedBy = "beneficiario", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @OrderBy("nossoNumero")
    private Set<Boleto> boletos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "beneficiario", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @OrderBy("id")
    private Set<Remessa> remessas = new LinkedHashSet<>();

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
