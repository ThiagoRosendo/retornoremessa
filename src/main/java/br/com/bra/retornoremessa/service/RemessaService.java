package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Remessa;
import br.com.bra.retornoremessa.repository.RemessaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemessaService{

    private final RemessaRepository remessaRepository;

    public RemessaService(RemessaRepository remessaRepository) {
        this.remessaRepository = remessaRepository;
    }

    // CREATE REMESSA
    public Remessa salvar(Remessa remessa) {
        return remessaRepository.save(remessa);
    }


    // READ REMESSA
    public Remessa getRemessa(Long id) throws Exception {
        var remessa =  remessaRepository.findById(id);

        if (remessa.isEmpty()) {
            throw new Exception("Remessa não enocntrada");
        }
        return remessa.get();
    }
    public List<Remessa> getAllRemessas() {
        return remessaRepository.findAll();
    }

    // DELETE REMESSA
    public String delete(Long id) throws Exception {
        remessaRepository.deleteById(getRemessa(id).getId());
        return "Remessa deletada";
    }

    public String deleteAll()  {
        remessaRepository.deleteAll();
        return "Remessas deletadas";
    }
}
