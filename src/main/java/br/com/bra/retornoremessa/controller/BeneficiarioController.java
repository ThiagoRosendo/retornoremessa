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
    public Beneficiario buscaPorId(@PathVariable(value = "id") String id) throws Exception {
        return beneficiarioService.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficiario salvar(@RequestBody Beneficiario beneficiario) {
        return beneficiarioService.salvar(beneficiario);
    }

    @PatchMapping("/{id}/{nome}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Beneficiario alteraNome(@PathVariable(value = "id") String cnpj,
                         @PathVariable(value = "nome") String nome) throws Exception {
        return beneficiarioService.alterarNome(cnpj, nome);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleta(@PathVariable(value = "id") String id) throws Exception {
        return beneficiarioService.delete(id);
    }
}