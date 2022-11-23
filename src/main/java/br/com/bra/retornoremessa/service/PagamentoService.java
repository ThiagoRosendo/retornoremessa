package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Pagamento;
import br.com.bra.retornoremessa.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> buscaTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscaPorId(Long id) throws Exception {
        var pagamento =  pagamentoRepository.findById(id);

        if (pagamento.isEmpty()) {
            throw new Exception("Pagamento não foi encontrado");
        }
        return pagamento.get();
    }

    public String delete(Long id) throws Exception {
        pagamentoRepository.deleteById(buscaPorId(id).getId());
        return "Pagamento deletado";
    }

    public String deleteAll()  {
        pagamentoRepository.deleteAll();
        return "Pagamentos deletados";
    }
}
