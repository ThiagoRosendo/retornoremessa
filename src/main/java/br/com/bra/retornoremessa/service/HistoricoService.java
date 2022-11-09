package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Historico;
import br.com.bra.retornoremessa.repository.BeneficiarioRepository;
import br.com.bra.retornoremessa.repository.HistoricoRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {
    private final HistoricoRepository historicoRepository;

    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }
    public Historico salvar(Historico historico) {
        return historicoRepository.save(historico);
    }

    public Historico buscaPorId(Long id) throws Exception {
        var historico =  historicoRepository.findById(id);

        if (historico.isEmpty()) {
            throw new Exception("Historico não foi encontrado");
        }
        return historico.get();
    }
}
