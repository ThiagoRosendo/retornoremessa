package br.com.bra.retornoremessa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter @Setter
public class Boleto {

    public Boleto(){}
    @Id
    private String nosso_numero;

    @OneToOne(mappedBy = "boleto", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Pagador pagador;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn (name = "beneficiario_id", nullable = true)
    private Beneficiario beneficiario;

    @OneToMany(mappedBy = "boleto")
    private Set<Pagamento> pagamento;

    @OneToMany(mappedBy = "boleto", cascade = CascadeType.ALL)
    private Set<Historico> historico;

    private String numero_documento;
    private String data_vencimento;
    private String data_movimento;
    private String valor;

    public Boleto(String nosso_numero, String numero_documento, String valor,
                  String data_vencimento, String data_movimento, Beneficiario beneficiario) {
        this.nosso_numero = nosso_numero;
        this.numero_documento = numero_documento;
        this.valor = valor;
        this.data_vencimento = data_vencimento;
        this.data_movimento = data_movimento;
        this.beneficiario = beneficiario;
    }
}