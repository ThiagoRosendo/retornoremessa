package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.service.BeneficiarioService;
import br.com.bra.retornoremessa.service.BoletoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/remessa")
public class BoletoController {
    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boleto buscaPorId(@PathVariable(value = "id") Long id) throws Exception {
        return boletoService.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String salvar(@RequestParam("file") MultipartFile file) throws IOException {
        List<String> data = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            data = reader.lines().toList();
            String linha1 = String.valueOf(data.subList(0, 1));
            String linha2 = String.valueOf(data.subList(1, 2));

            String cnpj = linha2.substring(4, 18);
            Long contrato = Long.parseLong(linha1.substring(27, 47));
            Long carteira = Long.parseLong(linha2.substring(23, 25));
            Long agencia = Long.parseLong(linha2.substring(25, 30));
            Long conta = Long.parseLong(linha2.substring(30, 37));
            String nome = linha1.substring(47, 76);

            System.out.println(" cnpj: " + cnpj + " nome: " + nome + " ag: "
                    + agencia + "conta: " + conta + " cart: " + carteira + " cont: " + contrato);

            Beneficiario beneficiario = new Beneficiario(cnpj, nome, agencia, conta, carteira, contrato);

            for (String linha : data.subList(1, data.size())) {
                String nossoNumString = linha.substring(71, 81);
                Long nossoNum = parseLong(nossoNumString);
                String numDoc = linha.substring(116, 126);
                String valor = linha.substring(152, 165);
                String dataVencimento = linha.substring(146,152);
                String dataMovimento = linha.substring(110,116);
                Boleto boleto = new Boleto(nossoNum, numDoc, valor, dataVencimento, dataMovimento, beneficiario);
                boletoService.salvar(boleto);
            }
        }
        return "success";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleta(@PathVariable(value = "id") Long id) throws Exception {
        return boletoService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAll() throws Exception {
        return boletoService.deleteAll();
    }

    @PatchMapping("/{id}/vencimento/{dataVencimento}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Boleto alteraVencimento(@PathVariable(value = "id") Long id,
                        @PathVariable(value = "dataVencimento") String dataVencimento) throws Exception {
        return boletoService.alterarDataVencimento(id, dataVencimento);
    }
}