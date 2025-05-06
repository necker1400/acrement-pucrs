package com.acmerent.acmerent_api.service;

import com.acmerent.acmerent_api.model.Automovel;
import com.acmerent.acmerent_api.model.StatusAutomovel;
import com.acmerent.acmerent_api.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomovelService {

    private final AutomovelRepository automovelRepository;

    @Autowired
    public AutomovelService(AutomovelRepository automovelRepository) {
        this.automovelRepository = automovelRepository;
    }

    /**
     * Lista todos os automoveis cadastrados
     * 
     * @return Lista de automoveis
     */
    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    /**
     * Busca um automovel pelo ID
     * 
     * @param id ID do automovel
     * @return Automovel encontrado
     * @throws RuntimeException se o automovel nao for encontrado
     */
    public Automovel buscarPorId(Long id) {
        return automovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automovel não encontrado com o ID: " + id));
    }

    /**
     * Verifica se um automovel esta disponivel para locacao
     * 
     * @param id ID do automovel
     * @return true se o automovel esta disponivel, false caso contrario
     */
    public boolean validarDisponibilidade(Long id) {
        Automovel automovel = buscarPorId(id);
        return automovel.getStatus() == StatusAutomovel.DISPONIVEL;
    }

    /**
     * Atualiza o status de um automovel
     * 
     * @param id ID do automovel
     * @param status Novo status do automovel
     * @return Automovel atualizado
     */
    public Automovel atualizarStatus(Long id, StatusAutomovel status) {
        Automovel automovel = buscarPorId(id);
        automovel.setStatus(status);
        return automovelRepository.save(automovel);
    }
}