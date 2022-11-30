package br.com.bra.retornoremessa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Remessa {

    public Remessa() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "remessa", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @OrderBy("nossoNumero")
    private Set<Boleto> boletos = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn (name = "beneficiario_id", nullable = true)
    private Beneficiario beneficiario;

    public Remessa(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

}
