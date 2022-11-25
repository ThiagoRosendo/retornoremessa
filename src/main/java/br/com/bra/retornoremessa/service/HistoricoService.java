package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.entity.Historico;
import br.com.bra.retornoremessa.repository.BeneficiarioRepository;
import br.com.bra.retornoremessa.repository.HistoricoRepository;
import br.com.bra.retornoremessa.status.StatusBoleto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.xml.bind.DatatypeConverter.parseString;

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

    public String delete(Long id) throws Exception {
        historicoRepository.deleteById(buscaPorId(id).getId());
        return "Historico deletado";
    }

    public Historico alteraStatus(Long id, String status) throws Exception {
        Historico historico = buscaPorId(id);
        historico.setStatus(status);
        historico.setDescricao(StatusBoleto.status(status));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDate dataAtual = LocalDate.now();
        historico.setData(parseString(dtf.format(dataAtual)));
        historicoRepository.save(historico);
        return historico;
    }
}
