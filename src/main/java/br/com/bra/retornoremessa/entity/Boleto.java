package br.com.bra.retornoremessa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter @Setter
public class Boleto {

    public Boleto(){}
    @Id
    private String nossoNumero;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn (name = "beneficiario_id", nullable = true)
    private Beneficiario beneficiario;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn (name = "remessa_id", nullable = true)
    private Remessa remessa;

    @OneToOne(mappedBy = "boleto", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Pagamento pagamento;

    @OneToOne(mappedBy = "boleto", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Historico historico;

    private String numeroDocumento;
    private LocalDate dataVencimento;
    private LocalDate dataMovimento;
    private BigDecimal valor;

    public Boleto(String nossoNumero, String numeroDocumento, BigDecimal valor,
                  LocalDate dataVencimento, LocalDate dataMovimento, Beneficiario beneficiario,
                  Remessa remessa) {
        this.nossoNumero = nossoNumero;
        this.numeroDocumento = numeroDocumento;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataMovimento = dataMovimento;
        this.beneficiario = beneficiario;
        this.remessa = remessa;
    }
}