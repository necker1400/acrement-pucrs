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
     * Lista todos os automu00f3veis cadastrados
     * 
     * @return Lista de automu00f3veis
     */
    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    /**
     * Busca um automu00f3vel pelo ID
     * 
     * @param id ID do automu00f3vel
     * @return Automu00f3vel encontrado
     * @throws RuntimeException se o automu00f3vel nu00e3o for encontrado
     */
    public Automovel buscarPorId(Long id) {
        return automovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automu00f3vel nu00e3o encontrado com o ID: " + id));
    }

    /**
     * Verifica se um automu00f3vel estu00e1 disponu00edvel para locau00e7u00e3o
     * 
     * @param id ID do automu00f3vel
     * @return true se o automu00f3vel estu00e1 disponu00edvel, false caso contru00e1rio
     */
    public boolean validarDisponibilidade(Long id) {
        Automovel automovel = buscarPorId(id);
        return automovel.getStatus() == StatusAutomovel.DISPONIVEL;
    }

    /**
     * Atualiza o status de um automu00f3vel
     * 
     * @param id ID do automu00f3vel
     * @param status Novo status do automu00f3vel
     * @return Automu00f3vel atualizado
     */
    public Automovel atualizarStatus(Long id, StatusAutomovel status) {
        Automovel automovel = buscarPorId(id);
        automovel.setStatus(status);
        return automovelRepository.save(automovel);
    }
}