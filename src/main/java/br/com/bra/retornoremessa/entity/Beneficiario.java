package br.com.bra.retornoremessa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Getter @Setter
public class Beneficiario {

    public Beneficiario() {
    }

    @Id
    private String cnpj;
}
/*
    @OneToMany(mappedBy = "id_beneficiario")
   private Set<Boleto> boleto;

    private String nome;
    private Long agencia;
    private Long conta;
    private Long carteira;
    private Long contrato;
}

*/