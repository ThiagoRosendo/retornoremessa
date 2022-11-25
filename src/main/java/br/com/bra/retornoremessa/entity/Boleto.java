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
    private String nossoNumero;

    @OneToMany(mappedBy = "boleto", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Pagador> pagador;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn (name = "beneficiario_id", nullable = true)
    private Beneficiario beneficiario;

    @OneToOne(mappedBy = "boleto", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @OneToOne(mappedBy = "boleto", cascade = CascadeType.ALL)
    private Historico historico;

    private String numeroDocumento;
    private LocalDate dataVencimento;
    private LocalDate dataMovimento;
    private BigDecimal valor;

    public Boleto(String nossoNumero, String numeroDocumento, BigDecimal valor,
                  LocalDate dataVencimento, LocalDate dataMovimento, Beneficiario beneficiario) {
        this.nossoNumero = nossoNumero;
        this.numeroDocumento = numeroDocumento;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataMovimento = dataMovimento;
        this.beneficiario = beneficiario;
    }
}