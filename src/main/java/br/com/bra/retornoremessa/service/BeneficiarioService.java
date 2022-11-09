package br.com.bra.retornoremessa.service;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.repository.BeneficiarioRepository;
import org.springframework.stereotype.Service;

@Service
public class BeneficiarioService{

    private final BeneficiarioRepository beneficiarioRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }
    public Beneficiario salvar(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    public Beneficiario buscaPorId(Long id) throws Exception {
        var beneficiario =  beneficiarioRepository.findById(id);

        if (beneficiario.isEmpty()) {
            throw new Exception("Beneficiario não foi encontrado");
        }
        return beneficiario.get();
    }
}