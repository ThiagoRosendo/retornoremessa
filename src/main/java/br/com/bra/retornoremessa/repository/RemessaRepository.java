package br.com.bra.retornoremessa.repository;

import br.com.bra.retornoremessa.entity.Remessa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemessaRepository extends JpaRepository<Remessa, Long> {
}