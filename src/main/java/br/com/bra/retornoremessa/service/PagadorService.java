package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Pagador;
import br.com.bra.retornoremessa.repository.PagadorRepository;
import org.springframework.stereotype.Service;

@Service
public class PagadorService {

private final PagadorRepository pagadorRepository;

    public PagadorService(PagadorRepository pagadorRepository) {
        this.pagadorRepository = pagadorRepository;
    }
    public Pagador salvar(Pagador pagador) {
        return pagadorRepository.save(pagador);
    }

    public Pagador buscaPorId(Long id) throws Exception {
        var pagador =  pagadorRepository.findById(id);

        if (pagador.isEmpty()) {
            throw new Exception("Pagador não foi encontrado");
        }
        return pagador.get();
    }

    public String delete(Long id) throws Exception {
        pagadorRepository.deleteById(buscaPorId(id).getId());
        return "Pagador deletado";
    }

    public String deleteAll()  {
        pagadorRepository.deleteAll();
        return "Pagadores deletados";
    }
}
