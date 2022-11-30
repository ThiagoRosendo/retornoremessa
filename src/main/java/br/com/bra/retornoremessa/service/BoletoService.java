package br.com.bra.retornoremessa.service;


import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.repository.BoletoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BoletoService{

    private final BoletoRepository boletoRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy");

    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    // CREATE BOLETO
    public Boleto salvar(Boleto boleto) {
        return boletoRepository.save(boleto);
    }


    // READ BOLETO
    public Boleto getBoleto(String nossoNumero) throws Exception {
        var boleto =  boletoRepository.findById(nossoNumero);

        if (boleto.isEmpty()) {
            throw new Exception("Boleto não foi encontrado");
        }
        return boleto.get();
    }
    public List<Boleto> getAllBoletos() {
        return boletoRepository.findAll(Sort.by(Sort.Direction.ASC, "nossoNumero"));
    }

    // UPDATE BOLETO (FORMATO: DDMMYY)
    public Boleto alterarDataVencimento(String nossoNumero, String dataVencimento) throws Exception {
        Boleto boleto = getBoleto(nossoNumero);
        boleto.setDataVencimento(LocalDate.parse(dataVencimento, dtf));
        LocalDate dataAtual = LocalDate.now();
        boleto.setDataMovimento(dataAtual);
        boletoRepository.save(boleto);
        return boleto;
    }

    public Boleto alterarValor(String nossoNumero, String valor) throws Exception {
        Boleto boleto = getBoleto(nossoNumero);
        boleto.setValor(new BigDecimal(valor).divide(new BigDecimal(100)));;
        LocalDate dataAtual = LocalDate.now();
        boleto.setDataMovimento(dataAtual);
        boletoRepository.save(boleto);
        return boleto;
    }

    // DELETE BOLETO
    public String delete(String nossoNumero) throws Exception {
        boletoRepository.deleteById(getBoleto(nossoNumero).getNossoNumero());
        return "Boleto deletado";
    }

    public String deleteAll()  {
        boletoRepository.deleteAll();
        return "Boletos deletados";
    }
}