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
    public Beneficiario salvar(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    public Beneficiario buscaPorId(String cnpj) throws Exception {
        var beneficiario =  beneficiarioRepository.findById(cnpj);

        if (beneficiario.isEmpty()) {
            throw new Exception("Beneficiario não foi encontrado");
        }
        return beneficiario.get();
    }

    public Beneficiario alterarNome(String cnpj, String nome) throws Exception {
        Beneficiario beneficiario = buscaPorId(cnpj);
        beneficiario.setNome(nome);
        beneficiarioRepository.save(beneficiario);
        return beneficiario;
    }

    public String delete(String id) throws Exception {
        beneficiarioRepository.deleteById(buscaPorId(id).getCnpj());
        return "Beneficiario deletado";
    }


}