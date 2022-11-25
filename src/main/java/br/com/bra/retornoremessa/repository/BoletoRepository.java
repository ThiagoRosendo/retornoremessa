package br.com.bra.retornoremessa.repository;

import br.com.bra.retornoremessa.entity.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoletoRepository extends JpaRepository<Boleto, String> {
}