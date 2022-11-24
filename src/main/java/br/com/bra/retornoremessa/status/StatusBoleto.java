package br.com.bra.retornoremessa.status;

// Java code to illustrate the put() method
import java.util.*;

public interface StatusBoleto {
    static String status(String s)
    {
        HashMap<String, String> status = new HashMap<String, String>();

       status.put("02", "Entrada Confirmada");
       status.put("03", "Entrada Rejeitada");
       status.put("06", "Liquidação Normal");
       status.put("07", "Conf. Exc. Cadastro Pagador Débito");
       status.put("08", "Rej. Ped. Exc. Cadastro de Pagador Débito");
       status.put("09", "Baixado Automat. via Arquivo");
       status.put("10", "Baixado conforme instruções da Agência");
       status.put("11", "Em Ser - Arquivo de Títulos Pendentes");
       status.put("12", "Abatimento Concedido");
       status.put("13", "Abatimento Cancelado");
       status.put("14", "Vencimento Alterado");
       status.put("15", "Liquidação em Cartório (sem motivo)");
       status.put("16", "Título Pago em Cheque - Vinculado");
       status.put("17", "Liquidação após Baixa ou Título não Registrado");
       status.put("18", "Acerto de Depositária");
       status.put("19", "Confirmação Receb. Inst. de Protesto");
       status.put("20", "Confirmação Recebimento Instrução Sustação de Protesto");
       status.put("21", "Acerto do Controle do Participante");
       status.put("22", "Título com Pagamento Cancelado");
       status.put("23", "Entrada do Título em Cartório");
       status.put("24", "Entrada Rejeitada por CEP Irregular");
       status.put("25", "Confirmação Receb.Inst.de Protesto Falimentar");
       status.put("27", "Baixa Rejeitada");
       status.put("28", "Débito de Tarifas/Custas");
       status.put("29", "Ocorrências do Pagador");
       status.put("30", "Alteração de Outros Dados Rejeitados");
       status.put("31", "Confirmado Inclusão Cadastro Pagador");
       status.put("32", "Instrução Rejeitada");
       status.put("33", "Confirmação Pedido Alteração Outros Dados");
       status.put("34", "Retirado de Cartório e Manutenção Carteira");
       status.put("35", "Cancelamento do Agendamento do Débito Automático");
       status.put("37", "Rejeitado Inclusão Cadastro Pagador");
       status.put("38", "Confirmado Alteração Pagador");
       status.put("39", "Rejeitado Alteração Cadastro Pagador");
       status.put("40", "Estorno de Pagamento");
       status.put("55", "Sustado Judicial");
       status.put("66", "Título Baixado por Pagamento via Pix");
       status.put("68", "Acerto dos Dados do Rateio de Crédito");
       status.put("69", "Cancelamento de Rateio");
       status.put("73", "Confirmação Receb. Pedido de Negativação");
       status.put("74", "Confir Pedido de Excl de Negat (com ou sem baixa)");

        return status.get(s);
    }
}


