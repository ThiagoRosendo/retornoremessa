package br.com.bra.retornoremessa.repository;

import br.com.bra.retornoremessa.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}