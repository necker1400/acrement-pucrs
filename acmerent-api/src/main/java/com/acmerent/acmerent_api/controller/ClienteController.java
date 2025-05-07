package com.acmerent.acmerent_api.controller;

import com.acmerent.acmerent_api.model.Cliente;
import com.acmerent.acmerent_api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acmerent")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Lista todos os clientes cadastrados
     * 
     * @return Lista de clientes
     */
    @GetMapping("/listaclientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Consulta um cliente pelo codigo
     * 
     * @param codigo Codigo do cliente
     * @return Cliente encontrado
     */
    @GetMapping("/consultacliente")
    public ResponseEntity<?> consultarCliente(@RequestParam Long codigo) {
        try {
            Cliente cliente = clienteService.buscarPorCodigo(codigo);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}