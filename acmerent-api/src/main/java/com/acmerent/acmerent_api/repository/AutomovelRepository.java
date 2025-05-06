package com.acmerent.acmerent_api.repository;

import com.acmerent.acmerent_api.model.Automovel;
import com.acmerent.acmerent_api.model.StatusAutomovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    
    /**
     * Encontra automoveis pelo status
     * 
     * @param status Status do automovel
     * @return Lista de automoveis com o status especificado
     */
    List<Automovel> findByStatus(StatusAutomovel status);
    
    /**
     * Encontra um automovel pela placa
     * 
     * @param placa Placa do automovel
     * @return Automovel encontrado ou null se nao encontrado
     */
    Automovel findByPlaca(String placa);
}
