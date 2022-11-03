package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Getter @Setter
public class Pagador {
    @Id
    @OneToOne
    @JoinColumn(name = "boleto_id_pagador")
    private Boleto boleto;
    private Long conta;
    private Long carteira;
}