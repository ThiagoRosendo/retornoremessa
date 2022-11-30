package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.entity.Pagamento;
import br.com.bra.retornoremessa.service.BoletoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/boleto")
public class BoletoController {
    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    // CREATE BOLETO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Boleto salvar(@RequestBody Boleto boleto) {
        return boletoService.salvar(boleto);
    }


    // READ BOLETO
    @GetMapping("/{nosso-numero}")
    @ResponseStatus(HttpStatus.OK)
    public Boleto getBoleto(@PathVariable(value = "nosso-numero") String nosso_numero) throws Exception {
        return boletoService.getBoleto(nosso_numero);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Boleto> getAllBoletos() throws Exception {
        return boletoService.getAllBoletos();
    }

    // UPDATE BOLETO
    @PatchMapping("/{nossoNumero}/vencimento/{dataVencimento}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Boleto alterarVencimento(@PathVariable(value = "nossoNumero") String nossoNumero,
                                   @PathVariable(value = "dataVencimento") String dataVencimento) throws Exception {
        return boletoService.alterarDataVencimento(nossoNumero, dataVencimento);
    }

    @PatchMapping("/{nossoNumero}/valor/{valor}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Boleto alterarValor(@PathVariable(value = "nossoNumero") String nossoNumero,
                                    @PathVariable(value = "valor") String valor) throws Exception {
        return boletoService.alterarValor(nossoNumero, valor);
    }

    // DELETE BOLETO
    @DeleteMapping("/{nosso-numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteBoleto(@PathVariable(value = "nosso-numero") String nosso_numero) throws Exception {
        return boletoService.delete(nosso_numero);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAllBoletos() throws Exception {
        return boletoService.deleteAll();
    }

}