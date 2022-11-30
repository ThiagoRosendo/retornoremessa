package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.*;
import br.com.bra.retornoremessa.service.*;
import br.com.bra.retornoremessa.status.StatusBoleto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/remessa")
public class RemessaController {

    private final BoletoService boletoService;
    private final HistoricoService historicoService;
    private final PagamentoService pagamentoService;

    private final RemessaService remessaService;
    private final BeneficiarioService beneficiarioService;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy");
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
    DecimalFormat format = new DecimalFormat("###,###.00");


    public RemessaController(BoletoService boletoService, HistoricoService historicoService,
                             PagamentoService pagamentoService, BeneficiarioService beneficiarioService,
                             RemessaService remessaService) {
        this.boletoService = boletoService;
        this.historicoService = historicoService;
        this.pagamentoService = pagamentoService;
        this.remessaService = remessaService;
        this.beneficiarioService = beneficiarioService;
    }

    // CREATE REMESSA
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String salvar(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        List<String> data = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            data = reader.lines().toList();

            //DUAS PRIMEIRAS LINHAS DO ARQUIVO
            String linha1 = String.valueOf(data.subList(0, 1));
            String linha2 = String.valueOf(data.subList(1, 2));

            //DADOS DO BENEFICIARIO
            String cnpj = linha2.substring(4, 18);
            Long contrato = Long.parseLong(linha1.substring(27, 47));
            Long carteira = Long.parseLong(linha2.substring(23, 25));
            Long agencia = Long.parseLong(linha2.substring(25, 30));
            Long conta = Long.parseLong(linha2.substring(30, 37));
            String nome = linha1.substring(47, 76);

            Beneficiario beneficiario = new Beneficiario(cnpj, nome, agencia, conta, carteira, contrato);
            Remessa remessa = new Remessa(beneficiario, "Remessa " + beneficiario.getCnpj());
            beneficiarioService.salvar(beneficiario);
            remessaService.salvar(remessa);

            for (String linha : data.subList(1, data.size() - 1)) {

                // DADOS DO BOLETO
                String nossoNum = linha.substring(71, 81);
                String numDoc = linha.substring(116, 126);
                String valorString = linha.substring(152, 165);
                BigDecimal valor = new BigDecimal(valorString).divide(new BigDecimal(100));
                LocalDate dataVencimento = LocalDate.parse(linha.substring(146,152), dtf);
                LocalDate dataMovimento = LocalDate.parse(linha.substring(110,116), dtf);

                Boleto boleto = new Boleto(nossoNum, numDoc, valor, dataVencimento,
                                           dataMovimento, beneficiario, remessa);
                boletoService.salvar(boleto);

                // DADOS DO HISTORICO

                String status = linha.substring(108, 110);
                String descricao = StatusBoleto.status(status);
                LocalDate dataAtual = LocalDate.now();

                Historico historico = new Historico (boleto, status, descricao, dataAtual);
                historicoService.salvar(historico);


                // DADOS DE PAGAMENTO

                String valorPagamentoString = linha.substring(253, 266);
                BigDecimal valorPagamento = new BigDecimal(valorPagamentoString).divide(new BigDecimal(100));
                String dataPagamentoString = linha.substring(295, 301);
                LocalDate dataPagamento;
                if ("      ".equals(dataPagamentoString)){
                    dataPagamento = null;
                }else {
                    dataPagamento = LocalDate.parse(linha.substring(295, 301), dtf);
                }

                Pagamento pagamento = new Pagamento(boleto, dataPagamento, valorPagamento);
                pagamentoService.salvar(pagamento);
            }
        }
        return "success";
    }

    // READ REMESSA
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Remessa getRemessa(@PathVariable(value = "id") Long id) throws Exception {
        return remessaService.getRemessa(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Remessa> getAllRemessas() {
        return remessaService.getAllRemessas();
    }

    // UPDATE REMESSA
    @PatchMapping("/{id}/nome/{nome}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Remessa alterarNome(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "nome") String nome) throws Exception {
        return remessaService.alterarNome(id, nome);
    }

    // DELETE REMESSA
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        return remessaService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAll() throws Exception {
        return remessaService.deleteAll();
    }

}
