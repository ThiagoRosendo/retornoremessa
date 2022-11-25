package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.pdf.BoletoPDF;
import br.com.bra.retornoremessa.service.BeneficiarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final BeneficiarioService beneficiarioService;

    public RelatorioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @GetMapping("/beneficiario/{cnpj}")
    public void exportToPDF(HttpServletResponse response,
                            @PathVariable(value = "cnpj") String cnpj) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-yyyy HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Relatorio_Retorno - " + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Beneficiario beneficiario = beneficiarioService.getBeneficiario(cnpj);

        BoletoPDF exporter = new BoletoPDF(beneficiario);
        exporter.export(response);
    }
}
