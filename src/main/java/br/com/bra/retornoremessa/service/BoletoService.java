package br.com.bra.retornoremessa.service;


import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.repository.BoletoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.xml.bind.DatatypeConverter.parseString;

@Service
public class BoletoService{

    private final BoletoRepository boletoRepository;

    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    public Boleto salvar(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public Boleto buscaPorId(String id) throws Exception {
        var boleto =  boletoRepository.findById(id);

        if (boleto.isEmpty()) {
            throw new Exception("Boleto não foi encontrado");
        }
        return boleto.get();
    }

    public String delete(String id) throws Exception {
        boletoRepository.deleteById(buscaPorId(id).getNosso_numero());
        return "Boleto deletado";
    }

    public String deleteAll()  {
        boletoRepository.deleteAll();
        return "Boletos deletados";
    }
    public Boleto alterarDataVencimento(String id, String data_vencimento) throws Exception {
        Boleto boleto = buscaPorId(id);
        boleto.setData_vencimento(data_vencimento);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDate dataAtual = LocalDate.now();
        boleto.setData_movimento(parseString(dtf.format(dataAtual)));
        boletoRepository.save(boleto);
        return boleto;
    }

}