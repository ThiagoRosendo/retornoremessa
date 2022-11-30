package br.com.bra.retornoremessa.pdf;

import br.com.bra.retornoremessa.entity.Beneficiario;
import br.com.bra.retornoremessa.entity.Boleto;
import br.com.bra.retornoremessa.entity.Remessa;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class RemessaToPDF {
    private final Set<Boleto> listaBoletos;
    private final Beneficiario beneficiario;

    DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter dataHoraFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    Locale brasil = new Locale("pt", "BR");
    NumberFormat format = NumberFormat.getCurrencyInstance(brasil);

    public RemessaToPDF(Remessa remessa) {
        this.listaBoletos = remessa.getBoletos();
        this.beneficiario = remessa.getBeneficiario();
    }

    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        font.setSize(9);

        cell.setPhrase(new Phrase("Nosso Número", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Número Docto", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Movimento", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Vencimento", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Pagamento", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Valor", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Valor Pagamento", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(8);

        int cont = 1;

        for (Boleto boleto : listaBoletos) {
            if (cont % 2 == 0){ cell.setBackgroundColor(Color.LIGHT_GRAY);}
            else { cell.setBackgroundColor(Color.WHITE);}

            cell.setPhrase(new Phrase(boleto.getNossoNumero(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(boleto.getNumeroDocumento(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(dataFormat.format(boleto.getDataMovimento()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(dataFormat.format(boleto.getDataVencimento()), font));
            table.addCell(cell);


            if(boleto.getPagamento().getData_pagamento() == null){
                cell.setPhrase(new Phrase("", font));
                table.addCell(cell);
            }else {

                cell.setPhrase(new Phrase(dataFormat.format(boleto.getPagamento().getData_pagamento()), font));
                table.addCell(cell);
            }
            cell.setPhrase(new Phrase(format.format(new BigDecimal(boleto.getValor().toString())), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(format.format(new BigDecimal(boleto.getPagamento().getValor().toString())), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(boleto.getHistorico().getDescricao(), font));
            table.addCell(cell);

            cont++;
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException, ParseException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
        String cnpj = beneficiario.getCnpj();
        mask.setValueContainsLiteralCharacters(false);
        cnpj = mask.valueToString(cnpj);

        document.open();
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA);
        fontHeader.setSize(18);
        fontBody.setSize(12);

        Paragraph header = new Paragraph("Relatório de Retorno de Remessa", fontHeader);

        Paragraph razao = new Paragraph("Razão Social: " + beneficiario.getNome(), fontBody);
        Paragraph cnpjHeader = new Paragraph("CNPJ: " + cnpj, fontBody);
        Paragraph agConta = new Paragraph("Agência: " + beneficiario.getAgencia().toString() +
                " | Conta: " + beneficiario.getConta().toString(), fontBody);
        Paragraph data = new Paragraph("Emitido em: " + dataHoraFormat.format(LocalDateTime.now()));


        Image img = Image.getInstance("src/main/resources/bradesco-logo.png");
        img.scalePercent(4f, 4f);

        header.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(img);
        document.add(header);
        document.add(razao);
        document.add(cnpjHeader);
        document.add(agConta);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.4f, 1.4f, 1.5f, 1.5f, 1.5f, 2f, 2f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.add(data);

        document.close();

    }
}