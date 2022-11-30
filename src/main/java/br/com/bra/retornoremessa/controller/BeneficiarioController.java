package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.service.BeneficiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {
    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    // CREATE BENEFICIARIO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficiario salvar(@RequestBody Beneficiario beneficiario) {
        return beneficiarioService.salvar(beneficiario);
    }

    // READ BENEFICIARIO
    @GetMapping("/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public Beneficiario getBeneficiario(@PathVariable(value = "cnpj") String cnpj) throws Exception {
        return beneficiarioService.getBeneficiario(cnpj);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Beneficiario> getAllBeneficiarios() throws Exception {
        return beneficiarioService.getAllBeneficiarios();
    }

    // UPDATE BENEFICIARIO
    @PatchMapping("/{cnpj}/{nome}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Beneficiario alteraNome(@PathVariable(value = "cnpj") String cnpj,
                                   @PathVariable(value = "nome") String nome) throws Exception {
        return beneficiarioService.alterarNome(cnpj, nome);
    }

    // DELETE BENEFICIARIO
    @DeleteMapping("/{cnpj}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable(value = "cnpj") String cnpj) throws Exception {
        return beneficiarioService.delete(cnpj);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAll() throws Exception {
        return beneficiarioService.deleteAll();
    }
}