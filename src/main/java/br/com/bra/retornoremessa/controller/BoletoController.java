package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.*;
import br.com.bra.retornoremessa.pdf.BoletoPDF;
import br.com.bra.retornoremessa.service.BoletoService;
import br.com.bra.retornoremessa.service.HistoricoService;
import br.com.bra.retornoremessa.service.PagadorService;
import br.com.bra.retornoremessa.service.PagamentoService;
import br.com.bra.retornoremessa.status.StatusBoleto;
import com.lowagie.text.DocumentException;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@RequestMapping("/remessa")
public class BoletoController {
    private final BoletoService boletoService;
    private final HistoricoService historicoService;
    private final PagamentoService pagamentoService;
    private final PagadorService pagadorService;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy");
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
    DecimalFormat format = new DecimalFormat("###,###.00");


    public BoletoController(BoletoService boletoService, HistoricoService historicoService,
                            PagamentoService pagamentoService, PagadorService pagadorService) {
        this.boletoService = boletoService;
        this.historicoService = historicoService;
        this.pagamentoService = pagamentoService;
        this.pagadorService = pagadorService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boleto buscaPorId(@PathVariable(value = "id") String id) throws Exception {
        return boletoService.buscaPorId(id);
    }

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

            for (String linha : data.subList(1, data.size())) {

                //DADOS DO BOLETO
                String nossoNum = linha.substring(71, 81);
                String numDoc = linha.substring(116, 126);
                String valorString = linha.substring(152, 165);
                BigDecimal valor = new BigDecimal(valorString).divide(new BigDecimal(100));
                LocalDate dataVencimento = LocalDate.parse(linha.substring(146,152), dtf);
                LocalDate dataMovimento = LocalDate.parse(linha.substring(110,116), dtf);

                Boleto boleto = new Boleto(nossoNum, numDoc, valor, dataVencimento, dataMovimento, beneficiario);
                boletoService.salvar(boleto);

                //DADOS DO HISTORICO

                String status = linha.substring(108, 110);
                String descricao = StatusBoleto.status(status);

                Historico historico = new Historico (boleto, status, descricao,"111122");
                historicoService.salvar(historico);


                //DADOS DE PAGAMENTO

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

                //DADOS PAGADOR

                Pagador pagador = new Pagador(boleto, 1L, 2L);
                //pagadorService.salvar(pagador);

            }
        }
        return "success";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleta(@PathVariable(value = "id") String id) throws Exception {
        return boletoService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAll() throws Exception {
        return boletoService.deleteAll();
    }

    @PatchMapping("/{id}/vencimento/{dataVencimento}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Boleto alteraVencimento(@PathVariable(value = "id") String id,
                        @PathVariable(value = "dataVencimento") LocalDate dataVencimento) throws Exception {
        return boletoService.alterarDataVencimento(id, dataVencimento);
    }

//    @GetMapping("/pdf")
//     public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        List<Boleto> listaBoleto = boletoService.buscaTodos();
//
//        BoletoPDF exporter = new BoletoPDF(listaBoleto);
//        exporter.export(response);
//
//    }
}