package br.com.bra.retornoremessa.controller;

import br.com.bra.retornoremessa.entity.Boleto;
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
@RequestMapping("/boleto")
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
            System.out.println(data);
            for (String linha : data.subList(1, data.size())) {
                String nossoNumS = linha.substring(0, 2);
                Long nossoNum = parseLong(nossoNumS);
                String numDoc = linha.substring(5, 10);
                String valor = linha.substring(128, 140);
                Boleto boleto = new Boleto(nossoNum, numDoc, valor);
                System.out.println(boleto);
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
}