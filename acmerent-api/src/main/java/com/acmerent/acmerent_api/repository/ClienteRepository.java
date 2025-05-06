package com.acmerent.acmerent_api.repository;

import com.acmerent.acmerent_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    /**
     * Encontra um cliente pelo cu00f3digo
     * 
     * @param codigo Cu00f3digo do cliente
     * @return Cliente encontrado ou null se nu00e3o encontrado
     */
    Cliente findByCodigo(Long codigo);
}