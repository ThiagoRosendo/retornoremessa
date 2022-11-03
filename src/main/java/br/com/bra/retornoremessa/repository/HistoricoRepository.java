package br.com.bra.retornoremessa.repository;

import br.com.bra.retornoremessa.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}