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
public class Boleto {
    public Boleto(){}
    @Id
    private Long nosso_numero;
/*
    @ManyToOne
    @JoinColumn(name = "beneficiario_id")
    private Beneficiario beneficiario;
*/
    private String numero_documento;
    private LocalDateTime data_vencimento;
    private LocalDateTime data_movimento;
    private String valor;
    private String  linha_digitavel;

    public Boleto(Long nosso_numero, String numero_documento, String valor) {
        this.nosso_numero = nosso_numero;
        this.numero_documento = numero_documento;
        this.valor = valor;
    }

    public Long getNosso_numero() {
        return nosso_numero;
    }
}