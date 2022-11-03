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
    @Id
    private Long nosso_numero;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id")
    private Beneficiario beneficiario;

    private Long numero_documento;
    private LocalDateTime data_vencimento;
    private LocalDateTime data_movimento;
    private BigDecimal valor;
    private String  linha_digitavel;
}