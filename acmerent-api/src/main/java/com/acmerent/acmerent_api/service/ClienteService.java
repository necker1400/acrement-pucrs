package com.acmerent.acmerent_api.service;

import com.acmerent.acmerent_api.model.Cliente;
import com.acmerent.acmerent_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Lista todos os clientes cadastrados
     * 
     * @return Lista de clientes
     */
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Busca um cliente pelo codigo
     * 
     * @param codigo Codigo do cliente
     * @return Cliente encontrado
     * @throws RuntimeException se o cliente nao for encontrado
     */
    public Cliente buscarPorCodigo(Long codigo) {
        Cliente cliente = clienteRepository.findByCodigo(codigo);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com o código: " + codigo);
        }
        return cliente;
    }
}