package br.com.bra.retornoremessa.repository;

import br.com.bra.retornoremessa.entity.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<Boleto, String> {
}