package com.acmerent.acmerent_api.controller;

import com.acmerent.acmerent_api.model.Automovel;
import com.acmerent.acmerent_api.model.StatusAutomovel;
import com.acmerent.acmerent_api.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/acmerent")
public class AutomovelController {

    private final AutomovelService automovelService;

    @Autowired
    public AutomovelController(AutomovelService automovelService) {
        this.automovelService = automovelService;
    }

    /**
     * Lista todos os automu00f3veis cadastrados
     * 
     * @return Lista de automu00f3veis
     */
    @GetMapping("/listaautomoveis")
    public ResponseEntity<List<Automovel>> listarAutomoveis() {
        List<Automovel> automoveis = automovelService.listarTodos();
        return ResponseEntity.ok(automoveis);
    }

    /**
     * Verifica se um automu00f3vel estu00e1 disponu00edvel para locau00e7u00e3o
     * 
     * @param requestBody Corpo da requisiu00e7u00e3o contendo o ID do automu00f3vel
     * @return true se o automu00f3vel estu00e1 disponu00edvel, false caso contru00e1rio
     */
    @PostMapping("/validaautomovel")
    public ResponseEntity<?> validarAutomovel(@RequestBody Map<String, Long> requestBody) {
        try {
            Long id = requestBody.get("id");
            boolean disponivel = automovelService.validarDisponibilidade(id);
            return ResponseEntity.ok(Map.of("disponivel", disponivel));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    /**
     * Atualiza o status de um automu00f3vel
     * 
     * @param id ID do automu00f3vel
     * @param status Novo status do automu00f3vel
     * @return Automu00f3vel atualizado
     */
    @PostMapping("/atendimento/atualizaautomovel/{id}/estado/{status}")
    public ResponseEntity<?> atualizarAutomovel(@PathVariable Long id, @PathVariable String status) {
        try {
            StatusAutomovel statusAutomovel = StatusAutomovel.valueOf(status.toUpperCase());
            Automovel automovel = automovelService.atualizarStatus(id, statusAutomovel);
            return ResponseEntity.ok(automovel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Status invu00e1lido: " + status));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}