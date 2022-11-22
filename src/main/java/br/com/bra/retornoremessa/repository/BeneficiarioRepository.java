package br.com.bra.retornoremessa.repository;

import br.com.bra.retornoremessa.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, String> {
}