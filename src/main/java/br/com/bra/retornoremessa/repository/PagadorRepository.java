package br.com.bra.retornoremessa.repository;

import br.com.bra.retornoremessa.entity.Pagador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagadorRepository extends JpaRepository<Pagador, Long> {
}