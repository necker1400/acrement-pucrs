package com.acmerent.acmerent_api.repository;

import com.acmerent.acmerent_api.model.Automovel;
import com.acmerent.acmerent_api.model.StatusAutomovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    
    /**
     * Encontra automu00f3veis pelo status
     * 
     * @param status Status do automu00f3vel
     * @return Lista de automu00f3veis com o status especificado
     */
    List<Automovel> findByStatus(StatusAutomovel status);
    
    /**
     * Encontra um automu00f3vel pela placa
     * 
     * @param placa Placa do automu00f3vel
     * @return Automu00f3vel encontrado ou null se nu00e3o encontrado
     */
    Automovel findByPlaca(String placa);
}
