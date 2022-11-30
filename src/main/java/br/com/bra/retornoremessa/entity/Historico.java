package br.com.bra.retornoremessa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Historico {
    public Historico(){}

    public Historico(Boleto boleto, String status, String descricao, LocalDate data) {
        this.boleto = boleto;
        this.status = status;
        this.descricao = descricao;
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "boleto_id", nullable = false)
    private Boleto boleto;

    private String status;
    private String descricao;
    private LocalDate data;

}