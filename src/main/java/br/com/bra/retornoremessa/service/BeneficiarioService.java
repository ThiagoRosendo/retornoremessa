package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.repository.BeneficiarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiarioService{

    private final BeneficiarioRepository beneficiarioRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }
    // CREATE BENEFICIARIO
    public Beneficiario salvar(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    // READ BENEFICIARIO
    public Beneficiario getBeneficiario(String cnpj) throws Exception {
        var beneficiario =  beneficiarioRepository.findById(cnpj);

        if (beneficiario.isEmpty()) {
            throw new Exception("Beneficiario não foi encontrado");
        }
        return beneficiario.get();
    }

    public List<Beneficiario> getAllBeneficiarios() {
        return beneficiarioRepository.findAll();
    }

    // UPDATE BENEFICIARIO
    public Beneficiario alterarNome(String cnpj, String nome) throws Exception {
        Beneficiario beneficiario = getBeneficiario(cnpj);
        beneficiario.setNome(nome);
        beneficiarioRepository.save(beneficiario);
        return beneficiario;
    }

    public String delete(String cnpj) throws Exception {
        beneficiarioRepository.deleteById(getBeneficiario(cnpj).getCnpj());
        return "Beneficiario deletado";
    }

    public String deleteAll() {
        beneficiarioRepository.deleteAll();
        return "Beneficiarios deletados";
    }


}