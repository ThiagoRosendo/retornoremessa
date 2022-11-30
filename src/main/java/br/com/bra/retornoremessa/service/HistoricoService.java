package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Historico;
import br.com.bra.retornoremessa.entity.Remessa;
import br.com.bra.retornoremessa.repository.HistoricoRepository;
import br.com.bra.retornoremessa.status.StatusBoleto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoricoService {
    private final HistoricoRepository historicoRepository;

    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    // CREATE HISTORICO
    public Historico salvar(Historico historico) {
        return historicoRepository.save(historico);
    }

    // READ HISTORICO
    public Historico getHistorico(Long id) throws Exception {
        var historico =  historicoRepository.findById(id);

        if (historico.isEmpty()) {
            throw new Exception("Historico não foi encontrado");
        }
        return historico.get();
    }

    public List<Historico> getAllHistoricos() {
        return historicoRepository.findAll();
    }


    // UPDATE HISTORICO

    public Historico alteraStatus(Long id, String status) throws Exception {
        Historico historico = getHistorico(id);
        historico.setStatus(status);
        historico.setDescricao(StatusBoleto.status(status));
        LocalDate dataAtual = LocalDate.now();
        historico.setData(dataAtual);
        historicoRepository.save(historico);
        return historico;
    }

    // DELETE HISTORICO
    public String delete(Long id) throws Exception {
        historicoRepository.deleteById(getHistorico(id).getId());
        return "Historico deletado";
    }

    public String deleteAll()  {
        historicoRepository.deleteAll();
        return "Historicos deletados";
    }


}
