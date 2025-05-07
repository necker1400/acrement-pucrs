package com.acmerent.acmerent_api.controller;

import com.acmerent.acmerent_api.model.Locacao;
import com.acmerent.acmerent_api.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/acmerent")
public class LocacaoController {

    private final LocacaoService locacaoService;

    @Autowired
    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    /**
     * Lista todas as locacoes cadastradas
     * 
     * @return Lista de locacoes
     */
    @GetMapping("/listalocacoes")
    public ResponseEntity<List<Locacao>> listarLocacoes() {
        List<Locacao> locacoes = locacaoService.listarTodas();
        return ResponseEntity.ok(locacoes);
    }

    /**
     * Cadastra uma nova locacao
     * 
     * @param locacao Dados da locacao
     * @return Locacao cadastrada
     */
    @PostMapping("/atendimento/cadlocacao")
    public ResponseEntity<?> cadastrarLocacao(@RequestBody Locacao locacao) {
        try {
            Locacao novaLocacao = locacaoService.cadastrar(locacao);
            return ResponseEntity.ok(novaLocacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    /**
     * Finaliza uma locacao
     * 
     * @param requestBody Corpo da requisicao contendo o numero da locacao
     * @return Locacao finalizada
     */
    @PostMapping("/atendimento/finalizalocacao")
    public ResponseEntity<?> finalizarLocacao(@RequestBody Map<String, Long> requestBody) {
        try {
            Long numero = requestBody.get("numero");
            Locacao locacao = locacaoService.finalizar(numero);
            return ResponseEntity.ok(locacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}