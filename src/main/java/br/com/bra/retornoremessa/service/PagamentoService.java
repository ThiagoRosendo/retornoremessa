package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.entity.Pagamento;
import br.com.bra.retornoremessa.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    // PAGAMENTO CREATE
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    // READ PAGAMENTO
    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento getPagamento(Long id) throws Exception {
        var pagamento =  pagamentoRepository.findById(id);

        if (pagamento.isEmpty()) {
            throw new Exception("Pagamento não foi encontrado");
        }
        return pagamento.get();
    }
    // UPDATE PAGAMENTO

    public Pagamento alterarValorPgto(Long id, String valor) throws Exception {
        Pagamento pagamento = getPagamento(id);
        BigDecimal valorPagamento = new BigDecimal(valor).divide(new BigDecimal(100));
        pagamento.setValor(valorPagamento);
        pagamentoRepository.save(pagamento);
        return pagamento;
    }

    // DELETE PAGAMENTO
    public String delete(Long id) throws Exception {
        pagamentoRepository.deleteById(getPagamento(id).getId());
        return "Pagamento deletado";
    }

    public String deleteAll()  {
        pagamentoRepository.deleteAll();
        return "Pagamentos deletados";
    }
}
