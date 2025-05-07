package com.acmerent.acmerent_api.repository;

import com.acmerent.acmerent_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    /**
     * Encontra um cliente pelo codigo
     * 
     * @param codigo Codigo do cliente
     * @return Cliente encontrado ou null se nao encontrado
     */
    Cliente findByCodigo(Long codigo);
}