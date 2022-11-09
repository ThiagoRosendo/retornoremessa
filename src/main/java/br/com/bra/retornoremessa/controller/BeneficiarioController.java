package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.service.BeneficiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {
    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Beneficiario buscaPorId(@PathVariable(value = "id") Long id) throws Exception {
        return beneficiarioService.buscaPorId(id);
    }
}