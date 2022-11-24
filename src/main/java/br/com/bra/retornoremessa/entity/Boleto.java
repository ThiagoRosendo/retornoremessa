package br.com.bra.retornoremessa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Entity
@Getter @Setter
public class Boleto {

    public Boleto(){}
    @Id
    private String nosso_numero;

    @OneToMany(mappedBy = "boleto", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Pagador> pagador;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn (name = "beneficiario_id", nullable = true)
    private Beneficiario beneficiario;

    @OneToOne(mappedBy = "boleto")
    private Pagamento pagamento;

    @OneToOne(mappedBy = "boleto", cascade = CascadeType.ALL)
    private Historico historico;

    private String numero_documento;
    private LocalDate data_vencimento;
    private LocalDate data_movimento;
    private BigDecimal valor;

    public Boleto(String nosso_numero, String numero_documento, BigDecimal valor,
                  LocalDate data_vencimento, LocalDate data_movimento, Beneficiario beneficiario) {
        this.nosso_numero = nosso_numero;
        this.numero_documento = numero_documento;
        this.valor = valor;
        this.data_vencimento = data_vencimento;
        this.data_movimento = data_movimento;
        this.beneficiario = beneficiario;
    }
}