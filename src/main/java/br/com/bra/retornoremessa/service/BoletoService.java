package br.com.bra.retornoremessa.service;


import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.repository.BoletoRepository;
import org.springframework.stereotype.Service;

@Service
public class BoletoService{

    private final BoletoRepository boletoRepository;

    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    public Boleto salvar(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public Boleto buscaPorId(Long id) throws Exception {
        var boleto =  boletoRepository.findById(id);

        if (boleto.isEmpty()) {
            throw new Exception("Boleto não foi encontrado");
        }
        return boleto.get();
    }

    public String delete(Long id) throws Exception {
        boletoRepository.deleteById(buscaPorId(id).getNosso_numero());
        return "Boleto deletado";
    }}