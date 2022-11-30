package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Remessa;
import br.com.bra.retornoremessa.pdf.RemessaToPDF;
import br.com.bra.retornoremessa.service.BeneficiarioService;
import br.com.bra.retornoremessa.service.RemessaService;
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
    private final RemessaService remessaService;

    public RelatorioController(BeneficiarioService beneficiarioService, RemessaService remessaService) {
        this.beneficiarioService = beneficiarioService;
        this.remessaService = remessaService;
    }

    @GetMapping("/remessa/{id}")
    public void exportToPDF(HttpServletResponse response,
                            @PathVariable(value = "id") Long id) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Relatorio_Retorno - " + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Remessa remessa = remessaService.getRemessa(id);

        RemessaToPDF exporter = new RemessaToPDF(remessa);
        exporter.export(response);
    }
}
